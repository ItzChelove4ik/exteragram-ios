package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.Cells.AudioPlayerCell;
import org.telegram.ui.Components.AudioPlayerAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public final /* synthetic */ AudioPlayerAlert.ListAdapter f$0;
    public final /* synthetic */ AudioPlayerCell f$1;
    public final /* synthetic */ MessageObject f$2;

    public /* synthetic */ AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda4(AudioPlayerAlert.ListAdapter r1, AudioPlayerCell r2, MessageObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        AudioPlayerAlert.ListAdapter.m8926$r8$lambda$mkoTzQ0tK9c1SZVkknqfe1HI(this.f$0, this.f$1, this.f$2, r3);
    }
}
