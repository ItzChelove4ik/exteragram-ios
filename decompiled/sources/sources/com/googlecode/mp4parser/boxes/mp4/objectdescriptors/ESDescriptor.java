package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Descriptor(tags = {3})
/* loaded from: classes5.dex */
public class ESDescriptor extends BaseDescriptor {
    private static Logger log;
    int URLFlag;
    int URLLength;
    String URLString;
    DecoderConfigDescriptor decoderConfigDescriptor;
    int dependsOnEsId;
    int esId;
    int oCREsId;
    int oCRstreamFlag;
    List<BaseDescriptor> otherDescriptors;
    int remoteODFlag;
    SLConfigDescriptor slConfigDescriptor;
    int streamDependenceFlag;
    int streamPriority;

    static {
        log = Logger.getLogger(ESDescriptor.class.getName());
    }

    public ESDescriptor() {
        this.URLLength = 0;
        this.otherDescriptors = new ArrayList();
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L66:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L66;
        ESDescriptor r52 = (ESDescriptor) r5;
        if (this.URLFlag == r52.URLFlag) goto L14;
        return false;
    L14:
        if (this.URLLength == r52.URLLength) goto L17;
        return false;
    L17:
        if (this.dependsOnEsId == r52.dependsOnEsId) goto L20;
        return false;
    L20:
        if (this.esId == r52.esId) goto L23;
        return false;
    L23:
        if (this.oCREsId == r52.oCREsId) goto L26;
        return false;
    L26:
        if (this.oCRstreamFlag == r52.oCRstreamFlag) goto L29;
        return false;
    L29:
        if (this.remoteODFlag == r52.remoteODFlag) goto L32;
        return false;
    L32:
        if (this.streamDependenceFlag == r52.streamDependenceFlag) goto L35;
        return false;
    L35:
        if (this.streamPriority == r52.streamPriority) goto L37;
        return false;
    L37:
        String r2 = this.URLString;
        String r3 = r52.URLString;
        if (r2 != null) goto L40;
        if (r3 == null) goto L44;
    L43:
        return false;
    L44:
        DecoderConfigDescriptor r22 = this.decoderConfigDescriptor;
        DecoderConfigDescriptor r32 = r52.decoderConfigDescriptor;
        if (r22 != null) goto L47;
        if (r32 == null) goto L51;
    L50:
        return false;
    L51:
        List<BaseDescriptor> r23 = this.otherDescriptors;
        List<BaseDescriptor> r33 = r52.otherDescriptors;
        if (r23 != null) goto L54;
        if (r33 == null) goto L58;
    L57:
        return false;
    L58:
        SLConfigDescriptor r4 = this.slConfigDescriptor;
        SLConfigDescriptor r53 = r52.slConfigDescriptor;
        if (r4 != null) goto L61;
        if (r53 == null) goto L65;
    L64:
        return false;
    L65:
        return true;
    L61:
        if (r4.equals(r53) == true) goto L65;
    L54:
        if (r23.equals(r33) == true) goto L58;
    L47:
        if (r22.equals(r32) == true) goto L51;
    L40:
        if (r2.equals(r3) == true) goto L44;
        goto L44
    }

