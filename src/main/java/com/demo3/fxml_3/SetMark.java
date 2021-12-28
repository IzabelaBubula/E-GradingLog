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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class SetMark implements Initializable {

    //info
    @FXML TextField firstNameTextField;
    @FXML TextField secondNameTextField;
    @FXML TextField IDTextField;

    //list
    @FXML ListView groupsListView;
    @FXML TextField addMarkTextField;

    public int IDGlobal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = HelloController.getSelectedToMarkStudentName();
        String nameOfStudent = "", surnameOfStudent = "";
        int id = 0;
        ObservableList<Marks> _marks = FXCollections.observableArrayList();
        for(Student s1 : Student.getStudent()){
            if(s1.getFirstName() == name){
                System.out.println(s1.getFirstName());
                nameOfStudent = s1.getFirstName();
                surnameOfStudent = s1.getLastName();
                id = s1.getID();
                IDGlobal = id;
                System.out.println("Name: " + nameOfStudent + " surname: " + surnameOfStudent + " id: " + id);
            }
        }

        // adding to view
        String nameAreaString = "";
        nameAreaString += String.format("%s%n", nameOfStudent);
        firstNameTextField.setText(nameAreaString);
        nameAreaString = "";
        nameAreaString += String.format("%s%n", surnameOfStudent);
        secondNameTextField.setText(nameAreaString);
        nameAreaString = "";
        nameAreaString += String.format("%s%n", id);
        IDTextField.setText(nameAreaString);


        groupsListView.getItems().addAll(Student.getStudent().get(id - 1).getGroupsNames());

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


    public void listViewButtonPushed(){
        String gName = groupsListView.getSelectionModel().getSelectedItem().toString();
        double value = Double.valueOf(addMarkTextField.getText());
        System.out.println("Group name = : " + gName + " value: " + value);
        Student.getStudent().get(IDGlobal - 1).setMark(gName, value);
    }
}
