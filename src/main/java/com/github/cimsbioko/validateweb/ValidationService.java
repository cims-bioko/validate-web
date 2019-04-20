package com.github.cimsbioko.validateweb;

import com.github.cimsbioko.validate.Result;

import java.io.InputStream;

interface ValidationService {
    Result validate(InputStream xformContent);
}
