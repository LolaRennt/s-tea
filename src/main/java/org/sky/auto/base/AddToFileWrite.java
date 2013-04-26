package org.sky.auto.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.output.FileWriterWithEncoding;
/**这个类用来在文件后面直接追加内容！不会覆盖内容*/
public class AddToFileWrite {
	private static  FileWriterWithEncoding fw;
	public static void writeContext(String text,String fname){
		if(!new File(fname).exists()){
			MyFile.createLevelFile(fname);
		}
		try {
			fw=new FileWriterWithEncoding(fname,"UTF-8",true);
			fw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {     
                if(fw != null){  
                    fw.close();     
                    fw=null;
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            } 
		}
	}
}
