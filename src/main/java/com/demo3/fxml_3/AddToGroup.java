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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class AddToGroup implements Initializable {
    //info
    @FXML TextField firstNameTextField;
    @FXML TextField secondNameTextField;
    @FXML TextField IDTextField;

    @FXML private ListView groupsListView;
    @FXML private TextArea groupTextArea;

    public int IDGlobal;
    public ObservableList listOfGroups;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupsListView.getItems().addAll(Group.getGroupsNames());
        groupsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        String name = HelloController.getSelectedToMarkStudentName();
        String nameOfStudent = "", surnameOfStudent = "";
        int id = 0;
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

    }

    public void listViewButtonPushed(){
        String textAreaString = "";
        listOfGroups = groupsListView.getSelectionModel().getSelectedItems();
        for(Object o1 : listOfGroups){
            textAreaString += String.format("%s%n", (String)o1);
        }
        this.groupTextArea.setText(textAreaString);
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

    public void acceptButtonPushed(){
        ObservableList listOfGroups =  groupsListView.getSelectionModel().getSelectedItems();
        String[] _gNames = new String[listOfGroups.size()];
        System.out.println("Chosen groups: ");
        for(int i = 0; i < listOfGroups.size(); i ++){
            _gNames[i] = listOfGroups.get(i).toString();
            System.out.print(_gNames[i] + " ");
        }
        Student.getStudent().get(IDGlobal - 1).addGroup(listOfGroups);
    }
}
