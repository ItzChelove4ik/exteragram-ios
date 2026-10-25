package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes7.dex */
public final /* synthetic */ class SuggestBirthdayActionLayout$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ SuggestBirthdayActionLayout f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;
    public final /* synthetic */ TL_account.TL_birthday f$2;

    public /* synthetic */ SuggestBirthdayActionLayout$$ExternalSyntheticLambda1(SuggestBirthdayActionLayout r1, TLRPC.UserFull r2, TL_account.TL_birthday r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SuggestBirthdayActionLayout.$r8$lambda$Avf38b9pChx6IA2bCG57Is3SvVg(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
