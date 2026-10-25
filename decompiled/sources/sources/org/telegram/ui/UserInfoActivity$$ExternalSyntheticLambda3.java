package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class UserInfoActivity$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ UserInfoActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_account.TL_birthday f$2;
    public final /* synthetic */ TLRPC.UserFull f$3;
    public final /* synthetic */ int[] f$4;
    public final /* synthetic */ ArrayList f$5;

    public /* synthetic */ UserInfoActivity$$ExternalSyntheticLambda3(UserInfoActivity r1, TLObject r2, TL_account.TL_birthday r3, TLRPC.UserFull r4, int[] r5, ArrayList r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        UserInfoActivity.m21092$r8$lambda$EEdZrLoaNfePj9c9wxBiufMU0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
