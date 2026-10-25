package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$StoryWidgetsCell$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ EmojiBottomSheet.StoryWidgetsCell f$0;
    public final /* synthetic */ EmojiBottomSheet.StoryWidgetsCell.Button[] f$1;

    public /* synthetic */ EmojiBottomSheet$StoryWidgetsCell$$ExternalSyntheticLambda0(EmojiBottomSheet.StoryWidgetsCell r1, EmojiBottomSheet.StoryWidgetsCell.Button[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        EmojiBottomSheet.StoryWidgetsCell.$r8$lambda$n_dt5VHIBdWZANpUXvyBvonVD8w(this.f$0, this.f$1, (Weather.State) r2);
    }
}
