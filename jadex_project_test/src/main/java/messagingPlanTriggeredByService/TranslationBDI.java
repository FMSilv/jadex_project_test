package messagingPlanTriggeredByService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.IBDIAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanPrecondition;
import jadex.bdiv3.annotation.ServiceTrigger;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.bridge.service.annotation.Service;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@Service
@ProvidedServices(
	@ProvidedService(name="transser", type=IChatService.class, implementation=@Implementation(IBDIAgent.class))
)
public class TranslationBDI{

	protected Map<String, String> wordtable; 
	
	@AgentCreated
	public void init() {
		wordtable = new HashMap<String, String>();
		wordtable.put("coffee", "Kaffee");
		wordtable.put("milk", "Milch");
		wordtable.put("cow", "Kuh");
		wordtable.put("cat", "Katze");
		wordtable.put("dog", "Hund");
	}
	
	
	@Plan(trigger=@Trigger(service=@ServiceTrigger(type=IChatService.class)))
	public class TranslatePlan
	{
	    @PlanPrecondition
	    public boolean checkPrecondition(Object[] params)
	    {
	    	System.out.println("Sender: "+params[1]+" ->Message: "+params[0]);
	        return wordtable.containsKey(params[0]);
	    }
	    
	    @PlanBody
	    public String body(Object[] params)
	    {
	    	System.out.println(params[0]);
	        String eword = (String)params[0];
	        String gword = wordtable.get(eword);
	        System.out.println("Translated with internal dictionary dictionary: "+eword+" - "+gword);
	        return gword;
	    }
	}

	@Plan(trigger=@Trigger(service=@ServiceTrigger(type=IChatService.class)))
	public String internetTranslate(Object[] params)
	{
	  String eword = (String)params[0];
	  String ret = null;
	  try
	  {
	    URL dict = new URL("http://wolfram.schneider.org/dict/dict.cgi?query="+eword);
	    System.out.println("Following translations were found online at: "+dict);
	    BufferedReader in = new BufferedReader(new InputStreamReader(dict.openStream()));
	    String inline;
	    while((inline = in.readLine())!=null)
	    {
	      if(inline.indexOf("<td>")!=-1 && inline.indexOf(eword)!=-1)
	      {
	        try
	        {
	          int start = inline.indexOf("<td>")+4;
	          int end = inline.indexOf("</td", start);
	          String worda = inline.substring(start, end);
	          start = inline.indexOf("<td", start);
	          start = inline.indexOf(">", start);
	          end = inline.indexOf("</td", start);
	          String wordb = inline.substring(start, end==-1? inline.length()-1: end);
	          wordb = wordb.replaceAll("<b>", "");
	          wordb = wordb.replaceAll("</b>", "");
	      ret = worda;
	          System.out.println("Translated with internet dictionary: "+worda+" - "+wordb);
	        }
	    catch(Exception e)
	    {
	          System.out.println(inline);
	        }
	      }
	    }
	    in.close();
	  }
	  catch(Exception e)
	  {
	    e.printStackTrace();
	    throw new PlanFailureException(e.getMessage());
	  }
	  return ret;
	}
	
}
