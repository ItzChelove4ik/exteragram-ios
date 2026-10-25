package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.SecureDocument;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$20$1$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ PassportActivity.AnonymousClass20.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ ArrayList f$10;
    public final /* synthetic */ SecureDocument f$11;
    public final /* synthetic */ SecureDocument f$12;
    public final /* synthetic */ SecureDocument f$13;
    public final /* synthetic */ ArrayList f$14;
    public final /* synthetic */ String f$15;
    public final /* synthetic */ String f$16;
    public final /* synthetic */ int f$17;
    public final /* synthetic */ Runnable f$18;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TL_account.saveSecureValue f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$6;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$7;
    public final /* synthetic */ TLRPC.TL_secureValue f$8;
    public final /* synthetic */ TLRPC.TL_secureValue f$9;

    public /* synthetic */ PassportActivity$20$1$$ExternalSyntheticLambda3(PassportActivity.AnonymousClass20.AnonymousClass1 r1, TLRPC.TL_error r2, PassportActivity.ErrorRunnable r3, String r4, TL_account.saveSecureValue r5, boolean r6, TLRPC.TL_secureRequiredType r7, TLRPC.TL_secureRequiredType r8, TLRPC.TL_secureValue r9, TLRPC.TL_secureValue r10, ArrayList r11, SecureDocument r12, SecureDocument r13, SecureDocument r14, ArrayList r15, String r16, String r17, int r18, Runnable r19) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
        this.f$12 = r13;
        this.f$13 = r14;
        this.f$14 = r15;
        this.f$15 = r16;
        this.f$16 = r17;
        this.f$17 = r18;
        this.f$18 = r19;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.AnonymousClass20.AnonymousClass1.$r8$lambda$ebYN6E8Hsu6X5dWEfEWLUhTrRNE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18);
    }
}
