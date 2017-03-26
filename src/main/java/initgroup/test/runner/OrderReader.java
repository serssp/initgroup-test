package initgroup.test.runner;

import initgroup.test.order.ProtocolConfigurator;
import initgroup.test.order.struct.Order;
import initgroup.test.protocol.Decoder;
import initgroup.test.protocol.Protocol;
import initgroup.test.protocol.decoder.TLVDecoder;
import initgroup.test.protocol.impl.ProtocolImpl;

import java.io.InputStream;

public class OrderReader {
	private final Protocol protocol;
	private final Decoder<Order> orderDecoder;

	public OrderReader() {
		protocol = new ProtocolImpl();
		new ProtocolConfigurator().configure(protocol);
		orderDecoder = new TLVDecoder<>(protocol, Order.class);
	}

	public Order read(InputStream stream) {
		return orderDecoder.decode(stream, 0);
	}
}
