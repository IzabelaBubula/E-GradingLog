package com.demo3.fxml_3;

public class GroupSer implements java.io.Serializable{
    String nameGroup;
    int maxStud, CurStud;

    public GroupSer(String nameGroup, int maxStud, int curStud) {
        this.nameGroup = nameGroup;
        this.maxStud = maxStud;
        CurStud = curStud;
    }
}
