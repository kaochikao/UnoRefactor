package websocket;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 * Created by jkao on 17/8/16.
 */
public class aggregationService {


    public static String jsonStrBundling(String dataType, String data){

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("dataType",dataType);
        builder.add("data",data);
        return  builder.build().toString();


    }

    public static String jsonStrBundling(String dataType, JsonArrayBuilder arrayBuilder){

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("dataType",dataType);
        builder.add("data",arrayBuilder.build());
        return builder.build().toString();

    }



}
