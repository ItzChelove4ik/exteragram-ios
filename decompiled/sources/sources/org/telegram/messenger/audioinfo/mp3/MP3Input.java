package org.telegram.messenger.audioinfo.mp3;

import java.io.InputStream;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.audioinfo.util.PositionInputStream;

/* loaded from: classes5.dex */
public class MP3Input extends PositionInputStream {
    public int exceptionsCount;

    public MP3Input(InputStream r1) {
        super(r1);
        this.exceptionsCount = 0;
    }

    public final void readFully(byte[] r4, int r5, int r6) {
        int r0 = 0;
    L3:
        if (r0 >= r6) goto L11;
        int r1 = read(r4, r5 + r0, r6 - r0);
        if (r1 <= 0) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }

    public void skipFully(long r8) {
        long r2 = 0;
    L4:
        if (r2 >= r8) goto L12;
        long r4 = skip(r8 - r2);
        if (r4 <= 0) goto L8;
        r2 = r2 + r4;
        goto L4
    L8:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }

    public String toString() {
        return "mp3[pos=" + getPosition() + "]";
    }
}
