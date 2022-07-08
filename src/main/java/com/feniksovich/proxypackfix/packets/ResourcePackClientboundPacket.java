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
 * Represents «Clientbound Resource Pack» packet implementation.
 *
 * @see <a href="https://wiki.vg/Protocol#Resource_Pack_.28clientbound.29">wiki.vg reference</a>
 * @author Feniksovich
 */
public class ResourcePackClientboundPacket extends AbstractPacket {

    public static final List<ProtocolIdMapping> MAPPINGS = Arrays.asList(
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_8, MINECRAFT_1_8, 0x48),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_9, MINECRAFT_1_11_2, 0x32),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_12, MINECRAFT_1_13, 0x34),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_13_1, MINECRAFT_1_13_2, 0x37),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_14_3, 0x39),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_15, MINECRAFT_1_15_2, 0x3A),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16, MINECRAFT_1_16_1,0x39),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16_2, MINECRAFT_1_16_5, 0x38),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_17, MINECRAFT_1_18, 0x3C),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19, MINECRAFT_LATEST, 0x3A)
    );

    private String url;
    private String hash;
    private boolean forced;
    private boolean hasPromptMessage;
    private String promptMessage;

    @Override
    public void read(ByteBuf byteBuf, PacketDirection packetDirection, int i) {
        url = ProtocolUtil.readString(byteBuf);
        hash = ProtocolUtil.readString(byteBuf);
        forced = byteBuf.readBoolean();
        hasPromptMessage = byteBuf.readBoolean();
        if (hasPromptMessage) {
            promptMessage = ProtocolUtil.readString(byteBuf);
        }

    }

    @Override
    public void write(ByteBuf byteBuf, PacketDirection packetDirection, int i) {
        //TODO Validation implementation (maybe out of scope of this method)?
        ProtocolUtil.writeString(byteBuf, url);
        ProtocolUtil.writeString(byteBuf, hash);
        byteBuf.writeBoolean(forced);
        byteBuf.writeBoolean(hasPromptMessage);
        ProtocolUtil.writeString(byteBuf, promptMessage);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public void setHasPromptMessage(boolean hasPromptMessage) {
        this.hasPromptMessage = hasPromptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
        setHasPromptMessage(true);
    }

    public String getUrl() {
        return url;
    }

    public String getHash() {
        return hash;
    }

    public boolean isForced() {
        return forced;
    }

    public boolean hasPromptMessage() {
        return hasPromptMessage;
    }

    public String getPromptMessage() {
        return promptMessage;
    }

    @Override
    public String toString() {
        return "ResourcePackClientboundPacket{" +
                "url='" + url + '\'' +
                ", hash='" + hash + '\'' +
                ", forced=" + forced +
                ", hasPromptMessage=" + hasPromptMessage +
                ", promptMessage='" + promptMessage + '\'' +
                '}';
    }
}
