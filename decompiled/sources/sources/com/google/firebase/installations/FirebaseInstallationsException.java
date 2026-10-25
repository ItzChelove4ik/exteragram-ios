package com.google.firebase.installations;

import com.google.firebase.FirebaseException;

/* loaded from: classes.dex */
public class FirebaseInstallationsException extends FirebaseException {
    private final Status status;

    /* loaded from: classes5.dex */
    public enum Status extends Enum<Status> {
        private static final /* synthetic */ Status[] $VALUES = null;
        public static final Status BAD_CONFIG = null;
        public static final Status TOO_MANY_REQUESTS = null;
        public static final Status UNAVAILABLE = null;

        private static /* synthetic */ Status[] $values() {
            return new Status[]{BAD_CONFIG, UNAVAILABLE, TOO_MANY_REQUESTS};
        }

        static {
            BAD_CONFIG = new Status("BAD_CONFIG", 0);
            UNAVAILABLE = new Status("UNAVAILABLE", 1);
            TOO_MANY_REQUESTS = new Status("TOO_MANY_REQUESTS", 2);
            $VALUES = $values();
        }

        Status(String r1, int r2) {
        }

        public static Status valueOf(String r1) {
            return (Status) Enum.valueOf(Status.class, r1);
        }

        public static Status[] values() {
            return (Status[]) $VALUES.clone();
        }
    }

    public FirebaseInstallationsException(Status r1) {
        this.status = r1;
    }

    public FirebaseInstallationsException(String r1, Status r2) {
        super(r1);
        this.status = r2;
    }
}
