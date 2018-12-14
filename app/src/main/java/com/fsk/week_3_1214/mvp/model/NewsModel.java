package com.fsk.week_3_1214.mvp.model;

import com.fsk.week_3_1214.bean.NewsBean;
import com.fsk.week_3_1214.mvp.NewsCallBack;
import com.fsk.week_3_1214.util.OkHttpUtil;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public class NewsModel {

    public void newsData(List<NewsBean.DataBean> dataBeans, final NewsCallBack callBack){
        String newsUrl = "http://www.xieast.com/api/news/news.php";
        new OkHttpUtil().OkHttpGet(newsUrl).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(data, NewsBean.class);
                List<NewsBean.DataBean> beans = newsBean.getData();
                if (!newsBean.getCode().equals("1")) {
                    callBack.onFailed(newsBean.getMsg());
                }else {
                    callBack.onSuccess(beans);
                }
            }

            @Override
            public void OkHttpError(String error) {

            }
        });
    }

}
