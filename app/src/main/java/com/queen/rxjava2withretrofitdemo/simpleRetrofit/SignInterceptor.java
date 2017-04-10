package com.queen.rxjava2withretrofitdemo.simpleRetrofit;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.queen.rxjava2withretrofitdemo.utils.SysUtils;
import com.queen.rxjava2withretrofitdemo.utils.UrlEncodeUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by liukun on 2017/4/5.
 */

public class SignInterceptor implements Interceptor {

    public static final String TAG = SignInterceptor.class.getSimpleName();

    private TreeMap<String, String> mParamMap;


    @Override
    public Response intercept(Chain chain) throws IOException {

        long startTime = System.currentTimeMillis();
        mParamMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        Request originRequest = chain.request();

        Request.Builder requestBuilder = originRequest.newBuilder();

        Log.e(TAG, "originRequest.url() = " + originRequest.url());

        //添加基础参数
        addBasicParams(originRequest, requestBuilder);

        //添加header
        addHeader(originRequest, requestBuilder);

        Log.e(TAG, "timeCost=" + (System.currentTimeMillis() - startTime));

        return chain.proceed(requestBuilder.build());
    }

    /**
     * 添加HttpHeader
     *
     * @param requestBuilder
     */
    private void addHeader(Request originRequest, Request.Builder requestBuilder) {
        long time = System.currentTimeMillis() / 1000;

        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        requestBuilder.addHeader("Auth-Appkey", "1003");
        requestBuilder.addHeader("Auth-Timestamp", time + "");
        requestBuilder.addHeader("Auth-Sign", getSignString(originRequest, time));
        requestBuilder.addHeader("Client-Info", "client-info");

        mParamMap = null;
    }


    /**
     * 添加基础参数
     *
     * @param request
     * @param requestBuilder
     */
    private void addBasicParams(Request request, Request.Builder requestBuilder) {
        if (request == null) {
            return;
        }

        String bodyToString = "";
        if ("GET".equals(request.method())) {
            //将参数添加到url后
            bodyToString = urlQueryToString(request);
        } else {
            //将参数转化为Map，添加基础参数，在转为body
            bodyToString = bodyToString(request);
        }

        //将参数字符串转换为map
        paramStrToMap(bodyToString);

        //添加基础参数到map
        builtBaseParams();

        if ("GET".equals(request.method())) {
            //将map在拼回url
            HttpUrl url = request.url().newBuilder().query(mapToParamString(true)).build();
            requestBuilder.url(url);

        } else {
            //将map转化为body
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8")
                    , mapToParamString(true)));
        }
    }


    /**
     * 将参数字符串转换为Map
     *
     * @param paramsStr
     * @return
     */
    private Map<String, String> paramStrToMap(String paramsStr) {

        if (TextUtils.isEmpty(paramsStr)) {
            return mParamMap;
        }

        String[] paramArray = paramsStr.split("&");

        if (paramArray == null) {
            return mParamMap;
        }

        for (int i = 0, size = paramArray.length; i < size; i++) {
            String[] entryStr = paramArray[i].split("=");
            if (entryStr != null && entryStr.length >= 2) {
                mParamMap.put(entryStr[0], entryStr[1]);
            }
        }

        return mParamMap;
    }

    /**
     * 将requestBody转换为String
     *
     * @param request
     * @return
     */
    private String bodyToString(Request request) {
        if (request == null) {
            return "";
        }
        Buffer buffer = new Buffer();
        try {
            request.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 获取get请求中的请求参数
     *
     * @param request
     * @return
     */
    private String urlQueryToString(Request request) {
        if (request == null) {
            return "";
        }

        HttpUrl httpUrl = request.url();
        return httpUrl.query();
    }

    /**
     * 添加基本参数
     */
    private void builtBaseParams() {

        mParamMap.put("appfrom", "appfrom");
        mParamMap.put("time_zone", "time_zone");
        mParamMap.put("platform", "2");
        mParamMap.put("region", "chinese");
        mParamMap.put("appver", "2.5.0");
        mParamMap.put("version_code", "2.5");
        mParamMap.put("user_id", "user_id");
    }

    /**
     * 将map转换为string
     * @return
     */
    private String mapToParamString(boolean needSeparator) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> i = mParamMap.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            String val = mParamMap.get(key);

            if (null != val) {
                try {
                    if (UrlEncodeUtils.isUtf8Url(val)) {
                        val = Uri.decode(val);
                    }
                } catch (Exception ex) {

                }
                if (null != val) {
                    sb.append(key + "=" + UrlEncodeUtils.encode(val));
                }
                if (needSeparator && i.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }


    /**
     * 生成签名
     * @param originRequest
     * @param time
     * @return
     */
    protected String getSignString(Request originRequest, long time) {
        StringBuilder sb = new StringBuilder();


        if (originRequest.method().equals("GET")) {
            sb.append("GET#");
        } else {
            sb.append("POST#");
        }
        sb.append(originRequest.url().encodedPath() + "#");
        sb.append(time + "#");
        sb.append("AppConstants.APP_ID" + "#");
//        sb.append(AppConstants.APP_ID + "#");
        sb.append("omg#");
        sb.append("AppConstants.APP_SECRET" + "#");
//        sb.append(AppConstants.APP_SECRET + "#");

        sb.append(mapToParamString(false));

        String signStr = sb.toString();
        Log.e(TAG, "signStr=" + signStr);
        return SysUtils.stringToMD5(signStr);
    }
}
