package com.alexgaoyh.MutiModule.service.facade.exception;

/**
 *  facade service服务的异常类
 * @author alexgaoyh
 *
 */
public class FacadeServiceException extends Exception {
	
    private static final long serialVersionUID = 949282286716874926L;

    public FacadeServiceException( String message )
    {
        super( message );
    }

    public FacadeServiceException( String message, Throwable throwable )
    {
        super( message, throwable );
    }
}
