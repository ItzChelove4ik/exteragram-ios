package org.telegram.ui.web;

import java.io.InputStream;
import org.telegram.messenger.Timer;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebInstantView$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ Timer.Task f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Timer f$2;
    public final /* synthetic */ WebInstantView f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ WebInstantView$$ExternalSyntheticLambda0(Timer.Task r1, boolean[] r2, Timer r3, WebInstantView r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        WebInstantView.$r8$lambda$YlCjfT0mhFdTUR2hES1ATvDObGA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (InputStream) r7);
    }
}
