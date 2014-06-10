package cn.doubi.weipin.utils;

import android.util.Log;
/**
 * 日志管理
 */
public class Logger
{
	private static boolean DEBUG = false;
	public static void i(String tag,String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}
	public static void e(String tag,String msg,Throwable tr) {
		if (DEBUG) {
			Log.e(tag, msg, tr);
		}
	}
}

