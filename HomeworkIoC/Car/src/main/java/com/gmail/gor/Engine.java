package com.gmail.gor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Engine {
    @Autowired
    private Wheel1 wheel1;
    @Autowired
    private Wheel2 wheel2;
    @Autowired
    private Wheel3 wheel3;
    @Autowired
    private Wheel4 wheel4;
    @Autowired
    private SteeringWheel steeringWheel;
    public void startTheCar(){
        wheel1.spin();
        wheel2.spin();
        wheel3.spin();
        wheel4.spin();
        steeringWheel.drive();
    }
}
