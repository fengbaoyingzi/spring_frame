package com.example.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(1)
public class MonitorController {

    @Pointcut("execution (public * com.example.controller.HomeController.*(..))")
    public void homeControllerAspect(){}

    @Before("homeControllerAspect()")
    public void beforePoint(JoinPoint point) throws ClassNotFoundException, NoSuchMethodException {
        //首先获取切点的类和方法名
        String className = point.getTarget().getClass().getName();
        Signature signature = point.getSignature();
        String methodName = signature.getName();
        System.out.println("切点类名为:"+className);
        System.out.println("方法名为:"+methodName);

        Object[] args = point.getArgs();
        Map<String,Object> map = new HashMap<String, Object>();
        // 获取参数名
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] paramNames = methodSignature.getParameterNames();
        for(int i=0;i<paramNames.length;i++){
            map.put(paramNames[i],args[i]);
        }
        //此处需要注意根据方法名反射获取方法，如果有重载的情况会出问题
        System.out.println("");
        List<Object> list = Arrays.asList();
        String ClassName = point.getTarget().getClass().getName();
        Class c1 = Class.forName(ClassName);
        Method method = c1.getMethod("hello");
        System.out.println(method.getName());
    }
}
