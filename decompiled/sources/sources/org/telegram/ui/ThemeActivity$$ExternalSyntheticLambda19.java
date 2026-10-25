package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeActivity$$ExternalSyntheticLambda19 implements Utilities.Callback {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ Utilities.Callback f$7;
    public final /* synthetic */ BottomSheet[] f$8;

    public /* synthetic */ ThemeActivity$$ExternalSyntheticLambda19(ButtonWithCounterView r1, MessagesController r2, String r3, int r4, Context r5, Theme.ResourcesProvider r6, int r7, Utilities.Callback r8, BottomSheet[] r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r11) {
        ThemeActivity.m20612$r8$lambda$XlJxHor5V0Uy8jknfANjs6yLaU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (Boolean) r11);
    }
}
