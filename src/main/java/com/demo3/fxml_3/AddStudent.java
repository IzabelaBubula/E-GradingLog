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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.spreadsheet.SpreadsheetCellEditor;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddStudent implements Initializable {


//    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private DatePicker birthdayDatePicker;
    @FXML private TextField numberOfPointsTextField;
    @FXML private ListView groupsListView;
    @FXML private TextArea groupTextArea;

    public ObservableList listOfGroups;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupsListView.getItems().addAll(Group.getGroupsNames());
        groupsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        Student s1 = new Student(nameTextField.getText(), surnameTextField.getText(), birthdayDatePicker.getValue(), Integer.valueOf(numberOfPointsTextField.getText()), (Student.getStudent().size() + 1));
        ObservableList listOfGroups =  groupsListView.getSelectionModel().getSelectedItems();
        String[] _gNames = new String[listOfGroups.size()];
        System.out.println("Chosen groups: ");
        for(int i = 0; i < listOfGroups.size(); i ++){
            _gNames[i] = listOfGroups.get(i).toString();
            System.out.print(_gNames[i] + " ");
        }
        //Group.setStudents(s1, _gNames);
        Student.addStudent(s1, _gNames);
//        for(int j = 0 ; j < _gNames.length; j ++) {
//            for (int i = 0; i < Group.getGroups().size(); i++) {
//                if (Group.getGroups().get(i).getGroupName() == _gNames[j]) {
//                    for (Student s2 : Group.getGroups().get(i).getStudents())
//                        System.out.println("In group" + Group.getGroups().get(i).getGroupName() + " students: " + s2.getFirstName() + " ");
//                }
//            }
//        }
    }
}

