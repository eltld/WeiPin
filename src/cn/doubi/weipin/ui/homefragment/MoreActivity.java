package cn.doubi.weipin.ui.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import cn.doubi.weipin.R;
import cn.doubi.weipin.ui.AboutActivity;
import cn.doubi.weipin.ui.BaseActivity;
import cn.doubi.weipin.ui.SuggestActivity;
import cn.doubi.weipin.ui.WeiPinApplcation;

public class MoreActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = getLayoutInflater().inflate(R.layout.fragment_more, null);
		LinearLayout mParentView = (LinearLayout) v.findViewById(R.id.parent_lin);
		for (int i = 0; i < mParentView.getChildCount(); i++) {
			View vi = mParentView.getChildAt(i);
			if(vi instanceof Button){
				Button b = (Button) vi;
				b.setOnClickListener(this);
			}
		}
		setContentView(v);
	}

	@Override
	protected void initViews() {
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.myinfo_button:
			startActivity(new Intent(this,FragmentMyInfoNew.class));
			break;
		case R.id.jianyi_button:
			startActivity(new Intent(this,SuggestActivity.class));
			break;
		case R.id.about_button:
			startActivity(new Intent(this,AboutActivity.class));
			break;
		case R.id.exit:
			finish();
			WeiPinApplcation.home.finish();
			break;
			
		default:
			break;
		}
	}
}
