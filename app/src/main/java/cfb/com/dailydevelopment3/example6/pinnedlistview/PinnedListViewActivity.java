package cfb.com.dailydevelopment3.example6.pinnedlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cfb.com.dailydevelopment3.R;

public class PinnedListViewActivity extends AppCompatActivity {

	private static final String TAG = "PinnedListViewActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pinned_list_view);

		PinnedHeaderListView listView = (PinnedHeaderListView) findViewById(R.id.pinnedListView);

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		LinearLayout header1 = (LinearLayout) inflater.inflate(R.layout.head_item_small, null);
		//((TextView) header1.findViewById(R.id.cityTextItem)).setText("省市信息列表");

		listView.addHeaderView(header1);

		TestSectionedAdapter sectionedAdapter = new TestSectionedAdapter();
		listView.setAdapter(sectionedAdapter);

		listView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
				Log.e(TAG, "点击了section：" + section + "的第" + position + "个元素");
			}

			@Override
			public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
				Log.e(TAG, "点击了session，位置为：" + section);
			}
		});
	}
}
