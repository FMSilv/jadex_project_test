package exchangeMessages;

import jadex.bdiv3.runtime.IGoal;
import jadex.bdiv3x.runtime.IMessageEvent;
import jadex.tools.comanalyzer.Message;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IMessageFeature;
import jadex.bridge.fipa.SFipa;
import jadex.bridge.service.types.message.MessageType;


public class iReceiverMessageBDI {

	
//    public void body()
//    {
//        IMessageEvent msg = (IMessageEvent) getInitialEvent();
//        Message content = (Message) msg.getContent();
//        String master_platform = content.getV();
//        System.out.println("master_platform = "+master_platform);
//        String master_address = "127.0.0.1";
//        System.out.println("Receiver created ("+this.getAgentName()+")");
//        registerService(new AgentIdentifier("df@"+master_platform,new String[] {"nio-mtp://"+master_platform+":1234"}));
//    }
//    public void registerService(AgentIdentifier df) {
//        try {
//            IGoal register = createGoal("df_register");
//            register.getParameter("description").setValue(getPropertybase().getProperty("fipa.agentdescription.receiver"));
//            register.getParameter("df").setValue(df);
//            register.getParameter("leasetime").setValue(2000);
//            dispatchSubgoalAndWait(register);
//        }catch(Exception e) {
//            printStackTrace(e);
//        }
//    }
//    public void printStackTrace(Exception e) {
//        System.err.println("Exception: "+e);
//        StackTraceElement[] ste = e.getStackTrace();
//        for(int i = ste.length - 1; i >= 0; i--) {
//            System.err.println("    "+ste[i]);
//        }
//    }
}
