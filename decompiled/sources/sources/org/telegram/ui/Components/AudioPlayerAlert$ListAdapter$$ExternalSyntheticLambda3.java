package org.telegram.ui.Components;

import android.view.MotionEvent;
import android.view.View;
import org.telegram.ui.Cells.AudioPlayerCell;
import org.telegram.ui.Components.AudioPlayerAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda3 implements View.OnTouchListener {
    public final /* synthetic */ AudioPlayerAlert.ListAdapter f$0;
    public final /* synthetic */ AudioPlayerCell f$1;

    public /* synthetic */ AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda3(AudioPlayerAlert.ListAdapter r1, AudioPlayerCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View r2, MotionEvent r3) {
        return AudioPlayerAlert.ListAdapter.$r8$lambda$VBc2oUX0wg5M_77EV9_EWFP2Kes(this.f$0, this.f$1, r2, r3);
    }
}
