package com.mycompany;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

/**
 *  1 -> Electronics
 *  2 -> Grocery
 *  3-> Mechanical
 */
public class CategoryConverter implements IConverter<Category> {
    @Override
    public Category convertToObject(String value, Locale locale) throws ConversionException {
        if(value==null||value.isEmpty()){
            throw new ConversionException("value can't be null or empty");
        }
        int catId=Integer.parseInt(value);
        if(catId==1){
            return Category.Electronics;
        }
        if(catId==2){
            return Category.Grocery;
        }
        if(catId==3) {
            return Category.Mechanical;
        }
        throw new ConversionException("cat id is invalid");
    }

    @Override
    public String convertToString(Category value, Locale locale) {
        return String.valueOf(value.getCategoryId());
    }
}
