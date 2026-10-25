package org.telegram.messenger.audioinfo.mp3;

import java.io.InputStream;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Buffer$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ID3v2DataInput {
    private final InputStream input;

    public ID3v2DataInput(InputStream r1) {
        this.input = r1;
    }

    public byte readByte() {
        int r0 = this.input.read();
        if (r0 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L5:
        return (byte) r0;
    }

    public final void readFully(byte[] r5, int r6, int r7) {
        int r0 = 0;
    L3:
        if (r0 >= r7) goto L11;
        int r1 = this.input.read(r5, r6 + r0, r7 - r0);
        if (r1 <= 0) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }

    public byte[] readFully(int r3) {
        byte[] r0 = new byte[r3];
        readFully(r0, 0, r3);
        return r0;
    }

    public int readInt() {
        int r0 = (((readByte() & UByte.MAX_VALUE) << 24) | ((readByte() & UByte.MAX_VALUE) << 16)) | ((readByte() & UByte.MAX_VALUE) << 8);
        return (readByte() & UByte.MAX_VALUE) | r0;
    }

    public int readSyncsafeInt() {
        int r0 = (((readByte() & ByteCompanionObject.MAX_VALUE) << 21) | ((readByte() & ByteCompanionObject.MAX_VALUE) << 14)) | ((readByte() & ByteCompanionObject.MAX_VALUE) << 7);
        return (readByte() & ByteCompanionObject.MAX_VALUE) | r0;
    }

    public void skipFully(long r8) {
        long r2 = 0;
    L4:
        if (r2 >= r8) goto L12;
        long r4 = this.input.skip(r8 - r2);
        if (r4 <= 0) goto L8;
        r2 = r2 + r4;
        goto L4
    L8:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }
}
