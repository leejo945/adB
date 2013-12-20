 
package cn.com.paioo.app.adapter;

import java.util.ArrayList;

import cn.com.paioo.app.R;
import cn.com.paioo.app.entity.PresenterIncome;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PresenterIncomeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PresenterIncome> list;
    public PresenterIncomeAdapter( Context context, ArrayList<PresenterIncome> list){
    	this.context = context;
    	this.list = list;
    }
    public void setData( ArrayList<PresenterIncome> list){
    	this.list.addAll(list);
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		return  position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView  = View.inflate(context, R.layout.presenter_income_lv_item, null);
			holder.time = (TextView) convertView.findViewById(R.id.presenter_income_lv_item_time_tv);
			holder.phone = (TextView) convertView.findViewById(R.id.presenter_income_lv_item_phone_tv);
			holder.money = (TextView) convertView.findViewById(R.id.presenter_income_lv_item_money_tv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		 
		return convertView;
	}
	private static class ViewHolder{
		TextView time,phone,money;
	}
  
}
