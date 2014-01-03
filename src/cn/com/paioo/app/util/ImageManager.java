package cn.com.paioo.app.util;

 
import cn.com.paioo.app.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

 

 
 

public class ImageManager {
	private static DisplayImageOptions imageOptions;
	/**
	 * ��ȡͼƬ����
	 * 
	 * @return
	 */
	public static ImageLoader getInstance() {
		return ImageLoader.getInstance();
	}
	/**
	 * ���������е�ͼƬ����
	 * 
	 * @return
	 */
	public static DisplayImageOptions getImageOptions() {
		 
		if (imageOptions == null) {
			imageOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_launcher)
			        .cacheInMemory(true) 
			        .cacheOnDisc(true)
			        
					.displayer(new SimpleBitmapDisplayer()).build();

		}
		return imageOptions;
	}
}
