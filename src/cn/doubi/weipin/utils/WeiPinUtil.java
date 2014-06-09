package cn.doubi.weipin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import cn.doubi.weipin.ui.homefragment.FragmentMyInfoNew;

/**
 * 封装了一些常用的方法
 */
public class WeiPinUtil
{
	private static final String APP_VERSION = "app_version";
	private static final String IS_FIRST_START = "isfirstrun";
	public static final String IS_EDIT_USERINFO = "isuserinfo";
	public static final String USER_TEL = "tel";
	public static final String IS_VERIFY = "verify";
	public static int dip2px(float dip , Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * Convert from Pixel value to DIP value.
     * @param pixel Value in Pixel
     * @return Value in DIP
     */
    public static int px2dip(float pixel, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pixel / scale + 0.5f);
    }
	public  static void saveMyInfo(Map<String,String> params , Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
		  while (it.hasNext()) {
		   Map.Entry<String, String> entry = it.next();
		   String key = entry.getKey();
		   String value = entry.getValue();
		   Editor e = sp.edit();
		   e.putString(key, value);
		   e.commit();
		  }
	}
	public static Map<String,String> getUserInfo(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		Map<String,String> params = new HashMap<String, String>();
		String[] s = FragmentMyInfoNew.mParamsKey;
		for (int i = 0; i < s.length; i++) {
			String text = sp.getString(s[i], "");
			if(!TextUtils.isEmpty(text)){
				params.put(s[i], text);
			}
		}
		return params;
	}
	public static int getUserCount(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getInt("userCount", 0);
	}
	public static void saveUserCount(Context context , int count){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		sp.edit().putInt("userCount", count).commit();
	}
	public static void download(String url,File path){
		new FinalHttp().download(url, path.getAbsolutePath(), new AjaxCallBack<File>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				super.onFailure(t, errorNo, strMsg);
				Logger.e("PUSH", "启动也图片下载失败:",t);
			}
			@Override
			public void onSuccess(File t) {
				super.onSuccess(t);
				Logger.i("PUSH", "启动也图片下载成功");
			}
		});
	}
	/**
	 * 保存手机号码
	 */
	public static void saveTel(String tel,Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		sp.edit().putString(USER_TEL, tel).commit();
	}
	
	public static final String EDIT_USER_ID = "userid";
	public static final String RECOM_USER_ID = "recom_userid";
	
	public static void saveEditUserInfoState(Context context , String userId){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		sp.edit().putBoolean(IS_EDIT_USERINFO, true).commit();
		sp.edit().putString(EDIT_USER_ID, userId).commit();
	}
	public static String getUserId(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getString(EDIT_USER_ID, "");
	}
	public static void saveRecommend(String recom , Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		sp.edit().putString(RECOM_USER_ID, recom).commit();
	}
	public static String getRecommend(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getString(RECOM_USER_ID, "");
	}
	
	public static String getUserIdForXml(Context context ){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getString(EDIT_USER_ID, ""); 
	}
	public static String getTelBySp(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getString(USER_TEL, "还未填写号码");
	}
	
	/**
	 * 是否便编辑过个人资料
	 */
	public static boolean isEditUserInfo(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getBoolean(IS_EDIT_USERINFO, false);
	}
	public static void saveVersionCode(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		int version;
		try {
			version = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
			sp.edit().putInt(APP_VERSION, version).commit();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 是否验证过手机
	 * @return
	 */
	public static boolean isVerify(Context context){
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		return sp.getBoolean(IS_VERIFY, false);
	}
	/**
	 * 判断APP是否更新
	 */
	public static boolean isFirstStartFromVersonCode(Context context)
	{
		try
		{
			SharedPreferences sp = SharedPreferencesManager.getSP(context);
			int version = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
			boolean isFirst = version > sp.getInt(APP_VERSION,
					1);
			saveVersionCode(context);
			return isFirst;
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * 是否是第一次运行.
	 */
	public static boolean isFirstStartFromConfig(Context context) {
		boolean isFirst = false;
		SharedPreferences sp = SharedPreferencesManager.getSP(context);
		if (!sp.getBoolean(IS_FIRST_START, false)){
			isFirst = true;
			sp.edit().putBoolean(IS_FIRST_START, true).commit();
			saveVersionCode(context);
		}
		return isFirst;
	}
	/**
	 * 根据路径安装一个App
	 */
	public static void installApk(String apkPath , Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);  
        intent.setDataAndType(Uri.fromFile(new File(apkPath)),  
                "application/vnd.android.package-archive");  
        context.startActivity(intent);
	}
	
}
