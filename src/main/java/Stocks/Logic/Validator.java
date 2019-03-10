package Stocks.Logic;

import JeaDemo.interceptor.UserInterceptor;
import Stocks.Models.User;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
@Stateless
@Interceptors(UserInterceptor.class)
public class Validator {

    public Set<ConstraintViolation<User>> validator(User user){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();


        Set<ConstraintViolation<User>> violations = validator.validate(user);

        for (ConstraintViolation<User> violation : violations) {
            //log.error(violation.getMessage());
            System.out.println(violation.getMessage());

        }
        return violations;
    }
}
