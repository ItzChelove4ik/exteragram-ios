package org.simplifiles.internal.io;

import j$.nio.file.Path;
import kotlin.jvm.functions.Function1;
import org.simplifiles.files.DirectoryTransferOptions;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileTreeCopier$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ DirectoryTransferOptions f$0;

    public /* synthetic */ FileTreeCopier$$ExternalSyntheticLambda2(DirectoryTransferOptions r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return Boolean.valueOf(FileTreeCopier.m3864$r8$lambda$vgR1FcIqk7lNtPkCVl7ezhx3g8(this.f$0, (Path) r1));
    }
}
