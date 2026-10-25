package com.google.android.gms.internal.vision;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzli extends zzlo {
    private final /* synthetic */ zzlh zza;

    private zzli(zzlh r2) {
        this.zza = r2;
        super(r2, null);
    }

    public /* synthetic */ zzli(zzlh r1, zzlg r2) {
        this(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzlo, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzlj(this.zza, null);
    }
}
