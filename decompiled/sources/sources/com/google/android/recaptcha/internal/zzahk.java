package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayDeque;
import java.util.Arrays;

/* loaded from: classes5.dex */
abstract class zzahk {
    public static final /* synthetic */ zzaer zza(zzaer r2, zzaer r3, ArrayDeque r4) {
        zzb(r2, r4);
        zzb(r3, r4);
        zzaer r22 = (zzaer) r4.pop();
    L4:
        if (r4.isEmpty() == true) goto L6;
        r22 = new zzahm((zzaer) r4.pop(), r22, null);
        goto L4
    L6:
        return r22;
    }

    private static final void zzb(zzaer r5, ArrayDeque r6) {
        if (r5.zzp() == false) goto L26;
        int r0 = zzc(r5.zzb(), r6);
        int r1 = zzahm.zzm(r0 + 1);
        if (r6.isEmpty() == false) goto L7;
    L23:
        r6.push(r5);
        return;
    L7:
        if (((zzaer) r6.peek()).zzb() >= r1) goto L23;
        int r02 = zzahm.zzm(r0);
        zzaer r12 = (zzaer) r6.pop();
    L11:
        if (r6.isEmpty() == true) goto L15;
        if (((zzaer) r6.peek()).zzb() >= r02) goto L15;
        r12 = new zzahm((zzaer) r6.pop(), r12, null);
    L15:
        zzahm r03 = new zzahm(r12, r5, null);
    L17:
        if (r6.isEmpty() == true) goto L21;
        int r52 = zzahm.zzm(zzc(r03.zzb(), r6) + 1);
        if (((zzaer) r6.peek()).zzb() >= r52) goto L21;
        r03 = new zzahm((zzaer) r6.pop(), r03, null);
    L21:
        r6.push(r03);
        return;
    L26:
        if ((r5 instanceof zzahm) == false) goto L29;
        zzahm r53 = (zzahm) r5;
        zzb(r53.zzn(), r6);
        zzb(r53.zzD(), r6);
        return;
    L29:
        f$$ExternalSyntheticBUOutline1.m("Has a new type of ByteString been created? Found ".concat(String.valueOf(r5.getClass())));
    }

    private static final int zzc(int r0, ArrayDeque r1) {
        int r02 = Arrays.binarySearch(zzahm.zzb, r0);
        if (r02 < 0) goto L5;
        return r02;
    L5:
        return (-(r02 + 1)) - 1;
    }
}
