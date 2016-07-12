/*
 * This file is the internal part of the PCJ Library
 */
package org.pcj.internal.message;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import org.pcj.internal.network.MessageDataInputStream;
import org.pcj.internal.network.MessageDataOutputStream;

/**
 * Abstract class for storing messages, containing base
 * methods for any type of different messages.
 *
 * @author Marek Nowicki (faramir@mat.umk.pl)
 */
abstract public class Message implements Serializable {

    protected static final Logger LOGGER = Logger.getLogger(Message.class.getName());
    private static final long serialVersionUID = 1L;
    private MessageType type;

    /**
     * Prevent from creating object
     */
    private Message() {
    }

    Message(MessageType type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Message");

        sb.append("{Type:");
        sb.append(type);

        sb.append(", objs:{");
        sb.append(paramsToString());
        sb.append("}");

        sb.append("}");

        return sb.toString();
    }

    public abstract void writeObjects(MessageDataOutputStream out) throws IOException;

    public abstract void readObjects(MessageDataInputStream in) throws IOException;

    public abstract String paramsToString();

    public abstract void execute(SocketChannel sender, MessageDataInputStream in) throws IOException;

}