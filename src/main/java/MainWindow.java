import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainWindow {
    private JTextField keyTextField;
    private JTextArea filePathtextArea;
    private JButton submitButton;
    private JPanel mainPanel;

    public MainWindow(){

        submitButton.addActionListener(click -> {
            String key =keyTextField.getText();;
            String filePath = filePathtextArea.getText();

            try {
            File inputFile = new File(filePath);
            File decryptedFile = new File("document.decrypted");
            CryptoUtils.decrypt(key, inputFile, decryptedFile);
            } catch (CryptoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
            File file = new File("document.decrypted");
            StringBuffer stringBuffer = new StringBuffer();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String st;
                while ((st = br.readLine()) != null)
                    stringBuffer.append(st);
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
        }
            JOptionPane.showMessageDialog(null, "The plain text is: "+ stringBuffer.toString());
        });

           }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("MainWindow");
        jFrame.setContentPane(new MainWindow().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}