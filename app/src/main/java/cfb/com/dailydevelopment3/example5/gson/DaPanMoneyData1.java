package cfb.com.dailydevelopment3.example5.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型使用的实体Bean1
 */

public class DaPanMoneyData1 {

	public int pages;

	@SerializedName("data")
	public List<DaPanMoneyBean1> dataList;


	public DaPanMoneyData1() {
		dataList = new ArrayList<>();
	}

	public static class DaPanMoneyBean1 {

		public String code;
		public String name;
		public String zxj;
		public String zdf;
		public String zs;
		public String hsl;
		public String ltsz;
		public String zsz;
		public String lb;
		public String zf5;
		public String zf10;
		public String zf20;
		public String zllr;
		public Object zllr5;
		public Object zllc;
		public Object zllc5;
		public Object zljlr;
	}
}
