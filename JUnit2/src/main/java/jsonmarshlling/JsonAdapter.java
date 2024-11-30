package jsonmarshlling;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsonAdapter implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT, com.google.gson.JsonDeserializationContext context) throws JsonParseException {
        String dateStr = json.getAsString();
        DateFormat df = new SimpleDateFormat("mm dd, yyyy", new Locale("ru"));

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw new JsonParseException("Failed to parse Date: " + dateStr, e);
        }
    }
}

