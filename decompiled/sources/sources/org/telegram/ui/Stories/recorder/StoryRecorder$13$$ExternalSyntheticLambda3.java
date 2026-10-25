package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.camera.CameraController;
import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$13$$ExternalSyntheticLambda3 implements CameraController.VideoTakeCallback {
    public final /* synthetic */ StoryRecorder.AnonymousClass13 f$0;

    public /* synthetic */ StoryRecorder$13$$ExternalSyntheticLambda3(StoryRecorder.AnonymousClass13 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.camera.CameraController.VideoTakeCallback
    public final void onFinishVideoRecording(String r1, long r2) {
        StoryRecorder.AnonymousClass13.m20554$r8$lambda$dJEOgd0Eq4gNt1W8tmeUuVFk6M(this.f$0, r1, r2);
    }
}
