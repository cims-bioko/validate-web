package com.github.cimsbioko.validator;

import java.util.List;

public interface ValidatorResult {

    boolean hasFailed();

    List<ValidatorMessage> getMessages();
}
