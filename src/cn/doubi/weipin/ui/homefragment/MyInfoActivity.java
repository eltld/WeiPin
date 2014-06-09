package cn.doubi.weipin.ui.homefragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.ResultCode;
import cn.doubi.weipin.domain.UrlContent;
import cn.doubi.weipin.ui.BaseActivity;
import cn.doubi.weipin.utils.Logger;
import cn.doubi.weipin.utils.WeiPinUtil;

import com.google.gson.Gson;

public class MyInfoActivity extends BaseActivity {

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_myinfo);
		initViews();
	}

	@Override
	protected void initViews() {
		//初始化本地数据
		mListView = (ListView) findViewById(R.id.myinfo_listview);
		mListView.setAdapter(new MyAdapter());
		mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
		mUerInfoCache = WeiPinUtil.getUserInfo(this);
		if(mUerInfoCache.size() == mParamsKey.length ){
			mRatingBar.setRating(5.0f);
		}
		TextView telview = (TextView) findViewById(R.id.usertel);
		TextView userid = (TextView) findViewById(R.id.userid);
		TextView usercount = (TextView) findViewById(R.id.usercount);
		
		initData();
		String tel = WeiPinUtil.getTelBySp(this);
		String id = WeiPinUtil.getUserIdForXml(this);
		int userCount = WeiPinUtil.getUserCount(getApplicationContext());
		usercount.setText("面试次数:"+userCount);
		telview.setText(tel);
		userid.setText("用户ID:"+id);
	}

	private List<TitleAndHitData> mItems = new ArrayList<TitleAndHitData>();
	private RatingBar mRatingBar;

	private void initData() {
		for (int i = 0; i <= 6; i++) {
			TitleAndHitData t = new TitleAndHitData();
			switch (i) {
			case 0:
				t.hit = "请输入姓名(编辑后不可修改)";
				t.title = "姓名";
				break;
			case 1:
				t.hit = "请输入身份证号码(编辑后不可修改)";
				t.title = "身份证";
				break;
			case 2:
				t.hit = "请输入性别";
				t.title = "性别";
				break;
			case 3:
				t.hit = "请输入您的期望薪酬";
				t.title = "期望薪资";

				break;
			case 4:
				t.hit = "例如:研发工程师";
				t.title = "工种";
				break;
			case 5:
				t.hit = "在职急于找工作";
				t.title = "目前状态";
				break;

			default:
				break;
			}
			mItems.add(t);
		}
	}

	private List<EditText> mEdits = new ArrayList<EditText>();

	@Override
	public void onClick(View view) {
		// 编辑完成提交信息
		for (int i = 0; i < mListView.getChildCount(); i++) {
			if (mListView.getChildAt(i) instanceof RelativeLayout) {
				RelativeLayout itemView = (RelativeLayout) mListView.getChildAt(i);
				if (itemView.getId() == R.id.myinfoitem) {
					for (int j = 0; j < itemView.getChildCount(); j++) {
						if (itemView.getChildAt(j) instanceof EditText) {
							EditText et = (EditText) itemView.getChildAt(j);
							if (TextUtils.isEmpty(et.getText().toString())) {
								showToast("个人信息不完整!");
								return;
							} else {
								mEdits.add(et);
							}
						}
					}
				}
			}
		}
		mRatingBar.setRating(5.0f);
		commitUserInfo();
	}

	private Map<String,String> mUerInfoCache = new HashMap<String, String>();
	public static String[] mParamsKey = new String[] { "userName",
			"userIdentitycard", "userSex", "userSalary", "userWork","userStatus" };

	private Map<String, String> mMyInfo = new HashMap<String, String>();
	private void commitUserInfo() {
		String userUrl = UrlContent.COMMIT_USERINFO_HEADERIMAGE;
		AjaxParams params = new AjaxParams();
		params.put("userTel", WeiPinUtil.getTelBySp(this));
		if (WeiPinUtil.isEditUserInfo(this)) {
			String userId = WeiPinUtil.getUserIdForXml(this);
			if (!TextUtils.isEmpty(userId)) {
				userUrl = UrlContent.UPDATE_USER_INFO;
				params.put("userId", userId);
				//推荐人ID
				params.put("userRecom", WeiPinUtil.getRecommend(getApplicationContext()));
				mMyInfo.put("userId", userId);
			}
		}
		for (int i = 0; i < mEdits.size(); i++) {
			if (mEdits.size() == mParamsKey.length) {
				String str = mEdits.get(i).getText().toString();
				params.put(mParamsKey[i], str);
				mMyInfo.put(mParamsKey[i],str);
			}
		}
		mEdits.clear();
		getHttpClient().post(userUrl, params, new AjaxCallBack<String>() {
			private ProgressDialog dialog;

			@Override
			public void onStart() {
				dialog = showBaseProgressDialog();
				super.onStart();
			}

			@Override
			public void onSuccess(String t) {
				dialog.dismiss();
				Logger.i("USERINFO", t);
				ResultCode code = new Gson().fromJson(t, ResultCode.class);
				WeiPinUtil.saveEditUserInfoState(MyInfoActivity.this,
						code.getCode());
				showCommitOkToast();
				//将数据保存到配置文件
				WeiPinUtil.saveMyInfo(mMyInfo,MyInfoActivity.this);
				WeiPinUtil.saveUserCount(getApplicationContext(), 5);
				finish();
				super.onSuccess(t);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				super.onFailure(t, errorNo, strMsg);
				dialog.dismiss();
				showNetErrorToast();
				Logger.e("USERINFO", strMsg, t);
			}
		});
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mParamsKey.length;
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
		public View getView(final int position, View arg1, ViewGroup arg2) {
			View v = getLayoutInflater()
					.inflate(R.layout.myinfo_listitem, null);
			final EditText edit = (EditText) v.findViewById(R.id.myinfoedittext);
			TextView title = (TextView) v.findViewById(R.id.myinfotitle);
			TitleAndHitData data = mItems.get(position);
			if(mUerInfoCache.size() == mParamsKey.length && position == 0){
				//如果有本地数据进入输入
				edit.setInputType(InputType.TYPE_NULL);
				edit.setFocusableInTouchMode(false);
				edit.setFocusable(false);
				edit.setEnabled(false);
				String text = mUerInfoCache.get(mParamsKey[position]);
				edit.setText(text);
			}else if( mUerInfoCache.size() == mParamsKey.length && position == 1){
				edit.setInputType(InputType.TYPE_NULL);
				edit.setFocusableInTouchMode(false);
				edit.setFocusable(false);
				edit.setEnabled(false);
				String text = mUerInfoCache.get(mParamsKey[position]);
				edit.setText(text);
			}
			if (position == 2) {
				edit.setInputType(InputType.TYPE_NULL);
				edit.setFocusableInTouchMode(false);
				edit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						showSexSelectAlert(edit);
					}
				});
			} else if (position == 4) {
				edit.setHint("不限");
				edit.setInputType(InputType.TYPE_NULL);
				edit.setFocusableInTouchMode(false);
				edit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						showGongzhongAlert(edit);

					}
				});

			}  else if (position == 3) {
				edit.setInputType(InputType.TYPE_NULL);
				edit.setFocusableInTouchMode(false);
				edit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						showSalaryDialog(edit);
					}
				});

			}
			else if (position == 5) {
				edit.setHint("离职正在找工作");
				edit.setInputType(InputType.TYPE_NULL);
				edit.setFocusableInTouchMode(false);
				edit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						showWorkStatusAlert(edit);

					}
				});

			}
			if(mUerInfoCache.size() == mParamsKey.length && position == 2){
				edit.setText(mUerInfoCache.get(mParamsKey[position]));
			}
			if(mUerInfoCache.size() == mParamsKey.length && position == 3){
				edit.setText(mUerInfoCache.get(mParamsKey[position]));
			}
