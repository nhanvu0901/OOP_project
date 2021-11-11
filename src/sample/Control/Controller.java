package sample.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.control.TextField;
import  javafx.scene.control.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;


import Database.DBConnection;
import sample.style.Style;

public class Controller {
        private Stage stage;
        private Scene scene;
        private Parent root;


        @FXML
        private TextField  name , phone;

        @FXML
        private Label infrom;
        @FXML
        private Label nameLabel;
        @FXML
        private Label phoneLabel;



    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        String Name = name.getText();
        String Phone = phone.getText();
        if(Name == "" && Phone ==""){
            Style.setDanger(name,nameLabel,infrom);
            Style.setDanger(phone,phoneLabel,infrom);
            infrom.setText("Không được để trống dòng");
        }
        else if(Name == ""){
            Style.setDanger(name,nameLabel,infrom);
            infrom.setText("Không được để tên trống dòng");
        }
        else if(Phone == ""){
            Style.setDanger(phone,phoneLabel,infrom);
            infrom.setText("Không được để dòng số điện thoại trống dòng");
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
                    stage.show();
            } else {
                Style.setInform(infrom);
                infrom.setText("Tên đăng nhập hoặc mật khẩu không đúng");
            }





        }
    }




}
