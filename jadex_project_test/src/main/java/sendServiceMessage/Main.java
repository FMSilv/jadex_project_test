package sendServiceMessage;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("iMessageEvent1.iSendMessageBDI.class");
        config.addComponent("iMessageEvent1.iReceiveMessageBDI.class");
        
        Starter.createPlatform(config).get();
	}

}
