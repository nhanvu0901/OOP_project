package sample.style;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import javax.swing.text.LabelView;
import java.awt.datatransfer.FlavorEvent;

public class Style {
    public static void success(Label infrom,String message){
        infrom.setVisible(true);
        infrom.setText(message);
        infrom.setStyle("-fx-text-fill:green");
        setTimeout(() -> infrom.setVisible(false), 5000);
    }
    public static void setDanger(TextField text ,Label label,Label inform){
        text.setStyle("-fx-border-color:red");
        label.setStyle("-fx-text-fill: red");
         inform.setVisible(true);
        setTimeout(() -> removeDanger(text,label,inform), 2500);
    }


    public static void setInform(Label inform){
        inform.setVisible(true);
        setTimeout(() -> inform.setVisible(false), 5000);
    }
    public static void removeDanger(TextField textField ,Label label ,Label inform) {
        textField.setStyle("-fx-border-color:none");

        label.setStyle("-fx-text-fill: white");
        inform.setVisible(false);
    }
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
