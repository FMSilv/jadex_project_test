package d5;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalRecurCondition;
import jadex.bdiv3.annotation.GoalResult;

@Goal(recur=true)
public class TranslateGoal {
	
	@GoalParameter
	protected String eword;

	@GoalResult
	protected String gword;

	public TranslateGoal(String eword) {
		this.eword = eword;
	}
	
	@GoalRecurCondition(beliefs="wordtable")
	public boolean checkRecur()
	{
	  return true;
	}

}
