package top.jfunc.common.response;

/**
 * 通用的返回
 * @author xiongshiyan at 2020/8/10 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class ResponseData {
    private int resultCode;
    private String message = "OK";
    private Object data;


    public ResponseData(int resultCode, String message, Object data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public ResponseData(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResponseData(int resultCode) {
        this.resultCode = resultCode;
    }

    public static ResponseData build(int resultCode, String message, Object data){
        return new ResponseData(resultCode, message, data);
    }
    public static ResponseData build(int resultCode, String message){
        return new ResponseData(resultCode);
    }
    public static ResponseData build(int resultCode){
        return new ResponseData(resultCode);
    }



    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
