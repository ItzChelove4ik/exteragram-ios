package org.telegram.ui;

import java.util.function.Predicate;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda26 implements Predicate {
    public final /* synthetic */ long f$0;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda26(long r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r3) {
        return CallLogActivity.$r8$lambda$_0SBMTDZzqEhCFeXNeob46nKQg4(this.f$0, (TLRPC.User) r3);
    }
}
