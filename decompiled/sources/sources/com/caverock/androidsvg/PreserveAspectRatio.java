package com.caverock.androidsvg;

/* loaded from: classes4.dex */
public class PreserveAspectRatio {
    public static final PreserveAspectRatio BOTTOM = null;
    public static final PreserveAspectRatio END = null;
    public static final PreserveAspectRatio FULLSCREEN = null;
    public static final PreserveAspectRatio FULLSCREEN_START = null;
    public static final PreserveAspectRatio LETTERBOX = null;
    public static final PreserveAspectRatio START = null;
    public static final PreserveAspectRatio STRETCH = null;
    public static final PreserveAspectRatio TOP = null;
    public static final PreserveAspectRatio UNSCALED = null;
    private Alignment alignment;
    private Scale scale;

    public enum Alignment extends Enum<Alignment> {
        private static final /* synthetic */ Alignment[] $VALUES = null;
        public static final Alignment none = null;
        public static final Alignment xMaxYMax = null;
        public static final Alignment xMaxYMid = null;
        public static final Alignment xMaxYMin = null;
        public static final Alignment xMidYMax = null;
        public static final Alignment xMidYMid = null;
        public static final Alignment xMidYMin = null;
        public static final Alignment xMinYMax = null;
        public static final Alignment xMinYMid = null;
        public static final Alignment xMinYMin = null;

        static {
            Alignment r0 = new Alignment("none", 0);
            none = r0;
            Alignment r1 = new Alignment("xMinYMin", 1);
            xMinYMin = r1;
            Alignment r2 = new Alignment("xMidYMin", 2);
            xMidYMin = r2;
            Alignment r3 = new Alignment("xMaxYMin", 3);
            xMaxYMin = r3;
            Alignment r4 = new Alignment("xMinYMid", 4);
            xMinYMid = r4;
            Alignment r5 = new Alignment("xMidYMid", 5);
            xMidYMid = r5;
            Alignment r6 = new Alignment("xMaxYMid", 6);
            xMaxYMid = r6;
            Alignment r7 = new Alignment("xMinYMax", 7);
            xMinYMax = r7;
            Alignment r8 = new Alignment("xMidYMax", 8);
            xMidYMax = r8;
            Alignment r9 = new Alignment("xMaxYMax", 9);
            xMaxYMax = r9;
            $VALUES = new Alignment[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
        }

        Alignment(String r1, int r2) {
        }

        public static Alignment valueOf(String r1) {
            return (Alignment) Enum.valueOf(Alignment.class, r1);
        }

        public static Alignment[] values() {
            return (Alignment[]) $VALUES.clone();
        }
    }

    public enum Scale extends Enum<Scale> {
        private static final /* synthetic */ Scale[] $VALUES = null;
        public static final Scale meet = null;
        public static final Scale slice = null;

        static {
            Scale r0 = new Scale("meet", 0);
            meet = r0;
            Scale r1 = new Scale("slice", 1);
            slice = r1;
            $VALUES = new Scale[]{r0, r1};
        }

        Scale(String r1, int r2) {
        }

        public static Scale valueOf(String r1) {
            return (Scale) Enum.valueOf(Scale.class, r1);
        }

        public static Scale[] values() {
            return (Scale[]) $VALUES.clone();
        }
    }

    static {
        UNSCALED = new PreserveAspectRatio(null, null);
        STRETCH = new PreserveAspectRatio(Alignment.none, null);
        Alignment r1 = Alignment.xMidYMid;
        Scale r2 = Scale.meet;
        LETTERBOX = new PreserveAspectRatio(r1, r2);
        Alignment r3 = Alignment.xMinYMin;
        START = new PreserveAspectRatio(r3, r2);
        END = new PreserveAspectRatio(Alignment.xMaxYMax, r2);
        TOP = new PreserveAspectRatio(Alignment.xMidYMin, r2);
        BOTTOM = new PreserveAspectRatio(Alignment.xMidYMax, r2);
        Scale r22 = Scale.slice;
        FULLSCREEN = new PreserveAspectRatio(r1, r22);
        FULLSCREEN_START = new PreserveAspectRatio(r3, r22);
    }

    public PreserveAspectRatio(Alignment r1, Scale r2) {
        this.alignment = r1;
        this.scale = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if (getClass() == r5.getClass()) goto L11;
        return false;
    L11:
        PreserveAspectRatio r52 = (PreserveAspectRatio) r5;
        if (this.alignment == r52.alignment) goto L14;
    L16:
        return false;
    L14:
        if (this.scale != r52.scale) goto L16;
        return true;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public Scale getScale() {
        return this.scale;
    }

    public String toString() {
        return this.alignment + " " + this.scale;
    }
}
