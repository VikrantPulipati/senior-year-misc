import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DestinyAPIDemo {
	public DestinyAPIDemo () {
		String apiKey = "YOUR-API-KEY-HERE";

		 // Endpoint for Gjallarhorn
		 String url = "https://www.bungie.net/platform/Destiny/Manifest/InventoryItem/1274330687/";

		 URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		 try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 // Set header
		 con.setRequestProperty("X-API-KEY", apiKey);

		 int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("\nSending 'GET' request to Bungie.Net : " + url);
		 System.out.println("Response Code : " + responseCode);

		 BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 String inputLine;
		 String response = "";

		 try {
			while ((inputLine = in.readLine()) != null) {
			     response += inputLine;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 System.out.println(response);
		 //Gjallarhorn
		
	}
	
	public static void main (String[] args) {
		DestinyAPIDemo app = new DestinyAPIDemo();
	}
}
