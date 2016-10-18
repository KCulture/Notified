package com.github.KCulture.Notified.Service;

public class CmdTrigger {
	private boolean fired = true;
	public void triggerCommand(Commando cmd){
		cmd.execute();
		this.fired = cmd.status(); 
	}
	
	public boolean getFired(){
		return this.fired;
	}

}
