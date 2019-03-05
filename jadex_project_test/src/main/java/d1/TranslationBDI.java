package d1;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.AgentFeature;

@Agent
public class TranslationBDI {

	@AgentFeature
	protected IBDIAgentFeature bdiFeature;

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
		System.out.println("Agent d1 initiated!");
		String eword = "cat";
		TranslateGoal goal = (TranslateGoal)bdiFeature.dispatchTopLevelGoal(new TranslateGoal(eword)).get();
		System.out.println("Translated: "+eword+" "+goal.getGWord());
	}
	
	@Plan(trigger=@Trigger(goals=TranslateGoal.class))
	protected void translate(TranslateGoal goal) {
		String eword = goal.getEWord();
		String gword = wordtable.get(eword);
		goal.setGWord(gword);
	}
	
}
