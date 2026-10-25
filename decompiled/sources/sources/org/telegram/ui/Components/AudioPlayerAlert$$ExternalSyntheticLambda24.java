package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda24 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ BaseFragment f$2;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda24(AudioPlayerAlert r1, Theme.ResourcesProvider r2, BaseFragment r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r12, ArrayList r13, CharSequence r14, boolean r15, boolean r16, int r17, int r18, TopicsFragment r19) {
        return AudioPlayerAlert.$r8$lambda$W0cVVTaNUiGwNt3PdvLUxZ2dYfc(this.f$0, this.f$1, this.f$2, r12, r13, r14, r15, r16, r17, r18, r19);
    }
}
