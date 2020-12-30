package com.client.ui.shop;

import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;

import butterknife.BindView;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.txt_counponNumber)
    TextView txtCounponNumber;
    @BindView(R.id.layout_total)
    ConstraintLayout layoutTotal;
    @BindView(R.id.layout_cost)
    ConstraintLayout layoutCost;
    @BindView(R.id.layout_coupon)
    ConstraintLayout layoutCoupon;

    private TextView txtTotalLt,txtTotalRt,txtCostLt,txtCostRt,txtCouponLt,txtCouponRt;

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        txtTotalLt = layoutTotal.findViewById(R.id.txt_left);
        txtTotalRt = layoutTotal.findViewById(R.id.txt_right);
        txtCostLt = layoutCost.findViewById(R.id.txt_left);
        txtCostRt = layoutCost.findViewById(R.id.txt_right);
        txtCouponLt = layoutCoupon.findViewById(R.id.txt_left);
        txtCouponRt = layoutCoupon.findViewById(R.id.txt_right);
        txtTotalLt.setText("商品合计");
        txtCostLt.setText("运费");
        txtCouponLt.setText("优惠券");
    }

    @Override
    protected void initData() {

    }
}
