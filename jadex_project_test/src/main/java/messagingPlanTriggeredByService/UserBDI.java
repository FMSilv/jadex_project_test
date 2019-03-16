package messagingPlanTriggeredByService;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.IResultListener;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.commons.gui.future.SwingResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class UserBDI {

    @Agent
    protected IInternalAccess agent;
	
    @AgentBody
    public void body()
    {
    	sendMessage();
    }
    
    
    public void sendMessage() {
        SServiceProvider.getServices(agent, IChatService.class, RequiredServiceInfo.SCOPE_PLATFORM)
        .addResultListener(new IntermediateDefaultResultListener<IChatService>()
    {
        public void intermediateResultAvailable(IChatService ts)
        {
            ts.sendMessage("OLA", agent.getComponentIdentifier().getLocalName())
                .addResultListener(new SwingResultListener<String>(new IResultListener<String>()
            {
                public void resultAvailable(String gword) 
                {
                	System.out.println(gword);
//                                    tfg.setText(gword);
                }

                public void exceptionOccurred(Exception exception)
                {
                    exception.printStackTrace();
//                                    tfg.setText(exception.getMessage());
                }
            }));
        }
    });
    }
    
    
}
