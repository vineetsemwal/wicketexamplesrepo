package com.mycompany;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

/**
 * id-name-price
 * 1-samsung-1000
 */
public class ProductConverter implements IConverter<Product> {
    @Override
    public Product convertToObject(String value, Locale locale) throws ConversionException {
        if(value==null || value.isEmpty()){
            throw new ConversionException("not valid input");
        }
        String parts[]= value.split("-");
       Product product = new Product();
       product.setId(Integer.parseInt(parts[0]));
       product.setName(parts[1]);
       product.setPrice(Double.parseDouble(parts[2]));
       return product;
    }

    @Override
    public String convertToString(Product value, Locale locale) {
        if(value==null){
            throw new ConversionException("not valid input");
        }
        return value.getId()+"-"+value.getName()+"-"+ value.getPrice();
    }
}
