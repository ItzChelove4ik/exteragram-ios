package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda38 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda38(PeerStoriesView.AnonymousClass8 r1, TL_stories.StoryItem r2, boolean r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$4QYZfkrikloNPLJQj7z40rxlC9o(this.f$0, this.f$1, this.f$2, this.f$3, (Boolean) r4);
    }
}
