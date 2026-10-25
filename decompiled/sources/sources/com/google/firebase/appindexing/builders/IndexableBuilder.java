package com.google.firebase.appindexing.builders;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.zzw;
import java.util.Arrays;

/* loaded from: classes5.dex */
public abstract class IndexableBuilder<T extends IndexableBuilder<?>> {
    public static void zza(Bundle r7, String r8, String... r9) {
        Preconditions.checkNotNull(r8);
        Preconditions.checkNotNull(r9);
        String[] r92 = (String[]) Arrays.copyOf(r9, r9.length);
        if (r92.length <= 0) goto L27;
        int r1 = 0;
        int r2 = 0;
    L6:
        if (r1 >= Math.min(r92.length, 100)) goto L24;
        String r3 = r92[r1];
        r92[r2] = r3;
        if (r92[r1] != null) goto L10;
        StringBuilder r32 = new StringBuilder(59);
        r32.append("String at ");
        r32.append(r1);
        r32.append(" is null and is ignored by put method.");
        zzw.zza(r32.toString());
    L23:
        r1 = r1 + 1;
        goto L6
    L10:
        int r4 = 20000;
        if (r3.length() <= 20000) goto L22;
        StringBuilder r33 = new StringBuilder(53);
        r33.append("String at ");
        r33.append(r1);
        r33.append(" is too long, truncating string.");
        zzw.zza(r33.toString());
        String r34 = r92[r2];
        if (r34.length() > 20000) goto L16;
    L21:
        r92[r2] = r34;
        goto L22
    L16:
        if (Character.isHighSurrogate(r34.charAt(19999)) == true) goto L18;
    L20:
        r34 = r34.substring(0, r4);
        goto L21
    L18:
        if (Character.isLowSurrogate(r34.charAt(20000)) == false) goto L20;
        r4 = 19999;
    L22:
        r2 = r2 + 1;
        goto L23
    L24:
        if (r2 <= 0) goto L32;
        r7.putStringArray(r8, (String[]) zzf((String[]) Arrays.copyOfRange(r92, 0, r2)));
        return;
    L32:
        return;
    L27:
        zzw.zza("String array is empty and is ignored by put method.");
    }

    private static <S> S[] zzf(S[] r2) {
        if (r2.length >= 100) goto L5;
        return r2;
    L5:
        zzw.zza("Input Array of elements is too big, cutting off.");
        return (S[]) Arrays.copyOf(r2, 100);
    }
}
