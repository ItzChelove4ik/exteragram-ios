package com.google.android.recaptcha.internal;

import android.app.Application;
import android.content.ContentResolver;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import java.util.List;
import kotlin.collections.CollectionsKt;

/* loaded from: classes5.dex */
public abstract class zzei {
    public static final zzeo zza(Application r2, zzhu r3, ContentResolver r4, StandardIntegrityManager r5, zzgp r6, zzhr r7, boolean r8) {
        List r42 = CollectionsKt.createListBuilder();
        r42.add(new zzez());
        r42.add(new zzeh());
        r42.add(new zzea(r2.getContentResolver()));
        r42.add(new zzee());
        r42.add(new zzec(r2, r6, r7));
        if (r8 == false) goto L6;
        r42.add(new zzfd(r3, r5, r7, new zzfp(r3, r5, 28800000)));
    L6:
        return new zzeo(CollectionsKt.build(r42));
    }
}
