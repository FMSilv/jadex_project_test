package d1;

import jadex.bdiv3.annotation.Goal;

@Goal
public class TranslateGoal {

	  protected String eword;

	  protected String gword;

	  public TranslateGoal(String eword)
	  {
	    this.eword = eword;
	  }

	  public String getEWord()
	  {
	    return eword;
	  }

	  public String getGWord()
	  {
	    return gword;
	  }

	  public void setGWord(String gword)
	  {
	    this.gword = gword;
	  }
	
}
