package com.demo3.fxml_3;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Group{
    private SimpleStringProperty groupName;
    private SimpleIntegerProperty maxNrOfStudents;
    private SimpleIntegerProperty currNrOfStudents;
    private SimpleIntegerProperty ID;

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }


    private static ObservableList<Student> students = FXCollections.observableArrayList();

    private static ObservableList<Group> data =
            FXCollections.observableArrayList(
               new Group("Math", 15, 0),
               new Group("PE", 30, 0),
               new Group("Bio", 20, 0)
            );

    static final String[] getGroupsNames(){
        String[] tab = new String[data.size()];
        for(int i = 0; i < data.size(); i ++){
            tab[i] = data.get(i).getGroupName();
        }
        return tab;
    }

    static final ObservableList<Group> getGroups(){
        return data;
    }

    static final void addGroup(Group g1){
        data.add(g1);
        for (Group g12: data) {
            System.out.println(g12.getGroupName() + " ");
        }
    }


    static final ObservableList<Group> addStudentGoGroup(Student s1){
        return data;
    }

    public final ObservableList<Student> getStudentsObserv(){return students;}

    public ArrayList<Student> getStudents() {
        ArrayList<Student> stud = new ArrayList<>();
        for(Student s1 : students){
            stud.add(s1);
        }
        return stud;
    }

    public static void setStudents(Student s1, String[] _groups) {
        for(int j = 0; j < _groups.length; j ++) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getGroupName() == _groups[j]) {
                    data.get(i).addStudent(s1);
                    data.get(i).setMaxNrOfStudents(data.get(i).getMaxNrOfStudents());
                    data.get(i).setCurrNrOfStudents(1);
                }
            }
        }
    }

    public static void setStudents(Student s1, String group){
        for(int i = 0 ; i < data.size(); i ++){
            if(data.get(i).getGroupName() == group)
                data.get(i).addStudent(s1);
        }
    }

    public void addStudent(Student s1){
        students.add(s1);
    }

    public void addStudent(String firstName, String lastName, LocalDate birthday, int nrOfPoints, int ID ){
        Student s1 = new Student(firstName, lastName, birthday, nrOfPoints, ID);
        students.add(s1);
    }

    public String getGroupName() {
        return groupName.get();
    }

    public SimpleStringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public int getMaxNrOfStudents() {
        return maxNrOfStudents.get();
    }

    public SimpleIntegerProperty maxNrOfStudentsProperty() {
        return maxNrOfStudents;
    }

    public void setMaxNrOfStudents(int maxNrOfStudents) {
        this.maxNrOfStudents.add(maxNrOfStudents);
    }

    public int getCurrNrOfStudents() {
        return students.size();
    }

    public SimpleIntegerProperty currNrOfStudentsProperty() {
        return currNrOfStudents;
    }

    public void setCurrNrOfStudents(int currNrOfStudents) {
        this.currNrOfStudents.set(this.getCurrNrOfStudents() + 1);
    }


    public Group(String groupName, Integer maxNrOfStudents, Integer currNrOfStudents) {
        this.groupName = new SimpleStringProperty(groupName);
        this.maxNrOfStudents = new SimpleIntegerProperty(maxNrOfStudents);
        this.currNrOfStudents = new SimpleIntegerProperty(currNrOfStudents);
    }

    public Group(String groupName, Integer maxNrOfStudents){
        this.groupName = new SimpleStringProperty(groupName);
        this.maxNrOfStudents = new SimpleIntegerProperty(maxNrOfStudents);
        this.currNrOfStudents = new SimpleIntegerProperty(0);
    }



    public Group(String groupName, int maxNrOfStudents, int currNrOfStudents, ObservableList<Student> studentsObserv) {}
}
