package com.exteragram.messenger.translator.core;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.translator.core.TranslationError;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.sun.jna.Callback;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b&\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H&J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0004J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u0016H\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006#"}, d2 = {"Lcom/exteragram/messenger/translator/core/HttpTranslator;", "Lcom/exteragram/messenger/translator/core/BaseTranslator;", "<init>", "()V", "limits", "Lcom/exteragram/messenger/translator/core/ProviderLimits;", "getLimits", "()Lcom/exteragram/messenger/translator/core/ProviderLimits;", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "buildRequest", "Lokhttp3/Request;", "texts", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "fromLang", "toLang", "parseResponse", "Lcom/exteragram/messenger/translator/core/ProviderResponse;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "expected", _UrlKt.FRAGMENT_ENCODE_SET, "translate", _UrlKt.FRAGMENT_ENCODE_SET, "text", Callback.METHOD_NAME, "Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;", "httpError", "Lcom/exteragram/messenger/translator/core/TranslationError;", "retryAfterMs", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class HttpTranslator extends BaseTranslator {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/translator/core/HttpTranslator$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MANUAL_TAG", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static void $r8$lambda$jSPAM0EKvxpcBjvYueCfZMWI2q4(TranslatorUtils.TranslateCallback r1, List r2, TranslationError r3) {
        if (r2 == null) goto L4;
        String r22 = (String) CollectionsKt.firstOrNull(r2);
    L5:
        if (r22 != null) goto L7;
    L11:
        if (r3 != null) goto L13;
        r3 = TranslationError.Fatal.INSTANCE;
    L13:
        r1.onFailed(r3);
        return;
    L7:
        if (r22.length() == 0) goto L11;
        r1.onSuccess(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    static {
        Companion = new Companion(null);
    }

    public HttpTranslator() {
    }

    public abstract Request buildRequest(List<String> r1, String r2, String r3);

    public OkHttpClient getClient() {
        return ExteraHttpClient.INSTANCE.getClient();
    }

    public abstract ProviderLimits getLimits();

    public final TranslationError httpError(Response r3) {
        if (r3.code() == 429) goto L5;
        int r2 = r3.code();
        if (500 > r2) goto L13;
        if (r2 >= 600) goto L13;
        return TranslationError.Transient.INSTANCE;
    L13:
        return TranslationError.Fatal.INSTANCE;
    L5:
        return new TranslationError.RateLimited(retryAfterMs(r3));
    }

    public abstract ProviderResponse parseResponse(Response r1, int r2);

    public final long retryAfterMs(Response r3) {
        String r2 = Response.header$default(r3, "Retry-After", null, 2, null);
        long r0 = 0;
        if (r2 == null) goto L12;
        String r22 = StringsKt.trim(r2).toString();
        if (r22 == null) goto L13;
        Long r23 = StringsKt.toLongOrNull(r22);
        if (r23 == null) goto L11;
        r0 = r23.longValue();
    L11:
        return r0 * 1000;
    L13:
        return 0;
    L12:
        return 0;
    }

    @Override // com.exteragram.messenger.translator.core.BaseTranslator
    public final void translate(String r9, String r10, String r11, TranslatorUtils.TranslateCallback r12) {
        if (StringsKt.isBlank(r9) == false) goto L6;
        r12.onFailed(TranslationError.Fatal.INSTANCE);
        return;
    L6:
        TranslationDispatcher.enqueue(this, 0, CollectionsKt.listOf(r9), r10, r11, true, new HttpTranslator$$ExternalSyntheticLambda0(r12));
    }
}
