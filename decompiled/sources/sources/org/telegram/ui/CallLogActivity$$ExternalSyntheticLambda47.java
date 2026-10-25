package org.telegram.ui;

import android.widget.FrameLayout;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda47 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.InputGroupCall f$1;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ FrameLayout f$3;
    public final /* synthetic */ LinkSpanDrawable.LinksTextView f$4;
    public final /* synthetic */ BottomSheet f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda47(int r1, TLRPC.InputGroupCall r2, String[] r3, FrameLayout r4, LinkSpanDrawable.LinksTextView r5, BottomSheet r6, Theme.ResourcesProvider r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        CallLogActivity.$r8$lambda$GHC6S6IidW7fVoaBk9qPej04rH0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
