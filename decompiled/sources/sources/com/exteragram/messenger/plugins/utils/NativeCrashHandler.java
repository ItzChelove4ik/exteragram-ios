package com.exteragram.messenger.plugins.utils;

import android.content.SharedPreferences;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0087 J\b\u0010\t\u001a\u00020\u0007H\u0007J\b\u0010\n\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/NativeCrashHandler;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CRASH_FLAG_FILENAME", _UrlKt.FRAGMENT_ENCODE_SET, "init", _UrlKt.FRAGMENT_ENCODE_SET, "flagPath", "checkAndHandleNativeCrash", "getCrashFlagPath", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNativeCrashHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NativeCrashHandler.kt\ncom/exteragram/messenger/plugins/utils/NativeCrashHandler\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,46:1\n41#2,12:47\n*S KotlinDebug\n*F\n+ 1 NativeCrashHandler.kt\ncom/exteragram/messenger/plugins/utils/NativeCrashHandler\n*L\n37#1:47,12\n*E\n"})
/* loaded from: classes.dex */
public final class NativeCrashHandler {
    private static final String CRASH_FLAG_FILENAME = "native_crash.flag";
    public static final NativeCrashHandler INSTANCE = null;

    static {
        INSTANCE = new NativeCrashHandler();
    }

    private NativeCrashHandler() {
    }

    @JvmStatic
    public static final void checkAndHandleNativeCrash() {
        File r0 = new File(ApplicationLoader.getFilesDirFixed(), CRASH_FLAG_FILENAME);
        if (r0.exists() == false) goto L6;
        FileLog.e("Native crash detected. Enabling safe mode for plugins.");
        SharedPreferences.Editor r1 = ApplicationLoader.applicationContext.getSharedPreferences("plugin_settings", 0).edit();
        r1.putBoolean("had_crash", true);
        r1.apply();
        r0.delete();
        return;
    }

    @JvmStatic
    public static final String getCrashFlagPath() {
        return new File(ApplicationLoader.getFilesDirFixed(), CRASH_FLAG_FILENAME).getAbsolutePath();
    }

    @JvmStatic
    public static final native void init(String r0);
}
