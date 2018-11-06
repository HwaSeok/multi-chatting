
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
 
public class ClientBackground {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGUI gui;
    private String msg;
    private String nickName;
 
    public void setGui(ClientGUI gui) {
        this.gui = gui;
    }
    
    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connect();
    }
    public void connect(){
        try {
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("서버에 연결됨");
            
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(nickName);
            System.out.println("클라이언트 : 닉네임 전송완료 ");
            
            while(in!=null){
                msg = in.readUTF();
                gui.appendMsg(msg);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setNickname(String nickName){
        this.nickName = nickName;
    }
    
}