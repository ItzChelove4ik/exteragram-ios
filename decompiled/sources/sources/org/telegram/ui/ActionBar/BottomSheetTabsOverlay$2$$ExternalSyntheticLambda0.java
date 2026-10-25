package org.telegram.ui.ActionBar;

import android.graphics.Bitmap;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.BottomSheetTabsOverlay;

/* loaded from: classes6.dex */
public final /* synthetic */ class BottomSheetTabsOverlay$2$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ BottomSheetTabs.WebTabData f$0;
    public final /* synthetic */ BottomSheetTabsOverlay.Sheet f$1;

    public /* synthetic */ BottomSheetTabsOverlay$2$$ExternalSyntheticLambda0(BottomSheetTabs.WebTabData r1, BottomSheetTabsOverlay.Sheet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        BottomSheetTabsOverlay.AnonymousClass2.$r8$lambda$GCfik7AYPJcOu1GrqTlmVf7Z84w(this.f$0, this.f$1, (Bitmap) r2);
    }
}
