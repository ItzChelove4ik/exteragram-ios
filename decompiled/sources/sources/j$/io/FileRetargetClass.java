package j$.io;

import j$.nio.file.Path;
import java.io.File;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class FileRetargetClass {
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Path toPath(File r1) {
        if ((r1 instanceof FileRetargetInterface) == false) goto L7;
        return ((FileRetargetInterface) r1).toPath();
    L7:
        return DesugarFile.toPath(r1);
    }
}
