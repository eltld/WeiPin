package cn.doubi.weipin.ui.homefragment;

import cn.doubi.weipin.ui.homefragment.PushEvent.OnPushClick;
import net.tsz.afinal.FinalHttp;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;


public class BaseFragment extends Fragment implements OnPushClick{

	private FinalHttp mFinalHttp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFinalHttp =  new FinalHttp();
		PushEvent.getInstance().addPushListener(this);
		PushEvent.getInstance().firePush();
	}
	protected FinalHttp getHttpClient() {
		return mFinalHttp;
	}
	protected ProgressDialog  showBaseProgressDialog() {
		return ProgressDialog.show(getActivity(), "提示", "操作中请等待..");
	}
	protected void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onPushClick() {
		
	}
}

