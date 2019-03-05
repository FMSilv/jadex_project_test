package a1;

import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Description;

@Agent
@Description("The translation agent A1. <br> Empty agent that can be loaded and started.")
@Plans(@Plan(body=@Body(TranslationPlan.class)))
public class TranslationBDI {

	@AgentFeature 
	protected IBDIAgentFeature bdiFeature;
	
	@AgentBody
	public void body()
	{
		System.out.println("Agent a1 initiated!");
		bdiFeature.adoptPlan(new TranslationPlan());
	}
	
}
