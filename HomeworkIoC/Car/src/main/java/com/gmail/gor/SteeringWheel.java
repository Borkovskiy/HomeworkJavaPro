package com.gmail.gor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SteeringWheel {
    @Autowired
    private Wheel1 wheel1;
    @Autowired
    private Wheel2 wheel2;
    @Autowired
    private Wheel3 wheel3;
    @Autowired
    private Wheel4 wheel4;
    public void drive(){
        if(wheel1.isSpinning()==true&&wheel2.isSpinning()==true&&wheel3.isSpinning()==true&&wheel4.isSpinning()==true){
            System.out.println("u can drive the car");
        }
    }
}
