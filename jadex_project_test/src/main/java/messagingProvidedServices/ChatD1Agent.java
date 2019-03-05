package messagingProvidedServices;

import java.util.Collection;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Description("This agent declares a required clock service.")
@Agent
@RequiredServices
({
		@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM)),
		@RequiredService(name="chatservices", type=IChatService.class, multiple=true, binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM, dynamic=true))
})
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatServiceD1.class)))
public class ChatD1Agent {

	
	IInternalAccess agent;
	@AgentFeature
	IRequiredServicesFeature requiredServicesFeature;
	
	@AgentBody
	public void executeBody() {
		
		IFuture<Collection<IChatService>> chatservices = requiredServicesFeature.getRequiredServices("chatservices");
		chatservices.addResultListener(new DefaultResultListener<Collection<IChatService>>()
		{
			public void resultAvailable(Collection<IChatService> result) {
                for(IChatService iChatService : result) {
                	iChatService.message(agent.getComponentIdentifier().getName(), "Test message Hello!");
//                	iChatService.message("ChatD1Agent", "Test message Hello!");
                }
			}
		});

	}
	
}
