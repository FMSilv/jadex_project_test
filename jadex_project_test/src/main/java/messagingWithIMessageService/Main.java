package messagingWithIMessageService;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("messagingWithIMessageService.TranslationBDI.class");
        config.addComponent("messagingWithIMessageService.ThirdAgentTestBDI.class");
        config.addComponent("messagingWithIMessageService.UserBDI.class");
        
        Starter.createPlatform(config).get();
	}

}
