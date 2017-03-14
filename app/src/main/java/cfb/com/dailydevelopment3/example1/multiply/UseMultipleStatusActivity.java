package cfb.com.dailydevelopment3.example1.multiply;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cfb.com.dailydevelopment3.R;

public class UseMultipleStatusActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_use_multiple_status);
		MultipleStatusButton myButton = (MultipleStatusButton) findViewById(R.id.my_multiple_status_button);
		myButton.setOnIndexChangedListener(new MultipleStatusButton.OnIndexChangedListener() {
			@Override
			public void onChanged(MultipleStatusButton view, int index) {
				Toast.makeText(UseMultipleStatusActivity.this, "点击了第" + index + "按钮", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
