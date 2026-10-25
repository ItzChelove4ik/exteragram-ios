package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$20$1$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ PassportActivity.AnonymousClass20.AnonymousClass1 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$3;
    public final /* synthetic */ PassportActivity.PassportActivityDelegate f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$6;

    public /* synthetic */ PassportActivity$20$1$$ExternalSyntheticLambda4(PassportActivity.AnonymousClass20.AnonymousClass1 r1, TLObject r2, String r3, TLRPC.TL_secureRequiredType r4, PassportActivity.PassportActivityDelegate r5, TLRPC.TL_error r6, PassportActivity.ErrorRunnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.AnonymousClass20.AnonymousClass1.$r8$lambda$K3AZo5iMJqjVgG0goe95dhc7Exs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
