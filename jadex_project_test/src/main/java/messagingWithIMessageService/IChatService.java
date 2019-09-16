package messagingWithIMessageService;

import jadex.commons.future.IFuture;

public interface IChatService {

	public IFuture<String> sendMessage(String sender, String receiver, String message);
	
}
