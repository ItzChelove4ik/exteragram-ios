package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes5.dex */
public final class zznd {
    private final List zza;

    public zznd(boolean r3) {
        this.zza = zzc(CollectionsKt.listOf(new String[]{"www.recaptcha.net", "www.gstatic.com/recaptcha", "www.gstatic.cn/recaptcha"}));
    }

    private final boolean zzb(String r5) {
        List r4 = this.zza;
        if ((r4 instanceof Collection) == true) goto L5;
    L7:
        Iterator r42 = r4.iterator();
    L9:
        if (r42.hasNext() == false) goto L14;
        if (StringsKt.startsWith$default(r5, (String) r42.next(), false, 2, null) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r4.isEmpty() == false) goto L7;
        return false;
    }

    private static final List zzc(List r4) {
        List r42 = r4;
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r42, 10));
        Iterator r43 = r42.iterator();
    L4:
        if (r43.hasNext() == false) goto L6;
        String r1 = (String) r43.next();
        StringBuilder r3 = new StringBuilder(String.valueOf(r1).length() + 9);
        r3.append("https://");
        r3.append(r1);
        r3.append("/");
        r0.add(r3.toString());
        goto L4
    L6:
        return r0;
    }

    public final boolean zza(String r3) {
        Uri r32 = Uri.parse(r3);
        if (TextUtils.isEmpty(r32.toString()) == false) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual("https", r32.getScheme()) == true) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(r32.getHost()) == false) goto L9;
        return false;
    L9:
        if (zzb(r32.toString()) == false) goto L16;
        return true;
    L16:
        return false;
    }
}
