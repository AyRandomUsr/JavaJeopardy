import java.io.*;
import java.net.*;






public class Client {

/*
public class QNA {
    private Character answer;

    public void question1(){
        socket_out.println("Attack on Titan: Who is the hidden royal?");
        socket_out.println("A: Sasha");
        socket_out.println("B: Levi");
        socket_out.println("C: Krista");
        socket_out.println("D: Hange");

        answer = 'C';
    }

    public void question2(){
        socket_out.println("Attack on Titan: What episode does Eren 'supposedly' die in?");
        socket_out.println("A: five");
        socket_out.println("B: four");
        socket_out.println("C: six");
        socket_out.println("D: three");

        answer = 'A';
    }

    public void question3(){
        socket_out.println("Demon Slayer: What color is Tanjiro's sword?");
        socket_out.println("A: red");
        socket_out.println("B: blue");
        socket_out.println("C: black");
        socket_out.println("D: white");

        answer = 'C';
    }
}; */



	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println(
					"Usage: java Client <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (
				Socket mySocket = new Socket(hostName, portNumber);
				PrintWriter socket_out = new PrintWriter(mySocket.getOutputStream(), true);
				BufferedReader socket_in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;
			System.out.println("Please enter your name:");
			fromUser = stdIn.readLine();
			socket_out.println(fromUser); //Transmit name over the interwebs

			//Read line from the server
			while ((fromServer = socket_in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("QUIT"))
					break;

				//Read line from the keyboard
				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					socket_out.println(fromUser);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
	}
}
