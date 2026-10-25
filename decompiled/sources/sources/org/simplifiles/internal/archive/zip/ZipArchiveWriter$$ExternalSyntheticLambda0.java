package org.simplifiles.internal.archive.zip;

import j$.nio.file.Path;
import kotlin.jvm.functions.Function1;
import org.simplifiles.archive.ArchiveSaveOptions;

/* loaded from: classes5.dex */
public final /* synthetic */ class ZipArchiveWriter$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ ArchiveSaveOptions f$0;

    public /* synthetic */ ZipArchiveWriter$$ExternalSyntheticLambda0(ArchiveSaveOptions r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return Boolean.valueOf(ZipArchiveWriter.$r8$lambda$usFLzXFoiCtGBD1WgvghnosfRl4(this.f$0, (Path) r1));
    }
}
