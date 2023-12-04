// Importing necessary Java Message Service (JMS) and ActiveMQ classes
package jms;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
// Class definition for the JMS producer
public class Producer {
    // Main method
    public static void main(String[] args) {
        try {
            // Establishing a connection to the ActiveMQ broker
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Creating a JMS session
            Session session = ((javax.jms.Connection) connection).createSession(true, Session.AUTO_ACKNOWLEDGE);

            // Creating a JMS destination (topic)
            Destination destination = session.createTopic("myTopic.topic");

            // Creating a message producer for the specified destination
            MessageProducer producer = session.createProducer(destination);

            // Setting the message delivery mode to NON_PERSISTENT (messages won't be saved)
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Creating a text message
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Hello world!");

            // Sending the text message to the destination
            producer.send(textMessage);

            // Committing the JMS transaction
            session.commit();

            // Printing a message indicating successful message sending
            System.out.println("Envoi du message...");

            // Closing the JMS session and connection
            session.close();
            connection.close();
        } catch (Exception e) {
            // Handling any exceptions that may occur and printing the stack trace
            e.printStackTrace();
        }
    }
}
