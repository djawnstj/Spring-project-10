package org.example.grade;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CourseTest {

    @DisplayName("과목(코스)를 생성한다.")
    @Test
    public void createTest() {
        assertThatCode(() -> new Course("OOP", 3, "A"))
                .doesNotThrowAnyException();
    }

}
