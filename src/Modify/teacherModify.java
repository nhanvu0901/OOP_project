package Modify;

import Database.DBConnection;
import Model.Teacher;
import Model.fullTimeTeacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class teacherModify {
    public static ObservableList<Teacher> findAll() { // lay du lieu tu database ve cho bang cua minh
        ObservableList<Teacher> userList = FXCollections.observableArrayList(); // tạo một list để lưu trữ dữ liệu được truyền từ database

        Connection connection = (Connection) DBConnection.getConnection();
        Statement statement = null;
        try {

            //query
            String sql = "select * from teacher";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Teacher em = new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name"),resultSet.getInt("teacher_year_at_school"),resultSet.getString("teacher_specialty"),resultSet.getString("teacher_phone"),resultSet.getDouble("teacher_salary"));
                userList.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(fullTimeModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(fullTimeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(fullTimeModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return userList;
    }

    public static ObservableList<Teacher> findByName(String name) {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

        Connection connection = (Connection) DBConnection.getConnection();
        PreparedStatement statement = null;

        try {

            //query
            String sql = "select * from teacher where teacher_name like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%"+name+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher em = new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name"),resultSet.getInt("teacher_year_at_school"),resultSet.getString("teacher_specialty"),resultSet.getString("teacher_phone"),resultSet.getDouble("teacher_salary"));
                teacherList.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.

        return teacherList;
    }
    public static ObservableList<Teacher> findBySpecialty(String specialty) {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

        Connection connection = (Connection) DBConnection.getConnection();
        PreparedStatement statement = null;

        try {

            //query
            String sql = "select * from teacher where teacher_specialty like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%"+specialty+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher em = new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name"),resultSet.getInt("teacher_year_at_school"),resultSet.getString("teacher_specialty"),resultSet.getString("teacher_phone"),resultSet.getDouble("teacher_salary"));
                teacherList.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.

        return teacherList;
    }
    public static ObservableList<Teacher> findByPhone(String phone) {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

        Connection connection = (Connection) DBConnection.getConnection();
        PreparedStatement statement = null;

        try {

            //query
            String sql = "select * from teacher where teacher_phone like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%"+phone+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher em = new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name"),resultSet.getInt("teacher_year_at_school"),resultSet.getString("teacher_specialty"),resultSet.getString("teacher_phone"),resultSet.getDouble("teacher_salary"));
                teacherList.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.

        return teacherList;
    }
    public static ObservableList<Teacher> findBySalary(Double salary) {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

        Connection connection = (Connection) DBConnection.getConnection();
        PreparedStatement statement = null;

        try {

            //query
            String sql = "select * from teacher where teacher_salary > ?";
            statement = connection.prepareCall(sql);
            statement.setDouble(1, salary);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher em = new Teacher(resultSet.getInt("teacher_id"),resultSet.getString("teacher_name"),resultSet.getInt("teacher_year_at_school"),resultSet.getString("teacher_specialty"),resultSet.getString("teacher_phone"),resultSet.getDouble("teacher_salary"));
                teacherList.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teacherModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.

        return teacherList;
    }
}
