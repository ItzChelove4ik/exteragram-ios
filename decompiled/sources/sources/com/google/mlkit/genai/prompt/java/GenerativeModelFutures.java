package com.google.mlkit.genai.prompt.java;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.CountTokensResponse;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerateContentResponse;
import com.google.mlkit.genai.prompt.GenerativeModel;
import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bJ\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002H&¢\u0006\u0004\b\u0007\u0010\u0005J\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00022\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u0002H&¢\u0006\u0004\b\r\u0010\u0005J\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00022\u0006\u0010\u000f\u001a\u00020\u000eH&¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002H&¢\u0006\u0004\b\u0013\u0010\u0005J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0002H&¢\u0006\u0004\b\u0015\u0010\u0005J\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0002H&¢\u0006\u0004\b\u0016\u0010\u0005J\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00022\u0006\u0010\u000f\u001a\u00020\u000eH&¢\u0006\u0004\b\u0018\u0010\u0012J%\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0019H&¢\u0006\u0004\b\u0018\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/common/util/concurrent/ListenableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "getBaseModelName", "()Lcom/google/common/util/concurrent/ListenableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "checkStatus", "Lcom/google/mlkit/genai/common/DownloadCallback;", Callback.METHOD_NAME, "Ljava/lang/Void;", "download", "(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;", "warmup", "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;", PluginsConstants.REQUEST, "Lcom/google/mlkit/genai/prompt/CountTokensResponse;", "countTokens", "(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;", "getTokenLimit", _UrlKt.FRAGMENT_ENCODE_SET, "isSystemPromptAvailable", "isThinkingModeAvailable", "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;", "generateContent", "Lcom/google/mlkit/genai/common/StreamingCallback;", "(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)Lcom/google/common/util/concurrent/ListenableFuture;", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class GenerativeModelFutures {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "from", "Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;", "generativeModel", "Lcom/google/mlkit/genai/prompt/GenerativeModel;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }

        @JvmStatic
        public final GenerativeModelFutures from(GenerativeModel r1) {
            return new zzal(r1);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public GenerativeModelFutures() {
    }

    @JvmStatic
    public static final GenerativeModelFutures from(GenerativeModel r1) {
        return Companion.from(r1);
    }

    public abstract ListenableFuture<Integer> checkStatus();

    public abstract ListenableFuture<CountTokensResponse> countTokens(GenerateContentRequest r1);

    public abstract ListenableFuture<Void> download(DownloadCallback r1);

    public abstract ListenableFuture<GenerateContentResponse> generateContent(GenerateContentRequest r1);

    public abstract ListenableFuture<GenerateContentResponse> generateContent(GenerateContentRequest r1, StreamingCallback r2);

    public abstract ListenableFuture<String> getBaseModelName();

    public abstract ListenableFuture<Integer> getTokenLimit();

    public abstract ListenableFuture<Boolean> isSystemPromptAvailable();

    public abstract ListenableFuture<Boolean> isThinkingModeAvailable();

    public abstract ListenableFuture<Void> warmup();
}
