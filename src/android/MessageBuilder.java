package com.dewmaple.geolocation.baidu;

import com.baidu.location.BDLocation;
import com.dewmaple.geolocation.w3.Coordinates;
import com.dewmaple.geolocation.w3.Position;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageBuilder {

    BDLocation location;

    MessageBuilder(BDLocation location) {
        this.location = location;
    }

    public JSONArray build() {
        Position result = new Position()
                .setTimestamp(System.currentTimeMillis())
                .setCoords(new Coordinates()
                        .setLatitude(location.getLatitude())
                        .setLongitude(location.getLongitude())
                        .setAccuracy(location.getRadius())
                        .setHeading(location.getDirection())
                        .setSpeed(location.getSpeed())
                        .setAltitude(location.getAltitude())
                );

        JSONObject extra = new JSONObject();
        try {
            extra.put("type", location.getLocType());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray message = new JSONArray();

        message.put(result.toJSON());
        message.put(extra);

        return message;
    }
}
