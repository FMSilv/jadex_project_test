package messagingWithIMessageService;

import jadex.bdiv3.IBDIAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanPrecondition;
import jadex.bdiv3.annotation.ServiceTrigger;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.message.IMessageService;
import jadex.commons.future.IResultListener;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.commons.gui.future.SwingResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@ProvidedServices
({
//	@ProvidedService(name="registryService", type=IRegistryService.class),
	@ProvidedService(name="transser", type=IMessageService.class, implementation=@Implementation(IBDIAgent.class))
})
public class UserBDI {

    @Agent
    protected IInternalAccess agent;
	
    @AgentBody
    public void body()
    {
    	sendMessage("ThirdAgentTestBDI");
    }
    
    
    public void sendMessage(String receiver) {
        SServiceProvider.getServices(agent, IChatService.class, RequiredServiceInfo.SCOPE_PLATFORM)
        .addResultListener(new IntermediateDefaultResultListener<IChatService>()
        {
	        public void intermediateResultAvailable(IChatService ts)
	        {
	            ts.sendMessage(agent.getComponentIdentifier().getLocalName(), "ThirdAgentTest", "dog")
	                .addResultListener(new SwingResultListener<String>(new IResultListener<String>()
	            {
	                public void resultAvailable(String response) 
	                {
	                	if(null!=response) {
		                	System.out.println("Message Received: "+ response);
	                	}
	                }
	
	                public void exceptionOccurred(Exception exception)
	                {
	                    exception.printStackTrace();
	                }
	            }));
	        }
        });
    } 
    
    
    
	@Plan(trigger=@Trigger(service=@ServiceTrigger(type=IMessageService.class)))
	public class TranslatePlan
	{
	    @PlanPrecondition
	    public boolean checkPrecondition(Object[] params)
	    {
	    	return params[1].equals(agent.getComponentIdentifier().getLocalName());
	    }
	    
	    @PlanBody
	    public String body(Object[] params)
	    {
	    	System.out.println("Sender: "+params[0]+"/ Receiver: "+params[1]+"/ Message: "+params[2]);
	    	return "Chegou a UserBDI";
	    }
	}
    
    
}
