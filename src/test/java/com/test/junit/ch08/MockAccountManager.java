package com.test.junit.ch08;

import java.util.HashMap;
import java.util.Map;

public class MockAccountManager implements AccountManager {
    private Map<String, Account> accounts = new HashMap<>();

    /**
     *  accounts 에 userId 를 키로 갖고 Account객체를 값으로 갖는 쌍을 추가 함
     * @param userId
     * @param account
     */
    public void addAccount(String userId, Account account) {
        this.accounts.put(userId, account);
    }

    public Account findAccountForUser(String userId) {
        return this.accounts.get(userId);
    }

    public void updateAccount(Account account) {
        // do nothing ...
    }
}
