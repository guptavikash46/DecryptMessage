import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CryptoUtilsTest {
    public static void main(String[] args) throws IOException {
        String key = "Mary has one cat";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path accurately:");
        String filePath = scanner.nextLine();
        File inputFile = new File(filePath);

        File decryptedFile = new File("document.decrypted");

        try {
            CryptoUtils.decrypt(key, inputFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        File file = new File("document.decrypted");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }

}