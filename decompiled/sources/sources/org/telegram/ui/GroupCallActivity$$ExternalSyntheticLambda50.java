package org.telegram.ui;

import java.util.HashSet;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda50 implements Utilities.Callback2 {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ ChatObject.Call f$1;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda50(GroupCallActivity r1, ChatObject.Call r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        GroupCallActivity.m15065$r8$lambda$tB6ybx_2HrPQy03TA2ufqnHypY(this.f$0, this.f$1, (Boolean) r2, (HashSet) r3);
    }
}
