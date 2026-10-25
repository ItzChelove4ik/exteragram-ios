package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;

@Descriptor(tags = {6})
/* loaded from: classes5.dex */
public class SLConfigDescriptor extends BaseDescriptor {
    int predefined;

    public SLConfigDescriptor() {
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L14:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L14;
        if (this.predefined == ((SLConfigDescriptor) r5).predefined) goto L13;
        return false;
    L13:
        return true;
    }

    public int hashCode() {
        return this.predefined;
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer r1) {
        this.predefined = IsoTypeReader.readUInt8(r1);
    }

    public ByteBuffer serialize() {
        ByteBuffer r0 = ByteBuffer.allocate(3);
        IsoTypeWriter.writeUInt8(r0, 6);
        IsoTypeWriter.writeUInt8(r0, 1);
        IsoTypeWriter.writeUInt8(r0, this.predefined);
        return r0;
    }

    public int serializedSize() {
        return 3;
    }

    public void setPredefined(int r1) {
        this.predefined = r1;
    }

    public String toString() {
        return "SLConfigDescriptor{predefined=" + this.predefined + '}';
    }
}
