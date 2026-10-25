package org.telegram.ui.iv;

import android.graphics.Bitmap;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$$ExternalSyntheticLambda8 implements Utilities.Callback2 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ ImageView f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ int[] f$5;
    public final /* synthetic */ ButtonWithCounterView f$6;
    public final /* synthetic */ HorizontalScrollView f$7;
    public final /* synthetic */ boolean[] f$8;

    public /* synthetic */ ChatAttachAlertRichLayout$$ExternalSyntheticLambda8(String r1, String[] r2, ImageView r3, Theme.ResourcesProvider r4, boolean r5, int[] r6, ButtonWithCounterView r7, HorizontalScrollView r8, boolean[] r9) {
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

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r12, Object r13) {
        ChatAttachAlertRichLayout.m21586$r8$lambda$zp9lBohig9Yn8FGLzmyhbJ2dBw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (Bitmap) r12, (Boolean) r13);
    }
}
