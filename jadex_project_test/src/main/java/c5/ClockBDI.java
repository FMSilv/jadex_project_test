package c5;

import java.text.SimpleDateFormat;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;

@Agent
public class ClockBDI {
	
	@Belief(updaterate=1000)
	protected long time = System.currentTimeMillis();
	
	protected SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	@Plan(trigger=@Trigger(factchangeds="time"))
	protected void printTime()
	{
	  System.out.println(formatter.format(time));
	}
	
}