    public int hashCode() {
        int r0 = ((((((((((this.esId * 31) + this.streamDependenceFlag) * 31) + this.URLFlag) * 31) + this.oCRstreamFlag) * 31) + this.streamPriority) * 31) + this.URLLength) * 31;
        String r1 = this.URLString;
        int r2 = 0;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L6:
        int r02 = (((((((r0 + r12) * 31) + this.remoteODFlag) * 31) + this.dependsOnEsId) * 31) + this.oCREsId) * 31;
        DecoderConfigDescriptor r13 = this.decoderConfigDescriptor;
        if (r13 == null) goto L9;
        int r14 = r13.hashCode();
    L10:
        int r03 = (r02 + r14) * 31;
        SLConfigDescriptor r15 = this.slConfigDescriptor;
        if (r15 == null) goto L13;
        int r16 = r15.hashCode();
    L14:
        int r04 = (r03 + r16) * 31;
        List<BaseDescriptor> r3 = this.otherDescriptors;
        if (r3 == null) goto L18;
        r2 = r3.hashCode();
    L18:
        return r04 + r2;
    L13:
        r16 = 0;
        goto L14
    L9:
        r14 = 0;
        goto L10
    L5:
        r12 = 0;
        goto L6
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer r13) {
        this.esId = IsoTypeReader.readUInt16(r13);
        int r0 = IsoTypeReader.readUInt8(r13);
        int r1 = r0 >>> 7;
        this.streamDependenceFlag = r1;
        this.URLFlag = (r0 >>> 6) & 1;
        this.oCRstreamFlag = (r0 >>> 5) & 1;
        this.streamPriority = r0 & 31;
        if (r1 != 1) goto L6;
        this.dependsOnEsId = IsoTypeReader.readUInt16(r13);
    L6:
        if (this.URLFlag != 1) goto L9;
        int r02 = IsoTypeReader.readUInt8(r13);
        this.URLLength = r02;
        this.URLString = IsoTypeReader.readString(r13, r02);
    L9:
        if (this.oCRstreamFlag != 1) goto L11;
        this.oCREsId = IsoTypeReader.readUInt16(r13);
    L11:
        int r03 = getSizeBytes() + 4;
        int r2 = 0;
        if (this.streamDependenceFlag != 1) goto L14;
        int r12 = 2;
    L15:
        int r04 = r03 + r12;
        if (this.URLFlag != 1) goto L18;
        int r14 = this.URLLength + 1;
    L19:
        int r05 = r04 + r14;
        if (this.oCRstreamFlag != 1) goto L22;
        r2 = 2;
    L22:
        int r06 = r05 + r2;
        int r15 = r13.position();
        if (getSize() <= (r06 + 2)) goto L35;
        BaseDescriptor r22 = ObjectDescriptorFactory.createFrom(-1, r13);
        long r8 = r13.position() - r15;
        Logger r3 = log;
        StringBuilder r10 = new StringBuilder();
        r10.append(r22);
        r10.append(" - ESDescriptor1 read: ");
        r10.append(r8);
        r10.append(", size: ");
        if (r22 == null) goto L27;
        Integer r11 = Integer.valueOf(r22.getSize());
    L28:
        r10.append(r11);
        r3.finer(r10.toString());
        if (r22 == null) goto L31;
        int r32 = r22.getSize();
        r13.position(r15 + r32);
        r06 = r06 + r32;
    L33:
        if ((r22 instanceof DecoderConfigDescriptor) == false) goto L35;
        this.decoderConfigDescriptor = (DecoderConfigDescriptor) r22;
        goto L35
    L31:
        r06 = (int) (r06 + r8);
        goto L33
    L27:
        r11 = null;
    L35:
        int r16 = r13.position();
        if (getSize() <= (r06 + 2)) goto L48;
        BaseDescriptor r23 = ObjectDescriptorFactory.createFrom(-1, r13);
        long r82 = r13.position() - r16;
        Logger r33 = log;
        StringBuilder r102 = new StringBuilder();
        r102.append(r23);
        r102.append(" - ESDescriptor2 read: ");
        r102.append(r82);
        r102.append(", size: ");
        if (r23 == null) goto L40;
        Integer r112 = Integer.valueOf(r23.getSize());
    L41:
        r102.append(r112);
        r33.finer(r102.toString());
        if (r23 == null) goto L44;
        int r34 = r23.getSize();
        r13.position(r16 + r34);
        r06 = r06 + r34;
    L46:
        if ((r23 instanceof SLConfigDescriptor) == false) goto L50;
        this.slConfigDescriptor = (SLConfigDescriptor) r23;
    L50:
        if ((getSize() - r06) <= 2) goto L51;
        int r17 = r13.position();
        BaseDescriptor r24 = ObjectDescriptorFactory.createFrom(-1, r13);
        long r83 = r13.position() - r17;
        Logger r35 = log;
        StringBuilder r103 = new StringBuilder();
        r103.append(r24);
        r103.append(" - ESDescriptor3 read: ");
        r103.append(r83);
        r103.append(", size: ");
        if (r24 == null) goto L55;
        Integer r113 = Integer.valueOf(r24.getSize());
    L56:
        r103.append(r113);
        r35.finer(r103.toString());
        if (r24 == null) goto L59;
        int r36 = r24.getSize();
        r13.position(r17 + r36);
        r06 = r06 + r36;
    L60:
        this.otherDescriptors.add(r24);
        goto L50
    L59:
        r06 = (int) (r06 + r83);
        goto L60
    L55:
        r113 = null;
        goto L56
    L51:
        return;
    L44:
        r06 = (int) (r06 + r82);
        goto L46
    L40:
        r112 = null;
        goto L41
    L48:
        log.warning("SLConfigDescriptor is missing!");
        goto L50
    L18:
        r14 = 0;
        goto L19
    L14:
        r12 = 0;
        goto L15
    }

