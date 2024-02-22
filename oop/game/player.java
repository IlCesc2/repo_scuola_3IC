package oop.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;


public class player {
    int gamesWon = 0;
    int desiredLength = 5;
    String nome = ""; 
    
    public static String fetchApi(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }
    public player() throws JSONException, Exception {
        //https://name-fake.com/it_IT
        JSONObject jsonObject = new JSONObject(fetchApi("https://api.namefake.com/it_IT"));
        
        this.nome = jsonObject.getString("name");

    }
    

}
