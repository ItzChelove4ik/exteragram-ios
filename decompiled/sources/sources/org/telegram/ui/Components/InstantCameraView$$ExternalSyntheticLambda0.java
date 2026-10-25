package org.telegram.ui.Components;

import java.io.File;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.InstantCameraView;

/* loaded from: classes7.dex */
public final /* synthetic */ class InstantCameraView$$ExternalSyntheticLambda0 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ InstantCameraView f$0;
    public final /* synthetic */ File f$1;
    public final /* synthetic */ InstantCameraView.SendOptions f$2;
    public final /* synthetic */ VideoEditedInfo f$3;

    public /* synthetic */ InstantCameraView$$ExternalSyntheticLambda0(InstantCameraView r1, File r2, InstantCameraView.SendOptions r3, VideoEditedInfo r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r8, int r9, int r10) {
        InstantCameraView.$r8$lambda$VCbx47UDplgrpdghX9vvodd0PME(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
