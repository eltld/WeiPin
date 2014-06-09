//package cn.doubi.weipin.ui.homefragment;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import cn.doubi.weipin.R;
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.onekeyshare.OnekeyShare;
//import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
//
//public class ShareFragmentBak extends Fragment{
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View  v = inflater.inflate(R.layout.fragment_share, null);
//		v.findViewById(R.id.share_edit).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				showOnekeyshare(null, true);
//			}
//		});
//		return v;
//	}
//	public void showOnekeyshare(String platform, boolean silent) {
//        OnekeyShare oks = new OnekeyShare();
//        
//        // 分享时Notification的图标和文字
//        oks.setNotification(R.drawable.ic_launcher, 
//        getResources().getString(R.string.app_name));
//        // address是接收人地址，仅在信息和邮件使用
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(this.getString(R.string.share));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("欢迎使用微聘平台");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////        oks.setImagePath(MainActivity.TEST_IMAGE);
//        // imageUrl是图片的网络路径，新浪微博、人人网、QQ空间、
//        // 微信的两个平台、Linked-In支持此字段
//        // url仅在微信（包括好友和朋友圈）中使用
//        // appPath是待分享应用程序的本地路劲，仅在微信中使用
////        oks.setAppPath(MainActivity.TEST_IMAGE);
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment(this.getString(R.string.share));
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getResources().getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        // venueName是分享社区名称，仅在Foursquare使用
//        oks.setVenueName("Southeast in China");
//        // venueDescription是分享社区描述，仅在Foursquare使用
//        oks.setVenueDescription("This is a beautiful place!");
//        // latitude是维度数据，仅在新浪微博、腾讯微博和Foursquare使用
//        oks.setLatitude(23.122619f);
//        // longitude是经度数据，仅在新浪微博、腾讯微博和Foursquare使用
//        oks.setLongitude(113.372338f);
//        // 是否直接分享（true则直接分享）
//        oks.setSilent(silent);
//        // 指定分享平台，和slient一起使用可以直接分享到指定的平台
//        if (platform != null) {
//                oks.setPlatform(platform);
//                
//        }
//        // 去除注释可通过OneKeyShareCallback来捕获快捷分享的处理结果
//        // oks.setCallback(new OneKeyShareCallback());
//        //通过OneKeyShareCallback来修改不同平台分享的内容
//        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
//			
//
//			@Override
//			public void onShare(Platform platform,
//					cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//        oks.show(getActivity());
//	}
//}
