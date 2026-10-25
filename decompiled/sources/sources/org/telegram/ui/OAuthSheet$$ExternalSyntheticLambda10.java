package org.telegram.ui;

import android.content.Context;
import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda10 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ Runnable f$10;
    public final /* synthetic */ TLRPC.TL_urlAuthResultRequest f$2;
    public final /* synthetic */ int[] f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ BaseFragment f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ String f$8;
    public final /* synthetic */ boolean[] f$9;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda10(ButtonWithCounterView r1, ButtonWithCounterView r2, TLRPC.TL_urlAuthResultRequest r3, int[] r4, Context r5, BaseFragment r6, boolean r7, boolean r8, String r9, boolean[] r10, Runnable r11) {
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
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r13) {
        OAuthSheet.$r8$lambda$AAswbIL2pMdG7We6ICRnWfm2ZPA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r13);
    }
}
