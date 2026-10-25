package com.android.dx.merge;

/* loaded from: classes4.dex */
public enum CollisionPolicy extends Enum<CollisionPolicy> {
    private static final /* synthetic */ CollisionPolicy[] $VALUES = null;
    public static final CollisionPolicy FAIL = null;
    public static final CollisionPolicy KEEP_FIRST = null;

    static {
        CollisionPolicy r0 = new CollisionPolicy("KEEP_FIRST", 0);
        KEEP_FIRST = r0;
        CollisionPolicy r1 = new CollisionPolicy("FAIL", 1);
        FAIL = r1;
        $VALUES = new CollisionPolicy[]{r0, r1};
    }

    CollisionPolicy(String r1, int r2) {
    }

    public static CollisionPolicy valueOf(String r1) {
        return (CollisionPolicy) Enum.valueOf(CollisionPolicy.class, r1);
    }

    public static CollisionPolicy[] values() {
        return (CollisionPolicy[]) $VALUES.clone();
    }
}
