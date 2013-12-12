package cn.com.paioo.app.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cn.com.paioo.app.entity.RechargeRecord;
import cn.com.paioo.app.view.PinnedSectionListView.PinnedSectionListAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RechargeRecordAdapter extends BaseAdapter implements
		PinnedSectionListAdapter {
	Context context;
	private ArrayList<RechargeRecord> list = new ArrayList<RechargeRecord>();

	public RechargeRecordAdapter(Context context) {
		this.context = context;
		for (int i = 0; i < 10000; i++) {
			RechargeRecord section;
			if(i%10==0){
				 section = new RechargeRecord(RechargeRecord.SECTION, i+"");
			}else{
				 section = new RechargeRecord(RechargeRecord.ITEM, i+"");
			}
			list.add(section);
		}

		//
		// final int sectionsNumber = 'Z' - 'A' + 1;
		// int sectionPosition = 0, listPosition = 0;
		// for (char i = 0; i < sectionsNumber; i++) {
		// RechargeRecord section = new RechargeRecord(RechargeRecord.SECTION,
		// String.valueOf((char) ('A' + i)));
		// section.sectionPosition = sectionPosition;
		// section.listPosition = listPosition++;
		// list.add(section);
		// // add(section);
		//
		// final int itemsNumber = (int) Math.abs((Math.cos(2f * Math.PI / 3f
		// * sectionsNumber / (i + 1f)) * 25f));
		// for (int j = 0; j < itemsNumber; j++) {
		// RechargeRecord item = new RechargeRecord(RechargeRecord.ITEM,
		// section.text.toUpperCase(Locale.ENGLISH) + " - " + j);
		// item.sectionPosition = sectionPosition;
		// item.listPosition = listPosition++;
		// // add(item);
		// list.add(item);
		// }
		// sectionPosition++;
		// }

	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = new TextView(context);
	
		view.setTag("" + position);
		RechargeRecord item = list.get(position);
		if (item.type == RechargeRecord.SECTION) {
			// view.setOnClickListener(PinnedSectionListActivity.this);
			Random r = new Random();
			view.setTextColor(Color.RED);
			view.setBackgroundColor(Color.rgb(r.nextInt(255), r.nextInt(255),
					r.nextInt(255)));
			view.setText(position + "");
		}else{
			view.setTextColor(Color.BLUE);
			view.setText(position + "");
		}
		return view;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return list.get(position).type;
	}

	@Override
	public boolean isItemViewTypePinned(int viewType) {

		return viewType == RechargeRecord.SECTION;
	}

}

// import java.util.ArrayList;
//
// import cn.com.paioo.app.R;
// import cn.com.paioo.app.entity.User;
//
// import android.annotation.SuppressLint;
// import android.content.Context;
// import android.view.View;
// import android.view.ViewGroup;
// import android.widget.BaseExpandableListAdapter;
// import android.widget.TextView;
//
// @SuppressLint("ResourceAsColor")
// public class RechargeRecordAdapter extends BaseExpandableListAdapter {
// private Context context;
// private ArrayList<User> list;
// public RechargeRecordAdapter(Context context){
// this.context =context;
// }
// @Override
// public int getGroupCount() {
// // TODO Auto-generated method stub
// return 10;
// }
//
// @Override
// public int getChildrenCount(int groupPosition) {
// // TODO Auto-generated method stub
// return 10;
// }
//
// @Override
// public Object getGroup(int groupPosition) {
// // TODO Auto-generated method stub
// return groupPosition;
// }
//
// @Override
// public Object getChild(int groupPosition, int childPosition) {
// // TODO Auto-generated method stub
// return childPosition;
// }
//
// @Override
// public long getGroupId(int groupPosition) {
// // TODO Auto-generated method stub
// return groupPosition;
// }
//
// @Override
// public long getChildId(int groupPosition, int childPosition) {
// // TODO Auto-generated method stub
// return childPosition;
// }
//
// @Override
// public boolean hasStableIds() {
// // TODO Auto-generated method stub
// return false;
// }
//
// @Override
// public View getGroupView(int groupPosition, boolean isExpanded,
// View convertView, ViewGroup parent) {
// ViewHodler holder;
// if (convertView == null) {
// holder = new ViewHodler();
// convertView = View.inflate(context,
// R.layout.recharge_record_group_item, null);
// holder.date = (TextView) convertView
// .findViewById(R.id.recharge_record_group_item_tv);
// convertView.setTag(holder);
// } else {
// holder = (ViewHodler) convertView.getTag();
// }
// holder.date.setText("12月");
// return convertView;
// }
//
//
// @Override
// public View getChildView(int groupPosition, int childPosition,
// boolean isLastChild, View convertView, ViewGroup parent) {
//
// ViewHodler holder;
// if (convertView == null) {
// holder = new ViewHodler();
// convertView = View.inflate(context,
// R.layout.recharge_record_child_item, null);
// holder.date = (TextView) convertView
// .findViewById(R.id.recharge_record_child_item_date_tv);
// holder.cardid = (TextView) convertView
// .findViewById(R.id.recharge_record_child_item_cardid_tv);
// holder.rechargeNumber = (TextView) convertView
// .findViewById(R.id.recharge_record_child_item_number_tv);
// holder.businessStatus = (TextView) convertView
// .findViewById(R.id.recharge_record_child_item_status_tv);
//
// convertView.setTag(holder);
// } else {
// holder = (ViewHodler) convertView.getTag();
// }
// holder.date.setText("12-12");
// holder.cardid.setText("1234432131231235");
//
// if(childPosition<3){//交易成功
// holder.rechargeNumber.setTextColor(R.color.orange);
// holder.businessStatus.setTextColor(R.color.business_success_text);
// holder.businessStatus.setText(R.string.business_success);
// }else{//交易失败
// holder.businessStatus.setTextColor(R.color.business_fail_text);
// holder.businessStatus.setText(R.string.business_fail);
// }
//
// holder.rechargeNumber.setText("+120");
//
//
// return convertView;
// }
//
// @Override
// public boolean isChildSelectable(int groupPosition, int childPosition) {
// // TODO Auto-generated method stub
// return false;
// }
//
// private static class ViewHodler {
// TextView date, cardid, rechargeNumber, businessStatus;
// }
// }
