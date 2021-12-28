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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowStudntPanel implements Initializable {

// personal info panel
    @FXML TextField firstNameTextField;
    @FXML TextField secondNameTextField;
    @FXML TextField IDTextField;
//Table View panel
    @FXML TableView<Marks> marksTableView;
    @FXML TableColumn<Marks, String> groupNameTableColumn;
    @FXML TableColumn<Marks, Integer> markValueTableColumn;
// Marks: List view and Text Field
    @FXML ListView groupNameListView;
    @FXML TextField averageTextField;

    public int IDGlobal = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student.getStudent().get(0).setMark("Math", 5);
        Student.getStudent().get(0).setMark("Math", 4);
        Student.getStudent().get(0).setMark("Math", 5);
        Student.getStudent().get(0).setMark("Math", 4.5);
        Student.getStudent().get(1).setMark("PE", 5);
        Student.getStudent().get(1).setMark("PE", 3);
        Student.getStudent().get(1).setMark("PE", 4);
        Student.getStudent().get(2).setMark("Bio", 5);
        Student.getStudent().get(2).setMark("Bio", 4);
        Student.getStudent().get(2).setMark("Bio", 3.5);


        // taking form main panel info about selected item
        String name = HelloController.getSelectedStudentName();
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
                for(int i=0; i < Student.getStudent().get(id - 1)._group.size(); i ++)
                    System.out.print(Student.getStudent().get(id - 1)._group.get(i).getGroupName());
                System.out.println();
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


        groupNameListView.getItems().addAll(Student.getStudent().get(id - 1).getGroupsNames());

        if(Student.getStudent().get(id - 1)._marks.size() == 0) {
            System.out.println("table is null");
        } else{
            for(Marks m1: Student.getStudent().get(id - 1)._marks){
                System.out.println("Mark for: " +  m1.getGroupName() + " value " + m1.getMarkValue());
            }
            groupNameTableColumn.setCellValueFactory(new PropertyValueFactory<Marks, String>("groupName"));
            markValueTableColumn.setCellValueFactory(new PropertyValueFactory<Marks, Integer>("markValue"));
            marksTableView.setItems(Student.getStudent().get(id - 1)._marks);
        }

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

   public void listButtonPushed(){
        String gName = (String) groupNameListView.getSelectionModel().getSelectedItem();
        double i = 0;
        double sum = 0;
        for(int j =0; j < Student.getStudent().get(IDGlobal - 1)._marks.size(); j ++){
            if(Student.getStudent().get(IDGlobal - 1)._marks.get(j).getGroupName() == gName){
                sum += Student.getStudent().get(IDGlobal - 1)._marks.get(j).getMarkValue();
                i++;
            }
        }
       System.out.println("Suma = " + sum + " nr of numbers = " + i);
       String textAreaString = Double.toString(sum / i);
       this.averageTextField.setText(textAreaString);
   }
}
