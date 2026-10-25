package com.google.android.recaptcha;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lcom/google/android/recaptcha/RecaptchaErrorCode;", "errorCode", _UrlKt.FRAGMENT_ENCODE_SET, "errorMessage", "<init>", "(Lcom/google/android/recaptcha/RecaptchaErrorCode;Ljava/lang/String;)V", "Lcom/google/android/recaptcha/RecaptchaErrorCode;", "getErrorCode", "()Lcom/google/android/recaptcha/RecaptchaErrorCode;", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RecaptchaException extends Exception {
    private final RecaptchaErrorCode errorCode;
    private final String errorMessage;

    public RecaptchaException(RecaptchaErrorCode r1, String r2) {
        super(r2);
        this.errorCode = r1;
        this.errorMessage = r2;
    }

    public /* synthetic */ RecaptchaException(RecaptchaErrorCode r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = r1.getErrorMessage();
    L5:
        this(r1, r2);
    }

    public final RecaptchaErrorCode getErrorCode() {
        return this.errorCode;
    }
}
