package com.google.common.io;

import com.google.common.base.Preconditions;
import com.google.common.graph.SuccessorsFunction;
import java.io.File;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class Files {
    private static final SuccessorsFunction<File> FILE_TREE = null;

    static {
        FILE_TREE = new Files$$ExternalSyntheticLambda0();
    }

    public static void createParentDirs(File r1) {
        Preconditions.checkNotNull(r1);
        File r0 = r1.getCanonicalFile().getParentFile();
        if (r0 == null) goto L10;
        r0.mkdirs();
        if (r0.isDirectory() == false) goto L8;
        return;
    L8:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("Unable to create parent directories of ", r1);
        return;
    }
}
