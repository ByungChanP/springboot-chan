package net.likelion.bebc25.spring.di.setter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 컨테이너가 알려주는 앱 설정 클래스
@Configuration
public class AppConfig {
    //스프링이 관리하는 Bean으로 등록
    @Bean // 메서드명 car가 빈의 이름이 됨
    public Car car(){
//        return new GasolineCar();
        return new HybridCar();
    }

    @Bean // 메서드명 driver
    public Driver driver(Car car){
        Driver driver = new Driver();
        driver.setCar(car); //Setter Injection
        return driver;
    }
}
