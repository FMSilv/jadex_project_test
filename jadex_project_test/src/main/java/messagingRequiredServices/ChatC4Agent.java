package messagingRequiredServices;

import jadex.bridge.FactoryFilter;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.factory.IComponentFactory;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.micro.MicroAgentFactory;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Description;

@Description("This agent declares a required clock service.")
@Agent
public class ChatC4Agent {

	@AgentFeature
	IRequiredServicesFeature requiredServicesFeature;
	@Agent
	IInternalAccess agent;
	
	@AgentBody
	public void executeBody() {
		IFuture<IComponentFactory>  factory = SServiceProvider.getService(agent, 
			    IComponentFactory.class, RequiredServiceInfo.SCOPE_PLATFORM, new FactoryFilter(MicroAgentFactory.FILETYPE_MICROAGENT));

			factory.addResultListener(new DefaultResultListener<IComponentFactory>()
			{
			    public void resultAvailable(IComponentFactory result)
			    {
			        System.out.println("Found: "+result);
			    }
			});
	}
	
}
