package org.telegram.utils.camera.roundvideo;

import java.io.File;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoSession$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ RoundVideoSession f$0;
    public final /* synthetic */ RoundVideoMp4Writer f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ File f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ RoundVideoSession.OutputInvalidationReason f$5;
    public final /* synthetic */ RoundVideoSession.OutputGeneration f$6;

    public /* synthetic */ RoundVideoSession$$ExternalSyntheticLambda8(RoundVideoSession r1, RoundVideoMp4Writer r2, boolean r3, File r4, boolean r5, RoundVideoSession.OutputInvalidationReason r6, RoundVideoSession.OutputGeneration r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RoundVideoSession.$r8$lambda$FUST4X2EdqFdZmDiSwtm2IOY8YI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
