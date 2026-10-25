package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ChatAttachAlertDocumentLayout.SearchAdapter f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ ArrayList f$8;

    public /* synthetic */ ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda3(ChatAttachAlertDocumentLayout.SearchAdapter r1, AccountInstance r2, String r3, int r4, boolean r5, long r6, long r8, ArrayList r10, ArrayList r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r10;
        this.f$8 = r11;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r14, TLRPC.TL_error r15) {
        ChatAttachAlertDocumentLayout.SearchAdapter.$r8$lambda$4PXWLWw74bAiZGlojxMgBZplQfo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r14, r15);
    }
}
