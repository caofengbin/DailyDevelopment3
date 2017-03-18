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
				switch(index) {
					case 0:
						Toast.makeText(UseMultipleStatusActivity.this, "设置为预发布环境", Toast.LENGTH_SHORT).show();
						break;
					case 1:
						Toast.makeText(UseMultipleStatusActivity.this, "设置为测试环境", Toast.LENGTH_SHORT).show();
						break;
					case 2:
						Toast.makeText(UseMultipleStatusActivity.this, "设置为正式环境", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		});
	}
}
