package usingServices.F2;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("usingServices.F2.TranslationBDI.class");
        config.addComponent("usingServices.F2.UserBDI.class");
        
        Starter.createPlatform(config).get();
	}

}
