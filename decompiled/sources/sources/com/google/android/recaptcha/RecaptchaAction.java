package com.google.android.recaptcha;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaAction;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "action", "<init>", "(Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getAction", "Companion", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RecaptchaAction {
    public static final Companion Companion = null;

    @JvmField
    public static final RecaptchaAction LOGIN = null;

    @JvmField
    public static final RecaptchaAction SIGNUP = null;
    private final String action;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaAction$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "LOGIN", "Lcom/google/android/recaptcha/RecaptchaAction;", "SIGNUP", PluginsConstants.Settings.TYPE_CUSTOM, "customAction", _UrlKt.FRAGMENT_ENCODE_SET, "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final RecaptchaAction custom(String r2) {
            return new RecaptchaAction(r2, null);
        }
    }

    static {
        Companion = new Companion(null);
        LOGIN = new RecaptchaAction("login");
        SIGNUP = new RecaptchaAction("signup");
    }

    private RecaptchaAction(String r1) {
        this.action = r1;
    }

    public /* synthetic */ RecaptchaAction(String r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    @JvmStatic
    public static final RecaptchaAction custom(String r1) {
        return Companion.custom(r1);
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof RecaptchaAction) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(this.action, ((RecaptchaAction) r4).action) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final String getAction() {
        return this.action;
    }

    public int hashCode() {
        return this.action.hashCode();
    }

    public String toString() {
        String r2 = this.action;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 24);
        r1.append("RecaptchaAction(action=");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }
}
