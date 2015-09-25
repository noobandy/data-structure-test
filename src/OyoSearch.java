import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * @author anandm
 * @date Sep 25, 2015 12:09:28 PM
 */

public class OyoSearch {

    private Gson gson;

    // oyo api url template
    private static final String OYO_API_URL = "http://utilities-oyorooms.herokuapp.com/api/v2/search/hotels?filters[coordinates][longitude]=@longitude@&filters[coordinates][latitude]=@latitude@&filters[coordinates][distance]=20&fields=name,longitude,latitude,oyo_id&additional_fields=room_pricing&access_token=MXB2cE44LWJGaTViWExHQ0xCOC06VUtucEhhVV9mclNNeWdrNFBveFY=";

    // google place api url template
    private static final String PLACE_API_URL = "https://maps.googleapis.com/maps/api/place/search/json?location=@location@&rankby=distance&sensor=true&type=@type@&key=AIzaSyCiUL3FVScMAT9pXvETbzMQqNcuek2C2WQ";

    public OyoSearch() {
        super();
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
    }

    public static class Oyo implements Comparable<Oyo> {

        private String oyo_id;

        private String name;

        private Double latitude;

        private Double longitude;

        private Double distance;

        private Double[] pricing;

        private Double weight;

        /**
         * 
         */
        public Oyo() {
            super();

        }

        public String getOyo_id() {
            return oyo_id;
        }

        public void setOyo_id(String oyo_id) {
            this.oyo_id = oyo_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Double[] getPricing() {
            return pricing;
        }

        public void setPricing(Double[] pricing) {
            this.pricing = pricing;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Oyo [oyo_id=" + oyo_id + ", name=" + name + ", weight="
                    + weight + "]";
        }

        @Override
        public int compareTo(Oyo o) {

            return Double.compare(o.weight, this.weight);
        }

    }

    private static class OyoResponse {

        private List<Oyo> hotels;

    }

    private static class Location {

        private Double lat;

        private Double lng;

    }

    private static class Geometry {

        private Location location;
    }

    private static class Place {

        private Geometry geometry;

    }

    private static class GoogleResponse {

        private List<Place> results;

    }

    private String jsonResponse(String request) throws IOException {
        URL url = new URL(request);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                url.openStream()));

        StringBuilder responseBuilder = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        reader.close();

        return responseBuilder.toString();
    }

    public List<Oyo> search(double latitude, double longitude)
            throws JsonSyntaxException, IOException {
        String oyoAPiReqeustURL = new String(OYO_API_URL);
        oyoAPiReqeustURL = oyoAPiReqeustURL.replace(
                "@latitude@", String.valueOf(latitude));
        oyoAPiReqeustURL = oyoAPiReqeustURL.replace(
                "@longitude@", String.valueOf(longitude));

        OyoResponse oyoResponse = gson.fromJson(
                jsonResponse(oyoAPiReqeustURL), OyoResponse.class);
        // calculate weight
        for (Oyo oyo : oyoResponse.hotels) {
            oyo.weight = weight(oyo);
        }

        // order by weight desc
        Collections.sort(oyoResponse.hotels);

        return oyoResponse.hotels;

    }

    public Place nearestPlaceOfType(double latitude,
                                    double longitude,
                                    String type) throws JsonSyntaxException,
            IOException {

        String googleAPiReqeustURL = new String(PLACE_API_URL);

        googleAPiReqeustURL = googleAPiReqeustURL.replace(
                "@location@", String.valueOf(latitude))
                + "," + String.valueOf(longitude);
        googleAPiReqeustURL = googleAPiReqeustURL.replace("@type@", type);

        GoogleResponse googleResponse = gson.fromJson(
                jsonResponse(googleAPiReqeustURL), GoogleResponse.class);

        Place place = null;
        if (googleResponse.results != null && !googleResponse.results.isEmpty()) {
            place = googleResponse.results.get(0);
        }

        return place;

    }

    private double weight(Oyo oyo) throws JsonSyntaxException, IOException {

        Place nearestAriport = nearestPlaceOfType(
                oyo.latitude, oyo.longitude, "airport");

        Place nearestHospital = nearestPlaceOfType(
                oyo.latitude, oyo.longitude, "hospital");

        double distanceOfNearestAirport = 4;

        if (nearestAriport != null) {
            distanceOfNearestAirport = distance(
                    oyo.latitude, oyo.longitude,
                    nearestAriport.geometry.location.lat,
                    nearestAriport.geometry.location.lng);

        }

        double distanceOfNearestHospital = 4;

        if (nearestHospital != null) {
            distanceOfNearestHospital = distance(
                    oyo.latitude, oyo.longitude,
                    nearestHospital.geometry.location.lat,
                    nearestHospital.geometry.location.lng);
        }

        double priceWeight = 0;

        double priceSum = 0;
        for (Double price : oyo.pricing) {
            priceSum = priceSum + price;
        }

        // average price weight
        priceWeight = 20 * ((4000 - (priceSum / oyo.pricing.length)) / 4000);

        // negative weight is 0
        priceWeight = priceWeight < 0 ? 0 : priceWeight;

        double distanceWeight = 20 * ((3 - oyo.distance) / 3);

        distanceWeight = distanceWeight < 0 ? 0 : distanceWeight;

        double airportWeight = 30 * ((3 - distanceOfNearestAirport) / 3);

        airportWeight = airportWeight < 0 ? 0 : airportWeight;

        double hospitalWeight = 30 * ((3 - distanceOfNearestHospital) / 3);

        hospitalWeight = hospitalWeight < 0 ? 0 : hospitalWeight;

        // total weight
        return (priceWeight + distanceWeight + airportWeight + hospitalWeight);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        // Km
        dist = dist * 1.609344;

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public static void main(String[] args) throws JsonSyntaxException,
            IOException {

        if (args.length < 2) {
            System.out.println("latitude and longitude arguments missing");
        }

        OyoSearch search = new OyoSearch();

        for (Oyo oyo : search.search(
                Double.parseDouble(args[0]), Double.parseDouble(args[1]))) {
            System.out.println(oyo);
        }
        // 12.9509468, 77.6416611)

    }
}
