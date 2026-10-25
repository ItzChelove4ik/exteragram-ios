package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda74 implements Runnable {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$4;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ Runnable f$8;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda74(PassportActivity r1, TLRPC.TL_error r2, PassportActivity.ErrorRunnable r3, boolean r4, TLRPC.TL_secureRequiredType r5, TLRPC.TL_secureRequiredType r6, boolean r7, ArrayList r8, Runnable r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.$r8$lambda$yAq0Zgbkpps5JQGNGpY6cRSXJbs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
