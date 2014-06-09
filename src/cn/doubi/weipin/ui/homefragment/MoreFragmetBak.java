package cn.doubi.weipin.ui.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import cn.doubi.weipin.R;
import cn.doubi.weipin.ui.AboutActivity;
import cn.doubi.weipin.ui.SuggestActivity;

public class MoreFragmetBak extends Fragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_more, null);
		LinearLayout mParentView = (LinearLayout) v.findViewById(R.id.parent_lin);
		for (int i = 0; i < mParentView.getChildCount(); i++) {
			View vi = mParentView.getChildAt(i);
			if(vi instanceof Button){
				Button b = (Button) vi;
				b.setOnClickListener(this);
			}
		}
		return v;
	}
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.myinfo_button:
			startActivity(new Intent(getActivity(),MyInfoActivity.class));
			break;
		case R.id.jianyi_button:
			startActivity(new Intent(getActivity(),SuggestActivity.class));
			break;
		case R.id.about_button:
			startActivity(new Intent(getActivity(),AboutActivity.class));
			break;

		default:
			break;
		}
	}
}
