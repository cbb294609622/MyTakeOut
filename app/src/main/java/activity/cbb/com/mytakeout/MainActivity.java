package activity.cbb.com.mytakeout;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.cbb.com.mytakeout.base.BaseActivity;
import activity.cbb.com.mytakeout.fragment.MyFragment;
import activity.cbb.com.mytakeout.fragment.ShopFragment;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragment;
    private LinearLayout ly1_shop, ly2_my;
    private ImageView img1_shop, img2_my;
    private TextView tv1_shop, tv2_my;

    private Fragment shopFragment;
    private Fragment myFragment;


    @Override
    public void initView() {
        mContext = MainActivity.this;
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ly1_shop = (LinearLayout) findViewById(R.id.ly1_shop);
        ly2_my = (LinearLayout) findViewById(R.id.ly2_my);

        img1_shop = (ImageView) findViewById(R.id.img1_shop);
        img2_my = (ImageView) findViewById(R.id.img2_my);

        tv1_shop = (TextView) findViewById(R.id.tv1_shop);
        tv2_my = (TextView) findViewById(R.id.tv2_my);

        mFragment = new ArrayList<Fragment>();
        shopFragment = new ShopFragment();
        myFragment = new MyFragment();


        mFragment.add(shopFragment);
        mFragment.add(myFragment);
        adapterData();
        viewPager.setAdapter(mAdapter);
        onPagerListener();
        initEvent();
        setSelect(0);

    }

    private void initEvent() {
        ly1_shop.setOnClickListener(new MyOnClickListener(this));
        ly2_my.setOnClickListener(new MyOnClickListener(this));
    }
    class MyOnClickListener implements View.OnClickListener {

        private Context context;

        public MyOnClickListener(Context mContext) {
            this.context = mContext;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ly1_shop:
                    setSelect(0);
                    break;
                case R.id.ly2_my:
                    setSelect(1);
                    break;
            }

        }
    }
    public void setSelect(int i) {
        resetImgText();
        switch (i) {
            case 0:
                img1_shop.setImageResource(R.mipmap.tab_home_pressed);
                tv1_shop.setTextColor(android.graphics.Color.parseColor("#1D9E9E"));
                break;
            case 1:
                img2_my.setImageResource(R.mipmap.tab_mine_pressed);
                tv2_my.setTextColor(android.graphics.Color.parseColor("#1D9E9E"));
                break;
        }
        viewPager.setCurrentItem(i);
    }
    /**
     * 将所以的图片切换暗色
     */
    private void resetImgText() {
        img1_shop.setImageResource(R.mipmap.tab_home_normal);
        img2_my.setImageResource(R.mipmap.tab_mine_normal);
        tv1_shop.setTextColor((android.graphics.Color.parseColor("#919191")));
        tv2_my.setTextColor((android.graphics.Color.parseColor("#919191")));

    }


    private void onPagerListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = viewPager.getCurrentItem();
                setSelect(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void adapterData() {
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragment.get(arg0);
            }
        };
    }
}
