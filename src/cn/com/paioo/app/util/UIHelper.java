package cn.com.paioo.app.util;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

 

public class UIHelper {
    public static View  getView(Context context,int resId){
    	return ((Activity)context).findViewById(resId);
    }  
	
	
	
   /**
    * ΩÁ√Ê«–ªª
    * @param from
    * @param to
    */
	public static void switcher(Context from, Class<?> to) {
           switcher(from, to, null); 
	}
	public static void switcher(Context from, Class<?> to,Map<String,Object> extras){
		Intent i = new Intent(from,to);
        if(extras!=null){
        	for(String name:extras.keySet()){
        		Object obj = extras.get(name);
        		if(obj instanceof String){
        			i.putExtra(name, (String)obj);
        		}else if(obj instanceof Integer){
        			i.putExtra(name, (Integer)obj);
        		}
        		
        	}
        } 
        from.startActivity(i);
	}
}
