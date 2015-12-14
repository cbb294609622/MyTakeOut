package activity.cbb.com.mytakeout.fragment;

import android.view.View;

import activity.cbb.com.mytakeout.R;
import activity.cbb.com.mytakeout.base.BaseFragment;

/**
 * Created by BoBo on 2015/12/14.
 */
public class ShopFragment extends BaseFragment {
    @Override
    public View initView() {
        view = View.inflate(mContext,R.layout.fragment_shop,null);
        return view;
    }

    @Override
    public void initData() {

    }
}
