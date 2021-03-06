package activity.cbb.com.mytakeout;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import activity.cbb.com.mytakeout.base.BaseActivity;
import activity.cbb.com.mytakeout.utils.ActivityAnimUitl;
import activity.cbb.com.mytakeout.utils.MyToastUitl;
import activity.cbb.com.mytakeout.utils.SharedPreferencesUitl;
import activity.cbb.com.mytakeout.view.DepthPageTransformer;

/**
 * Created by BoBo on 2015/12/15.
 */
public class GuideActivity extends BaseActivity{

    private ViewPager guide_vp;
    private Button guide_btn;
    private List<View> pageViews;
    private LinearLayout guide_ll;
    private List<View> viewList;

    @Override
    public void initView() {
        mContext = GuideActivity.this;
        setContentView(R.layout.activity_guide);
        guide_vp = (ViewPager) findViewById(R.id.guide_vp);
        guide_btn = (Button) findViewById(R.id.guide_btn);
        guide_ll = (LinearLayout) findViewById(R.id.guide_ll);
        init();
    }

    @Override
    public void initData() {
        boolean isForst = SharedPreferencesUitl.getBooleanData(mContext, "isForst", false);
        if (isForst) {
            //查看是否是从设置界面过来的
            boolean isSettingFlag = SharedPreferencesUitl.getBooleanData(mContext, "isSettingFlag", false);
            if (!isSettingFlag) {
                //不是第一次进入应用
                MyToastUitl.showToastFlag(mContext, "不是第一次进入");
                forwardSplash();
                return;
            }
            initFill();

        } else {
            //是第一次进入应用
            MyToastUitl.showToastFlag(mContext, "是第一次进入");
            SharedPreferencesUitl.saveBooleanData(mContext, "isForst", true);
            initFill();
        }
    }

    private void forwardSplash() {
        startActivity(new Intent(mContext, MainActivity.class));
        ActivityAnimUitl.isRightLeft(GuideActivity.this);
        SharedPreferencesUitl.saveBooleanData(mContext, "isSettingFlag", false);
        finish();
    }

    private void initFill() {
        initPager();
        initCirclePoint();
    }

    /**
     * 小圆点
     */
    private void initCirclePoint() {
        //清理点所在的线性布局
        guide_ll.removeAllViews();
        viewList = new ArrayList<View>();
        for (int i = 0; i < pageViews.size(); i++) {
            View view = new View(mContext);
            if (i == 0) {
                view.setBackgroundResource(R.mipmap.point_focused);
            } else {
                view.setBackgroundResource(R.mipmap.point_unfocused);
            }
            //1,定义点的宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //2,给点设置间距
            layoutParams.setMargins(0, 0, 6, 0);
            //3,作用当前规则给子控件
            guide_ll.addView(view, layoutParams);
            viewList.add(view);
        }
    }


    /**
     * ViewPager的各种配置
     */
    private void initPager() {
        //数据适配器
        guide_vp.setAdapter(new GuideMyAdapter());
        //动画
        guide_vp.setPageTransformer(true, new DepthPageTransformer());
        //监听
        guide_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == pageViews.size() - 1) {
                    guide_btn.setVisibility(View.VISIBLE);
                    guide_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            forwardSplash();
                        }
                    });
                } else {
                    guide_btn.setVisibility(View.GONE);
                }

                //处理点的逻辑
                for (int i = 0; i < viewList.size(); i++) {
                    if (i == position) {
                        viewList.get(position).setBackgroundResource(R.mipmap.point_focused);
                    } else {
                        viewList.get(i).setBackgroundResource(R.mipmap.point_unfocused);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    /**
     * 数据填充
     */
    private void init() {
        pageViews = new ArrayList<View>();

        ImageView img1 = new ImageView(mContext);
        img1.setBackgroundResource(R.mipmap.pager1);
        pageViews.add(img1);

        ImageView img2 = new ImageView(mContext);
        img2.setBackgroundResource(R.mipmap.pager2);
        pageViews.add(img2);

        ImageView img3 = new ImageView(mContext);
        img3.setBackgroundResource(R.mipmap.pager3);
        pageViews.add(img3);

        ImageView img4 = new ImageView(mContext);
        img4.setBackgroundResource(R.mipmap.pager4);
        pageViews.add(img4);

        ImageView img5 = new ImageView(mContext);
        img5.setBackgroundResource(R.mipmap.pager5);
        pageViews.add(img5);
    }
    /**
     * ViewPager的数据适配器
     */
    class GuideMyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        // 添加
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageViews.get(position));
            return pageViews.get(position);
        }

        // 删除
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
