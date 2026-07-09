package net.likelion.bebc25.spring.aop.staticproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 컨테이너가 알려주는 앱 설정 클래스
@Configuration
public class AppConfig {
    //스프링이 관리하는 Bean으로 등록
    @Bean // 메서드명 car가 빈의 이름이 됨
    public Car car(){
        Car target = new HybridCar();
        Car logProxy = new LogProxy(target);
        return logProxy;
    }

    @Bean // 메서드명 driver
    public Driver driver(Car car){
        return new Driver(car);
    }
}
