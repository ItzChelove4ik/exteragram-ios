package org.telegram.ui.Components.Premium;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Loadable;

/* loaded from: classes7.dex */
public final /* synthetic */ class LimitReachedBottomSheet$$ExternalSyntheticLambda23 implements Utilities.Callback {
    public final /* synthetic */ LimitReachedBottomSheet f$0;
    public final /* synthetic */ Loadable f$1;

    public /* synthetic */ LimitReachedBottomSheet$$ExternalSyntheticLambda23(LimitReachedBottomSheet r1, Loadable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        LimitReachedBottomSheet.$r8$lambda$x6IvzePHnUd6ndGizBdYLZNLOvs(this.f$0, this.f$1, (TLRPC.TL_error) r2);
    }
}
