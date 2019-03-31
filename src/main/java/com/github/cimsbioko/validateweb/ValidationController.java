package com.github.cimsbioko.validateweb;

import com.github.cimsbioko.validate.Result;
import com.github.cimsbioko.validate.ResultBuilder;
import com.github.cimsbioko.validate.XFormValidator;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
public class ValidationController {

    @PostMapping(consumes = APPLICATION_XML_VALUE)
    public ResponseEntity<Result> validate(@RequestBody Resource xmlBody) throws IOException {
        ResultBuilder resultBuilder = new ResultBuilder();
        XFormValidator validator = new XFormValidator(resultBuilder);
        validator.validateStream(xmlBody.getInputStream());
        Result result = resultBuilder.buildResult();
        if (result.hasFailed()) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

}
