package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda40 implements Utilities.Callback4 {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda40(TranslateController r1, MessageObject r2, boolean r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback4
    public final void run(Object r10, Object r11, Object r12, Object r13) {
        TranslateController.$r8$lambda$64Lep8ul4ZigUxFPbYRXvppzRYc(this.f$0, this.f$1, this.f$2, this.f$3, (Boolean) r10, (Integer) r11, (TLRPC.TL_textWithEntities) r12, (String) r13);
    }
}
