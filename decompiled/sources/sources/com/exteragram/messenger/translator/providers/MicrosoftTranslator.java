package com.exteragram.messenger.translator.providers;

import android.util.Base64;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.core.HttpTranslator;
import com.exteragram.messenger.translator.core.ProviderLimits;
import com.exteragram.messenger.translator.core.ProviderResponse;
import com.exteragram.messenger.translator.core.TranslationError;
import j$.net.URLEncoder;
import j$.util.DesugarTimeZone;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0001!B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/exteragram/messenger/translator/providers/MicrosoftTranslator;", "Lcom/exteragram/messenger/translator/core/HttpTranslator;", "<init>", "()V", "displayName", _UrlKt.FRAGMENT_ENCODE_SET, "getDisplayName", "()Ljava/lang/String;", "supportedLanguages", _UrlKt.FRAGMENT_ENCODE_SET, "getSupportedLanguages", "()Ljava/util/Set;", "limits", "Lcom/exteragram/messenger/translator/core/ProviderLimits;", "getLimits", "()Lcom/exteragram/messenger/translator/core/ProviderLimits;", "buildRequest", "Lokhttp3/Request;", "texts", _UrlKt.FRAGMENT_ENCODE_SET, "fromLang", "toLang", "signature", "path", "parseResponse", "Lcom/exteragram/messenger/translator/core/ProviderResponse;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "expected", _UrlKt.FRAGMENT_ENCODE_SET, "failure", "Lcom/exteragram/messenger/translator/core/TranslationError;", "body", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MicrosoftTranslator extends HttpTranslator {
    public static final Companion Companion = null;
    private static final TimeZone GMT = null;
    private static final Lazy<MicrosoftTranslator> shared$delegate = null;
    private final String displayName;
    private final ProviderLimits limits;
    private final Set<String> supportedLanguages;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u000eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/exteragram/messenger/translator/providers/MicrosoftTranslator$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SIGNING_KEY", _UrlKt.FRAGMENT_ENCODE_SET, "SCHEME", "ENDPOINT_PATH", "SIGNATURE_SCHEME", "HMAC_ALGORITHM", "USER_AGENT", "GMT", "Ljava/util/TimeZone;", "shared", "Lcom/exteragram/messenger/translator/providers/MicrosoftTranslator;", "getShared", "()Lcom/exteragram/messenger/translator/providers/MicrosoftTranslator;", "shared$delegate", "Lkotlin/Lazy;", "getInstance", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final MicrosoftTranslator getShared() {
            return (MicrosoftTranslator) MicrosoftTranslator.access$getShared$delegate$cp().getValue();
        }

        @JvmStatic
        public final MicrosoftTranslator getInstance() {
            return getShared();
        }
    }

    public static MicrosoftTranslator $r8$lambda$0dLF5QmlPQKXkZb52oac7kc5HWc() {
        return new MicrosoftTranslator();
    }

    static {
        Companion = new Companion(null);
        GMT = DesugarTimeZone.getTimeZone("GMT");
        shared$delegate = LazyKt.lazy(new MicrosoftTranslator$$ExternalSyntheticLambda0());
    }

    private MicrosoftTranslator() {
        this.displayName = "Microsoft";
        this.supportedLanguages = SetsKt.emptySet();
        this.limits = ProviderLimits.MICROSOFT;
    }

    public static final /* synthetic */ Lazy access$getShared$delegate$cp() {
        return shared$delegate;
    }

    private final TranslationError failure(Response r3, String r4) {
        JSONObject r42 = new JSONObject(r4).optJSONObject("error");
        if (r42 == null) goto L5;
        int r43 = r42.optInt("code");
    L7:
        if (r3.code() != 429) goto L11;
        return new TranslationError.RateLimited(retryAfterMs(r3));
    L11:
        if ((r43 / 1000) != 401) goto L14;
        FileLog.e("translator: Microsoft rejected the signature, invalid credentials");
        return TranslationError.Fatal.INSTANCE;
    L14:
        int r2 = r3.code();
        if (500 > r2) goto L21;
        if (r2 >= 600) goto L21;
        return TranslationError.Transient.INSTANCE;
    L21:
        return TranslationError.Fatal.INSTANCE;
    L5:
        r43 = 0;
        goto L7
    }

    private final String signature(String r7) {
        String r6 = StringsKt.replace$default(UUID.randomUUID().toString(), "-", _UrlKt.FRAGMENT_ENCODE_SET, false, 4, null);
        Locale r1 = Locale.US;
        SimpleDateFormat r0 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", r1);
        TimeZone r2 = GMT;
        r0.setTimeZone(r2);
        String r02 = r0.format(new Date(Calendar.getInstance(r2).getTimeInMillis())).toLowerCase(r1) + "GMT";
        String r72 = ("MSTranslatorAndroidApp" + URLEncoder.encode(r7, "UTF-8") + r02 + r6).toLowerCase(r1);
        Mac r22 = Mac.getInstance("HmacSHA256");
        r22.init(new SecretKeySpec(Base64.decode("oik6PdDdMnOXemTbwvMn9de/h9lFnfBaCWbGMMZqqoSaQaqUOqjVGm5NqsmjcBI1x+sS9ugjB55HEJWRiFXYFw", 0), "HmacSHA256"));
        return "MSTranslatorAndroidApp::" + Base64.encodeToString(r22.doFinal(r72.getBytes(StandardCharsets.UTF_8)), 2) + "::" + r02 + "::" + r6;
    }

    @Override // com.exteragram.messenger.translator.core.HttpTranslator
    public Request buildRequest(List<String> r8, String r9, String r10) {
        if (StringsKt.equals(r9, "auto", true) == false) goto L5;
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r92 = "api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=" + r9 + "&to=" + r10;
        JSONArray r102 = new JSONArray();
        Iterator<String> r82 = r8.iterator();
    L7:
        if (r82.hasNext() == false) goto L10;
        r102.put(new JSONObject().put("Text", r82.next()));
        goto L7
    L10:
        return new Request.Builder().url("https://".concat(r92)).header("X-Mt-Signature", signature(r92)).header("User-Agent", "okhttp/4.5.0").post(RequestBody.Companion.create$default(RequestBody.Companion, r102.toString().getBytes(StandardCharsets.UTF_8), MediaType.Companion.parse("application/json; charset=UTF-8"), 0, 0, 6, null)).build();
    }

    @Override // com.exteragram.messenger.translator.core.BaseTranslator
    public String getDisplayName() {
        return this.displayName;
    }

    @Override // com.exteragram.messenger.translator.core.HttpTranslator
    public ProviderLimits getLimits() {
        return this.limits;
    }

    @Override // com.exteragram.messenger.translator.core.BaseTranslator
    public Set<String> getSupportedLanguages() {
        return this.supportedLanguages;
    }

    @Override // com.exteragram.messenger.translator.core.HttpTranslator
    public ProviderResponse parseResponse(Response r6, int r7) {
        String r0 = r6.body().string();
        if (r0.length() != 0) goto L11;
        if (r6.isSuccessful() == false) goto L7;
        TranslationError r5 = TranslationError.Transient.INSTANCE;
    L9:
        return new ProviderResponse.Failure(r5);
    L7:
        r5 = httpError(r6);
        goto L9
    L11:
        if (StringsKt.startsWith$default(r0, "[", false, 2, null) == false) goto L13;
        JSONArray r52 = new JSONArray(r0);
        if (r52.length() != r7) goto L17;
        int r62 = r52.length();
        ArrayList r72 = new ArrayList(r62);
        int r02 = 0;
    L19:
        if (r02 >= r62) goto L22;
        r72.add(r52.getJSONObject(r02).getJSONArray("translations").getJSONObject(0).optString("text"));
        r02 = r02 + 1;
        goto L19
    L22:
        return new ProviderResponse.Success(r72);
    L17:
        return new ProviderResponse.Failure(TranslationError.Transient.INSTANCE);
    L13:
        return new ProviderResponse.Failure(failure(r6, r0));
    }
}
