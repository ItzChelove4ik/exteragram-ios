package org.telegram.ui;

import java.io.File;
import org.telegram.messenger.FileLoader;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda69 implements FileLoader.FileResolver {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ TLObject f$1;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda69(PhotoViewer r1, TLObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.FileLoader.FileResolver
    public final File getFile() {
        return PhotoViewer.$r8$lambda$NPYLbl8nSxy62PPJTtu4PiRy98I(this.f$0, this.f$1);
    }
}
