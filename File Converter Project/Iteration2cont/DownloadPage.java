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

//Attempting to use HTTP Post with a converters API. Sends XML file to website and website converts pdf to svg
	// packages downloaded
	// some are outdated and dont compile. This is to demonstrate the code I have been working on for Iteration 2. 

	public String rent(){
        String folderAndFile = createTransaction();

        try {
        	//file location of xml file
            File file = new File("C:/Users/dalyc2/Desktop/download.xml");
            //website to send xml file to
            HttpPost post = new HttpPost("http:api.online-convert.com/queue-insert");
            post.setEntity((HttpEntity) new InputStreamEntity(new FileInputStream(file),file.length()));
            post.setHeader("Content-type", "text/xml; charset=ISO-8859-1");

          //  creating the HTTP Post
            DefaultHttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();

            System.out.println(EntityUtils.toString(httpEntity));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        // URL to the web page
        URL url = new URL("http:image.online-convert.com/convert-to-svg");

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

       
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

         //read each line and write to System.out
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
