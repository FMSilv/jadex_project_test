package iMessageEvent;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

    public static void main(String[] args) {
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("iMessageEvent.iMessageBDI.class");
        Starter.createPlatform(config).get();
    }
	
}
