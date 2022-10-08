package com.snoweegamecorp.backend.model.actions.user;

import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.service.validation.user.UserInsertValid;

@UserInsertValid
public class UserInsert extends UserModel {
    private static final long serialVerionUID = 1L;
    private String password;
    UserInsert(){
        super();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
