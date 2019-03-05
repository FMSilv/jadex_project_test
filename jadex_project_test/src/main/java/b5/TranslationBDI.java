package b5;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bridge.component.IExecutionFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Description;

@Agent
@Description("The translation agent A1. <br> Empty agent that can be loaded and started.")
@Plans(@Plan(body=@Body(TranslationPlan.class)))
public class TranslationBDI {

	@Belief
	public boolean context = true;
	
	@AgentFeature 
	protected IBDIAgentFeature bdiFeature;
	
	@AgentFeature
	protected IExecutionFeature execFeature;
	
	@AgentBody
	public void body()
	{
		System.out.println("Agent b5 initiated!");
		
		try
		{
		  bdiFeature.adoptPlan(new TranslationPlan());
		  execFeature.waitForDelay(1000).get();
		  context = false;
		  System.out.println("context set to false");
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		}
		
	}
	
}
