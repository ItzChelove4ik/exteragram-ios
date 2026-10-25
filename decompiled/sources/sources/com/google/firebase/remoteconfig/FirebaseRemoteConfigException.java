package com.google.firebase.remoteconfig;

import com.google.firebase.FirebaseException;

/* loaded from: classes.dex */
public class FirebaseRemoteConfigException extends FirebaseException {
    private final Code code;

    /* loaded from: classes5.dex */
    public enum Code extends Enum<Code> {
        private static final /* synthetic */ Code[] $VALUES = null;
        public static final Code CONFIG_UPDATE_MESSAGE_INVALID = null;
        public static final Code CONFIG_UPDATE_NOT_FETCHED = null;
        public static final Code CONFIG_UPDATE_STREAM_ERROR = null;
        public static final Code CONFIG_UPDATE_UNAVAILABLE = null;
        public static final Code UNKNOWN = null;
        private final int value;

        private static /* synthetic */ Code[] $values() {
            return new Code[]{UNKNOWN, CONFIG_UPDATE_STREAM_ERROR, CONFIG_UPDATE_MESSAGE_INVALID, CONFIG_UPDATE_NOT_FETCHED, CONFIG_UPDATE_UNAVAILABLE};
        }

        static {
            UNKNOWN = new Code("UNKNOWN", 0, 0);
            CONFIG_UPDATE_STREAM_ERROR = new Code("CONFIG_UPDATE_STREAM_ERROR", 1, 1);
            CONFIG_UPDATE_MESSAGE_INVALID = new Code("CONFIG_UPDATE_MESSAGE_INVALID", 2, 2);
            CONFIG_UPDATE_NOT_FETCHED = new Code("CONFIG_UPDATE_NOT_FETCHED", 3, 3);
            CONFIG_UPDATE_UNAVAILABLE = new Code("CONFIG_UPDATE_UNAVAILABLE", 4, 4);
            $VALUES = $values();
        }

        Code(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static Code valueOf(String r1) {
            return (Code) Enum.valueOf(Code.class, r1);
        }

        public static Code[] values() {
            return (Code[]) $VALUES.clone();
        }
    }

    public FirebaseRemoteConfigException(String r1) {
        super(r1);
        this.code = Code.UNKNOWN;
    }

    public FirebaseRemoteConfigException(String r1, Code r2) {
        super(r1);
        this.code = r2;
    }

    public FirebaseRemoteConfigException(String r1, Throwable r2) {
        super(r1, r2);
        this.code = Code.UNKNOWN;
    }

    public FirebaseRemoteConfigException(String r1, Throwable r2, Code r3) {
        super(r1, r2);
        this.code = r3;
    }
}
