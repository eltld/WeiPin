package cn.doubi.weipin.ui;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.UrlContent;
import cn.doubi.weipin.utils.Logger;

/**
 *求职者个人信息
 */
public class MyInfoActivity extends BaseActivity
{
	
	public static final String POST_MYINFO_URL = UrlContent.BASE_URL+"jobuser/save";
	private LinearLayout mUserEditTextLinearLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		initViews();
	}
	@Override
	protected void initViews()
	{
		setHeaderTitleAndButonText(getResources().getString(R.string.myinfo), getResources().getString(R.string.goback));
		setHeaderRightButtonVisibilityAndText(View.VISIBLE,getString(R.string.editstr));
		mUserEditTextLinearLayout = (LinearLayout) findViewById(R.id.userEditTextLinearLayout);
	}

	private boolean mInitInfo = true;
	@Override
	public void onClick(View view)
	{
		AjaxParams params = new AjaxParams();
		for (int i = 0; i < mUserEditTextLinearLayout.getChildCount(); i++) {
			View v = mUserEditTextLinearLayout.getChildAt(i);
			if(v instanceof EditText ){
				if (!TextUtils.isEmpty(((EditText)v).getText().toString())) {
					EditText e = (EditText)v;
					String key = (String) e.getTag();
					String value = e.getText().toString();
					params.put(key, value);
					mInitInfo = true;
				}else{
					mInitInfo = false;
					showToast(getResources().getString(R.string.pleaseEdit)+((EditText)v).getHint().toString());
					break;
				}
			}
		}
	/*
	 * userName":"xx",
					"userSex":"xx",
					"userTel":+new Date,
					"userCode":+new Date,
					"userSalary":"xx",
					"userYears":"xx",
					"userTrade":"xx",
					"userWork":"xx",
					"userAddress":"xx",
					"userTimes":"xx",
					"userStatus":"0",
					"userRecom":"xx",
					"createTime":"xx",
					"modifyTime":"xx"	
	 */
		
//		params.put("userName", "暴走青年");
		params.put("userSex", "男");
//		params.put("userTel", "13263485448");
//		params.put("userCode", "13108219901028201X");
//		params.put("userSalary", "8K");
//		params.put("userYears", "5年");
//		params.put("userTrade", "IT行业");
//		params.put("userWork", "Android软件工程师");
//		params.put("userAddress", "北京市朝阳区三里屯");
//		params.put("userTimes", "2");
//		params.put("userStatus", "0");
//		params.put("userRecom", "3");
//		params.put("createTime", "2014年4月14日11:42");
//		params.put("modifyTime", "2014年4月14日12:42");
		if (mInitInfo) {
			new FinalHttp().post(POST_MYINFO_URL, params, new AjaxCallBack<String>() {
				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg) {
					Logger.i("POST", "失败信息:"+strMsg);
					showToast(strMsg);
				}
				@Override
				public void onSuccess(String t) {
					Logger.i("POST", "成功信息:"+t);
					showToast("提交结果:"+t);
					super.onSuccess(t);
				}
			});
		}
		}

}

