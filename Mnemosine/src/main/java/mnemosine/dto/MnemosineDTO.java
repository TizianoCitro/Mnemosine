package mnemosine.dto;

import java.io.Serializable;

public class MnemosineDTO<T> implements Serializable {
    public MnemosineDTO(String code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public MnemosineDTO() {
        clear();
    }

    public void clear() {
        this.success=false;
        this.code=DEFAULT_CODE;
        this.data=null;
    }

    public MnemosineDTO<T> success(String code, String message) {
        this.success=true;
        this.code=code;
        this.message=message;

        return this;
    }

    public MnemosineDTO<T> error(String code, String message) {
        this.success=false;
        this.code=code;
        this.message=message;
        this.data=null;

        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public MnemosineDTO<T> setData(T data) {
        this.data = data;

        return this;
    }

    private String code;
    private boolean success;
    private String message;
    private T data;

    public static final String DEFAULT_CODE="0";
    public static final String CODE="200";

    public static final String SUCCES_MESSAGE="Operazione effettuata con successo";
    public static final String FAIL_MESSAGE="Si è verificato un errore durante l'operazione";
}
