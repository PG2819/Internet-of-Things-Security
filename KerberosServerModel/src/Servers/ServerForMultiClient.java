/*Server*/
package Servers;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

import DBManger.*;

//����һ���ɹ����û���½��server
public class ServerForMultiClient {
    private int port=9999;
    private ServerSocket serverSocket;
    private ExecutorService executorService;//�̳߳�
    private final int POOL_SIZE=100;//����CPU�̳߳ش�С
    public static int infoID = DBExcute.getMAXInfoID() + 1;
    public ServerForMultiClient() throws IOException{
        serverSocket=new ServerSocket(port);
        //Runtime��availableProcessor()�������ص�ǰϵͳ��CPU��Ŀ.
        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        System.out.println("����������");
    }
    public void service(){
        while(true){
            Socket socket=null;
            try {
                //���տͻ�����,ֻҪ�ͻ�����������,�ͻᴥ��accept();�Ӷ���������
                socket=serverSocket.accept();
                executorService.execute(new Servers.ServerThread(socket));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerForMultiClient().service();
    }
}