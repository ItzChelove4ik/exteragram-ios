package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzabe {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Comparator zzb = null;
    private static final zzabe zzc = null;
    private final zzabc zzd;

    static {
        zzb = new zzaax();
        zzc = new zzabe(new zzabc(Collections.EMPTY_LIST));
    }

    private zzabe(zzabc r1) {
        this.zzd = r1;
    }

    public static zzabe zza() {
        return zzc;
    }

    public static /* synthetic */ Comparator zze() {
        return zzb;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzabe) == true) goto L5;
        return false;
    L5:
        if (((zzabe) r2).zzd.equals(this.zzd) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        return ~this.zzd.hashCode();
    }

    public final String toString() {
        return this.zzd.toString();
    }

    public final Map zzb() {
        return this.zzd;
    }

    public final boolean zzc() {
        return this.zzd.isEmpty();
    }

    public final zzabe zzd(zzabe r3) {
        zzabc r0 = r3.zzd;
        if (r0.isEmpty() == true) goto L9;
        zzabc r2 = this.zzd;
        if (r2.isEmpty() == false) goto L8;
        return r3;
    L8:
        return new zzabe(new zzabc(r2, r0));
    L9:
        return this;
    }
}
