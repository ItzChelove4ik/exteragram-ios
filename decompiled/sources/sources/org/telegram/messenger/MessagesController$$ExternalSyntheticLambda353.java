package org.telegram.messenger;

import org.telegram.messenger.MessagesStorage;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda353 implements MessagesStorage.IntCallback {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda353(int[] r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r2) {
        MessagesController.$r8$lambda$ilNh6I1eyictndatRRWaR5JV9vo(this.f$0, this.f$1, r2);
    }
}
