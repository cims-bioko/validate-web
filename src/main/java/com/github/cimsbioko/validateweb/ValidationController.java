package com.github.cimsbioko.validateweb;

import com.github.cimsbioko.validate.Result;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class ValidationController {

    private final ValidationService service;

    public ValidationController(ValidationService validationService) {
        service = validationService;
    }

    @PostMapping(consumes = APPLICATION_XML_VALUE)
    public ResponseEntity<Result> validateXml(@RequestBody Resource xmlBody) throws IOException {
        Result result = service.validate(xmlBody.getInputStream());
        if (result.hasFailed()) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Result> validateMultipart(@RequestPart("xform") FilePart xformPart) throws IOException {
        File temp = File.createTempFile("mpupload", ".xml");
        xformPart.transferTo(temp);
        try (InputStream xmlStream = new FileInputStream(temp)) {
            Result result = service.validate(xmlStream);
            if (result.hasFailed()) {
                return ResponseEntity.badRequest().body(result);
            } else {
                return ResponseEntity.ok().body(result);
            }
        } finally {
            temp.delete();
        }
    }

}
