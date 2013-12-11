 
package cn.com.paioo.app.util;

import com.slidingmenu.lib.app.SlidingActivity;

import cn.com.paioo.app.R;
import cn.com.paioo.app.ui.AboutUs;
import cn.com.paioo.app.ui.ExtraCompanyInfoActivity;
import cn.com.paioo.app.ui.ForgetActivity;
import cn.com.paioo.app.ui.MainActivity;
import cn.com.paioo.app.ui.ModifyContactWayActivity;
import cn.com.paioo.app.ui.ModifyPassword;
import cn.com.paioo.app.ui.RechargeRecordActivity;
import cn.com.paioo.app.ui.RegisterActivity;
import cn.com.paioo.app.ui.SuggestActivity;
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
        
        
        if(RegisterActivity.class.getSimpleName().equals(cur) 	
        ){//注册界面
        	titleName.setText(R.string.free_register);
        	back.setVisibility(View.VISIBLE);
        }
        //补充公司信息
        if(ExtraCompanyInfoActivity.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.eci_company_info_title);
        	back.setVisibility(View.VISIBLE);
        }
        //忘记密码
        if(ForgetActivity.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.find_pwd);
        	back.setVisibility(View.VISIBLE);
        }
        //修改联系方式
        if(ModifyContactWayActivity.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.modify_contact_way);
        	back.setVisibility(View.VISIBLE);
        }
        //修改密码
        
        if(ModifyPassword.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.modify_password);
        	back.setVisibility(View.VISIBLE);
        }
        //意见反馈
        if(SuggestActivity.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.suggest);
        	back.setVisibility(View.VISIBLE);
        }
        //关于我们
        if(AboutUs.class.getSimpleName().equals(cur)){
        	titleName.setText(R.string.about_us);
        	back.setVisibility(View.VISIBLE);
        }
        
        if(MainActivity.class.getSimpleName().equals(cur)){
        	slidemenu.setVisibility(View.VISIBLE);
        }
        
    }
}
