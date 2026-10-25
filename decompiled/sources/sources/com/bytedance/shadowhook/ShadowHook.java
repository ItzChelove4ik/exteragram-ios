package com.bytedance.shadowhook;

/* loaded from: classes4.dex */
public final class ShadowHook {
    private static final int defaultMode = 0;

    public enum Mode extends Enum<Mode> {
        private static final /* synthetic */ Mode[] $VALUES = null;
        public static final Mode MULTI = null;
        public static final Mode SHARED = null;
        public static final Mode UNIQUE = null;
        private final int value;

        private static /* synthetic */ Mode[] $values() {
            return new Mode[]{SHARED, UNIQUE, MULTI};
        }

        static {
            SHARED = new Mode("SHARED", 0, 0);
            UNIQUE = new Mode("UNIQUE", 1, 1);
            MULTI = new Mode("MULTI", 2, 2);
            $VALUES = $values();
        }

        Mode(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static Mode valueOf(String r1) {
            return (Mode) Enum.valueOf(Mode.class, r1);
        }

        public static Mode[] values() {
            return (Mode[]) $VALUES.clone();
        }

        public int getValue() {
            return this.value;
        }
    }

    static {
        defaultMode = Mode.SHARED.getValue();
    }

    private static native String nativeGetArch();

    private static native boolean nativeGetDebuggable();

    private static native boolean nativeGetDisable();

    private static native int nativeGetInitErrno();

    private static native int nativeGetMode();

    private static native boolean nativeGetRecordable();

    private static native String nativeGetRecords(int r0);

    private static native String nativeGetVersion();

    private static native int nativeInit(int r0, boolean r1);

    private static native void nativeSetDebuggable(boolean r0);

    private static native void nativeSetDisable(boolean r0);

    private static native void nativeSetRecordable(boolean r0);

    private static native String nativeToErrmsg(int r0);
}
