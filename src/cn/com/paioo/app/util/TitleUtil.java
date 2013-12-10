 
package cn.com.paioo.app.util;

import com.slidingmenu.lib.app.SlidingActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.ExtraCompanyInfoActivity;
import cn.com.paioo.app.ui.MainActivity;
import cn.com.paioo.app.ui.RechargeRecordActivity;
import cn.com.paioo.app.ui.RegisterActivity;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TitleUtil {
	 
    
    /**
     * title显示的工具类
     * @param activity
     */
    public static void  show(final Activity activity){
    	String temp = activity.getLocalClassName();
    	String cur = temp.substring(temp.lastIndexOf(".")+1);
        TextView titleName = (TextView) activity.findViewById(R.id.title_bar_title_tv);
        ImageButton back = (ImageButton) activity.findViewById(R.id.title_bar_back_ib);
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 activity.finish();
			}
		});
        
        
        ImageButton slidemenu = (ImageButton) activity.findViewById(R.id.title_bar_slidemenu_ib);
        slidemenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 //打开侧滑
				if(activity instanceof SlidingActivity){
					 ((SlidingActivity) activity).toggle();
				}
			}
		});
        
       
        
        ImageButton rech_record = (ImageButton) activity.findViewById(R.id.title_bar_recharge_record_ib);
        rech_record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//  充值记录
				UIHelper.switcher(activity, RechargeRecordActivity.class);
				
			}
		});
        
        
        if(RegisterActivity.class.getSimpleName().equals(cur)){//注册界面
        	titleName.setText(R.string.free_register);
        	back.setVisibility(View.VISIBLE);
        }
        if(ExtraCompanyInfoActivity.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.eci_company_info_title);
        	back.setVisibility(View.VISIBLE);
        }
        if(MainActivity.class.getSimpleName().equals(cur)){
        	slidemenu.setVisibility(View.VISIBLE);
        	 
        }
        
    }
}