//			else {
//				//初始化本地数据
//				
//					edit.setEnabled(true);
//					edit.setFocusable(true);
//					edit.setInputType(InputType.TYPE_CLASS_TEXT);
//					edit.setFocusableInTouchMode(true);
//
//			}
			edit.setHint(data.hit);
			title.setText(data.title);
			return v;
		}

	}

	private void showGongzhongAlert(final EditText et) {
		final String[] items = getResources().getStringArray(
				R.array.gongzhongitem);
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("请选择");
		dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}

		});

		dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				et.setText(items[which]);
			}

		});
		dialog.show();

	}
	private void showWorkStatusAlert(final EditText et) {
		final String[] items = getResources().getStringArray(
				R.array.workstateitem);
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("请选择");
		dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}

		});

		dialog.setSingleChoiceItems(items, 0,  new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				et.setText(items[which]);
			}

		});
		dialog.show();

	}

	private void showSexSelectAlert(final EditText et) {
		final String[] items = getResources().getStringArray(R.array.sexitem);
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("请选择");
		dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}

		});

		dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				et.setText(items[which]);
			}

		});
		dialog.show();
	}
	private void showSalaryDialog(final EditText text){
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		View view = getLayoutInflater().inflate(R.layout.salary_enter_dialog, null);
		final EditText min = (EditText) view.findViewById(R.id.min_edit);
		final EditText max = (EditText) view.findViewById(R.id.max_edit);
		dialog.setTitle("请填写期望薪资");
		dialog.setView(view);
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(TextUtils.isEmpty(min.getText()) || TextUtils.isEmpty(max.getText())){
					showToast("请输入期望薪资");
				}else{
					text.setText(min.getText().toString()+"~"+max.getText().toString());
				}
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		dialog.show();
	}
	class TitleAndHitData {
		String title;
		String hit;
	}
}
