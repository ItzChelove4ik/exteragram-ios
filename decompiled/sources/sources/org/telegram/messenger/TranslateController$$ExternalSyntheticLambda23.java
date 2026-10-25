package org.telegram.messenger;

import java.util.Set;
import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TranslateController.PendingTranslation f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ Set f$6;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda23(TranslateController r1, TranslateController.PendingTranslation r2, TLObject r3, boolean r4, TLRPC.TL_error r5, long r6, Set r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranslateController.m5023$r8$lambda$5AcYUmp7hJXLPqj8Upb3H1YdQ4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
