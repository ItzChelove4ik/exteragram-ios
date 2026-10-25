package androidx.car.app;

import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.serialization.BundlerException;
import java.security.InvalidParameterException;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class FailureResponse {
    public static final int BUNDLER_EXCEPTION = 1;
    public static final int ILLEGAL_STATE_EXCEPTION = 2;
    public static final int INVALID_PARAMETER_EXCEPTION = 3;
    public static final int REMOTE_EXCEPTION = 6;
    public static final int RUNTIME_EXCEPTION = 5;
    public static final int SECURITY_EXCEPTION = 4;
    public static final int UNKNOWN_ERROR = 0;
    private final int mErrorType;
    private final String mStackTrace;

    private FailureResponse() {
        this.mStackTrace = null;
        this.mErrorType = 0;
    }

    public FailureResponse(Throwable r2) {
        Objects.requireNonNull(r2);
        this.mStackTrace = Log.getStackTraceString(r2);
        if ((r2 instanceof BundlerException) == false) goto L7;
        this.mErrorType = 1;
        return;
    L7:
        if ((r2 instanceof IllegalStateException) == false) goto L11;
        this.mErrorType = 2;
        return;
    L11:
        if ((r2 instanceof InvalidParameterException) == false) goto L15;
        this.mErrorType = 3;
        return;
    L15:
        if ((r2 instanceof SecurityException) == false) goto L19;
        this.mErrorType = 4;
        return;
    L19:
        if ((r2 instanceof RuntimeException) == false) goto L23;
        this.mErrorType = 5;
        return;
    L23:
        if ((r2 instanceof RemoteException) == false) goto L26;
        this.mErrorType = 6;
        return;
    L26:
        this.mErrorType = 0;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof FailureResponse) == true) goto L5;
        return false;
    L5:
        FailureResponse r42 = (FailureResponse) r4;
        if (this.mErrorType == r42.mErrorType) goto L8;
    L11:
        return false;
    L8:
        if (Objects.equals(this.mStackTrace, r42.mStackTrace) == false) goto L11;
        return true;
    }

    public int getErrorType() {
        return this.mErrorType;
    }

    public String getStackTrace() {
        String r0 = this.mStackTrace;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mErrorType), this.mStackTrace});
    }
}
