package com.google.android.gms.internal.measurement;

import java.util.Random;

/* loaded from: classes4.dex */
final class zzys extends ThreadLocal {
    public zzys() {
    }

    @Override // java.lang.ThreadLocal
    public final /* synthetic */ Object initialValue() {
        return new Random();
    }
}
