package org.telegram.ui;

import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda456 implements View.OnClickListener {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ TLRPC.InputPeer f$3;
    public final /* synthetic */ int[] f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ TL_iv.RichMessage f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ Utilities.CallbackReturn f$9;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda456(ChatActivity r1, int r2, ArrayList r3, TLRPC.InputPeer r4, int[] r5, String r6, String r7, TL_iv.RichMessage r8, boolean r9, Utilities.CallbackReturn r10) {
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
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r12) {
        ChatActivity.m7511$r8$lambda$hfo4DNoW8GuomAOXXEXINumr0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r12);
    }
}
