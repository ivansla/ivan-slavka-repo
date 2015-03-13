package ivan.slavka.utils.beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientBean {

	private final PrintWriter clientWriter;

	public ClientBean(Socket clientSocket) throws IOException{
		this.clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
	}

	public PrintWriter getClientWriter() {
		return this.clientWriter;
	}
}
