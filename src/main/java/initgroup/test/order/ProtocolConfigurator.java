package initgroup.test.order;

import initgroup.test.order.struct.Order;
import initgroup.test.order.struct.OrderItem;
import initgroup.test.protocol.Protocol;
import initgroup.test.protocol.decoder.DateDecoder;
import initgroup.test.protocol.decoder.FVLNDecoder;
import initgroup.test.protocol.decoder.ListStructDecoder;
import initgroup.test.protocol.decoder.StringDecoder;
import initgroup.test.protocol.decoder.VLNDecoder;

/**
 * Примечание: Протокол ничего не должне знать об тегах, т.к. это уже прикладная логика
 * Здесь некая пародия на OSGi, когда модуль производит конфигурирование сервисов.
 * Сюда так же подошел же бы какой-нибудь dependency injection, например Google Guice.
 * Тогда каждый прикладной модуль мог бы конфигурировать протокол
 */
public class ProtocolConfigurator {
	public void configure(Protocol protocol) {
		/*
		 * Замечание: можно кодек определять по структуре, при декодировании но не для этого ТЗ :(
		 * Мешают в т.ч. ограничение на длину, BigDecimal для целого ряда полей...
		 * Можно натравить classpath scanner или парсить классы в кастомном ClassLoader...
		 *
		 * Я бы выпилил ограничения по длинны. Непонятно от чего защищаемся...
		 * Я бы спросил зачем, но сейчас воскресенье :)
		 */
		protocol.registerDecoder(Order.TAGS.DATE, new DateDecoder());
		protocol.registerDecoder(Order.TAGS.NUMBER, new VLNDecoder(8));
		protocol.registerDecoder(Order.TAGS.CUSTOMER, new StringDecoder(1000));
		protocol.registerDecoder(Order.TAGS.ITEMS, new ListStructDecoder<>(protocol, OrderItem.class));

		protocol.registerDecoder(OrderItem.TAGS.PRODUCT, new StringDecoder(200));
		protocol.registerDecoder(OrderItem.TAGS.PRICE, new VLNDecoder(6));
		protocol.registerDecoder(OrderItem.TAGS.COUNT, new FVLNDecoder(8));
		protocol.registerDecoder(OrderItem.TAGS.COST, new VLNDecoder(6));
	}
}
