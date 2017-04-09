package cfb.com.dailydevelopment3.example7.notification;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cfb.com.dailydevelopment3.R;

/**
 * 该Activity可以由通知栏的点击事件来唤醒
 */
public class Notification2Activity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification2);

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 通过下面的设置可以使通知栏的图标消失
		manager.cancel(2);
	}
}
