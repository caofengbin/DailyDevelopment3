package cfb.com.dailydevelopment3.example9.expand;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cfb.com.dailydevelopment3.R;


public class ExpandListViewActivity extends AppCompatActivity {

	private AnimatedExpandableListView listView;
	private ExampleAdapter adapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expand_list_view);

//		final ImageView rotationImage = (ImageView) findViewById(bt_rotationX);
//		final Animation anim = AnimationUtils
//				.loadAnimation(this, R.anim.anim);
//		final Animation anim2 = AnimationUtils
//				.loadAnimation(this, R.anim.anim2);
//		anim.setFillAfter(true);
//		anim2.setFillAfter(true);
//
//
//		rotationImage.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				rotationImage.startAnimation(anim2);
//			}
//		});




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

		adapter = new ExampleAdapter(this);
		adapter.setData(items);

		listView = (AnimatedExpandableListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		listView.setGroupIndicator(null);

		// In order to show animations, we need to use a custom click handler
		// for our ExpandableListView.
		listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				// We call collapseGroupWithAnimation(int) and
				// expandGroupWithAnimation(int) to animate group
				// expansion/collapse.
				if (listView.isGroupExpanded(groupPosition)) {
					listView.collapseGroupWithAnimation(groupPosition);
					// 展开状态，启动收缩的动画
					adapter.setIndicatorState(groupPosition, true);
					adapter.setArrowInvisible(groupPosition, false);
				} else {
					listView.expandGroupWithAnimation(groupPosition);
					// 收缩状态，启动展开的动画
					adapter.setIndicatorState(groupPosition, false);
					adapter.setArrowInvisible(groupPosition, true);
				}

				// Log.e("fengbincao","点击了分组：" + groupPosition);
				return true;
			}

		});

		listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				// Log.e("fengbincao","点击了分组：" + groupPosition + "中的第：" + childPosition + "个元素");
				return false;
			}
		});

		for(int i = 0; i < adapter.getGroupCount(); i++){

			listView.expandGroup(i);
//			adapter.setIndicatorState(i, true);
//			adapter.setArrowInvisible(i, false);

		}
	}

	private static class GroupItem {
		String title;
		List<ChildItem> items = new ArrayList<>();
	}

	private static class ChildItem {
		String title;
		String hint;
	}

	private static class ChildHolder {
		TextView title;
		TextView hint;
	}

	private static class GroupHolder {
		ImageView arrowIcon;
		TextView title;
		ImageView rightArea;
	}

	/**
	 * Adapter for our list of {@link GroupItem}s.
	 */
	private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
		private LayoutInflater inflater;

		private List<GroupItem> items;

		private Context mContext;
		//  用于存放Indicator的集合
		private SparseArray<ImageView> mIndicators;

		// 用于存放右侧的点击View
		private SparseArray<ImageView> mRightArrows;

		public ExampleAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(context);
			mIndicators = new SparseArray<>();
			mRightArrows = new SparseArray<>();
		}

		public void setData(List<GroupItem> items) {
			this.items = items;
		}

		public void setIndicatorState(final int groupPosition, boolean isExpanded) {

			if (isExpanded) {
				// 展开状态，启动收缩的动画
				Animation anim2 = AnimationUtils.loadAnimation(mContext, R.anim.anim2);
				anim2.setFillAfter(true);
				mIndicators.get(groupPosition).startAnimation(anim2);
			} else {
				// 收缩状态，启动展开的动画
				Animation anim1 = AnimationUtils.loadAnimation(mContext, R.anim.anim);
				anim1.setFillAfter(true);
				mIndicators.get(groupPosition).startAnimation(anim1);
			}
		}

		public void setArrowInvisible(final int groupPosition, boolean isExpanded) {

			if (isExpanded) {
				// 展开状态，设置为可见的
				//mRightArrows.get(groupPosition).setVisibility(View.VISIBLE);
				mRightArrows.get(groupPosition).animate()
						.alpha(1f)
						.setDuration(300)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								mRightArrows.get(groupPosition).setVisibility(View.VISIBLE);
							}
						});
			} else {
				// 收缩状态，设置为不可见的
				//mRightArrows.get(groupPosition).setVisibility(View.GONE);
				mRightArrows.get(groupPosition).animate()
						.alpha(0f)
						.setDuration(300)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								mRightArrows.get(groupPosition).setVisibility(View.GONE);
							}
						});
			}
		}

		@Override
		public ChildItem getChild(int groupPosition, int childPosition) {
			return items.get(groupPosition).items.get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
			ChildHolder holder;
			ChildItem item = getChild(groupPosition, childPosition);
			if (convertView == null) {
				holder = new ChildHolder();
				convertView = inflater.inflate(R.layout.list_item_expand, parent, false);
				holder.title = (TextView) convertView.findViewById(R.id.textTitle);
				holder.hint = (TextView) convertView.findViewById(R.id.textHint);
				convertView.setTag(holder);
			} else {
				holder = (ChildHolder) convertView.getTag();
			}


			holder.title.setText(item.title);
			holder.hint.setText(item.hint);

			return convertView;
		}

		@Override
		public int getRealChildrenCount(int groupPosition) {
			return items.get(groupPosition).items.size();
		}

		@Override
		public GroupItem getGroup(int groupPosition) {
			return items.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return items.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
			final GroupHolder holder;
			GroupItem item = getGroup(groupPosition);
			if (convertView == null) {
				holder = new GroupHolder();
				convertView = inflater.inflate(R.layout.group_item_expand, parent, false);
				holder.arrowIcon = (ImageView) convertView.findViewById(R.id.arrow_icon);
				holder.title = (TextView) convertView.findViewById(R.id.textTitle);
				holder.rightArea = (ImageView) convertView.findViewById(R.id.rightArea);
				convertView.setTag(holder);
			} else {
				holder = (GroupHolder) convertView.getTag();
			}

			mIndicators.put(groupPosition, holder.arrowIcon);
			mRightArrows.put(groupPosition, holder.rightArea);

			holder.title.setText(item.title);

			holder.rightArea.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, "点击了分组"+ groupPosition + "的查看更多按钮" ,Toast.LENGTH_SHORT).show();
				}
			});

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}


	}

}
