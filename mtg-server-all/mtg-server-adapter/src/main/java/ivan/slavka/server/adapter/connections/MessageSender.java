package ivan.slavka.server.adapter.connections;

import ivan.slavka.utils.interfaces.IMessage;
import ivan.slavka.utils.interfaces.IMessageSender;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MessageSender extends Thread implements IMessageSender{

	private static final Log logger = LogFactory.getLog(MessageSender.class);

	private final BlockingQueue<IMessage> messagesQueue = new LinkedBlockingQueue<IMessage>();

	@Override
	public void run(){

		try{
			IMessage message = null;
			while(true){
				message = this.messagesQueue.take();
				message.execute();
			}
		} catch (InterruptedException e) {
			logger.error("### ERROR ### - Unexpected error while running MessageSender", e);
		}
	}

	public BlockingQueue<IMessage> getMessagesQueue() {
		return this.messagesQueue;
	}

	@Override
	public void sendMessage(IMessage message) {
		try{
			this.messagesQueue.put(message);
		} catch(Exception e){
			logger.error("### ERROR ### - Error while inserting message into the messagesQueue", e);
		}
	}
}
