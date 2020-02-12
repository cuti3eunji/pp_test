package com.bacode.hi.controller;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.bacode.hi.rmi.IRemote;
import com.bacode.hi.rmi.RemoteImpl;

public class SwingServerController {

	public SwingServerController() throws AlreadyBoundException {
		swingconnect();
	}
	
	
	public void swingconnect() throws AlreadyBoundException{
		//client(swing) connect
		try {
			Registry reg = LocateRegistry.createRegistry(0115);
			
			IRemote rmi = new RemoteImpl();
			reg.rebind("server1234", rmi);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
