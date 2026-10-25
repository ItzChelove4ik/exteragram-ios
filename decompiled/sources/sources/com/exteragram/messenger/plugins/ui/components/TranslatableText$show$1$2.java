package com.exteragram.messenger.plugins.ui.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2", "Landroid/animation/AnimatorListenerAdapter;", "onAnimationEnd", _UrlKt.FRAGMENT_ENCODE_SET, "animation", "Landroid/animation/Animator;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class TranslatableText$show$1$2 extends AnimatorListenerAdapter {
    final /* synthetic */ TextView $view;
    final /* synthetic */ TranslatableText this$0;

    public TranslatableText$show$1$2(TranslatableText r1, TextView r2) {
        this.this$0 = r1;
        this.$view = r2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator r3) {
        TranslatableText.access$setOldLayout$p(this.this$0, null);
        TranslatableText.access$setTransitionProgress$p(this.this$0, 1.0f);
        TranslatableText.access$setTransitionAnimator$p(this.this$0, null);
        this.$view.requestLayout();
        this.$view.invalidate();
    }
}
