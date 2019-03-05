package messagingProvidedServices;

import jadex.bridge.IExternalAccess;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;

public class ChatServiceD3 extends ChatServiceD2 implements IExtendedChatService{

	UserProfileD3 profile;
	
	public IFuture<UserProfileD3> getUserProfile() {
		profile = new UserProfileD3((int)Math.random()*4);
		return new Future<UserProfileD3>(profile);
	}
	
	@Override
	protected ChatGuiD3 createGui(IExternalAccess agent) {
		return new ChatGuiD3(agent);
	}
}
