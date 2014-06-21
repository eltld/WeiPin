package cn.doubi.weipin.ui.homefragment;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.ResultCode;
import cn.doubi.weipin.domain.UrlContent;
import cn.doubi.weipin.domain.UserScore;
import cn.doubi.weipin.ui.BaseActivity;
import cn.doubi.weipin.utils.Logger;
import cn.doubi.weipin.utils.WeiPinUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FragmentMyInfoNew extends BaseActivity implements OnClickListener {

	public static String[] mParamsKey = new String[] { "userName",
			"userIdentitycard", "userSalary", "userWork", "userStatus" };
	private EditText mNameEt;
	private EditText mIdCardEt;
	private Button mXinziEt;
	private Button mGongZhongEt;
	private Button mZhuangTaiEt;
	private Map<String, String> mUerInfoCache;
	private Map<String, String> mMyInfo = new HashMap<String, String>();
	private RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_myinfo_new);
		initViews();
	}

	@Override
	protected void initViews() {
		mNameEt = (EditText) findViewById(R.id.name_et);
		mIdCardEt = (EditText) findViewById(R.id.idcard_et);
		mXinziEt = (Button) findViewById(R.id.xinzi_et);
		mGongZhongEt = (Button) findViewById(R.id.gongzhong_et);
		mZhuangTaiEt = (Button) findViewById(R.id.zhuangtai_et);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		TextView telview = (TextView) findViewById(R.id.usertel);
		TextView userid = (TextView) findViewById(R.id.userid);
		TextView usercount = (TextView) findViewById(R.id.usercount);
		String tel = WeiPinUtil.getTelBySp(this);
		String id = WeiPinUtil.getUserIdForXml(this);
		int userCount = WeiPinUtil.getUserCount(getApplicationContext());
		usercount.setText("面试次数:"+userCount);
		telview.setText(tel);
		userid.setText("用户ID:"+id);
		mXinziEt.setOnClickListener(this);
		mGongZhongEt.setOnClickListener(this);
		mZhuangTaiEt.setOnClickListener(this);

		mUerInfoCache = WeiPinUtil.getUserInfo(this);
		String userName = mUerInfoCache.get(mParamsKey[0]);
		String idCard = mUerInfoCache.get(mParamsKey[1]);
		String xinzi = mUerInfoCache.get(mParamsKey[2]);
		String gongzhong = mUerInfoCache.get(mParamsKey[3]);
		String zhuangtai = mUerInfoCache.get(mParamsKey[4]);
		if (TextUtils.isEmpty(userName)) {
			// 没填写过
			ratingBar.setRating(0f);
		} else {
			//身份证,名字不可编辑
			mNameEt.setInputType(InputType.TYPE_NULL);
			mNameEt.setFocusableInTouchMode(false);
			mNameEt.setFocusable(false);
			mNameEt.setEnabled(false);
			mIdCardEt.setInputType(InputType.TYPE_NULL);
			mIdCardEt.setFocusableInTouchMode(false);
			mIdCardEt.setFocusable(false);
			mIdCardEt.setEnabled(false);
			ratingBar.setRating(5.0f);
			mNameEt.setText(userName);
			mIdCardEt.setText(idCard);
			mXinziEt.setText(xinzi);
			mGongZhongEt.setText(gongzhong);
			mZhuangTaiEt.setText(zhuangtai);
			//获取评分
			getUserScore();
		}
	}

	private void getUserScore() {
		final String userId = WeiPinUtil.getUserId(this);
		if(TextUtils.isEmpty(userId)){
			showToast("获取评分失败");
		}else{
			AjaxParams params  = new AjaxParams();
			params.put("userId", userId);
			getHttpClient().post(UrlContent.URL_USER_SCORE,params, new AjaxCallBack<String>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				super.onFailure(t, errorNo, strMsg);
				Logger.e("score", strMsg, t);
				showToast("获取评分失败");
				ratingBar.setRating(0);
			}
			@Override
			public void onSuccess(String t) {
				super.onSuccess(t);
				Logger.i("score", t);
				float score = 0.0f;
				if(!TextUtils.isEmpty(t) && t.contains("scoreWork")){
					List<UserScore> us = new Gson().fromJson(t,  new TypeToken<List<UserScore>>(){}.getType());
					if(us != null){
						UserScore u = us.get(0);
						if(u != null){
							score = (u.getScore_oral() + u.getScore_tel() + u.getScore_work())/3;
							if(score > 5.0f){
								score = 5;
							}
						}
					}
				}
				ratingBar.setRating(score);
			}
		});
		}
	}

	private void commitUserInfo() {
		ratingBar.setRating(0f);
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
		params.put(mParamsKey[0], mNameEt.getText().toString());
		params.put(mParamsKey[1], mIdCardEt.getText().toString());
		params.put(mParamsKey[2], mXinziEt.getText().toString());
		params.put(mParamsKey[3], mGongZhongEt.getText().toString());
		params.put(mParamsKey[4], mZhuangTaiEt.getText().toString());
		
		mMyInfo.put(mParamsKey[0], mNameEt.getText().toString());
		mMyInfo.put(mParamsKey[1], mIdCardEt.getText().toString());
		mMyInfo.put(mParamsKey[2], mXinziEt.getText().toString());
		mMyInfo.put(mParamsKey[3], mGongZhongEt.getText().toString());
		mMyInfo.put(mParamsKey[4], mZhuangTaiEt.getText().toString());
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
				if(!WeiPinUtil.isEditUserInfo(FragmentMyInfoNew.this)){
					WeiPinUtil.saveEditUserInfoState(FragmentMyInfoNew.this,
							code.getCode());
					WeiPinUtil.saveUserCount(getApplicationContext(), 5);
				}
				showCommitOkToast();
				// 将数据保存到配置文件
				WeiPinUtil.saveMyInfo(mMyInfo, FragmentMyInfoNew.this);
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

	private void showGongzhongAlert() {
		final String[] items = getResources().getStringArray(
				R.array.gongzhongitem);
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("请选择");
		dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}

		});

		dialog.setSingleChoiceItems(items, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mGongZhongEt.setText(items[which]);
					}

				});
		dialog.show();

	}

	private void showSalaryDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		View view = getLayoutInflater().inflate(R.layout.salary_enter_dialog,
				null);
		final EditText min = (EditText) view.findViewById(R.id.min_edit);
		final EditText max = (EditText) view.findViewById(R.id.max_edit);
		dialog.setTitle("请填写期望薪资");
		dialog.setView(view);
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (TextUtils.isEmpty(min.getText())
						|| TextUtils.isEmpty(max.getText())) {
					showToast("请输入期望薪资");
				} else {
					mXinziEt.setText(min.getText().toString() + "~"
							+ max.getText().toString());
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

	private void showWorkStatusAlert() {
		final String[] items = getResources().getStringArray(
				R.array.workstateitem);
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("请选择");
		dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}

		});

		dialog.setSingleChoiceItems(items, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mZhuangTaiEt.setText(items[which]);
					}

				});
		dialog.show();

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.xinzi_et:
			showSalaryDialog();
			break;
			
		case R.id.gongzhong_et:
			showGongzhongAlert();
			break;
			
		case R.id.zhuangtai_et:
			showWorkStatusAlert();
			break;
		case R.id.myinfo_edit_done_button:
			if(!TextUtils.isEmpty(mNameEt.getText().toString()) && !TextUtils.isEmpty(mIdCardEt.getText().toString())){
				commitUserInfo();
			}else{
				showToast("姓名与身份证号码必须填写");
			}
			
			break;
		default:
			break;
		}
	}
}
