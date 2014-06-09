package cn.doubi.weipin.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.ui.homefragment.FragmentMyInfoNew;
import cn.doubi.weipin.ui.homefragment.FriendFragmet;
import cn.doubi.weipin.ui.homefragment.LuYongFragment;
import cn.doubi.weipin.ui.homefragment.OralFragment;
import cn.doubi.weipin.ui.homefragment.ZhiWeiFragment;
import cn.doubi.weipin.utils.WeiPinUtil;

import com.amap.api.maps.SupportMapFragment;
/**
 * HomeActivity的 Fragment
 */
public class ContentFragment extends SupportMapFragment implements OnClickListener
{
	private ViewPager mHomePager;
	private TextView mHomeTitleText;
	private View rootView;
	private int[] titleIds;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		titleIds = new int[]{R.string.zhiwei,R.string.oral,R.string.offer,R.string.haoyou};
		rootView = inflater.inflate(R.layout.activity_main, null);
		mHomeTitleText = (TextView) rootView.findViewById(R.id.homeTitle);
		mMoreButton = (Button) rootView.findViewById(R.id.more);
		mMoreButton.setOnClickListener(this);
		mHomePager = (ViewPager) rootView.findViewById(R.id.homePager);
		mHomePager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mHomeTitleText.setText(getResources().getString(titleIds[position]));
				changeButtonColor(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		initTableButton(rootView);
		initHomePager();
		return rootView;
	}
	private void initTableButton(View view) {
		LinearLayout tab = (LinearLayout) view.findViewById(R.id.tabParentLayout);
		
		for (int i = 0; i < tab.getChildCount(); i++) {
			Button b = (Button) tab.getChildAt(i);
			mTableButtonList.add(b);
			b.setOnClickListener(this);
		}
		
	}
	private void changeButtonColor(int position){
		for (int i = 0; i < mTableButtonList.size(); i++) {
			if(i == position){
				mTableButtonList.get(i).setBackgroundColor(Color.parseColor("#0576B3"));
			}else{
				mTableButtonList.get(i).setBackgroundColor(Color.parseColor("#00A0DC"));
			}
		}
	}
	private List<Button> mTableButtonList = new ArrayList<Button>();
	private Button mMoreButton;
	private void initHomePager() {
		ZhiWeiFragment mOralFragment = new ZhiWeiFragment();
		LuYongFragment mShareFragment = new LuYongFragment();
		FriendFragmet mMoreFragmet = new FriendFragmet();
		OralFragment mOfferFragment = new OralFragment();
		List<Fragment>  fragments = new ArrayList<Fragment>();
		fragments.add(mOralFragment);
		fragments.add(mOfferFragment);
		fragments.add(mShareFragment);
		fragments.add(mMoreFragmet);
		mHomePager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(), fragments));
		mHomePager.setOffscreenPageLimit(4);
	}
	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	    private List<Fragment> fragmentsList;

	    public MyFragmentPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }

	    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
	        super(fm);
	        this.fragmentsList = fragments;
	    }

	    @Override
	    public int getCount() {
	        return fragmentsList.size();
	    }

	    @Override
	    public Fragment getItem(int arg0) {
	        return fragmentsList.get(arg0);
	    }

	    @Override
	    public int getItemPosition(Object object) {
	        return super.getItemPosition(object);
	    }

	}
	@Override
	public void onResume() {
		super.onResume();
	}
	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.oralButton:
			mHomePager.setCurrentItem(0, true);
			changeButtonColor(0);
			break;
		case R.id.offerButton:
			mHomePager.setCurrentItem(1, true);
			changeButtonColor(1);
			break;
		case R.id.shareButton:
			mHomePager.setCurrentItem(2, true);
			changeButtonColor(2);
			break;
		case R.id.moreButton:
			mHomePager.setCurrentItem(3, true);
			changeButtonColor(3);
			break;
		case R.id.more:
			showMoreWindow();
			break;
		default:
			break;
		}
	}
	private void showMoreWindow(){
		final String[] words = new String[]{"我的资料","建议反馈","关于微聘"};
		ListView moreList = (ListView) LayoutInflater.from(getActivity()).inflate(R.layout.more_listview, null);
		moreList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pview, View v, int position,
					long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getActivity(),FragmentMyInfoNew.class));
					break;
				case 1:
					startActivity(new Intent(getActivity(),SuggestActivity.class));
					break;
				case 2:
					startActivity(new Intent(getActivity(),AboutActivity.class));
					break;

				default:
					break;
				}
			}
		});
		moreList.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup g) {
				String text =  words[position];
				TextView itemView = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.more_item, null);
				itemView.setText(text);
				return itemView;
			}
			
			@Override
			public long getItemId(int arg0) {
				return 0;
			}
			
			@Override
			public Object getItem(int arg0) {
				return null;
			}
			
			@Override
			public int getCount() {
				return words.length;
			}
		});
		PopupWindow pop = new PopupWindow(moreList , WeiPinUtil.dip2px(120, getActivity()) , WeiPinUtil.dip2px(168, getActivity()),true);
		pop.setBackgroundDrawable(new ColorDrawable(-000000));
		pop.showAsDropDown(mMoreButton);
	}
}

