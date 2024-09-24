package com.test.junit.ch08.sample03;

import com.test.junit.ch08.sample1.Account;
import com.test.junit.ch08.sample1.AccountManager;
import com.test.junit.ch08.sample1.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;

public class TestAccountServiceEasyMock {

    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOK() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        // 기대를 정의 한다
        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

        // 기대 정의가 끝나면 replay를 호출한다
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        // 테스트 대상 메서드
        accountService.transfer("1", "2", 50);

        Assertions.assertEquals(150, senderAccount.getBalance());
        Assertions.assertEquals(150, beneficiaryAccount.getBalance());
    }

    @AfterEach
    public void tearDown() {
        verify(mockAccountManager); // 기대가 충족 되었는지 검증
    }
}
