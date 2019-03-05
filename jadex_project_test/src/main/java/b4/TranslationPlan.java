package b4;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.runtime.impl.PlanFailureException;

@Plan
public class TranslationPlan {

	 protected Map<String, String> wordtable;

	  public TranslationPlan()
	  {
	    // Init the wordtable and add some words
		  this.wordtable = new HashMap<String, String>();
		  this.wordtable.put("coffee", "Kaffee");
		  this.wordtable.put("milk", "Milch");
		  this.wordtable.put("cow", "Kuh");
		  this.wordtable.put("cat", "Katze");
		  this.wordtable.put("dog", "Hund");
	  }

	  @PlanBody
	  public void translateEnglishGerman()
	  {
		  throw new PlanFailureException();
//		   System.out.println("Translated: dog - " + wordtable.get("dog"));
	  }
	  
	  @PlanPassed
	  public void passed()
	  {
	    System.out.println("Plan finished successfully.");
	  }

	  @PlanAborted
	  public void aborted()
	  {
	    System.out.println("Plan aborted.");
	  }

	  @PlanFailed
	  public void failed(Exception e)
	  {
	    System.out.println("Plan failed: "+e);
	  }
	
}
