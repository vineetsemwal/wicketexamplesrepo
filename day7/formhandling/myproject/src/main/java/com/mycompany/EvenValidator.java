package com.mycompany;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class EvenValidator implements IValidator<Integer> {

    public static EvenValidator getInstance(){
        return new EvenValidator();
    }

    @Override
    public void validate(IValidatable<Integer> validatable) {
      Integer value=validatable.getValue();
      ValidationError error=new ValidationError(this);
      error.setVariable("evenfail","value is not even");
      error.setMessage("value is not even");
      if( value==null || value%2!=0){
          validatable.error(error);
      }
    }
}
