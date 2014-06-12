package cn.doubi.weipin.domain;

public interface UrlContent {

	String BASE_URL = "http://www.vpinbao.com/";
	/**
	 * 面试信息列表
	 */
	String HIRELIST_URL = BASE_URL+"oralinfo/queryList.ac";
	
	/**
	 * 没有头像的地址
	 */
	String COMMIT_USERINFO = BASE_URL+"user/save.ac";
	/**
	 *带头像参数的地址 
	 */
	String COMMIT_USERINFO_HEADERIMAGE = BASE_URL+"user/register.ac";
	/**
	 * 验证短信
	 */
	String SEND_TEL_MESSAGE = BASE_URL+"push/pushshortinfo.ac";
	/**
	 * 更新用户资料
	 */
	String UPDATE_USER_INFO = BASE_URL+"user/update.ac";
	 
	/**
	 * App更新地址
	 */
	String UPDATE_APK =BASE_URL+"wp/apk/version.txt";
	
	/**
	 * 投递
	 */
	String OREL_TOUDI = BASE_URL+"oralinfo/applyoral.ac";
	/**
	 * 建议反馈
	 */
	String URL_SUGGEST = BASE_URL+"feedback/save.ac";
	
	/**
	 * 评分
	 */
	String URL_USER_SCORE  = BASE_URL + "userscore/findbyuserid.ac";
}
