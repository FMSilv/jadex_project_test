package messagingProvidedServices;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

//        config.addComponent("messagingProvidedServices.ChatD1Agent.class");
//        config.addComponent("messagingProvidedServices.ChatD2Agent.class");
        config.addComponent("messagingProvidedServices.ChatD3Agent.class");

        Starter.createPlatform(config).get();
	}

}
