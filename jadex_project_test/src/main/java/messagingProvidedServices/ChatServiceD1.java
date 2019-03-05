package messagingProvidedServices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.annotation.ServiceStart;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.ExceptionDelegationResultListener;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;

@Service
public class ChatServiceD1 implements IChatService{

	@ServiceComponent
	IInternalAccess agent;
	@ServiceComponent
	IRequiredServicesFeature requiredServicesFeature;
	
	IClockService clock;
	DateFormat format;
	
	public void message(String sender, String text) {
		System.out.println(agent.getComponentIdentifier().getLocalName()+" received at \""+new Date(clock.getTime())+"\" from: " +sender+" message: "+text);
	}
	
	@ServiceStart
	public IFuture<Void> startService() {
		format = new SimpleDateFormat("hh:mm:ss");
		final Future<Void> ret = new Future<Void>();
		IFuture<IClockService> fut = requiredServicesFeature.getRequiredService("clockservice");
		fut.addResultListener(new ExceptionDelegationResultListener<IClockService, Void>(ret)
		{
		    public void customResultAvailable(IClockService result)
		    {
		        clock = result;
		        ret.setResult(null);
		    }
		});
		return ret;
	}

}
