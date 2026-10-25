package com.google.android.recaptcha.internal;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.RangesKt;

/* loaded from: classes5.dex */
public final class zzfr implements zzfu {
    private final zzbb zza;

    public zzfr(zzbb r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzfu
    public final Object zza(Object... r9) {
        String r1 = (String) r9[0];
        List r92 = ArraysKt.slice(r9, RangesKt.until(1, r9.length));
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r92, 10));
        Iterator r93 = r92.iterator();
    L4:
        if (r93.hasNext() == false) goto L6;
        r2.add((String) r93.next());
        goto L4
    L6:
        Cursor r8 = this.zza.zzd(r1, (String[]) r2.toArray(new String[0]));
        if (r8 != null) goto L9;
        return null;
    L9:
        ArrayList r12 = new ArrayList();
    L11:
        if (r8.moveToNext() == false) goto L30;
        ArrayList r22 = new ArrayList();
        int r4 = r8.getColumnCount();
        int r5 = 0;
    L13:
        if (r5 >= r4) goto L29;
        int r6 = r8.getType(r5);
        if (r6 == 0) goto L23;
        if (r6 != 1) goto L18;
        Object r62 = Integer.valueOf(r8.getInt(r5));
    L28:
        r22.add(r62);
        r5 = r5 + 1;
        goto L13
    L18:
        if (r6 != 2) goto L20;
        r62 = Float.valueOf(r8.getFloat(r5));
        goto L28
    L20:
        if (r6 != 3) goto L22;
        r62 = r8.getString(r5);
        goto L28
    L22:
        if (r6 != 4) goto L23;
        r62 = r8.getBlob(r5);
    L23:
        r62 = null;
        goto L28
    L29:
        r12.add(r22);
        goto L11
    L30:
        r8.close();
        return r12;
    }
}
