package org.telegram.ui;

import java.io.File;
import org.telegram.messenger.FileLoader;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda70 implements FileLoader.FileResolver {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ TLRPC.Message f$1;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda70(PhotoViewer r1, TLRPC.Message r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.FileLoader.FileResolver
    public final File getFile() {
        return PhotoViewer.$r8$lambda$ZtFiMiuHtWCcri21zgvm0fpwKQM(this.f$0, this.f$1);
    }
}
