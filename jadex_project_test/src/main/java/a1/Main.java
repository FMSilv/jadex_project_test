package a1;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

    public static void main(String[] args) {
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("a1.TranslationBDI.class");
        Starter.createPlatform(config).get();
    }
	
}
