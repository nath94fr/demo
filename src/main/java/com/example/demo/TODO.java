package com.example.demo;

public class TODO {
    private String Title;
    private int State;  //State = 1 for "To do"
                        //State = 2 for "Done and crossed out"

    public TODO(String Title){
        this.Title = Title;
        this.State = 1; //State set on "To do" by default at Todo object creation
    }

    public String getTitle(){
        return this.Title;
    }
}
