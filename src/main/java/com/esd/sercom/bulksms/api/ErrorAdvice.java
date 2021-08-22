package com.esd.sercom.bulksms.api;


import com.esd.sercom.bulksms.exceptions.BadRequestException;
import com.esd.sercom.bulksms.exceptions.NotFoundException;
import com.esd.sercom.bulksms.model.base.CompleteLog;
import com.esd.sercom.bulksms.model.base.Error;
import com.esd.sercom.bulksms.model.base.Response;
import com.esd.sercom.bulksms.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
public class ErrorAdvice {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleBadRequestException(BadRequestException e){
        String code = "";
        if(StringUtils.hasText(e.getCode())) code = e.getCode();
        else code = "400";
        return createAPIResponse(e,code);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleNotFoundException(NotFoundException e){
        return createAPIResponse(e,"404");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleGeneralException(Exception e){
        return createAPIResponse(e,"404");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        Response response = new Response("400", "Error Occur in one or more fields");
        List<Error> errors = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(error);
        }
       response.setErrors(errors);
        return response;
    }
    private Response createAPIResponse(Exception e, String code){
        e.printStackTrace();
        CompleteLog log = LogUtil.getExceptionMessageWithGUID(e);
        logger.error(log.getUuid() + "\n" + log.getMessage());
        Response response = new Response(code, e.getMessage());
        response.setLogId(log.getUuid());

        return response;
    }
}
