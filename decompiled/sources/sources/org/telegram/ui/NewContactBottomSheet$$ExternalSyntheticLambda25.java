package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class NewContactBottomSheet$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ NewContactBottomSheet$$ExternalSyntheticLambda25(Utilities.Callback r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NewContactBottomSheet.$r8$lambda$0PY0qvgygP7aPM219MWjKFoPcbM(this.f$0, this.f$1);
    }
}
