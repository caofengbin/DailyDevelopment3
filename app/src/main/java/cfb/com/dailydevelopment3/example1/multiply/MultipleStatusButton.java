package cfb.com.dailydevelopment3.example1.multiply;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cfb.com.dailydevelopment3.R;

/**
 * 三状态切换按钮
 * Created by fengbincao on 2017/3/14.
 */

public class MultipleStatusButton extends LinearLayout {

	protected int index = -1;
	protected float textSize = -1;
	protected int textColorN = Color.BLACK;
	protected int textColorP = Color.BLACK;
	protected int leftBg = SEGMENT_LEFT_BG;
	protected int centerBg = SEGMENT_CENTER_BG;
	protected int rightBg = SEGMENT_RIGHT_BG;

	protected final static int SEGMENT_LEFT_BG = R.drawable.segment_left_selector;
	protected final static int SEGMENT_CENTER_BG = R.drawable.segment_center_selector;
	protected final static int SEGMENT_RIGHT_BG = R.drawable.segment_right_selector;

	protected CheckedRelativeLayout2[] checkedRelativeLayouts;
	protected OnIndexChangedListener onIndexChangedListener;

	public MultipleStatusButton(Context context) {
		super(context);
		initialize();
	}

	public MultipleStatusButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
		initFromAttributes(context, attrs);
	}

	public MultipleStatusButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
		initFromAttributes(context, attrs);
	}

	protected void initialize() {
		setGravity(Gravity.CENTER);
	}

	protected void initFromAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView);
		String content = a.getString(R.styleable.MultipleStatusView_content);
		index = a.getInt(R.styleable.MultipleStatusView_index, index);
		textSize = a.getDimension(R.styleable.MultipleStatusView_textSize, textSize);
		textColorN = a.getColor(R.styleable.MultipleStatusView_textColorN, textColorN);
		textColorP = a.getColor(R.styleable.MultipleStatusView_textColorP, textColorP);
		leftBg = a.getResourceId(R.styleable.MultipleStatusView_leftBg, leftBg);
		centerBg = a.getResourceId(R.styleable.MultipleStatusView_centerBg, centerBg);
		rightBg = a.getResourceId(R.styleable.MultipleStatusView_rightBg, rightBg);
		a.recycle();

		if(!TextUtils.isEmpty(content)) {
			String[] contentStrings = content.split(",");
			setContent(contentStrings);
		}
		setIndex(index);
	}

	public void setContent(String... content) {
		View[] views = new View[content.length];
		for (int i = 0, len = content.length; i < len; i++) {
			String s = content[i];
			TextView tv = new TextView(getContext());
			tv.setTextColor(textColorN);
			tv.setText(s);
			if (textSize != -1) {
				tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
			}
			views[i] = tv;
		}
		setContent(views);
	}

	public void setContent(View... content) {
		removeAllViews();
		int lastIndex = content.length - 1;
		checkedRelativeLayouts = new CheckedRelativeLayout2[content.length];
		checkedRelativeLayouts[0] = createLeftView(content[0]);
		checkedRelativeLayouts[lastIndex] = createRightView(content[lastIndex]);
		for (int i = 1; i < lastIndex; i++) {
			checkedRelativeLayouts[i] = createCenterView(content[i]);
		}
		for (View view : checkedRelativeLayouts) {
			LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			llp.weight = 1;
			addView(view, llp);
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int i) {
		if (i < 0)
			return;
		checkedRelativeLayouts[i].setChecked(true);
	}

	public void setTextColorN(int textColorN) {
		this.textColorN = textColorN;
	}

	public void setTextColorP(int textColorP) {
		this.textColorP = textColorP;
	}

	protected CheckedRelativeLayout.OnCheckedChangeListener checkedChangeListener = new CheckedRelativeLayout.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CheckedRelativeLayout layout, boolean isChecked) {
			if (isChecked) {
				for (CheckedRelativeLayout item : checkedRelativeLayouts) {
					if (!item.equals(layout)) {
						item.setChecked(false);
					}
				}
				if (onIndexChangedListener != null) {
					int i = indexOf(checkedRelativeLayouts, layout);
					index = i;
					if (onIndexChangedListener != null) {
						onIndexChangedListener.onChanged(MultipleStatusButton.this, index);
					}
				}
			}
		}
	};

	protected CheckedRelativeLayout2 createLeftView(View contentView) {
		CheckedRelativeLayout2 layout = new CheckedRelativeLayout2(getContext());
		layout.setBackgroundResource(leftBg);
		layout.setGravity(Gravity.CENTER);
		layout.addView(contentView);
		layout.setOnCheckedChangeListener(checkedChangeListener);
		return layout;
	}

	protected CheckedRelativeLayout2 createCenterView(View contentView) {
		CheckedRelativeLayout2 layout = new CheckedRelativeLayout2(getContext());
		layout.setBackgroundResource(centerBg);
		layout.setGravity(Gravity.CENTER);
		layout.addView(contentView);
		layout.setOnCheckedChangeListener(checkedChangeListener);
		return layout;
	}

	protected CheckedRelativeLayout2 createRightView(View contentView) {
		CheckedRelativeLayout2 layout = new CheckedRelativeLayout2(getContext());
		layout.setBackgroundResource(rightBg);
		layout.setGravity(Gravity.CENTER);
		layout.addView(contentView);
		layout.setOnCheckedChangeListener(checkedChangeListener);
		return layout;
	}

	public void setOnIndexChangedListener(OnIndexChangedListener l) {
		this.onIndexChangedListener = l;
	}

	protected class CheckedRelativeLayout2 extends CheckedRelativeLayout {

		protected TextView textView;

		public CheckedRelativeLayout2(Context context) {
			super(context);
		}

		@Override
		public void addView(View child) {
			super.addView(child);
			if (child instanceof TextView) {
				textView = (TextView) child;
			}
		}

		@Override
		public void setChecked(boolean checked) {
			super.setChecked(checked);
			if (textView != null) {
				if (checked) {
					textView.setTextColor(textColorP);
				} else {
					textView.setTextColor(textColorN);
				}
			}
		}
	}

	public interface OnIndexChangedListener {
		void onChanged(MultipleStatusButton view, int index);
	}

	public static <T> int indexOf(T[] array, T obj) {
		for (int i = 0, len = array.length; i < len; i++) {
			if (array[i].equals(obj))
				return i;
		}
		return -1;
	}
}
