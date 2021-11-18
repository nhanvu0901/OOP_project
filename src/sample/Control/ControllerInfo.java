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
import java.util.ResourceBundle;

public class ControllerInfo implements Initializable {
    @FXML
    private Button btnPartTimeTable,btnHome,btnFullTimetable,btnSignout,btnInfo;

    @FXML
    private Label Name, textName,textYear,textSpecialty,textPhone,textSalary,textHour,textCoeffient,labelHour,labelCoefficient,textKind;



    private Stage stage;
    private Scene scene;
    private Parent root;
    private double x, y;
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnPartTimeTable) {
            URL url = new File("src/sample/Scene/FXsceneTabPartTime.fxml").toURI().toURL();
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

        }
        if (actionEvent.getSource() == btnHome) {
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
        }
        if (actionEvent.getSource() == btnFullTimetable) {
            URL url = new File("src/sample/Scene/FXsceneTabFullTime.fxml").toURI().toURL();
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
        }

        if (actionEvent.getSource() == btnSignout) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm data");
            alert.setContentText("Bạn có muốn quay lại trang đăng nhập");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);

            ButtonType cancelButton = new ButtonType("Quay lại", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    URL url = null;
                    try {
                        url = new File("src/sample/Scene/FXsceneLogin.fxml").toURI().toURL();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        root = FXMLLoader.load(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                } else{
                    alert.close();
                }
            });

        }
        if (actionEvent.getSource() == btnInfo) {
            URL url = new File("src/sample/Scene/FXsceneInfo.fxml").toURI().toURL();
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
        }

    }
    @FXML
    private void Close(MouseEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teacher teacher;
        teacher = ControllerLogin.TransferData();
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
        Name.setText(teacher.getName());
    }
}
