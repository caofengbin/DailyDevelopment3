package cfb.com.dailydevelopment3.example6.pinnedlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import cfb.com.dailydevelopment3.R;

public class PinnedListViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pinned_list_view);

		PinnedHeaderListView listView = (PinnedHeaderListView) findViewById(R.id.pinnedListView);

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		LinearLayout header1 = (LinearLayout) inflater.inflate(R.layout.list_item, null);
		((TextView) header1.findViewById(R.id.cityTextItem)).setText("省市信息列表");

		listView.addHeaderView(header1);

		TestSectionedAdapter sectionedAdapter = new TestSectionedAdapter();
		listView.setAdapter(sectionedAdapter);
	}
}
