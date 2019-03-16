package sendServiceMessage;

import java.util.Collection;

import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;


public class MessageManager {

	protected String sender;
	
	protected String receiver;
	
	protected String message;

	public MessageManager() {
		
	}
	
	public MessageManager(final IExternalAccess agent, final String message) {
		
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
                    		iChatService.message(agent.getComponentIdentifier().getLocalName(), message);
                        }
                    }
                });
                return IFuture.DONE;
            }
        });
		
	}

	public void addMessage(String sender, String receiver, String message) {
		setSender(sender);
		setReceiver(receiver);
		setMessage(message);
		
		System.out.println(getSender());
		System.out.println(getReceiver());
		System.out.println(getMessage());
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageManager [sender=" + sender + ", receiver=" + receiver + ", message=" + message + "]";
	}
	
}
