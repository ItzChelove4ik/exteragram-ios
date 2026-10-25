package org.telegram.ui.Cells;

import android.animation.ValueAnimator;
import org.telegram.ui.Cells.TextSelectionHelper;

/* loaded from: classes6.dex */
public final /* synthetic */ class TextSelectionHelper$ChatListTextSelectionHelper$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatMessageCell f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ TextSelectionHelper$ChatListTextSelectionHelper$$ExternalSyntheticLambda0(ChatMessageCell r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        TextSelectionHelper.ChatListTextSelectionHelper.m7101$r8$lambda$Gcwvj2Ry5WM_FcJtCCC93mDpYk(this.f$0, this.f$1, r2);
    }
}
