import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainWindow {
    private JTextField keyTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JButton selectFileButton;
    private JFileChooser jFileChooser;
    static JFrame jFrame = new JFrame("MainWindow");
    private File inputFile;

    public MainWindow(){

        //code to select the file from the path - 21st april 2019
        jFileChooser = new JFileChooser("/home/vikas/IdeaProjects/EncryptMessage");
        selectFileButton.addActionListener(click -> {
            int returnVal = jFileChooser.showOpenDialog(jFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    inputFile = jFileChooser.getSelectedFile();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"problem accessing file"+inputFile.getAbsolutePath());
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"File access cancelled by user.");
            }
        });

        //decryption on button click - 17th april 2019
        submitButton.addActionListener(click -> {
            String key =keyTextField.getText();
            try {
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
        jFrame.setContentPane(new MainWindow().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(600, 500));
        jFrame.pack();
        jFrame.setVisible(true);

    }


}