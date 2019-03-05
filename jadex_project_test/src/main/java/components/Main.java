package components;

import jadex.base.PlatformConfiguration;
import jadex.base.RootComponentConfiguration;
import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.CreationInfo;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.SUtil;
import jadex.commons.future.IFuture;
import jadex.commons.future.ITuple2Future;

public class Main {

    public static void main(String[] args) {
        
    	/** Plataform configuring **/
    	PlatformConfiguration platformConfig = PlatformConfiguration.getDefault();
    	platformConfig.setDebugFutures(true); // enables stacktraces of exceptions
    	platformConfig.setPlatformName("jadexplatform"); // set plataform name
    	RootComponentConfiguration rootConfig = platformConfig.getRootConfig();
    	rootConfig.setGui(true); 		// run with/without GUI
    	rootConfig.setCli(true); 		// run with/without CLI
    	rootConfig.setLogging(true); 	// enables the printing of info and warning messages in addition to severe messages.
    	rootConfig.setWelcome(false); 	// do not print welcomemessage on startup
    	rootConfig.setPrintPass(false); // do not print password message on startup
    	// set available component kernels
    	rootConfig.setKernels(RootComponentConfiguration.KERNEL.micro,
                RootComponentConfiguration.KERNEL.component,
                RootComponentConfiguration.KERNEL.v3);
    	rootConfig.setAwareness(false); // disable plataform awareness
    	// set awareness mechanisms
    	rootConfig.setAwaMechanisms(RootComponentConfiguration.AWAMECHANISM.broadcast, 
    		    RootComponentConfiguration.AWAMECHANISM.relay);
    	rootConfig.setUsePass(false); // disable password protection (Caution!)
    	
    	IExternalAccess platform = Starter.createPlatform(platformConfig).get();
//    	Starter.createPlatform(platformConfig).get();
    	
    	/** Obtaining the CMS **/
    	IFuture<IComponentManagementService> fut = SServiceProvider.getService(platform, IComponentManagementService.class);
    	IComponentManagementService cms = fut.get();
        
    	/** Passing argumnts to the agent **/
    	CreationInfo parameters = new CreationInfo(SUtil.createHashMap(new String[]{"argumentTest", "secondArgumentTest"}, new Object[]{"Harald", "Filipe"}));
    	
    	/** Starting the component **/
    	ITuple2Future<IComponentIdentifier,java.util.Map<java.lang.String,java.lang.Object>> iTupleFut = cms.createComponent("componentBDIAgent", "components.componentBDI.class", parameters);
    	IComponentIdentifier cid = iTupleFut.getFirstResult();
    	System.out.println("Started component: " + cid);
    	
    	/** Call this to destroy conponents **/
//    	Map<String,Object> results = cms.destroyComponent(cid).get();
    	
    }
	
}
