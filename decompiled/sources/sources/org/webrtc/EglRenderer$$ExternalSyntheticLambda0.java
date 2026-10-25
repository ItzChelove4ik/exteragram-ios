package org.webrtc;

import org.webrtc.EglRenderer;
import org.webrtc.RendererCommon;

/* loaded from: classes6.dex */
public final /* synthetic */ class EglRenderer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ EglRenderer f$0;
    public final /* synthetic */ RendererCommon.GlDrawer f$1;
    public final /* synthetic */ EglRenderer.FrameListener f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ EglRenderer$$ExternalSyntheticLambda0(EglRenderer r1, RendererCommon.GlDrawer r2, EglRenderer.FrameListener r3, float r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EglRenderer.$r8$lambda$p4pBsZz9unlJCHmOkWZ3krSu6ho(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
