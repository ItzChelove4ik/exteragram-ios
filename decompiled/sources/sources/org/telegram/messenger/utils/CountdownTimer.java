package org.telegram.messenger.utils;

import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public class CountdownTimer {
    private final Callback callback;
    private final Runnable doUpdate;
    private boolean isRunning;
    private long seconds;

    public interface Callback {
        void onTimerUpdate(long r1);
    }

    public static /* synthetic */ void $r8$lambda$Q0hY3F9I6xL3bxDD8S2fuIMku4c(CountdownTimer r0) {
        r0.update();
    }

    public CountdownTimer(Callback r2) {
        this.doUpdate = new CountdownTimer$$ExternalSyntheticLambda0(this);
        this.callback = r2;
    }

    private void update() {
        long r0 = this.seconds;
        if (r0 <= 0) goto L6;
        long r02 = r0 - 1;
        this.seconds = r02;
        this.callback.onTimerUpdate(r02);
    L6:
        if (this.seconds > 0) goto L9;
        this.isRunning = false;
    L9:
        if (this.isRunning == false) goto L12;
        AndroidUtilities.runOnUIThread(this.doUpdate, 1000);
        return;
    }

    public void start(long r3) {
        if (this.isRunning == true) goto L5;
    L7:
        this.seconds = r3;
        if (r3 > 0) goto L11;
        stop();
        return;
    L11:
        this.isRunning = true;
        AndroidUtilities.cancelRunOnUIThread(this.doUpdate);
        AndroidUtilities.runOnUIThread(this.doUpdate, 1000);
        return;
    L5:
        if (this.seconds != r3) goto L7;
    }

    public void stop() {
        this.isRunning = false;
        AndroidUtilities.cancelRunOnUIThread(this.doUpdate);
    }
}
