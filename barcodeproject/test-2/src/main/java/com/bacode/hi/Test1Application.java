package com.bacode.hi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bacode.hi.controller.SwingServerController;
import com.bacode.hi.rmi.IRemote;
import com.bacode.hi.rmi.RemoteImpl;

@SpringBootApplication
public class Test1Application {

	public static void main(String[] args) throws AlreadyBoundException{
		SpringApplication.run(Test1Application.class, args);
		
		SwingServerController sw = new SwingServerController();
	}
}
