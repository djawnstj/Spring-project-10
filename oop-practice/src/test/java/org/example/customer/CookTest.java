package org.example.customer;

import org.assertj.core.api.Assertions;
import org.example.Customer.Cook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CookTest {

    @DisplayName("요리를 생성")
    @Test
    public void createTest() {
        assertThatCode(() -> new Cook("만두", 5000))
                .doesNotThrowAnyException();
    }

}
