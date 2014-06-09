package cn.doubi.weipin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.doubi.weipin.R;

/**
 * 面试信息列表
 */
public class MyOralInfoListActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myoralinfolist);
		initViews();
	}
	@Override
	protected void initViews()
	{
		setHeaderTitleAndButonText(getResources().getString(R.string.myoral),getString(R.string.goback));
		setHeaderRightButtonVisibilityAndText(View.GONE,getString(R.string.editstr));
	}

	@Override
	public void onClick(View view)
	{
		startActivity(new Intent(this , OralInfoActivity.class));
	}

}

