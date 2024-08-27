package com.test.junit.ch08.sample1;

/**
 *  Account 객체의 생애 주기와 영속성을 관리함
 */
public interface AccountManager {
    Account findAccountForUser(String userId);
    void updateAccount(Account account);
}
