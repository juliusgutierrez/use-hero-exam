package ph.com.irs.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ph.com.irs.web.dto.BaseResponseDTO;
import ph.com.irs.web.dto.BaseResponseDTO.Status;

/**
 * Created by julius on 14/09/2018.
 */

@ControllerAdvice
public class GlobalExceptionController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(GlobalExceptionController.class);

  /**
   * Exception Handler for Exception
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponseDTO> handleMR(Exception ex) {
    LOGGER.warn(ex.getMessage(), ex);
    BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
    baseResponseDTO.setStatus(Status.ERROR);
    baseResponseDTO.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(baseResponseDTO);
  }
}
