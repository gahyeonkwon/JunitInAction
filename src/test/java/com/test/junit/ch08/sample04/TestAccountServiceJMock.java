package com.test.junit.ch08.sample04;

import com.test.junit.ch08.sample1.Account;
import com.test.junit.ch08.sample1.AccountManager;
import com.test.junit.ch08.sample1.AccountService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class TestAccountServiceJMock {

    @RegisterExtension // 확장 등록
    Mockery context = new JUnit5Mockery();

    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
        mockAccountManager = context.mock(AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        context.checking(new Expectations() {
            {
                /**
                 *  아래는 기대를 선언하는데, 다음과 같은 문법으로 사용할 수 있다.
                 *  invocation-count(mock-object) 까지만 필수로 작성해야하고 그 뒤에는 선택적 요소다
                 *
                 *  //////////////////////////////////////////////////////////////
                 *  invocation-count(mock-object).method(argument-constraints);
                 *  inSequence(sequence-name);
                 *  when(state-machine.is(state-name));
                 *  will(action);
                 *  then(state-machine.is(new-state-name));
                 */
                oneOf(mockAccountManager).findAccountForUser("1");
                will(returnValue(senderAccount));
                oneOf(mockAccountManager).findAccountForUser("2");
                will(returnValue(beneficiaryAccount));

                oneOf(mockAccountManager).updateAccount(senderAccount);
                oneOf(mockAccountManager).updateAccount(beneficiaryAccount);
            }
        });

        // 모의 객체 세팅
        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        // 테스트 대상
        accountService.transfer("1", "2", 50);


        // 예상 결과 단언문
        Assertions.assertEquals(150, senderAccount.getBalance());
        Assertions.assertEquals(150, beneficiaryAccount.getBalance());
    }

}
