package iMessageEvent1;

import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3x.runtime.IMessageEvent;
import jadex.bridge.fipa.SFipa;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Description;

@Agent
@Description("The translation agent A1. <br> Empty agent that can be loaded and started.")
public class iSendMessageBDI {

	@AgentFeature 
	protected IBDIAgentFeature bdiFeature;
	
	@AgentBody
	public void body()
	{
		System.out.println("Agent iSendMessageBDI initiated!");
		
		
//		  IMessageEvent me = createMessageEvent("query_ref");
//		  me.getParameterSet(SFipa.RECEIVERS).addValue(cid);
//		  // Set/change content if necessary
//		  me.getParameter(SFipa.CONTENT).setValue("ping 2"); 
//		  sendMessage(me);
		
//		Map msg = new HashMap();
//		msg.put(SFipa.CONTENT, content);
//		msg.put(SFipa.PERFORMATIVE, SFipa.QUERY_IF);
//		msg.put(SFipa.CONVERSATION_ID, "someuniqueid");
//		msg.put(SFipa.RECEIVERS, new IComponentIdentifier[]{receiver});
//		msg.put(SFipa.SENDER, getComponentIdentifier());
//		agent.sendMessage(msg, SFipa.FIPA_MESSAGE_TYPE);
		
	}
	
}
