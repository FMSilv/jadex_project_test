package messagingPlanTriggeredByService;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("messagingPlanTriggeredByService.TranslationBDI.class");
        config.addComponent("messagingPlanTriggeredByService.UserBDI.class");
        
        Starter.createPlatform(config).get();
	}

}
