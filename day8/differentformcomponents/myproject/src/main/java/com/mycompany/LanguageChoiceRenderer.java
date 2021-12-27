package com.mycompany;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.util.List;

public class LanguageChoiceRenderer implements IChoiceRenderer<Language> {
    @Override
    public Object getDisplayValue(Language object) {
        return object.getLanguageName().toUpperCase();
    }


}
