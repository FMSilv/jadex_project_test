package messagingPlanTriggeredByService;

import jadex.commons.future.IFuture;

public interface IChatService {

	  public IFuture<String> sendMessage(String message, String sender);
	
}
