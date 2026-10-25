package org.telegram.ui.Stories;

import android.animation.ValueAnimator;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$40$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PeerStoriesView.AnonymousClass40 f$0;
    public final /* synthetic */ ValueAnimator f$1;
    public final /* synthetic */ boolean[] f$2;

    public /* synthetic */ PeerStoriesView$40$$ExternalSyntheticLambda1(PeerStoriesView.AnonymousClass40 r1, ValueAnimator r2, boolean[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        PeerStoriesView.AnonymousClass40.$r8$lambda$bOjwct9yPRlFl9QAPId5ZESDfWs(this.f$0, this.f$1, this.f$2, r3);
    }
}
