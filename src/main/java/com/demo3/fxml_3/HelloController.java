package com.demo3.fxml_3;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

public class HelloController implements Initializable {

    private static String nameOfGroup;
    private static String nameOfStudent;
    private static String toMarkStudentName;
    //students field
    @FXML private TableView<Student> tableView;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;
    @FXML private TableColumn<Student, LocalDate> birthdayColumn;
    @FXML private TableColumn<Student, Integer> nrofpktColumn;
    @FXML private TableColumn<Student, Integer> idColumn;

    //group field
    @FXML private TableView<Group> tableView1;
    @FXML private TableColumn<Group, String> nameColumn;
    @FXML private TableColumn<Group, Integer> maxColumn;
    @FXML private TableColumn<Group, Integer> currColumn;

    //filter field
    @FXML private TextField filteredField;




//    public ObservableList<Student> getStudent() {
//        ObservableList<Student> people = FXCollections.observableArrayList();
//        people.add(new Student("Frank","Sinatra",LocalDate.of(1915, Month.DECEMBER, 12), 12, 1));
//        people.add(new Student("Rebecca","Fergusson",LocalDate.of(1986, Month.JULY, 21), 13, 2));
//        people.add(new Student("Mr.","T",LocalDate.of(1952, Month.MAY, 21), 34, 3));
//        return people;
//    }

//    public ObservableList<Group> getGroups() {
//        ObservableList<Group> groups = FXCollections.observableArrayList();
//        groups.add(new Group("Math", 15, 0));
//        groups.add(new Group("PE", 30, 0));
//        groups.add(new Group("Bio", 20, 0));
//        return  groups;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("Birthday"));
        nrofpktColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("nrOfPoints"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));

        tableView.setItems(Student.getStudent());

        nameColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("groupName"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<Group, Integer>("maxNrOfStudents"));
        currColumn.setCellValueFactory(new PropertyValueFactory<Group, Integer>("currNrOfStudents"));

        tableView1.setItems(Group.getGroups());

        FilteredList<Student> filteredData = new FilteredList<>(Student.getStudent(), b -> true);

        filteredField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(student -> {

            if(newValue == null || newValue.isEmpty()){return true;}

            String lowerCaseFilter = newValue.toLowerCase();

            if(student.getFirstName().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                return true;
            } else if (student.getLastName().toLowerCase().indexOf(lowerCaseFilter) > 1) {
                return true;
            } else if (String.valueOf(student.getID()).indexOf(lowerCaseFilter) > 1) {
                return  true;
            } else if(String.valueOf(student.getID()).indexOf(lowerCaseFilter) > -1) {
                return true;
            } else
                return false;
        });
    });

        SortedList<Student> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
}

//   pressed buttopn methodes


    public void openAddStudentPanel(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("add-student.fxml"));

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

    public void openAddClassPanel(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("add-class.fxml"));

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

    public void openAddMerksSetStudentPanel(ActionEvent actionEvent) throws Exception {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("You must select a student to add mark");
            errorAlert.showAndWait();
        } else {

            toMarkStudentName = tableView.getSelectionModel().getSelectedItem().getFirstName();
            Parent root = FXMLLoader.load(getClass().getResource("set-mark.fxml"));

// get the source of the event
            Object eventSource = actionEvent.getSource();

// the event only knows its source is some kind of object, however, we
// registered this listener with a button, which is a Node, so we know
// the actual runtime type of the source must be Button (which is a Node)
// So tell the compiler we are confident we can treat this as a Node:

            Node sourceAsNode = (Node) eventSource;

// get the scene containing the Node (i.e. containing the button):
            Scene oldScene = sourceAsNode.getScene();

// get the window containing the scene:
            Window window = oldScene.getWindow();

// Again, the Scene only knows it is in a Window, but we know we specifically
// put it in a stage. So we can downcast the Window to a Stage:

            Stage stage = (Stage) window;

// Equivalently, just omitting all the intermediate variables:
// Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void openAddToGroupPanel(ActionEvent actionEvent) throws Exception {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("You must select a student to add mark");
            errorAlert.showAndWait();
        } else {

            toMarkStudentName = tableView.getSelectionModel().getSelectedItem().getFirstName();
            Parent root = FXMLLoader.load(getClass().getResource("add-to-group.fxml"));

// get the source of the event
            Object eventSource = actionEvent.getSource();

// the event only knows its source is some kind of object, however, we
// registered this listener with a button, which is a Node, so we know
// the actual runtime type of the source must be Button (which is a Node)
// So tell the compiler we are confident we can treat this as a Node:

            Node sourceAsNode = (Node) eventSource;

// get the scene containing the Node (i.e. containing the button):
            Scene oldScene = sourceAsNode.getScene();

// get the window containing the scene:
            Window window = oldScene.getWindow();

// Again, the Scene only knows it is in a Window, but we know we specifically
// put it in a stage. So we can downcast the Window to a Stage:

            Stage stage = (Stage) window;

// Equivalently, just omitting all the intermediate variables:
// Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void openSearchPanel(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("search-panel.fxml"));

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


    //pressed table methodes
    public void showGroupPanel(Event Event) throws Exception{
        try {
            nameOfGroup = tableView1.getSelectionModel().getSelectedItem().getGroupName();
            System.out.println("in main " + nameOfGroup + " \n");

            Parent root = FXMLLoader.load(getClass().getResource("show-group-info.fxml"));


// get the source of the event
            Object eventSource = Event.getSource();

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
        } catch (IOException e) {
//            Logger logger = Logger.getLogger(getClass().getName());
//            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void showStudentPanel(Event Event) throws Exception{
        try {
            nameOfStudent = tableView.getSelectionModel().getSelectedItem().getFirstName();
//            nameOfGroup = tableView1.getSelectionModel().getSelectedItem().getGroupName();
            System.out.println("in main " + nameOfStudent + " \n");

            Parent root = FXMLLoader.load(getClass().getResource("show-studnt-panel.fxml"));


// get the source of the event
            Object eventSource = Event.getSource();

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
        } catch (IOException e) {
//            Logger logger = Logger.getLogger(getClass().getName());
//            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


    static final String getSelectedGroupName(){ return nameOfGroup;}
    static final String getSelectedStudentName() {return nameOfStudent;}
    static final String getSelectedToMarkStudentName() {return toMarkStudentName;};
}



