package org.telegram.ui.Components.Premium;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LimitReachedBottomSheet$$ExternalSyntheticLambda20 implements Runnable {
    public final /* synthetic */ LimitReachedBottomSheet f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_messages_inactiveChats f$3;

    public /* synthetic */ LimitReachedBottomSheet$$ExternalSyntheticLambda20(LimitReachedBottomSheet r1, ArrayList r2, int r3, TLRPC.TL_messages_inactiveChats r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LimitReachedBottomSheet.$r8$lambda$YjX9okCNMaKHpLC12iX596aGxyE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
