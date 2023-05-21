package org.example.di;

import org.assertj.core.api.Assertions;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {
    public static final Logger log = LoggerFactory.getLogger(BeanFactoryTest.class);

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);

        beanFactory = new BeanFactory(preInstantiatedClazz);

    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();

        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }

    @Test
    public void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        UserService service = beanFactory.getBean(UserService.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
        assertThat(userController.getUserService()).isEqualTo(service);
    }
}