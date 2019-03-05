package components;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bridge.component.IExecutionFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentArgument;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import jadex.rules.eca.ChangeInfo;

@Agent
@Arguments
(value={
	@Argument(name="argumentTest", description="componentBDI.argumentTest", clazz=String.class, defaultvalue="\"defaultValue1\""), 
	@Argument(name="secondArgumentTest", description="componentBDI.twoArgumentTest", clazz=String.class, defaultvalue="\"defaultValue2\"")
})
public class componentBDI {

	@AgentFeature
	protected IBDIAgentFeature bdiFeature;

	@AgentFeature
	protected IExecutionFeature execFeature;

	@Belief
	protected Map<String, String> wordtable;
	
	@AgentArgument
	protected String argumentTest;
	
	@AgentArgument
	protected String secondArgumentTest;
	
	@AgentCreated
	public void init() {
		System.out.println("Agent c1 initiated!");
		System.out.println(argumentTest);
		System.out.println(secondArgumentTest);
	    this.wordtable = new HashMap<String, String>();
	    this.wordtable.put("coffee", "Kaffee");
	    this.wordtable.put("milk", "Milch");
	    this.wordtable.put("cow", "Kuh");
	    this.wordtable.put("cat", "Katze");
	    this.wordtable.put("dog", "Hund");
	    // colloquial pair:
	    this.wordtable.put("bugger", "Flegel");
	}
	
	@Plan(trigger=@Trigger(factaddeds="wordtable"))
	public void checkWordPairPlan(@SuppressWarnings("rawtypes") ChangeEvent event)
	{
	    @SuppressWarnings("unchecked")
		ChangeInfo<String> change = ((ChangeInfo<String>)event.getValue());
	    System.out.println("Was added a value to the wordtable: " + change.getInfo());
	    if(change.getInfo().equals("bugger"))
	    {
	        System.out.println("Warning, a colloquial word pair has been added: "+change.getInfo()+" "+change.getValue());
	    }
	}
	
}
