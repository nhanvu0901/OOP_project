package sample.Control;

import Model.fullTimeTeacher;
import Model.partTimeTeacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;


import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import  javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import Model.Teacher;
import Database.DBConnection;
import javafx.util.Duration;
import sample.style.Style;

public class ControllerLogin {
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
        private static Teacher teacherProp;


    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        String Name = name.getText();
        String Phone = phone.getText();
        URL image = ControllerLogin.class.getClassLoader().getResource("asset/delete.png");
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
                            ("SELECT * FROM teacher  WHERE teacher_name =? AND teacher_phone=? ");
            ps.setString(1, Name);
            ps.setString(2, Phone);


            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // check if the user_name and pass exist
               infrom.setVisible(true);
                infrom.setText("Đăng nhập thành công");
                infrom.setStyle("-fx-text-fill:green");

                    URL url = new File("src/sample/Scene/DashBoard.fxml").toURI().toURL();
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

                PreparedStatement ps1 =
                        con.prepareStatement
                                ("SELECT * FROM full_time_teacher  WHERE teacher_name =? AND teacher_phone=? ");
                ps1.setString(1, Name);
                ps1.setString(2, Phone);

                PreparedStatement ps2 =
                        con.prepareStatement
                                ("SELECT * FROM part_time_teacher  WHERE teacher_name =? AND teacher_phone=? ");
                ps2.setString(1, Name);
                ps2.setString(2, Phone);

                //truy van mysql de lay du lieu cua giao vien de hien thi thong tin nguoi dang nhap


                ResultSet rs1 = ps1.executeQuery();
                ResultSet rs2 = ps2.executeQuery();

                if(rs1.next()){ // nguoi dang nhap la full_time_teacher
                    teacherProp = new fullTimeTeacher(rs1.getInt("teacher_id"),rs1.getString("teacher_name"),rs1.getInt("teacher_year_at_school"),rs1.getString("teacher_specialty"),rs1.getString("teacher_phone"),rs1.getDouble("teacher_salary"),rs1.getDouble("teacher_coefficient"));
                }
                if(rs2.next()){
                    teacherProp = new partTimeTeacher(rs2.getInt("teacher_id"),rs2.getString("teacher_name"),rs2.getInt("teacher_year_at_school"),rs2.getString("teacher_specialty"),rs2.getString("teacher_phone"),rs2.getDouble("teacher_salary"),rs2.getDouble("teacher_hour"));
                }

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
    public static Teacher TransferData(){
         return teacherProp;
    }




}
