package cfb.com.dailydevelopment3.example5.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cfb.com.dailydevelopment3.R;

/**
 * 演示gosn的基本和高级用法示例
 */
public class GsonActivity extends AppCompatActivity {

	private static final String TAG = "GsonActivity";

	@BindView(R.id.useGsonButton1)
	Button useGson1;

	@BindView(R.id.useGsonButton2)
	Button useGson2;

	@BindView(R.id.useGsonButton3)
	Button useGson3;

	@BindView(R.id.useGsonButton4)
	Button useGson4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gson);

		ButterKnife.bind(this);
	}

	@OnClick(R.id.useGsonButton1)
	public void useGson1() {
		// 主要为了演示在gson中使用泛型
		Gson gson = new Gson();
		List<City> cityList = gson.fromJson(getDataFileContent("data.json"), new TypeToken<List<City>>() {}.getType());
		for(City city : cityList) {
			Log.e(TAG, city.getId() + ":" + city.getName());
		}
	}

	@OnClick(R.id.useGsonButton2)
	public void useGson2() {
		// 主要为了演示在gson中使用泛型
		Gson gson = new Gson();
		Type DaPanMoneyDataType1 = new TypeToken<ResultData<DaPanMoneyData1>>(){}.getType();
		try {
			ResultData<DaPanMoneyData1> resultData1 = gson.fromJson(getDataFileContent("data2.json"), DaPanMoneyDataType1);
			Log.e(TAG, "code值为："+resultData1.code);
			Log.e(TAG, "msg值为："+resultData1.msg);
			Log.e(TAG, "size值为："+resultData1.data.dataList.size());
			Log.e(TAG, "page值为："+resultData1.data.pages);
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "gson解析数据抛出了异常");
		}
	}

	@OnClick(R.id.useGsonButton3)
	public void useGson3() {
		// 主要为了演示在gson中使用泛型
		Gson gson = new Gson();
		Type DaPanMoneyDataType2 = new TypeToken<ResultData<DaPanMoneyData2>>(){}.getType();
		try {
			ResultData<DaPanMoneyData2> resultData2 = gson.fromJson(getDataFileContent("data3.json"), DaPanMoneyDataType2);
			Log.e(TAG, "code值为：" + resultData2.code);
			Log.e(TAG, "msg值为：" + resultData2.msg);
			Log.e(TAG, "size值为：" + resultData2.data.dataList.size());
			Log.e(TAG, "page值为：" + resultData2.data.pages);
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "gson解析数据抛出了异常");
		}
	}

	@OnClick(R.id.useGsonButton4)
	public void useGson4() {
		// 主要为了演示在gson中使用泛型
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
				.registerTypeAdapter(int.class, new IntegerDefault0Adapter())
				.create();

		JsonBean result = gson.fromJson(getDataFileContent("data4.json"), JsonBean.class);
		Log.e(TAG, "result的id：" + result.getData().getId());
		Log.e(TAG, "result的newsId：" + result.getData().getNewsId());
	}

	private String getDataFileContent(String fileName) {
		try {
			InputStream in = getApplicationContext().getResources().getAssets().open(fileName);
			String resInput = convertStreamToString(in);

			return resInput;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String convertStreamToString(InputStream is) {
        /*
        * To convert the InputStream to String we use the BufferedReader.readLine()
        * method. We iterate until the BufferedReader return null which means
        * there's no more data to read. Each line will appended to a StringBuilder
        * and returned as String.
        */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
