package com.mp4parser.iso14496.part15;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitWriterBuffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class AvcDecoderConfigurationRecord {
    public int avcLevelIndication;
    public int avcProfileIndication;
    public int bitDepthChromaMinus8;
    public int bitDepthChromaMinus8PaddingBits;
    public int bitDepthLumaMinus8;
    public int bitDepthLumaMinus8PaddingBits;
    public int chromaFormat;
    public int chromaFormatPaddingBits;
    public int configurationVersion;
    public boolean hasExts;
    public int lengthSizeMinusOne;
    public int lengthSizeMinusOnePaddingBits;
    public int numberOfSequenceParameterSetsPaddingBits;
    public List<byte[]> pictureParameterSets;
    public int profileCompatibility;
    public List<byte[]> sequenceParameterSetExts;
    public List<byte[]> sequenceParameterSets;

    public AvcDecoderConfigurationRecord() {
        this.sequenceParameterSets = new ArrayList();
        this.pictureParameterSets = new ArrayList();
        this.hasExts = true;
        this.chromaFormat = 1;
        this.bitDepthLumaMinus8 = 0;
        this.bitDepthChromaMinus8 = 0;
        this.sequenceParameterSetExts = new ArrayList();
        this.lengthSizeMinusOnePaddingBits = 63;
        this.numberOfSequenceParameterSetsPaddingBits = 7;
        this.chromaFormatPaddingBits = 31;
        this.bitDepthLumaMinus8PaddingBits = 31;
        this.bitDepthChromaMinus8PaddingBits = 31;
    }

    public AvcDecoderConfigurationRecord(ByteBuffer r12) {
        this.sequenceParameterSets = new ArrayList();
        this.pictureParameterSets = new ArrayList();
        this.hasExts = true;
        this.chromaFormat = 1;
        int r0 = 0;
        this.bitDepthLumaMinus8 = 0;
        this.bitDepthChromaMinus8 = 0;
        this.sequenceParameterSetExts = new ArrayList();
        this.lengthSizeMinusOnePaddingBits = 63;
        this.numberOfSequenceParameterSetsPaddingBits = 7;
        this.chromaFormatPaddingBits = 31;
        this.bitDepthLumaMinus8PaddingBits = 31;
        this.bitDepthChromaMinus8PaddingBits = 31;
        this.configurationVersion = IsoTypeReader.readUInt8(r12);
        this.avcProfileIndication = IsoTypeReader.readUInt8(r12);
        this.profileCompatibility = IsoTypeReader.readUInt8(r12);
        this.avcLevelIndication = IsoTypeReader.readUInt8(r12);
        BitReaderBuffer r1 = new BitReaderBuffer(r12);
        this.lengthSizeMinusOnePaddingBits = r1.readBits(6);
        this.lengthSizeMinusOne = r1.readBits(2);
        this.numberOfSequenceParameterSetsPaddingBits = r1.readBits(3);
        int r13 = r1.readBits(5);
        int r6 = 0;
    L3:
        if (r6 >= r13) goto L4;
        byte[] r7 = new byte[IsoTypeReader.readUInt16(r12)];
        r12.get(r7);
        this.sequenceParameterSets.add(r7);
        r6 = r6 + 1;
        goto L3
    L4:
        long r72 = IsoTypeReader.readUInt8(r12);
        int r14 = 0;
    L6:
        if (r14 >= r72) goto L8;
        byte[] r62 = new byte[IsoTypeReader.readUInt16(r12)];
        r12.get(r62);
        this.pictureParameterSets.add(r62);
        r14 = r14 + 1;
        goto L6
    L8:
        if (r12.remaining() >= 4) goto L11;
        this.hasExts = false;
    L11:
        if (this.hasExts == false) goto L25;
        int r15 = this.avcProfileIndication;
        if (r15 != 100) goto L15;
    L20:
        BitReaderBuffer r16 = new BitReaderBuffer(r12);
        this.chromaFormatPaddingBits = r16.readBits(6);
        this.chromaFormat = r16.readBits(2);
        this.bitDepthLumaMinus8PaddingBits = r16.readBits(5);
        this.bitDepthLumaMinus8 = r16.readBits(3);
        this.bitDepthChromaMinus8PaddingBits = r16.readBits(5);
        this.bitDepthChromaMinus8 = r16.readBits(3);
        long r17 = IsoTypeReader.readUInt8(r12);
    L22:
        if (r0 >= r17) goto L23;
        byte[] r3 = new byte[IsoTypeReader.readUInt16(r12)];
        r12.get(r3);
        this.sequenceParameterSetExts.add(r3);
        r0 = r0 + 1;
        goto L22
    L23:
        return;
    L15:
        if (r15 == 110) goto L20;
        if (r15 == 122) goto L20;
        if (r15 == 144) goto L20;
    L25:
        this.chromaFormat = -1;
        this.bitDepthLumaMinus8 = -1;
        this.bitDepthChromaMinus8 = -1;
    }

    public void getContent(ByteBuffer r8) {
        IsoTypeWriter.writeUInt8(r8, this.configurationVersion);
        IsoTypeWriter.writeUInt8(r8, this.avcProfileIndication);
        IsoTypeWriter.writeUInt8(r8, this.profileCompatibility);
        IsoTypeWriter.writeUInt8(r8, this.avcLevelIndication);
        BitWriterBuffer r0 = new BitWriterBuffer(r8);
        r0.writeBits(this.lengthSizeMinusOnePaddingBits, 6);
        r0.writeBits(this.lengthSizeMinusOne, 2);
        r0.writeBits(this.numberOfSequenceParameterSetsPaddingBits, 3);
        r0.writeBits(this.pictureParameterSets.size(), 5);
        Iterator<byte[]> r02 = this.sequenceParameterSets.iterator();
    L4:
        if (r02.hasNext() == false) goto L5;
        byte[] r1 = r02.next();
        IsoTypeWriter.writeUInt16(r8, r1.length);
        r8.put(r1);
        goto L4
    L5:
        IsoTypeWriter.writeUInt8(r8, this.pictureParameterSets.size());
        Iterator<byte[]> r12 = this.pictureParameterSets.iterator();
    L7:
        if (r12.hasNext() == false) goto L9;
        byte[] r03 = r12.next();
        IsoTypeWriter.writeUInt16(r8, r03.length);
        r8.put(r03);
        goto L7
    L9:
        if (this.hasExts == false) goto L23;
        int r04 = this.avcProfileIndication;
        if (r04 != 100) goto L13;
    L18:
        BitWriterBuffer r05 = new BitWriterBuffer(r8);
        r05.writeBits(this.chromaFormatPaddingBits, 6);
        r05.writeBits(this.chromaFormat, 2);
        r05.writeBits(this.bitDepthLumaMinus8PaddingBits, 5);
        r05.writeBits(this.bitDepthLumaMinus8, 3);
        r05.writeBits(this.bitDepthChromaMinus8PaddingBits, 5);
        r05.writeBits(this.bitDepthChromaMinus8, 3);
        Iterator<byte[]> r7 = this.sequenceParameterSetExts.iterator();
    L20:
        if (r7.hasNext() == false) goto L30;
        byte[] r06 = r7.next();
        IsoTypeWriter.writeUInt16(r8, r06.length);
        r8.put(r06);
        goto L20
    L30:
        return;
    L13:
        if (r04 == 110) goto L18;
        if (r04 == 122) goto L18;
        if (r04 == 144) goto L18;
        return;
    }

    public long getContentSize() {
        long r1 = 6;
    L4:
        if (this.sequenceParameterSets.iterator().hasNext() == false) goto L5;
        r1 = (r1 + 2) + r0.next().length;
        goto L4
    L5:
        long r12 = r1 + 1;
    L7:
        if (this.pictureParameterSets.iterator().hasNext() == false) goto L9;
        r12 = (r12 + 2) + r3.next().length;
        goto L7
    L9:
        if (this.hasExts == false) goto L23;
        int r0 = this.avcProfileIndication;
        if (r0 != 100) goto L13;
    L18:
        long r13 = r12 + 4;
    L20:
        if (this.sequenceParameterSetExts.iterator().hasNext() == false) goto L21;
        r13 = (r13 + 2) + r8.next().length;
        goto L20
    L21:
        return r13;
    L13:
        if (r0 == 110) goto L18;
        if (r0 == 122) goto L18;
        if (r0 == 144) goto L18;
    L23:
        return r12;
    }

    public String toString() {
        return "AvcDecoderConfigurationRecord{configurationVersion=" + this.configurationVersion + ", avcProfileIndication=" + this.avcProfileIndication + ", profileCompatibility=" + this.profileCompatibility + ", avcLevelIndication=" + this.avcLevelIndication + ", lengthSizeMinusOne=" + this.lengthSizeMinusOne + ", hasExts=" + this.hasExts + ", chromaFormat=" + this.chromaFormat + ", bitDepthLumaMinus8=" + this.bitDepthLumaMinus8 + ", bitDepthChromaMinus8=" + this.bitDepthChromaMinus8 + ", lengthSizeMinusOnePaddingBits=" + this.lengthSizeMinusOnePaddingBits + ", numberOfSequenceParameterSetsPaddingBits=" + this.numberOfSequenceParameterSetsPaddingBits + ", chromaFormatPaddingBits=" + this.chromaFormatPaddingBits + ", bitDepthLumaMinus8PaddingBits=" + this.bitDepthLumaMinus8PaddingBits + ", bitDepthChromaMinus8PaddingBits=" + this.bitDepthChromaMinus8PaddingBits + '}';
    }
}
