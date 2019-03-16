package sendServiceMessage;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanPrecondition;
import jadex.bdiv3.annotation.ServiceTrigger;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.types.clock.IClockService;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentArgument;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Description("This agent declares a required clock service.")
@Agent

@Arguments
(value={
	@Argument(name="message", description="sendServiceMessage.Chat1Agent", clazz=String.class, defaultvalue="\"defaultValue1\"")
})
@RequiredServices
({
		@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM)),
		@RequiredService(name="chatservices", type=IChatService.class, multiple=true, binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM, dynamic=true))
})
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatService.class)))
public class Chat1Agent {
	
	@AgentArgument
	public String message = "messageChat1";
	
	private String serviceMessage;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@AgentCreated
	public void init()
	{
		
	}
	
	@Plan(trigger=@Trigger(service=@ServiceTrigger(type=IChatService.class)))
	public class TranslatePlan
	{
	    @PlanPrecondition
	    public boolean checkPrecondition()
	    {
	    	return true;
	    }
	    
	    @PlanBody
	    public void pringPlanTest()
	    {
	    	System.out.println("ENTREI NO PLANO!!!!!");
	    }
	}
	
	
	public String getServiceMessage() {
		return serviceMessage;
	}

	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}
	
	
	
	

//	IExternalAccess agent;
//	
//	@AgentFeature
//	IRequiredServicesFeature requiredServicesFeature;
	
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
	
//	@AgentBody
//	public void executeBody() {
//	    final String text = "";
//	    agent.scheduleStep(new IComponentStep<Void>()
//	    {
//	        public IFuture<Void> execute(IInternalAccess ia)
//	        {
//	            IFuture<Collection<IChatService>> chatservices = ia.getComponentFeature(IRequiredServicesFeature.class).getRequiredServices("chatservices");
//	            chatservices.addResultListener(new DefaultResultListener<Collection<IChatService>>()
//	            {
//	                public void resultAvailable(Collection<IChatService> result)
//	                {
//	                	for(IChatService iChatService : result)
//	                    {
//	                		if(!text.isEmpty())
//	                		{
//	                            iChatService.message(agent.getComponentIdentifier().getLocalName(), text);
//	                		}
//	                    }
//	                }
//	            });
//	            return IFuture.DONE;
//	        }
//	    });
//	}

	
}
