package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.SessionState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public abstract class SessionTransferCallback {
    public static final int TRANSFER_FAILED_REASON_STORE_SESSION_STATE = 100;
    public static final int TRANSFER_FAILED_REASON_TRANSFER_TIMEOUT = 101;
    public static final int TRANSFER_TYPE_FROM_REMOTE_TO_LOCAL = 1;
    public static final int TRANSFER_TYPE_UNKNOWN = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransferFailedReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransferType {
    }

    public SessionTransferCallback() {
    }

    public void onTransferFailed(int r1, int r2) {
    }

    public void onTransferred(int r1, SessionState r2) {
    }

    public void onTransferring(int r1) {
    }
}
