package d3;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalResult;

@Goal
public class TranslateGoal {
	
	@GoalParameter
	protected String eword;

	@GoalResult
	protected String gword;

	public TranslateGoal(String eword) {
		this.eword = eword;
	}

}
