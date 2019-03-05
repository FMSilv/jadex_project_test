package initiateAgentFromAnotherAgent;

import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.AgentService;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Agent
@Description("The translation agent A1. <br> Empty agent that can be loaded and started.")
@RequiredServices
({
	@RequiredService(type= IComponentManagementService.class, name = "cms", binding=@Binding(scope="platform"))
})
@Plans(@Plan(body=@Body(CreateAgentPlan.class)))
public class CreateAgentBDI {

	@AgentFeature 
	protected IBDIAgentFeature bdiFeature;
	
	@AgentService
	private IComponentManagementService cms;
	
	@AgentBody
	public void body()
	{
		System.out.println("Agent CreateAgentBDI initiated!");
		bdiFeature.adoptPlan(new CreateAgentPlan(cms));
	}
	
}
