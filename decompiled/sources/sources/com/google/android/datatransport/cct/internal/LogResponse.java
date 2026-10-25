package com.google.android.datatransport.cct.internal;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;
import java.io.Reader;

/* loaded from: classes4.dex */
public abstract class LogResponse {
    public LogResponse() {
    }

    public static LogResponse create(long r1) {
        return new AutoValue_LogResponse(r1);
    }

    public static LogResponse fromJson(Reader r3) {
        JsonReader r0 = new JsonReader(r3);
        r0.beginObject();     // Catch: Throwable -> L13
    L5:
        if (r0.hasNext() == false) goto L20;
        if (r0.nextName().equals("nextRequestWaitMillis") == true) goto L9;
        r0.skipValue();     // Catch: Throwable -> L13
        goto L5
    L9:
        if (r0.peek() != JsonToken.STRING) goto L15;
        LogResponse r32 = create(Long.parseLong(r0.nextString()));     // Catch: Throwable -> L13
        r0.close();
        return r32;
    L15:
        LogResponse r33 = create(r0.nextLong());     // Catch: Throwable -> L13
        r0.close();
        return r33;
    L20:
        throw new IOException("Response is missing nextRequestWaitMillis field.");     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        r0.close();
        throw th;
    }

    public abstract long getNextRequestWaitMillis();
}
