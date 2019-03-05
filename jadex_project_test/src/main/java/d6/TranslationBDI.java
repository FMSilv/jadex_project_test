package d6;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalMaintainCondition;
import jadex.bdiv3.annotation.GoalTargetCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.model.MProcessableElement.ExcludeMode;
import jadex.bridge.IComponentStep;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IExecutionFeature;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.AgentFeature;

@Agent
public class TranslationBDI {

	@AgentFeature
	protected IBDIAgentFeature bdiFeature;

	@AgentFeature
	protected IExecutionFeature execFeature;
	
	@Belief
	protected Map<String, String> wordtable;
	
	@AgentCreated
	public void init()
	{
	    this.wordtable = new HashMap<String, String>();
	    wordtable.put("coffee", "Kaffee");
	    wordtable.put("milk", "Milch");
	    wordtable.put("cow", "Kuh");
	    wordtable.put("cat", "Katze");
	    wordtable.put("dog", "Hund");
	}
	
	@AgentBody
	public void body()
	{
		  bdiFeature.dispatchTopLevelGoal(new MaintainStorageGoal());

		  wordtable = new HashMap<String, String>();
		  wordtable.put("milk", "Milch");
		  wordtable.put("cow", "Kuh");
		  wordtable.put("cat", "Katze");
		  wordtable.put("dog", "Hund");


		  execFeature.repeatStep(0, 2000, new IComponentStep<Void>()
		    {
		      int cnt = 0;
		      public IFuture<Void> execute(IInternalAccess ia)
		      {
		        wordtable.put("eword_#"+cnt, "gword_#"+cnt);
		        cnt++;
		        System.out.println("wordtable: "+wordtable);
		        return IFuture.DONE;
		      }
		    });
	}
	
	@Goal(excludemode=ExcludeMode.Never)
	public class MaintainStorageGoal {
		  @GoalMaintainCondition(beliefs="wordtable")
		  protected boolean maintain()
		  {
		    return wordtable.size()<=4;
		  }

		  @GoalTargetCondition(beliefs="wordtable")
		  protected boolean target()
		  {
		    return wordtable.size()<3;
		  }
	}
	
	@Plan(trigger=@Trigger(goals=MaintainStorageGoal.class))
	protected void removeEntry()
	{
	    String key = wordtable.keySet().iterator().next();
	    String val = wordtable.remove(key);
	    System.out.println("removed: "+key+" "+val+" "+ wordtable);
	}
	
}
