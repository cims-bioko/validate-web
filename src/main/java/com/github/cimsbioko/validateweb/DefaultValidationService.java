package com.github.cimsbioko.validateweb;

import com.github.cimsbioko.validate.Result;
import com.github.cimsbioko.validate.ResultBuilder;
import com.github.cimsbioko.validate.XFormValidator;

import java.io.InputStream;

public class DefaultValidationService implements ValidationService {
    @Override
    public Result validate(InputStream xformContent) {
        ResultBuilder resultBuilder = new ResultBuilder();
        XFormValidator validator = new XFormValidator(resultBuilder);
        validator.validateStream(xformContent);
        return resultBuilder.buildResult();
    }
}
