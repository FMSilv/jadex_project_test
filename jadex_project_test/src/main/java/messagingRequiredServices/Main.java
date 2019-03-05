package messagingRequiredServices;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

    public static void main(String[] args) {
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

//        config.addComponent("messagingRequiredServices.ChatC1Agent.class");
//        config.addComponent("messagingRequiredServices.ChatC2Agent.class");
//        config.addComponent("messagingRequiredServices.ChatC3Agent.class");
        config.addComponent("messagingRequiredServices.ChatC4Agent.class");

        Starter.createPlatform(config).get();
    }
	
}
