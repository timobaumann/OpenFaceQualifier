package edu.cmu.inmind.openfacequalifier;

import java.io.IOException;

import edu.cmu.inmind.multiuser.controller.blackboard.BlackboardEvent;
import edu.cmu.inmind.multiuser.controller.blackboard.BlackboardSubscription;
import edu.cmu.inmind.multiuser.controller.plugin.PluggableComponent;
import edu.cmu.inmind.multiuser.controller.plugin.StatefulComponent;
import edu.cmu.inmind.openfacequalifier.eventDetector.EventDetector;
import edu.cmu.inmind.openfacequalifier.eventDetector.RuleBasedEventDetector;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;
import edu.cmu.inmind.openfacequalifier.input.ProcessInput;
import edu.cmu.inmind.openfacequalifier.output.ConsoleOutput;

@BlackboardSubscription(messages={"MSG_openFaceStartup"})
@StatefulComponent
public class OpenFaceComponent extends PluggableComponent {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(BlackboardEvent event) {
		String URL = event.getElement().toString();
		OpenFaceInput ofi;
		try {
			ofi = new ProcessInput(URL);
			EventDetector ed = new RuleBasedEventDetector(ofi);
			ed.addListener(new ConsoleOutput(/*some parameters?*/));
			ed.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
