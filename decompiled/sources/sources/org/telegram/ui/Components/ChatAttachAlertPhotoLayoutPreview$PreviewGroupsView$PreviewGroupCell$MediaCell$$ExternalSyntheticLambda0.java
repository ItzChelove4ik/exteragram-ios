package org.telegram.ui.Components;

import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$PreviewGroupCell$MediaCell$$ExternalSyntheticLambda0 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ ChatAttachAlertPhotoLayoutPreview.PreviewGroupsView.PreviewGroupCell.MediaCell f$0;
    public final /* synthetic */ MediaController.PhotoEntry f$1;

    public /* synthetic */ ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$PreviewGroupCell$MediaCell$$ExternalSyntheticLambda0(ChatAttachAlertPhotoLayoutPreview.PreviewGroupsView.PreviewGroupCell.MediaCell r1, MediaController.PhotoEntry r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r7, boolean r8, boolean r9, boolean r10) {
        ChatAttachAlertPhotoLayoutPreview.PreviewGroupsView.PreviewGroupCell.MediaCell.$r8$lambda$vBrPPztxOhW_QVE2L2G8eLqxCqw(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
