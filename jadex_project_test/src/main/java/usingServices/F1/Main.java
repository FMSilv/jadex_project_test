package usingServices.F1;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("usingServices.F1.TranslationBDI.class");
        config.addComponent("usingServices.F1.UserBDI.class");
        
        Starter.createPlatform(config).get();
	}

}
