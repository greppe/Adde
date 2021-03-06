import java.net.*;
import java.io.*;

public class Client 
{

	// private GameEngine engine;
	private boolean ready;
	private Socket socket;
	static PrintWriter output;
	
	public Client(GameEngine engine, String ip, int port) 
	{
		try {
			socket = new Socket(ip, port);
			//this.engine = engine;
			ConnectionHandler handler = new ConnectionHandler(socket);
			handler.delegate = new ConnectionHandler.Delegate() {
				
				@Override
				public void addRow(int row) {
					engine.addRow(row, 1);
				}
				
				@Override
				public void gameOver() {
					System.out.println("Game Over! No");
				}
			};
			
			
			output =  new PrintWriter(socket.getOutputStream(), true);
			new Thread(handler).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Socket cSocket = new Socket(IP, PORT);

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		

		
		while (true) 
		{
			System.out.println("--");
			String cmd = keyboard.readLine();
			
			if (cmd.equals("exit"))
			{
				 break;
			}
			else if (cmd.equals("ready")) 
			{
				sendInt(11);
			}
			else if (cmd.equals("lose")) 
			{
				sendInt(10);
			}
			else if (cmd.startsWith("add")) 
			{
				int firstSpace = cmd.indexOf(" ");
				if (firstSpace != -1) 
				{
					sendInt(Integer.parseInt(cmd.substring(firstSpace+1)));
				}
			}
			else 
			{
				output.println(cmd);
			}

		}
		

		
	}
	
	/*
	 *  Sends an integer to the other client via the server
	 */
	
	protected void sendInt(int n)
	{
		output.println("msg " + n);
	}
	
	private void exit() 
	{
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
