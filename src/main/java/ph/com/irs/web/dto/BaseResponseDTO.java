package ph.com.irs.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by julius on 12/09/2018.
 */
@JsonInclude(Include.NON_NULL)
public class BaseResponseDTO<T> {

  private String message;
  private Status status;
  private String code;
  private T data;

  public enum Status {
    SUCCESS,
    ERROR
    ;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
