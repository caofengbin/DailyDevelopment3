package cfb.com.dailydevelopment3.example5.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 泛型使用的实体Bean2
 */

public class DaPanMoneyData2 {

	public int pages;

	@SerializedName("data")
	public List<DaPanMoneyData2.DaPanMoneyBean2> dataList;

	public static class DaPanMoneyBean2 {
		public String code;
		public String name;
		public String zdf;
		public String cje;
		public String hsl;
		public String zgb;
		public String zllr;
		public String zllc;
		public String zljlr;
		public LzgBean lzg;

		public static class LzgBean {
			public String name;
			public String code;
			public String zdf;
		}
	}
}
