package cfb.com.dailydevelopment3.example5.gson;

/**
 * 网络请求返回的数据实体通用Bean
 */

public class ResultData<T> {
	public int code;
	public String msg;
	public T data;
}
