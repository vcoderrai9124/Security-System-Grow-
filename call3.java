import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class call3 {
    public static void main(String[] args) {
        // Assuming these values are coming from a form submission
        String textMessage = "Your message here"; // Replace with actual message
        String mobileNumber = "9702689882"; // Replace with actual mobile number
        String apiKey = "NzI3MTc0Nzc1YTRlNTc3MDc4NGY0NTc5NmU0MjZmMzk=";

        try {
            // Message details
            String sender = URLEncoder.encode("TXTLCL", StandardCharsets.UTF_8.toString());
            String message = URLEncoder.encode(textMessage, StandardCharsets.UTF_8.toString());
            String numbers = URLEncoder.encode(mobileNumber, StandardCharsets.UTF_8.toString());

            // Prepare data for POST request
            Map<String, String> data = new HashMap<>();
            data.put("apikey", apiKey);
            data.put("numbers", numbers);
            data.put("sender", sender);
            data.put("message", message);

            // Send the POST request
            @SuppressWarnings("deprecation")
            URL url = new URL("https://api.textlocal.in/send/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Write data to the request body
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
                postData.append('=');
                postData.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            }

            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData.toString().getBytes(StandardCharsets.UTF_8));
            }

            // Get the response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Process your response here
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Failed to send message. Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

