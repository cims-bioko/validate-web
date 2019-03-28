package com.github.cimsbioko.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ValidatorServiceTest {

    ValidatorService service;

    @Before
    public void setup() {
        service = new ValidatorService();
    }

    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void nullStream() {
        ValidatorResult result = service.validate(null);
        assertNotNull(result);
        assertTrue(result.hasFailed());
    }

    @Test
    public void emptyStream() {
        ValidatorResult result = service.validate(new ByteArrayInputStream("".getBytes()));
        assertNotNull(result);
        assertTrue(result.hasFailed());
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getMessages().stream().anyMatch(m -> m.toString().contains("Premature end of file")));
    }

    @Test
    public void simplestValid() {
        ValidatorResult result = service.validate(loadTestForm("simplest"));
        assertFalse(result.hasFailed());
        assertTrue(result.getMessages().stream().anyMatch(m -> m.toString().contains("Xform is valid")));
    }

    @Test
    public void simplestInvalid() {
        ValidatorResult result = service.validate(loadTestForm("simplest-broken"));
        assertTrue(result.hasFailed());
        assertTrue(result.getMessages().stream().anyMatch(m -> m.toString().contains("XForm is invalid")));
        assertTrue(result.getMessages().stream().anyMatch(m -> m.toString().contains("problem with readonly condition")));
    }

    private InputStream loadTestForm(String name) {
        return ValidatorServiceTest.class.getResourceAsStream(String.format("/%s.xml", name));
    }
}
