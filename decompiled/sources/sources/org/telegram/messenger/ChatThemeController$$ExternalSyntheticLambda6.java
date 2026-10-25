package org.telegram.messenger;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda6 implements Executor {
    public final /* synthetic */ DispatchQueue f$0;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda6(DispatchQueue r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.f$0.postRunnable(r1);
    }
}
