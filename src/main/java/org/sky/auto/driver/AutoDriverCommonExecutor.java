package org.sky.auto.driver;

import java.io.IOException;

import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.remote.service.DriverService;

public class AutoDriverCommonExecutor extends DriverCommandExecutor{

	public AutoDriverCommonExecutor(DriverService service) {
		super(service);
	}

	@Override
	public Response execute(Command command) throws IOException {
		
		return super.execute(command);
	}
	
	
	
}
