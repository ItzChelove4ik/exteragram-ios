package com.google.android.gms.cast;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes4.dex */
final class zzt {
    private final String zza;
    private final String zzb;
    private final Collection zzc;

    public /* synthetic */ zzt(String r1, String r2, Collection r3, boolean r4, boolean r5, byte[] r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final /* synthetic */ String zza() {
        StringBuilder r0 = new StringBuilder(this.zza);
        String r1 = this.zzb;
        if (r1 == null) goto L9;
        String r4 = r1.toUpperCase(Locale.ROOT);
        if (r4.matches("[A-F0-9]+") == false) goto L7;
        r0.append("/");
        r0.append(r4);
        goto L9
    L7:
        f$$ExternalSyntheticBUOutline1.m("Invalid application ID: ".concat(r1));
        return null;
    L9:
        Collection r8 = this.zzc;
        boolean r42 = false;
        if (r8 != null) goto L12;
        if (r1 == null) goto L31;
        r42 = true;
    L31:
        if (true == r42) goto L34;
        if (r8 != null) goto L34;
        r0.append("/");
    L34:
        if (r8 != null) goto L36;
        r0.append("/");
    L36:
        r0.append("//ALLOW_IPV6");
        return r0.toString();
    L12:
        if (r8.isEmpty() == true) goto L26;
        if (r1 != null) goto L15;
        boolean r2 = false;
    L16:
        if (r1 != null) goto L18;
        r0.append("/");
    L18:
        r0.append("/");
        Iterator r12 = r8.iterator();
        boolean r6 = true;
    L20:
        if (r12.hasNext() == false) goto L25;
        String r7 = (String) r12.next();
        CastUtils.throwIfInvalidNamespace(r7);
        if (r6 == true) goto L24;
        r0.append(",");
    L24:
        r0.append(CastUtils.zzc(r7));
        r6 = false;
        goto L20
    L25:
        r42 = r2;
        goto L31
    L15:
        r2 = true;
        goto L16
    L26:
        f$$ExternalSyntheticBUOutline1.m("Must specify at least one namespace");
        return null;
    }
}
