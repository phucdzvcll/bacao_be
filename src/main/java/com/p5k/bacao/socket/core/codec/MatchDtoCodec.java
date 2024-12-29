package com.p5k.bacao.socket.core.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p5k.bacao.socket.dto.match.MatchDto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.nio.charset.StandardCharsets;

public class MatchDtoCodec extends BaseCodec {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Encoder encoder;
    private final Decoder<Object> decoder;

    public MatchDtoCodec() {
        this.encoder = in -> {
            if (in == null) {
                return ByteBufAllocator.DEFAULT.buffer(0);
            }
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            String json = objectMapper.writeValueAsString(in);
            out.writeCharSequence(json, StandardCharsets.UTF_8);
            return out;
        };
        this.decoder = (buf, state) -> {
            String json = buf.toString(StandardCharsets.UTF_8);
            buf.readerIndex(buf.readableBytes());

            return objectMapper.readValue(json, MatchDto.class);
        };
    }

    public Decoder<Object> getValueDecoder() {
        return this.decoder;
    }

    public Encoder getValueEncoder() {
        return this.encoder;
    }
}
