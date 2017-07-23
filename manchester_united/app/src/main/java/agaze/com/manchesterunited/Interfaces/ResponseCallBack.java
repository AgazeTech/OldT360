package agaze.com.manchesterunited.Interfaces;

/**
 * Created by agaze on 11/8/16.
 * Use this interface in your activities to implement a callback when calling asynchronous methods defined in the model.
 * The T type can be any non primitive data types.
 */
public interface ResponseCallBack<T>
{
   public void  responseCallBack(T result);
}