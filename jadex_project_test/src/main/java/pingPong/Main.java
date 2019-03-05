package pingPong;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
		
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("pingPong.PingBDI.class");
        config.addComponent("pingPong.PongBDI.class");
        Starter.createPlatform(config).get();

	}

}
