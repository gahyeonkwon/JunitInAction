package com.test.junit.ch08.sample1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DefaultAccountManager2 implements AccountManager {
    private Log logger;
    private Configuration configuration;


    /**
     *  외부에서 제어할 수 있도록 logger, configuration 를 생성자로 받을 수 있게 변경
     * @param logger
     * @param configuration
     */
    public DefaultAccountManager2(Log logger, Configuration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

    public DefaultAccountManager2() {
        this(LogFactory.getLog(DefaultAccountManager2.class), new DefaultConfiguration("technical")); // sample 이라 오류 발생
    }

    public Account findAccountForUser(String userId) {
        this.logger.debug("Getting account for user [" + userId + "]");
        this.configuration.getSQL("FIND_ACCOUNT_FOR_USER");  // sample 이라 오류 발생

        //비즈니스로직 ...
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }
}
