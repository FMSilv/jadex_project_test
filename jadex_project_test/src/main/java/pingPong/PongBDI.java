package pingPong;

import java.util.Map;

import jadex.bdiv3x.runtime.IMessageEvent;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IMessageFeature;
import jadex.bridge.fipa.SFipa;
import jadex.bridge.service.types.message.MessageType;
import jadex.micro.annotation.Agent;

@Agent
public class PongBDI {


	IInternalAccess agent;
	
//	public void messageArrived(Map msg, MessageType mt){
//		// Get the message performative
//		String perf = (String)msg.get("performative");
//		switch (perf) {
//			case "query-if":
//			case "query-ref":
//				//Check if the message contains a 'ping'
//				if(msg.get("content").equals("ping"))
//				{
//				    IMessageEvent me = (IMessageEvent) getInitialEvent();
//				    String message = (String) me.getContent();
//				    System.out.println("Message : " + message);
//
//				    IMessageEvent rep = me.createReply("inform","I'm fine, thank you!");
//				    sendMessage(rep);
//					
//					
//					// Great! Message contains ping. Reply with Pong.
//					reply.put("content", "pong");
//					reply.put("performative", "inform");
//					reply.put("sender", agent.getComponentIdentifier());
//					
//					IMessageFeature iMessageFeature = ia.getComponentFeature(IMessageFeature.class);
//					iMessageFeature.sendMessage(reply, mt);
//					System.out.println("[" + this + "] Just sended a Pong!");
//				}
//			break;
//			default:
//				agent.getLogger().severe("Unknown Performative");
//			break;
//			}
//		}

	
}
