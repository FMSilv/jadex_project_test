package usingServices.F1;

import java.util.HashMap;
import java.util.Map;

import jadex.bridge.service.annotation.Service;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@Service
@ProvidedServices(@ProvidedService(type=ITranslationService.class))
public class TranslationBDI implements ITranslationService{

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
	
	
	public IFuture<String> translateEnglishGerman(String eword) {
		String gword = wordtable.get(eword);
		return new Future<String>(gword);
	}

}
