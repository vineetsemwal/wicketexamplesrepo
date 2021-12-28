package com.mycompany;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

public class ExactErrorLevelFilter implements IFeedbackMessageFilter {

    private int desiredLevel;

    public ExactErrorLevelFilter(int desiredLevel){
        this.desiredLevel=desiredLevel;
    }

    @Override
    public boolean accept(FeedbackMessage message) {
        return message.getLevel()==desiredLevel;
    }
}
