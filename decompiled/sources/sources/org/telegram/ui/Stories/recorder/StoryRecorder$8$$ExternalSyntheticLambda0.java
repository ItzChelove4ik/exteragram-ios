package org.telegram.ui.Stories.recorder;

import java.io.File;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$8$$ExternalSyntheticLambda0 implements Utilities.Callback3 {
    public final /* synthetic */ StoryRecorder.AnonymousClass8 f$0;
    public final /* synthetic */ RoundVideoRecorder f$1;

    public /* synthetic */ StoryRecorder$8$$ExternalSyntheticLambda0(StoryRecorder.AnonymousClass8 r1, RoundVideoRecorder r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback3
    public final void run(Object r2, Object r3, Object r4) {
        StoryRecorder.AnonymousClass8.m20558$r8$lambda$2gBK8FoGHMOEwZPEkYTOcZzfq8(this.f$0, this.f$1, (File) r2, (String) r3, (Long) r4);
    }
}
