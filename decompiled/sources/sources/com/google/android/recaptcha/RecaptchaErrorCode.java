package com.google.android.recaptcha;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaErrorCode;", _UrlKt.FRAGMENT_ENCODE_SET, "errorCode", _UrlKt.FRAGMENT_ENCODE_SET, "errorMessage", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getErrorCode", "()I", "getErrorMessage", "()Ljava/lang/String;", "UNKNOWN_ERROR", "NETWORK_ERROR", "INVALID_SITEKEY", "INVALID_KEYTYPE", "INVALID_PACKAGE_NAME", "INVALID_ACTION", "INVALID_TIMEOUT", "NO_NETWORK_FOUND", "INTERNAL_ERROR", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum RecaptchaErrorCode extends Enum<RecaptchaErrorCode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ RecaptchaErrorCode[] $VALUES = null;
    public static final RecaptchaErrorCode INTERNAL_ERROR = null;
    public static final RecaptchaErrorCode INVALID_ACTION = null;
    public static final RecaptchaErrorCode INVALID_KEYTYPE = null;
    public static final RecaptchaErrorCode INVALID_PACKAGE_NAME = null;
    public static final RecaptchaErrorCode INVALID_SITEKEY = null;
    public static final RecaptchaErrorCode INVALID_TIMEOUT = null;
    public static final RecaptchaErrorCode NETWORK_ERROR = null;
    public static final RecaptchaErrorCode NO_NETWORK_FOUND = null;
    public static final RecaptchaErrorCode UNKNOWN_ERROR = null;
    private final int errorCode;
    private final String errorMessage;

    private static final /* synthetic */ RecaptchaErrorCode[] $values() {
        return new RecaptchaErrorCode[]{UNKNOWN_ERROR, NETWORK_ERROR, INVALID_SITEKEY, INVALID_KEYTYPE, INVALID_PACKAGE_NAME, INVALID_ACTION, INVALID_TIMEOUT, NO_NETWORK_FOUND, INTERNAL_ERROR};
    }

    static {
        UNKNOWN_ERROR = new RecaptchaErrorCode("UNKNOWN_ERROR", 0, 0, "Unknown Error");
        NETWORK_ERROR = new RecaptchaErrorCode("NETWORK_ERROR", 1, 1, "Network Error");
        INVALID_SITEKEY = new RecaptchaErrorCode("INVALID_SITEKEY", 2, 2, "Site key invalid");
        INVALID_KEYTYPE = new RecaptchaErrorCode("INVALID_KEYTYPE", 3, 3, "Key type invalid");
        INVALID_PACKAGE_NAME = new RecaptchaErrorCode("INVALID_PACKAGE_NAME", 4, 4, "Package name not allowed");
        INVALID_ACTION = new RecaptchaErrorCode("INVALID_ACTION", 5, 5, "Invalid action name, may only include alphanumeric characters like [A-Z], [a-z], [0-9], / and _. Do not include user-specific information");
        INVALID_TIMEOUT = new RecaptchaErrorCode("INVALID_TIMEOUT", 6, 6, "Invalid timeout, minimum value is 5_000L milliseconds");
        NO_NETWORK_FOUND = new RecaptchaErrorCode("NO_NETWORK_FOUND", 7, 7, "No network found on device");
        INTERNAL_ERROR = new RecaptchaErrorCode("INTERNAL_ERROR", 8, 100, "Internal Error");
        RecaptchaErrorCode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    RecaptchaErrorCode(String r1, int r2, int r3, String r4) {
        this.errorCode = r3;
        this.errorMessage = r4;
    }

    public static RecaptchaErrorCode valueOf(String r1) {
        return (RecaptchaErrorCode) Enum.valueOf(RecaptchaErrorCode.class, r1);
    }

    public static RecaptchaErrorCode[] values() {
        return (RecaptchaErrorCode[]) $VALUES.clone();
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }
}
