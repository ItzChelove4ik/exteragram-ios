package org.telegram.ui.Components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class PostRunnableHolder {
    private final HashMap<Runnable, Runnable> wrappedRunnable;

    public static /* synthetic */ void $r8$lambda$X7iKl57UDAm3TFOQXe60llltWos(PostRunnableHolder r0, Runnable r1) {
        r0.lambda$post$0(r1);
    }

    public PostRunnableHolder() {
        this.wrappedRunnable = new HashMap();
    }

    private /* synthetic */ void lambda$post$0(Runnable r1) {
        r1.run();
        this.wrappedRunnable.remove(r1);
    }

    public void cancel(Runnable r1) {
        Runnable r0 = this.wrappedRunnable.remove(r1);
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        return;
    }

    public void clear() {
        Iterator<Map.Entry<Runnable, Runnable>> r0 = this.wrappedRunnable.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0.next().getValue());
        goto L4
    L6:
        this.wrappedRunnable.clear();
    }

    public void post(Runnable r2, long r3) {
        cancel(r2);
        PostRunnableHolder$$ExternalSyntheticLambda0 r0 = new PostRunnableHolder$$ExternalSyntheticLambda0(this, r2);
        this.wrappedRunnable.put(r2, r0);
        if (r3 <= 0) goto L6;
        AndroidUtilities.runOnUIThread(r0, r3);
        return;
    L6:
        AndroidUtilities.runOnUIThread(r0);
    }
}
