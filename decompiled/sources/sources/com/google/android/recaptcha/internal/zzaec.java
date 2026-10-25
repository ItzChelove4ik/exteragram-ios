package com.google.android.recaptcha.internal;

import com.google.android.gms.internal.cast.zzwz$$ExternalSyntheticBUOutline0;
import com.google.android.recaptcha.internal.zzaeb;
import com.google.android.recaptcha.internal.zzaec;
import java.io.IOException;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class zzaec<MessageType extends zzaec<MessageType, BuilderType>, BuilderType extends zzaeb<MessageType, BuilderType>> implements zzagx {
    protected transient int zzb;

    public zzaec() {
        this.zzb = 0;
    }

    public static void zzy(Iterable r0, List r1) {
        zzaeb.zzj(r0, r1);
    }

    public final zzaer zzv() {
        int r1 = zzJ();     // Catch: IOException -> L5
        zzaer r2 = zzaer.zza;     // Catch: IOException -> L5
        byte[] r22 = new byte[r1];     // Catch: IOException -> L5
        zzaex r3 = new zzaex(r22, 0, r1, null);     // Catch: IOException -> L5
        zzI(r3);     // Catch: IOException -> L5
        r3.zzw();     // Catch: IOException -> L5
        return new zzaep(r22);
    L5:
        e = move-exception;
        String r5 = getClass().getName();
        zzwz$$ExternalSyntheticBUOutline0.m(r5.length() + 72, r5, " to a ByteString threw an IOException (should never happen).", e);
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzagx
    public final byte[] zzw() {
        int r1 = zzJ();     // Catch: IOException -> L5
        byte[] r2 = new byte[r1];     // Catch: IOException -> L5
        zzaex r3 = new zzaex(r2, 0, r1, null);     // Catch: IOException -> L5
        zzI(r3);     // Catch: IOException -> L5
        r3.zzw();     // Catch: IOException -> L5
        return r2;
    L5:
        e = move-exception;
        String r5 = getClass().getName();
        zzwz$$ExternalSyntheticBUOutline0.m(r5.length() + 72, r5, " to a byte array threw an IOException (should never happen).", e);
        return null;
    }

    public abstract int zzx(zzahn r1);
}
