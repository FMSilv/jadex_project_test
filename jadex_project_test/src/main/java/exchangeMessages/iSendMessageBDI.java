package exchangeMessages;

import jadex.bdiv3.runtime.IGoal;
import jadex.bdiv3.runtime.impl.GoalFailureException;
import jadex.bdiv3x.runtime.IMessageEvent;
import jadex.bridge.IInternalAccess;
import jadex.bridge.fipa.SFipa;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Description;
import jadex.tools.comanalyzer.Message;

@Agent
@Description("The translation agent A1. <br> Empty agent that can be loaded and started.")
public class iSendMessageBDI {

//	IInternalAccess internalAgent;
//	
//    public void body()
//    {        
//        System.out.println("AgentIdentifier = "+this.getAgentIdentifier());
//        internalAgent.getComponentIdentifier().getName()AgentIdentifier name = (AgentIdentifier)this.getAgentIdentifier();
//        // TODO to think more on it    
//        System.out.println("Sender created ("+name+")");
//        AgentIdentifier aid = createAgent();
//            if(aid != null) {
//                System.out.println("Agent created...."+aid);
//                sendCmd(new AgentIdentifier[]{aid}, "master_platform", name.getPlatformName());
//            }
//    }
//    private AgentIdentifier createAgent() {
//        AgentIdentifier aid = null;
//        try{
//            String remote_platform = "nirvana-450a5dc";
//            AgentIdentifier remote_ams = new AgentIdentifier("ams@"+remote_platform,new String[]{"nio-mtp://"+remote_platform+":9876"});
//            IGoal create_agent = createGoal("ams_create_agent");        
//            create_agent.getParameter("ams").setValue(remote_ams);
//            create_agent.getParameter("type").setValue("mas.test.Receiver");
//            create_agent.getParameter("name").setValue("myReader");
//            create_agent.getParameter("start").setValue(true);
//            dispatchSubgoalAndWait(create_agent);
//            aid = (AgentIdentifier) create_agent.getParameter("agentidentifier").getValue();
//        }catch(GoalFailureException gfe) {
//            System.out.println("can't create receiver");
//        }
//        return aid;
//    }
//    private void sendCmd(AgentIdentifier[] receivers, String name, String content) {
//        try {
//            IMessageEvent msg = createMessageEvent("request_carries");
//            msg.getParameterSet(SFipa.RECEIVERS).addValues(receivers);
//            Message msg_content = new Message();
//            msg_content.setK(name);
//            msg_content.setV(content);
//            msg.setContent(msg_content);
//            sendMessage(msg);
//        }catch(Exception e) {
//            System.err.println("failing to send command");
//        }
//    }
	
}
