package com.test.junit.ch08.sample1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DefaultAccountManage1 implements AccountManager {

    // 문제1) 로거 객체가 내부에서 선언 되어 있다
    private static final Log logger =
            LogFactory.getLog(DefaultAccountManage1.class);

    public Account findAccountForUser(String userId) {
        logger.debug("Getting account for user [" + userId + "]");

        //
        ResourceBundle bundle = PropertyResourceBundle.getBundle("technical"); // 문제2) 만약 technical 에서 변경 된다면?
        String sql = bundle.getString("FIND_ACCOUNT_FOR_USER");
        return null;
    }

    public void updateAccount(Account account) {

    }
}
