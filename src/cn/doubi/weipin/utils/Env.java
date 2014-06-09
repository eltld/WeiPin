package cn.doubi.weipin.utils;

/**
 * Environments configurations.
 * 
 * @author GuoLin
 * 
 */
public class Env {

	public static boolean DEBUG = true;

	public static boolean LOGD = DEBUG;

	public static boolean LOGI = DEBUG;

	public static boolean LOGW = DEBUG;

	public static boolean LOGE = DEBUG;

	public static String LAUNCHER_UPDATE_URI = "http://192.168.2.100:9001/api/update/launcher";

	// 内容屏
	// 下方导航默认配置
	public static String WEBWORKSPACE_NAV_CONFIG = 
			"NOXUS_NEWS::headline::http://192.168.1.41:9007/news?type=NEWS&newsType=PARSE_QQ_NEWS::/news?d=1&at=view_NOXUS_NEWS::/ajax/News/news?page=::/news?type=NEWS_CONTEXT::Noxus.News," +
			"NOXUS_HOT::focus::http://192.168.1.41:9007/news?type=HOT::/hots?d=1&at=view_NOXUS_HOT::/ajax/News/hots?type=shishi::::Noxus.Hot," +
			"NOXUS_SETTING::settingName::http://192.168.1.41:9007/setting";
	// 下方导航默认选中项
	public static String WEBWORKSPACE_NAV_CURRET = "NOXUS_NEWS";
	
	// 第2版新闻屏的api地址.
 	public static String NEWS_ADDRESS = "http://192.168.1.174:9007";
 	public static String API_ADDRESS = "http://192.168.1.174:9007";
 	//public static String API_ADDRESS = "http://192.168.1.174:9007";
	
	public static String DEFAULT_ENCODE = "utf-8";
	//微信分享id
	public static final String WECHAT_APPID = "wx3510d5a21df33b70";
	//新浪微博分享id
	public static final String SINA_APPID = "1637876328";
}
