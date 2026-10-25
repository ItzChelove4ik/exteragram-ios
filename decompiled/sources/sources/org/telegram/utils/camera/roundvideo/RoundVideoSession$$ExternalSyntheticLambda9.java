package org.telegram.utils.camera.roundvideo;

import java.io.File;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoSession$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ RoundVideoSession f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ RoundVideoMp4Writer f$2;
    public final /* synthetic */ RoundVideoSession.OutputGeneration f$3;
    public final /* synthetic */ File f$4;

    public /* synthetic */ RoundVideoSession$$ExternalSyntheticLambda9(RoundVideoSession r1, boolean r2, RoundVideoMp4Writer r3, RoundVideoSession.OutputGeneration r4, File r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RoundVideoSession.$r8$lambda$xCjTDFhOAvgAgt__DxKVwDx15As(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
