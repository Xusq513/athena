package com.refutrue.athena.utils.exception;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/2 11:09
 * @Description: 框架Service层抛出异常
 */
public class AthenaException extends RuntimeException {
    
    protected String code;

    /**
     * 是否需要控制台输出
     */
    protected boolean loged = true;

    public AthenaException(String message) {

        super(message);
    }

    public AthenaException(String code, String message) {

        super(message);
        this.code = code;
    }

    public AthenaException(Throwable cause) {

        super(cause);
        // TODO Auto-generated constructor stub
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public boolean isLoged() {

        return loged;
    }

    public void setLoged(boolean loged) {

        this.loged = loged;
    }
}
