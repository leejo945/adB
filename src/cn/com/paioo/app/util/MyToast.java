package cn.com.paioo.app.util;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
    
	public static void show(Context context,Object text) {
       show(context, text, 0);
	}

	public static void show(Context context, Object text, int duration) {
		if (text instanceof String) {

			Toast.makeText(context, (String) text, duration).show();
			return;
		}
		if (text instanceof Integer) {

			Toast.makeText(context, (Integer) text, duration).show();
			return;
		}

	}
}
