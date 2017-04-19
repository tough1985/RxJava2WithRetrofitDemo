package com.queen.mvplib.BaseMvp;

/**
 * Created by liukun on 2017/4/18.
 */

public interface MvpView {

    /**
     * 显示错误信息
     * @param errMes
     */
    void showError(String errMes);

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 显示空态
     */
    void showNull();

    /**
     * 隐藏loading
     */
    void dismissLoading();

    /**
     * view是否可用
     * @return
     */
    boolean isAlived();
}
