package com.kafeinmevlut.garage.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.Date;

/**
 * @author mevlutbeder
 * @created 02/01/2023 03:51
 */
@Data
public class ApiError {

  private HttpStatus status;
  private int code;
  private String localMessage;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private Date date;

  private String debugMessage;
  private String oldStackTrace;

  private ApiError() {
    date = Calendar.getInstance().getTime();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status;
    this.code = status.value();
  }

  public ApiError(HttpStatus status, Throwable ex) {
    this.code = status.value();
    this.status = status;
    this.date = Calendar.getInstance().getTime();
    this.oldStackTrace = ex.getStackTrace()[0] != null ? ex.getStackTrace()[0].getFileName() + " : " + ex.getStackTrace()[0].getLineNumber() : "";
    this.localMessage = ex.getLocalizedMessage();
  }

  public ApiError(HttpStatus status, String localMessage, Throwable ex) {
    this.code = status.value();
    this.status = status;
    this.date = Calendar.getInstance().getTime();
    this.oldStackTrace = ex.getStackTrace()[0] != null ? ex.getStackTrace()[0].getFileName() + " : " + ex.getStackTrace()[0].getLineNumber() : "";
    this.localMessage = localMessage;
  }

  public ApiError(HttpStatus status, Throwable ex, String localMessage, String debugMessage) {
    this.code = status.value();
    this.status = status;
    this.date = Calendar.getInstance().getTime();
    this.oldStackTrace = ex.getStackTrace()[0] != null ? ex.getStackTrace()[0].getFileName() + " : " + ex.getStackTrace()[0].getLineNumber() : "";
    this.localMessage = localMessage;
    this.debugMessage = debugMessage;
  }

}
