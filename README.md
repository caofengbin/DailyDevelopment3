# DailyDevelopment3
该项目主要记录平时学习过程中的一些零碎知识点(2017年2月)，具体目录如下：

## 1.一个三状态切换按钮的实现

仿照iOS的效果实现的一个三状态按钮，效果如下：

![三状态按钮的实现](http://occl9k36n.bkt.clouddn.com/2017_03_23_three_state_button.png)

具体代码实现细节可以参考第一个demo中的具体示例。

## 2.Android运行时权限处理

&emsp;&emsp;比较重要的一个话题，本demo中给出最基本的实现方式。

## 3.读取系统联系人

## 4.使用自定义的ContentProvider

---

## 5.gson的基本使用

---

## 6.头部悬停ListView的实现

[Github项目地址](https://github.com/JimiSmith/PinnedHeaderListView)

[另外一个不错的实现头部悬停效果的PinnedListView](https://github.com/beworker/pinned-section-listview)

[ Android仿联系人列表分组悬浮列表实现，自定义PinnedHeaderListView实现](http://blog.csdn.net/u010335298/article/details/51150346)

---

## 7.通知的初高级使用

### (1)通知的基本实现代码

``` java
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
```


### (2)使通知可以响应点击事件的方法

关键之处，在于构建PendingIntent:

``` java
// 创建PendingIntent
Intent intent = new Intent(this, Notification2Activity.class);
PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
```

而后再创建Notification时：

``` java

.setContentIntent(pi)

```

关于PendingIntent是一个非常重要的概念，可以参照《Android开发艺术探索》一书的第五章内容进行相应的学习。

### (3)演示通知的震动声音等效果

``` java
.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
.setVibrate(new long[]{0, 1000, 1000, 1000})
.setLights(Color.GREEN, 1000, 1000)
.setDefaults(NotificationCompat.DEFAULT_ALL)
```

上面的实现根据各个应用的需求选择性的设置即可。

### (4)构建富文本效果的通知

``` java
setStyle(new NotificationCompat.BigTextStyle().bigText()
```

### (5)使通知展示图片的效果

```java
.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
```

### (6)设置通知的优先级

``` java
.setPriority(NotificationCompat.PRIORITY_MAX)
```