package com.github.lmm.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableObject {
	
	@SuppressWarnings("unchecked")
	public static <T>T cloneObject(T obj) throws IOException, ClassNotFoundException{
		 ByteArrayOutputStream byteOut = new ByteArrayOutputStream(); 
	     ObjectOutputStream out = new ObjectOutputStream(byteOut); 
	     out.writeObject(obj); 
	     ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray()); 
	     ObjectInputStream in =new ObjectInputStream(byteIn);
	     return (T) in.readObject();
	}
	
	public static void serializeToFile(String File){
		
	}
}
