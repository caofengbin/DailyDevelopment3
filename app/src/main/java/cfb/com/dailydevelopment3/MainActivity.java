package cfb.com.dailydevelopment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment3.example1.multiply.UseMultipleStatusActivity;
import cfb.com.dailydevelopment3.example2.runtimepermission.RunTimePermissionActivity;
import cfb.com.dailydevelopment3.example3.readcontacts.ReadContactsActivity;
import cfb.com.dailydevelopment3.example5.gson.GsonActivity;
import cfb.com.dailydevelopment3.example6.pinnedlistview.PinnedListViewActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	private String[] mainItems;
	private ListView mMainListView;
	private ArrayAdapter<String> itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainItems = getResources().getStringArray(R.array.main_view_string_array);
		mMainListView = (ListView) findViewById(R.id.main_activity_listView);
		itemAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, mainItems);

		mMainListView.setAdapter(itemAdapter);
		mMainListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
		switch (position) {
			case 0:
				startIntent(UseMultipleStatusActivity.class);
				break;
			case 1:
				startIntent(RunTimePermissionActivity.class);
				break;
			case 2:
				startIntent(ReadContactsActivity.class);
				break;
			case 3:
				// 没有相应的Activity跳转实现
				break;
			case 4:
				// 使用gson
				startIntent(GsonActivity.class);
				break;
			case 5:
				startIntent(PinnedListViewActivity.class);
				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
		}
	}

	private void startIntent(Class class1){
		Intent intent = new Intent(MainActivity.this,class1);
		startActivity(intent);
	}
}
