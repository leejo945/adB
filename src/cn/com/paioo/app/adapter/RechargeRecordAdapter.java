package cn.com.paioo.app.adapter;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class RechargeRecordAdapter extends BaseExpandableListAdapter {
	private Context context;
	private ArrayList<User> list;      
    public RechargeRecordAdapter(Context context){
    	this.context =context;
    }
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 10;
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
		ViewHodler holder;
		if (convertView == null) {
			holder = new ViewHodler();
			convertView = View.inflate(context,
					R.layout.recharge_record_group_item, null);
			holder.date = (TextView) convertView
					.findViewById(R.id.recharge_record_group_item_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHodler) convertView.getTag();
		}
		holder.date.setText("12月");
		return convertView;
	}

	 
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		ViewHodler holder;
		if (convertView == null) {
			holder = new ViewHodler();
			convertView = View.inflate(context,
					R.layout.recharge_record_child_item, null);
			holder.date = (TextView) convertView
					.findViewById(R.id.recharge_record_child_item_date_tv);
			holder.cardid = (TextView) convertView
					.findViewById(R.id.recharge_record_child_item_cardid_tv);
			holder.rechargeNumber = (TextView) convertView
					.findViewById(R.id.recharge_record_child_item_number_tv);
			holder.businessStatus = (TextView) convertView
					.findViewById(R.id.recharge_record_child_item_status_tv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHodler) convertView.getTag();
		}
		holder.date.setText("12-12");
		holder.cardid.setText("1234432131231235");
		
		if(childPosition<3){//交易成功
			holder.rechargeNumber.setTextColor(R.color.orange);
			holder.businessStatus.setTextColor(R.color.business_success_text);
			holder.businessStatus.setText(R.string.business_success);
		}else{//交易失败
			holder.businessStatus.setTextColor(R.color.business_fail_text);
			holder.businessStatus.setText(R.string.business_fail);
		}
		
		holder.rechargeNumber.setText("+120");
		
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	private static class ViewHodler {
		TextView date, cardid, rechargeNumber, businessStatus;
	}
}
