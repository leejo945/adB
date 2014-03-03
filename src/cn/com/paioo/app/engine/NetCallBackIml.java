 
package cn.com.paioo.app.engine;

import cn.com.paioo.app.R;
import cn.com.paioo.app.util.ToastManager;
import android.content.Context;

public abstract class NetCallBackIml implements NetCallBack {

	public  void netCallBack(Object response){
		
	};

	@Override
	public void netErrorCallBack(Context context,String errorReason) {
		 ToastManager.show(context, context.getResources().getString(R.string.warn_toast_net_error));
	}

}
