package initiateAgentFromAnotherAgent;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.service.types.cms.CreationInfo;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.SUtil;
import jadex.commons.future.ITuple2Future;

@Plan
public class CreateAgentPlan {
	 
	private IComponentManagementService cms;
	
	  public CreateAgentPlan(IComponentManagementService cms)
	  {
		  this.cms = cms;
	  }

	  @PlanBody
	  public void createAgent()
	  {
	    	/** Passing argumnts to the agent **/
	    	CreationInfo parameters = new CreationInfo(SUtil.createHashMap(new String[]{"argumentTest", "secondArgumentTest"}, new Object[]{"Harald", "Filipe"}));
	    	
	    	/** Starting the component **/
	    	ITuple2Future<IComponentIdentifier,java.util.Map<java.lang.String,java.lang.Object>> iTupleFut = this.cms.createComponent("newAgentBDIAgent", "initiateAgentFromAnotherAgent.NewAgentBDI.class", parameters);
	    	IComponentIdentifier cid = iTupleFut.getFirstResult().getParent();
	    	System.out.println("Started component: " + cid);
	  }
	
}
