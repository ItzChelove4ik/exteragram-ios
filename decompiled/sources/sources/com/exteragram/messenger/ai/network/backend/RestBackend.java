package com.exteragram.messenger.ai.network.backend;

import android.text.TextUtils;
import android.util.Base64;
import com.android.dx.io.Opcodes;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.ai.network.ProxyDns;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.google.android.gms.cast.MediaError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SharedConfig;

/* loaded from: classes4.dex */
public class RestBackend implements Backend {
    private static final String[] ERROR_MESSAGE_KEYS = null;
    private static final int STREAM_SYMBOLS_LIMIT = 0;
    private final OkHttpClient httpClient;

    public static final class Endpoint extends RecordTag {
        private final boolean responsesApi;
        private final String url;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof Endpoint) == false) goto L10;
            Endpoint r32 = (Endpoint) r3;
            if (this.responsesApi == r32.responsesApi) goto L7;
            return false;
        L7:
            if (Objects.equals(this.url, r32.url) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.url, Boolean.valueOf(this.responsesApi)};
        }

        private Endpoint(String r1, boolean r2) {
            this.url = r1;
            this.responsesApi = r2;
        }

        public /* synthetic */ Endpoint(String r1, boolean r2, RestBackendIA r3) {
            this(r1, r2);
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return RestBackend$Endpoint$$ExternalSyntheticRecord0.m(this.responsesApi, this.url);
        }

        public boolean responsesApi() {
            return this.responsesApi;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), Endpoint.class, "url;responsesApi");
        }

        public String url() {
            return this.url;
        }
    }

    public static class ReasoningContentFilter {
        private boolean inReasoning;
        private String pending;
        private boolean reasoningSignal;

        private ReasoningContentFilter() {
            this.pending = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public /* synthetic */ ReasoningContentFilter(RestBackendIA r1) {
            this();
        }

        private String getCloseTagPrefixSuffix(String r3, int r4) {
            int r2 = Math.min(7, r3.length() - r4);
            String r42 = r3.toLowerCase(Locale.ROOT);
        L3:
            if (r2 <= 0) goto L9;
            if ("</think>".startsWith(r42.substring(r3.length() - r2)) == true) goto L7;
            r2 = r2 - 1;
            goto L3
        L7:
            return r3.substring(r3.length() - r2);
        L9:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        private String getOpenTagPrefixSuffix(String r3, int r4) {
            int r2 = Math.min(6, r3.length() - r4);
            String r42 = r3.toLowerCase(Locale.ROOT);
        L3:
            if (r2 <= 0) goto L9;
            if ("<think>".startsWith(r42.substring(r3.length() - r2)) == true) goto L7;
            r2 = r2 - 1;
            goto L3
        L7:
            return r3.substring(r3.length() - r2);
        L9:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public boolean consumeReasoningSignal() {
            boolean r0 = this.reasoningSignal;
            this.reasoningSignal = false;
            return r0;
        }

        public String filter(String r7) {
            if (TextUtils.isEmpty(r7) == false) goto L6;
            return null;
        L6:
            String r72 = this.pending + r7;
            this.pending = _UrlKt.FRAGMENT_ENCODE_SET;
            StringBuilder r0 = new StringBuilder(r72.length());
            int r2 = 0;
        L8:
            if (r2 >= r72.length()) goto L26;
            String r3 = r72.toLowerCase(Locale.ROOT);
            if (this.inReasoning == false) goto L17;
            this.reasoningSignal = true;
            int r32 = r3.indexOf("</think>", r2);
            if (r32 < 0) goto L13;
            int r33 = r32 + 8;
            this.inReasoning = false;
        L16:
            r2 = r33;
            goto L8
        L13:
            this.pending = getCloseTagPrefixSuffix(r72, r2);
            return r0.toString();
        L17:
            int r34 = r3.indexOf("<think>", r2);
            if (r34 < 0) goto L19;
            r0.append(r72, r2, r34);
            r33 = r34 + 7;
            this.inReasoning = true;
            this.reasoningSignal = true;
            goto L16
        L19:
            this.pending = getOpenTagPrefixSuffix(r72, r2);
            this.reasoningSignal = !r1.isEmpty();
            int r1 = r72.length() - this.pending.length();
            if (r1 <= r2) goto L23;
            r0.append(r72, r2, r1);
        L23:
            return r0.toString();
        L26:
            return r0.toString();
        }

        public String flush() {
            if (this.inReasoning == false) goto L5;
            String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            this.pending = _UrlKt.FRAGMENT_ENCODE_SET;
            return r0;
        L5:
            r0 = this.pending;
            goto L6
        }
    }

    public static final class StreamResponsePart extends RecordTag {
        static final StreamResponsePart DONE = null;
        private final String content;
        private final boolean done;
        private final String error;
        private final boolean hasReasoning;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof StreamResponsePart) == false) goto L14;
            StreamResponsePart r32 = (StreamResponsePart) r3;
            if (this.hasReasoning == r32.hasReasoning) goto L7;
            return false;
        L7:
            if (this.done == r32.done) goto L9;
            return false;
        L9:
            if (Objects.equals(this.content, r32.content) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.error, r32.error) == false) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.content, Boolean.valueOf(this.hasReasoning), Boolean.valueOf(this.done), this.error};
        }

        static {
            DONE = new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, false, true, null);
        }

        public StreamResponsePart(String r3, boolean r4) {
            this(r3, r4, false, null);
        }

        private StreamResponsePart(String r1, boolean r2, boolean r3, String r4) {
            this.content = r1;
            this.hasReasoning = r2;
            this.done = r3;
            this.error = r4;
        }

        public /* synthetic */ StreamResponsePart(String r1, boolean r2, boolean r3, String r4, RestBackendIA r5) {
            this(r1, r2, r3, r4);
        }

        public String content() {
            return this.content;
        }

        public boolean done() {
            return this.done;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public String error() {
            return this.error;
        }

        public boolean hasReasoning() {
            return this.hasReasoning;
        }

        public final int hashCode() {
            return RestBackend$StreamResponsePart$$ExternalSyntheticRecord0.m(this.hasReasoning, this.done, this.content, this.error);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), StreamResponsePart.class, "content;hasReasoning;done;error");
        }
    }

    static {
        if (SharedConfig.getDevicePerformanceClass() < 1) goto L5;
        int r0 = 10;
    L6:
        STREAM_SYMBOLS_LIMIT = r0;
        ERROR_MESSAGE_KEYS = new String[]{"error", "message", "detail"};
        return;
    L5:
        r0 = 20;
        goto L6
    }

    public RestBackend() {
        OkHttpClient.Builder r0 = ExteraHttpClient.INSTANCE.getClient().newBuilder().dns(ProxyDns.INSTANCE);
        TimeUnit r3 = TimeUnit.MINUTES;
        this.httpClient = r0.connectTimeout(1, r3).readTimeout(5, r3).build();
    }

    private void applyReasoningConfig(JSONObject r4, Service r5, String r6, boolean r7) {
        if (r5.isReasoningEnabled() == true) goto L40;
        String r52 = r5.getModel();
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r6 != null) goto L8;
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (r52 == null) goto L13;
        r0 = r52.toLowerCase(Locale.ROOT);
    L13:
        if (r62.contains("openrouter.ai") == false) goto L16;
        r4.put("reasoning", new JSONObject().put("effort", "none"));
    L32:
        String r3 = null;
    L33:
        if (r3 != null) goto L35;
        return;
    L35:
        if (r7 == false) goto L38;
        r4.put("reasoning", new JSONObject().put("effort", r3));
        return;
    L38:
        r4.put("reasoning_effort", r3);
        return;
    L16:
        if (isGeminiService(r62) == false) goto L21;
        if (isGeminiReasoningModel(r0) == false) goto L21;
        r3 = getGeminiReasoningEffort(r0);
    L21:
        if (isOpenAiService(r62) == false) goto L26;
        if (isOpenAiReasoningModel(r0) == false) goto L26;
        r3 = getOpenAiReasoningEffort(r0);
    L26:
        if (isPerplexityService(r62) == false) goto L32;
        if (isGeminiReasoningModel(r0) == false) goto L30;
    L31:
        r3 = "minimal";
        goto L33
    L30:
        if (isOpenAiReasoningModel(r0) == false) goto L32;
    L8:
        r62 = r6.toLowerCase(Locale.ROOT);
        goto L9
    }

    private JSONObject createMessageObject(Message r7, boolean r8) {
        JSONObject r6 = new JSONObject();
        if (r8 == false) goto L5;
        r6.put("type", "message");
    L5:
        r6.put("role", r7.role());
        if (r7.getImageData() != null) goto L8;
    L22:
        r6.put("content", r7.content());
        return r6;
    L8:
        if (TextUtils.isEmpty(r7.getMimeType()) == true) goto L22;
        JSONArray r1 = new JSONArray();
        if (TextUtils.isEmpty(r7.content()) == true) goto L16;
        JSONObject r3 = new JSONObject();
        if (r8 == false) goto L14;
        String r5 = "input_text";
    L15:
        r1.put(r3.put("type", r5).put("text", r7.content()));
        goto L16
    L14:
        r5 = "text";
    L16:
        String r72 = "data:" + r7.getMimeType() + ";base64," + Base64.encodeToString(r7.getImageData(), 2);
        if (r8 == false) goto L19;
        r1.put(new JSONObject().put("type", "input_image").put("image_url", r72));
    L20:
        r6.put("content", r1);
        return r6;
    L19:
        r1.put(new JSONObject().put("type", "image_url").put("image_url", new JSONObject().put("url", r72)));
        goto L20
    }

    private Request createRequest(BackendRequest r9, Endpoint r10) {
        Service r0 = r9.service();
        boolean r1 = r10.responsesApi();
        JSONObject r2 = new JSONObject();
        JSONArray r3 = new JSONArray();
        Role r4 = r9.role();     // Catch: Exception -> L31
        if (r4 != null) goto L6;
    L10:
        Iterator<Message> r42 = r9.history().iterator();     // Catch: Exception -> L31
    L12:
        if (r42.hasNext() == false) goto L14;
        r3.put(createMessageObject(r42.next(), r1));     // Catch: Exception -> L31
        goto L12
    L14:
        r3.put(createMessageObject(r9.currentMessage(), r1));     // Catch: Exception -> L31
        r2.put("model", r0.getModel());     // Catch: Exception -> L31
        if (r1 == false) goto L17;
        String r43 = PluginsConstants.Settings.TYPE_INPUT;
    L18:
        r2.put(r43, r3);     // Catch: Exception -> L31
        r2.put("stream", r9.stream());     // Catch: Exception -> L31
        r2.put("temperature", r9.temperature());     // Catch: Exception -> L31
        applyReasoningConfig(r2, r0, r0.getUrl(), r1);     // Catch: Exception -> L31
        if (r1 == false) goto L21;
        String r8 = "max_output_tokens";
    L22:
        r2.put(r8, r9.maxOutputTokens());     // Catch: Exception -> L31
        boolean r82 = false;
        if (r1 == false) goto L25;
        r2.put("store", false);     // Catch: Exception -> L31
    L25:
        FileLog.d("AI_REQUEST_URL: " + r10.url());     // Catch: Exception -> L31
        FileLog.d("AI_REQUEST_MODEL: " + r0.getModel());     // Catch: Exception -> L31
        StringBuilder r12 = new StringBuilder();     // Catch: Exception -> L31
        r12.append("AI_REQUEST_MESSAGES: ");     // Catch: Exception -> L31
        r12.append(r3.length());     // Catch: Exception -> L31
        r12.append(", stream=");     // Catch: Exception -> L31
        r12.append(r9.stream());     // Catch: Exception -> L31
        r12.append(", image=");     // Catch: Exception -> L31
        if (r9.currentMessage().getImageData() == null) goto L28;
        r82 = true;
    L28:
        r12.append(r82);     // Catch: Exception -> L31
        FileLog.d(r12.toString());     // Catch: Exception -> L31
        return new Request.Builder().url(r10.url()).addHeader("Content-Type", "application/json").addHeader("Authorization", "Bearer " + r0.getKey()).addHeader("User-Agent", TranslatorUtils.formatUserAgent()).addHeader("HTTP-Referer", "exteragram.app").addHeader("X-Title", "exteraGram").post(RequestBody.create(r2.toString(), MediaType.parse("application/json"))).build();
    L21:
        r8 = "max_tokens";
        goto L22
    L17:
        r43 = "messages";
        goto L18
    L6:
        if (TextUtils.isEmpty(r4.getPrompt()) == true) goto L10;
        if (r1 == false) goto L9;
        r2.put("instructions", r4.getPrompt());     // Catch: Exception -> L31
        goto L10
    L9:
        r3.put(new JSONObject().put("role", "system").put("content", r4.getPrompt()));     // Catch: Exception -> L31
    L31:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private String extractErrorMessage(String r6) {
        if (TextUtils.isEmpty(r6) == false) goto L5;
        return null;
    L5:
        String r5 = r6.trim();
        int r3 = 0;
        if (r5.startsWith("[") == false) goto L25;
        JSONArray r62 = new JSONArray(r5);     // Catch: Exception -> L47
    L11:
        if (r3 >= r62.length()) goto L23;
        JSONObject r52 = r62.optJSONObject(r3);     // Catch: Exception -> L47
        if (r52 != null) goto L15;
        JSONObject r53 = null;
    L16:
        if (r53 != null) goto L18;
        String r54 = null;
    L20:
        if (TextUtils.isEmpty(r54) == false) goto L21;
        r3 = r3 + 1;     // Catch: Exception -> L47
        goto L11
    L21:
        return r54;
    L18:
        r54 = r53.optString("message", null);     // Catch: Exception -> L47
        goto L20
    L15:
        r53 = r52.optJSONObject("error");     // Catch: Exception -> L47
        goto L16
    L23:
        return null;
    L25:
        if (r5.startsWith("{") == false) goto L40;
        JSONObject r63 = new JSONObject(r5);     // Catch: Exception -> L47
        JSONObject r55 = r63.optJSONObject("error");     // Catch: Exception -> L47
        if (r55 != null) goto L29;
        String[] r56 = ERROR_MESSAGE_KEYS;     // Catch: Exception -> L47
        int r1 = r56.length;     // Catch: Exception -> L47
    L31:
        if (r3 >= r1) goto L38;
        Object r2 = r63.opt(r56[r3]);     // Catch: Exception -> L47
        if ((r2 instanceof String) == false) goto L37;
        String r22 = (String) r2;     // Catch: Exception -> L47
        if (r22.isEmpty() == true) goto L37;
        return r22;
    L37:
        r3 = r3 + 1;
        goto L31
    L38:
        return null;
    L29:
        return r55.optString("message", null);
    L40:
        if (r5.startsWith("<") == true) goto L46;
        if (r5.length() > 200) goto L46;
        if (r5.indexOf(10) >= 0) goto L46;
        return r5;
    L46:
        return null;
    }

    private String getGeminiReasoningEffort(String r1) {
        if (r1.contains("gemini-2.5") == true) goto L5;
        return "minimal";
    L5:
        if (r1.contains("pro") == true) goto L10;
        return "none";
    L10:
        return "minimal";
    }

    private String getOpenAiReasoningEffort(String r1) {
        if (supportsOpenAiNoReasoning(stripProviderPrefix(r1)) == false) goto L6;
        return "none";
    L6:
        return "minimal";
    }

    private String getResponsesStreamError(JSONObject r2) {
        JSONObject r0 = r2.optJSONObject("error");
        if (r0 != null) goto L10;
        JSONObject r02 = r2.optJSONObject(PluginsConstants.RESPONSE);
        if (r02 == null) goto L8;
        JSONObject r1 = r02.optJSONObject("error");
    L7:
        r0 = r1;
        goto L10
    L8:
        r1 = null;
    L10:
        if (r0 == null) goto L12;
        String r12 = r0.optString("message");
    L14:
        if (TextUtils.isEmpty(r12) == false) goto L17;
        return "Unknown error";
    L17:
        return r12;
    L12:
        r12 = r2.optString("message");
        goto L14
    }

    private void handleStreamResponse(ResponseBody r9, BackendSink r10, boolean r11) {
        StringBuilder r0 = new StringBuilder();
        String r2 = null;
        ReasoningContentFilter r1 = new ReasoningContentFilter(null);
        BufferedReader r3 = new BufferedReader(new InputStreamReader(r9.byteStream()));     // Catch: Exception -> L43
        int r4 = 0;
    L74:
        String r5 = r3.readLine();     // Catch: Throwable -> L18
        if (r5 == null) goto L41;
        if (r10.isActive() == false) goto L41;
        if (TextUtils.isEmpty(r5) == true) goto L74;
        if (r5.startsWith("data:") == false) goto L74;
        String r52 = r5.substring(5).trim();     // Catch: Throwable -> L18
        if (r52.equals("[DONE]") == false) goto L20;
        StreamResponsePart r53 = StreamResponsePart.DONE;     // Catch: Throwable -> L18
    L24:
        if (r53.error() != null) goto L25;
        if (r53.hasReasoning() == false) goto L29;
        r10.onThinking();     // Catch: Throwable -> L18
    L29:
        String r6 = r1.filter(r53.content());     // Catch: Throwable -> L18
        if (r1.consumeReasoningSignal() == false) goto L33;
        r10.onThinking();     // Catch: Throwable -> L18
    L33:
        if (TextUtils.isEmpty(r6) == true) goto L38;
        r0.append(r6);     // Catch: Throwable -> L18
        r4 = r4 + r6.length();     // Catch: Throwable -> L18
        if (r4 < STREAM_SYMBOLS_LIMIT) goto L38;
        r10.onChunk(r0.toString());     // Catch: Throwable -> L18
        r4 = 0;
    L38:
        if (r53.done() == false) goto L74;
        if (r4 <= 0) goto L41;
        r10.onChunk(r0.toString());     // Catch: Throwable -> L18
        goto L41
    L25:
        r2 = r53.error();     // Catch: Throwable -> L18
        goto L41
    L20:
        if (r11 == false) goto L22;
        r53 = parseResponsesStreamPart(r52);     // Catch: Throwable -> L18
        goto L24
    L22:
        r53 = parseStreamResponsePart(r52);     // Catch: Throwable -> L18
    L41:
        r3.close();     // Catch: Exception -> L43
    L58:
        if (r10.isActive() == false) goto L87;
        if (r2 == null) goto L63;
        r10.onError(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r2);
        return;
    L63:
        String r92 = r1.flush();
        if (TextUtils.isEmpty(r92) == true) goto L66;
        r0.append(r92);
        r10.onChunk(r0.toString());
    L66:
        String r8 = trimTrailing(r0.toString());
        if (TextUtils.isEmpty(r8) == true) goto L69;
        r10.onComplete(r8);
        return;
    L69:
        r10.onError(Opcodes.SUB_DOUBLE_2ADDR, "Response body is empty");
        return;
    L87:
        return;
    L18:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L47
    L49:
        throw th;     // Catch: Exception -> L43
    L47:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L43
    L43:
        e = move-exception;
        if (r10.isActive() == false) goto L58;
        FileLog.e(e);
        if (e.getMessage() == null) goto L56;
        String r93 = e.getMessage();
    L55:
        r2 = r93;
        goto L58
    L56:
        r93 = "Unknown error";
        goto L55
    }

    private boolean hasReasoning(JSONObject r2) {
        if (hasValue(r2, "reasoning") == false) goto L5;
        return true;
    L5:
        if (hasValue(r2, "reasoning_content") == false) goto L7;
        return true;
    L7:
        if (hasValue(r2, "reasoning_details") == true) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean hasValue(JSONObject r2, String r3) {
        if (r2 != null) goto L5;
    L23:
        return false;
    L5:
        if (r2.has(r3) == false) goto L23;
        if (r2.isNull(r3) == true) goto L23;
        Object r22 = r2.opt(r3);
        if ((r22 instanceof String) == false) goto L14;
        return !TextUtils.isEmpty((String) r22);
    L14:
        if ((r22 instanceof JSONArray) == true) goto L16;
        if (r22 == null) goto L23;
        if (r22 == JSONObject.NULL) goto L23;
        return true;
    L16:
        if (((JSONArray) r22).length() <= 0) goto L18;
        return true;
    L18:
        return false;
    }

    private boolean isGeminiReasoningModel(String r1) {
        String r0 = stripProviderPrefix(r1);
        if (r0.startsWith("gemini-2.5") == false) goto L5;
        return true;
    L5:
        if (r0.startsWith("gemini-3") == false) goto L7;
        return true;
    L7:
        if (r0.contains("thinking") == true) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean isGeminiService(String r1) {
        return r1.contains("generativelanguage.googleapis");
    }

    private boolean isOpenAiReasoningModel(String r1) {
        String r0 = stripProviderPrefix(r1);
        if (r0.contains("gpt-5-chat") == false) goto L5;
        return false;
    L5:
        if (r0.startsWith("gpt-5") == false) goto L7;
        return true;
    L7:
        if (r0.startsWith("o1") == false) goto L9;
        return true;
    L9:
        if (r0.startsWith("o3") == false) goto L11;
        return true;
    L11:
        if (r0.startsWith("o4") == false) goto L19;
        return true;
    L19:
        return false;
    }

    private boolean isOpenAiService(String r1) {
        return r1.contains("api.openai.com");
    }

    private boolean isPerplexityService(String r1) {
        return r1.contains("api.perplexity.ai");
    }

    private String parseErrorMessage(String r1, String r2) {
        String r0 = extractErrorMessage(r1);
        if (TextUtils.isEmpty(r0) == true) goto L6;
        return r0;
    L6:
        if (TextUtils.isEmpty(r2) == false) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return r2.toLowerCase(Locale.ROOT);
    }

    private String parseResponseContent(String r4, boolean r5) {
        JSONObject r2 = new JSONObject(r4);     // Catch: Exception -> L9
        if (r5 == false) goto L12;
        String r42 = parseResponsesOutput(r2);     // Catch: Exception -> L9
        if (r42 != null) goto L8;
        return null;
    L8:
        return stripReasoningMarkup(r42);
    L12:
        JSONArray r43 = r2.optJSONArray("choices");     // Catch: Exception -> L9
        if (r43 != null) goto L15;
    L30:
        return null;
    L15:
        if (r43.length() <= 0) goto L30;
        JSONObject r44 = r43.getJSONObject(0).optJSONObject("message");     // Catch: Exception -> L9
        if (r44 != null) goto L19;
    L28:
        return null;
    L19:
        if (r44.has("content") == false) goto L28;
        if (r44.isNull("content") == true) goto L28;
        Object r45 = r44.opt("content");     // Catch: Exception -> L9
        if (r45 != null) goto L27;
        return null;
    L27:
        return stripReasoningMarkup(r45.toString());
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L30
    }

    private String parseResponsesOutput(JSONObject r10) {
        JSONArray r9 = r10.optJSONArray("output");
        if (r9 != null) goto L5;
        return null;
    L5:
        StringBuilder r0 = new StringBuilder();
        int r2 = 0;
    L7:
        if (r2 >= r9.length()) goto L27;
        JSONObject r3 = r9.optJSONObject(r2);
        if (r3 != null) goto L11;
    L13:
        JSONArray r32 = null;
    L14:
        if (r32 == null) goto L25;
        int r5 = 0;
    L18:
        if (r5 >= r32.length()) goto L25;
        JSONObject r6 = r32.optJSONObject(r5);
        if (r6 == null) goto L24;
        if ("output_text".equals(r6.optString("type")) == false) goto L24;
        r0.append(r6.optString("text"));
    L24:
        r5 = r5 + 1;
    L25:
        r2 = r2 + 1;
        goto L7
    L11:
        if ("message".equals(r3.optString("type")) == false) goto L13;
        r32 = r3.optJSONArray("content");
        goto L14
    L27:
        return r0.toString();
    }

    private StreamResponsePart parseResponsesStreamPart(String r11) {
        JSONObject r3 = new JSONObject(r11);     // Catch: Exception -> L36
        String r112 = r3.optString("type");     // Catch: Exception -> L36
        switch(r112.hashCode()) {
            case -975974101: goto L31;
            case -183760246: goto L27;
            case 96784904: goto L24;
            case 814923998: goto L21;
            case 1836015944: goto L17;
            case 1981626358: goto L7;
            default: goto L35;
        };     // Catch: Exception -> L36
    L35:
        return new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, r112.startsWith("response.reasoning"));
    L7:
        if (r112.equals("response.output_item.added") == false) goto L35;
        JSONObject r10 = r3.optJSONObject(PluginsConstants.Settings.ITEM);     // Catch: Exception -> L36
        if (r10 != null) goto L11;
    L13:
        boolean r102 = false;
    L15:
        return new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, r102);
    L11:
        if ("reasoning".equals(r10.optString("type")) == false) goto L13;
        r102 = true;
        goto L15
    L17:
        if (r112.equals("response.output_text.delta") == false) goto L35;
        return new StreamResponsePart(r3.optString("delta"), false);
    L21:
        if (r112.equals("response.completed") == false) goto L35;
    L33:
        return StreamResponsePart.DONE;
    L24:
        if (r112.equals("error") == false) goto L35;
    L29:
        return new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, false, true, getResponsesStreamError(r3), null);
    L27:
        if (r112.equals("response.failed") == false) goto L35;
    L31:
        if (r112.equals("response.incomplete") == false) goto L35;
    L37:
        return new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, false);
    }

    private StreamResponsePart parseStreamResponsePart(String r5) {
        JSONArray r52 = new JSONObject(r5).optJSONArray("choices");     // Catch: Exception -> L22
        if (r52 == null) goto L21;
        if (r52.length() <= 0) goto L21;
        JSONObject r53 = r52.getJSONObject(0).optJSONObject("delta");     // Catch: Exception -> L22
        if (r53 == null) goto L10;
        Object r2 = r53.opt("content");     // Catch: Exception -> L22
        if (r2 != null) goto L14;
    L17:
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        return new StreamResponsePart(r22, hasReasoning(r53));
    L14:
        if (r2 == JSONObject.NULL) goto L17;
        r22 = r2.toString();     // Catch: Exception -> L22
        goto L19
    L10:
        return new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, false);
    L21:
        return new StreamResponsePart(_UrlKt.FRAGMENT_ENCODE_SET, false);
    }

    private Endpoint resolveEndpoint(String r6) {
        if (TextUtils.isEmpty(r6) == false) goto L5;
        return null;
    L5:
        Locale r0 = Locale.ROOT;
        String r2 = r6.toLowerCase(r0);
        if (isGeminiService(r2) == false) goto L10;
        return new Endpoint("https://generativelanguage.googleapis.com/v1beta/openai/chat/completions", false, null);
    L10:
        if (isPerplexityService(r2) == false) goto L16;
        if (r2.contains("/router") == true) goto L16;
        return new Endpoint("https://api.perplexity.ai/v1/agent", true, null);
    L16:
        if (r6.endsWith("/") == false) goto L18;
        r6 = r6.substring(0, r6.length() - 1);
    L18:
        String r5 = r6.toLowerCase(r0);
        if (r5.endsWith("/responses") == true) goto L30;
        if (r5.endsWith("/agent") == true) goto L30;
        if (r5.endsWith("/chat/completions") == true) goto L28;
        r6 = r6.concat("/chat/completions");
    L28:
        return new Endpoint(r6, false, null);
    L30:
        return new Endpoint(r6, true, null);
    }

    private String stripProviderPrefix(String r1) {
        int r0 = r1.indexOf(47);
        if (r0 >= 0) goto L5;
        return r1;
    L5:
        return r1.substring(r0 + 1);
    }

    private String stripReasoningMarkup(String r5) {
        StringBuilder r0 = new StringBuilder(r5.length());
        String r1 = r5.toLowerCase(Locale.ROOT);
        int r2 = 0;
    L4:
        if (r2 >= r5.length()) goto L13;
        int r3 = r1.indexOf("<think>", r2);
        if (r3 < 0) goto L7;
        r0.append(r5, r2, r3);
        int r22 = r1.indexOf("</think>", r3 + 7);
        if (r22 < 0) goto L13;
        r2 = r22 + 8;
        goto L4
    L7:
        r0.append(r5, r2, r5.length());
    L13:
        return trimLeading(r0.toString());
    }

    private boolean supportsOpenAiNoReasoning(String r1) {
        if (r1.startsWith("gpt-5.1") == false) goto L5;
        return true;
    L5:
        if (r1.startsWith("gpt-5.2") == false) goto L7;
        return true;
    L7:
        if (r1.startsWith("gpt-5.3") == false) goto L9;
        return true;
    L9:
        if (r1.startsWith("gpt-5.4") == false) goto L11;
        return true;
    L11:
        if (r1.startsWith("gpt-5.5") == true) goto L20;
        return false;
    L20:
        return true;
    }

    private String trimLeading(String r2) {
        int r1 = 0;
    L4:
        if (r1 >= r2.length()) goto L9;
        if (Character.isWhitespace(r2.charAt(r1)) == false) goto L9;
        r1 = r1 + 1;
    L9:
        return r2.substring(r1);
    }

    private String trimTrailing(String r2) {
        int r1 = r2.length();
    L3:
        if (r1 <= 0) goto L8;
        if (Character.isWhitespace(r2.charAt(r1 - 1)) == false) goto L8;
        r1 = r1 - 1;
    L8:
        return r2.substring(0, r1);
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public void execute(BackendRequest r7, BackendSink r8) {
        Endpoint r1 = resolveEndpoint(r7.service().getUrl());
        String r2 = null;
        if (r1 == null) goto L5;
        Request r3 = createRequest(r7, r1);
    L7:
        if (r3 != null) goto L11;
        r8.onError(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, "Failed to create request body");
        return;
    L11:
        if (r8.isActive() == true) goto L13;
        return;
    L13:
        Call r32 = this.httpClient.newCall(r3);
        Objects.requireNonNull(r32);
        r8.onCancellable(new RestBackend$$ExternalSyntheticLambda0(r32));
        Response r33 = r32.execute();
    L18:
        th = move-exception;
        if (r33 != null) goto L51;
    L47:
        throw th;
    L51:
        r33.close();     // Catch: Throwable -> L45
    L45:
        th = move-exception;
        th.addSuppressed(th);
        goto L47
    L15:
        if (r33.isSuccessful() == false) goto L48;
        ResponseBody r0 = r33.body();     // Catch: Throwable -> L18
        if (r7.stream() == false) goto L28;
        handleStreamResponse(r0, r8, r1.responsesApi());     // Catch: Throwable -> L18
    L40:
        r33.close();
        return;
    L28:
        String r72 = r0.string();     // Catch: Throwable -> L18
        String r02 = parseResponseContent(r72, r1.responsesApi());     // Catch: Throwable -> L18
        if (TextUtils.isEmpty(r02) == false) goto L35;
        String r6 = extractErrorMessage(r72);     // Catch: Throwable -> L18
        if (TextUtils.isEmpty(r6) == true) goto L35;
        r8.onError(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r6);     // Catch: Throwable -> L18
        r33.close();
        return;
    L35:
        if (r02 != null) goto L39;
        r8.onError(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, "Failed to parse response");     // Catch: Throwable -> L18
        r33.close();
        return;
    L39:
        r8.onComplete(r02);     // Catch: Throwable -> L18
        goto L40
    L48:
        r2 = r33.body().string();     // Catch: Throwable -> L18 IOException -> L20
        FileLog.e("AI_ERROR_RESPONSE_BODY (" + r33.code() + "): " + r2);     // Catch: Throwable -> L18 IOException -> L20
    L22:
        r8.onError(r33.code(), parseErrorMessage(r2, r33.message()));     // Catch: Throwable -> L18
        r33.close();
        return;
    L20:
        e = move-exception;
        FileLog.e("AI_ERROR_READING_RESPONSE_BODY: ", e);     // Catch: Throwable -> L18
        goto L22
    L5:
        r3 = null;
        goto L7
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public int getHistoryCharsLimit() {
        return 24000;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public int getHistoryMessagesLimit() {
        return 32;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public int getMaxOutputTokens() {
        return 4096;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public float getMaxTemperature() {
        return 2.0f;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public boolean supportsImages() {
        return true;
    }
}
