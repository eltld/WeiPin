package cn.doubi.weipin.ui;

import android.os.Bundle;
import android.view.View;
import cn.doubi.weipin.R;

/**
 * 面试信息详细,接收面试信息列表界面传递的数据
 */
public class OralInfoActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oralinfo);
		initViews();
	}
	@Override
	protected void initViews()
	{
		setHeaderTitleAndButonText(getResources().getString(R.string.oralinfotitle),getResources().getString(R.string.goback));
		setHeaderRightButtonVisibilityAndText(View.GONE,getString(R.string.editstr));
	}
	@Override
	public void onClick(View view)
	{

	}

}

