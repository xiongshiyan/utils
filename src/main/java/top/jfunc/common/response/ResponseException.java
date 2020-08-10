package top.jfunc.common.response;

/**
 * 使用的时候直接抛出异常ResponseException(如果想携带resultCode,message,data等信息)
 * @author xiongshiyan at 2020/8/10 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class ResponseException extends RuntimeException{
    private int resultCode;
    private Object data = null;

    public ResponseException(int resultCode, String message, Object data){
        super(message);
        this.resultCode = resultCode;
        this.data = data;
    }

    public ResponseException(int resultCode, String message){
        this(resultCode, message, null);
    }
    public ResponseException(String message){
        super(message);
    }
    public int getResultCode(){
        return resultCode;
    }
    public void setResultCode(int resultCode){ this.resultCode = resultCode; }
    public Object getData(){
        return data;
    }
    public void setData(Object data){
        this.data = data;
    }
    
}
