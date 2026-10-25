package org.telegram.ui.Stars;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda112 implements Utilities.Callback2 {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda112(StarGiftSheet r1, Utilities.Callback2 r2, ArrayList r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        StarGiftSheet.m18949$r8$lambda$4Y3YMpCDqdUJ31OJerdRyMT5sk(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.Updates) r7, (TLRPC.TL_error) r8);
    }
}
