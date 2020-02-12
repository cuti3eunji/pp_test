package com.bacode.hi.controller.connect;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.bacode.hi.rmi.IRemote;

public class ClientConnect {
	
	private static Registry reg = null;
	private static IRemote inf = null;
	public static IRemote getConnect()  {
		try {
			reg = LocateRegistry.getRegistry("localhost", 0115);
			inf = (IRemote) reg.lookup("server1234");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return inf;
	}
}
