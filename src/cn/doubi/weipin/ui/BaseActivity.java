package cn.doubi.weipin.ui;

import net.tsz.afinal.FinalHttp;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.doubi.weipin.R;
import cn.doubi.weipin.utils.SharedPreferencesManager;

/**
 * Activity 基类
 */
abstract public class BaseActivity extends Activity 
{
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	 }
	 /**
	  * 请在这里初始化你的View
	  */
	abstract protected void initViews();
	/**
	 * 这里可以是任何View的单机事件
	 */
	abstract public void onClick(View view);
	
	/**
	 * 拿到一个被统一管理的SharedPreferences对象
	 */
	public SharedPreferences getSP(Context context) {
		return SharedPreferencesManager.getSP(context);
	}
	/**
	 * 显示一个提示
	 */
	public void showToast(String msg) {
		Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
	}
	public void showNetErrorToast() {
		Toast.makeText(getApplicationContext(),"操作失败,请检查网络是否畅通!", Toast.LENGTH_SHORT).show();
	}
	public void showCommitOkToast() {
		Toast.makeText(getApplicationContext(),"信息提交成功!", Toast.LENGTH_SHORT).show();
	}
	
	
	/**
	 * 设置标题和按钮的文字
	 */
	public void setHeaderTitleAndButonText(String headerTitle , String buttonText) {
		TextView headerTitleText = (TextView) findViewById(R.id.headerTtile);
		Button menuButton = (Button) findViewById(R.id.menuButton);
		if(headerTitleText != null && menuButton != null) {
			headerTitleText.setText(headerTitle);
			menuButton.setText(buttonText);
			if(getResources().getString(R.string.goback).equals(buttonText)) {
				menuButton.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						finish();
					}
				});
			}
		}
	}
	/**
	 * 控制标题右面按钮的显示
	 */
	public void setHeaderRightButtonVisibilityAndText(int visibility,String text) {
		Button right = (Button) findViewById(R.id.menuButtonRight);
		if(right != null) {
			right.setVisibility(visibility);
			right.setText(text);
		}
	}
	
	public String getResString(int resId){
		return getResources().getString(resId);
	}
	public FinalHttp getHttpClient(){
		return new FinalHttp();
	}
	protected ProgressDialog  showBaseProgressDialog() {
		return ProgressDialog.show(this, "提示", "操作中请等待..");
	}
}

