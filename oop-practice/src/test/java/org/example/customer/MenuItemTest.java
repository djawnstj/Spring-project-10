package org.example.customer;

import org.assertj.core.api.Assertions;
import org.example.Customer.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MenuItemTest {

    @DisplayName("메뉴항목을 생성한다.")
    @Test
    public void createTest() {
        assertThatCode(() -> new MenuItem("만두", 5000))
                .doesNotThrowAnyException();

    }

}
