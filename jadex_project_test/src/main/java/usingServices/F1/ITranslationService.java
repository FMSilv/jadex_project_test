package usingServices.F1;

import jadex.commons.future.IFuture;

public interface ITranslationService {

	  public IFuture<String> translateEnglishGerman(String eword);
	
}
