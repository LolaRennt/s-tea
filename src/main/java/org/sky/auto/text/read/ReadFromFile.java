package org.sky.auto.text.read;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
	StringContorl sc=new StringContorl();
	private File file;
	//private String text;
	/**对应每一个文件都存在一个对象
	 * 构造方法
	 * */
	public ReadFromFile(File file){
		this.file=file;
	}
	
	public ReadFromFile(String path){
		this.file=new File(path);
	}
	
	/**
	 * @return 返回格式化后的文件资源，以String形式返回，并且去掉了所有的空格，包括</br>
	 * 换行符等等
	 * */
	public String readFile(){
		String text=null;
		BufferedReader br = null;
		try {
			text=new String();
			br= new BufferedReader(new FileReader(getFile()));
			String tmp=br.readLine();
			while(tmp!=null){
				text=text+tmp;
				tmp=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return StringContorl.replaceBlank(text);
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return 返回文件资源中的块资源的数组
	 * */
	public String[] getStringBlocks(){
		if(!isFrameBlockFile()){
			String[] strs=readFile().split(Block.BLOCK_END);
			String[] newstrs = new String[strs.length];
			for(int i=0;i<strs.length;i++){
				newstrs[i]=strs[i]+Block.BLOCK_END;
			}
			return newstrs;
		}
		return null;	
	}
	/**
	 *获取资源中的块资源
	 *@return 返回文件资源中的块资源List 
	 **/
	public List<StringBlock> getStringBlockList(){
		List<StringBlock> list = new ArrayList<StringBlock>();
		String[] strs=getStringBlocks();
		for(String str:strs){
			list.add(new StringBlock(str));
		}	
		return list;
	}
	
	/**
	 * 判断这个文件的类型，是不是frame类型
	 * @return true 为文件为Frame的资源， false则文件不为Frame的资源
	 * */
	public boolean isFrameBlockFile(){
		if(readFile().startsWith("Frame")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @return 获取Frame资源文件中的块数组
	 * */
	public String[] getStringFrameBlocks(){
		if(isFrameBlockFile()){
			String text=readFile();
			String frameEntity = readFile().substring(text.indexOf(";")+1, text.lastIndexOf("}"));
			String[] strs=frameEntity.split(Block.BLOCK_END);
			String[] newstrs = new String[strs.length];
			for(int i=0;i<strs.length;i++){
				newstrs[i]=strs[i]+Block.BLOCK_END;
			}
			return newstrs;
		}
		return null;
	}
	
	/**获取文件内的块内容的list
	 * @return 获取文件内的块内容的list
	 * */
	public List<StringFrameBlock>  getStringFrameBlockList(){
		List<StringFrameBlock> list = new ArrayList<StringFrameBlock>();
		String[] strs=getStringBlocks();
		for(String str:strs){
			list.add(new StringFrameBlock(str));
		}	
		return list;
	}
	
	
}
