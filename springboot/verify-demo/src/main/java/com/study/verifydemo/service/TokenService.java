package com.study.verifydemo.service;

import com.study.verifydemo.common.ResultMsg;
import com.study.verifydemo.model.User;

public interface TokenService {

    ResultMsg createToken(User user);


    ResultMsg checkToken(String token);
}
