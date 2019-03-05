package c2;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentCreated;
import jadex.rules.eca.ChangeInfo;

@Agent
public class TranslationBDI {

	@Belief
	protected Map<String, String> wordtable = new HashMap<String, String>();
	
	@Belief(dynamic=true)
	protected boolean alarm = wordtable.containsKey("bugger");
	
	@AgentCreated
	public void init() {
		System.out.println("Agent c2 initiated!");
		System.out.println(TranslationBDI.this.getClass().getName());
	
	    this.wordtable = new HashMap<String, String>();
	    this.wordtable.put("coffee", "Kaffee");
	    this.wordtable.put("milk", "Milch");
	    this.wordtable.put("cow", "Kuh");
	    this.wordtable.put("cat", "Katze");
	    this.wordtable.put("dog", "Hund");
	    // colloquial pair:
	    this.wordtable.put("bugger", "Flegel");
	}
	
	@Plan(trigger=@Trigger(factchangeds="alarm"))
	public void checkWordPairPlan(@SuppressWarnings("rawtypes") ChangeEvent event)
	{
	  @SuppressWarnings("unchecked")
	  ChangeInfo<Boolean> change = (ChangeInfo<Boolean>)event.getValue();
	  // Print warning when value changes from false to true.
	  if(Boolean.FALSE.equals(change.getOldValue()) && Boolean.TRUE.equals(change.getValue()))
	  {
	    System.out.println("Warning, a colloquial word pair has been added.");
	  }
	}
	
}
