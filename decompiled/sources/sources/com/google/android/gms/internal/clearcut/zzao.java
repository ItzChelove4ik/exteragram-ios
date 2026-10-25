package com.google.android.gms.internal.clearcut;

import android.net.Uri;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzao {
    private final String zzef;
    private final Uri zzeg;
    private final String zzeh;
    private final String zzei;
    private final boolean zzej;
    private final boolean zzek;

    public zzao(Uri r8) {
        this(null, r8, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    }

    private zzao(String r1, Uri r2, String r3, String r4, boolean r5, boolean r6) {
        this.zzef = r1;
        this.zzeg = r2;
        this.zzeh = r3;
        this.zzei = r4;
        this.zzej = r5;
        this.zzek = r6;
    }

    public static /* synthetic */ String zza(zzao r0) {
        return r0.zzef;
    }

    public static /* synthetic */ Uri zzb(zzao r0) {
        return r0.zzeg;
    }

    public static /* synthetic */ String zzc(zzao r0) {
        return r0.zzeh;
    }

    public static /* synthetic */ String zzd(zzao r0) {
        return r0.zzei;
    }

    public static /* synthetic */ boolean zze(zzao r0) {
        return r0.zzek;
    }

    public static /* synthetic */ boolean zzf(zzao r0) {
        return r0.zzej;
    }

    public final <T> zzae<T> zza(String r1, T r2, zzan<T> r3) {
        return zzae.zzb(this, r1, r2, r3);
    }

    public final zzae<String> zza(String r1, String r2) {
        return zzae.zzb(this, r1, null);
    }

    public final zzae<Boolean> zzc(String r1, boolean r2) {
        return zzae.zzb(this, r1, false);
    }

    public final zzao zzc(String r8) {
        boolean r5 = this.zzej;
        if (r5 == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot set GServices prefix and skip GServices");
        return null;
    L5:
        return new zzao(this.zzef, this.zzeg, r8, this.zzei, r5, this.zzek);
    }

    public final zzao zzd(String r8) {
        return new zzao(this.zzef, this.zzeg, this.zzeh, r8, this.zzej, this.zzek);
    }
}
