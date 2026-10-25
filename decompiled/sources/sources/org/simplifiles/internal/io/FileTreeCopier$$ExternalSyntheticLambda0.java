package org.simplifiles.internal.io;

import j$.nio.file.Path;
import kotlin.jvm.functions.Function1;
import org.simplifiles.files.DirectoryTransferOptions;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileTreeCopier$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ DirectoryTransferOptions f$0;

    public /* synthetic */ FileTreeCopier$$ExternalSyntheticLambda0(DirectoryTransferOptions r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return Boolean.valueOf(FileTreeCopier.$r8$lambda$mUdELbRj367i4FBJiko7U8j6JPQ(this.f$0, (Path) r1));
    }
}
