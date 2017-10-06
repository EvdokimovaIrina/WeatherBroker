package weatherBroker.jms;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import weatherBroker.model.QueryWeather;

import javax.jms.*;

class MessageConverterImpl implements MessageConverter {

    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        ObjectMessage message = session.createObjectMessage((QueryWeather) object);
        return message;
    }

    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        return objectMessage.getObject();
    }
}
