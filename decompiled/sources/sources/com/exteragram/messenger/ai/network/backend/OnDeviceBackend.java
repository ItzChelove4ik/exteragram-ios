package com.exteragram.messenger.ai.network.backend;

import android.os.Build;
import android.text.TextUtils;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.data.Role;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.CachedContext;
import com.google.mlkit.genai.prompt.Candidate;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerateContentResponse;
import com.google.mlkit.genai.prompt.GenerativeModel;
import com.google.mlkit.genai.prompt.ImagePart;
import com.google.mlkit.genai.prompt.PromptPrefix;
import com.google.mlkit.genai.prompt.SystemInstruction;
import com.google.mlkit.genai.prompt.TextPart;
import com.google.mlkit.genai.prompt.java.CachesFutures;
import com.google.mlkit.genai.prompt.java.GenerativeModelFutures;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public class OnDeviceBackend implements Backend {
    private static volatile boolean cachingUnsupported;

    /* renamed from: com.exteragram.messenger.ai.network.backend.OnDeviceBackend$1, reason: invalid class name */
    public class AnonymousClass1 implements StreamingCallback {
        final /* synthetic */ OnDeviceBackend this$0;
        final /* synthetic */ StringBuffer val$accumulated;
        final /* synthetic */ BackendSink val$sink;

        public AnonymousClass1(OnDeviceBackend r1, BackendSink r2, StringBuffer r3) {
            this.this$0 = r1;
            this.val$sink = r2;
            this.val$accumulated = r3;
        }

        @Override // com.google.mlkit.genai.common.StreamingCallback
        public void onNewText(String r2) {
            if (this.val$sink.isActive() == true) goto L5;
            return;
        L5:
            if (TextUtils.isEmpty(r2) == true) goto L10;
            this.val$accumulated.append(r2);
            this.val$sink.onChunk(this.val$accumulated.toString());
            return;
        }

        @Override // com.google.mlkit.genai.common.StreamingCallback
        public void onNewThought(String r1) {
            if (this.val$sink.isActive() == false) goto L6;
            this.val$sink.onThinking();
            return;
        }
    }

    /* renamed from: $r8$lambda$ou-CoPEgPbw-7-oeG85KvVCFEWs, reason: not valid java name */
    public static /* synthetic */ void m1033$r8$lambda$ouCoPEgPbw7oeG85KvVCFEWs(ListenableFuture r1) {
        r1.cancel(true);
    }

    public OnDeviceBackend() {
    }

    private String buildInstruction(String r2) {
        StringBuilder r1 = new StringBuilder();
        if (TextUtils.isEmpty(r2) == true) goto L5;
        r1.append(r2);
        r1.append("\n\n");
    L5:
        r1.append("Unless told otherwise, always reply in the same language the user writes in.");
        Locale r22 = LocaleController.getInstance().getCurrentLocale();
        if (r22 == null) goto L8;
        String r23 = r22.getDisplayLanguage(Locale.ENGLISH);
    L10:
        if (TextUtils.isEmpty(r23) == true) goto L13;
        r1.append(" If the language is unclear, reply in ");
        r1.append(r23);
        r1.append('.');
    L13:
        return r1.toString();
    L8:
        r23 = null;
        goto L10
    }

    private String buildPrompt(List<Message> r5, Message r6, int r7) {
        StringBuilder r4 = new StringBuilder();
    L3:
        String r1 = "User: ";
        if (r7 >= r5.size()) goto L15;
        Message r0 = r5.get(r7);
        if (r0 == null) goto L14;
        if (TextUtils.isEmpty(r0.content()) == true) goto L14;
        if ("assistant".equals(r0.role()) == false) goto L13;
        r1 = "Assistant: ";
    L13:
        r4.append(r1);
        r4.append(r0.content());
        r4.append("\n\n");
    L14:
        r7 = r7 + 1;
        goto L3
    L15:
        r4.append("User: ");
        r4.append(r6.content());
        return r4.toString();
    }

    private int countTokens(GenerativeModelFutures r3, GenerateContentRequest r4) {
        return r3.countTokens(r4).get(10, TimeUnit.SECONDS).getTotalTokens();
    L4:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_COUNT_TOKENS", e);
        return 0;
    }

    private ImagePart createImagePart(Message r2) {
        byte[] r1 = r2.getImageData();
        if (r1 != null) goto L5;
    L11:
        return null;
    L5:
        if (r1.length == 0) goto L11;
        return new ImagePart(r1);
    L9:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_IMAGE_REJECTED", e);
        goto L11
    }

    private GenerateContentRequest createRequest(BackendRequest r1, SystemInstruction r2, String r3, ImagePart r4, String r5, boolean r6) {
        TextPart r0 = new TextPart(r5);
        if (r2 == null) goto L6;
        if (r4 == null) goto L6;
        GenerateContentRequest.Builder r52 = new GenerateContentRequest.Builder(r2, r4, r0);
    L11:
        r52.setTemperature(Float.valueOf(r1.temperature()));
        r52.setMaxOutputTokens(Integer.valueOf(r1.maxOutputTokens()));
        r52.setEnableThinking(r6);
        if (r3 == null) goto L15;
        r52.setCachedContextName(r3);
    L15:
        return r52.build();
    L6:
        if (r2 == null) goto L8;
        r52 = new GenerateContentRequest.Builder(r2, r0);
        goto L11
    L8:
        if (r4 == null) goto L10;
        r52 = new GenerateContentRequest.Builder(r4, r0);
        goto L11
    L10:
        r52 = new GenerateContentRequest.Builder(r0);
        goto L11
    }

    private String ensureCachedContext(GenerativeModel r10, String r11) {
        TimeUnit r9 = TimeUnit.SECONDS;
        if (cachingUnsupported == false) goto L5;
    L43:
        return null;
    L5:
        if (TextUtils.isEmpty(r11) == true) goto L43;
        String r0 = Utilities.MD5(r11);
        if (TextUtils.isEmpty(r0) == false) goto L10;
        return null;
    L10:
        String r02 = "extera_role_" + r0;
        CachesFutures r102 = CachesFutures.from(r10);     // Catch: Exception -> L28 InterruptedException -> L42
        ArrayList r2 = new ArrayList();     // Catch: Exception -> L28 InterruptedException -> L42
        List<CachedContext> r4 = r102.list().get(10, r9);     // Catch: Exception -> L28 InterruptedException -> L42
        if (r4 == null) goto L31;
        Iterator<CachedContext> r42 = r4.iterator();     // Catch: Exception -> L28 InterruptedException -> L42
    L15:
        if (r42.hasNext() == false) goto L31;
        CachedContext r7 = r42.next();     // Catch: Exception -> L28 InterruptedException -> L42
        if (r7 != null) goto L19;
        String r72 = null;
    L20:
        if (r72 == null) goto L15;
        if (r72.startsWith("extera_role_") == false) goto L15;
        if (r02.equals(r72) == true) goto L26;
        r2.add(r72);     // Catch: Exception -> L28 InterruptedException -> L42
        goto L15
    L26:
        return r02;
    L19:
        r72 = r7.getName();     // Catch: Exception -> L28 InterruptedException -> L42
    L31:
        if (r2.size() < 4) goto L35;
        int r3 = r2.size();     // Catch: Exception -> L28 InterruptedException -> L42
        int r43 = 0;
    L33:
        if (r43 >= r3) goto L35;
        Object r73 = r2.get(r43);     // Catch: Exception -> L28 InterruptedException -> L42
        r43 = r43 + 1;     // Catch: Exception -> L28 InterruptedException -> L42
        r102.delete((String) r73).get(10, r9);     // Catch: Exception -> L28 InterruptedException -> L42
    L35:
        CachedContext r92 = r102.create(new CreateCachedContextRequest.Builder(r02, new PromptPrefix(r11)).build()).get(30, r9);     // Catch: Exception -> L28 InterruptedException -> L42
        if (r92 != null) goto L38;
        return null;
    L38:
        return r92.getName();
    L42:
        Thread.currentThread().interrupt();
    L28:
        e = move-exception;
        cachingUnsupported = true;
        FileLog.e("AI_ON_DEVICE_CACHE", e);
        return null;
    }

    private String extractText(GenerateContentResponse r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        List<Candidate> r22 = r2.getCandidates();
        if (r22 != null) goto L8;
        return null;
    L8:
        if (r22.isEmpty() == false) goto L11;
        return null;
    L11:
        return r22.get(0).getText();
    }

    private static GenAiException findGenAiException(Throwable r1) {
    L2:
        if (r1 == null) goto L8;
        if ((r1 instanceof GenAiException) == true) goto L6;
        r1 = r1.getCause();
        goto L2
    L6:
        return (GenAiException) r1;
    L8:
        return null;
    }

    private void generate(BackendRequest r17, BackendSink r18, GenerativeModelFutures r19, GenerativeModel r20) {
        Role r1 = r17.role();
        String r9 = null;
        if (r1 == null) goto L5;
        String r12 = r1.getPrompt();
    L6:
        String r10 = buildInstruction(r12);
        if (r17.service().isReasoningEnabled() == true) goto L9;
    L11:
        boolean r6 = false;
    L13:
        if (OnDeviceAvailability.isSystemPromptSupported() == false) goto L16;
        SystemInstruction r13 = new SystemInstruction(r10);
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        String r11 = r3;
        ImagePart r4 = createImagePart(r17.currentMessage());
        List<Message> r122 = r17.history();
        int r132 = (resolveTokenLimit() - r17.maxOutputTokens()) - 256;
        int r14 = 0;
    L19:
        if (r18.isActive() == false) goto L80;
        String r15 = buildPrompt(r122, r17.currentMessage(), r14);
        SystemInstruction r2 = r13;
        int r16 = countTokens(r19, createRequest(r17, r2, null, r4, r11 + r15, r6));
        if (r16 <= r132) goto L27;
        if (r14 >= r122.size()) goto L27;
        r14 = r14 + 2;
        r13 = r2;
    L27:
        if (r16 <= r132) goto L31;
        r18.onError(-4, "prompt exceeds the on-device token limit");
        return;
    L31:
        if (r18.isActive() == false) goto L78;
        if (r2 != null) goto L36;
        if (r4 != null) goto L36;
        r9 = ensureCachedContext(r20, r10);
    L36:
        String r32 = r9;
        if (r32 == null) goto L39;
        GenerateContentRequest r22 = createRequest(r17, null, r32, r4, r15, r6);
        OnDeviceBackend r0 = this;
    L40:
        StringBuffer r110 = new StringBuffer();
        if (r17.stream() == false) goto L43;
        ListenableFuture<GenerateContentResponse> r23 = r19.generateContent(r22, new AnonymousClass1(r0, r18, r110));
    L44:
        r18.onCancellable(new OnDeviceBackend$$ExternalSyntheticLambda0(r23));
        GenerateContentResponse r24 = r23.get();     // Catch: ExecutionException -> L58
        if (r18.isActive() == false) goto L79;
        String r25 = r0.extractText(r24);
        if (TextUtils.isEmpty(r25) == false) goto L52;
        r25 = r110.toString();
    L52:
        String r02 = r0.trimTrailing(r25);
        if (TextUtils.isEmpty(r02) == false) goto L56;
        r18.onError(Opcodes.SUB_DOUBLE_2ADDR, "Response body is empty");
        return;
    L56:
        r18.onComplete(r02);
        return;
    L79:
        return;
    L58:
        e = move-exception;
        GenAiException r111 = findGenAiException(e);
        if (r111 != null) goto L62;
    L71:
        throw e;
    L62:
        if (isInferenceFailure(r111.getErrorCode()) == false) goto L71;
        if (r18.isActive() == false) goto L77;
        FileLog.e("AI_ON_DEVICE_INFERENCE_FAILED", e);
        if (r111.getMessage() == null) goto L68;
        String r03 = r111.getMessage();
    L69:
        r18.onError(-5, r03);
        return;
    L68:
        r03 = "Inference failed";
        goto L69
    L77:
        return;
    L43:
        r23 = r19.generateContent(r22);
        goto L44
    L39:
        r0 = this;
        r22 = r0.createRequest(r17, r2, null, r4, r11 + r15, r6);
        goto L40
    L78:
        return;
    L80:
        return;
    L16:
        r3 = r10 + "\n\n";
        r13 = null;
        goto L15
    L9:
        if (OnDeviceAvailability.isThinkingSupported() == false) goto L11;
        r6 = true;
        goto L13
    L5:
        r12 = null;
        goto L6
    }

    private static boolean isInferenceFailure(int r1) {
        if (r1 != (-101)) goto L5;
        return false;
    L5:
        if (r1 != 12) goto L7;
        return false;
    L7:
        if (r1 != 16) goto L9;
        return false;
    L9:
        if (r1 != 27) goto L11;
        return false;
    L11:
        if (r1 != 30) goto L13;
        return false;
    L13:
        if (r1 != 501) goto L15;
        return false;
    L15:
        if (r1 != 604) goto L17;
        return false;
    L17:
        if (r1 != 7) goto L19;
        return false;
    L19:
        if (r1 != 8) goto L21;
        return false;
    L21:
        if (r1 == 9) goto L34;
        return true;
    L34:
        return false;
    }

    private int resolveTokenLimit() {
        int r0 = OnDeviceAvailability.getTokenLimit();
        if (r0 <= 0) goto L5;
        return r0;
    L5:
        return 4000;
    }

    private String trimTrailing(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        int r1 = r2.length();
    L6:
        if (r1 <= 0) goto L11;
        if (Character.isWhitespace(r2.charAt(r1 - 1)) == false) goto L11;
        r1 = r1 - 1;
    L11:
        return r2.substring(0, r1);
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public void execute(BackendRequest r9, BackendSink r10) {
        if (Build.VERSION.SDK_INT >= 26) goto L6;
        r10.onError(-1, "on-device model requires android 8.0");
        return;
    L6:
        int r0 = OnDeviceAvailability.getConfigGeneration();
        GenerativeModel r1 = null;
        r1 = OnDeviceAvailability.createModel();     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        GenerativeModelFutures r3 = GenerativeModelFutures.from(r1);     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        int r4 = r3.checkStatus().get(10, TimeUnit.SECONDS).intValue();     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        OnDeviceAvailability.report(r4, r0);     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        if (r10.isActive() == true) goto L11;
        if (r1 != null) goto L58;
    L64:
        return;
    L58:
        r1.close();     // Catch: Throwable -> L55
        return;
    L66:
        return;
    L11:
        if (r4 != 0) goto L20;
        r10.onError(-1, "on-device model is not available");     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        if (r1 == null) goto L64;
    L20:
        if (r4 != 1) goto L25;
        r10.onError(-2, "on-device model is not downloaded");     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        if (r1 == null) goto L64;
    L25:
        if (r4 != 2) goto L30;
        r10.onError(-3, "on-device model is downloading");     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        if (r1 == null) goto L64;
    L30:
        OnDeviceAvailability.ensureMetadata(r3, r0);     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        generate(r9, r10, r3, r1);     // Catch: Throwable -> L15 Exception -> L17 InterruptedException -> L48
        if (r1 == null) goto L64;
        r1.close();     // Catch: Throwable -> L56
        return;
    L15:
        th = move-exception;
        if (r1 != null) goto L61;
    L54:
        throw th;
    L61:
        r1.close();     // Catch: Throwable -> L57
    L48:
        Thread.currentThread().interrupt();     // Catch: Throwable -> L15
        if (r1 == null) goto L68;
    L46:
        r1.close();     // Catch: Throwable -> L56
        return;
    L68:
        return;
    L17:
        e = move-exception;
        if (r10.isActive() == false) goto L45;
        FileLog.e("AI_ON_DEVICE_FAILED", e);     // Catch: Throwable -> L15
        GenAiException r92 = findGenAiException(e);     // Catch: Throwable -> L15
        if (e.getMessage() == null) goto L39;
        String r8 = e.getMessage();     // Catch: Throwable -> L15
    L40:
        if (r92 != null) goto L42;
    L44:
        r10.onError(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r8);     // Catch: Throwable -> L15
        goto L45
    L42:
        if (r92.getErrorCode() != 12) goto L44;
        r10.onError(-4, r8);     // Catch: Throwable -> L15
        goto L45
    L39:
        r8 = "Unknown error";
    L45:
        if (r1 != null) goto L46;
        return;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public int getHistoryCharsLimit() {
        return 12000;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public int getHistoryMessagesLimit() {
        return 24;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public int getMaxOutputTokens() {
        return 1024;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public float getMaxTemperature() {
        return 1.0f;
    }

    @Override // com.exteragram.messenger.ai.network.backend.Backend
    public boolean supportsImages() {
        return true;
    }
}
