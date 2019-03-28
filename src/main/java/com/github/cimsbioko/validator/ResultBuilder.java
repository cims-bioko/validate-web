package com.github.cimsbioko.validator;

import org.opendatakit.validate.ErrorListener;
import org.opendatakit.validate.FormValidator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.github.cimsbioko.validator.Throwables.getStackTrace;
import static java.lang.System.lineSeparator;

class ResultBuilder implements ErrorListener {

    private final FormValidator validator;
    private final List<ValidatorMessage> messages = new LinkedList<>();

    ResultBuilder(FormValidator validator) {
        this.validator = validator;
    }

    @Override
    public void error(Object o) {
        append(new ErrorMessage(o));
    }

    @Override
    public void error(Object o, Throwable throwable) {
        append(new ExceptionMessage(o, throwable));
    }

    @Override
    public void info(Object o) {
        append(new InfoMessage(o));
    }

    private void append(ValidatorMessage msg) {
        messages.add(msg);
    }

    ValidatorResult buildResult() {
        return new Result(validator.isError(), messages);
    }
}


class InfoMessage implements ValidatorMessage {

    private final String message;

    InfoMessage(Object object) {
        message = object.toString();
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public String toString() {
        return message;
    }
}


class ErrorMessage implements ValidatorMessage {

    private final String message;

    ErrorMessage(Object object) {
        message = object.toString();
    }

    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public String toString() {
        return message;
    }
}


class ExceptionMessage extends ErrorMessage {

    private final String stackTrace;

    ExceptionMessage(Object object, Throwable throwable) {
        super(object);
        stackTrace = getStackTrace(throwable);
    }

    @Override
    public String toString() {
        return super.toString() + lineSeparator() + stackTrace;
    }
}


class Result implements ValidatorResult {

    private final boolean failed;
    private final List<ValidatorMessage> messages;

    Result(boolean failed, List<ValidatorMessage> messages) {
        this.failed = failed;
        this.messages = Collections.unmodifiableList(messages);
    }

    @Override
    public boolean hasFailed() {
        return failed;
    }

    @Override
    public List<ValidatorMessage> getMessages() {
        return messages;
    }
}
