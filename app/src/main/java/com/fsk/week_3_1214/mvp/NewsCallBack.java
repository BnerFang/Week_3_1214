package com.fsk.week_3_1214.mvp;

import com.fsk.week_3_1214.bean.NewsBean;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public interface NewsCallBack {
    //成功
    void onSuccess(List<NewsBean.DataBean> dataBeans);
    //失败
    void onFailed(String error);
}
