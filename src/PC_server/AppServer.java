package MyApp;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class AppServer implements Runnable{

    public static final int SERVERPORT = 1111;

    // 最後要儲存照片的位置
    public static final String file_name = "C:\\Users\\User\\Desktop\\test.txt";

    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍歷所有的網路介面
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的介面下再遍歷IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback型別地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local型別的地址未被發現，先記錄候選地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果沒有發現 non-loopback地址.只能用最次選的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }
    public void run() {

        try {
            System.out.println("My IP: " + getLocalHostLANAddress().getHostAddress());

            System.out.println("Server: Connecting...");
            ServerSocket serverSocket = new ServerSocket(SERVERPORT);

            while (true) {

                Socket client = serverSocket.accept();
                //System.out.println("Server: Receiving...");

                OutputStream out = new FileOutputStream(file_name);
                byte buf[] = new byte[1024];
                int len;

                // 讀入從手機端傳來的照片
                InputStream inputStream = client.getInputStream();
                try {
                    while ((len = inputStream.read(buf)) != -1) {
                        // 將照片寫入到電腦裡
                        out.write(buf, 0, len);
                    }
                    out.close();
                    inputStream.close();

                    //print 接收到的內容
                    FileReader fr = new FileReader(file_name);
                    BufferedReader br = new BufferedReader(fr);
                    String content = null;
                    while (br.ready()) {
                        content = br.readLine();
                        System.out.println(content);
                        placeTextOnClipboard(content);
                    }
                    fr.close();


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    client.close();
                    //System.out.println("Server: Done.");

                }

            }

        } catch (Exception e) {

            System.out.println("Server: Error");
            e.printStackTrace();

        }

    }



    public void placeTextOnClipboard(String text) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection stringSel = new StringSelection(text);
        clipboard.setContents(stringSel, null);
    }


    public static void main(String str[]) {

        Thread desktopSerThread = new Thread(new AppServer());
        desktopSerThread.start();

    }
}




