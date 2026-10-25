package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.SystemClock;
import java.io.File;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzry {
    public static File zza(Context r2) {
        File r0 = r2.getFilesDir();
        if (r0 != null) goto L9;
        SystemClock.sleep(100);
        File r22 = r2.getFilesDir();
        if (r22 == null) goto L7;
        return r22;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("getFilesDir returned null twice.");
        return null;
    L9:
        return r0;
    }
}
