package com.example.chat.other.validation;

import com.example.chat.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private UserDao userDao;

    @Autowired
    public EmailValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userDao.existsByEmail(s);
    }

    @Override
    public void initialize(Email constraintAnnotation) {

    }
}
