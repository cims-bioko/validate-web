package com.github.cimsbioko.validateweb;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.cimsbioko.validate.Message;
import com.github.cimsbioko.validate.Result;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ResultConverter {

    public static class MessageMarshaller extends JsonSerializer<Message> {
        @Override
        public void serialize(Message message, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeBooleanField("error", message.isError());
            gen.writeStringField("message", message.toString());
            gen.writeEndObject();
        }
    }

    public static class ResultMarshaller extends JsonSerializer<Result> {
        @Override
        public void serialize(Result result, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeBooleanField("valid", !result.hasFailed());
            jsonGenerator.writeFieldName("output");
            jsonGenerator.writeObject(result.getMessages());
            jsonGenerator.writeEndObject();
        }
    }
}
