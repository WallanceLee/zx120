package cn.edu.njupt.zx120.controller.userAuthority;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dao.AuthoritiesDao;
import cn.edu.njupt.zx120.dao.UserAuthorityDao;
import cn.edu.njupt.zx120.dao.UserDao;
import cn.edu.njupt.zx120.dto.AuthStateEnum;
import cn.edu.njupt.zx120.model.UserAuthorityModel;
import cn.edu.njupt.zx120.model.UserModel;
import cn.edu.njupt.zx120.service.UserService;
import cn.edu.njupt.zx120.shiro.token.TokenUtil;
import cn.edu.njupt.zx120.util.EncryptUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

/**
 * Created by wallance on 3/3/17.
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserAuthorityDao userAuthorityDao;

    @Autowired
    private AuthoritiesDao authoritiesDao;

//    @RequiresRoles(value = { "ADMIN" })
    @RequestMapping(value = "/api/user/getList", method = RequestMethod.GET)
    public Map getList() {
        return resultMap(true, null, userService.queryAll());
    }

    // 创建新用户
    // account:password:username:department
    @RequestMapping(value = "/api/user/create", method = RequestMethod.POST)
    public Map addNewMember(@RequestBody JSONObject infoObject) throws Exception {
        String info = new String(Base64.getDecoder().decode(infoObject.getString("info")), "UTF-8");
        UserModel user = new UserModel();
        user.setAccount(info.split(":")[0]);
        user.setPassword(info.split(":")[1]);
        user.setUsername(info.split(":")[2]);
        user.setDepartment(info.split(":")[3]);
        EncryptUtil encryptUtil = new EncryptUtil();
        encryptUtil.encryptPassword(user);
        userService.saveUser(user);
        return resultMap(true, "注册用户成功");
    }

    @RequestMapping(value = "/api/user/updatePassword", method = RequestMethod.POST)
    public Map updatePassword(@RequestBody Map<String, String> paramMap) {

        String account = paramMap.get("account");

        UserModel userModel = userService.queryByAccount(account);
        EncryptUtil encryptUtil = new EncryptUtil();
        String encryptedOldPassword = encryptUtil.encryptPassword(userModel, paramMap.get("oldPassword"));
        if (!encryptedOldPassword.equals(userService.queryByAccount(account).getPassword())) {
            return resultMap(false, "原密码错误");
        }

        if (!paramMap.get("newPassword").equals(paramMap.get("newPasswordConfirmation"))) {
            return resultMap(false, "两次输入密码不一致");
        }

        userModel = userService.queryByAccount(account);
        userModel.setPassword(paramMap.get("newPassword"));
        encryptUtil.encryptPassword(userModel);
        userDao.updatePassword(account, userModel.getPassword());

        return null;
    }

    // 删除用户
//    @RequiresRoles(value = { "ADMIN" })
    @RequestMapping(value = "/api/user/delete", method = RequestMethod.DELETE)
    public Map deleteMember(@RequestParam("account") String account) throws Exception {
        userService.deleteUser(userService.queryByAccount(account));
        return resultMap(true, "删除用户成功");
    }

    // 用于管理员给用户分配权限
//    @RequiresRoles(value = { "ADMIN" })
    @RequestMapping(value = "/api/user/assignRole", method = RequestMethod.POST)
    public Map assignUserRole(@RequestParam("account") String account,
                              @RequestParam("type") String type) {
        UserAuthorityModel userAuthorityModel = new UserAuthorityModel();
        userAuthorityModel.setUserId((int)userService.queryByAccount(account).getId());
        userAuthorityModel.setAuthorityId((int)authoritiesDao.queryByType(type).getId());
        userAuthorityDao.addNewUserRoleAssignment(userAuthorityModel);
        return resultMap(true, "用户成功绑定权限");
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody Map<String, String> paramMap, HttpServletResponse response)
            throws UnsupportedEncodingException, AuthenticationException {

        String auth = paramMap.get("auth");
        System.out.println(auth);
        try {
            if (auth != null) {
                String authInfo = new String(Base64.getDecoder().decode(auth.toString()), "UTF-8");
                String username = authInfo.split(":")[0];
                String password = authInfo.split(":")[1];

                AuthenticationToken token = new UsernamePasswordToken(username, password, true);
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                response.addHeader("token", tokenUtil.createAndSaveToken(subject));
                UserModel user = userService.queryByAccount(username);
                user.setAuthorities(userService.queryAuthorities(username));
                return resultMap(true, AuthStateEnum.LOGIN_SUCCESSFULLY.getType(), user);
            } else {
                throw new AuthenticationException();
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return resultMap(false, AuthStateEnum.WRONG_USERNAME_OR_PASSOWRD.getType());
        }

    }

    @RequestMapping(value = "/temp", method = RequestMethod.POST)
    public Map generateTempToken(HttpServletRequest request) throws Exception {
        String token = tokenUtil.generateTempToken(SecurityUtils.getSubject());
        return resultMap(true, (Object) token);
    }

//    @RequestMapping(name = "/logout", method = RequestMethod.POST)
//    public Map logout(@RequestHeader(required = false) String token) throws Exception {
//        Subject subject = tokenUtil.getSubject(token);
//        if (subject != null) {
//            try {
//                subject.logout();
//            } catch (Exception e) {
//
//            }
//        }
//
//        return resultMap(true, "退出登录成功");
//    }

}
