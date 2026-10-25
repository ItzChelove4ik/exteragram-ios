package com.google.android.recaptcha.internal;

import android.content.Context;
import java.io.File;
import kotlin.io.FilesKt;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzop {
    public zzop(Context r1) {
    }

    public static final byte[] zza(File r0) {
        return FilesKt.readBytes(r0);
    }

    public static final void zzb(File r1, byte[] r2) {
        if (r1.exists() == true) goto L5;
    L9:
        FilesKt.writeBytes(r1, r2);
        return;
    L5:
        if (r1.delete() == true) goto L9;
        Model$$ExternalSyntheticBUOutline0.m("Unable to delete existing encrypted file");
    }
}
