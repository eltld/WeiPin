package cn.doubi.weipin.ui.homefragment;

import com.google.gson.Gson;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.ResultCode;
import cn.doubi.weipin.domain.UrlContent;
import cn.doubi.weipin.ui.BaseActivity;
import cn.doubi.weipin.utils.Logger;
import cn.doubi.weipin.utils.WeiPinUtil;

public class HireDetailActivity extends BaseActivity {

	private ListView mListView;

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hire_detail);
		initViews();
	};
	@Override
	protected void initViews() {
		mListView = (ListView) findViewById(R.id.hireList);
		mToudiBt = (Button) findViewById(R.id.toudibt);
		initData();
		String type = getIntent().getStringExtra("type");
		if(!TextUtils.isEmpty(type)){
			mToudiBt.setVisibility(View.GONE);
		}else{
			mToudiBt.setVisibility(View.VISIBLE);
		}
		mDatas = getIntent().getStringArrayExtra("oral");
		mListView.setAdapter(new MyAdapter(mDatas));
	}

	private String[] mItemTitles;
	private Button mToudiBt;
	private String[] mDatas;
	
	private void initData() {
		mItemTitles = new String[]{this.getString(R.string.com_name),this.getString(R.string.gangwei),this.getString(R.string.oral_time_str),this.getString(R.string.com_address_str),this.getString(R.string.tel_number_str)};
	}
	@Override
	public void onClick(View view) {
		int count = WeiPinUtil.getUserCount(getApplicationContext());
		if(count < 0){
			showToast("您的面试次数为0,可以通过分享好友注册获得面试次数.每分享给10个好友注册将获得5次面试机会");
		}else{
			postToudi();
		}
	}
	private void postToudi(){
		AjaxParams params = new AjaxParams();
		params.put("oralRst", "1"); 
		params.put("oralId",mDatas[mDatas.length-1]);
		getHttpClient().post(UrlContent.OREL_TOUDI,params,new AjaxCallBack<String>() {
			ProgressDialog dialog = showBaseProgressDialog();
			public void onSuccess(String t) {
				dialog.dismiss();
				if(!TextUtils.isEmpty(t)){
					ResultCode code = new Gson().fromJson(t, ResultCode.class);
					if(code != null){
						if(code.getCode().equals("1")){
							showToast("申请面试成功");
							int count = WeiPinUtil.getUserCount(getApplicationContext());
							WeiPinUtil.saveUserCount(getApplicationContext(), --count);
						}else{
							showToast("申请面试失败");
						}
					}
				}
			};
			public void onStart() {
				dialog.show();
			};
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				dialog.dismiss();
				Logger.e("TOUDI", strMsg, t);
			};
		} );
	}
	class MyAdapter extends BaseAdapter{
		private String[] mData;
		public MyAdapter(String[] mData) {
			this.mData = mData;
		}
		@Override
		public int getCount() {
			return mItemTitles.length;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = View.inflate(HireDetailActivity.this, R.layout.list_hire_item, null);
			TextView title = (TextView) v.findViewById(R.id.hireTitle);
			title.setText(mItemTitles[position]);
			TextView content = (TextView) v.findViewById(R.id.hireContent);
			content.setText(mData[position]);
			return v;
		}
		
	}
}
