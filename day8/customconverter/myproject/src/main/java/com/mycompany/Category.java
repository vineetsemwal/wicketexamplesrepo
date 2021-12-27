package com.mycompany;

public enum Category {

    Electronics(1,"el"), Grocery(2,"gro"), Mechanical(3,"mec");

    private int categoryId;

    private String shortName;

    public String getShortName(){
      return shortName;
    }

    public int getCategoryId(){
        return categoryId;
    }

    Category(int id, String shortName){
        this.categoryId = id;
        this.shortName=shortName;
    };

}
