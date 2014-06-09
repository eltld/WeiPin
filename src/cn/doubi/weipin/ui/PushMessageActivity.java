package cn.doubi.weipin.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 推送的消息汇总界面
 */
public class PushMessageActivity extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ImageView i = new ImageView(this);
		i.setBackgroundColor(Color.YELLOW);
		setContentView(i);
	}

}

