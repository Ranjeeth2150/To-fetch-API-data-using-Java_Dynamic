package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try
        {
            String apis="https://api.zippopotam.us/us/33162";
            URL url=new URL(apis);

            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode=connection.getResponseCode();
            if(responseCode == 200)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder apiData = new StringBuilder();
                String readLine = null;

                while((readLine = in.readLine())!= null)
                {
                    apiData.append(readLine);
                }

                //
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(apiData.toString());
                JSONObject jsonAPIResponse = new JSONObject(apiData.toString());

                System.out.println(jsonAPIResponse.get("post code"));
                System.out.println(jsonAPIResponse.get("country"));
                System.out.println(jsonAPIResponse.get("country abbreviation"));
               // System.out.println(jsonAPIResponse.get("places"));

                JSONArray placesArray = jsonAPIResponse.getJSONArray("places");

                    JSONObject placeObject = placesArray.getJSONObject(0);
                    System.out.println(placeObject.get("place name"));
                    System.out.println(placeObject.get("longitude"));
                    System.out.println(placeObject.get("state"));
                    System.out.println(placeObject.get("state abbreviation"));
                    System.out.println(placeObject.get("latitude"));


            }
            else
            {
                System.out.println("Api request failed with response code "+responseCode);
            }
            connection.disconnect();


        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

}