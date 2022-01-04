package com.demo3.fxml_3;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Student implements java.io.Serializable{
    private SimpleStringProperty firstName, lastName;
    private LocalDate birthday;
    private SimpleIntegerProperty nrOfPoints, ID;
    ObservableList<Group> _group;
    ObservableList<Marks> _marks = FXCollections.observableArrayList();

    private static ObservableList<Student> data =
            FXCollections.observableArrayList(
                    new Student("Frank","Sinatra",LocalDate.of(1915, Month.DECEMBER, 12), 12, 1, "Math"),
                    new Student("Rebecca","Fergusson",LocalDate.of(1986, Month.JULY, 21), 13, 2, "PE"),
                    new Student("Mr.","T",LocalDate.of(1952, Month.MAY, 21), 34, 3, "Bio")
            );


    static final String[] getStudentsInfo(){
        String[] info = new String[data.size()];
        for(int i = 0 ; i < data.size(); i ++){
            info[i] = Integer.toString(data.get(i).getID()) + " " + data.get(i).getFirstName() + " " + data.get(i).getLastName();
        }
        return info;
    }

    public final String[] getGroupsNames(){
        String[] names = new String[_group.size()];
        for(int i = 0; i < _group.size(); i++){
            names[i] = _group.get(i).getGroupName();
            System.out.print( names[i] + " ");
        }
        return names;
    }

    public void addGroup(ObservableList<Group> groups){
        for(int i = 0; i < _group.size() - 1; i++)
            System.out.print(_group.get(i).getGroupName() + " ");
    }

    public ObservableList<Marks> getMarks(){
        return _marks;
    }

    public void setMark(String groupName, double markValue){
        Marks m1 = new Marks(groupName, markValue);
        _marks.add(m1);
    }

    public Student(String firstName, String lastName, LocalDate birthday, int nrOfPoints, int ID) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.nrOfPoints = new SimpleIntegerProperty(nrOfPoints);
        this.ID = new SimpleIntegerProperty(ID);
    }

    public Student(String firstName, String lastName, LocalDate birthday, int nrOfPoints, int ID, String g1) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.nrOfPoints = new SimpleIntegerProperty(nrOfPoints);
        this.ID = new SimpleIntegerProperty(ID);
        _group = FXCollections.observableArrayList();
        for(int i = 0 ; i < Group.getGroups().size(); i ++){
            if(Group.getGroups().get(i).getGroupName() == g1){
                Group.getGroups().get(i).addStudent(firstName, lastName, birthday, nrOfPoints, ID);
                _group.add(Group.getGroups().get(i));
            }
        }
    }

    public Student(String firstName, String lastName, LocalDate birthday, int nrOfPoints, int ID, String[] groups) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.nrOfPoints = new SimpleIntegerProperty(nrOfPoints);
        this.ID = new SimpleIntegerProperty(ID);
        _group = FXCollections.observableArrayList();
        for(int i = 0 ; i < Group.getGroups().size(); i ++){
            if(Group.getGroups().get(i).getGroupName() == groups[i]){
                Group.getGroups().get(i).addStudent(firstName, lastName, birthday, nrOfPoints, ID);
                _group.add(Group.getGroups().get(i));
            }
        }
    }

    public final void addToClass(String[] names){
        for(int i = 0; i < Group.getGroups().size();i++){
            if(Group.getGroups().get(i).getGroupName() == names[i]){
                _group.add(Group.getGroups().get(i));
                Group.getGroups().get(i).addStudent(this);
            }
        }
    }

    static final ObservableList<Student> getStudent(){
        return data;
    }

    static final void addStudent(Student s1){
        data.add(s1);
        for (Student s12: data) {
            System.out.println(s12.getFirstName() + " ");
        }
    }

    static final void addStudent(Student s1, String[] groups){
        data.add(s1);
        for (Student s12: data) {
            System.out.println(s12.getFirstName() + " ");
        }
        s1._group = FXCollections.observableArrayList();
        for(int i = 0; i < groups.length; i++){
            for(int j = 0; j < Group.getGroups().size(); j++){
                System.out.println("Groups: " + groups[i]);
                if(Group.getGroups().get(j).getGroupName() == groups[i]){
                    Group.getGroups().get(j).addStudent(s1);
                    s1._group.add(Group.getGroups().get(j));
                }
            }
        }

//        for(int i = 0 ; i < Group.getGroups().size(); i ++){
//            if(Group.getGroups().get(i).getGroupName() == groups[i]){
//                Group.getGroups().get(i).addStudent(s1);
//                s1._group.add(Group.getGroups().get(i));
//            }
//        }
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getNrOfPoints() {
        return nrOfPoints.get();
    }

    public SimpleIntegerProperty nrOfPointsProperty() {
        return nrOfPoints;
    }

    public void setNrOfPoints(int nrOfPoints) {
        this.nrOfPoints.set(nrOfPoints);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }
}
