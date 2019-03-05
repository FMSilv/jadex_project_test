package c3;

import java.text.SimpleDateFormat;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class ClockBDI {

	protected long time;
	
	protected SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	
	@AgentBody
	protected void body() {
	    setTime(System.currentTimeMillis());
	}
	
	
	@Belief
	public long getTime() {
		return time;
	}

	@Belief
	public void setTime(long time) {
		this.time = time;
	}
	
	@Plan(trigger=@Trigger(factchangeds="time"))
	protected void printTime()
	{
	  System.out.println(formatter.format(getTime()));
	}
	
}
