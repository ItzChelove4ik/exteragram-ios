package org.telegram.messenger;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final /* synthetic */ class AiTonesController$$ExternalSyntheticLambda0 implements Executor {
    public /* synthetic */ AiTonesController$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        AndroidUtilities.runOnUIThread(r1);
    }
}
