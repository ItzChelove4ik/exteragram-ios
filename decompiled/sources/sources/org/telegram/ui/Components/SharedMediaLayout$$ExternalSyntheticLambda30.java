package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda30 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ StoriesController.BotPreviewsList f$1;
    public final /* synthetic */ ArrayList f$2;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda30(SharedMediaLayout r1, StoriesController.BotPreviewsList r2, ArrayList r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        SharedMediaLayout.$r8$lambda$JaHvFekyc84ovlPAcZfdw4TsMIg(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
