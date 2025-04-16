import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class call1 {
    public static void main(String[] args) {
        if (isSubmitButtonPressed()) {
            String textMessage = getUserMessage();
            String mobileNumber = getUserMobile();
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
                URL url = new URL("https://api.textlocal.in/send/?");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

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

                // Process your response here
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response (not shown here)
                    System.out.println("Message sent successfully.");
                } else {
                    System.out.println("Failed to send message. Response code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isSubmitButtonPressed() {
        // Implement logic to check if the submit button is pressed
        return true; // Placeholder
    }

    private static String getUserMessage() {
        // Implement logic to get user message
        return "Your message here"; // Placeholder
    }

    private static String getUserMobile() {
        // Implement logic to get user mobile number
        return "9702689882"; // Placeholder
    }
}

