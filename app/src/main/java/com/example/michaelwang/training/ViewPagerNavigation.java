package com.example.michaelwang.training;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNavigation extends AppCompatActivity {

    private ViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1,textView2,textView3;
    private List<View> views;// Tab页面列表
    private View view1,view2,view3;//各个页卡
    private List<TextView> textViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_pager_navigation);

        InitTextView();
        InitViewPager();
    }

    private void InitViewPager() {

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        views = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        view1 = li.inflate(R.layout.first_view_layout,null);
        view2 = li.inflate(R.layout.sencond_view_layout,null);
        view3 = li.inflate(R.layout.third_view_layout,null);

        views.add(view1);
        views.add(view2);
        views.add(view3);

        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setTextColor(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private class MyViewPagerAdapter extends PagerAdapter{

        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews){
            this.mListViews = mListViews;
        }

        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView(mListViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
    private void InitTextView() {
        textView1 = (TextView)findViewById(R.id.textFirst);
        textView2 = (TextView)findViewById(R.id.textSecond);
        textView3 = (TextView)findViewById(R.id.textThird);

        textViews = new ArrayList<TextView>();
        textViews.add(textView1);
        textViews.add(textView2);
        textViews.add(textView3);

        textView1.setOnClickListener(new TextViewOnClickListener(0));
        textView2.setOnClickListener(new TextViewOnClickListener(1));
        textView3.setOnClickListener(new TextViewOnClickListener(2));

        setTextColor(0);
    }


    private class TextViewOnClickListener implements View.OnClickListener{

        int index;

        public TextViewOnClickListener(int i){
            index = i;
        }

        @Override
        public void onClick(View view) {
            viewPager.setCurrentItem(index);
            setTextColor(index);
        }
    }


    private void setTextColor(int index){
        for(int i = 0; i < textViews.size(); i++){
            textViews.get(i).setTextColor(Color.parseColor("#000000"));
        }

        textViews.get(index).setTextColor(Color.parseColor("#0000FF"));
    }
}
