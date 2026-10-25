package org.telegram.ui;

import android.widget.FrameLayout;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda51 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ FrameLayout f$2;
    public final /* synthetic */ LinkSpanDrawable.LinksTextView f$3;
    public final /* synthetic */ BottomSheet f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda51(TLObject r1, String[] r2, FrameLayout r3, LinkSpanDrawable.LinksTextView r4, BottomSheet r5, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CallLogActivity.m6525$r8$lambda$XP3zZ7XrhNj3c7Y0VBP453XMXs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
