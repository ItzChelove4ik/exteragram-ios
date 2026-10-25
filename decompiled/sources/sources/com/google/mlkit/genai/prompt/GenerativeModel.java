package com.google.mlkit.genai.prompt;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.StreamingCallback;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H¦@¢\u0006\u0004\b\u0006\u0010\u0004J\u0010\u0010\b\u001a\u00020\u0007H¦@¢\u0006\u0004\b\b\u0010\u0004J\u0010\u0010\t\u001a\u00020\u0007H¦@¢\u0006\u0004\b\t\u0010\u0004J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH¦@¢\u0006\u0004\b\r\u0010\u000eJ \u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH¦@¢\u0006\u0004\b\r\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H&¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0018\u001a\u00020\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Lcom/google/mlkit/genai/prompt/GenerativeModel;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getBaseModelName", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "checkStatus", _UrlKt.FRAGMENT_ENCODE_SET, "isSystemPromptAvailable", "isThinkingModeAvailable", "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;", PluginsConstants.REQUEST, "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;", "generateContent", "(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/google/mlkit/genai/common/StreamingCallback;", "streamingCallback", "(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Lcom/google/mlkit/genai/prompt/Caches;", "getCaches", "()Lcom/google/mlkit/genai/prompt/Caches;", "caches", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface GenerativeModel {
    Object checkStatus(Continuation<? super Integer> r1);

    void close();

    Object generateContent(GenerateContentRequest r1, StreamingCallback r2, Continuation<? super GenerateContentResponse> r3);

    Object generateContent(GenerateContentRequest r1, Continuation<? super GenerateContentResponse> r2);

    Object getBaseModelName(Continuation<? super String> r1);

    Caches getCaches();

    Object isSystemPromptAvailable(Continuation<? super Boolean> r1);

    Object isThinkingModeAvailable(Continuation<? super Boolean> r1);

    ListenableFuture zza(DownloadCallback r1);

    ListenableFuture zzb();

    ListenableFuture zzc(GenerateContentRequest r1);

    ListenableFuture zzd();

    ExecutorService zzf();
}
