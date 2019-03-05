package e1;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalResult;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;

@Capability
public class TranslationCapability {

	@Belief
	protected Map<String, String> wordtable = new HashMap<String, String>();

	@Goal
	public class Translate
	{
		@GoalParameter
		protected String eword;
		@GoalResult
		protected String gword;
		
		public Translate(String eword)
		{
			this.eword = eword;
		}
	}
	
	public TranslationCapability(){
		wordtable.put("milk", "Milch");
		wordtable.put("cow", "Kuh");
		wordtable.put("cat", "Katze");
		wordtable.put("dog", "Hund");
	}
	
	@Plan(trigger=@Trigger(goals=Translate.class))
	protected String translate(String eword)
	{
		return wordtable.get(eword);
	}
	
}
