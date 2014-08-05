package cn.doubi.weipin.ui;

import java.io.File;
import java.util.ArrayList;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.UpdateObj;
import cn.doubi.weipin.domain.UrlContent;
import cn.doubi.weipin.push.MyPushMessageReceiver;
import cn.doubi.weipin.service.WeiPinService;
import cn.doubi.weipin.ui.homefragment.FragmentMyInfoNew;
import cn.doubi.weipin.ui.homefragment.VerifyActivity;
import cn.doubi.weipin.utils.PushUtils;
import cn.doubi.weipin.utils.SharedPreferencesManager;
import cn.doubi.weipin.utils.WeiPinUtil;

import com.google.gson.Gson;
import com.slidingmenu.lib.SlidingMenu;

/**
 * 主页,负责检查更新 初始化 滑动菜单等
 */
@SuppressLint({ "InlinedApi", "NewApi" })
public class HomeActivity extends SlideBaseActivity {

	private DownloadManager mDownloadManager;
	private static final String DOWN_ID = "downloadid";
	private BroadcastReceiver mDownloadReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			queryDownloadStatus();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WeiPinApplcation.home = this;
		if(!WeiPinUtil.isVerify(this)){
			startActivity(new Intent(this, VerifyActivity.class));
		}
		if(WeiPinUtil.isVerify(this) && !WeiPinUtil.isEditUserInfo(this)){
			startActivity(new Intent(this, FragmentMyInfoNew.class));
		}
//		startPushMessageActivity();
		startService(new Intent(this, WeiPinService.class));
		PushUtils.startPush(this);
		mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		registerReceiver(mDownloadReceiver, new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		haveUpdate(UrlContent.UPDATE_APK);
		initSlideActivity();
	}

	/**
	 * 决定是否开启PUSH信息列表
	 */
//	private void startPushMessageActivity() {
//		SharedPreferences sp = SharedPreferencesManager.getSP(this);
//		if (sp.getBoolean(MyPushMessageReceiver.HAVE_PUSH, false)) {
//			Intent i = new Intent(this, PushMessageActivity.class);
//			i.putExtra(MyPushMessageReceiver.PUSH_TITLE,
//					sp.getString(MyPushMessageReceiver.PUSH_TITLE, ""));
//			i.putExtra(MyPushMessageReceiver.PUSH_DESCRIPTION,
//					sp.getString(MyPushMessageReceiver.PUSH_DESCRIPTION, ""));
//			i.putExtra(MyPushMessageReceiver.PUSH_CUSTOMCONTENTSTRING, sp
//					.getString(MyPushMessageReceiver.PUSH_CUSTOMCONTENTSTRING,
//							""));
//			startActivity(i);
//			sp.edit().putBoolean(MyPushMessageReceiver.HAVE_PUSH, false)
//					.commit();
//		}
//	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragmentsList;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> fragments) {
			super(fm);
			this.fragmentsList = fragments;
		}

		@Override
		public int getCount() {
			return fragmentsList.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentsList.get(arg0);
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}
	}

	private void initSlideActivity() {
		getSlidingMenu().setMode(SlidingMenu.LEFT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		 setContentView(R.layout.content_frame);
		 getSupportFragmentManager()
		 .beginTransaction()
		 .replace(R.id.content_frame, new ContentFragment())
		 .commit();
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_two, new LeftFragment()).commit();
	}

	private void initDownloadManager(UpdateObj updateObj) {
		if (!SharedPreferencesManager.getSP(this).contains(DOWN_ID)) {
			Uri resource = Uri.parse(updateObj.getDownloadUrl());
			DownloadManager.Request request = new DownloadManager.Request(
					resource);
			request.setAllowedNetworkTypes(Request.NETWORK_MOBILE
					| Request.NETWORK_WIFI);
			request.setAllowedOverRoaming(false);
			MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
			String mimeString = mimeTypeMap
					.getMimeTypeFromExtension(MimeTypeMap
							.getFileExtensionFromUrl(updateObj.getDownloadUrl()));
			request.setMimeType(mimeString);
			// 在通知栏中显示
			request.setShowRunningNotification(true);
			request.setVisibleInDownloadsUi(true);
			// sdcard的目录下的download文件夹
			request.setDestinationInExternalPublicDir("/weipin/", "weipin.apk");
			request.setTitle(getString(R.string.notifationupdatetitle));
			long id = mDownloadManager.enqueue(request);
			// 保存id
			SharedPreferencesManager.getSP(this).edit().putLong(DOWN_ID, id)
					.commit();
		} else {
			// 下载已经开始，检查状态
			queryDownloadStatus();
		}
		registerReceiver(mDownloadReceiver, new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE));

	}

	private void queryDownloadStatus() {
		DownloadManager.Query query = new DownloadManager.Query();
		query.setFilterById(SharedPreferencesManager.getSP(this).getLong(
				DOWN_ID, 0));
		Cursor c = mDownloadManager.query(query);
		if (c.moveToFirst()) {
			int status = c.getInt(c
					.getColumnIndex(DownloadManager.COLUMN_STATUS));
			switch (status) {
			case DownloadManager.STATUS_SUCCESSFUL:
				// 完成
				WeiPinUtil.installApk(
						new File(Environment.getExternalStorageDirectory(),
								"/weipin/weipin.apk").getAbsolutePath(), this);
				break;
			case DownloadManager.STATUS_FAILED:
				// 清除已下载的内容，重新下载
				mDownloadManager.remove(SharedPreferencesManager.getSP(this)
						.getLong(DOWN_ID, 0));
				SharedPreferencesManager.getSP(this).edit().remove(DOWN_ID)
						.commit();
				break;
			}
		}
	}

	private void downloadAppFile(UpdateObj updateObj) {
		initDownloadManager(updateObj);
	}

	private void haveUpdate(String url) {

		new FinalHttp().get(url, new AjaxCallBack<String>() {
			@Override
			public void onSuccess(String t) {
				if (!TextUtils.isEmpty(t)) {
					UpdateObj dow = new Gson().fromJson(t, UpdateObj.class);
					try {
						if (dow.getVersonCode() > getPackageManager()
								.getPackageInfo(getPackageName(), 0).versionCode) {
							showUpdateDialog(dow);
						}
					} catch (NameNotFoundException e) {

						e.printStackTrace();
					}
				}
				super.onSuccess(t);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// update failure
				showToast(getString(R.string.updatefailure));
				super.onFailure(t, errorNo, strMsg);
			}
		});
	}

	private void showUpdateDialog(final UpdateObj dobj) {
		new AlertDialog.Builder(this)
				.setTitle(R.string.updatehint)
				.setMessage(dobj.getUpdateTitle())
				// 二次提示
				.setNegativeButton(R.string.buttoncancle,
						new OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

							}
						})
				.setPositiveButton(R.string.buttonsure, new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						downloadAppFile(dobj);
					}
				}).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mDownloadReceiver);
	}

//	@Override
//	protected void onNewIntent(Intent intent) {
//		startPushMessageActivity();
//	}

	public void onClick(View view) {

	}

	class Code{
		public String code;
		public String addr;
	}
}
