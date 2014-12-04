import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;


public class DownloadPage {


	public class PostFile {
	  public void main(String[] args) throws Exception {
	    HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

	    HttpPost httppost = new HttpPost("http://localhost:9001/upload.php");
	    File file = new File("c:/TRASH/zaba_1.jpg");

	    MultipartEntity mpEntity = new MultipartEntity();
	    ContentBody cbFile = new FileBody(file, "image/jpeg");
	    mpEntity.addPart("userfile", cbFile);


	    httppost.setEntity(mpEntity);
	    System.out.println("executing request " + httppost.getRequestLine());
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    System.out.println(response.getStatusLine());
	    if (resEntity != null) {
	      System.out.println(EntityUtils.toString(resEntity));
	    }
	    if (resEntity != null) {
	      resEntity.consumeContent();
	    }

	    httpclient.getConnectionManager().shutdown();
	  }
	}
//	public String rent(){
//        //String folderAndFile = createTransaction();
//
//        try {
//            File file = new File("C:/Users/dalyc2/Desktop/download.xml");
//            HttpPost post = new HttpPost("http://api.online-convert.com/queue-insert");
//            post.setEntity((HttpEntity) new InputStreamEntity(new FileInputStream(file),file.length()));
//            post.setHeader("Content-type", "text/xml; charset=ISO-8859-1");
//
//            //creating the HTTP Post
//            DefaultHttpClient client = new DefaultHttpClient();
//
//            HttpResponse response = client.execute(post);
//            HttpEntity httpEntity = response.getEntity();
//
//            System.out.println(EntityUtils.toString(httpEntity));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static void main(String[] args) throws IOException {
//
//        // Make a URL to the web page
//        URL url = new URL("http://image.online-convert.com/convert-to-svg");
//
//        // Get the input stream through URL Connection
//        URLConnection con = url.openConnection();
//        InputStream is =con.getInputStream();
//
//        // Once you have the Input Stream, it's just plain old Java IO stuff.
//
//        // For this case, since you are interested in getting plain-text web page
//        // I'll use a reader and output the text content to System.out.
//
//        // For binary content, it's better to directly read the bytes from stream and write
//        // to the target file.
//
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//        String line = null;
//
//        // read each line and write to System.out
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//    }
}
