package com.github.cimsbioko.validator;

import org.opendatakit.validate.FormValidator;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ValidatorService {

    public ValidatorResult validate(InputStream xmlSource) {
        FormValidator validator = new FormValidator();
        ResultBuilder resultBuilder = new ResultBuilder(validator);
        validator.setErrorListener(resultBuilder);
        validator.validate(xmlSource);
        return resultBuilder.buildResult();
    }

}
