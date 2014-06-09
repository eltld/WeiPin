package cn.doubi.weipin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.doubi.weipin.R;
/**
 * 侧边栏Fragment
 */
public class LeftFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RelativeLayout leftContent = (RelativeLayout) inflater.inflate(R.layout.leftcontent, null);
		LinearLayout leftLinearLayout = (LinearLayout) leftContent.findViewById(R.id.leftLinearlayout); 
		for (int i = 0; i < leftLinearLayout.getChildCount(); i++)
		{
			
			View view = leftLinearLayout.getChildAt(i);
			if(view instanceof Button) {
				Button b = (Button) view;
				b.setOnClickListener(this);
			}
		}
		return leftContent;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.myoralButton:
			startActivity(new Intent(getActivity(),MyOralInfoListActivity.class));
			break;
		case R.id.myinfoButton:
			startActivity(new Intent(getActivity(),MyInfoActivity.class));
			break;
		case R.id.shareFriendButton:
			Toast.makeText(getActivity(), "分享给好友", Toast.LENGTH_SHORT).show();
			break;
		case R.id.mySettingButton:
			startActivity(new Intent(getActivity(),MySettingActivity.class));
			break;

		default:
			break;
		}
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}
