package cl.cokke;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class main {

	public static void main(String[] args) throws Exception {
		
		//URL url= new URL("https://rickandmortyapi.com/api/character");
		URL url= new URL("https://jsonplaceholder.typicode.com/users");
        HttpURLConnection cx = (HttpURLConnection) url.openConnection();
        cx.setRequestMethod("GET");
        
        InputStream strm= cx.getInputStream();
        byte[] arrStream = strm.readAllBytes();
		String cntJson="";
		for(byte tmp: arrStream)
			cntJson+=(char)tmp;
		
		JSONArray json = new JSONArray(cntJson);
		
		for(Object obj: json)
			System.out.println(((JSONObject)obj).get("name").toString());
		

	}

}
