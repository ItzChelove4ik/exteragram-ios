package org.telegram.ui;

import android.widget.FrameLayout;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda50 implements RequestDelegate {
    public final /* synthetic */ String[] f$0;
    public final /* synthetic */ FrameLayout f$1;
    public final /* synthetic */ LinkSpanDrawable.LinksTextView f$2;
    public final /* synthetic */ BottomSheet f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda50(String[] r1, FrameLayout r2, LinkSpanDrawable.LinksTextView r3, BottomSheet r4, Theme.ResourcesProvider r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        CallLogActivity.$r8$lambda$1AAYfCqj63puMb9lzhgTNOeBKdY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
