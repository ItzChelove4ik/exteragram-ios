package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
final class zzbe extends zzbi {
    private final int zzfm;
    private final int zzfn;

    public zzbe(byte[] r2, int r3, int r4) {
        super(r2);
        zzbb.zzb(r3, r3 + r4, r2.length);
        this.zzfm = r3;
        this.zzfn = r4;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi, com.google.android.gms.internal.clearcut.zzbb
    public final int size() {
        return this.zzfn;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi
    public final int zzac() {
        return this.zzfm;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi, com.google.android.gms.internal.clearcut.zzbb
    public final byte zzj(int r4) {
        int r0 = size();
        if (((r0 - (r4 + 1)) | r4) >= 0) goto L11;
        if (r4 >= 0) goto L8;
        StringBuilder r02 = new StringBuilder(22);
        r02.append("Index < 0: ");
        r02.append(r4);
        throw new ArrayIndexOutOfBoundsException(r02.toString());
    L8:
        StringBuilder r1 = new StringBuilder(40);
        r1.append("Index > length: ");
        r1.append(r4);
        r1.append(", ");
        r1.append(r0);
        throw new ArrayIndexOutOfBoundsException(r1.toString());
    L11:
        return this.zzfp[this.zzfm + r4];
    }
}
