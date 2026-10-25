package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$20$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.AnonymousClass20.AnonymousClass1 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$2;
    public final /* synthetic */ PassportActivity.PassportActivityDelegate f$3;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$4;

    public /* synthetic */ PassportActivity$20$1$$ExternalSyntheticLambda0(PassportActivity.AnonymousClass20.AnonymousClass1 r1, String r2, TLRPC.TL_secureRequiredType r3, PassportActivity.PassportActivityDelegate r4, PassportActivity.ErrorRunnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        PassportActivity.AnonymousClass20.AnonymousClass1.$r8$lambda$J29p3AeNEB22GGTl9uuQyl_7HEk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
