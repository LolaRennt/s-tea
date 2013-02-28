package org.sky.auto.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFile {

	private String filePath;

	File file;

	public MyFile(String filePath) {

		this.filePath = filePath;

		this.file = new File(filePath);

		if (!file.exists()) {

			createFile(filePath);

		}
	}

	public MyFile(File file) {

		this.filePath = file.getAbsolutePath();

		this.file = file;

	}

	/**根据指定文件地址新建文件，如果有多级目录没有建立，那么建立中间目录*/
	public static boolean createDictory(String path) {

		if (!new File(path).exists()) {

			return new File(path).mkdir();

		}
		return true;
	}

	/**
	 * 跨目录级别建立文件
	 * */
	public static boolean createFile(String path) {

		if (!new File(path).exists()) {

			StringBuilder dictoryPath = new StringBuilder(path);

			dictoryPath.delete(path.lastIndexOf(File.separator), path.length());

			createDictory(dictoryPath.toString());

			try {

				new File(path).createNewFile();

			} catch (IOException e) {

				System.out.println("创建失败！");
			}
		}
		return true;

	}

	public static boolean exist(String path) {

		return new File(path).exists();

	}

	public String getFileContentByString() {

		List<String> fileContentList = getFileContentByList();

		StringBuilder content = new StringBuilder();

		for (String str : fileContentList) {

			content.append(str);

		}
		return content.toString();
	}

	public ArrayList<String> getFileContentByList() {

		if (!new File(filePath).exists()) {

			return null;

		}
		ArrayList<String> fileContent = new ArrayList<String>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(new File(
					filePath)));

			String temp;

			while ((temp = reader.readLine()) != null) {

				fileContent.add(temp.trim());

			}
			reader.close();

			return fileContent;

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}


	/**删除目录及目录下所有文件和文件夹*/
	public static void deleteDirectory(String directoryPath) {

		File file = new File(directoryPath);

		if (file.isDirectory()) { 

			File temp = null;

			String[] childsFile = file.list(); 

			for (String s : childsFile) {
				
				if (directoryPath.endsWith(File.separator)) {

					temp = new File(directoryPath + s);

				} else {

					temp = new File(directoryPath + File.separator + s);

				}
				// delete file
				if (temp != null && temp.isFile()) { 

					System.out.println(temp.delete());

					System.out.println("delete of the fileName: "
							+ temp.getAbsolutePath());

				} else if (temp != null && temp.isDirectory()) { 

					deleteDirectory(temp.getAbsolutePath());
				}
			}

			file.delete();

		} else if (file.isFile()) { 

			file.delete();

		}
	}

	public boolean rename(String newName) {

		return this.file.renameTo(new File(this.file.getParent() + File.separator
				+ newName));
	}
	/**
	 * @return 得到目录文件下的列表，如果有子目录的话，会遍历子目录
	 * */
	public static List<String> listFile(File f, String suffix, boolean isDepth) {
		
		List<String> fileList = new ArrayList<String>();

		if (f.isDirectory() && isDepth == true) {

			File[] t = f.listFiles();

			for (int i = 0; i < t.length; i++) {

				listFile(t[i], suffix, isDepth);

			}
		} else {

			String filePath = f.getAbsolutePath();

			if (suffix != null) {

				int begIndex = filePath.lastIndexOf(".");

				String tempsuffix = "";

				if (begIndex != -1) {

					tempsuffix = filePath.substring(begIndex + 1,
							filePath.length());

				}
				if (tempsuffix.equals(suffix)) {

					fileList.add(filePath);// 
				}
			}
		}

		return fileList;

	}
	
private static String lineBreak;
	
	public static String lineBreak(){
		String os = System.getProperty("os.name");
		if(os.toLowerCase().contains("win")){
			lineBreak="\r\n";
		}else if(os.toLowerCase().contains("unix")){
			lineBreak="\n";
		}else{
			lineBreak="\r";
		}
		return lineBreak;
	}
}
