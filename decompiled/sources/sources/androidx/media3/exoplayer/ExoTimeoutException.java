package androidx.media3.exoplayer;

/* loaded from: classes4.dex */
public final class ExoTimeoutException extends RuntimeException {
    public final int timeoutOperation;

    public ExoTimeoutException(int r2) {
        super(getErrorMessage(r2));
        this.timeoutOperation = r2;
    }

    private static String getErrorMessage(int r1) {
        if (r1 != 1) goto L5;
        return "Player release timed out.";
    L5:
        if (r1 != 2) goto L7;
        return "Setting foreground mode timed out.";
    L7:
        if (r1 == 3) goto L10;
        return "Undefined timeout.";
    L10:
        return "Detaching surface timed out.";
    }
}
