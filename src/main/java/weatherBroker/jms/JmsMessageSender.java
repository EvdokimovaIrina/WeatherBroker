package weatherBroker.jms;



import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
import weatherBroker.model.QueryWeather;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

public class JmsMessageSender {

    private JmsTemplate jmsTemplate;
    private Destination queue;
    private MessageConverterImpl messageConverter;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Destination queue) {
        this.queue = queue;
    }

    public void simpleSend() {
       /* jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("1111");
            }
        });
*/

    }

    public void sendMessage(final QueryWeather queryWeather) throws JMSException {
        /*jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(queryWeather);
            }
        });*/
        this.jmsTemplate.convertAndSend(queryWeather);
        /*this.jmsTemplate.convertAndSend("2222");
        String eee = (String) jmsTemplate.receiveAndConvert();
        System.out.println(eee);*/
    }
}
