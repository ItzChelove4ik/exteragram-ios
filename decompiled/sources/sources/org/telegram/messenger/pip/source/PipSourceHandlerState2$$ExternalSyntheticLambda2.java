package org.telegram.messenger.pip.source;

import android.graphics.Canvas;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class PipSourceHandlerState2$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ IPipSourceDelegate f$0;

    public /* synthetic */ PipSourceHandlerState2$$ExternalSyntheticLambda2(IPipSourceDelegate r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.pipRenderForeground((Canvas) r1);
    }
}
