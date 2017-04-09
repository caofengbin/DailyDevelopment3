package cfb.com.dailydevelopment3.example7.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cfb.com.dailydevelopment3.R;

public class NotificationActivity extends AppCompatActivity {

	@BindView(R.id.first_notification)
	Button firstButton;

	@BindView(R.id.second_notification)
	Button secondButton;

	@BindView(R.id.third_notification)
	Button thirdButton;

	@BindView(R.id.fourth_notification)
	Button fourthButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.first_notification)
	public void sendFirstNotification() {
		// 第一步，获取NotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 第二步，创建Notification
		Notification notification = new NotificationCompat.Builder(this)
				.setContentTitle("通知的标题")
				.setContentText("通知的内容")
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.build();
		// 第三步，发送通知
		manager.notify(1, notification);
	}

	@OnClick(R.id.second_notification)
	public void sendSecondtNotification() {
		// 创建PendingIntent
		Intent intent = new Intent(this, Notification2Activity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

		// 第一步，获取NotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 第二步，创建Notification
		Notification notification = new NotificationCompat.Builder(this)
				.setContentTitle("通知的标题")
				.setContentText("通知的内容")
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.setContentIntent(pi)
				.build();
		// 第三步，发送通知
		manager.notify(2, notification);
	}

	/**
	 * 该案例的核心，是使用setDefaults(NotificationCompat.DEFAULT_ALL)
	 * 根据当前手机的环境来决定播放什么铃声，以及如何震动等效果
	 */
	@OnClick(R.id.third_notification)
	public void sendThirdNotification() {
		// 创建PendingIntent
		Intent intent = new Intent(this, Notification2Activity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

		// 第一步，获取NotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 第二步，创建Notification
		Notification notification = new NotificationCompat.Builder(this)
				.setContentTitle("通知的标题")
				.setContentText("通知的内容")
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.setContentIntent(pi)
				.setDefaults(NotificationCompat.DEFAULT_ALL)
				.build();
		// 第三步，发送通知
		manager.notify(2, notification);
	}

	/**
	 * 是通知可以展示长文字
	 */
	@OnClick(R.id.fourth_notification)
	public void sendFourthNotification() {
		// 创建PendingIntent
		Intent intent = new Intent(this, Notification2Activity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

		// 第一步，获取NotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 第二步，创建Notification
		Notification notification = new NotificationCompat.Builder(this)
				.setContentTitle("通知的标题")
				.setContentText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android.")
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.setContentIntent(pi)
				.setDefaults(NotificationCompat.DEFAULT_ALL)
				//.setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
				.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
				.setPriority(NotificationCompat.PRIORITY_MAX)
				.build();
		// 第三步，发送通知
		manager.notify(2, notification);
	}
}
