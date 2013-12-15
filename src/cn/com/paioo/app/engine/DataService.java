 
package cn.com.paioo.app.engine;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import cn.com.paioo.app.entity.ShareInfo;
import cn.com.paioo.app.entity.User;

public class DataService {
	/**
	 * 获得分享到的app列表
	 * @param context
	 * @return
	 * 如果前端已经指定分享到固定的app。有如下情况;
	 * 1.指定的app并没有安装在当前设备中。只要分享列表中
	 * 2.指定的app一个都没有
	 * 
	 */
    public  static ArrayList<ShareInfo> getShareInfoList(Context context){
    	Intent it = new Intent(Intent.ACTION_SEND);
		it.setType("text/plain");
		PackageManager packManager  = context.getPackageManager();
		List<ResolveInfo> resInfos = packManager.queryIntentActivities(it, 0);
		ArrayList<ShareInfo> list = new ArrayList<ShareInfo>();
		if (!resInfos.isEmpty()) {
			ShareInfo shareInfo = null;
			for (ResolveInfo resInfo : resInfos) {
				ActivityInfo activityInfo = resInfo.activityInfo;
				String packName = activityInfo.packageName;
			//	if (packName.contains("sina") || packName.contains("tencent")||packName.contains("qihoo")
			//			||packName.contains("")) {
					shareInfo = new ShareInfo();
					shareInfo.packName = packName;
					shareInfo.icon = activityInfo.loadIcon(packManager);
					shareInfo.name = activityInfo.loadLabel(packManager).toString();
					list.add(shareInfo);
			//	}
			}
		} else {
			// 没有社交应用分享,如果本身已经
		}
		
		return list;
		
    }
    
    
    public static ArrayList<User>  getRechargeRecordList(){
    	return null;
    }
    
    
}
