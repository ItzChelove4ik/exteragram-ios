package org.telegram.ui.Business;

import android.content.SharedPreferences;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TimezonesController$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ TimezonesController f$0;
    public final /* synthetic */ SharedPreferences f$1;

    public /* synthetic */ TimezonesController$$ExternalSyntheticLambda0(TimezonesController r1, SharedPreferences r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        TimezonesController.$r8$lambda$lZbl1KMXn6igVK4mOPpyuY7xotA(this.f$0, this.f$1, r2, r3);
    }
}
