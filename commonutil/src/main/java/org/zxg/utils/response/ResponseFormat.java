package org.zxg.utils.response;


/**
 * 统一返回客户端数据格式
 */
public class ResponseFormat {

	public static GenericResponse error(int status,String message){
		return new GenericResponse(status, message);
	}
	public static GenericResponse error(int status,String message,Object data){
		return new GenericResponse(status, message,data);
	}
	public static GenericResponse success() {
		return new GenericResponse(ErrorCode.SUCCESS, "操作成功");
	}
	public static GenericResponse success(Object data) {
		return new GenericResponse(ErrorCode.SUCCESS, "操作成功",data);
	}
	public static GenericResponse success(Object data,String message) {
		return new GenericResponse(ErrorCode.SUCCESS, message,data);
	}
}