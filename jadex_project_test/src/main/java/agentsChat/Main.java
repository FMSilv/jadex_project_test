package agentsChat;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
        PlatformConfiguration   config  = PlatformConfiguration.getDefaultNoGui();

        config.addComponent("agentsChat.Chat1Agent.class");
        config.addComponent("agentsChat.Chat2Agent.class");
        
        Starter.createPlatform(config).get();
//        BpmnEditor.main(null);
	}

}
