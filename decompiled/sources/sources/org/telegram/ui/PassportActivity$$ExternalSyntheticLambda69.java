package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda69 implements RequestDelegate {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$3;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ ArrayList f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda69(PassportActivity r1, PassportActivity.ErrorRunnable r2, boolean r3, TLRPC.TL_secureRequiredType r4, TLRPC.TL_secureRequiredType r5, boolean r6, ArrayList r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        PassportActivity.m16402$r8$lambda$HpjrHm86krwJCwlBrmpJDHhnhs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r11, r12);
    }
}
