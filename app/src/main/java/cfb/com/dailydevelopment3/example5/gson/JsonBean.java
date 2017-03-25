package cfb.com.dailydevelopment3.example5.gson;

/**
 * Created by Administrator on 2017/3/25.
 */

public class JsonBean {

	/**
	 * data : {"id":5638,"newsId":""}
	 */

	private DataBean data;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 5638
		 * newsId :
		 */

		private int id;
		private int newsId;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getNewsId() {
			return newsId;
		}

		public void setNewsId(int newsId) {
			this.newsId = newsId;
		}
	}
}