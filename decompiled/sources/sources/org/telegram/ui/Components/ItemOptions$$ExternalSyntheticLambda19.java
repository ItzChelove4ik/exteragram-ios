package org.telegram.ui.Components;

import android.view.View;
import java.util.HashSet;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class ItemOptions$$ExternalSyntheticLambda19 implements View.OnClickListener {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ HashSet f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ StoriesController.StoryAlbum f$4;

    public /* synthetic */ ItemOptions$$ExternalSyntheticLambda19(boolean r1, HashSet r2, int r3, Utilities.Callback r4, StoriesController.StoryAlbum r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        ItemOptions.$r8$lambda$vSz0p4NuWPEworjG4CXrdcrV69Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
