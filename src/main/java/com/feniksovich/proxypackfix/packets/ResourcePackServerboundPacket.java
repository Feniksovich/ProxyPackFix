package com.feniksovich.proxypackfix.packets;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.mapping.AbstractProtocolMapping;
import dev.simplix.protocolize.api.mapping.ProtocolIdMapping;
import dev.simplix.protocolize.api.packet.AbstractPacket;
import dev.simplix.protocolize.api.util.ProtocolUtil;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;
import java.util.List;

import static dev.simplix.protocolize.api.util.ProtocolVersions.*;

/**
 * Represents «Serverbound Resource Pack» packet implementation.
 *
 * @see <a href="https://wiki.vg/Protocol#Resource_Pack_.28serverbound.29">wiki.vg reference</a>
 * @author Feniksovich
 */
public class ResourcePackServerboundPacket extends AbstractPacket {

    public static final List<ProtocolIdMapping> MAPPINGS = Arrays.asList(
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_8, MINECRAFT_1_8, 0x19),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_9, MINECRAFT_1_11_2, 0x16),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_12, MINECRAFT_1_12_2, 0x18),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_13, MINECRAFT_1_13_2, 0x1D),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_15_2, 0x1F),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16, MINECRAFT_1_18_2, 0x21),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19, MINECRAFT_LATEST, 0x23)
    );

    private int result;

    @Override
    public void read(ByteBuf byteBuf, PacketDirection packetDirection, int i) {
        result = ProtocolUtil.readVarInt(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf, PacketDirection packetDirection, int i) {
        ProtocolUtil.writeVarInt(byteBuf, result);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResourcePackServerboundPacket{" +
                "result=" + result +
                '}';
    }
}
