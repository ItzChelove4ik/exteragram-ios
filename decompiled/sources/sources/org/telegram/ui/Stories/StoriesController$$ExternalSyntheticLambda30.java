package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda30 implements Runnable {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda30(Utilities.Callback r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesController.m19692$r8$lambda$b7OkWgFOuLvuxCAhP7cdmXUtw8(this.f$0, this.f$1);
    }
}
