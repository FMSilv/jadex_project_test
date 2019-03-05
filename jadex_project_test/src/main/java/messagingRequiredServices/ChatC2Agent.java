package messagingRequiredServices;

import java.util.Date;

import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Description("This agent declares a required clock service.")
@Agent
@RequiredServices
({
		@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM))
})
public class ChatC2Agent {

	@AgentFeature
	IRequiredServicesFeature requiredServicesFeature;
	
	@AgentBody
	public void executeBody() {
		
		IFuture<IClockService> fut = requiredServicesFeature.getRequiredService("clockservice");
		fut.addResultListener(new DefaultResultListener<IClockService>()
		{
		  public void resultAvailable(IClockService cs)
		  {
		    System.out.println("Time for a chat, buddy: "+new Date(cs.getTime()));
		  }
		});

	}
	
}
