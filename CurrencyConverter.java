import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject; // Make sure you have org.json library

public class CurrencyConverter {

    // Function to get exchange rate
    public static double getExchangeRate(String base, String target) {
        double rate = 0.0;
        try {
            String urlStr = "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + target;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject obj = new JSONObject(response.toString());
            rate = obj.getJSONObject("rates").getDouble(target);

        } catch (Exception e) {
            System.out.println("⚠️ Error fetching exchange rate: " + e.getMessage());
        }
        return rate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("💱 Currency Converter");

        System.out.print("Enter base currency (e.g., USD, INR, EUR): ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., USD, INR, EUR): ");
        String target = scanner.next().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(base, target);
        if (rate != 0.0) {
            double convertedAmount = amount * rate;
            System.out.printf("\n✅ %.2f %s = %.2f %s\n", amount, base, convertedAmount, target);
        } else {
            System.out.println("⚠️ Unable to get exchange rate. Please check your currency codes.");
        }

        scanner.close();
    }
}
