package com.google.android.datatransport.runtime.backends;

/* loaded from: classes4.dex */
public abstract class BackendResponse {

    public enum Status extends Enum<Status> {
        private static final /* synthetic */ Status[] $VALUES = null;
        public static final Status FATAL_ERROR = null;
        public static final Status INVALID_PAYLOAD = null;
        public static final Status OK = null;
        public static final Status TRANSIENT_ERROR = null;

        static {
            Status r0 = new Status("OK", 0);
            OK = r0;
            Status r1 = new Status("TRANSIENT_ERROR", 1);
            TRANSIENT_ERROR = r1;
            Status r2 = new Status("FATAL_ERROR", 2);
            FATAL_ERROR = r2;
            Status r3 = new Status("INVALID_PAYLOAD", 3);
            INVALID_PAYLOAD = r3;
            $VALUES = new Status[]{r0, r1, r2, r3};
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

    public BackendResponse() {
    }

    public static BackendResponse fatalError() {
        return new AutoValue_BackendResponse(Status.FATAL_ERROR, -1);
    }

    public static BackendResponse invalidPayload() {
        return new AutoValue_BackendResponse(Status.INVALID_PAYLOAD, -1);
    }

    public static BackendResponse ok(long r2) {
        return new AutoValue_BackendResponse(Status.OK, r2);
    }

    public static BackendResponse transientError() {
        return new AutoValue_BackendResponse(Status.TRANSIENT_ERROR, -1);
    }

    public abstract long getNextRequestWaitMillis();

    public abstract Status getStatus();
}
