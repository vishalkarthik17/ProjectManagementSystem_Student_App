package com.example.student_app;

public class GroupClasss {
    private String groupid,facultyid,title,mainarea,subarea,panelAssign;

    public GroupClasss() {
    }

    public String getGroupid() {
        return groupid;
    }

    public String getPanelAssign() {
        return panelAssign;
    }

    public void setPanelAssign(String panelAssign) {
        this.panelAssign = panelAssign;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(String facultyid) {
        this.facultyid = facultyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainarea() {
        return mainarea;
    }

    public void setMainarea(String mainarea) {
        this.mainarea = mainarea;
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }
}
