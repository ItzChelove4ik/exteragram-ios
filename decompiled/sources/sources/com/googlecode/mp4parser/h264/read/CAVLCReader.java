package com.googlecode.mp4parser.h264.read;

import com.googlecode.mp4parser.h264.Debug;
import java.io.InputStream;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public class CAVLCReader extends BitstreamReader {
    public CAVLCReader(InputStream r1) {
        super(r1);
    }

    private int readUE() {
        int r1 = 0;
    L4:
        if (read1Bit() != 0) goto L5;
        r1 = r1 + 1;
        goto L4
    L5:
        if (r1 > 0) goto L7;
        return 0;
    L7:
        return (int) (((1 << r1) - 1) + readNBit(r1));
    }

    private void trace(String r7, String r8) {
        StringBuilder r0 = new StringBuilder();
        String r1 = String.valueOf(BitstreamReader.bitsRead - this.debugBits.length());
        int r2 = 8 - r1.length();
        r0.append("@".concat(r1));
        int r12 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L5;
        r0.append(' ');
        r3 = r3 + 1;
        goto L4
    L5:
        r0.append(r7);
        int r5 = (100 - r0.length()) - this.debugBits.length();
    L6:
        if (r12 >= r5) goto L7;
        r0.append(' ');
        r12 = r12 + 1;
        goto L6
    L7:
        r0.append(this.debugBits);
        r0.append(" (" + r8 + ")");
        this.debugBits.clear();
        Debug.println(r0.toString());
    }

    public boolean readBool(String r3) {
        if (read1Bit() != 0) goto L5;
        boolean r0 = false;
    L6:
        if (r0 == false) goto L8;
        String r1 = "1";
    L9:
        trace(r3, r1);
        return r0;
    L8:
        r1 = MVEL.VERSION_SUB;
        goto L9
    L5:
        r0 = true;
        goto L6
    }

    public long readNBit(int r3, String r4) {
        long r0 = readNBit(r3);
        trace(r4, String.valueOf(r0));
        return r0;
    }

    public int readU(int r1, String r2) {
        return (int) readNBit(r1, r2);
    }

    public int readUE(String r3) {
        int r0 = readUE();
        trace(r3, String.valueOf(r0));
        return r0;
    }
}
