package messagingProvidedServices;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;

import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;

public class ChatGuiD3 extends ChatGuiD2{

	public ChatGuiD3(final IExternalAccess agent) throws HeadlessException {
		super(agent);
		
		JButton buttonProfiles = new JButton("Profiles");
		getContentPane().add(buttonProfiles, BorderLayout.NORTH);
		
		buttonProfiles.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
				agent.scheduleStep(new IComponentStep<Void>()
				{
				  public IFuture<Void> execute(IInternalAccess ia)
				  {
					  IFuture<Collection<IExtendedChatService>> chatservices = ia.getComponentFeature(IRequiredServicesFeature.class).getRequiredServices("chatservices");
					  chatservices.addResultListener(new DefaultResultListener<Collection<IExtendedChatService>>()
				    {
						public void resultAvailable(Collection<IExtendedChatService> result) {
		                	for(IExtendedChatService iExtendedChatService : result)
		                    {
		                		addMessage(iExtendedChatService.getUserProfile().get().toString());
		                    }
						}
				    });
					return IFuture.DONE;
				  }
				});
		    }
		});
		
	}


	
	private static final long serialVersionUID = 1L;



}
