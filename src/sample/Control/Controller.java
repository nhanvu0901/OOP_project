package sample.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;


import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import  javafx.scene.control.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;


import Database.DBConnection;
import javafx.util.Duration;
import sample.style.Style;

public class Controller {
        private Stage stage;
        private Scene scene;
        private Parent root;
        private double x, y;

        @FXML
        private TextField  name , phone;

        @FXML
        private Label infrom;
        @FXML
        private Label nameLabel;
        @FXML
        private Label phoneLabel;
        @FXML
        private ImageView closeButton;


    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        String Name = name.getText();
        String Phone = phone.getText();
        URL image = Controller.class.getClassLoader().getResource("asset/delete.png");
        Image img = new Image(String.valueOf(image));
        if(Name == "" && Phone ==""){
            Style.setDanger(name,nameLabel,infrom);
            Style.setDanger(phone,phoneLabel,infrom);
            infrom.setText("Không được để trống dòng");

            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Tên và số điện thoại không được để trống")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .graphic(new ImageView(img));
            notifications.darkStyle();
            notifications.show();
        }
        else if(Name == ""){
            Style.setDanger(name,nameLabel,infrom);
            infrom.setText("Không được để tên trống dòng");
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Tên không được để trống")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .graphic(new ImageView(img));
            notifications.darkStyle();
            notifications.show();
        }
        else if(Phone == ""){
            Style.setDanger(phone,phoneLabel,infrom);
            infrom.setText("Không được để dòng số điện thoại trống dòng");
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Số điện thoại không được để trống")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .graphic(new ImageView(img));
            notifications.darkStyle();
            notifications.show();
        }
        else{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement
                            ("SELECT  teacher_name FROM teacher  WHERE teacher_name =? AND teacher_phone=? ");
            ps.setString(1, Name);
            ps.setString(2, Phone);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // check if the user_name and pass exist
               infrom.setVisible(true);
                infrom.setText("Đăng nhập thành công");
                infrom.setStyle("-fx-text-fill:green");

                    URL url = new File("src/sample/Scene/FXsceneMain.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);

                    root.setOnMousePressed(event -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    root.setOnMouseDragged(event -> {

                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);

                    });


                    stage.show();
            } else {
                Style.setInform(infrom);
                infrom.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                Notifications notifications = Notifications.create()
                        .title("Error")
                        .text("Tên đăng nhập hoặc mật khẩu không đúng")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_CENTER)
                        .graphic(new ImageView(img));
                notifications.darkStyle();
                notifications.show();
            }
        }
    }

    @FXML
    private void Close(MouseEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.hide();
    }


}
