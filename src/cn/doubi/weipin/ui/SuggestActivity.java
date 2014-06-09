package cn.doubi.weipin.ui;

import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import cn.doubi.weipin.R;
import cn.doubi.weipin.domain.UrlContent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class SuggestActivity extends BaseActivity {

	private EditText mSuggest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggest);
		initViews();
	}
	@Override
	protected void initViews() {
		mSuggest = (EditText) findViewById(R.id.edit_suggest);
	}

	@Override
	public void onClick(View view) {
		
		String content = mSuggest.getText().toString();
		if(!TextUtils.isEmpty(content)){
			commit(content);
		}else{
			
			showToast("请输入内容");
		}
	}
	public void commit(String str){
		final ProgressDialog dialog = showBaseProgressDialog();
		AjaxParams params = new AjaxParams();
		params.put("content", str);
		params.put("source", "手机客户端");
		params.put("title", "建议反馈");
		getHttpClient().post(UrlContent.URL_SUGGEST,params,new AjaxCallBack<String>(){
			@Override
			public void onStart() {
				
				super.onStart();
			}
			@Override
			public void onSuccess(String t) {
				if(!TextUtils.isEmpty(t)){
					t.contains("1");
					showToast("提交成功");
				}else{
					showToast("提交失败,请检查网络");
				}
				dialog.dismiss();
				super.onSuccess(t);
			}
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				super.onFailure(t, errorNo, strMsg);
				showToast("提交失败,请检查网络");
				dialog.dismiss();
			}
		});
	}

}
