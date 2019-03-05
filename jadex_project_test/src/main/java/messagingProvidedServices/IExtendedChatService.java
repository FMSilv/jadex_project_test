package messagingProvidedServices;

import jadex.commons.future.IFuture;

public interface IExtendedChatService extends IChatService{

	public IFuture<UserProfileD3> getUserProfile();
	
}
