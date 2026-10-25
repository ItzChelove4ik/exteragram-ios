package com.google.android.play.integrity.internal;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public final class az implements ay {

    /* renamed from: a, reason: collision with root package name */
    private final Object f126a;

    private az(Object r1) {
        this.f126a = r1;
    }

    public static ay b(Object r1) {
        if (r1 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("instance cannot be null");
        return null;
    L5:
        return new az(r1);
    }

    @Override // com.google.android.play.integrity.internal.bd
    public final Object a() {
        return this.f126a;
    }
}
