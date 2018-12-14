package com.fsk.week_3_1214.mvp.view;

import com.fsk.week_3_1214.bean.NewsBean;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public interface NewsView {
    //成功
    void onNewsSuccess(List<NewsBean.DataBean> dataBeans);
    //失败
    void onNewsFailed(String error);
}
