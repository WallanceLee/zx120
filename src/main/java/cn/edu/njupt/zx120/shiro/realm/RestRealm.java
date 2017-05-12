package cn.edu.njupt.zx120.shiro.realm;

import cn.edu.njupt.zx120.model.AuthorityModel;
import cn.edu.njupt.zx120.model.UserModel;
import cn.edu.njupt.zx120.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wallance on 2/22/17.
 */
public class RestRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        if (username != null) {
            UserModel userModel = null;
            List<AuthorityModel> authorityModelList = null;

            try {
                userModel = userService.queryByAccount(username);
                authorityModelList = userService.queryAuthorities(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            if (authorityModelList != null) {
                for (AuthorityModel authorityModel : authorityModelList) {
                    info.addRole(authorityModel.getType());
                }
                return info;
            } else {
                throw new AuthorizationException("无法查询到对应的权限");
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        SimpleAuthenticationInfo authenticationInfo = null;
        UserModel userModel = null;

        try {
            userModel = userService.queryByAccount(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userModel != null) {
            authenticationInfo = new SimpleAuthenticationInfo(userModel.getAccount(), userModel.getPassword(), ByteSource.Util.bytes(username + "" + userModel.getCredentialsSalt()), getName());
            return authenticationInfo;
        } else {
            throw new UnknownAccountException("帐号或密码错误");
        }
    }

    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        super.clearCachedAuthorizationInfo(principalCollection);
    }

    public void clearCachedAuthenticationInfo(PrincipalCollection principalCollection) {
        super.clearCachedAuthenticationInfo(principalCollection);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearCache(PrincipalCollection principalCollection) {
        super.clearCache(principalCollection);
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
