package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class UserInfoActivity$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ UserInfoActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_account.TL_birthday f$3;
    public final /* synthetic */ TLRPC.UserFull f$4;
    public final /* synthetic */ TLObject f$5;
    public final /* synthetic */ int[] f$6;
    public final /* synthetic */ ArrayList f$7;

    public /* synthetic */ UserInfoActivity$$ExternalSyntheticLambda4(UserInfoActivity r1, TLRPC.TL_error r2, TLObject r3, TL_account.TL_birthday r4, TLRPC.UserFull r5, TLObject r6, int[] r7, ArrayList r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UserInfoActivity.$r8$lambda$Jmg5919uLMlzviCnSkp7aLpXUt8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
