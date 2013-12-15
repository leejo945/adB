 
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
	 * ��÷�����app�б�
	 * @param context
	 * @return
	 * ���ǰ���Ѿ�ָ�������̶���app�����������;
	 * 1.ָ����app��û�а�װ�ڵ�ǰ�豸�С�ֻҪ�����б���
	 * 2.ָ����appһ����û��
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
			// û���罻Ӧ�÷���,��������Ѿ�
		}
		
		return list;
		
    }
    
    
    public static ArrayList<User>  getRechargeRecordList(){
    	return null;
    }
    
    
}
