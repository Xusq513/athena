package com.refutrue.athena.utils.template.exception;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/2 11:09
 * @Description: Template抛出异常
 */
public class TemplateException extends RuntimeException {
    
	private static final long serialVersionUID = -5959920715991771677L;

	protected String code;

    /**
     * 是否需要控制台输出
     */
    protected boolean loged = true;

    public TemplateException(String message) {

        super(message);
    }

    public TemplateException(String code, String message) {

        super(message);
        this.code = code;
    }

    public TemplateException(Throwable cause) {

        super(cause);
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
