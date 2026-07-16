package net.likelion.bebc25.intellij;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {
    @Autowired
    private Car car;

    // DI
    Driver(Car car){
        System.out.println("Constructor Injection 호출");
        this.car = car;
    }

    public void driveCar(int speed){
        car.startEngine();
        car.drive();
        car.stopEngine();
    }
}
