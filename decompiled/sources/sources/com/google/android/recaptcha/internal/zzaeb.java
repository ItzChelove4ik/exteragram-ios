package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.recaptcha.internal.zzaeb;
import com.google.android.recaptcha.internal.zzaec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes5.dex */
public abstract class zzaeb<MessageType extends zzaec<MessageType, BuilderType>, BuilderType extends zzaeb<MessageType, BuilderType>> implements zzagw {
    public zzaeb() {
    }

    private static void zza(List r3, int r4) {
        int r0 = r3.size() - r4;
        StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 26);
        r2.append("Element at index ");
        r2.append(r0);
        r2.append(" is null.");
        String r02 = r2.toString();
        int r1 = r3.size();
    L3:
        r1 = r1 - 1;
        if (r1 < r4) goto L7;
        r3.remove(r1);
        goto L3
    L7:
        throw new NullPointerException(r02);
    }

    public static void zzj(Iterable r4, List r5) {
        r4.getClass();
        int r1 = 0;
        if ((r4 instanceof zzagn) == false) goto L23;
        List r42 = ((zzagn) r4).zza();
        zzagn r0 = (zzagn) r5;
        int r52 = r5.size();
        Iterator r43 = r42.iterator();
    L6:
        if (r43.hasNext() == false) goto L49;
        Object r2 = r43.next();
        if (r2 == null) goto L9;
        if ((r2 instanceof zzaer) == true) goto L17;
        if ((r2 instanceof byte[]) == true) goto L20;
        r0.add((String) r2);
        goto L6
    L20:
        byte[] r22 = (byte[]) r2;
        zzaer.zzs(r22, 0, r22.length);
        r0.zzb();
        goto L6
    L17:
        zzaer r23 = (zzaer) r2;
        r0.zzb();
        goto L6
    L9:
        int r44 = r0.size() - r52;
        StringBuilder r24 = new StringBuilder(String.valueOf(r44).length() + 26);
        r24.append("Element at index ");
        r24.append(r44);
        r24.append(" is null.");
        String r45 = r24.toString();
        int r12 = r0.size();
    L10:
        r12 = r12 - 1;
        if (r12 < r52) goto L13;
        r0.remove(r12);
        goto L10
    L13:
        f$$ExternalSyntheticBUOutline2.m(r45);
        return;
    L49:
        return;
    L23:
        if ((r4 instanceof zzahf) == false) goto L25;
        r5.addAll((Collection) r4);
        return;
    L25:
        if ((r4 instanceof Collection) == false) goto L32;
        int r02 = ((Collection) r4).size();
        if ((r5 instanceof ArrayList) == false) goto L30;
        ((ArrayList) r5).ensureCapacity(r5.size() + r02);
        goto L32
    L30:
        if ((r5 instanceof zzahh) == false) goto L32;
        ((zzahh) r5).zze(r5.size() + r02);
    L32:
        int r03 = r5.size();
        if ((r4 instanceof List) == true) goto L35;
    L42:
        Iterator r46 = r4.iterator();
    L44:
        if (r46.hasNext() == false) goto L70;
        Object r13 = r46.next();
        if (r13 != null) goto L48;
        zza(r5, r03);
    L48:
        r5.add(r13);
        goto L44
    L70:
        return;
    L35:
        if ((r4 instanceof RandomAccess) == false) goto L42;
        List r47 = (List) r4;
        int r25 = r47.size();
    L37:
        if (r1 >= r25) goto L69;
        Object r3 = r47.get(r1);
        if (r3 != null) goto L41;
        zza(r5, r03);
    L41:
        r5.add(r3);
        r1 = r1 + 1;
        goto L37
    }
}
