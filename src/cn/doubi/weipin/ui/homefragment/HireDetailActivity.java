package cn.doubi.weipin.ui.homefragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.ProgressDialog;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.ResultCode;
import cn.doubi.weipin.domain.UrlContent;
import cn.doubi.weipin.ui.BaseActivity;
import cn.doubi.weipin.utils.Logger;
import cn.doubi.weipin.utils.WeiPinUtil;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class HireDetailActivity extends BaseActivity {

	private ListView mListView;

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hire_detail);
		ShareSDK.initSDK(this,"18f152c218e5");
		initViews();
	};

	public void showOnekeyshare(String platform, boolean silent) {
        OnekeyShare oks = new OnekeyShare();
        
       
        // 分享时Notification的图标和文字
        oks.setNotification(R.drawable.ic_launcher, 
        getResources().getString(R.string.app_name));
        // address是接收人地址，仅在信息和邮件使用
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(this.getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://115.28.136.194:8088/wp/index.html");
        // text是分享文本，所有平台都需要这个字段
        String userId = WeiPinUtil.getUserId(this);
        oks.setText("刚刚在微聘Android客户端获得了一个公司的录用通知,微聘真的很不错.点击链接到官网下载试试看吧!\n微聘官网:http://115.28.136.194:8088/wp/index.html\n来自微聘用户:"+userId);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // imageUrl是图片的网络路径，新浪微博、人人网、QQ空间、
        // 微信的两个平台、Linked-In支持此字段
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://115.28.136.194:8088/wp/index.html");
        // appPath是待分享应用程序的本地路劲，仅在微信中使用
//        oks.setAppPath(MainActivity.TEST_IMAGE);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(this.getString(R.string.share));
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getResources().getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        // 是否直接分享（true则直接分享）
        oks.setSilent(silent);
        // 指定分享平台，和slient一起使用可以直接分享到指定的平台
        if (platform != null) {
                oks.setPlatform(platform);
                
        }
        // 去除注释可通过OneKeyShareCallback来捕获快捷分享的处理结果
         oks.setCallback(new PlatformActionListener() {
			
			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				
			}
			
			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				int count = WeiPinUtil.getUserCount(HireDetailActivity.this);
				WeiPinUtil.saveUserCount(HireDetailActivity.this, ++count);
			}
			
			@Override
			public void onCancel(Platform arg0, int arg1) {
				
			}
		});
        //通过OneKeyShareCallback来修改不同平台分享的内容
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
			
			@Override
			public void onShare(Platform platform,
					cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
				if("WechatMoments".equals(platform.getName())){
					File f = new File(Environment.getExternalStorageDirectory(),"share.png");
			        if(f.exists()){
			        	paramsToShare.setImagePath(f.getAbsolutePath());
			        }
					paramsToShare.setTitleUrl(null);
					paramsToShare.setText(null);
					paramsToShare.setUrl(null);
					paramsToShare.setTitle(null);
				}
			}
			
		});
        oks.show(this);
	}
	@Override
	protected void initViews() {
		mListView = (ListView) findViewById(R.id.hireList);
		mToudiBt = (Button) findViewById(R.id.toudibt);
		TextView mMianshiScr = (TextView) findViewById(R.id.mianshiscription);
		mMianshiScr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showOnekeyshare(null, true);
			}
		});
		mMianshiScr.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				showOnekeyshare(null, true);
				return false;
			}
		});
		initData();
		String type = getIntent().getStringExtra("type");
		Map<String, String> info = WeiPinUtil.getUserInfo(this);
		mDatas = getIntent().getStringArrayExtra("oral");
		String scr = "尊敬的"
				+ info.get("userName")
				+ "：\n我们已确认您的面试时间，请您参见以下内容并牢记面试编号,请您携带个人身份证件，学历和技能证书（复印件）前往，如面试时间冲突，请致电调整。";
		String mianshiContent = "公司名称:"
				+ mDatas[0] + "\n" + "月薪:" + mDatas[5] + "/月(税前)\n"
				+ "入职时间:" + mDatas[2] + "\n" + "入职地址:" + mDatas[3]
				+ "\n" + "联系人:" + "陈先生" + "\n" + "联系电话:" + mDatas[4];
		if (!TextUtils.isEmpty(type)) {
			 if ("oral".equals(type) || "offer".equals(type)) {
				mListView.setVisibility(View.GONE);
				int oralState = getIntent().getIntExtra("oralState", 0);
				if(oralState == 4){
					String share1 = "只需要您分享本信息，即可更多获得5次工作推荐机会，希望您再接再厉。";
					String s = mDatas[0]+"公司"+mDatas[1]+"职位的面试。";
					scr = "尊敬的"+info.get("userName")+"：\n我们很遗憾的通知您，您没有通过"+s+"\n"+share1;
				}else if(oralState == 2){
					scr = "尊敬的" + info.get("userName")
							+ "：\n您的入职时间已确认，请您携带本人身份证前往一下地址办理入职手续\n" + mianshiContent
							+ "\n\n分享您的成功经历，您将有机会双喜临门，抽取APPLE IPHONE6大奖（预约）。";
				}else{
					String share = "长按本信息分享您的喜悦，让更多好友找工作只需要动动手。";
					scr = "尊敬的"+info.get("userName")+"：\n我们很高兴的通知您，您已通过"+mDatas[0]+"公司"+mDatas[1]+"职位税前月薪"+mDatas[5	]+"元的面试，请您保持手机通信畅通，我们会在48小时内与您联系并确认入职时间。\n"+share;
				}

			}

			mToudiBt.setVisibility(View.GONE);
			// 显示面试描述
			mMianshiScr.setVisibility(View.VISIBLE);
			mMianshiScr.setText(scr);
		} else {
			mToudiBt.setVisibility(View.VISIBLE);
			mMianshiScr.setVisibility(View.GONE);
		}

		mListView.setAdapter(new MyAdapter(mDatas));
	}

	private String[] mItemTitles;
	private Button mToudiBt;
	private String[] mDatas;

	private void initData() {
		mItemTitles = new String[] { this.getString(R.string.com_name),
				this.getString(R.string.gangwei),
				this.getString(R.string.oral_time_str),
				this.getString(R.string.com_address_str),
				this.getString(R.string.tel_number_str),
				this.getString(R.string.dsalary_str),
				this.getString(R.string.tel_yeartime) };
	}

	@Override
	public void onClick(View view) {
		int count = WeiPinUtil.getUserCount(getApplicationContext());
		if (count < 0) {
			showToast("您的面试次数为0,可以通过分享好友注册获得面试次数.每分享给10个好友注册将获得5次面试机会");
		} else {
			postToudi();
		}
	}

	private void postToudi() {
		AjaxParams params = new AjaxParams();
		params.put("oralRst", "1");
		params.put("oralId", mDatas[mDatas.length - 1]);
		getHttpClient().post(UrlContent.OREL_TOUDI, params,
				new AjaxCallBack<String>() {
					ProgressDialog dialog = showBaseProgressDialog();

					public void onSuccess(String t) {
						dialog.dismiss();
						if (!TextUtils.isEmpty(t)) {
							ResultCode code = new Gson().fromJson(t,
									ResultCode.class);
							if (code != null) {
								if (code.getCode().equals("1")) {
									showToast("申请面试成功");
									int count = WeiPinUtil
											.getUserCount(getApplicationContext());
									WeiPinUtil.saveUserCount(
											getApplicationContext(), --count);
								} else {
									showToast("申请面试失败");
								}
							}
						}
					};

					public void onStart() {
						dialog.show();
					};

					public void onFailure(Throwable t, int errorNo,
							String strMsg) {
						dialog.dismiss();
						Logger.e("TOUDI", strMsg, t);
					};
				});
	}

	class MyAdapter extends BaseAdapter {
		private String[] mData;

		public MyAdapter(String[] mData) {
			this.mData = mData;
		}

		@Override
		public int getCount() {
			return mItemTitles.length;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = View.inflate(HireDetailActivity.this,
					R.layout.list_hire_item, null);
			TextView title = (TextView) v.findViewById(R.id.hireTitle);
			title.setText(mItemTitles[position]);
			TextView content = (TextView) v.findViewById(R.id.hireContent);
			content.setText(mData[position]);
			return v;
		}

	}
}
