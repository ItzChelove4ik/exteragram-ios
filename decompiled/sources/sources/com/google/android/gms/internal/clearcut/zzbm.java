package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
final class zzbm extends zzbk {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzfu;
    private int zzfv;
    private int zzfw;
    private int zzfx;

    private zzbm(byte[] r2, int r3, int r4, boolean r5) {
        super(null);
        this.zzfx = Integer.MAX_VALUE;
        this.buffer = r2;
        this.limit = r4 + r3;
        this.pos = r3;
        this.zzfw = r3;
        this.zzfu = r5;
    }

    public /* synthetic */ zzbm(byte[] r1, int r2, int r3, boolean r4, zzbl r5) {
        this(r1, r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbk
    public final int zzaf() {
        return this.pos - this.zzfw;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbk
    public final int zzl(int r4) {
        if (r4 < 0) goto L14;
        int r42 = r4 + zzaf();
        int r0 = this.zzfx;
        if (r42 > r0) goto L12;
        this.zzfx = r42;
        int r1 = this.limit + this.zzfv;
        this.limit = r1;
        int r2 = r1 - this.zzfw;
        if (r2 <= r42) goto L9;
        int r22 = r2 - r42;
        this.zzfv = r22;
        this.limit = r1 - r22;
        return r0;
    L9:
        this.zzfv = 0;
        return r0;
    L12:
        throw zzco.zzbl();
    L14:
        throw new zzco("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
}
