package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
public final /* synthetic */ class FileStore$$ExternalSyntheticLambda0 implements FilenameFilter {
    public final /* synthetic */ String f$0;

    public /* synthetic */ FileStore$$ExternalSyntheticLambda0(String r1) {
        this.f$0 = r1;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File r1, String r2) {
        return FileStore.$r8$lambda$HmP8MQOXpRxpTLnqACFXr9djRFI(this.f$0, r1, r2);
    }
}
