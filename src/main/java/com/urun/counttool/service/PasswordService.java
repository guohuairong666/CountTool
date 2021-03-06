package com.urun.counttool.service;

import com.urun.counttool.model.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;



@Service
public class PasswordService {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private int hashIterations = 2;

//    public void encryptPassword(User user) {
//
//        user.setPasswordSalt(randomNumberGenerator.nextBytes().toHex());
//
//        String newPassword = new SimpleHash(
//                algorithmName,
//                user.getPassword(),
//                ByteSource.Util.bytes(user.getUserName()+user.getPasswordSalt()),
//                hashIterations).toHex();
//        user.setPassword(newPassword);
//    }
}
