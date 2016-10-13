package com.github.KCulture.Notified.Services;

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
