package com.netposa.gat.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FileUtil {

	private static final Log logger = LogFactory.getLog(FileUtil.class);
	
	public static String getConfigFromLibPath() {
		String appPath = initAppPath(FileUtil.class);
		String appConfigPath = appPath + File.separator + "config"
				+ File.separator;
		return appConfigPath;
	}

	private static String initAppPath(Class<?> cls) {
		try {
			URL urlPath = cls.getProtectionDomain().getCodeSource()
					.getLocation();
			String realPath = java.net.URLDecoder.decode(urlPath.getPath(),
					"utf-8");
			if (realPath.endsWith(".jar")) {
				realPath = realPath.substring(0, realPath.lastIndexOf("/") + 1);
			}

			File file = new File(realPath);
			realPath = file.getAbsolutePath();
			return realPath;

		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
	}
	
	public static String getServerConfigJson(){
		String appConfigPath = getConfigFromLibPath();
		String path = appConfigPath + GatConstant.SERVERCONFIGNAME;
		String json = FileUtil.readJsonFile(path);
		return json;
	}
	
	/**
	 * 读取json文件
	 * 
	 * @param path
	 * @return
	 */
	public static String readJsonFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		String laststr = "";
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException el) {
					logger.error(el);
				}
			}
		}
		return laststr;
	}


	public static void savefile(File f, String data) {
		try {
			FileWriter fileWriter = new FileWriter(f);
			fileWriter.write(data);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	public static void saveFile(String file, String conent)
	{
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(conent+"\r\n");
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
			out.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		
	}

	public static Properties getProperties(String name) {

		String appConfigPath = FileUtil.getConfigFromLibPath();
		String Config = appConfigPath + name;
		File file = new File(Config);
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			p.load(in);

		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}


	/** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
        	SimpleDateFormat  sdf = new SimpleDateFormat(format);  
        	Date date = sdf.parse(date_str);
        	long ts = date.getTime();
            return String.valueOf(ts);  
        } catch (Exception e) {  
        	logger.error(e);
        	e.printStackTrace();
        	logger.error("日期转换异常"+e.getMessage());
        }  
        return "";  
    }  
    
    public static List<String> Readtxt(String filepath){
    	try{
    		List<String> templist= new ArrayList<String>();
    		File f= new File(filepath);
    		if(f.isFile() && f.exists()){
    			InputStreamReader read = new InputStreamReader(
                        new FileInputStream(f),"UTF-8");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    templist.add(lineTxt);
                }
                read.close();
    		}else{
    			
    			logger.error("没有找到文件"+filepath);	
    		}
    		return templist;
    	}catch (Exception e) {  
    		logger.error(e);
        	e.printStackTrace();
         } 
    	return null;
    }
    
    public static String Readtxt1(String filepath){
    	try{
    		StringBuffer sb = new StringBuffer();
    		File f= new File(filepath);
    		if(f.isFile() && f.exists()){
    			InputStreamReader read = new InputStreamReader(
                        new FileInputStream(f));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	sb.append(lineTxt);
                }
                read.close();
    		}else{
    			
    			logger.error("没有找到文件"+filepath);	
    		}
    		return sb.toString();
    	}catch (Exception e) {  
    		logger.error(e);
        	e.printStackTrace();
         } 
    	return null;
    }
      
    
    public static void threadSleep(int value){
    	try{
    		Thread.sleep(value);
    	} catch (Exception e) {  
            logger.error("getlocalImage"+e);
        } 
    }
    
    public static byte[] createImage(String imgurl) throws Exception {
		URL url = new URL(imgurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(1000);  
        conn.setConnectTimeout(1000);  
		InputStream inputStream = conn.getInputStream(); // 通过输入流获得图片数据
		return readInputStream(inputStream); // 获得图片的二进制数据
	}
    
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		buffer = null;
		bos.close();
		return bos.toByteArray();
	}
	/**
	  * 下载文件到本地
	  *
	  * @param urlString
	  *          被下载的文件地址
	  * @param filename
	  *          本地文件名
	  * @throws Exception
	  *           各种异常
	  */
	public static void download(String urlString, String filename) throws Exception {
		   // 构造URL
		   URL url = new URL(urlString);
		   // 打开连接
		   URLConnection con = url.openConnection();
		   // 输入流
		   InputStream is = con.getInputStream();
		   // 1K的数据缓冲
		   byte[] bs = new byte[1024];
		   // 读取到的数据长度
		   int len;
		   // 输出的文件流
		   OutputStream os = new FileOutputStream(filename);
		   // 开始读取
		   while ((len = is.read(bs)) != -1) {
		     os.write(bs, 0, len);
		   }
		   // 完毕，关闭所有链接
		   os.close();
		   is.close();
		}   
	
	 /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
    public static String parseRequest(BufferedReader reader){
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        try{
            while ((line=reader.readLine())!=null){
                stringBuffer.append(line);
            }
        }catch (Exception e){

        }
        String postString = stringBuffer.toString();
        return postString;
    }
    
}
