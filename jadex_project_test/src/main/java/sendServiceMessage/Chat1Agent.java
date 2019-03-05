package sendServiceMessage;

import java.util.Collection;

import agentsChat.IChatService;
import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
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
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatService.class)))
public class Chat1Agent {
	
	IExternalAccess agent;
	
	@AgentFeature
	IRequiredServicesFeature requiredServicesFeature;
	
//	@AgentCreated
//	public void executeBody() {
//		IFuture<IChatService> fut = requiredServicesFeature.getRequiredService("chatservices");
//		fut.addResultListener(new DefaultResultListener<IChatService>()
//		{
//			public void resultAvailable(IChatService result) {
//				result.message(agent.getComponentIdentifier().getName(), "messagem enviada!");
//			}
//		});
//	}
	
	@AgentBody
	public void executeBody() {
	    final String text = "";
	    agent.scheduleStep(new IComponentStep<Void>()
	    {
	        public IFuture<Void> execute(IInternalAccess ia)
	        {
	            IFuture<Collection<IChatService>> chatservices = ia.getComponentFeature(IRequiredServicesFeature.class).getRequiredServices("chatservices");
	            chatservices.addResultListener(new DefaultResultListener<Collection<IChatService>>()
	            {
	                public void resultAvailable(Collection<IChatService> result)
	                {
	                	for(IChatService iChatService : result)
	                    {
	                		if(!text.isEmpty())
	                		{
	                            iChatService.message(agent.getComponentIdentifier().getLocalName(), text);
	                		}
	                    }
	                }
	            });
	            return IFuture.DONE;
	        }
	    });
	}
	

	
}
