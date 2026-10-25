package org.telegram.ui.Components.Reactions;

import android.graphics.Canvas;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class CustomEmojiReactionsWindow$$ExternalSyntheticLambda2 implements SelectAnimatedEmojiDialog.BackgroundDelegate {
    public final /* synthetic */ CustomEmojiReactionsWindow f$0;
    public final /* synthetic */ ReactionsContainerLayout f$1;

    public /* synthetic */ CustomEmojiReactionsWindow$$ExternalSyntheticLambda2(CustomEmojiReactionsWindow r1, ReactionsContainerLayout r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.SelectAnimatedEmojiDialog.BackgroundDelegate
    public final void drawRect(Canvas r10, int r11, int r12, int r13, int r14, float r15, float r16) {
        CustomEmojiReactionsWindow.m12341$r8$lambda$J9Zz_cH5ZcsiZ6uwvhDLiUj4Js(this.f$0, this.f$1, r10, r11, r12, r13, r14, r15, r16);
    }
}
