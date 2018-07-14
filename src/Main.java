import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Main
{
        private static String URL = "http://www.hanhande.com/manhua/op/11579";

    public static void main(String[] args) throws IOException {
        for (int page = 886; page <= 890; page++) {
            int url_index = page - 820;
            Connection connection = Jsoup.connect(URL+url_index+".shtml");   //解释网址
            Document document = connection.timeout(1000).get();  //连接网址
            Elements list = document.getElementsByTag("img");  //获取img数据

            System.out.println(list.size());

            File file = new File("E:\\JAVA\\crawler_onepiece\\"+page);
            file.mkdir();  //创建文件夹

            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);
                Element li = list.get(i);
                Element img = li.getElementsByTag("img").first();  //获取img第一个属性src的值
                String imgUrl = img.attr("src");
                //筛选只存在1283568的值
                if (imgUrl.indexOf("1283568") != -1) {
                    System.out.println(imgUrl);
                    DownLoad(imgUrl, page,i);
                }
            }
        }
    }

    public static void DownLoad(String imgUrl,int i,int j) throws IOException {
        URL url = new URL(imgUrl);
        InputStream inputstream = url.openStream();
        File dir = new File("E:\\JAVA\\crawler_onepiece\\"+i);
        File file = new File(dir,j+".png");
        FileOutputStream outputStream = new FileOutputStream(file);

        byte[] buffer = new byte[1024];   //分配1024个字节大小的内存给buffer
        int len;
        while ((len=inputstream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        inputstream.close();
        outputStream.close();
    }

}
