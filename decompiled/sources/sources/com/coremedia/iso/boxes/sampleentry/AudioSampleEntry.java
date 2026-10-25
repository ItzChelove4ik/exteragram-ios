package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes4.dex */
public final class AudioSampleEntry extends AbstractSampleEntry {
    private long bytesPerFrame;
    private long bytesPerPacket;
    private long bytesPerSample;
    private int channelCount;
    private int compressionId;
    private int packetSize;
    private int reserved1;
    private long reserved2;
    private long sampleRate;
    private int sampleSize;
    private long samplesPerPacket;
    private int soundVersion;
    private byte[] soundVersion2Data;

    static {
    }

    public AudioSampleEntry(String r1) {
        super(r1);
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel r9) {
        r9.write(getHeader());
        int r0 = this.soundVersion;
        int r1 = 0;
        if (r0 != 1) goto L5;
        int r4 = 16;
    L6:
        int r42 = r4 + 28;
        if (r0 != 2) goto L9;
        r1 = 36;
    L9:
        ByteBuffer r02 = ByteBuffer.allocate(r42 + r1);
        r02.position(6);
        IsoTypeWriter.writeUInt16(r02, this.dataReferenceIndex);
        IsoTypeWriter.writeUInt16(r02, this.soundVersion);
        IsoTypeWriter.writeUInt16(r02, this.reserved1);
        IsoTypeWriter.writeUInt32(r02, this.reserved2);
        IsoTypeWriter.writeUInt16(r02, this.channelCount);
        IsoTypeWriter.writeUInt16(r02, this.sampleSize);
        IsoTypeWriter.writeUInt16(r02, this.compressionId);
        IsoTypeWriter.writeUInt16(r02, this.packetSize);
        if (this.type.equals("mlpa") == false) goto L12;
        IsoTypeWriter.writeUInt32(r02, getSampleRate());
    L14:
        if (this.soundVersion != 1) goto L17;
        IsoTypeWriter.writeUInt32(r02, this.samplesPerPacket);
        IsoTypeWriter.writeUInt32(r02, this.bytesPerPacket);
        IsoTypeWriter.writeUInt32(r02, this.bytesPerFrame);
        IsoTypeWriter.writeUInt32(r02, this.bytesPerSample);
    L17:
        if (this.soundVersion != 2) goto L19;
        IsoTypeWriter.writeUInt32(r02, this.samplesPerPacket);
        IsoTypeWriter.writeUInt32(r02, this.bytesPerPacket);
        IsoTypeWriter.writeUInt32(r02, this.bytesPerFrame);
        IsoTypeWriter.writeUInt32(r02, this.bytesPerSample);
        r02.put(this.soundVersion2Data);
    L19:
        r9.write((ByteBuffer) r02.rewind());
        writeContainer(r9);
        return;
    L12:
        IsoTypeWriter.writeUInt32(r02, getSampleRate() << 16);
        goto L14
    L5:
        r4 = 0;
        goto L6
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public long getSampleRate() {
        return this.sampleRate;
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public long getSize() {
        int r0 = this.soundVersion;
        int r2 = 0;
        int r3 = 16;
        if (r0 != 1) goto L5;
        int r1 = 16;
    L6:
        int r12 = r1 + 28;
        if (r0 != 2) goto L9;
        r2 = 36;
    L9:
        long r02 = (r12 + r2) + getContainerSize();
        if (this.largeBox == true) goto L16;
        if ((8 + r02) >= 4294967296L) goto L16;
        r3 = 8;
    L16:
        return r02 + r3;
    L5:
        r1 = 0;
        goto L6
    }

    public void setChannelCount(int r1) {
        this.channelCount = r1;
    }

    public void setSampleRate(long r1) {
        this.sampleRate = r1;
    }

    public void setSampleSize(int r1) {
        this.sampleSize = r1;
    }

    @Override // com.googlecode.mp4parser.BasicContainer
    public String toString() {
        return "AudioSampleEntry{bytesPerSample=" + this.bytesPerSample + ", bytesPerFrame=" + this.bytesPerFrame + ", bytesPerPacket=" + this.bytesPerPacket + ", samplesPerPacket=" + this.samplesPerPacket + ", packetSize=" + this.packetSize + ", compressionId=" + this.compressionId + ", soundVersion=" + this.soundVersion + ", sampleRate=" + this.sampleRate + ", sampleSize=" + this.sampleSize + ", channelCount=" + this.channelCount + ", boxes=" + getBoxes() + '}';
    }
}
