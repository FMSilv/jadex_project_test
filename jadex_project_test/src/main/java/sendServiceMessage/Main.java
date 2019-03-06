package sendServiceMessage;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("sendServiceMessage.Chat1Agent.class");
        config.addComponent("sendServiceMessage.Chat2Agent.class");
        
        Starter.createPlatform(config).get();
	}

}
