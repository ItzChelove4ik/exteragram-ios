package com.google.android.gms.internal.mlkit_language_id_bundled;

import java.util.List;

/* loaded from: classes4.dex */
final class zbh extends zbi {
    final transient int zba;
    final transient int zbb;
    final /* synthetic */ zbi zbc;

    public zbh(zbi r1, int r2, int r3) {
        this.zbc = r1;
        this.zba = r2;
        this.zbb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zbc.zba(r3, this.zbb, "index");
        return this.zbc.get(r3 + this.zba);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbi, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zbf(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final int zbb() {
        return (this.zbc.zbc() + this.zba) + this.zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final int zbc() {
        return this.zbc.zbc() + this.zba;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final Object[] zbe() {
        return this.zbc.zbe();
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbi
    public final zbi zbf(int r2, int r3) {
        zbc.zbc(r2, r3, this.zbb);
        int r0 = this.zba;
        return this.zbc.zbf(r2 + r0, r3 + r0);
    }
}
