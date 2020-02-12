package com.bacode.hi.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.springframework.stereotype.Service;

import com.bacode.hi.service.master.IProdMaster;
import com.bacode.hi.service.master.ProdMasterImpl;

@Service
public class RemoteImpl extends UnicastRemoteObject implements IRemote {

	public RemoteImpl() throws RemoteException {
		super();
	}
	
	@Override
	public IProdMaster getProdMaster() throws RemoteException {
		IProdMaster prodMaster = new ProdMasterImpl();
		return prodMaster;
	}

}
