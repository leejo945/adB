package cn.com.paioo.app.adapter;

import cn.com.paioo.app.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ThreeInOneAdapter2 extends BaseAdapter {
	private Context context;

	public ThreeInOneAdapter2(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 100;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private static class ViewHolder {
		TextView gMonth, cDate, cCard0, cCard1, cMoney, cSatus;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context,
					R.layout.three_in_one_item_child, null);
			holder.cDate = (TextView) convertView
					.findViewById(R.id.three_in_one_item_child_date_tv);
			holder.cCard0 = (TextView) convertView
					.findViewById(R.id.three_in_one_item_child_cardid0_tv);
			holder.cCard1 = (TextView) convertView
					.findViewById(R.id.three_in_one_item_child_cardid1_tv);
			holder.cMoney = (TextView) convertView
					.findViewById(R.id.three_in_one_item_child_money_tv);
			holder.cSatus = (TextView) convertView
					.findViewById(R.id.three_in_one_item_child_status_tv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.cDate.setText("12-12");
	//	switch (type) {
		//case RECHARGE: {
			holder.cCard0.setText("卡号：");
			holder.cCard1.setText("12345687");

			if (position % 2 == 0) {
				holder.cMoney.setTextColor(Color.rgb(255, 97, 0));
				holder.cMoney.setText("+1000");
				holder.cSatus.setTextColor(Color.rgb(105, 185, 0));
				holder.cSatus.setText("交易成功");
			} else {
				holder.cMoney.setTextColor(Color.rgb(102, 102, 102));
				holder.cMoney.setText("+1000");
				holder.cSatus.setTextColor(Color.RED);
				holder.cSatus.setText("交易失败");
			}
//
//			break;
//		}

//		case AD: {
//			holder.cCard0.setVisibility(View.GONE);
//			holder.cCard1.setTextColor(Color.rgb(102, 102, 102));
//			holder.cCard1.setText("jiaojiao美食店123");
//			if (childPosition % 2 == 0) {
//				holder.cMoney.setTextColor(Color.RED);
//				holder.cMoney.setText("+1000");
//
//				holder.cSatus.setTextColor(Color.rgb(105, 185, 0));
//				holder.cSatus.setText("交易成功");
//			} else {
//				holder.cMoney.setTextColor(Color.rgb(102, 102, 102));
//				holder.cMoney.setText("+1000");
//				holder.cSatus.setTextColor(Color.RED);
//				holder.cSatus.setText("交易失败");
//			}
//			break;
//		}
//		case TRANSFER: {
//			holder.cCard0.setTextColor(Color.rgb(102, 102, 102));
//			holder.cCard0.setText("jiaojiao美食店123");
//			holder.cCard1.setTextColor(Color.rgb(102, 102, 102));
//			holder.cCard1.setText("jli@efubo.com");
//			if (childPosition % 2 == 0) {
//				holder.cMoney.setTextColor(Color.RED);
//				holder.cMoney.setText("+1000");
//
//				holder.cSatus.setTextColor(Color.rgb(105, 185, 0));
//				holder.cSatus.setText("交易成功");
//			} else {
//				holder.cMoney.setTextColor(Color.rgb(102, 102, 102));
//				holder.cMoney.setText("+1000");
//				holder.cSatus.setTextColor(Color.RED);
//				holder.cSatus.setText("交易失败");
//			}
//			break;
//		}

		//}

		return convertView;
	}

}

// public static class ViewHolder {
// @SuppressWarnings("unchecked")
// public static <T extends View> T get(View view, int id) {
// SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
// if (viewHolder == null) {
// viewHolder = new SparseArray<View>();
// view.setTag(viewHolder);
// }
// View childView = viewHolder.get(id);
// if (childView == null) {
// childView = view.findViewById(id);
// viewHolder.put(id, childView);
// }
// return (T) childView;
// }
// }