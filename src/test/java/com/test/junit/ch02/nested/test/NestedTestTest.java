package com.test.junit.ch02.nested.test;

import com.test.junit.ch02.nested.Customer;
import com.test.junit.ch02.nested.Gender;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedTestTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";

    @Nested
    class BuilerTest {
        private String MIDDLE_NAME = "Michael";

        @Test
        void customerBuilder() throws ParseException {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate customerDate = LocalDate.parse( "04-21-2019", dateFormatter);

            Customer customer =  new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .withMiddleName(MIDDLE_NAME)
                    .withBecomeCustomer(customerDate)
                    .build();

            assertAll(
                    () -> {
                        assertEquals(Gender.MALE, customer.getGender());
                        assertEquals(FIRST_NAME, customer.getFirstName());
                        assertEquals(LAST_NAME, customer.getLastName());
                        assertEquals(MIDDLE_NAME, customer.getMiddleName());
                        assertEquals(customerDate, customer.getBecomeCustomer());

                    });
        }
    }

}
