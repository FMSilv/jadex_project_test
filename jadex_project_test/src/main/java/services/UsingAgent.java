package services;

import jadex.bridge.service.RequiredServiceInfo;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentService;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@RequiredServices({
    @RequiredService(name="sumService", type=ISumService.class, 
    binding=@Binding(scope=RequiredServiceInfo.SCOPE_GLOBAL))
})
@Agent
public class UsingAgent {

	@AgentService
	private ISumService sumService;
	
}
