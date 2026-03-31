package paymentprocessing;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;

public class QRCode {

    public static void generateQR(String data, String filePath) {
        try {
            int width = 300;
            int height = 300;

            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix matrix = new MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    width,
                    height,
                    hints
            );

            MatrixToImageWriter.writeToPath(
                    matrix,
                    "PNG",
                    java.nio.file.Path.of(filePath)
            );

            System.out.println("QR Code generated: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readQR(String filePath) {
        try {
            var bufferedImage = ImageIO.read(new File(filePath));
            var source = new BufferedImageLuminanceSource(bufferedImage);
            var bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            Result result = new MultiFormatReader().decode(bitmap, hints);

            return result.getText();

        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}