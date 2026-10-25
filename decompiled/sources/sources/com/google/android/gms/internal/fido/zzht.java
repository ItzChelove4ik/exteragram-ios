package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzht {
    private final Deque zza;

    private zzht(boolean r2) {
        this.zza = new ArrayDeque(16);
    }

    public static zzht zza() {
        return new zzht(false);
    }

    private final long zzh() {
        if (this.zza.isEmpty() == false) goto L7;
        return 0;
    L7:
        return ((Long) this.zza.peek()).longValue();
    }

    private final void zzi(long r2) {
        this.zza.pop();
        this.zza.push(Long.valueOf(r2));
    }

    public final void zzb() {
        if (this.zza.isEmpty() == false) goto L6;
        return;
    L6:
        throw new IOException(String.format("data item not completed, stackSize: %s scope: %s", new Object[]{Integer.valueOf(this.zza.size()), Long.valueOf(zzh())}));
    }

    public final void zzc() {
        long r0 = zzh();
        if (r0 >= 0) goto L11;
        if (r0 == (-5)) goto L8;
        this.zza.pop();
        return;
    L8:
        Model$$ExternalSyntheticBUOutline0.m("expected a value for dangling key in indefinite-length map");
        return;
    L11:
        throw new IOException(String.format("expected indefinite length scope but found %s", new Object[]{Long.valueOf(r0)}));
    }

    public final void zzd() {
        long r0 = zzh();
        if (r0 == (-1)) goto L9;
        if (r0 == (-2)) goto L7;
        return;
    L7:
        r0 = -2;
    L9:
        throw new IOException(String.format("expected non-string scope but found %s", new Object[]{Long.valueOf(r0)}));
    }

    public final void zze(long r5) {
        long r0 = zzh();
        if (r0 != r5) goto L5;
        return;
    L5:
        if (r0 == (-1)) goto L11;
        if (r0 != (-2)) goto L13;
        r0 = -2;
        goto L11
    L13:
        return;
    L11:
        throw new IOException(String.format("expected non-string scope or scope %s but found %s", new Object[]{Long.valueOf(r5), Long.valueOf(r0)}));
    }

    public final void zzf() {
        long r0 = zzh();
        if (r0 != 1) goto L6;
        this.zza.pop();
        return;
    L6:
        if (r0 <= 1) goto L10;
        zzi(r0 - 1);
        return;
    L10:
        if (r0 != (-4)) goto L14;
        zzi(-5);
        return;
    L14:
        if (r0 != (-5)) goto L17;
        zzi(-4);
        return;
    }

    public final void zzg(long r1) {
        this.zza.push(Long.valueOf(r1));
    }
}
