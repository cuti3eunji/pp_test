package com.bacode.hi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.bacode.hi.service.master.IProdMaster;

public interface IRemote extends Remote {
	
	public IProdMaster getProdMaster() throws RemoteException;

}
