package ghc.files;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOpt {

	public FileOpt() {
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * 追加方式写文件：使用FileWriter
     */
    public static void writeAppend(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public static void downloadFile(String urlIn,String fileNameOut){
		try{            
            URL url = new URL(urlIn);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(fileNameOut);
            byte[] buffer = new byte[2048];
            
            int length = 0;
            
            while ((length = inputStream.read(buffer)) != -1) {
               //System.out.println("Buffer Read of length: " + length);
               outputStream.write(buffer, 0, length);
            }
            
            inputStream.close();
            outputStream.close();
            
         }catch(Exception e){
            System.out.println("FileDownloadException: " + e.getMessage());
         }
	}
	
	/**
	 * 如果文件夹不存在 则建立新文件夹 
	 * @param strDir
	 */
	public static void directoryCheckAndMake(String strDir){		
		File dir = new File(strDir);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdir();
		}
	}
	
	/**
	 * 文件选择对话框
	 * @param parent
	 * @return
	 */
	public static String chooseFile(Component parent){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = 
				new FileNameExtensionFilter("图像文件(JPG/PNG/BMP)", "jpg","png","bmp");
		fileChooser.setFileFilter(filter);
		int ret = fileChooser.showOpenDialog(parent);
		String filepath = "";
		if (ret == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			filepath = selectedFile.getPath();
		}
		return filepath;
	}
	
	/** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public static void copyFile(String oldPath, String newPath) { 
       try { 
           int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //字节数 文件大小 
                   System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 
       }
   }

}
