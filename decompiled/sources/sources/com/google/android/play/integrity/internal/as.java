package com.google.android.play.integrity.internal;

import java.util.Set;

/* loaded from: classes5.dex */
public abstract class as extends ao implements Set {

    /* renamed from: a, reason: collision with root package name */
    private transient ar f117a;

    public as() {
    }

    public static as h() {
        return au.f120a;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof as) == false) goto L11;
        ((as) r5).i();
        if (r5.hashCode() == 0) goto L11;
        return false;
    L11:
        if (r5 != this) goto L14;
        return true;
    L14:
        if ((r5 instanceof Set) == false) goto L22;
        Set r52 = (Set) r5;
        if (size() != r52.size()) goto L22;
        if (containsAll(r52) == true) goto L21;
        return false;
    L21:
        return true;
    L22:
        return false;
    }

    public final ar f() {
        ar r0 = this.f117a;
        if (r0 != null) goto L6;
        ar r02 = g();
        this.f117a = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract ar g();

    public abstract boolean i();
}
