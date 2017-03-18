package cfb.com.dailydevelopment3.example2.runtimepermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cfb.com.dailydevelopment3.R;

/**
 * 2.运行时权限处理的代码实现
 */

public class RunTimePermissionActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_time_permission);

		Button makeTelephoneCall = (Button) findViewById(R.id.makePhoneCall);
		makeTelephoneCall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ContextCompat.checkSelfPermission(RunTimePermissionActivity.this,
						Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(RunTimePermissionActivity.this,
							new String[]{Manifest.permission.CALL_PHONE}, 1);
				} else {
					call();
				}
			}
		});
	}

	private void call() {
		try {
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:10086"));
			startActivity(intent);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch(requestCode) {
			case 1:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					call();
				} else {
					Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
		}
	}
}
