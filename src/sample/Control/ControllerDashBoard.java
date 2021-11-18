package sample.Control;
import Database.DBConnection;
import Model.Teacher;
import Model.fullTimeTeacher;
import Modify.fullTimeModify;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Teacher;
import Modify.teacherModify;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.style.Style;
import sample.Control.ControllerLogin;

import javax.lang.model.element.Name;

public class ControllerDashBoard implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private double x, y;
    Integer index = -1;

    //Button
    @FXML
    private Button btnPartTimeTable,btnHome,btnFullTimetable,btnSignout,btnInfo;

    //Pane

    @FXML
    private Pane pnlTable;



    @FXML
    private TextField  searchText;
    @FXML
    private ImageView reload;
    @FXML
    private Label inform ,totalNum,totalNumFullTime,totalNumPartTime;

    @FXML
    private TableView<Teacher> table;

    @FXML
    private TableColumn<Teacher, String> col_name;
    @FXML
    private TableColumn<Teacher, Integer> col_teachAtSchoolYear;
    @FXML
    private TableColumn<Teacher, Integer> col_id;
    @FXML
    private TableColumn<Teacher, String> col_specialty;
    @FXML
    private TableColumn<Teacher, String> col_phone;
    @FXML
    private TableColumn<Teacher, Double> col_salary;
    @FXML
    private ChoiceBox<String> choiceBox;

    ObservableList<String> options = FXCollections.observableArrayList("Tên giáo viên","Đơn vị chuyên môn","Số điện thoại","Tìm kiếm lương lớn hơn số lương nhập vào");

    ObservableList<Teacher> data;
    public void textChange(){
        String text = searchText.getText();

        switch (choiceBox.getValue())//Switch on choiceBox value
        {
            case "Tên giáo viên":
                data = teacherModify.findByName(text.trim());
                table.setItems(data);
                break;
            case "Đơn vị chuyên môn":
                 data = teacherModify.findBySpecialty(text.trim());
                 table.setItems(data);
                 break;
            case "Số điện thoại":
                data = teacherModify.findByPhone(text.trim());
                table.setItems(data);
                break;
            case "Tìm kiếm lương lớn hơn số lương nhập vào":
                data = teacherModify.findBySalary(Double.parseDouble(text.trim()));
                table.setItems(data);
                break;

        }


    }


    public  void UpdateTable() {
        ObservableList<Teacher> listM = teacherModify.findAll();
        col_name.setCellValueFactory(new PropertyValueFactory<Teacher,String>("name"));
        col_teachAtSchoolYear.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("teachAtSchoolYear"));
        col_id.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("id"));
        col_specialty.setCellValueFactory(new PropertyValueFactory<Teacher,String>("specialty"));
        col_phone.setCellValueFactory(new PropertyValueFactory<Teacher,String>("phoneNumber"));
        col_salary.setCellValueFactory(new PropertyValueFactory<Teacher,Double>("salary"));


        table.setItems(listM);
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
        choiceBox.setItems(options);
        choiceBox.setValue("Tìm kiếm");
        int total = teacherModify.findTotalTeacher();
        totalNum.setText(String.valueOf(total));
        int totalFullTime = teacherModify.findTotalFullTimeTeacher();
        totalNumFullTime.setText(String.valueOf(totalFullTime));
        int totalPartTime = teacherModify.findTotalPartTimeTeacher();
        totalNumPartTime.setText(String.valueOf(totalPartTime));
    }
    public void reloadPage(){
        UpdateTable();
    }

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

}
