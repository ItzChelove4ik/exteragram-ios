package com.exteragram.messenger.camera;

import androidx.camera.core.ZoomState;
import androidx.lifecycle.Observer;

/* loaded from: classes4.dex */
public final /* synthetic */ class InstantCameraZoomSlider$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ InstantCameraZoomSlider f$0;

    public /* synthetic */ InstantCameraZoomSlider$$ExternalSyntheticLambda2(InstantCameraZoomSlider r1) {
        this.f$0 = r1;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object r1) {
        InstantCameraZoomSlider.$r8$lambda$gO3UQutF66xYIJcAIdCV1LtLzHs(this.f$0, (ZoomState) r1);
    }
}
