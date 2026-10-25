package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
public enum zzfl extends Enum<zzfl> {
    public static final zzfl zzqc = null;
    public static final zzfl zzqd = null;
    public static final zzfl zzqe = null;
    public static final zzfl zzqf = null;
    public static final zzfl zzqg = null;
    public static final zzfl zzqh = null;
    public static final zzfl zzqi = null;
    public static final zzfl zzqj = null;
    public static final zzfl zzqk = null;
    public static final zzfl zzql = null;
    public static final zzfl zzqm = null;
    public static final zzfl zzqn = null;
    public static final zzfl zzqo = null;
    public static final zzfl zzqp = null;
    public static final zzfl zzqq = null;
    public static final zzfl zzqr = null;
    public static final zzfl zzqs = null;
    public static final zzfl zzqt = null;
    private static final /* synthetic */ zzfl[] zzqw = null;
    private final zzfq zzqu;
    private final int zzqv;

    static {
        zzfl r0 = new zzfl("DOUBLE", 0, zzfq.zzra, 1);
        zzqc = r0;
        zzfl r1 = new zzfl("FLOAT", 1, zzfq.zzqz, 5);
        zzqd = r1;
        zzfq r5 = zzfq.zzqy;
        zzfl r2 = new zzfl("INT64", 2, r5, 0);
        zzqe = r2;
        zzfl r7 = new zzfl("UINT64", 3, r5, 0);
        zzqf = r7;
        zzfq r11 = zzfq.zzqx;
        zzfl r9 = new zzfl("INT32", 4, r11, 0);
        zzqg = r9;
        zzfl r12 = new zzfl("FIXED64", 5, r5, 1);
        zzqh = r12;
        zzfl r14 = new zzfl("FIXED32", 6, r11, 5);
        zzqi = r14;
        zzfl r15 = new zzfl("BOOL", 7, zzfq.zzrb, 0);
        zzqj = r15;
        zzfm r4 = new zzfm("STRING", 8, zzfq.zzrc, 2);
        zzqk = r4;
        zzfq r13 = zzfq.zzrf;
        zzfn r6 = new zzfn("GROUP", 9, r13, 3);
        zzql = r6;
        zzfo r3 = new zzfo("MESSAGE", 10, r13, 2);
        zzqm = r3;
        zzfp r8 = new zzfp("BYTES", 11, zzfq.zzrd, 2);
        zzqn = r8;
        zzfl r02 = new zzfl("UINT32", 12, r11, 0);
        zzqo = r02;
        zzfl r10 = new zzfl("ENUM", 13, zzfq.zzre, 0);
        zzqp = r10;
        zzfl r03 = new zzfl("SFIXED32", 14, r11, 5);
        zzqq = r03;
        zzfl r16 = new zzfl("SFIXED64", 15, r5, 1);
        zzqr = r16;
        zzfl r04 = new zzfl("SINT32", 16, r11, 0);
        zzqs = r04;
        zzfl r22 = new zzfl("SINT64", 17, r5, 0);
        zzqt = r22;
        zzqw = new zzfl[]{r0, r1, r2, r7, r9, r12, r14, r15, r4, r6, r3, r8, r02, r10, r03, r16, r04, r22};
    }

    zzfl(String r1, int r2, zzfq r3, int r4) {
        this.zzqu = r3;
        this.zzqv = r4;
    }

    /* synthetic */ zzfl(String r1, int r2, zzfq r3, int r4, zzfk r5) {
        this(r1, r2, r3, r4);
    }

    public static zzfl[] values() {
        return (zzfl[]) zzqw.clone();
    }
}
