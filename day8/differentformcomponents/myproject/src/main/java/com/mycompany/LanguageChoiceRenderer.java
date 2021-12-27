package com.mycompany;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * choice renderer will help me in displaying the text i want for a particular modelobject language in our case
 */
public class LanguageChoiceRenderer implements IChoiceRenderer<Language> {
    @Override
    public Object getDisplayValue(Language object) {
        return object.getLanguageName().toUpperCase();
    }


}
