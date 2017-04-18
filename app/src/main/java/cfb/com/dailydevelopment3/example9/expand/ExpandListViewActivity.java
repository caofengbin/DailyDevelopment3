package cfb.com.dailydevelopment3.example9.expand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cfb.com.dailydevelopment3.R;


public class ExpandListViewActivity extends AppCompatActivity {

	private AnimatedExpandableListView expandListView;
	private ExpandListAdapter expandAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expand_list_view);


		List<GroupItem> items = new ArrayList<>();

		// Populate our list with groups and it's children
		for(int i = 1; i < 8; i++) {
			GroupItem item = new GroupItem();

			item.title = "分组 " + i;

			for(int j = 0; j < i; j++) {
				ChildItem child = new ChildItem();
				child.title = "分组中的元素 " + j;
				child.hint = "分组中的元素";

				item.items.add(child);
			}

			items.add(item);
		}

		expandAdapter = new ExpandListAdapter(this);
		expandAdapter.setData(items);

		expandListView = (AnimatedExpandableListView) findViewById(R.id.listView);
		expandListView.setAdapter(expandAdapter);

		expandListView.setGroupIndicator(null);

		// In order to show animations, we need to use a custom click handler
		// for our ExpandableListView.
		expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				// We call collapseGroupWithAnimation(int) and
				// expandGroupWithAnimation(int) to animate group
				// expansion/collapse.
				if (expandListView.isGroupExpanded(groupPosition)) {
					expandListView.collapseGroupWithAnimation(groupPosition);
					// 展开状态，启动收缩的动画
					expandAdapter.setIndicatorState(groupPosition, true);
					expandAdapter.setArrowInvisible(groupPosition, false);
				} else {
					expandListView.expandGroupWithAnimation(groupPosition);
					// 收缩状态，启动展开的动画
					expandAdapter.setIndicatorState(groupPosition, false);
					expandAdapter.setArrowInvisible(groupPosition, true);
				}

				// Log.e("fengbincao","点击了分组：" + groupPosition);
				return true;
			}

		});

		expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				// Log.e("fengbincao","点击了分组：" + groupPosition + "中的第：" + childPosition + "个元素");
				return false;
			}
		});

		// 首次今日页卡，默认展开所有的分组
		for(int i = 0; i < expandAdapter.getGroupCount(); i++){
			expandListView.expandGroup(i);
		}
	}

}
