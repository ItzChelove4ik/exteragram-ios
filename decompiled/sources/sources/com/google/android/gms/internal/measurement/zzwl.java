package com.google.android.gms.internal.measurement;

import androidx.collection.SimpleArrayMap;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzwl {
    private static final zzwj zza = null;
    private final zzwl zzb;
    private final SimpleArrayMap zzc;
    private boolean zzd;

    static {
        zza = zzwj.zza(Boolean.class);
    }

    public /* synthetic */ zzwl(zzwl r1, SimpleArrayMap r2, byte[] r3) {
        this.zzd = false;
        if (r1 == null) goto L5;
        Preconditions.checkArgument(r1.zzd);
    L5:
        this.zzb = r1;
        this.zzc = r2;
    }

    public static zzwl zza(zzwl r7, zzwl r8) {
        if (r7.zzc() == false) goto L6;
        return r8;
    L6:
        if (r8.zzc() == false) goto L8;
        return r7;
    L8:
        ImmutableSet r72 = ImmutableSet.of(r7, r8);
        if (r72.isEmpty() == false) goto L13;
        return zzwk.zza;
    L13:
        if (r72.size() == 1) goto L15;
        Iterator<E> r82 = r72.iterator();
        int r2 = 0;
    L18:
        if (r82.hasNext() == false) goto L23;
        zzwl r3 = (zzwl) r82.next();
    L20:
        r2 = r2 + r3.zzc.size();
        r3 = r3.zzb;
        if (r3 != null) goto L20;
    L23:
        if (r2 == 0) goto L25;
        SimpleArrayMap r83 = new SimpleArrayMap(r2);
        Iterator<E> r73 = r72.iterator();
    L28:
        if (r73.hasNext() == false) goto L42;
        zzwl r22 = (zzwl) r73.next();
    L30:
        int r32 = 0;
    L31:
        SimpleArrayMap r4 = r22.zzc;
        if (r32 >= r4.size()) goto L38;
        if (r83.put((zzwj) r4.keyAt(r32), r4.valueAt(r32)) != null) goto L36;
        boolean r5 = true;
    L37:
        Preconditions.checkArgument(r5, "Duplicate bindings: %s", r4.keyAt(r32));
        r32 = r32 + 1;
        goto L31
    L36:
        r5 = false;
        goto L37
    L38:
        r22 = r22.zzb;
        if (r22 != null) goto L30;
    L42:
        return new zzwk(null, r83, null).zzb();
    L25:
        return zzwk.zza;
    L15:
        return (zzwl) r72.iterator().next();
    }

    public static /* synthetic */ zzwj zzf() {
        return zza;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("SpanExtras<");
        zzwl r1 = this;
    L3:
        if (r1 == null) goto L9;
        int r2 = 0;
    L6:
        if (r2 >= r1.zzc.size()) goto L8;
        r0.append("[");
        r0.append(this.zzc.valueAt(r2));
        r0.append("], ");
        r2 = r2 + 1;
        goto L6
    L8:
        r1 = r1.zzb;
        goto L3
    L9:
        r0.append(">");
        return r0.toString();
    }

    public final zzwl zzb() {
        if (this.zzd == true) goto L10;
        this.zzd = true;
        zzwl r0 = this.zzb;
        if (r0 != null) goto L7;
    L9:
        return this;
    L7:
        if (this.zzc.isEmpty() == false) goto L9;
        return r0;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Already frozen");
        return null;
    }

    public final boolean zzc() {
        if (this != zzwk.zza) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzd(zzwj r3) {
        if (this.zzc.containsKey(r3) == true) goto L10;
        zzwl r2 = this.zzb;
        if (r2 != null) goto L7;
    L9:
        return false;
    L7:
        if (r2.zzd(r3) == false) goto L9;
        return true;
    L10:
        return true;
    }

    public final boolean zze() {
        return this.zzd;
    }

    public final /* synthetic */ SimpleArrayMap zzg() {
        return this.zzc;
    }

    public final /* synthetic */ boolean zzh() {
        return this.zzd;
    }
}
