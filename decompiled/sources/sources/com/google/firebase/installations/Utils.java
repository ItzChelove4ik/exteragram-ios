package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.time.Clock;
import com.google.firebase.installations.time.SystemClock;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class Utils {
    private static final Pattern API_KEY_FORMAT = null;
    public static final long AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS = 0;
    private static Utils singleton;
    private final Clock clock;

    static {
        AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS = 3600;
        API_KEY_FORMAT = Pattern.compile("\\AA[\\w-]{38}\\z");
    }

    private Utils(Clock r1) {
        this.clock = r1;
    }

    public static Utils getInstance() {
        return getInstance(SystemClock.getInstance());
    }

    public static Utils getInstance(Clock r1) {
        if (singleton != null) goto L6;
        singleton = new Utils(r1);
    L6:
        return singleton;
    }

    public static boolean isValidApiKeyFormat(String r1) {
        return API_KEY_FORMAT.matcher(r1).matches();
    }

    public static boolean isValidAppIdFormat(String r1) {
        return r1.contains(":");
    }

    public long currentTimeInMillis() {
        return this.clock.currentTimeMillis();
    }

    public long currentTimeInSecs() {
        return currentTimeInMillis() / 1000;
    }

    public long getRandomDelayForSyncPrevention() {
        return (long) (Math.random() * 1000.0d);
    }

    public boolean isAuthTokenExpired(PersistedInstallationEntry r7) {
        if (TextUtils.isEmpty(r7.getAuthToken()) == false) goto L6;
        return true;
    L6:
        if ((r7.getTokenCreationEpochInSecs() + r7.getExpiresInSecs()) >= (currentTimeInSecs() + AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS)) goto L8;
        return true;
    L8:
        return false;
    }
}
