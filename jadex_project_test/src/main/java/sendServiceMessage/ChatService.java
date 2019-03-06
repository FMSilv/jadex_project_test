package sendServiceMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.annotation.ServiceStart;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.commons.gui.future.SwingExceptionDelegationResultListener;

@Service
public class ChatService implements IChatService{

	@ServiceComponent
	IInternalAccess agent;
	@ServiceComponent
	IRequiredServicesFeature requiredServicesFeature;
	
	IClockService clock;
	DateFormat format;
	
	MessageManager messageManager;
	
	@ServiceStart
	public IFuture<Void> startService() {
		final IExternalAccess exta = agent.getExternalAccess();
		format = new SimpleDateFormat("hh:mm:ss");
		final Future<Void> ret = new Future<Void>();
		IFuture<IClockService> clockservice = requiredServicesFeature.getRequiredService("clockservice");
		clockservice.addResultListener(new SwingExceptionDelegationResultListener<IClockService, Void>(ret)
		{
		    public void customResultAvailable(IClockService result)
		    {
		    	String message = "Mensagem a enviar";
		        clock = result;
		        messageManager = createMessageManager(exta, message);
		        ret.setResult(null);
		    }
		});
		return ret;
	}
	
	protected MessageManager createMessageManager(IExternalAccess agent, String message) {
		return new MessageManager(agent, message);
	}
	
	public void message(String sender, String message) {
		String receiver = agent.getComponentIdentifier().getLocalName();
		messageManager.addMessage(sender, receiver, message);
	}
	
}
