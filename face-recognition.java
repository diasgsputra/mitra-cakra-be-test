import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class SimpleFaceRecognitionClient {
    public static void main(String[] args) {
        // Gambar dari directory
        String imagePath = "face_image.jpg";
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            System.out.println("Gambar tidak ditemukan: " + imagePath);
            return;
        }

        // Kirim gambar ke API
        boolean success = sendToApi(imageFile);
        if (success) {
            System.out.println("Gambar berhasil dikirim ke API!");
        } else {
            System.out.println("Gagal mengirim gambar ke API.");
        }
    }

    public static boolean sendToApi(File imageFile) {
        String apiEndpoint = "http://example-api-endpoint.com/face-recognition";
        try {
            URL url = new URL(apiEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "image/jpeg");

            // Mengirim file gambar ke API
            try (OutputStream outputStream = connection.getOutputStream()) {
                Files.copy(imageFile.toPath(), outputStream);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response dari API: " + responseCode);
            return responseCode == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
