//package cn.doubi.weipin.utils;
//
//import java.io.ByteArrayOutputStream;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.widget.Toast;
//import cn.doubi.weipin.R;
//
//import com.sina.weibo.sdk.api.ImageObject;
//import com.sina.weibo.sdk.api.WebpageObject;
//import com.sina.weibo.sdk.api.WeiboMessage;
//import com.sina.weibo.sdk.api.share.BaseResponse;
//import com.sina.weibo.sdk.api.share.IWeiboHandler;
//import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
//import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
//import com.sina.weibo.sdk.api.share.WeiboShareSDK;
//import com.sina.weibo.sdk.constant.WBConstants;
//import com.sina.weibo.sdk.utils.Utility;
//
//public class SinaShareUtils implements IWeiboHandler.Response {
//	private Context mContext;
//	private static String APP_KEY = Env.SINA_APPID;
//	public static final String REDIRECT_URL = "http://www.yuandian.cn";
//	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
//			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read," + "follow_app_official_microblog," + "invitation_write";
//	/** 微博分享的接口实例 */
//	private static IWeiboShareAPI mWeiboShareAPI;
//	/** 从微博客户端唤起第三方应用时，客户端发送过来的请求数据对象 */
//
//	public SinaShareUtils(Context mContext) {
//		this.mContext = mContext;
//		Activity mActivity = (Activity) mContext;
//		// 创建微博 SDK 接口实例
//		mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, APP_KEY);
//		mWeiboShareAPI.registerApp();
//		mWeiboShareAPI.handleWeiboResponse(mActivity.getIntent(), this);
//	}
//
//	public static IWeiboShareAPI getWeiboShare() {
//		return mWeiboShareAPI;
//	}
//
//	public boolean isInstall() {
//		return mWeiboShareAPI.isWeiboAppInstalled();
//	}
//
//	public void shareUrl(String url, String title, String des) {
//		// mSsoHandler.authorize(new AuthListener());
//		// 1. 初始化微博的分享消息
//		WeiboMessage weiboMessage = new WeiboMessage();
//		weiboMessage.mediaObject = getWebpageObj(url, title, des);
//		// 2. 初始化从第三方到微博的消息请求
//		SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
//		// 用transaction唯一标识一个请求
//		request.transaction = String.valueOf(System.currentTimeMillis());
//		request.message = weiboMessage;
//		// 3. 发送请求消息到微博，唤起微博分享界面
//		mWeiboShareAPI.sendRequest(request);
//	}
//
//	public void shareImage(Bitmap bmp) {
//		// 1. 初始化微博的分享消息
//		WeiboMessage weiboMessage = new WeiboMessage();
//		weiboMessage.mediaObject = getImageObj(bmp);
//		// 2. 初始化从第三方到微博的消息请求
//		SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
//		// 用transaction唯一标识一个请求
//		request.transaction = String.valueOf(System.currentTimeMillis());
//		request.message = weiboMessage;
//		// 3. 发送请求消息到微博，唤起微博分享界面
//		mWeiboShareAPI.sendRequest(request);
//	}
//
//	/**
//	 * 创建图片消息对象。
//	 * 
//	 * @return 图片消息对象。
//	 */
//	private ImageObject getImageObj(Bitmap bmp) {
//		ImageObject imageObject = new ImageObject();
//		imageObject.setImageObject(bmp);
//		return imageObject;
//	}
//
//	/**
//	 * 创建多媒体（网页）消息对象。
//	 * 
//	 * @return 多媒体（网页）消息对象。
//	 */
//	private WebpageObject getWebpageObj(String url, String title, String des) {
//		WebpageObject mediaObject = new WebpageObject();
//		mediaObject.identify = Utility.generateGUID();
//		mediaObject.title = title;
//		mediaObject.description = des;
//
//		Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_sina_logo);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//		mediaObject.setThumbImage(bmp);
//		mediaObject.thumbData = baos.toByteArray();
//		mediaObject.actionUrl = url;
//		mediaObject.defaultText = "";
//		return mediaObject;
//	}
//	@Override
//	public void onResponse(BaseResponse baseResp) {
//		switch (baseResp.errCode) {
//		case WBConstants.ErrorCode.ERR_OK:
//			Toast.makeText(mContext, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
//			break;
//		case WBConstants.ErrorCode.ERR_CANCEL:
//			Toast.makeText(mContext, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
//			break;
//		case WBConstants.ErrorCode.ERR_FAIL:
//			Toast.makeText(mContext, mContext.getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: " + baseResp.errMsg,
//					Toast.LENGTH_LONG).show();
//			break;
//		}
//	}
//}
