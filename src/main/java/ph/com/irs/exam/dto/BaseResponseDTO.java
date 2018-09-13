package ph.com.irs.exam.dto;

import java.util.List;

/**
 * Created by julius on 12/09/2018.
 */
public class BaseResponseDTO<T> {

  protected String message;
  protected boolean status;        // mr specific code
  protected String code;
  List<T> data;


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
