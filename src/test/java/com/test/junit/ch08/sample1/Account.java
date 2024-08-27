package com.test.junit.ch08.sample1;


/**
 *  데이터 접근을 위한 객체
 */
public class Account {
    private String accountId;
    private long balance;

    public Account(String accountId, long balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    /***
     *  출금
     * @param amount
     */
    public void debit(long amount) {
        this.balance -= amount;
    }

    /***
     *  입금
     * @param amount
     */
    public void credit(long amount) {
        this.balance += amount;
    }

    /**
     *  잔액 확인
     * @return
     */
    public long getBalance() {
        return this.balance;
    }
}
