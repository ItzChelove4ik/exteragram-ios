package org.telegram.messenger.pip.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes5.dex */
public class Trigger implements Runnable {
    private final Callback action;
    private final Handler handler;
    private final Runnable timeoutRunnable;
    private final AtomicBoolean triggered;

    public interface Callback {
        void run(boolean r1);
    }

    public static /* synthetic */ void $r8$lambda$ZU7V9vlGI0ZRRmAZeDXCJA_SEnU(Trigger r0) {
        r0.lambda$run$1();
    }

    public static /* synthetic */ void $r8$lambda$veaziGEfPREDVwCEj7z7J8n5ie0(Trigger r0, Callback r1) {
        r0.lambda$new$0(r1);
    }

    private Trigger(Handler r4, Callback r5, long r6) {
        this.triggered = new AtomicBoolean(false);
        this.handler = r4;
        this.action = r5;
        Trigger$$ExternalSyntheticLambda1 r0 = new Trigger$$ExternalSyntheticLambda1(this, r5);
        this.timeoutRunnable = r0;
        if (r6 <= 0) goto L6;
        r4.postDelayed(r0, r6);
        return;
    }

    private /* synthetic */ void lambda$new$0(Callback r3) {
        if (this.triggered.compareAndSet(false, true) == false) goto L6;
        r3.run(true);
        return;
    }

    private /* synthetic */ void lambda$run$1() {
        this.action.run(false);
    }

    public static Trigger run(Callback r2, long r3) {
        return new Trigger(ApplicationLoader.applicationHandler, r2, r3);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.triggered.compareAndSet(false, true) == false) goto L10;
        this.handler.removeCallbacks(this.timeoutRunnable);
        if (Looper.myLooper() != this.handler.getLooper()) goto L8;
        this.action.run(false);
        return;
    L8:
        this.handler.post(new Trigger$$ExternalSyntheticLambda0(this));
        return;
    }
}
