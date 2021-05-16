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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
public class ErrorAdvice {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleBadRequestException(BadRequestException e){
        return createAPIResponse(e,"400");
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
        Response response = new Response();
        response.setCode("400");
        response.setDescription("Error in one or more field");

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.getErrors().add(new Error(fieldName,errorMessage));
        });
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
