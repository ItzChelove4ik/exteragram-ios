package androidx.core.app;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.internal.url._UrlKt;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes3.dex */
public abstract class AppLocalesStorageHelper {
    private static final Object sAppLocaleStorageSync = null;

    static {
        sAppLocaleStorageSync = new Object();
    }

    public static void persistLocales(Context r5, String r6) {
        Object r0 = sAppLocaleStorageSync;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (r6.equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L44;
        r5.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L44:
        FileOutputStream r52 = r5.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);     // Catch: Throwable -> L9 FileNotFoundException -> L29
        XmlSerializer r1 = Xml.newSerializer();     // Catch: Throwable -> L9
        r1.setOutput(r52, null);     // Catch: Throwable -> L18 Exception -> L20
        r1.startDocument("UTF-8", Boolean.TRUE);     // Catch: Throwable -> L18 Exception -> L20
        r1.startTag(null, "locales");     // Catch: Throwable -> L18 Exception -> L20
        r1.attribute(null, "application_locales", r6);     // Catch: Throwable -> L18 Exception -> L20
        r1.endTag(null, "locales");     // Catch: Throwable -> L18 Exception -> L20
        r1.endDocument();     // Catch: Throwable -> L18 Exception -> L20
        if (r52 != null) goto L42;
    L24:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L42:
        r52.close();     // Catch: Throwable -> L9 IOException -> L34
        goto L24
    L18:
        th = move-exception;
        if (r52 != null) goto L40;
    L28:
        throw th;     // Catch: Throwable -> L9
    L40:
        r52.close();     // Catch: Throwable -> L9 IOException -> L35
    L20:
        e = move-exception;
        Log.w("AppLocalesStorageHelper", "Storing App Locales : Failed to persist app-locales in storage ", e);     // Catch: Throwable -> L18
        if (r52 == null) goto L24;
    L29:
        Log.w("AppLocalesStorageHelper", String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", new Object[]{"androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"}));     // Catch: Throwable -> L9
    }

    public static String readLocales(Context r8) {
        Object r0 = sAppLocaleStorageSync;
        monitor-enter(r0);
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        FileInputStream r2 = r8.openFileInput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");     // Catch: Throwable -> L26 FileNotFoundException -> L40
        XmlPullParser r3 = Xml.newPullParser();     // Catch: Throwable -> L14 Throwable -> L28
        r3.setInput(r2, "UTF-8");     // Catch: Throwable -> L14 Throwable -> L28
        int r4 = r3.getDepth();     // Catch: Throwable -> L14 Throwable -> L28
    L7:
        int r5 = r3.next();     // Catch: Throwable -> L14 Throwable -> L28
        if (r5 == 1) goto L23;
        if (r5 == 3) goto L12;
    L16:
        if (r5 == 3) goto L7;
        if (r5 == 4) goto L7;
        if (r3.getName().equals("locales") == false) goto L7;
        r1 = r3.getAttributeValue(null, "application_locales");     // Catch: Throwable -> L14 Throwable -> L28
    L12:
        if (r3.getDepth() > r4) goto L16;
    L23:
        if (r2 == null) goto L32;
    L48:
        r2.close();     // Catch: Throwable -> L26 IOException -> L44
    L32:
        if (r1.isEmpty() == false) goto L35;
        r8.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");     // Catch: Throwable -> L26
    L35:
        monitor-exit(r0);     // Catch: Throwable -> L26
        return r1;
    L14:
        th = move-exception;
        if (r2 != null) goto L46;
    L39:
        throw th;     // Catch: Throwable -> L26
    L46:
        r2.close();     // Catch: Throwable -> L26 IOException -> L45
    L28:
        Log.w("AppLocalesStorageHelper", "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");     // Catch: Throwable -> L14
        if (r2 == null) goto L32;
    L26:
        th = move-exception;
        throw th;
    L41:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
