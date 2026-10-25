package com.android.dx.rop.annotation;

import com.android.dx.util.ToHuman;

/* loaded from: classes4.dex */
public enum AnnotationVisibility extends Enum<AnnotationVisibility> implements ToHuman {
    private static final /* synthetic */ AnnotationVisibility[] $VALUES = null;
    public static final AnnotationVisibility BUILD = null;
    public static final AnnotationVisibility EMBEDDED = null;
    public static final AnnotationVisibility RUNTIME = null;
    public static final AnnotationVisibility SYSTEM = null;
    private final String human;

    static {
        AnnotationVisibility r0 = new AnnotationVisibility("RUNTIME", 0, "runtime");
        RUNTIME = r0;
        AnnotationVisibility r1 = new AnnotationVisibility("BUILD", 1, "build");
        BUILD = r1;
        AnnotationVisibility r2 = new AnnotationVisibility("SYSTEM", 2, "system");
        SYSTEM = r2;
        AnnotationVisibility r3 = new AnnotationVisibility("EMBEDDED", 3, "embedded");
        EMBEDDED = r3;
        $VALUES = new AnnotationVisibility[]{r0, r1, r2, r3};
    }

    AnnotationVisibility(String r1, int r2, String r3) {
        this.human = r3;
    }

    public static AnnotationVisibility valueOf(String r1) {
        return (AnnotationVisibility) Enum.valueOf(AnnotationVisibility.class, r1);
    }

    public static AnnotationVisibility[] values() {
        return (AnnotationVisibility[]) $VALUES.clone();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.human;
    }
}
