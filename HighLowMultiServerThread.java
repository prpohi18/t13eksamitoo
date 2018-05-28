import java.net.*;
import java.io.*;

public class HighLowMultiServerThread extends Thread {
    private Socket socket = null;

    public HighLowMultiServerThread(Socket socket) {
        super("HighLowMultiServerThread");
		this.socket = socket;
		System.out.println("TEST");
    }
    
    public void run() {

        	try(
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		){
			boolean connection = true;
			boolean ContGame = true;
			int time = 0;
			System.out.println("Connection made");
			String inputLine, outputLine;
            
            // Initiate conversation with client
			Round r1 = new Round();
			r1.FirstStart();
            outputLine = r1.StartRoundRequest();
            out.println(outputLine);
			
			
            while (ContGame==true){
				
				if(in.ready()){				
					inputLine = in.readLine();
					outputLine = r1.midGame(inputLine);
					if(outputLine.equals("Correct answer")){
						outputLine = r1.FinishRoundRequest();
						out.println(outputLine);
					}
					else if (outputLine.equals("Try again")){
						out.println(outputLine);
						System.out.println("Client did not type the correct type of answer");
					}
					else if (outputLine.equals("Wrong answer")){
						System.out.println("Client typed the wrong answer");
						out.println(outputLine);
						break;
					}
					outputLine = r1.StartRoundRequest();
					out.println(outputLine);
					time=0;
				}
				else if(time==10){	
					ContGame=false;
					out.println("Time is up");
					break;
				}else{
					time+=1;
					try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
					out.println(time);
                }
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
