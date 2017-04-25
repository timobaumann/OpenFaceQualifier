package edu.cmu.inmind.openfacequalifier.output;

import edu.cmu.inmind.openfacequalifier.Event;
import edu.usc.ict.vhmsg.VHMsg;


public class VHTOutput implements EventOutput {

	private VHMsg sender;
	
	public VHTOutput(){
		sender = new VHMsg();
		sender.openConnection("localhost");
	}
	
	@Override
	public void nextEvent(Event e) {
		//System.out.println("isSmiling " + e.getSmile());
		//System.err.println("not sending to VHT");
		if (e != null) {
		sender.sendMessage("vrMultisense 0 " + e.getSmile() + " 0.939988519996405 false false neutral 1.0 true");
		}
		//sender.sendMessage("vrTest");
		//e.toString();
	}

}
