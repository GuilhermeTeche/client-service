package br.com.itau.clientapi.modules.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;

public class CustomStringDeserializer extends StdDeserializer<String> {

    public CustomStringDeserializer() {
        this(null);
    }

    public CustomStringDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (JsonNodeType.STRING.equals(node.getNodeType())) {
            return node.asText();
        }

        return "";
    }
}
