package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ReportBottomSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$4$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ MessageObject f$3;

    public /* synthetic */ ReportBottomSheet$4$$ExternalSyntheticLambda2(ChatActivity r1, Context r2, Theme.ResourcesProvider r3, MessageObject r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReportBottomSheet.AnonymousClass4.$r8$lambda$o7YJQajG1ZXWrikg12x_57K64do(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
