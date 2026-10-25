package org.telegram.ui.Components;

import org.telegram.messenger.camera.CameraController;
import org.telegram.ui.Components.ChatAttachAlertPhotoLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPhotoLayout$10$$ExternalSyntheticLambda2 implements CameraController.VideoTakeCallback {
    public final /* synthetic */ ChatAttachAlertPhotoLayout.AnonymousClass10 f$0;

    public /* synthetic */ ChatAttachAlertPhotoLayout$10$$ExternalSyntheticLambda2(ChatAttachAlertPhotoLayout.AnonymousClass10 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.camera.CameraController.VideoTakeCallback
    public final void onFinishVideoRecording(String r1, long r2) {
        ChatAttachAlertPhotoLayout.AnonymousClass10.$r8$lambda$zHJ2G1OK6Q9aFSG0VnvNHvzaZWQ(this.f$0, r1, r2);
    }
}
