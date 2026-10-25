package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ byte[] f$4;
    public final /* synthetic */ ChatActivity f$5;
    public final /* synthetic */ MessageObject f$6;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda16(TLObject r1, Context r2, Theme.ResourcesProvider r3, long r4, byte[] r6, ChatActivity r7, MessageObject r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReportBottomSheet.$r8$lambda$DdbvQ1AdVDFEvfY4VDSDQYMcg7I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
