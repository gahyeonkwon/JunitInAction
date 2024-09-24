package com.test.junit.ch08.sample05;

import com.test.junit.ch08.sample1.Account;
import com.test.junit.ch08.sample1.AccountManager;
import com.test.junit.ch08.sample1.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Junit5 테스트 확장
public class TestAccountServiceMockito {

    @Mock // MockitoExtension을 통해 확장되었기 때문에 @Mock을 사용할 수 있다.
    private AccountManager mockAccountManager;

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

//        Mockito.lenient() // lenient() 메서드가 있어야 동일한 findAccountForUser 메서드에 대해 기대를 여러개 선언할 수 있다.
//                .when(mockAccountManager.findAccountForUser("1"))
//                .thenReturn(senderAccount);
//
//        Mockito.lenient().when(mockAccountManager.findAccountForUser("2"))
//                .thenReturn(beneficiaryAccount);


        when(mockAccountManager.findAccountForUser("1"))
                .thenReturn(senderAccount);

        when(mockAccountManager.findAccountForUser("2"))
                .thenReturn(beneficiaryAccount);

        when(mockAccountManager.findAccountForUser("3"))
                .thenReturn(null);

//        Mockito.lenient().when(mockAccountManager.findAccountForUser("3"))
//                .thenReturn(null);


        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());

    }
}
