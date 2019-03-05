package messagingRequiredServices;

import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.bridge.service.types.cms.IComponentDescription;
import jadex.bridge.service.types.cms.IComponentManagementService;
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
		@RequiredService(name="cms", type=IComponentManagementService.class, 
		binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM))
})
public class ChatC3Agent {

	@AgentFeature
	IRequiredServicesFeature requiredServicesFeature;
	
	@AgentBody
	public void executeBody() {
		IFuture<IComponentManagementService> fut = requiredServicesFeature.getRequiredService("cms");
		fut.addResultListener(new DefaultResultListener<IComponentManagementService>()
		{
		  public void resultAvailable(IComponentManagementService cs)
		  {
			  IFuture<IComponentDescription[]> componentDescriptions = cs.getComponentDescriptions();
			  componentDescriptions.addResultListener(new DefaultResultListener<IComponentDescription[]>()
			  {
				public void resultAvailable(IComponentDescription[] result) {
					for(IComponentDescription iComponentDescription : result) {
						System.out.println(iComponentDescription);
					}
				}
			  });
		      
//		    System.out.println("Time for a chat, buddy: "+new Date(cs.getTime()));
		  }
		});

	}
	
}
