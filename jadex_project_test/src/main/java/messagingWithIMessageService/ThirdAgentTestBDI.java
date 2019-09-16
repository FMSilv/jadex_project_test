package messagingWithIMessageService;

import jadex.bdiv3.IBDIAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanPrecondition;
import jadex.bdiv3.annotation.ServiceTrigger;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.types.message.IMessageService;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@ProvidedServices
(
	@ProvidedService(name="transser", type=IMessageService.class, implementation=@Implementation(IBDIAgent.class))
)
public class ThirdAgentTestBDI {

    @Agent
    protected IInternalAccess agent;
	
	
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
	    	return "Obigado pelo contacto "+params[0]+". Eu, o ThirdAgentTestBDI, agradeço.";
	    }
	}
}
