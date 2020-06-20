package com.codenation.central.api.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationObject {
    private String message;
    private String field;
    private Object parameter;
}
