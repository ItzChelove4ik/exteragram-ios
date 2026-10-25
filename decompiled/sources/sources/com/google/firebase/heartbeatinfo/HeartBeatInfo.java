package com.google.firebase.heartbeatinfo;

/* loaded from: classes.dex */
public interface HeartBeatInfo {

    /* loaded from: classes5.dex */
    public enum HeartBeat extends Enum<HeartBeat> {
        private static final /* synthetic */ HeartBeat[] $VALUES = null;
        public static final HeartBeat COMBINED = null;
        public static final HeartBeat GLOBAL = null;
        public static final HeartBeat NONE = null;
        public static final HeartBeat SDK = null;
        private final int code;

        private static /* synthetic */ HeartBeat[] $values() {
            return new HeartBeat[]{NONE, SDK, GLOBAL, COMBINED};
        }

        static {
            NONE = new HeartBeat("NONE", 0, 0);
            SDK = new HeartBeat("SDK", 1, 1);
            GLOBAL = new HeartBeat("GLOBAL", 2, 2);
            COMBINED = new HeartBeat("COMBINED", 3, 3);
            $VALUES = $values();
        }

        HeartBeat(String r1, int r2, int r3) {
            this.code = r3;
        }

        public static HeartBeat valueOf(String r1) {
            return (HeartBeat) Enum.valueOf(HeartBeat.class, r1);
        }

        public static HeartBeat[] values() {
            return (HeartBeat[]) $VALUES.clone();
        }

        public int getCode() {
            return this.code;
        }
    }

    HeartBeat getHeartBeatCode(String r1);
}
