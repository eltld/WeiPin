package cn.doubi.weipin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.doubi.weipin.utils.Logger;

/**
 * 暂未使用的服务类
 */
public class WeiPinService extends Service
{

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Logger.i("Ser", "start service");
	};
	@Override
	public void onCreate()
	{
		super.onCreate();
		Logger.i("Ser", "open service");
		
	}
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

}

