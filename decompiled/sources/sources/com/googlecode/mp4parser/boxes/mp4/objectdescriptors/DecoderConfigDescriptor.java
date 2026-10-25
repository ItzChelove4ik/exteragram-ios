package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Descriptor(tags = {4})
/* loaded from: classes5.dex */
public class DecoderConfigDescriptor extends BaseDescriptor {
    private static Logger log;
    AudioSpecificConfig audioSpecificInfo;
    long avgBitRate;
    int bufferSizeDB;
    byte[] configDescriptorDeadBytes;
    long maxBitRate;
    int objectTypeIndication;
    List<ProfileLevelIndicationDescriptor> profileLevelIndicationDescriptors;
    int streamType;
    int upStream;

    static {
        log = Logger.getLogger(DecoderConfigDescriptor.class.getName());
    }

    public DecoderConfigDescriptor() {
        this.profileLevelIndicationDescriptors = new ArrayList();
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer r10) {
        this.objectTypeIndication = IsoTypeReader.readUInt8(r10);
        int r0 = IsoTypeReader.readUInt8(r10);
        this.streamType = r0 >>> 2;
        this.upStream = (r0 >> 1) & 1;
        this.bufferSizeDB = IsoTypeReader.readUInt24(r10);
        this.maxBitRate = IsoTypeReader.readUInt32(r10);
        this.avgBitRate = IsoTypeReader.readUInt32(r10);
        if (r10.remaining() <= 2) goto L17;
        int r02 = r10.position();
        BaseDescriptor r4 = ObjectDescriptorFactory.createFrom(this.objectTypeIndication, r10);
        int r5 = r10.position() - r02;
        Logger r03 = log;
        StringBuilder r6 = new StringBuilder();
        r6.append(r4);
        r6.append(" - DecoderConfigDescr1 read: ");
        r6.append(r5);
        r6.append(", size: ");
        if (r4 == null) goto L7;
        Integer r7 = Integer.valueOf(r4.getSize());
    L8:
        r6.append(r7);
        r03.finer(r6.toString());
        if (r4 == null) goto L14;
        int r04 = r4.getSize();
        if (r5 >= r04) goto L14;
        byte[] r05 = new byte[r04 - r5];
        this.configDescriptorDeadBytes = r05;
        r10.get(r05);
    L14:
        if ((r4 instanceof AudioSpecificConfig) == false) goto L17;
        this.audioSpecificInfo = (AudioSpecificConfig) r4;
        goto L17
    L7:
        r7 = null;
    L17:
        if (r10.remaining() <= 2) goto L18;
        long r42 = r10.position();
        BaseDescriptor r06 = ObjectDescriptorFactory.createFrom(this.objectTypeIndication, r10);
        long r62 = r10.position() - r42;
        Logger r43 = log;
        StringBuilder r52 = new StringBuilder();
        r52.append(r06);
        r52.append(" - DecoderConfigDescr2 read: ");
        r52.append(r62);
        r52.append(", size: ");
        if (r06 == null) goto L22;
        Integer r07 = Integer.valueOf(r06.getSize());
    L23:
        r52.append(r07);
        r43.finer(r52.toString());
        goto L17
    L22:
        r07 = null;
        goto L23
    }

    public ByteBuffer serialize() {
        ByteBuffer r0 = ByteBuffer.allocate(serializedSize());
        IsoTypeWriter.writeUInt8(r0, 4);
        IsoTypeWriter.writeUInt8(r0, serializedSize() - 2);
        IsoTypeWriter.writeUInt8(r0, this.objectTypeIndication);
        IsoTypeWriter.writeUInt8(r0, ((this.streamType << 2) | (this.upStream << 1)) | 1);
        IsoTypeWriter.writeUInt24(r0, this.bufferSizeDB);
        IsoTypeWriter.writeUInt32(r0, this.maxBitRate);
        IsoTypeWriter.writeUInt32(r0, this.avgBitRate);
        AudioSpecificConfig r3 = this.audioSpecificInfo;
        if (r3 == null) goto L5;
        r0.put(r3.serialize().array());
    L5:
        return r0;
    }

    public int serializedSize() {
        AudioSpecificConfig r0 = this.audioSpecificInfo;
        if (r0 != null) goto L5;
        int r02 = 0;
    L7:
        return r02 + 15;
    L5:
        r02 = r0.serializedSize();
        goto L7
    }

    public void setAudioSpecificInfo(AudioSpecificConfig r1) {
        this.audioSpecificInfo = r1;
    }

    public void setAvgBitRate(long r1) {
        this.avgBitRate = r1;
    }

    public void setBufferSizeDB(int r1) {
        this.bufferSizeDB = r1;
    }

    public void setMaxBitRate(long r1) {
        this.maxBitRate = r1;
    }

    public void setObjectTypeIndication(int r1) {
        this.objectTypeIndication = r1;
    }

    public void setStreamType(int r1) {
        this.streamType = r1;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("DecoderConfigDescriptor{objectTypeIndication=");
        r0.append(this.objectTypeIndication);
        r0.append(", streamType=");
        r0.append(this.streamType);
        r0.append(", upStream=");
        r0.append(this.upStream);
        r0.append(", bufferSizeDB=");
        r0.append(this.bufferSizeDB);
        r0.append(", maxBitRate=");
        r0.append(this.maxBitRate);
        r0.append(", avgBitRate=");
        r0.append(this.avgBitRate);
        r0.append(", decoderSpecificInfo=null, audioSpecificInfo=");
        r0.append(this.audioSpecificInfo);
        r0.append(", configDescriptorDeadBytes=");
        byte[] r1 = this.configDescriptorDeadBytes;
        if (r1 != null) goto L6;
        r1 = new byte[0];
    L6:
        r0.append(Hex.encodeHex(r1));
        r0.append(", profileLevelIndicationDescriptors=");
        List<ProfileLevelIndicationDescriptor> r3 = this.profileLevelIndicationDescriptors;
        if (r3 != null) goto L9;
        String r32 = "null";
    L10:
        r0.append(r32);
        r0.append('}');
        return r0.toString();
    L9:
        r32 = Arrays.asList(new List[]{r3}).toString();
        goto L10
    }
}
