package com.gmail.gor;

import org.springframework.stereotype.Component;

@Component
public class Wheel4 {
    private boolean spinning= false;
    public boolean isSpinning() {
        return spinning;
    }

    public void setSpinning(boolean spinning) {
        this.spinning = spinning;
    }
    public void spin(){
        spinning= true;
    }
}
