package com.google.android.gms.internal.mlkit_language_id_bundled;

import java.util.Objects;

/* loaded from: classes.dex */
final class zbj extends zbi {
    static final zbi zba = null;
    final transient Object[] zbb;
    private final transient int zbc;

    static {
        zba = new zbj(new Object[0], 0);
    }

    public zbj(Object[] r1, int r2) {
        this.zbb = r1;
        this.zbc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zbc.zba(r3, this.zbc, "index");
        Object r2 = this.zbb[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbi, com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final int zba(Object[] r3, int r4) {
        System.arraycopy(this.zbb, 0, r3, 0, this.zbc);
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final int zbb() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final int zbc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final Object[] zbe() {
        return this.zbb;
    }
}
