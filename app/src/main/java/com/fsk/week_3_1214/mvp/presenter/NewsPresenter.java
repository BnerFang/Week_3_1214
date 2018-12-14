package com.fsk.week_3_1214.mvp.presenter;

import com.fsk.week_3_1214.bean.NewsBean;
import com.fsk.week_3_1214.mvp.NewsCallBack;
import com.fsk.week_3_1214.mvp.model.NewsModel;
import com.fsk.week_3_1214.mvp.view.NewsView;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public class NewsPresenter {
    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenter(NewsView newsView) {
        mNewsView = newsView;
        mNewsModel = new NewsModel();
    }

    //解绑
    public void datechView() {
        mNewsView = null;
    }

    public void newsData(List<NewsBean.DataBean> dataBeans) {
        mNewsModel.newsData(dataBeans, new NewsCallBack() {
            @Override
            public void onSuccess(List<NewsBean.DataBean> dataBeans) {
                mNewsView.onNewsSuccess(dataBeans);
            }

            @Override
            public void onFailed(String error) {
                mNewsView.onNewsFailed(error);
            }
        });
    }
}
