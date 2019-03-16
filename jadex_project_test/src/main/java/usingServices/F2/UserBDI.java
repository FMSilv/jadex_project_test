package usingServices.F2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.IResultListener;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.commons.gui.PropertiesPanel;
import jadex.commons.gui.SGUI;
import jadex.commons.gui.future.SwingResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class UserBDI {

    @Agent
    protected IInternalAccess agent;
	
    @AgentBody
    public void body()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame();
                PropertiesPanel pp = new PropertiesPanel();
                final JTextField tfe = pp.createTextField("English Word", "dog", true);
                final JTextField tfg = pp.createTextField("German Word");
                JButton bt = pp.createButton("Initiate", "Translate");
                f.add(pp, BorderLayout.CENTER);
                f.pack();
                f.setLocation(SGUI.calculateMiddlePosition(f));
                f.setVisible(true);
                
                bt.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        SServiceProvider.getServices(agent, ITranslationService.class, RequiredServiceInfo.SCOPE_PLATFORM)
                            .addResultListener(new IntermediateDefaultResultListener<ITranslationService>()
                        {
                            public void intermediateResultAvailable(ITranslationService ts)
                            {
                                ts.translateEnglishGerman(tfe.getText())
                                    .addResultListener(new SwingResultListener<String>(new IResultListener<String>()
                                {
                                    public void resultAvailable(String gword) 
                                    {
                                        tfg.setText(gword);
                                    }

                                    public void exceptionOccurred(Exception exception)
                                    {
                                        exception.printStackTrace();
                                        tfg.setText(exception.getMessage());
                                    }
                                }));
                            }
                        });
                    }
                });
                
            }
        });
    }
    
}
