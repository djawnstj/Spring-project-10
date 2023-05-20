package org.example;

import org.assertj.core.api.Assertions;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class ReflectionTest {
    private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        log.debug("beans[{}]", beans);
    }

    @Test
    public void showClass() {
        Class<User> clazz = User.class;
        log.debug(clazz.getName());
        log.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        log.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        log.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    /**
     * 힙 영역에 로드되어있는 객체를 가져오는 방법
     */
    @Test
    public void load() throws ClassNotFoundException {
        // 1
        Class<User> clazz = User.class;

        // 2
        User user = new User("id", "name");
        Class<? extends User> clazz2 = user.getClass();

        // 3
        Class<?> clazz3 = Class.forName("org.example.model.User");

        log.debug("clazz: [{}]", clazz);
        log.debug("clazz2: [{}]", clazz2);
        log.debug("clazz3: [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz == clazz3).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflection = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(anno -> beans.addAll(reflection.getTypesAnnotatedWith(anno)));
        return beans;
    }

}
