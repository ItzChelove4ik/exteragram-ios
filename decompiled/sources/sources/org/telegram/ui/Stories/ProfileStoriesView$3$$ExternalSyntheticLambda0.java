package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.ui.Stories.ProfileStoriesView;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileStoriesView$3$$ExternalSyntheticLambda0 implements StoryViewer.HolderClip {
    public final /* synthetic */ ProfileStoriesView.AnonymousClass3 f$0;
    public final /* synthetic */ RectF f$1;
    public final /* synthetic */ ProfileStoriesView.StoryCircle f$2;
    public final /* synthetic */ RectF f$3;
    public final /* synthetic */ ProfileStoriesView.StoryCircle f$4;

    public /* synthetic */ ProfileStoriesView$3$$ExternalSyntheticLambda0(ProfileStoriesView.AnonymousClass3 r1, RectF r2, ProfileStoriesView.StoryCircle r3, RectF r4, ProfileStoriesView.StoryCircle r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.Stories.StoryViewer.HolderClip
    public final void clip(Canvas r10, RectF r11, float r12, boolean r13) {
        ProfileStoriesView.AnonymousClass3.$r8$lambda$SjqFQApuaYFC42Vp5VbxuBnWgsA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11, r12, r13);
    }
}
