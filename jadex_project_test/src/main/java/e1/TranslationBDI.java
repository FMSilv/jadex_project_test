package e1;

import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;

@Agent
public class TranslationBDI {

	@AgentFeature
	protected IBDIAgentFeature bdiFeature;
	
	@Capability
	protected TranslationCapability capability = new TranslationCapability();
	
	@AgentBody
	public void body(){
		System.out.println("Agent e1 created!");
		String eword = "dog";
		String gword = (String) bdiFeature.dispatchTopLevelGoal(capability.new Translate(eword)).get();
		System.out.printf("Translating %s to %s", eword, gword);
	}
	
}
