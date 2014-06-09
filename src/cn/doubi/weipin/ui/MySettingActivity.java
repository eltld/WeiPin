package cn.doubi.weipin.ui;

import cn.doubi.weipin.R;
import android.os.Bundle;
import android.view.View;

/**
 * 设置界面
 */
public class MySettingActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initViews();	
	}
	@Override
	protected void initViews()
	{
		setHeaderTitleAndButonText(getString(R.string.settingstr),getResources().getString(R.string.goback));
		setHeaderRightButtonVisibilityAndText(View.GONE,getString(R.string.editstr));
	}

	@Override
	public void onClick(View view)
	{

	}

}

