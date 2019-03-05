package pingPong;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jadex.bridge.ComponentIdentifier;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IMessageFeature;
import jadex.bridge.component.IMessageHandler;
import jadex.bridge.fipa.SFipa;
import jadex.bridge.service.types.message.MessageType;
import jadex.commons.SUtil;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentArgument;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;

@Agent
@Description("A simple agent that sends pings to another agent and waits for replies.")
@Arguments
(value={
	@Argument(name="receiver", clazz=IComponentIdentifier.class, description="The component receiver of the ping target."), 
	@Argument(name="missed_max", clazz=int.class, description="Maximum number of allowed missed replies", defaultvalue="3"), 
	//@Argument(name="timeout", clazz=long.class, description="Timeout for reply", defaultvalue="1000"),
	@Argument(name="content", clazz=String.class, description="Ping message content", defaultvalue="\"ping\"")
})
public class PingBDI {

	@AgentArgument
	protected IComponentIdentifier receiver;
	@AgentArgument
	protected int missed_max;
	@AgentArgument
	protected String timeout;
	@AgentArgument
	protected String content;
	
	IExternalAccess iExternalAccess;
	
	protected int dif;
	protected Set sent;
	
	
	public IFuture<Void> executeBody(){
		final Future ret = new Future();
		this.sent = new HashSet();
		
		//Ping agent will start
		IComponentStep step = new IComponentStep()
		{
//			@Agent
//			IInternalAccess agent;
//			IMessageFeature msg = agent.getComponentFeature(IMessageFeature.class);
//			msg.sendMessage(mapwithmessagecontent, SFipa.FIPA_MESSAGE_TYPE);
			
			public IFuture<Void> execute(IInternalAccess ia){
				//After send "missed_max" if there is no answer, than terminate.
				if (PingBDI.this.dif > missed_max)
				{
					ia.getLogger().warning("Ping target does not respond: " + PingBDI.this.receiver);
					ret.setResult(null);
				}
				else
				{
					String convid = SUtil.createUniqueId(ia.getComponentIdentifier().getName());
					Map msg = new HashMap();
					msg.put("content", content);
					msg.put("performative", "query-if");
					msg.put("conversation_id", convid);
					msg.put("receivers", new IComponentIdentifier[] {PingBDI.this.receiver});
					PingBDI.this.dif += 1;
					PingBDI.this.sent.add(convid);
					
//					Map msg = new HashMap();
//					msg.put(SFipa.CONTENT, content);
//					msg.put(SFipa.PERFORMATIVE, SFipa.QUERY_IF);
//					msg.put(SFipa.CONVERSATION_ID, "someuniqueid");
//					msg.put(SFipa.RECEIVERS, new IComponentIdentifier[]{receiver});
//					msg.put(SFipa.SENDER, getComponentIdentifier());
//					agent.sendMessage(msg, SFipa.FIPA_MESSAGE_TYPE);
					
					IMessageFeature iMessageFeature = ia.getComponentFeature(IMessageFeature.class);
					iMessageFeature.sendMessage(msg, SFipa.FIPA_MESSAGE_TYPE);
//					iMessageFeature.sendMessageAndWait(me, mt, handler);
					
//					PingBDI.this.sendMessage(msg, SFipa.FIPA_MESSAGE_TYPE);
//					PingBDI.this.waitFor(timeout, this);
				}
				return IFuture.DONE;
			}
		};
//		if (this.receiver == null)
//		{
//			this.receiver = new ComponentIdentifier("Pong", iExternalAccess.getComponentIdentifier().getParent());
//		}
		iExternalAccess.scheduleStep(step);
		return ret;
	}


	public void messageArrived(Map msg, MessageType mt)
	{
		//If a message arrives, clear dif and missed messages.
		if (mt.equals(SFipa.FIPA_MESSAGE_TYPE))
		{
			String convid = (String)msg.get("conversation_id");
			if (this.sent.remove(convid))
			{
				this.dif = 0;
				this.sent.clear();
				System.out.println("[" + this + "] Just received a Pong!");
			}
		}
	}

}
