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
		/*�������ת�룬���򱨷Ƿ��ַ��Ĵ���*/
		uri1= uri1.replaceAll(" ", "");
		src1= src1.replaceAll(" ", "");
		dst1= dst1.replaceAll(" ", "");
    }
	    
public static void main(String[] args) throws Exception {
	//�����Ҫ���еĳ���
	HdfsToLocal down = new HdfsToLocal();
	down.DownToLocal();
}
	//��HDFS�����ص������ļ���ϵͳ
public void DownToLocal() throws URISyntaxException, IOException{
    //��ȡ�ļ�ϵͳ�������ļ�
    Configuration conf = new Configuration() ;
    URI uri = new URI(uri1) ;//throw URISyntaxException
    FileSystem fs = FileSystem.get(uri, conf);//throw IOException
    //�����ļ���Դ��ַ��Ŀ�ĵ�ַ����
    Path src = new Path(src1) ;
    Path dst = new Path(dst1) ;
    //�ж��ļ��Ƿ���ڣ�����������ϴ������������ʾ
    if(fs.exists(src)){
        fs.copyToLocalFile(src, dst);
    }else{
        System.out.println("�ļ������ڣ�");
    }
    fs.close();
	}
}