package org.sky.auto.intrumentation;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
@Deprecated
public class FileFinder {
	public static List<String> fileList = new ArrayList<String>();
	/**返回当前项目的目录*/
	public static String getProjectFile() {
		return System.getProperty("user.dir");
	}      
	/**返回classes的目录，里面都是class文件*/
	public static String getClassFile(){
		return ClassLoader.getSystemResource("").getPath();
	}
	/**
	 *@param f 指定的File对象 
	 *@param suffix 指定的要查找的文件的后缀名
	 *@param idDepth 是否需要对子目录进行遍历
	 *@return 返回得到文件的list
	 */
	public static List<String> listFile(File f, String suffix, boolean isDepth) {
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
					tempsuffix = filePath.substring(begIndex + 1,filePath.length());
	            }
				if (tempsuffix.equals(suffix)) {
					fileList.add(filePath);
				}
			}
		}
		return fileList;
	}
	/**listFile方法的补充方法
	 * @param path 要查找的目录地址
	 * @param suffix 后缀名
	 * @param isDepth 是否遍历子文件夹
	 * @return 返回文件的list
	 * */
	public static List<String> getFileList(String path, String suffix, boolean isDepth) {
		File file = new File(path);
		return FileFinder.listFile(file, suffix, isDepth);
	}
	/**
	 * @param 目录的地址
	 * @param 后缀名
	 * @param 是否遍历子文件夹
	 * @return 返回不带有后缀名的文件的名字列表，如果是class不含有class的包名地址
	 */
	public static List<String> getClassNameList(String path, String suffix,boolean isDepth) {
		List<?> listDir = FileFinder.getFileList(path, suffix, isDepth);
		List<String> listFileName = new ArrayList<String>();
		for (int i = 0; i < listDir.size(); i++) {
			int index = ((String) listDir.get(i)).lastIndexOf(File.separator);
			int len = ((String) listDir.get(i)).length();
			String fileNameWithsuffix = ((String) listDir.get(i)).substring(index + 1, len - suffix.length() - 1);
			listFileName.add(fileNameWithsuffix);
	    }
		return listFileName;
	}
	/**
	 * @param path 目录的地址
	 * @param suffix 后缀名
	 * @param 是否遍历子文件夹
	 * @return 如果查找的目录是class文件目录，会返回这个目录下的class文件的全名，包括包的信息都有，但是不带有class的后缀名
	 */
	public static List<String> getPackageName(String path, String suffix, boolean isDepth) {
		List<?> listDir = FileFinder.getFileList(path, suffix, isDepth);
		List<String> listPackPathName = new ArrayList<String>();
		for (int i = 0; i < listDir.size(); i++) {
			// int index=((String)listDir.get(i)).lastIndexOf(":");
			int index = FileFinder.getProjectFile().length();
			int len = ((String) listDir.get(i)).length();
			String fileNameWithsuffix = ((String) listDir.get(i)).substring( index + 5, len - suffix.length() - 1).replace(File.separator, ".");
			listPackPathName.add(fileNameWithsuffix);
		}
		return listPackPathName;
	}
	/**
	 * @param 目录的地址
	 * @param 后缀名
	 * @param 是否遍历子文件夹
	 * @return 不带路径的只返回文件的名字不带有后缀名，只是实现的方式是通过getPackageName实现的。
	 */
	 public static List<String> getOnlyClassNameByPackage(String path, String suffix, boolean isDepth) {
		 List<String> listFileNameByPackage = new ArrayList<String>();
		 List<String> listPackPathName = FileFinder.getPackageName(path,suffix, isDepth);
		 for (int i = 0; i < listPackPathName.size(); i++) {
			 int index = listPackPathName.get(i).lastIndexOf(".");
			 int len = listPackPathName.get(i).length();
			 String tempClassName = listPackPathName.get(i).substring(index + 1, len);
			 listFileNameByPackage.add(tempClassName);
		 }
		 return listFileNameByPackage;
	 }
	 /**
	  * @param path目录地址
	  * @param suffix 后缀名
	  * @param 是否遍历子文件夹
	  * @return 通过指定文件的目录，返回目录下所有文件的全路径,不包含文件名！只查出属于的path。不区分系统
	  * 这个方法建议不好上面的所有方法一起调用，会造成一个小bug，就是重复查找了文件，因为放在了list里面所以不会去重。
	  */
	 public static List<String> getLocationPath(String path, String suffix,boolean isDepth) {
		 // String proDir = SKFileFinder.getProjectFile();
		 List<String> listPathNoName = new ArrayList<String>();
		 List<String> listFile = FileFinder .getFileList(path, suffix, isDepth);
		 for (int i = 0; i < listFile.size(); i++) {
			 int index = listFile.get(i).lastIndexOf(File.separator);
			 String tempPathNoName = listFile.get(i).substring(0, index + 1);
			 listPathNoName.add(tempPathNoName);
		 }
		 return listPathNoName;
	 }
}
