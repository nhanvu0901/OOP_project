package sample.Control;

import Database.DBConnection;
import Model.Teacher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Model.fullTimeTeacher;
import Modify.fullTimeModify;



import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.style.Style;


public class ControllerFullTime implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Double x,y;
    Integer index = -1;

    ObservableList<fullTimeTeacher> listM;

    fullTimeModify model = new fullTimeModify();

    URL image = ControllerLogin.class.getClassLoader().getResource("asset/verified.png");
    Image imgSuccess = new Image(String.valueOf(image));

    //Button
    @FXML
    private Button btnPartTimeTable,btnHome,btnFullTimetable,btnSignout,btnInfo;

    @FXML
    private TextField  nameText,teachAtSchoolYearText,specialtyText,phoneText,coefficientText,salaryText;

    @FXML
    private Label Name,inform,nameLabel,teachAtSchoolYearLabel,specialtyLabel,phoneLabel,coefficientLabel;
    @FXML
    private TableView<fullTimeTeacher> table1;

    @FXML
    private TableColumn<fullTimeTeacher, String> col_name;
    @FXML
    private TableColumn<fullTimeTeacher, Integer> col_teachAtSchoolYear;
    @FXML
    private TableColumn<fullTimeTeacher, Integer> col_id;
    @FXML
    private TableColumn<fullTimeTeacher, String> col_specialty;
    @FXML
    private TableColumn<fullTimeTeacher, String> col_phone;
    @FXML
    private TableColumn<fullTimeTeacher, Double> col_salary;
    @FXML
    private TableColumn<fullTimeTeacher, Double> col_coefficient;

    int idProp ;
    Teacher teacherProp ;



    @FXML
    void getSelected(MouseEvent event){
        index = table1.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        nameText.setText(col_name.getCellData(index));
        teachAtSchoolYearText.setText(col_teachAtSchoolYear.getCellData(index).toString());
        specialtyText.setText(col_specialty.getCellData(index));
        phoneText.setText(col_phone.getCellData(index));
        coefficientText.setText(col_coefficient.getCellData(index).toString());
        salaryText.setText(col_salary.getCellData(index).toString());
        idProp = Integer.parseInt(col_id.getCellData(index).toString());


        String name = col_name.getCellData(index);
        int year = Integer.parseInt(col_teachAtSchoolYear.getCellData(index).toString());
        String specialty = col_specialty.getCellData(index);
        String phone = col_phone.getCellData(index);

        teacherProp = new  Teacher(idProp,name,year,specialty,phone);
        int id = fullTimeModify.findAllTeacher(teacherProp);
        System.out.println(id);
    }
    public void UpdateTable(){

        col_name.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,String>("name"));
        col_teachAtSchoolYear.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,Integer>("teachAtSchoolYear"));
        col_id.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,Integer>("id"));
        col_specialty.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,String>("specialty"));
        col_phone.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,String>("phoneNumber"));
        col_salary.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,Double>("salary"));
        col_coefficient.setCellValueFactory(new PropertyValueFactory<fullTimeTeacher,Double>("coefficient"));
        listM = model.findAll();
        table1.setItems(listM);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        UpdateTable();

    }

    public void addUser(javafx.event.ActionEvent actionEvent) throws IOException{
        String name = nameText.getText();
        String schoolYearTxt = teachAtSchoolYearText.getText();
        String specialty = specialtyText.getText();
        String phone = phoneText.getText();
        String coefficentTxt = coefficientText.getText();


        if(name == "" && schoolYearTxt =="" && specialty=="" && phone==""&&coefficentTxt==""){
            Style.setDanger(nameText,nameLabel,inform);
            Style.setDanger(teachAtSchoolYearText,teachAtSchoolYearLabel,inform);
            Style.setDanger(specialtyText,specialtyLabel,inform);
            Style.setDanger(phoneText,phoneLabel,inform);
            Style.setDanger(coefficientText,coefficientLabel,inform);

            inform.setText("Kh??ng ???????c ????? tr???ng d??ng");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }


        else if(name == ""){
            Style.setDanger(nameText,nameLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng t??n");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(schoolYearTxt == ""){
            Style.setDanger(teachAtSchoolYearText,teachAtSchoolYearLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng n??m nh???p tr?????ng");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(specialty == ""){
            Style.setDanger(specialtyText,specialtyLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng chuy??n m??n");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(phone == ""){
            Style.setDanger(phoneText,phoneLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng s??? ??i???n tho???i");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(coefficentTxt == ""){
            Style.setDanger(coefficientText,coefficientLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng t??? l??? ngh???ch");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }



        else {
            try {
                Double coefficient = Double.parseDouble(coefficientText.getText());
                int teachAtSchoolYear = Integer.parseInt(teachAtSchoolYearText.getText());

                Connection con = DBConnection.getConnection();


                fullTimeTeacher teacher = new fullTimeTeacher(name, teachAtSchoolYear, specialty, phone, coefficient);
                fullTimeModify modify = new fullTimeModify();

                PreparedStatement ps =
                        con.prepareStatement
                                ("SELECT  teacher_phone FROM full_time_teacher  WHERE teacher_phone = ?");
                ps.setString(1, phone);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Style.setInform(inform);
                    inform.setText("S??? ??i???n tho???i ???? ???????c nh???p vui l??ng nh???p th??ng tin kh??c !");
                    clearText();
                } else {

                    modify.insert(teacher);
                    fullTimeModify.insertTeacher(teacher);
                    Style.success(inform, "Insert database th??nh c??ng!");
                    double luong = teacher.getLuong();
                    salaryText.setText(Double.toString(luong));

                    Notifications notifications = Notifications.create()
                            .title("Th??nh c??ng")
                            .text("B???n ???? th??m data th??nh c??ng")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.TOP_CENTER)
                            .graphic(new ImageView(imgSuccess));
                    notifications.darkStyle();
                    notifications.show();

                    UpdateTable();
                    clearText();
                }

            } catch (Exception e) {

                System.out.println(e);
            }
        }
    }
    public void editUser(ActionEvent actionEvent) throws IOException {
        String name = nameText.getText();
        String schoolYearTxt = teachAtSchoolYearText.getText();
        String specialty = specialtyText.getText();
        String phone = phoneText.getText();
        String coefficentTxt = coefficientText.getText();

        if(name == "" && schoolYearTxt =="" && specialty=="" && phone==""&& coefficentTxt==""){
            Style.setDanger(nameText,nameLabel,inform);
            Style.setDanger(teachAtSchoolYearText,teachAtSchoolYearLabel,inform);
            Style.setDanger(specialtyText,specialtyLabel,inform);
            Style.setDanger(phoneText,phoneLabel,inform);
            Style.setDanger(coefficientText,coefficientLabel,inform);

            inform.setText("Kh??ng ???????c ????? tr???ng d??ng");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }


        else if(name == ""){
            Style.setDanger(nameText,nameLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng t??n");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(schoolYearTxt == ""){
            Style.setDanger(teachAtSchoolYearText,teachAtSchoolYearLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng n??m nh???p tr?????ng");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(specialty == ""){
            Style.setDanger(specialtyText,specialtyLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng chuy??n m??n");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(phone == ""){
            Style.setDanger(phoneText,phoneLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng s??? ??i???n tho???i");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else if(coefficentTxt == ""){
            Style.setDanger(coefficientText,coefficientLabel,inform);
            inform.setText("Kh??ng ???????c ????? tr???ng d??ng t??? l??? ngh???ch");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        else{
            try {

                Connection con = DBConnection.getConnection();
                Double coefficient = Double.parseDouble(coefficientText.getText());
                int teachAtSchoolYear = Integer.parseInt(teachAtSchoolYearText.getText());

                int id = fullTimeModify.findAllTeacher(teacherProp);


                fullTimeTeacher teacher = new fullTimeTeacher(idProp,name, teachAtSchoolYear, specialty, phone, coefficient);
                fullTimeTeacher teacher1 = new fullTimeTeacher(id,name, teachAtSchoolYear, specialty, phone, coefficient);


                fullTimeModify.update(teacher);
                fullTimeModify.updateTeacher(teacher1);
                Style.success(inform,"Update database th??nh c??ng");

                Notifications notifications = Notifications.create()
                        .title("Th??nh c??ng")
                        .text("B???n ???? s???a th??ng tin th??nh c??ng")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_CENTER)
                        .graphic(new ImageView(imgSuccess));
                notifications.darkStyle();
                notifications.show();

                UpdateTable();
                clearText();

            } catch(Exception e){
                System.out.println(e);
            }
        }

    }
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        String name = nameText.getText();
        String schoolYearTxt = teachAtSchoolYearText.getText();
        String specialty = specialtyText.getText();
        String phone = phoneText.getText();
        String coefficentTxt = coefficientText.getText();

        if(name == "" && schoolYearTxt =="" && specialty=="" && phone==""&&coefficentTxt==""){
            Style.setDanger(nameText,nameLabel,inform);
            Style.setDanger(teachAtSchoolYearText,teachAtSchoolYearLabel,inform);
            Style.setDanger(specialtyText,specialtyLabel,inform);
            Style.setDanger(phoneText,phoneLabel,inform);
            Style.setDanger(coefficientText,coefficientLabel,inform);

            inform.setText("Ch???n h??ng ??? b???ng ????? x??a gi??o vi??n ");
            inform.setVisible(true);
            inform.setStyle("-fx-text-fill:red");
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm data");
        alert.setContentText("B???n c?? mu???n x??a d??? li???u c???a ng?????i n??y ? (d??? li???u s??? b??? x??a m??i m??i)");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);

        ButtonType cancelButton = new ButtonType("Quay l???i", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                int id = fullTimeModify.findAllTeacher(teacherProp);
                fullTimeModify.delete(idProp);
                fullTimeModify.deleteTeacher(id);
                UpdateTable();
                clearText();
                Style.success(inform,"X??a d??? li???u gi??o vi??n th??nh c??ng");
            } else{
                alert.close();
            }
        });
    }
    public void clearText(){
        nameText.setText("");
        teachAtSchoolYearText.setText("");
        specialtyText.setText("");
        phoneText.setText("");
        coefficientText.setText("");
        salaryText.setText("");
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
            alert.setContentText("B???n c?? mu???n quay l???i trang ????ng nh???p");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);

            ButtonType cancelButton = new ButtonType("Quay l???i", ButtonBar.ButtonData.CANCEL_CLOSE);
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
