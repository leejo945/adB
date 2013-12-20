 
package cn.com.paioo.app.entity;

import android.graphics.drawable.Drawable;

public class ShareInfo {
   public int id;
   public Drawable icon;
   public int icon0;
   public String name;
   public String packName;
   public boolean isInstall;
   public ShareInfo(int icon,String name,String packName){
	   this.icon0= icon;
	   this.name = name;
	   this.packName = packName;
   }
}
