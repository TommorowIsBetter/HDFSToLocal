package com.cn.plase;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsToLocal {
	private String uri1 = null;
	private String src1 = null;
	private String dst1 = null;
	public HdfsToLocal() throws IOException{
		ReadFile readfile = new ReadFile();
		uri1 =readfile.readValue("uri");
		src1 = readfile.readValue("src");
		dst1 = readfile.readValue("dst");
		/*下面进行转码，否则报非法字符的错误*/
		uri1= uri1.replaceAll(" ", "");
		src1= src1.replaceAll(" ", "");
		dst1= dst1.replaceAll(" ", "");
    }
	    
public static void main(String[] args) throws Exception {
	//添加需要运行的程序
	HdfsToLocal down = new HdfsToLocal();
	down.DownToLocal();
}
	//从HDFS上下载到本地文件的系统
public void DownToLocal() throws URISyntaxException, IOException{
    //获取文件系统的配置文件
    Configuration conf = new Configuration() ;
    URI uri = new URI(uri1) ;//throw URISyntaxException
    FileSystem fs = FileSystem.get(uri, conf);//throw IOException
    //建立文件的源地址和目的地址对象
    Path src = new Path(src1) ;
    Path dst = new Path(dst1) ;
    //判断文件是否存在，如果存在则上传，否则给出提示
    if(fs.exists(src)){
        fs.copyToLocalFile(src, dst);
    }else{
        System.out.println("文件不存在！");
    }
    fs.close();
	}
}