package cn.doubi.weipin.ui.homefragment;

public class PushEvent {

	private static PushEvent event;
	private OnPushClick mOnPushClickListener;
	private PushEvent() {
	}
	public static PushEvent getInstance(){
		if(event == null){
			event = new PushEvent();
		}
		return event;
	}
	public void addPushListener(OnPushClick mOnPushClickListener){
		this.mOnPushClickListener = mOnPushClickListener;
	}
	public void firePush(){
		if(mOnPushClickListener != null){
			mOnPushClickListener.onPushClick();
		}
	}
	public interface OnPushClick{
		void onPushClick();
	}
}
