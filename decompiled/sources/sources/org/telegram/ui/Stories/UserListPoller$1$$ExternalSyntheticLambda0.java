package org.telegram.ui.Stories;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.Stories.UserListPoller;

/* loaded from: classes3.dex */
public final /* synthetic */ class UserListPoller$1$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ UserListPoller.AnonymousClass1 f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ UserListPoller$1$$ExternalSyntheticLambda0(UserListPoller.AnonymousClass1 r1, ArrayList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        UserListPoller.AnonymousClass1.$r8$lambda$61rVoe1u7Mevb7ZKAMfCZuQ1ADw(this.f$0, this.f$1, (Vector) r2, (TLRPC.TL_error) r3);
    }
}
