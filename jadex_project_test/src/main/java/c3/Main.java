package c3;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

    public static void main(String[] args) {
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("c3.ClockBDI.class");
        Starter.createPlatform(config).get();
    }
	
}
