package com.exteragram.messenger.translator;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda15 implements Utilities.Callback {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.InputPeer f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ CharSequence f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ Utilities.CallbackReturn f$5;
    public final /* synthetic */ ChatActivity f$6;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda15(BaseFragment r1, TLRPC.InputPeer r2, int r3, CharSequence r4, ArrayList r5, Utilities.CallbackReturn r6, ChatActivity r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r9) {
        TranslatorUtils.$r8$lambda$RaYg0oz_vg60aFgnHZ0iJpaAus0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, (String) r9);
    }
}
