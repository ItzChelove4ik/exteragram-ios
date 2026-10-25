package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
final class zzee {
    private final String info;
    private int position;

    public zzee(String r1) {
        this.info = r1;
        this.position = 0;
    }

    public final boolean hasNext() {
        if (this.position >= this.info.length()) goto L6;
        return true;
    L6:
        return false;
    }

    public final int next() {
        String r0 = this.info;
        int r1 = this.position;
        this.position = r1 + 1;
        char r02 = r0.charAt(r1);
        if (r02 >= 55296) goto L5;
        return r02;
    L5:
        int r03 = r02 & 8191;
        int r2 = 13;
    L6:
        String r3 = this.info;
        int r4 = this.position;
        this.position = r4 + 1;
        char r32 = r3.charAt(r4);
        if (r32 < 55296) goto L10;
        r03 = r03 | ((r32 & 8191) << r2);
        r2 = r2 + 13;
        goto L6
    L10:
        return (r32 << r2) | r03;
    }
}
