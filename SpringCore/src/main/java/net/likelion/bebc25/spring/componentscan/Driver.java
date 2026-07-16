package net.likelion.bebc25.spring.componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Driver {
    @Autowired
    private Car car;

//    Driver(){ // 기본생성자 메서드 오버로딩
//        System.out.println("Driver 기본 생성자 호출됨");
//    }
//    // DI
//    @Autowired
//    Driver(@Qualifier("gasolineCar") Car car){ // 필요한 빈을 직접 지정하는 방식
//        System.out.println("Constructor Injection: " + car);
//        this.car = car;
//    }
//    @Autowired
//    public void setCar(Car car){
//        System.out.println("Setter Injection 호출됨");
//        this.car = car;
//    }

    public void driveCar(int maxSpeed){
        car.startEngine();
        car.drive();
        car.stopEngine();
    }

}
