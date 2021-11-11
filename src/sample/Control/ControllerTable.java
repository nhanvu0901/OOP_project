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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

import Model.Teacher;
import Modify.teacherModify;
import javafx.stage.Stage;
import sample.style.Style;
public class ControllerTable implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    Integer index = -1;




    @FXML
    private TextField  searchText;
    @FXML
    private ImageView reload;
    @FXML
    private Label inform;

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
    }
    public void reloadPage(){
        UpdateTable();
    }
}
