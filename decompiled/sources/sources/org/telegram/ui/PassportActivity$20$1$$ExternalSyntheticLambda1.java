package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$20$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PassportActivity.ErrorRunnable f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ PassportActivity$20$1$$ExternalSyntheticLambda1(PassportActivity.ErrorRunnable r1, TLRPC.TL_error r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.AnonymousClass20.AnonymousClass1.m16535$r8$lambda$8A9NA18SIwQeWDFtLr02MDjbTU(this.f$0, this.f$1, this.f$2);
    }
}
