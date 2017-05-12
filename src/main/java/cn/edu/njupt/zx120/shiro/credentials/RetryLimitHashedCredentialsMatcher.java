package cn.edu.njupt.zx120.shiro.credentials;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.ByteSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wallance on 2/22/17.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String account = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(account);

        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(account, retryCount);
        }

        if (retryCount.incrementAndGet() > 5) throw new ExcessiveAttemptsException("密码错误次数过多， 请5分钟后再试");

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) passwordRetryCache.remove(account);
        return matches;
    }

    public String buildCredentials(String username, String password, String credentialSalt) {
        System.out.println("RetryLimitHashedCredentialsMatcher.buildCredentials");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(username + credentialSalt), username);
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        return super.hashProvidedCredentials(token, authenticationInfo).toString();
    }

}