package sample.Control;

import Model.Teacher;
import Model.fullTimeTeacher;
import Model.partTimeTeacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Control.ControllerLogin;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerInfo implements Initializable {
    private static Teacher teacher;
    @FXML
    private Button btnPartTimeTable,btnHome,btnFullTimetable,btnSignout,btnInfo;

    @FXML
    private Label  textName,textYear,textSpecialty,textPhone,textSalary,textHour,textCoeffient,labelHour,labelCoefficient,textKind;



    private Stage stage;
    private Scene scene;
    private Parent root;
    private double x, y;


    @FXML
    private void Close(MouseEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ControllerLogin data = new ControllerLogin();
        try {
            teacher = data.TransferData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        textName.setText(teacher.getName());
        textYear.setText(String.valueOf(teacher.getTeachAtSchoolYear()));
        textSpecialty.setText(teacher.getSpecialty());
        textPhone.setText(teacher.getPhoneNumber());
        textSalary.setText(String.valueOf(teacher.getSalary()));
        textKind.setText(teacher.GetKind());
        if(teacher.GetKind() == "Full Time"){
            fullTimeTeacher teacherProp = (fullTimeTeacher) teacher;
            textCoeffient.setVisible(true);
            textCoeffient.setText(String.valueOf(teacherProp.getCoefficient()));
            labelCoefficient.setVisible(true);

        }
        if(teacher.GetKind() =="Part Time"){
            partTimeTeacher teacherProp = (partTimeTeacher) teacher;
            textHour.setVisible(true);
            textHour.setText(String.valueOf(teacherProp.getTeachingHour()));
            labelHour.setVisible(true);
        }

    }
}
