package com.demo3.fxml_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClass implements Initializable {

    @FXML private TextField groupNameTextField;
    @FXML private TextField maxNrOfStudentsTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        Group g1 = new Group(groupNameTextField.getText(), Integer.valueOf(maxNrOfStudentsTextField.getText()));
        Group.addGroup(g1);
    }
}
