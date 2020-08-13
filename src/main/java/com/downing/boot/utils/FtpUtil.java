package com.downing.boot.utils;

import org.apache.commons.net.ftp.*;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class FtpUtil {

	private FTPClient           ftpClient;
	public static final int     BINARY_FILE_TYPE = FTP.BINARY_FILE_TYPE;
	public static final int     ASCII_FILE_TYPE  = FTP.ASCII_FILE_TYPE;
	public static final String ENCODING_TYPE_GBK    = "GBK";
	public static final String ENCODING_TYPE_UTF_8  = "UTF-8";


	/**
	 * 初始化相关参数，并连接FTP服务器<br/>
	 * path should not the path from root index or some FTP server would go to root as '/'.
	 * @param ftpConfig         配置文件
	 * @throws SocketException  连接错误抛出异常
	 * @throws IOException

	public boolean connectServer(FtpConfig ftpConfig) throws SocketException, IOException {
	String server = ftpConfig.getServer();
	String user = ftpConfig.getUsername();
	String password = ftpConfig.getPassword();
	String location = ftpConfig.getLocation();
	return connectServer(server, user, password, location);
	}*/
	/**
	 * 连接FTP服务器
	 * @param server            FTP服务器地址
	 * @param user              FTP用户名
	 * @param password          FTP密码
	 * @param path              FTP远程目录
	 * @param encoding			编码，默认GBK
	 * @throws SocketException
	 * @throws IOException
	 */
	public boolean connectServer(String server, String user, String password, String path, String encoding) throws SocketException, IOException {
		boolean isLogin = false;
		ftpClient = new FTPClient();
		if (null == encoding || encoding.equals("")) {
			ftpClient.setControlEncoding(encoding);
		} else {
			ftpClient.setControlEncoding(ENCODING_TYPE_UTF_8);
		}
		ftpClient.connect(server);
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
		conf.setServerLanguageCode("zh");
		isLogin = ftpClient.login(user, password);
		if (isLogin && !path.equals("")) {
			ftpClient.changeWorkingDirectory(path);
		}
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // 设置传输二进制文件
		return isLogin;
	}

	/**
	 * 下载文件
	 * @param fileName    远程文件的名字
	 * @param localFileName     本地文件的路径
	 * @return                  true:下载成功<br/>
	 *                          false:下载失败
	 * @throws IOException
	 * @throws IOException
	 * @author: huanghe
	 */
	public static boolean downFile(String url, int port, String filePath, String fileName, String localFilePath, String localFileName, String username, String passWord) throws IOException {
		FTPClient ftpClient = new FTPClient();
		boolean isSuccess=false;
		try {
			ftpClient.connect(url, port);
			ftpClient.login(username, passWord);// 登录
			ftpClient.changeWorkingDirectory(filePath);// 转移到FTP服务器目录
			ftpClient.setControlEncoding("UTF-8");
			File localFile = new File(localFilePath + File.separator + localFileName);
			FileOutputStream is = new FileOutputStream(localFile);
			isSuccess=ftpClient.retrieveFile(fileName, is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ftpClient.disconnect();
		}
		return isSuccess;
	}

	/**
	 * 设置文件类型<br/>
	 * FTP.BINARY_FILE_TYPE | FTP.ASCII_FILE_TYPE
	 * Set transform type
	 * @param fileType      文件类型
	 * @throws IOException
	 */
	public void setFileType(int fileType) throws IOException {
		ftpClient.setFileType(fileType);
	}

	/**
	 * 关闭FTP连接
	 * @throws IOException
	 */
	public void closeServer() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.disconnect();
		}
	}

	/**
	 * 更改远程目录路径
	 * @param path     要设置的远程路径
	 * @return
	 * @throws IOException
	 */
	public boolean changeDirectory(String path) throws IOException {
		return ftpClient.changeWorkingDirectory(path);
	}

	/**
	 * 在远程服务器上创建目录
	 * @param pathName  目录名称
	 * @return          true:创建成功<br/>false:创建失败
	 * @throws IOException
	 */
	public boolean createDirectory(String pathName) throws IOException {
		return ftpClient.makeDirectory(pathName);
	}

	/**
	 * 删除远程服务器上路径
	 * @param path  要删除的远程路径
	 * @return      true:删除目录成功<br/>false:删除目录失败
	 * @throws IOException
	 */
	public boolean removeDirectory(String path) throws IOException {
		return ftpClient.removeDirectory(path);
	}

	/**
	 * 删除一个目录及目录下所有文件
	 * @param path  要删除的远程目录
	 * @param isAll 是否连目录下的所有文件一起删除
	 * @return      true:删除目录成功<br/>false:删除目录失败
	 * @throws IOException
	 */
	public boolean removeDirectory(String path, boolean isAll) throws IOException {
		if (!isAll) {
			return removeDirectory(path);
		}
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr == null || ftpFileArr.length == 0) {
			return removeDirectory(path);
		}
		for (FTPFile ftpFile : ftpFileArr) {
			String name = ftpFile.getName();
			if (!ftpFile.getName().equals(".") && (!ftpFile.getName().equals(".."))) {
				if (ftpFile.isDirectory()) {
					removeDirectory(path + "/" + name, true);
				} else if (ftpFile.isFile()) {
					deleteFile(path + "/" + name);
				} else if (ftpFile.isSymbolicLink()) {
					// 如果为超链接
				} else if (ftpFile.isUnknown()) {
					// 如果文件类型为未知
				}
			}
		}
		return ftpClient.removeDirectory(path);
	}

	/**
	 * 判断指定数据是否存在文件夹名为<tt>name</tt>目录<br>
	 * @param path  指定路径
	 * @param name  目录名称
	 * @return      true:此目录已经存在</br>false:此目录不存在
	 * @throws IOException
	 */
	public boolean existDirectory(String path, String name) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr != null && ftpFileArr.length > 0) {
			for (FTPFile ftpFile : ftpFileArr) {
				if (ftpFile != null && ftpFile.isDirectory() && ftpFile.getName().equalsIgnoreCase(name)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 获取指定目录下所有文件名列表
	 * @param path  指定目录
	 * @return      目录下所有文件名列表
	 * @throws IOException
	 */
	public List<String> getFileList(String path) throws IOException {
		// listFiles return contains directory and file, it's FTPFile instance
		// listNames() contains directory, so using following to filer directory.
		// String[] fileNameArr = ftpClient.listNames(path);
		FTPFile[] ftpFiles = ftpClient.listFiles(path);
		List<String> retList = new ArrayList<String>();
		if (ftpFiles == null || ftpFiles.length == 0) {
			return retList;
		}
		for (FTPFile ftpFile : ftpFiles) {
			if (ftpFile.isFile()) {
				retList.add(ftpFile.getName());
			}
		}
		return retList;
	}

	/**
	 * 获取指定目录下所有文件名和文件夹名列表
	 * @param path  指定目录
	 * @return      目录下所有文件名和文件夹名列表
	 * @throws IOException
	 */
	public List<String> getFileAndFolderList(String path) throws IOException {
		FTPFile[] ftpFiles = ftpClient.listFiles(path);
		List<String> retList = new ArrayList<String>();
		if (ftpFiles != null && ftpFiles.length > 0) {
			for (FTPFile ftpFile : ftpFiles) {
				if (!ftpFile.getName().equals(".") && !ftpFile.getName().equals("..")) {
					retList.add(ftpFile.getName());
				}
			}
		}
		return retList;
	}

	/**
	 * 删除指定文件
	 * @param pathName  要删除的文件名称
	 * @return          true:删除成功<br/>
	 *                  false:删除失败
	 * @throws IOException
	 */
	public boolean deleteFile(String pathName) throws IOException {
		return ftpClient.deleteFile(pathName);
	}

	/**
	 * 上传单个文件到FTP服务器上
	 * @param filePath  文件路径
	 * @param newName   新文件名称
	 * @return          true:上传成功<br/>
	 *                  false:上传失败
	 * @throws IOException
	 */
	public boolean uploadFile(String filePath, String newName) throws IOException {
		boolean flag = false;
		InputStream iStream = null;
		try {
			iStream = new FileInputStream(filePath);
			flag = ftpClient.storeFile(newName, iStream);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (iStream != null) {
				iStream.close();
			}
		}
		return flag;
	}

	/**
	 * 上传单个文件到FTP服务器上
	 * @param filePath  要上传的文件路径
	 * @return          true:上传成功<br/>
	 *                   false:上传失败
	 * @throws IOException
	 */
	public boolean uploadFile(String filePath) throws IOException {
		String fileName = "";
		int iFile = filePath.lastIndexOf(File.separator);
		if (iFile != -1) {
			fileName = filePath.substring(iFile + 1, filePath.length());
		}
		if (fileName.equals("")) {
			return false;
		} else {
			return uploadFile(filePath, fileName);
		}
	}

	/**
	 * 上传单个文件到FTP服务器上
	 * @param uploadFile 要上传的文件
	 * @param ftpPath    ftp目录
	 * @return           true:上传成功<br/>
	 *                   false:上传失败
	 * @throws IOException
	 */
	public boolean uploadFile(File uploadFile, String ftpPath) throws IOException {
		return uploadFile(uploadFile.getPath(), uploadFile.getName());
	}

	/**
	 * 上传单个文件到FTP服务器上
	 * @param iStream   文件流
	 * @param newName   新文件名称
	 * @return          true:上传成功<br/>
	 *                  false:上传失败
	 * @throws IOException
	 */
	public boolean uploadFile(InputStream iStream, String newName) throws IOException {
		boolean flag = false;
		try {
			flag = ftpClient.storeFile(newName, iStream);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (iStream != null) {
				iStream.close();
			}
		}
		return flag;
	}

	/**
	 * 下载文件
	 * @param sourceFileName    源文件
	 * @return                  true:下载成功<br/>
	 *                          false:下载失败
	 * @throws IOException
	 */
	public InputStream downFile(String sourceFileName) throws IOException {
		return ftpClient.retrieveFileStream(sourceFileName);
	}

	public static void main(String[] args) throws Exception {
		FtpUtil t = new FtpUtil();
		t.connectServer( "180.150.190.200", "root","123456","/usr/local", "");
		File file = new File("e:\\1_2016-04-26_18.log.zip");
		t.uploadFile(file,"/dandandao2/realtime");
	}
}