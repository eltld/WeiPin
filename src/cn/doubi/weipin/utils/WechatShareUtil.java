//package cn.doubi.weipin.utils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import cn.doubi.weipin.R;
//
//import com.tencent.mm.sdk.openapi.IWXAPI;
//import com.tencent.mm.sdk.openapi.SendMessageToWX;
//import com.tencent.mm.sdk.openapi.WXAPIFactory;
//import com.tencent.mm.sdk.openapi.WXImageObject;
//import com.tencent.mm.sdk.openapi.WXMediaMessage;
//import com.tencent.mm.sdk.openapi.WXTextObject;
//import com.tencent.mm.sdk.openapi.WXWebpageObject;
//import com.tencent.mm.sdk.platformtools.Util;
//
//
//public class WechatShareUtil {
//	private static final int THUMB_SIZE_W = 75;
//	private static final int THUMB_SIZE_H = 150;
//	private static IWXAPI api;
//
//	public static void regToWx(Context mContext) {
//		api = WXAPIFactory.createWXAPI(mContext, Env.WECHAT_APPID, false);
//		api.registerApp(Env.WECHAT_APPID);
//	}
//
//	public static boolean isInstall(){
//		return api.isWXAppInstalled();
//	}
//	public static void sendUrl(Context mContext, String title, String url, String description, boolean isSendGroup) {
//		WXWebpageObject webpage = new WXWebpageObject();
//		webpage.webpageUrl = url;
//		WXMediaMessage msg = new WXMediaMessage(webpage);
//		msg.title = title;
//		msg.description = description;
//		Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
//		msg.thumbData = Util.bmpToByteArray(thumb, true);
//
//		SendMessageToWX.Req req = new SendMessageToWX.Req();
//		req.transaction = buildTransaction("webpage");
//		req.message = msg;
//		req.scene = isSendGroup ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//		api.sendReq(req);
//	}
//
//	public static void sendImage(Context mContext, boolean isSendGroup, Bitmap bmp) {
//		if(bmp==null)
//		{
//			return;
//		}
//		WXImageObject imgObj = new WXImageObject(bmp);
//
//		WXMediaMessage msg = new WXMediaMessage();
//		msg.mediaObject = imgObj;
//		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE_W, THUMB_SIZE_H, true);
//		bmp.recycle();
//		msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
//
//		SendMessageToWX.Req req = new SendMessageToWX.Req();
//		req.transaction = buildTransaction("img");
//		req.message = msg;
//		req.scene = isSendGroup ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//		api.sendReq(req);
//	}
//
//	public static void send(String text) {
//		WXTextObject textObj = new WXTextObject();
//		textObj.text = text;
//		WXMediaMessage msg = new WXMediaMessage();
//		msg.mediaObject = textObj;
//		msg.description = text;
//		
//		SendMessageToWX.Req req = new SendMessageToWX.Req();
//		req.transaction = buildTransaction("text");
//		req.message = msg;
//		req.scene = true ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//		api.sendReq(req);
//	}
//
//	private static String buildTransaction(final String type) {
//		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
//	}
//}
