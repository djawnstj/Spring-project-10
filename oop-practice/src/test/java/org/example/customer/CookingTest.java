package org.example.customer;

import org.assertj.core.api.Assertions;
import org.example.Customer.Cook;
import org.example.Customer.Cooking;
import org.example.Customer.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CookingTest {

    @DisplayName("메뉴에 해당하는 음식을 만든다.")
    @Test
    public void makeCookTest() {
        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("돈까스", 5000);

        Cook cook = cooking.makeCook(menuItem);

        Assertions.assertThat(cook).isEqualTo(new Cook("돈까스", 5000));
    }

}
