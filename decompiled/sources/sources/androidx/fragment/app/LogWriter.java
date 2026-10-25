package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;

/* loaded from: classes3.dex */
final class LogWriter extends Writer {
    private StringBuilder mBuilder;
    private final String mTag;

    public LogWriter(String r3) {
        this.mBuilder = new StringBuilder(128);
        this.mTag = r3;
    }

    private void flushBuilder() {
        if (this.mBuilder.length() <= 0) goto L6;
        Log.d(this.mTag, this.mBuilder.toString());
        StringBuilder r2 = this.mBuilder;
        r2.delete(0, r2.length());
        return;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flushBuilder();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        flushBuilder();
    }

    @Override // java.io.Writer
    public void write(char[] r4, int r5, int r6) {
        int r0 = 0;
    L3:
        if (r0 >= r6) goto L9;
        char r1 = r4[r5 + r0];
        if (r1 != '\n') goto L7;
        flushBuilder();
    L8:
        r0 = r0 + 1;
        goto L3
    L7:
        this.mBuilder.append(r1);
        goto L8
    }
}
