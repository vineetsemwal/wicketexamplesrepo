package com.mycompany;

import java.util.Objects;

public class Language {

    private String languageName;


    public Language(){}

    public Language(String langName){
        this.languageName=langName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return (language.getLanguageName().equalsIgnoreCase(languageName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageName);
    }
}
