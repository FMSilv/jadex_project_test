package d5;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.impl.PlanFailureException;
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
	  execFeature.waitForDelay(3000, new IComponentStep<Void>()
	  {
	    public IFuture<Void> execute(IInternalAccess ia)
	    {
	      wordtable.put("bugger", "Flegel");
	      return IFuture.DONE;
	    }
	  });

	  String eword = "bugger";
	  String gword = (String)bdiFeature.dispatchTopLevelGoal(new TranslateGoal(eword)).get();
	  System.out.println("Translated: "+eword+" "+gword);
	}
	
	@Plan(trigger=@Trigger(goals=TranslateGoal.class))
	protected String translate(String eword)
	{
	  String ret = wordtable.get(eword);
	  if(ret==null)
	    throw new PlanFailureException();
	  return ret;
	}
	
}
