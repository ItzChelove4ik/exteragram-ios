package org.telegram.ui.Components;

import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda70 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda70(SharedMediaLayout r1, TL_stories.StoryItem r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        SharedMediaLayout.$r8$lambda$cUylFeDtfSXoeCcKAiZrSllYhqE(this.f$0, this.f$1, r2, r3);
    }
}
