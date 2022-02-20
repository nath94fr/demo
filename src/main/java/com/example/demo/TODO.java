package com.example.demo;

public class TODO {

    private String title;
    private int state;  //State = 1 for "To do"
                        //State = 2 for "Done and crossed out"
    private String description;

    public TODO(String title, String description){
        this.title = title;
        this.state = 1; //State set on "To do" by default at Todo object creation
        this.description = description;
    }

    public String getTitle(){
        return this.title;
    }

    //US2 add for state checking when loading page
    public int getState() {
        return this.state;
    }

    public void setSateToDone(){
        this.state = 2;
    }

    public String getDescription(){
        return this.description;
    }
}