    public ByteBuffer serialize() {
        ByteBuffer r0 = ByteBuffer.allocate(serializedSize());
        IsoTypeWriter.writeUInt8(r0, 3);
        IsoTypeWriter.writeUInt8(r0, serializedSize() - 2);
        IsoTypeWriter.writeUInt16(r0, this.esId);
        IsoTypeWriter.writeUInt8(r0, (((this.streamDependenceFlag << 7) | (this.URLFlag << 6)) | (this.oCRstreamFlag << 5)) | (this.streamPriority & 31));
        if (this.streamDependenceFlag <= 0) goto L6;
        IsoTypeWriter.writeUInt16(r0, this.dependsOnEsId);
    L6:
        if (this.URLFlag <= 0) goto L9;
        IsoTypeWriter.writeUInt8(r0, this.URLLength);
        IsoTypeWriter.writeUtf8String(r0, this.URLString);
    L9:
        if (this.oCRstreamFlag <= 0) goto L11;
        IsoTypeWriter.writeUInt16(r0, this.oCREsId);
    L11:
        ByteBuffer r1 = this.decoderConfigDescriptor.serialize();
        ByteBuffer r3 = this.slConfigDescriptor.serialize();
        r0.put(r1.array());
        r0.put(r3.array());
        return r0;
    }

    public int serializedSize() {
        if (this.streamDependenceFlag <= 0) goto L5;
        int r0 = 7;
    L7:
        if (this.URLFlag <= 0) goto L10;
        r0 = r0 + (this.URLLength + 1);
    L10:
        if (this.oCRstreamFlag <= 0) goto L13;
        r0 = r0 + 2;
    L13:
        return (r0 + this.decoderConfigDescriptor.serializedSize()) + this.slConfigDescriptor.serializedSize();
    L5:
        r0 = 5;
        goto L7
    }

    public void setDecoderConfigDescriptor(DecoderConfigDescriptor r1) {
        this.decoderConfigDescriptor = r1;
    }

    public void setEsId(int r1) {
        this.esId = r1;
    }

    public void setSlConfigDescriptor(SLConfigDescriptor r1) {
        this.slConfigDescriptor = r1;
    }

    public String toString() {
        return "ESDescriptor{esId=" + this.esId + ", streamDependenceFlag=" + this.streamDependenceFlag + ", URLFlag=" + this.URLFlag + ", oCRstreamFlag=" + this.oCRstreamFlag + ", streamPriority=" + this.streamPriority + ", URLLength=" + this.URLLength + ", URLString='" + this.URLString + "', remoteODFlag=" + this.remoteODFlag + ", dependsOnEsId=" + this.dependsOnEsId + ", oCREsId=" + this.oCREsId + ", decoderConfigDescriptor=" + this.decoderConfigDescriptor + ", slConfigDescriptor=" + this.slConfigDescriptor + '}';
    }
}
