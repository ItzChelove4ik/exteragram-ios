package org.telegram.ui;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$$ExternalSyntheticLambda15 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SelectAnimatedEmojiDialog f$0;
    public final /* synthetic */ Rect f$1;
    public final /* synthetic */ SelectAnimatedEmojiDialog.ImageViewEmoji f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ AnimatedEmojiDrawable f$5;

    public /* synthetic */ SelectAnimatedEmojiDialog$$ExternalSyntheticLambda15(SelectAnimatedEmojiDialog r1, Rect r2, SelectAnimatedEmojiDialog.ImageViewEmoji r3, boolean[] r4, Runnable r5, AnimatedEmojiDrawable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r8) {
        SelectAnimatedEmojiDialog.$r8$lambda$MG1f_Mlp0SbAkgGM5_34RG_OFHQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
