import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


public class guiv2 {

    public static String FILEPATH;
    public static String SavedContent;
    public static String filename;
    public static String content;

    public static Font font1 = new Font("SansSerif", Font.PLAIN,15);

    public static void Draw(JFrame frame,TextArea textField, JButton buttonSave, JButton buttonOpen, String content)
    {
        textField.setBounds(50,50,frame.getWidth() - 100 ,frame.getHeight() -100);
        buttonOpen.setBounds(50,25,100,25);
        buttonSave.setBounds(150,25,100,25);
    }

    public static void main(String[] args)
    {


        JFrame frame = new JFrame("Not Defteri");
        frame.setSize(600,600);

        TextArea textField = new TextArea();
        JButton buttonSave = new JButton();
        JButton buttonOpen = new JButton();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        buttonOpen.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);

            if(result == JFileChooser.APPROVE_OPTION)
            {
                File selectedfile = fileChooser.getSelectedFile();
                filename = selectedfile.getName();
                buttonOpen.setText(filename);
                FILEPATH = selectedfile.getAbsolutePath();
                Path filepath = Path.of(FILEPATH);

                try {
                    content = Files.readString(filepath);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                textField.setText(content);
            }
            else
            {
                buttonOpen.setText("Aç");
            }
        });


        buttonSave.addActionListener(e -> {
            SavedContent = textField.getText();
            Path realfilepath = Path.of(FILEPATH);

            try {
                Files.writeString(realfilepath,textField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            buttonSave.setText("Kaydedildi!");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            buttonSave.setText("Kaydet");
        });


        frame.add(textField);
        frame.add(buttonOpen);
        frame.add(buttonSave);

        frame.setLayout(null);
        frame.setVisible(true);

        textField.setBackground(Color.lightGray);
        textField.setFont(font1);
        buttonOpen.setText("Aç");
        buttonSave.setText("Kaydet");




        while(true){Draw(frame,textField,buttonSave,buttonOpen,content);}
    }

}
