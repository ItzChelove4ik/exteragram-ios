package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
enum zzcd extends Enum<zzcd> {
    public static final zzcd zzjg = null;
    public static final zzcd zzjh = null;
    public static final zzcd zzji = null;
    public static final zzcd zzjj = null;
    private static final /* synthetic */ zzcd[] zzjl = null;
    private final boolean zzjk;

    static {
        zzcd r0 = new zzcd("SCALAR", 0, false);
        zzjg = r0;
        zzcd r1 = new zzcd("VECTOR", 1, true);
        zzjh = r1;
        zzcd r3 = new zzcd("PACKED_VECTOR", 2, true);
        zzji = r3;
        zzcd r4 = new zzcd("MAP", 3, false);
        zzjj = r4;
        zzjl = new zzcd[]{r0, r1, r3, r4};
    }

    zzcd(String r1, int r2, boolean r3) {
        this.zzjk = r3;
    }

    public static zzcd[] values() {
        return (zzcd[]) zzjl.clone();
    }
}
