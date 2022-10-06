package com.snoweegamecorp.backend.service.validation;

import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.model.actions.UserInsert;
import com.snoweegamecorp.backend.repository.UserRepository;
import com.snoweegamecorp.backend.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsert> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann){
    }

    @Override
    public boolean isValid(UserInsert user, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        UserModel userModel = repository.findByEmail(user.getEmail());
        if (userModel != null){
            list.add(new FieldMessage("email", "Email já cadastrado!"));
        }

        for (FieldMessage e: list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(
                            e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
