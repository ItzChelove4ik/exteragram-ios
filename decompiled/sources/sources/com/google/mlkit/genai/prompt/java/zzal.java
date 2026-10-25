package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.CountTokensResponse;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerateContentResponse;
import com.google.mlkit.genai.prompt.GenerativeModel;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;

/* loaded from: classes5.dex */
public final class zzal extends GenerativeModelFutures {
    private final GenerativeModel zza;
    private final CoroutineScope zzb;

    public zzal(GenerativeModel r1) {
        this.zza = r1;
        this.zzb = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(r1.zzf()));
    }

    public static final /* synthetic */ GenerativeModel zza(zzal r0) {
        return r0.zza;
    }

    public static /* synthetic */ Object zzb(zzal r6, CallbackToFutureAdapter.Completer r7) {
        zzai r3 = new zzai(r7, r6, null);
        r7.addCancellationListener(new zzz(BuildersKt.launch$default(r6.zzb, null, null, r3, 3, null)), r6.zza.zzf());
        return "getBaseModelName";
    }

    public static /* synthetic */ Object zzc(zzal r6, CallbackToFutureAdapter.Completer r7) {
        zzad r3 = new zzad(r7, r6, null);
        r7.addCancellationListener(new zzaa(BuildersKt.launch$default(r6.zzb, null, null, r3, 3, null)), r6.zza.zzf());
        return "checkStatus";
    }

    public static /* synthetic */ Object zzd(zzal r6, CallbackToFutureAdapter.Completer r7) {
        zzaj r3 = new zzaj(r7, r6, null);
        r7.addCancellationListener(new zzab(BuildersKt.launch$default(r6.zzb, null, null, r3, 3, null)), r6.zza.zzf());
        return "isSystemPromptAvailable";
    }

    public static /* synthetic */ Object zze(zzal r6, CallbackToFutureAdapter.Completer r7) {
        zzak r3 = new zzak(r7, r6, null);
        r7.addCancellationListener(new zzo(BuildersKt.launch$default(r6.zzb, null, null, r3, 3, null)), r6.zza.zzf());
        return "isThinkingModeAvailable";
    }

    public static /* synthetic */ Object zzf(zzal r6, GenerateContentRequest r7, CallbackToFutureAdapter.Completer r8) {
        zzae r3 = new zzae(r8, r6, r7, null);
        r8.addCancellationListener(new zzp(BuildersKt.launch$default(r6.zzb, null, null, r3, 3, null)), r6.zza.zzf());
        return "generateContent";
    }

    public static /* synthetic */ Object zzg(zzal r6, GenerateContentRequest r7, StreamingCallback r8, CallbackToFutureAdapter.Completer r9) {
        zzaf r0 = new zzaf(r9, r6, r7, r8, null);
        r9.addCancellationListener(new zzq(BuildersKt.launch$default(r6.zzb, null, null, r0, 3, null)), r6.zza.zzf());
        return "generateContentStream";
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<Integer> checkStatus() {
        return CallbackToFutureAdapter.getFuture(new zzn(this));
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<CountTokensResponse> countTokens(GenerateContentRequest r1) {
        return this.zza.zzc(r1);
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<Void> download(DownloadCallback r1) {
        return this.zza.zza(r1);
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<GenerateContentResponse> generateContent(GenerateContentRequest r2) {
        return CallbackToFutureAdapter.getFuture(new zzv(this, r2));
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<GenerateContentResponse> generateContent(GenerateContentRequest r2, StreamingCallback r3) {
        return CallbackToFutureAdapter.getFuture(new zzw(this, r2, r3));
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<String> getBaseModelName() {
        return CallbackToFutureAdapter.getFuture(new zzac(this));
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<Integer> getTokenLimit() {
        return this.zza.zzd();
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<Boolean> isSystemPromptAvailable() {
        return CallbackToFutureAdapter.getFuture(new zzt(this));
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<Boolean> isThinkingModeAvailable() {
        return CallbackToFutureAdapter.getFuture(new zzu(this));
    }

    @Override // com.google.mlkit.genai.prompt.java.GenerativeModelFutures
    public final ListenableFuture<Void> warmup() {
        return this.zza.zzb();
    }
}
