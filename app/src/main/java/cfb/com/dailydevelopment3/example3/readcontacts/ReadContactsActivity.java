package cfb.com.dailydevelopment3.example3.readcontacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cfb.com.dailydevelopment3.R;

/**
 * 3.读取系统联系人
 */
public class ReadContactsActivity extends AppCompatActivity {

	@BindView(R.id.myContactsListView)
	public ListView myContactsListView;

	private ArrayAdapter<String> mAdapter;
	private List<String> contactsList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_contacts);

		ButterKnife.bind(this);

		mAdapter = new ArrayAdapter<>(this, android.R.layout. simple_list_item_1, contactsList);
		myContactsListView.setAdapter(mAdapter);

		if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
		} else {
			readContacts();
		}

	}

	/**
	 * 读取联系人信息的方法
	 */
	private void readContacts() {
		Cursor cursor = null;
		try {
			// 查询联系人数据
			cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {
					// 获取联系人姓名
					String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
					// 获取联系人手机号
					String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					contactsList.add(displayName + "\n" + number);
				}
				mAdapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					readContacts();
				} else {
					Toast.makeText(this, "您拒绝了读取联系人信息的权限", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
		}
	}
}
