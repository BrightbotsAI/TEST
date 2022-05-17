package com.brightbots.dto.watsonchat;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;

import java.io.IOException;

@JsonDeserialize(using = ResponseUnion.Deserializer.class)
@JsonSerialize(using = ResponseUnion.Serializer.class)
public class ResponseUnion {

    public ResponseMSTeams responseResponseValue;
    public String stringValue;

    static class Deserializer extends JsonDeserializer<ResponseUnion> {
        @Override
        public ResponseUnion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            ResponseUnion value = new ResponseUnion();
            switch (jsonParser.currentToken()) {
                case VALUE_STRING:
                    String string = jsonParser.readValueAs(String.class);
                    value.stringValue = string;
                    break;
                case START_OBJECT:
                    value.responseResponseValue = jsonParser.readValueAs(ResponseMSTeams.class);
                    break;
                default: throw new IOException("Cannot deserialize ResponseUnion");
            }
            return value;
        }
    }

    static class Serializer extends JsonSerializer<ResponseUnion> {
        @Override
        public void serialize(ResponseUnion obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (obj.responseResponseValue != null) {
                jsonGenerator.writeObject(obj.responseResponseValue);
                return;
            }
            if (obj.stringValue != null) {
                jsonGenerator.writeObject(obj.stringValue);
                return;
            }
            throw new IOException("ResponseUnion must not be null");
        }
    }
}
