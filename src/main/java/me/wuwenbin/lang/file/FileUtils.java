package me.wuwenbin.lang.file;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * @desc:文件工具类
 * 
 * @Author:chenssy
 * @date:2014年8月7日
 */
public class FileUtils {
	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';

	/**
	 * @desc:判断指定路径是否存在，如果不存在，根据参数决定是否新建
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param filePath
	 *            指定的文件路径
	 * @param isNew
	 *            true：新建、false：不新建
	 * @return 存在返回TRUE，不存在返回FALSE
	 */
	public static boolean isExist(String filePath, boolean isNew) {
		File file = new File(filePath);
		if (!file.exists() && isNew) {
			return file.mkdirs(); // 新建文件路径
		}
		return false;
	}


	/**
	 * 获取指定文件的大小
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 *
	 * @author:chenssy
	 * @date : 2016年4月30日 下午9:10:12
	 */
	@SuppressWarnings("resource")
	public static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
		}
		return size;
	}

	/**
	 * 删除所有文件，包括文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:41:08
	 *
	 * @param dirpath
	 */
	public void deleteAll(String dirpath) {
		File path = new File(dirpath);
		try {
			if (!path.exists())
				return;// 目录不存在退出
			if (path.isFile()) // 如果是文件删除
			{
				path.delete();
				return;
			}
			File[] files = path.listFiles();// 如果目录中有文件递归删除文件
			for (int i = 0; i < files.length; i++) {
				deleteAll(files[i].getAbsolutePath());
			}
			path.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件或者文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:41:59
	 *
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目的文件
	 * @param isOverWrite
	 *            是否覆盖文件
	 * @throws IOException
	 */
	public static void copy(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (!inputFile.exists()) {
			throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
		}
		copyPri(inputFile, outputFile, isOverWrite);
	}

	/**
	 * 复制文件或者文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:43:24
	 *
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目的文件
	 * @param isOverWrite
	 *            是否覆盖文件
	 * @throws IOException
	 */
	private static void copyPri(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (inputFile.isFile()) { // 文件
			copySimpleFile(inputFile, outputFile, isOverWrite);
		} else {
			if (!outputFile.exists()) { // 文件夹
				outputFile.mkdirs();
			}
			// 循环子文件夹
			for (File child : inputFile.listFiles()) {
				copy(child, new File(outputFile.getPath() + "/" + child.getName()), isOverWrite);
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:44:07
	 *
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目的文件
	 * @param isOverWrite
	 *            是否覆盖
	 * @throws IOException
	 */
	private static void copySimpleFile(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (outputFile.exists()) {
			if (isOverWrite) { // 可以覆盖
				if (!outputFile.delete()) {
					throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
				}
			} else {
				// 不允许覆盖
				return;
			}
		}
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(outputFile);
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		in.close();
		out.close();
	}

	/**
	 * 获取文件的MD5
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:50:38
	 *
	 * @param file
	 *            文件
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	/**
	 * 获取文件的后缀
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:51:59
	 *
	 * @param file
	 *            文件
	 * @return
	 */
	public static String getFileSuffix(String file) {
		if (file == null) {
			return null;
		}
		int extIndex = file.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = file.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return file.substring(extIndex + 1);
	}

	/**
	 * 文件重命名
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:56:05
	 *
	 * @param oldPath
	 *            老文件
	 * @param newPath
	 *            新文件
	 */
	public boolean renameDir(String oldPath, String newPath) {
		File oldFile = new File(oldPath);// 文件或目录
		File newFile = new File(newPath);// 文件或目录

		return oldFile.renameTo(newFile);// 重命名
	}

	/**
	 * 
	 * <b>Author</b> : Wuwenbin<br>
	 * <b>Title</b> : getBytesByFilepath<br>
	 * <b>Description</b> : 获得指定文件的byte数组<br>
	 * 
	 * @param filePath
	 * @return
	 */
	public static byte[] getBytesByFilepath(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 
	 * <b>Author</b> : Wuwenbin<br>
	 * <b>Title</b> : getFileByByte<br>
	 * <b>Description</b> : 根据byte数组，生成文件<br>
	 * 
	 * @param bfile
	 * @param filePath
	 * @param fileName
	 */
	public static void getFileByByte(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * <b>Author</b> : Wuwenbin<br>
	 * <b>Title</b> : inputStream2ByteArray<br>
	 * <b>Description</b> : 根据流生成byte数组二进制<br>
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] inputStream2ByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
