package com.yiwangqingshui.mybatis.gen.expection;


public class DalgenException extends RuntimeException {
    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 7336283442300122583L;

    /**
     * Instantiates a new Dalgen exception.
     *
     * @param errorMsg the error msg
     */
    public DalgenException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * Instantiates a new Dalgen exception.
     *
     * @param errorMsg  the error msg
     * @param throwable the throwable
     */
    public DalgenException(String errorMsg, Throwable throwable){
        super(errorMsg,throwable);
    }
}
