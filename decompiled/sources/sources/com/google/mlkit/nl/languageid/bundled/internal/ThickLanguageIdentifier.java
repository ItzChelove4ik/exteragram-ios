package com.google.mlkit.nl.languageid.bundled.internal;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.nl.languageid.IdentifiedLanguage;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierDelegate;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ThickLanguageIdentifier implements LanguageIdentifierDelegate {
    private static boolean zba;
    private final Context zbb;
    private long zbc;

    public ThickLanguageIdentifier(Context r1, LanguageIdentificationOptions r2) {
        this.zbb = r1;
    }

    private native void nativeDestroy(long r1);

    private native IdentifiedLanguage[] nativeIdentifyPossibleLanguages(long r1, byte[] r3, float r4);

    private native long nativeInitFromBuffer(MappedByteBuffer r1, long r2);

    public static synchronized void zba() {
        monitor-enter(ThickLanguageIdentifier.class);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (zba == false) goto L21;
        monitor-exit(ThickLanguageIdentifier.class);
        return;
    L21:
        System.loadLibrary("language_id_l2c_jni");     // Catch: Throwable -> L13 UnsatisfiedLinkError -> L15
    L10:
        zba = true;     // Catch: Throwable -> L13
        monitor-exit(ThickLanguageIdentifier.class);
        return;
    L15:
        e = move-exception;
        throw new MlKitException("Couldn't load language identification library.", 13, e);     // Catch: Throwable -> L13
    }

    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierDelegate
    public final List identifyPossibleLanguages(String r5, float r6) {
        int r1 = 0;
        if (this.zbc == 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0);
        IdentifiedLanguage[] r4 = nativeIdentifyPossibleLanguages(this.zbc, r5.getBytes(StandardCharsets.UTF_8), r6);
        ArrayList r52 = new ArrayList();
        int r62 = r4.length;
    L7:
        if (r1 >= r62) goto L9;
        IdentifiedLanguage r02 = r4[r1];
        r52.add(new IdentifiedLanguage(r02.getLanguageTag(), r02.getConfidence()));
        r1 = r1 + 1;
        goto L7
    L9:
        return r52;
    L5:
        r0 = false;
        goto L6
    }

    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierDelegate
    public final void init() {
        if (this.zbc != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0);
        zba();
        AssetFileDescriptor r4 = this.zbb.getAssets().openFd("tflite_langid.tflite.jpg");     // Catch: IOException -> L15
        FileChannel r6 = new FileInputStream(r4.getFileDescriptor()).getChannel();     // Catch: Throwable -> L17
        long r7 = nativeInitFromBuffer(r6.map(FileChannel.MapMode.READ_ONLY, r4.getStartOffset(), r4.getDeclaredLength()), r4.getDeclaredLength());     // Catch: Throwable -> L21
        this.zbc = r7;     // Catch: Throwable -> L21
        if (r7 == 0) goto L20;
        r6.close();     // Catch: Throwable -> L17
        r4.close();     // Catch: IOException -> L15
        return;
    L20:
        throw new MlKitException("Couldn't load language identification model", 13);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        if (r6 == null) goto L45;
        r6.close();     // Catch: Throwable -> L26
    L46:
        throw th;     // Catch: Throwable -> L17
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L17
        throw th;     // Catch: Throwable -> L17
    L45:
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        if (r4 == null) goto L47;
        r4.close();     // Catch: Throwable -> L32
    L48:
        throw th;     // Catch: IOException -> L15
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L15
        throw th;     // Catch: IOException -> L15
    L47:
        throw th;     // Catch: IOException -> L15
    L15:
        e = move-exception;
        throw new MlKitException("Couldn't open language identification model file", 13, e);
    L5:
        r0 = false;
        goto L6
    }

    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierDelegate
    public final void release() {
        long r0 = this.zbc;
        if (r0 != 0) goto L5;
        return;
    L5:
        nativeDestroy(r0);
        this.zbc = 0;
    }
}
