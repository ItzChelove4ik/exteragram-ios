package org.telegram.messenger;

import android.os.Build;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class BuildVars {
    public static int BUILD_VERSION = 0;
    public static String BUILD_VERSION_STRING = null;
    public static boolean CHECK_UPDATES = false;
    public static boolean DEBUG_PRIVATE_VERSION = false;
    public static boolean DEBUG_VERSION = false;
    private static final String EXTERA_APP_HASH = null;
    private static final int EXTERA_APP_ID = 0;
    public static String GITHUB_APP_URL;
    public static String GOOGLE_AUTH_CLIENT_ID;
    public static boolean IS_BILLING_UNAVAILABLE;
    public static boolean IS_LITE_VERSION;
    public static boolean LOGS_ENABLED;
    public static boolean NO_SCOPED_STORAGE;
    public static boolean PM_BUILD;
    public static String RELEASES_URL;
    public static String SAFETYNET_KEY;
    public static boolean SUPPORTS_PASSKEYS;
    public static boolean USE_CLOUD_STRINGS;

    static {
        LOGS_ENABLED = ExteraConfig.getLogging();
        boolean r0 = false;
        DEBUG_PRIVATE_VERSION = false;
        USE_CLOUD_STRINGS = true;
        CHECK_UPDATES = false;
        int r2 = Build.VERSION.SDK_INT;
        if (r2 > 29) goto L5;
        boolean r3 = true;
    L6:
        NO_SCOPED_STORAGE = r3;
        PM_BUILD = false;
        IS_LITE_VERSION = false;
        SAFETYNET_KEY = _UrlKt.FRAGMENT_ENCODE_SET;
        GITHUB_APP_URL = "https://github.com/exteraSquad/exteraGram/releases/latest";
        RELEASES_URL = "https://t.me/exteraReleases";
        GOOGLE_AUTH_CLIENT_ID = "760348033671-81kmi3pi84p11ub8hp9a1funsv0rn2p9.apps.googleusercontent.com";
        IS_BILLING_UNAVAILABLE = false;
        if (r2 < 34) goto L9;
        r0 = true;
    L9:
        SUPPORTS_PASSKEYS = r0;
        BUILD_VERSION = BuildConfig.VERSION_CODE;
        BUILD_VERSION_STRING = "12.10.6";
        EXTERA_APP_ID = BuildConfig.APP_ID;
        EXTERA_APP_HASH = BuildConfig.APP_HASH;
        return;
    L5:
        r3 = false;
        goto L6
    }

    public BuildVars() {
    }

    public static String getExteraAppHash() {
        return EXTERA_APP_HASH;
    }

    public static int getExteraAppId() {
        return EXTERA_APP_ID;
    }

    public static String getSmsHash() {
        if (DEBUG_VERSION == false) goto L6;
        return "O2P2z+/jBpJ";
    L6:
        return "oLeq9AcOZkT";
    }

    private static boolean hasDirectCurrency() {
        return false;
    }

    public static boolean isBetaApp() {
        return DEBUG_VERSION;
    }

    public static boolean useInvoiceBilling() {
        return true;
    }
}
