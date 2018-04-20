package com.nzgreens.common.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.ByteSource;


public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
   // private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
       // passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
        AuthenticationInfo info) {
        boolean matches = super.doCredentialsMatch(token, info);
        return matches;
    }
	
	/**
	* build user password
	*/
	public String buildCredentials(String userName, String password,String credentialsSalt) {
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,ByteSource.Util.bytes(userName + credentialsSalt),userName);
		AuthenticationToken token = new UsernamePasswordToken(userName, password);
		return super.hashProvidedCredentials(token, authenticationInfo).toString();
    }
}
