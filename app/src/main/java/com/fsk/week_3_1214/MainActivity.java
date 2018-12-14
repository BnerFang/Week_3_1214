package com.fsk.week_3_1214;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fsk.week_3_1214.adapter.MyNewsAdapter;
import com.fsk.week_3_1214.bean.NewsBean;
import com.fsk.week_3_1214.customer.DividerGridItemDecoration;
import com.fsk.week_3_1214.mvp.presenter.NewsPresenter;
import com.fsk.week_3_1214.mvp.view.NewsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsView, View.OnClickListener {

    private ImageView mImgFh;
    private ImageView mImgXh;
    private RecyclerView mRv;
    private NewsPresenter mPresenter;
    private List<NewsBean.DataBean> mDataBeans = new ArrayList<>();
    private MyNewsAdapter mMyNewsAdapter;
    private boolean isCheck = false;//定义一个boolean值变量,初始值为false
    private DividerGridItemDecoration mDividerGridItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        mImgFh = (ImageView) findViewById(R.id.img_fh);
        mImgXh = (ImageView) findViewById(R.id.img_xh);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mImgXh.setOnClickListener(this);
        mImgFh.setOnClickListener(this);
        //线性布局管理器
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //创建 presenter 实例
        mPresenter = new NewsPresenter(this);
        mPresenter.newsData(mDataBeans);
        mDividerGridItemDecoration = new DividerGridItemDecoration(this);
    }

    /**
     * 访问接口成功
     *
     * @param dataBeans
     */
    @Override
    public void onNewsSuccess(List<NewsBean.DataBean> dataBeans) {
        mMyNewsAdapter = new MyNewsAdapter(MainActivity.this, dataBeans);
        mRv.addItemDecoration(mDividerGridItemDecoration);
        mRv.setAdapter(mMyNewsAdapter);
        //访问接口成功   自定义接口回调
        mMyNewsAdapter.setMyListenar(new MyNewsAdapter.MyListenar() {
            @Override
            public void onClicked(int position, View view) {
                mMyNewsAdapter.deleData(position);//删除
                mMyNewsAdapter.notifyDataSetChanged();//局部刷新
            }
        });
    }

    /**
     * 访问接口失败
     *
     * @param error
     */
    @Override
    public void onNewsFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    /**
     * 防止内存泄露
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.datechView();
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_xh:
                //初始化属性动画
                initAnimator(v);
                break;
            case R.id.img_fh://返回
                this.finish();
                //吐司
                Toast.makeText(this, "退出程序成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //初始化属性动画
    private void initAnimator(View view) {
        mImgXh.setTextDirection(R.drawable.xin);
        //图片平移出去还能弹回来
        //如果没有mimg.getTranslationX()|mImgXh.getTranslationY()则图片不会弹回来
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mImgXh, "translationX", mImgXh.getTranslationX(), -600f, mImgXh.getTranslationX());//, mImgXh.getTranslationX()
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mImgXh, "translationY", mImgXh.getTranslationY(), 1100f, mImgXh.getTranslationY());//, mImgXh.getTranslationY()
        //动画时间
        translationX.setDuration(3000);
        translationY.setDuration(3000);
        //执行动画
        //渐变透明  从不透明到透明在到不透明
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mImgXh, "alpha", 0f, 1f, 0f, 1f);
        alpha.setDuration(3000);//时间
        if (view == mImgXh) {
            //isCheck 初始值为false
            if (isCheck) {//未选中状态
                mImgXh.setImageDrawable(getResources().getDrawable(R.drawable.xin));
            } else {//选中状态
                translationY.start();//开始执行translationY
                translationX.start();//开始执行translationX
                alpha.start();//开始执行渐变透明
                mImgXh.setImageDrawable(getResources().getDrawable(R.drawable.xin_fill));
            }
            isCheck = !isCheck;//选中与未选中状态之间来回反转
        }
    }
}
