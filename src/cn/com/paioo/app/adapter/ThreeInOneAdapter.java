package cn.com.paioo.app.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.Record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

//@SuppressLint("ResourceAsColor")
//public class RechargeRecordAdapter extends BaseAdapter implements
//		PinnedSectionListAdapter {
//	Context context;
//	private ArrayList<RechargeRecord> list = new ArrayList<RechargeRecord>();
//
//	public RechargeRecordAdapter(Context context) {
//		this.context = context;
//		for (int i = 0; i < 1000; i++) {
//			RechargeRecord section;
//			if (i % 10 == 0) {
//				section = new RechargeRecord(RechargeRecord.SECTION, i + "月");
//			} else {
//				section = new RechargeRecord(RechargeRecord.ITEM, i + "");
//			}
//			list.add(section);
//		}
//	}
//
//	@Override
//	public int getCount() {
//
//		return list.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHodler holder;
//		if(convertView==null){
//			holder = new ViewHodler();
//			convertView = View.inflate(context, R.layout.three_in_one_item, null);
//			holder.date = (TextView) convertView.findViewById(R.id.three_in_one_item_date_tv);
//			holder.cardid0 = (TextView) convertView.findViewById(R.id.three_in_one_item_cardid0_tv);
//			holder.cardid1 = (TextView) convertView.findViewById(R.id.three_in_one_item_cardid1_tv);
//			holder.money = (TextView) convertView.findViewById(R.id.three_in_one_item_money_tv);
//			holder.status = (TextView) convertView.findViewById(R.id.three_in_one_item_status_tv);
//			convertView.setTag(holder);
//		}else{
//			holder = (ViewHodler) convertView.getTag();
//		}
//		 RechargeRecord item = list.get(position);
//		if (item.type == RechargeRecord.SECTION) {//头
//			convertView.setBackgroundColor(R.color.app_bg);
//			holder.date.setTextColor(Color.rgb(255, 97, 0));
//			holder.date.getPaint().setFakeBoldText(true);
//			holder.date.setText("本月");
//		} else {
//			  holder.date.setText("11-11");
//			 holder.cardid0.setVisibility(View.VISIBLE);
//		     holder.cardid1.setText("123343");
//		     holder.money.setText("+1000");
//		     holder.status.setText(R.string.business_success);
//		    
//		}
//		return convertView;
//	}
//	private static class ViewHodler {
//		TextView date, cardid0,cardid1, money, status;
//	}
//	@Override
//	public int getViewTypeCount() {
//		return 2;
//	}
//
//	@Override
//	public int getItemViewType(int position) {
//		return list.get(position).type;
//	}
//
//	@Override
//	public boolean isItemViewTypePinned(int viewType) {
//		return viewType == RechargeRecord.SECTION;
//	}
//
//
//
//}

public class ThreeInOneAdapter extends BaseExpandableListAdapter {
	private Context context;
	private ArrayList<Record> list = new ArrayList<Record>();
	public static final int RECHARGE = R.string.title_bar_title_recharge_rocord;
	public static final int AD = R.string.title_bar_title_adcosume;
	public static final int TRANSFER = R.string.title_bar_title_transfer;
	private int type;

	public ThreeInOneAdapter(Context context,ArrayList<Record> list, int type) {
		this.context = context;
		this.list = list;
		this.type = type;

	}
	
	public void setData(ArrayList<Record> list){
		this.list.addAll(list);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context,
					R.layout.three_in_one_item_group, null);
			holder.gMonth = (TextView) convertView
					.findViewById(R.id.three_in_one_item_group_date_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.gMonth.setText("12月");
		return convertView;
	}

	private static class ViewHolder {
		TextView gMonth, cDate, cCard0, cCard1, cMoney, cSatus;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
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
		switch (type) {
		case RECHARGE: {
			holder.cCard0.setText("卡号：");
			holder.cCard1.setText("12345687");

			if (childPosition % 2 == 0) {
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

			break;
		}

		case AD: {
			holder.cCard0.setVisibility(View.GONE);
			holder.cCard1.setTextColor(Color.rgb(102, 102, 102));
			holder.cCard1.setText("jiaojiao美食店123");
			if (childPosition % 2 == 0) {
				holder.cMoney.setTextColor(Color.RED);
				holder.cMoney.setText("+1000");

				holder.cSatus.setTextColor(Color.rgb(105, 185, 0));
				holder.cSatus.setText("交易成功");
			} else {
				holder.cMoney.setTextColor(Color.rgb(102, 102, 102));
				holder.cMoney.setText("+1000");
				holder.cSatus.setTextColor(Color.RED);
				holder.cSatus.setText("交易失败");
			}
			break;
		}
		case TRANSFER: {
			holder.cCard0.setTextColor(Color.rgb(102, 102, 102));
			holder.cCard0.setText("jiaojiao美食店123");
			holder.cCard1.setTextColor(Color.rgb(102, 102, 102));
			holder.cCard1.setText("jli@efubo.com");
			if (childPosition % 2 == 0) {
				holder.cMoney.setTextColor(Color.RED);
				holder.cMoney.setText("+1000");

				holder.cSatus.setTextColor(Color.rgb(105, 185, 0));
				holder.cSatus.setText("交易成功");
			} else {
				holder.cMoney.setTextColor(Color.rgb(102, 102, 102));
				holder.cMoney.setText("+1000");
				holder.cSatus.setTextColor(Color.RED);
				holder.cSatus.setText("交易失败");
			}
			break;
		}

		}

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
