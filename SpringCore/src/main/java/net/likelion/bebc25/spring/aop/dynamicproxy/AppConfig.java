package net.likelion.bebc25.spring.aop.dynamicproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

// 스프링 컨테이너가 알려주는 앱 설정 클래스
@Configuration
public class AppConfig {
    //스프링이 관리하는 Bean으로 등록
    @Bean // 메서드명 car가 빈의 이름이 됨
    public Car car(){
        // 동적 프록시 생성
        Car target = new HybridCar();
        Car proxyCar = (Car)Proxy.newProxyInstance(Car.class.getClassLoader(), //클래스 로더
                new Class[]{Car.class}, // 구현할 인터페이스 목록
                new TimeCheckInvocationHandler(target));
        return proxyCar;
    }

    @Bean // 메서드명 driver
    public Driver driver(Car car){
        return new Driver(car);
    }
}
