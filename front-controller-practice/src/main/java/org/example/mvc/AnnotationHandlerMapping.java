package org.example.mvc;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackages;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... baseBPackage) {
        basePackages = baseBPackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackages);

        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzesWithControllerAnnotation.forEach(clazz ->
                // @Controller 애노테이션이 붙은 클래스들의 메서드를 확인하며 @RequestMapping 애노테이션을 확인하기 위해
                Arrays.stream(clazz.getDeclaredMethods())
                        .forEach(method -> {
                            RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);

                            Arrays.stream(getRequestMethods(requestMapping))
                                    .forEach(requestMethod ->
                                            handlers.put(new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, method))
                                    );
                        })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
