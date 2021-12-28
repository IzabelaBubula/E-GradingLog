package com.demo3.fxml_3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.SortedMap;

public class ShowGroupInfo implements Initializable{

    @FXML private TextField nameTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField currTextField;
    @FXML private TableView<Student> studentTableView;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;
    @FXML private TableColumn<Student, LocalDate> birthdayColumn;
    @FXML private TableColumn<Student, Integer> nrofpktColumn;
    @FXML private TableColumn<Student, Integer> idColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Hello");
        String name = HelloController.getSelectedGroupName();
        String nameG = "";
        int max = 0, cur = 0;
        Group g1;
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        for(Group g2 : Group.getGroups()) {
            if (g2.getGroupName() == name){
                System.out.println("hello2");
                System.out.println("G1 : " + name + " g2: " + g2.getGroupName());
                nameG = g2.getGroupName();
                max = g2.getMaxNrOfStudents();
                cur = g2.getCurrNrOfStudents();
                studentList = g2.getStudentsObserv();
                System.out.println("nameG = " + nameG + " max  = " + max + " cur = " + cur);
                System.out.println("Students = " + studentList.get(0).getFirstName());
            }
        }
//      System.out.println(studentList.get(0).getFirstName());

        String nameAreaString = "";
        nameAreaString += String.format("%s%n", nameG);
        this.nameTextField.setText(nameAreaString);
        nameAreaString = "";
        nameAreaString += String.format("%s%n", Integer.toString(max));
        this.maxTextField.setText(nameAreaString);
        nameAreaString = "";
        nameAreaString += String.format("%s%n", Integer.toString(cur));
        this.currTextField.setText(nameAreaString);

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("Birthday"));
        nrofpktColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("nrOfPoints"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        studentTableView.setItems(studentList);
    }

    public void openMainPanel(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        // get the source of the event
        Object eventSource = actionEvent.getSource();

        // the event only knows its source is some kind of object, however, we
        // registered this listener with a button, which is a Node, so we know
        // the actual runtime type of the source must be Button (which is a Node)
        // So tell the compiler we are confident we can treat this as a Node:

        Node sourceAsNode = (Node) eventSource ;

        // get the scene containing the Node (i.e. containing the button):
        Scene oldScene = sourceAsNode.getScene();

        // get the window containing the scene:
        Window window = oldScene.getWindow();

        // Again, the Scene only knows it is in a Window, but we know we specifically
        // put it in a stage. So we can downcast the Window to a Stage:

        Stage stage = (Stage) window ;

        // Equivalently, just omitting all the intermediate variables:
        // Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
