package cn.doubi.weipin.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.ui.homefragment.VerifyActivity;
import cn.doubi.weipin.utils.WeiPinUtil;

/**
 * 欢迎界面,当第一次运行和版本更新的时候打开次界面RR
 */
public class WelcomeActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ViewPager viewPager = new ViewPager(this);
		ImageView i1 = new ImageView(this);
		i1.setBackgroundResource(R.drawable.wel1);
		ImageView i2 = new ImageView(this);
		i2.setBackgroundResource(R.drawable.wel2);
		i2.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				finish();
				if(WeiPinUtil.isVerify(WelcomeActivity.this)){
					startActivity(new Intent(WelcomeActivity.this,HomeActivity.class));
				}else{
					startActivity(new Intent(WelcomeActivity.this,VerifyActivity.class));
				}
			}
		});
		List<View> items = new ArrayList<View>();
		items.add(i1);
		items.add(i2);
		viewPager.setAdapter(new MyPagerAdapter(items));
		setContentView(viewPager);
	}
	@Override
	protected void initViews()
	{

	}

	@Override
	public void onClick(View view)
	{

	}

	class MyPagerAdapter extends PagerAdapter{
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}
		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}
	}
}

