package b1;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Description;

@Agent
@Description("The translation agent A1. <br> Empty agent that can be loaded and started.")
public class TranslationBDI {

	protected Map<String, String> wordtable;
	
	@AgentFeature 
	protected IBDIAgentFeature bdiFeature;
	
	@AgentCreated
	public void init()
	{
		this.wordtable = new HashMap<String, String>();
		this.wordtable.put("coffee", "cafe");
		this.wordtable.put("milk", "leite");
		this.wordtable.put("cow", "vaca");
		this.wordtable.put("cat", "gato");
		this.wordtable.put("dog", "cao");
	}
	
	@AgentBody
	public void body()
	{
		System.out.println("Agent b1 initiated!");
		bdiFeature.adoptPlan("translateEnglishPortuguese", "dog", "cat");
		System.out.println("test1_translateEnglishPortuguese");
		bdiFeature.adoptPlan("planTest", "coffee");
		System.out.println("test2_planTest");
	}
	
	@Plan
	public void translateEnglishPortuguese(ChangeEvent<Object[]> event)
	{
		String eword = (String)event.getValue()[0];
		String cword = (String)event.getValue()[1];
		String gword = wordtable.get(eword);
		String cgword = wordtable.get(cword);
		System.out.println("Translated: "+eword+" - "+gword);
		System.out.println("Translated: "+cword+" - "+cgword);
	}
	
	@Plan
	public void planTest(ChangeEvent<Object[]> event)
	{
		String eword = (String)event.getValue()[0];
		String gword = wordtable.get(eword);
		System.out.println("Translated: "+eword+" - "+gword);
	}
	
}
