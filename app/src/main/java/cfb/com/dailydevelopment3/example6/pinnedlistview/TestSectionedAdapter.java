package cfb.com.dailydevelopment3.example6.pinnedlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cfb.com.dailydevelopment3.R;

/**
 * 测试使用的数据Adapter
 * Created by fengbincao on 2017/3/30.
 */

public class TestSectionedAdapter extends SectionedBaseAdapter {

	@Override
	public Object getItem(int section, int position) {
		return null;
	}

	@Override
	public long getItemId(int section, int position) {
		return 0;
	}

	@Override
	public int getSectionCount() {
		return 5;
	}

	@Override
	public int getCountForSection(int section) {
		return 5;
	}

	@Override
	public View getItemView(int section, int position, View convertView, ViewGroup parent) {
		LinearLayout layout;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = (LinearLayout) inflater.inflate(R.layout.list_item, null);
		} else {
			layout = (LinearLayout) convertView;
		}
		TextView cityText = (TextView) layout.findViewById(R.id.cityTextItem);
		cityText.setText(cityList[section][position]);
		return layout;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
		RelativeLayout layout;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = (RelativeLayout) inflater.inflate(R.layout.header_item, null);
		} else {
			layout = (RelativeLayout) convertView;
		}
		final TextView provinceText = (TextView) layout.findViewById(R.id.province_name);
		switch (section) {
			case 0:
				provinceText.setText("北京");
				break;
			case 1:
				provinceText.setText("河南");
				break;
			case 2:
				provinceText.setText("山东");
				break;
			case 3:
				provinceText.setText("江苏");
				break;
			case 4:
				provinceText.setText("湖北");
				break;
		}

		return layout;
	}

	String cityList[ ][ ] = {
			{"崇文区", "玄武区", "海淀区", "怀柔县", "延庆县"},
			{"郑州", "驻马店", "许昌", "鹤壁", "焦作"},
			{"济南", "青岛", "烟台", "威海", "泰安"},
			{"南京", "苏州", "连云港", "徐州", "扬州"},
			{"武汉", "襄阳", "十堰", "荆州", "宜昌"},
	};
}
