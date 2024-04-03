package se.ifmo.core.io.format.json.adapters;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import se.ifmo.core.collection.Resource;
import se.ifmo.core.collection.objects.HumanBeing;

public class ResourceDeserializer implements JsonDeserializer<Resource> {
    @Override
    public Resource deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Resource resource = new Resource();
        json.getAsJsonObject().entrySet().forEach(entry -> {
            long key = Long.parseLong(entry.getKey());
            HumanBeing humanBeing = context.deserialize(entry.getValue(), HumanBeing.class);
            resource.put(key, humanBeing);
        });

        return resource;
    }
}

