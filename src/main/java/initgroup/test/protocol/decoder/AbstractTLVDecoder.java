package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Protocol;
import initgroup.test.protocol.decoder.exception.DecodeException;
import initgroup.test.protocol.decoder.exception.NotEnoughDataForDecodingException;
import initgroup.test.protocol.objectbuilder.StructBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractTLVDecoder<T> {

	private static final int PACKET_LENGTH = 4;

	private final Protocol protocol;
	protected final StructBuilder<T> builder;

	public AbstractTLVDecoder(Protocol protocol, Class<T> clazz) {
		this.protocol = protocol;
		this.builder = new StructBuilder<>(clazz);
	}

	protected T decodeStruct(InputStream stream, AtomicInteger positon) {
		try {
			Map<Integer, Object> structs = new HashMap<>();
			// Примечание: первичная информация это length и цикл должен быть ею ограничен
			// Из-за того, что мы не знаем у корневой структуру длинну, приходиться вычитывать данные
			// на основе  количества полей
			for (int i = 0; i < builder.getFieldsCount(); i++) {
				if (stream.available() < 4) {
					throw new NotEnoughDataForDecodingException();
				}

				int itemTag = readTag(stream);
				int itemLength = readLength(stream);
				Object struct = protocol.getDecoder(itemTag).decode(stream, itemLength);
				structs.put(itemTag, struct);

				positon.addAndGet(PACKET_LENGTH + itemLength);
			}
			return builder.build(structs);
		} catch (IOException e) {
			throw new DecodeException(e);
		}
	}

	private int readTag(InputStream stream) {
		return readShort(stream);
	}

	private int readLength(InputStream stream) {
		return readShort(stream);
	}

	private int readShort(InputStream stream) {
		try {
			int lo = stream.read();
			int hi = stream.read();
			return lo + (hi << 8);
		} catch (IOException e) {
			throw new DecodeException(e);
		}
	}
}
