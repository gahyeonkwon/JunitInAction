package com.test.junit.ch08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAccountService {

    @Test
    public void testTransferOK() {

        // 굳이 데이터 접근 객체까지 모의로 선언할 필요는 없다. 이런 객체는 환경에 영향을 받지도 않고 매우 단순하다.
        Account sendAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        // mock 객체에 반환될 계좌 정보 추가
        MockAccountManager mockAccountManager = new MockAccountManager();
        mockAccountManager.addAccount("1", sendAccount);
        mockAccountManager.addAccount("2", beneficiaryAccount);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        accountService.transfer("1", "2", 50);

       assertEquals(150, sendAccount.getBalance());
       assertEquals(150, beneficiaryAccount.getBalance());


    }
}
