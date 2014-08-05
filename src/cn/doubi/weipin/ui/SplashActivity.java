package cn.doubi.weipin.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.BaseUserInfo;
import cn.doubi.weipin.domain.EmploymentInfo;
import cn.doubi.weipin.sqlite.SqliteImplements;
import cn.doubi.weipin.ui.homefragment.PushEvent;
import cn.doubi.weipin.utils.PushUtils;
import cn.doubi.weipin.utils.WeiPinUtil;

/**
 * 启动界面
 */
public class SplashActivity extends BaseActivity
{

	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg)
		{
			startHome();
			finish();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		WeiPinUtil.saveVersionCode(this);
		ImageView i = new ImageView(this);
		File f = new File(getCacheDir(),"start.png");
		if(f.exists()){
			Bitmap start = BitmapFactory.decodeFile(f.getAbsolutePath());
			i.setImageBitmap(start);
		}else{
			i.setBackgroundResource(R.drawable.splash_bg);
		}
		setContentView(i);
		SqliteImplements sql = new SqliteImplements(this);
		sql.createTableWidthObj(new BaseUserInfo(), "userinfo");
		sql.createTableWidthObj(new EmploymentInfo(), "employmentInfo");
		new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				File f = new File(Environment.getExternalStorageDirectory(),"share.png");
				if(!f.exists()){
					//复制分享图片到SD卡
					saveBitmap();
				}
				SystemClock.sleep(2000);
				mHandler.sendEmptyMessage(0);
				
			}
		}).start();
		PushUtils.startPush(this);
	}
	@Override
	protected void initViews()
	{
		
	}

	@Override
	public void onClick(View view)
	{
		
	} 
	private void saveBitmap() {
		  File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"share.png" );
		  if (f.exists()) {
		   f.delete();
		  }
		  Bitmap bm;
		try {
			bm = BitmapFactory.decodeStream(getAssets().open("share_pic.png"));
			  FileOutputStream out = new FileOutputStream(f);
			   bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			   out.flush();
			   out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		 }
	private void startHome() {
		// 如果是第一次运行.或者是高版本
//			if (WeiPinUtil.isFirstStartFromConfig(this) || WeiPinUtil.isFirstStartFromVersonCode(this))
//			{
//				startActivity(new Intent(this, WelcomeActivity.class));
//			}else {
//			}
			startActivity(new Intent(this, HomeActivity.class));
			
	}
}

