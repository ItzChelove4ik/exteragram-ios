package org.telegram.messenger;

import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.nl.languageid.LanguageIdentification;

/* loaded from: classes5.dex */
public class LanguageDetector {

    public interface ExceptionCallback {
        void run(Exception r1);
    }

    public interface StringCallback {
        void run(String r1);
    }

    public static /* synthetic */ void $r8$lambda$A9hSaUSYsiHXwXl3Wg4NimNwdkM(StringCallback r0, String r1) {
        if (r0 == null) goto L5;
        r0.run(r1);
        return;
    }

    public static /* synthetic */ void $r8$lambda$AoS_Dilvh2Sr5L6lsNQg9lI_0nY(ExceptionCallback r0, Exception r1) {
        if (r0 == null) goto L5;
        r0.run(r1);
        return;
    }

    public LanguageDetector() {
    }

    public static void detectLanguage(String r1, StringCallback r2, ExceptionCallback r3) {
        detectLanguage(r1, r2, r3, false);
    }

    public static void detectLanguage(String r2, StringCallback r3, ExceptionCallback r4, boolean r5) {
        if (r5 == true) goto L24;
    L10:
        LanguageIdentification.getClient().identifyLanguage(r2).addOnSuccessListener(new LanguageDetector$$ExternalSyntheticLambda0(r3)).addOnFailureListener(new LanguageDetector$$ExternalSyntheticLambda1(r4));     // Catch: Throwable -> L4 Exception -> L6 IllegalStateException -> L8
        return;
    L8:
        e = move-exception;
        if (r5 == true) goto L20;
        detectLanguage(r2, r3, r4, true);
        return;
    L20:
        if (r4 == null) goto L22;
        r4.run(e);
    L22:
        FileLog.e(e);
        return;
    L6:
        e = move-exception;
        if (r4 == null) goto L17;
        r4.run(e);
    L17:
        FileLog.e(e);
        return;
    L4:
        th = move-exception;
        if (r4 == null) goto L14;
        r4.run(null);
    L14:
        FileLog.e(th);
        return;
    L24:
        MlKitContext.zza(ApplicationLoader.applicationContext);     // Catch: Throwable -> L4 Exception -> L6 IllegalStateException -> L8
        goto L10
    }

    public static boolean hasSupport() {
        return true;
    }
}
