package cfb.com.dailydevelopment3.example9.expand;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cfb.com.dailydevelopment3.R;

/**
 * 可展开ListView的Adapter
 * Created by fengbincao on 2017/4/18.
 */

public class ExpandListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

	private Context mContext;
	private LayoutInflater inflater;

	private List<GroupItem> items;

	//  用于存放Indicator的集合
	private SparseArray<ImageView> mIndicators;

	// 用于存放右侧的点击View
	private SparseArray<ImageView> mRightArrows;

	public ExpandListAdapter(Context context) {
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
	public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ChildHolder holder;
		ChildItem item = (ChildItem)getChild(groupPosition, childPosition);
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
	public int getGroupCount() {
		return items.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return items.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return items.get(groupPosition).items.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		final GroupHolder holder;
		GroupItem item = (GroupItem)getGroup(groupPosition);
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
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
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

}
