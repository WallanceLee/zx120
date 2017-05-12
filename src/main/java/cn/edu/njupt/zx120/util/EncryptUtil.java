package cn.edu.njupt.zx120.util;

import cn.edu.njupt.zx120.model.UserModel;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by wallance on 3/7/17.
 */
public class EncryptUtil {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public void encryptPassword(UserModel user) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setCredentialsSalt(salt);
        String newPassword = md5Encrypt(user.getPassword(), user.getAccount() + salt);
        user.setPassword(newPassword);
    }

    public String encryptPassword(UserModel userModel, String password) {
        String salt = userModel.getCredentialsSalt();
        String encryptedPassword = md5Encrypt(password, userModel.getAccount() + salt);
        return encryptedPassword;
    }

    public String md5Encrypt(String originStr, String salt) {
        return new SimpleHash("md5", originStr, salt, 2).toHex();
    }

}
