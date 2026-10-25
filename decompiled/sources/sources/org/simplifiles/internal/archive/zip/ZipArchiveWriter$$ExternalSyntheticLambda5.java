package org.simplifiles.internal.archive.zip;

import j$.nio.file.Path;
import kotlin.jvm.functions.Function1;
import org.simplifiles.archive.ArchiveSaveOptions;

/* loaded from: classes5.dex */
public final /* synthetic */ class ZipArchiveWriter$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ ArchiveSaveOptions f$0;
    public final /* synthetic */ Path f$1;

    public /* synthetic */ ZipArchiveWriter$$ExternalSyntheticLambda5(ArchiveSaveOptions r1, Path r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return Boolean.valueOf(ZipArchiveWriter.$r8$lambda$BnsDqbdRbvJNOdYAMqyhqS2Yfk4(this.f$0, this.f$1, (Path) r2));
    }
}
