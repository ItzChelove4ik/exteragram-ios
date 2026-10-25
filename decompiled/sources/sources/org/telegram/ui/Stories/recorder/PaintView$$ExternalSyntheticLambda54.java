package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$$ExternalSyntheticLambda54 implements Utilities.Callback {
    public final /* synthetic */ PaintView f$0;
    public final /* synthetic */ EmojiBottomSheet f$1;

    public /* synthetic */ PaintView$$ExternalSyntheticLambda54(PaintView r1, EmojiBottomSheet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        PaintView.$r8$lambda$QgZACO4zVBx_Bnl7TAfGDW2LDQg(this.f$0, this.f$1, (Weather.State) r2);
    }
}
