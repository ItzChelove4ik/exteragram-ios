package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class zzlp implements Iterator {
    private int zza;
    private boolean zzb;
    private Iterator zzc;
    private final /* synthetic */ zzlh zzd;

    private zzlp(zzlh r1) {
        this.zzd = r1;
        this.zza = -1;
    }

    public /* synthetic */ zzlp(zzlh r1, zzlg r2) {
        this(r1);
    }

    private final Iterator zza() {
        if (this.zzc != null) goto L6;
        this.zzc = zzlh.zzc(this.zzd).entrySet().iterator();
    L6:
        return this.zzc;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if ((this.zza + 1) >= zzlh.zzb(this.zzd).size()) goto L5;
    L11:
        return true;
    L5:
        if (zzlh.zzc(this.zzd).isEmpty() == false) goto L7;
        return false;
    L7:
        if (zza().hasNext() == true) goto L11;
        return false;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzb = true;
        int r1 = this.zza + 1;
        this.zza = r1;
        if (r1 >= zzlh.zzb(this.zzd).size()) goto L7;
        return (Map.Entry) zzlh.zzb(this.zzd).get(this.zza);
    L7:
        return (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.zzb == false) goto L10;
        this.zzb = false;
        zzlh.zza(this.zzd);
        if (this.zza >= zzlh.zzb(this.zzd).size()) goto L8;
        zzlh r0 = this.zzd;
        int r1 = this.zza;
        this.zza = r1 - 1;
        zzlh.zza(r0, r1);
        return;
    L8:
        zza().remove();
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("remove() was called before next()");
    }
}
