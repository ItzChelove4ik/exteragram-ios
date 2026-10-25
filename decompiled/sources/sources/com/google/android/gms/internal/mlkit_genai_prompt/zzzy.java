package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.DownloadStatus;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.common.internal.GenAiUtils;
import com.google.mlkit.genai.prompt.Caches;
import com.google.mlkit.genai.prompt.CountTokensResponse;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import com.google.mlkit.genai.prompt.CreateCachedContextRequestKt;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerateContentResponse;
import com.google.mlkit.genai.prompt.GenerationConfig;
import com.google.mlkit.genai.prompt.GenerativeModel;
import com.google.mlkit.genai.prompt.PromptPrefix;
import java.util.List;
import java.util.concurrent.ExecutorService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.reflect.KClass;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.guava.ListenableFutureKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzzy implements GenerativeModel {
    public static final zzyt zza = null;
    private static final Lazy zzn = null;
    private final GenerationConfig zzb;
    private final zzaah zzc;
    private final zzwl zzd;
    private final zzaak zze;
    private final zzaah zzf;
    private final zzaak zzg;
    private final ExecutorService zzh;
    private final CoroutineScope zzi;
    private final zzyd zzj;
    private zzxs zzk;
    private zzaag zzl;
    private final Lazy zzm;

    static {
        zza = new zzyt(null);
        LazyKt.lazy(zzza.zza);
        zzn = LazyKt.lazy(zzzb.zza);
    }

    public zzzy(GenerationConfig r7, zzaah r8, zzwl r9, zzaak r10, zzaah r11, zzaak r12) {
        this.zzb = r7;
        this.zzc = r8;
        this.zzd = r9;
        this.zze = r10;
        this.zzf = r11;
        this.zzg = r12;
        ExecutorService r72 = r7.getWorkerExecutor();
        if (r72 != null) goto L5;
        r72 = new MlKitThreadPool();
    L5:
        this.zzh = r72;
        this.zzi = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(r72));
        this.zzj = new zzyd(r8, CollectionsKt.listOf(new zzaur[]{r9, r10, r11, r12}));
        zzxq r82 = zzxs.zza;
        zzxs r92 = zzxs.zzf();
        if (r92 != null) goto L18;
        monitor-enter(r82);
        r92 = zzxs.zzf();     // Catch: Throwable -> L12
        if (r92 != null) goto L14;
        r92 = new zzxs(new zzxg(r72, ".mlkit_genai_prompt_explicit_prefix_cache", zzxu.zza), null);     // Catch: Throwable -> L12
        zzxs.zzg(r92);     // Catch: Throwable -> L12
    L14:
        monitor-exit(r82);
    L12:
        th = move-exception;
        throw th;
    L18:
        this.zzk = r92;
        zzzz r83 = zzaag.zza;
        zzaag r93 = zzaag.zzf();
        if (r93 != null) goto L31;
        monitor-enter(r83);
        r93 = zzaag.zzf();     // Catch: Throwable -> L25
        if (r93 != null) goto L27;
        zzaag r0 = new zzaag(new zzxg(r72, ".mlkit_prefix_cache", zzaac.zza), 0, null, 6, null);     // Catch: Throwable -> L25
        zzaag.zzg(r0);     // Catch: Throwable -> L25
        r93 = r0;
    L27:
        monitor-exit(r83);
    L25:
        th = move-exception;
        throw th;
    L31:
        this.zzl = r93;
        this.zzm = LazyKt.lazy(new zzzc(this));
    }

    private final Object zzA(Continuation r5) {
        if ((r5 instanceof zzze) == false) goto L7;
        zzze r0 = (zzze) r5;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        boolean r3 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r52);
    L17:
        if (((Number) r52).intValue() == 3) goto L21;
        r3 = false;
    L21:
        return Boxing.boxBoolean(r3);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r52);
        r0.zzc = 1;
        r52 = zzD(r0);
        if (r52 != r12) goto L17;
        return r12;
    L7:
        r0 = new zzze(this, r5);
        goto L8
    }

    private final Object zzC(Continuation r2) {
        zzwl r0 = this.zzd;
        if (this.zzj.zzd(r0) == false) goto L7;
        return Boxing.boxInt(0);
    L7:
        return zzwl.zzb(r0, r2);
    }

    private final Object zzD(Continuation r2) {
        zzaak r0 = this.zze;
        if (this.zzj.zzd(r0) == false) goto L7;
        return Boxing.boxInt(0);
    L7:
        return zzaak.zzb(r0, r2);
    }

    private static final int zzE(String r4) {
        MatchResult r42 = Regex.find$default(new Regex("nano-v(\\d+)"), r4, 0, 2, null);
        if (r42 == null) goto L12;
        List<String> r43 = r42.getGroupValues();
        if (r43 == null) goto L12;
        String r44 = r43.get(1);
        if (r44 == null) goto L12;
        Integer r45 = StringsKt.toIntOrNull(r44);
        if (r45 == null) goto L12;
        return r45.intValue();
    L12:
        return 0;
    }

    public static final /* synthetic */ Object zzg(zzzy r0, Continuation r1) {
        return r0.zzu(r1);
    }

    public static final /* synthetic */ Object zzh(zzzy r0, GenerateContentRequest r1, KClass r2, boolean r3, Continuation r4) {
        return r0.zzv(null, null, false, r4);
    }

    public static final /* synthetic */ Object zzj(zzzy r0, GenerateContentRequest r1, StreamingCallback r2, KClass r3, boolean r4, Continuation r5) {
        return r0.zzw(null, null, null, false, r5);
    }

    public static final /* synthetic */ Object zzk(zzzy r0, GenerateContentRequest r1, StreamingCallback r2, String r3, KClass r4, boolean r5, Continuation r6) {
        return r0.zzx(null, null, null, null, false, r6);
    }

    public static final /* synthetic */ Object zzl(zzzy r0, GenerateContentRequest r1, StreamingCallback r2, String r3, KClass r4, boolean r5, Continuation r6) {
        return r0.zzy(null, null, null, null, false, r6);
    }

    public static final /* synthetic */ Object zzm(zzzy r0, Continuation r1) {
        return r0.zzz(r1);
    }

    public static final /* synthetic */ Object zzn(zzzy r0, Continuation r1) {
        return r0.zzA(r1);
    }

    public static final /* synthetic */ Lazy zzp() {
        return zzn;
    }

    public static /* synthetic */ zzxo zzq(zzzy r3) {
        return new zzxo(zzyt.zza(zza), r3.zzd, r3.zzk);
    }

    public static /* synthetic */ Object zzr(zzzy r6, DownloadCallback r7, CallbackToFutureAdapter.Completer r8) {
        zzzl r3 = new zzzl(r6, r8, r7, null);
        r8.addCancellationListener(new zzyx(BuildersKt.launch$default(r6.zzi, null, null, r3, 3, null)), r6.zzh);
        return "download";
    }

    public static /* synthetic */ Object zzs(zzzy r6, CallbackToFutureAdapter.Completer r7) {
        zzzx r3 = new zzzx(r6, r7, null);
        r7.addCancellationListener(new zzyy(BuildersKt.launch$default(r6.zzi, null, null, r3, 3, null)), r6.zzh);
        return "warmup";
    }

    private final Object zzu(Continuation r8) {
        if ((r8 instanceof zzzv) == false) goto L7;
        zzzv r0 = (zzzv) r8;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L22;
        if (r2 == 1) goto L20;
        if (r2 == 2) goto L19;
        if (r2 == 3) goto L18;
        if (r2 != 4) goto L16;
        ResultKt.throwOnFailure(r82);     // Catch: GenAiException -> L38
    L41:
        return Unit.INSTANCE;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r82);     // Catch: GenAiException -> L38
    L33:
        if (((Number) r82).intValue() == 0) goto L41;
        zzaak r7 = this.zze;     // Catch: GenAiException -> L38
        r0.zzc = 4;     // Catch: GenAiException -> L38
        if (r7.zzE(r0) != r12) goto L41;
    L37:
        return r12;
    L19:
        ResultKt.throwOnFailure(r82);     // Catch: GenAiException -> L38
    L30:
        r0.zzc = 3;     // Catch: GenAiException -> L38
        r82 = zzD(r0);     // Catch: GenAiException -> L38
        if (r82 == r12) goto L37;
    L20:
        ResultKt.throwOnFailure(r82);     // Catch: GenAiException -> L38
    L26:
        if (((Number) r82).intValue() == 0) goto L30;
        zzwl r83 = this.zzd;     // Catch: GenAiException -> L38
        r0.zzc = 2;     // Catch: GenAiException -> L38
        if (r83.zzE(r0) != r12) goto L30;
    L22:
        ResultKt.throwOnFailure(r82);
        r0.zzc = 1;     // Catch: GenAiException -> L38
        r82 = zzC(r0);     // Catch: GenAiException -> L38
        if (r82 == r12) goto L37;
    L38:
        e = move-exception;
        Log.w("GenerativeModel", "Failed to prepare inference engine for prefix caching", e);
    L7:
        r0 = new zzzv(this, r8);
        goto L8
    }

    private final Object zzv(GenerateContentRequest r11, KClass r12, boolean r13, Continuation r14) {
        if ((r14 instanceof zzzi) == false) goto L7;
        zzzi r0 = (zzzi) r14;
        int r1 = r0.zzf;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzf = r1 - Integer.MIN_VALUE;
    L8:
        Object r142 = r0.zzd;
        Object r15 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzf;
        if (r2 == 0) goto L20;
        if (r2 == 1) goto L19;
        if (r2 == 2) goto L18;
        if (r2 == 3) goto L17;
        if (r2 != 4) goto L15;
        zzys r10 = (zzys) r0.zza;
        ResultKt.throwOnFailure(r142);
    L44:
        return r10.zza((zzdz) r142);
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L17:
        zzys r102 = (zzys) r0.zza;
        ResultKt.throwOnFailure(r142);
    L38:
        return r102.zza((zzdz) r142);
    L18:
        boolean r112 = r0.zzc;
        String r122 = r0.zzg;
        KClass r132 = (KClass) r0.zzb;
        GenerateContentRequest r22 = (GenerateContentRequest) r0.zza;
        ResultKt.throwOnFailure(r142);
        boolean r143 = r112;
        r11 = r22;
        Object r23 = r142;
    L31:
        zzxt r24 = (zzxt) r23;
        if (r24 == null) goto L39;
        GenerateContentRequest.Builder r113 = r11.zza(null, null);
        r113.setPromptPrefix(new PromptPrefix(r24.zzd()));
        r113.setCachedContextName(null);
        GenerateContentRequest r114 = r113.build();
        zzaah r103 = this.zzc;
        zzys r123 = zzys.zza;
        zzaan r25 = new zzaan(r114, r132, r143);
        r0.zza = r123;
        r0.zzb = null;
        r0.zzg = null;
        r0.zzf = 3;
        r142 = r103.zzG(r25, r0);
        if (r142 == r15) goto L45;
        r102 = r123;
    L45:
        return r15;
    L39:
        Log.w("GenerativeModel", "Cached context not found for name: ".concat(String.valueOf(r122)));
        r12 = r132;
        r13 = r143;
    L40:
        zzaah r104 = this.zzc;
        zzys r144 = zzys.zza;
        zzaan r26 = new zzaan(r11, r12, r13);
        r0.zza = r144;
        r0.zzb = null;
        r0.zzg = null;
        r0.zzf = 4;
        Object r105 = r104.zzG(r26, r0);
        if (r105 == r15) goto L45;
        r142 = r105;
        r10 = r144;
        goto L44
    L19:
        r13 = r0.zzc;
        String r115 = r0.zzg;
        r12 = (KClass) r0.zzb;
        GenerateContentRequest r27 = (GenerateContentRequest) r0.zza;
        ResultKt.throwOnFailure(r142);
        String r145 = r115;
        r11 = r27;
        Object r28 = r142;
    L28:
        zzxs r6 = this.zzk;
        Context r8 = zzyt.zza(zza);
        r0.zza = r11;
        r0.zzb = r12;
        r0.zzg = r145;
        r0.zzc = r13;
        r0.zzf = 2;
        r23 = zzxs.zza(r6, r8, r145, (String) r28, r0);
        if (r23 == r15) goto L45;
        boolean r9 = r13;
        r132 = r12;
        r122 = r145;
        r143 = r9;
        goto L31
    L20:
        ResultKt.throwOnFailure(r142);
        r145 = r11.getCachedContextName();
        if (r145 != null) goto L24;
        r145 = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        if (r145.length() <= 0) goto L40;
        r0.zza = r11;
        r0.zzb = r12;
        r0.zzg = r145;
        r0.zzc = r13;
        r0.zzf = 1;
        r28 = this.zzc.zzz(r0);
        if (r28 != r15) goto L28;
    L7:
        r0 = new zzzi(this, r14);
        goto L8
    }

    private final Object zzw(GenerateContentRequest r11, StreamingCallback r12, KClass r13, boolean r14, Continuation r15) {
        if ((r15 instanceof zzzn) == false) goto L8;
        zzzn r0 = (zzzn) r15;
        int r1 = r0.zzf;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.zzf = r1 - Integer.MIN_VALUE;
    L7:
        zzzn r7 = r0;
        Object r152 = r7.zzd;
        Object r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r16 = r7.zzf;
        if (r16 == 0) goto L27;
        if (r16 == 1) goto L25;
        if (r16 == 2) goto L24;
        if (r16 == 3) goto L22;
        if (r16 == 4) goto L20;
        if (r16 != 5) goto L18;
        ResultKt.throwOnFailure(r152);
        return r152;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        ResultKt.throwOnFailure(r152);
        return r152;
    L22:
        boolean r112 = r7.zzc;
        String r122 = r7.zzh;
        KClass r132 = (KClass) r7.zzb;
        StreamingCallback r142 = (StreamingCallback) r7.zza;
        GenerateContentRequest r17 = r7.zzg;
        ResultKt.throwOnFailure(r152);
        boolean r6 = r112;
        KClass r5 = r132;
        StreamingCallback r3 = r142;
    L23:
        String r4 = r122;
        if (((Boolean) r152).booleanValue() == false) goto L68;
        r7.zzg = null;
        r7.zza = null;
        r7.zzb = null;
        r7.zzh = null;
        r7.zzf = 4;
        Object r10 = zzy(r17, r3, r4, r5, r6, r7);
        if (r10 == r02) goto L75;
        return r10;
    L75:
        return r02;
    L68:
        throw new GenAiException("Prefix Caching feature is not available.", null, -103);
    L24:
        boolean r113 = r7.zzc;
        KClass r123 = (KClass) r7.zzb;
        StreamingCallback r133 = (StreamingCallback) r7.zza;
        GenerateContentRequest r143 = r7.zzg;
        ResultKt.throwOnFailure(r152);
    L48:
        GenerateContentResponse r153 = (GenerateContentResponse) r152;
        if (r153 == null) goto L70;
        return r153;
    L70:
        if (r123 == null) goto L72;
        zzaah r102 = this.zzf;
    L73:
        zzaan r154 = new zzaan(r143, r123, r113);
        r7.zzg = null;
        r7.zza = null;
        r7.zzb = null;
        r7.zzh = null;
        r7.zzi = null;
        r7.zzf = 5;
        Object r103 = r102.zzI(r154, r133, r7);
        if (r103 == r02) goto L75;
        return r103;
    L72:
        r102 = this.zzc;
        goto L73
    L25:
        r14 = r7.zzc;
        String r114 = r7.zzi;
        String r124 = r7.zzh;
        KClass r134 = (KClass) r7.zzb;
        StreamingCallback r18 = (StreamingCallback) r7.zza;
        GenerateContentRequest r52 = r7.zzg;
        ResultKt.throwOnFailure(r152);
        String r19 = r114;
        r11 = r52;
        r5 = r134;
        int r135 = 2;
        String r42 = r124;
        r12 = r18;
    L26:
        boolean r62 = r14;
        if (((Boolean) r152).booleanValue() == false) goto L52;
        r7.zzg = r11;
        r7.zza = r12;
        r7.zzb = r5;
        r7.zzh = null;
        r7.zzi = null;
        r7.zzc = r62;
        r7.zzf = r135;
        GenerateContentRequest r2 = r11;
        StreamingCallback r32 = r12;
        r152 = zzx(r2, r32, r42, r5, r62, r7);
        r143 = r2;
        r133 = r32;
        if (r152 == r02) goto L75;
        r123 = r5;
        r113 = r62;
        goto L48
    L52:
        r14 = r62;
    L53:
        r133 = r12;
        r122 = r19;
        if (r122.length() <= 0) goto L69;
        r7.zzg = r11;
        r7.zza = r133;
        r7.zzb = r5;
        r7.zzh = r122;
        r7.zzi = null;
        r7.zzc = r14;
        r7.zzf = 3;
        r152 = zzA(r7);
        if (r152 == r02) goto L75;
        r17 = r11;
        r3 = r133;
        r6 = r14;
        goto L23
    L69:
        boolean r125 = r14;
        r143 = r11;
        r113 = r125;
        r123 = r5;
        goto L70
    L27:
        ResultKt.throwOnFailure(r152);
        PromptPrefix r155 = r11.getPromptPrefix();
        r19 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r155 == null) goto L31;
        String r156 = r155.getTextString();
        if (r156 == null) goto L31;
    L32:
        String r63 = r11.getCachedContextName();
        if (r63 == null) goto L37;
        r19 = r63;
    L37:
        if (r156.length() <= 0) goto L54;
        r7.zzg = r11;
        r7.zza = r12;
        r7.zzb = r13;
        r7.zzh = r156;
        r7.zzi = r19;
        r7.zzc = r14;
        r7.zzf = 1;
        Object r53 = zzA(r7);
        if (r53 == r02) goto L75;
        r5 = r13;
        r135 = 2;
        r42 = r156;
        r152 = r53;
        goto L26
    L54:
        r5 = r13;
    L31:
        r156 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r0 = new zzzn(this, r15);
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r24v0, types: [com.google.android.gms.internal.mlkit_genai_prompt.zzzy] */
    /* JADX WARN: Type inference failed for: r6v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    private final Object zzx(GenerateContentRequest r25, StreamingCallback r26, String r27, KClass r28, boolean r29, Continuation r30) {
        if ((r30 instanceof zzzq) == false) goto L7;
        zzzq r2 = (zzzq) r30;
        int r5 = r2.zzh;
        if ((r5 & Integer.MIN_VALUE) == 0) goto L7;
        r2.zzh = r5 - Integer.MIN_VALUE;
    L8:
        Object r0 = r2.zzf;
        Object r52 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r6 = r2.zzh;
        switch(r6) {
            case 0: goto L46;
            case 1: goto L41;
            case 2: goto L40;
            case 3: goto L39;
            case 4: goto L34;
            case 5: goto L31;
            case 6: goto L26;
            case 7: goto L24;
            case 8: goto L19;
            case 9: goto L14;
            case 10: goto L13;
            case 11: goto L12;
            default: goto L10;
        };
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        ResultKt.throwOnFailure(r0);
    L136:
        ((Boolean) r0).getClass();
        return null;
    L13:
        ResultKt.throwOnFailure(r0);
    L132:
        zzaab r02 = (zzaab) r0;
        if (r02 == null) goto L160;
        zzaag r1 = this.zzl;
        Context r3 = zzyt.zza(zza);
        r2.zzh = 11;
        r0 = zzaag.zzd(r1, r3, r02, r2);
        if (r0 != r52) goto L136;
    L138:
        return r52;
    L160:
        return null;
    L14:
        String r32 = (String) r2.zzb;
        String r4 = (String) r2.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L17
        return r0;
    L17:
        e = e;
    L126:
        Log.w("GenerativeModel", "Inference failed with prefix cache, retry without cache.", e);
        String r9 = r32;
        String r11 = r4;
    L122:
        int r8 = 1;
    L129:
        if (r8 == 0) goto L159;
        zzaag r03 = this.zzl;
        Context r33 = zzyt.zza(zza);
        r2.zza = null;
        r2.zzb = null;
        r2.zzi = null;
        r2.zzc = null;
        r2.zzj = null;
        r2.zzk = null;
        r2.zzh = 10;
        r0 = zzaag.zza(r03, r33, r11, r9, r2);
        if (r0 == r52) goto L138;
    L159:
        return null;
    L19:
        boolean r42 = r2.zzd;
        String r62 = r2.zzj;
        KClass r10 = (KClass) r2.zzc;
        String r112 = r2.zzi;
        StreamingCallback r12 = (StreamingCallback) r2.zzb;
        GenerateContentRequest r13 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L22
        r9 = r62;
        StreamingCallback r63 = r12;
        String r122 = r112;
    L158:
        ParcelFileDescriptor r14 = (ParcelFileDescriptor) r0;     // Catch: GenAiException -> L113
        if (r14 == null) goto L120;
        if (r10 == null) goto L115;
        zzaak r04 = this.zzg;     // Catch: GenAiException -> L113
    L116:
        zzaan r34 = new zzaan(r13, r10, r42);     // Catch: GenAiException -> L113
        zzaal r113 = new zzaal(r122, null, r14, 2, null);     // Catch: GenAiException -> L113
        r2.zza = r122;     // Catch: GenAiException -> L113
        r2.zzb = r9;     // Catch: GenAiException -> L113
        r2.zzi = null;     // Catch: GenAiException -> L113
        r2.zzc = null;     // Catch: GenAiException -> L113
        r2.zzj = null;     // Catch: GenAiException -> L113
        r2.zzh = 9;     // Catch: GenAiException -> L113
        Object r05 = zzaak.zzc(r04, r34, r113, r63, r2);     // Catch: GenAiException -> L113
        if (r05 == r52) goto L138;
        return r05;
    L115:
        r04 = this.zze;     // Catch: GenAiException -> L113
        goto L116
    L120:
        StringBuilder r43 = new StringBuilder(String.valueOf(r122).length() + 33);     // Catch: GenAiException -> L113
        r43.append("Failed to open cache for prefix: ");     // Catch: GenAiException -> L113
        r43.append(r122);     // Catch: GenAiException -> L113
        Log.w("GenerativeModel", r43.toString());     // Catch: GenAiException -> L113
        r11 = r122;
    L113:
        e = e;
        r32 = r9;
        r4 = r122;
    L22:
        e = e;
        r32 = r62;
        r4 = r112;
        goto L126
    L24:
        int r44 = r2.zze;
        boolean r64 = r2.zzd;
        zzaab r102 = r2.zzk;
        String r114 = r2.zzj;
        KClass r123 = (KClass) r2.zzc;
        String r132 = r2.zzi;
        StreamingCallback r142 = (StreamingCallback) r2.zzb;
        GenerateContentRequest r15 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);
        r8 = r44;
        r9 = r114;
        r4 = r132;
        r13 = r15;
        boolean r65 = r64;
    L25:
        zzaab r115 = r102;
        r10 = r123;
        StreamingCallback r124 = r142;
        if (((Boolean) r0).booleanValue() == true) goto L150;
        r11 = r4;
        goto L129
    L150:
        zzaag r06 = this.zzl;     // Catch: GenAiException -> L124
        Context r82 = zzyt.zza(zza);     // Catch: GenAiException -> L124
        r2.zza = r13;     // Catch: GenAiException -> L124
        r2.zzb = r124;     // Catch: GenAiException -> L124
        r2.zzi = r4;     // Catch: GenAiException -> L124
        r2.zzc = r10;     // Catch: GenAiException -> L124
        r2.zzj = r9;     // Catch: GenAiException -> L124
        r2.zzk = null;     // Catch: GenAiException -> L124
        r2.zzd = r65;     // Catch: GenAiException -> L124
        r2.zzh = 8;     // Catch: GenAiException -> L124
        r0 = zzaag.zzb(r06, r82, r115, r2);     // Catch: GenAiException -> L124
        if (r0 == r52) goto L138;
        r122 = r4;
        r42 = r65;
        r63 = r124;
    L124:
        e = e;
        r32 = r9;
        goto L126
    L26:
        int r45 = r2.zze;
        boolean r66 = r2.zzd;
        r102 = r2.zzk;
        String r116 = r2.zzj;
        r123 = (KClass) r2.zzc;
        String r133 = r2.zzi;
        r142 = (StreamingCallback) r2.zzb;
        GenerateContentRequest r152 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L29
        r6 = r66;
    L81:
        r102 = (zzaab) r0;     // Catch: GenAiException -> L29
        boolean r67 = r6;
    L83:
        r9 = r116;
        r11 = r133;
        r13 = r152;
        boolean r68 = r67;
    L100:
        if (r102 == null) goto L128;
        r2.zza = r13;
        r2.zzb = r142;
        r2.zzi = r11;
        r2.zzc = r123;
        r2.zzj = r9;
        r2.zzk = r102;
        r2.zzd = r68;
        r2.zze = r45;
        r2.zzh = 7;
        r0 = zzA(r2);
        if (r0 == r52) goto L138;
        r8 = r45;
        r4 = r11;
        r65 = r68;
        goto L25
    L128:
        r8 = r45;
        goto L129
    L31:
        r45 = r2.zze;
        boolean r69 = r2.zzd;
        r102 = r2.zzk;
        r116 = r2.zzj;
        r123 = (KClass) r2.zzc;
        r133 = r2.zzi;
        r142 = (StreamingCallback) r2.zzb;
        r152 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L29
        boolean r610 = r69;
    L79:
        zzaag r07 = this.zzl;     // Catch: GenAiException -> L29
        Context r83 = zzyt.zza(zza);     // Catch: GenAiException -> L29
        r2.zza = r152;     // Catch: GenAiException -> L29
        r2.zzb = r142;     // Catch: GenAiException -> L29
        r2.zzi = r133;     // Catch: GenAiException -> L29
        r2.zzc = r123;     // Catch: GenAiException -> L29
        r2.zzj = r116;     // Catch: GenAiException -> L29
        r2.zzk = r102;     // Catch: GenAiException -> L29
        r2.zzd = r610;     // Catch: GenAiException -> L29
        r2.zze = r45;     // Catch: GenAiException -> L29
        r2.zzh = 6;     // Catch: GenAiException -> L29
        r0 = zzaag.zza(r07, r83, r133, r116, r2);     // Catch: GenAiException -> L29
        r6 = r610;
        if (r0 == r52) goto L138;
    L34:
        int r611 = r2.zze;
        boolean r103 = r2.zzd;
        zzaab r117 = r2.zzk;
        String r125 = r2.zzj;
        KClass r134 = (KClass) r2.zzc;
        String r143 = r2.zzi;
        StreamingCallback r153 = (StreamingCallback) r2.zzb;
        GenerateContentRequest r92 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L37
        r152 = r92;
        StreamingCallback r93 = r153;
        String r18 = r143;
        int r144 = r611;
        boolean r612 = r103;
        r102 = r117;
        r116 = r125;
        r123 = r134;
    L156:
        ParcelFileDescriptor r19 = (ParcelFileDescriptor) r0;     // Catch: GenAiException -> L88
        if (r19 == null) goto L93;
        zzwl r08 = this.zzd;     // Catch: GenAiException -> L88
        PromptPrefix r46 = r152.getPromptPrefix();     // Catch: GenAiException -> L88
        if (r46 == null) goto L92;
        CreateCachedContextRequest r47 = CreateCachedContextRequestKt.createCachedContextRequest(r46.getTextString(), r46);     // Catch: GenAiException -> L88
        zzaal r17 = new zzaal(r18, r19, null, 4, null);     // Catch: GenAiException -> L88
        r133 = r18;
        r2.zza = r152;     // Catch: GenAiException -> L86
        r2.zzb = r93;     // Catch: GenAiException -> L86
        r2.zzi = r133;     // Catch: GenAiException -> L86
        r2.zzc = r123;     // Catch: GenAiException -> L86
        r2.zzj = r116;     // Catch: GenAiException -> L86
        r2.zzk = r102;     // Catch: GenAiException -> L86
        r2.zzd = r612;     // Catch: GenAiException -> L86
        r2.zze = r144;     // Catch: GenAiException -> L86
        boolean r16 = r612;
        r2.zzh = 5;     // Catch: GenAiException -> L84
        if (zzwl.zzc(r08, r47, r17, r2) == r52) goto L138;
        r45 = r144;
        r610 = r16;
        r142 = r93;
    L86:
        e = e;
        boolean r162 = r612;
    L96:
        r142 = r93;
        r6 = r162;
    L97:
        Log.w("GenerativeModel", "Failed to create cache for prefix: ".concat(String.valueOf(r133)), e);
        r9 = r116;
        r11 = r133;
        r13 = r152;
        boolean r613 = r6;
    L95:
        r45 = 1;
        r68 = r613;
        goto L100
    L92:
        throw new IllegalArgumentException("Prompt prefix is not set.");     // Catch: GenAiException -> L84
    L93:
        boolean r163 = r612;
        String r135 = r18;
        StringBuilder r614 = new StringBuilder(String.valueOf(r135).length() + 59);     // Catch: GenAiException -> L84
        r614.append("Failed to open file descriptor to create cache for prefix: ");     // Catch: GenAiException -> L84
        r614.append(r135);     // Catch: GenAiException -> L84
        Log.w("GenerativeModel", r614.toString());     // Catch: GenAiException -> L84
        r142 = r93;
        r9 = r116;
        r11 = r135;
        r13 = r152;
        r613 = r163;
    L88:
        e = e;
        r162 = r612;
        r133 = r18;
    L37:
        e = e;
        r6 = r103;
        r102 = r117;
        r116 = r125;
        r123 = r134;
        r133 = r143;
        r142 = r153;
        r152 = r92;
        goto L97
    L39:
        int r615 = r2.zze;
        boolean r94 = r2.zzd;
        r102 = r2.zzk;
        r116 = r2.zzj;
        r123 = (KClass) r2.zzc;
        r133 = r2.zzi;
        StreamingCallback r145 = (StreamingCallback) r2.zzb;
        r152 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);
        r144 = r615;
        r612 = r94;
        r93 = r145;
    L62:
        if (((Boolean) r0).booleanValue() == true) goto L147;
        r45 = r144;
        r142 = r93;
        r67 = r612;
        goto L83
    L147:
        zzaag r09 = this.zzl;     // Catch: GenAiException -> L86
        Context r84 = zzyt.zza(zza);     // Catch: GenAiException -> L86
        r2.zza = r152;     // Catch: GenAiException -> L86
        r2.zzb = r93;     // Catch: GenAiException -> L86
        r2.zzi = r133;     // Catch: GenAiException -> L86
        r2.zzc = r123;     // Catch: GenAiException -> L86
        r2.zzj = r116;     // Catch: GenAiException -> L86
        r2.zzk = r102;     // Catch: GenAiException -> L86
        r2.zzd = r612;     // Catch: GenAiException -> L86
        r2.zze = r144;     // Catch: GenAiException -> L86
        r162 = r612;
        r2.zzh = 4;     // Catch: GenAiException -> L84
        r0 = zzaag.zzc(r09, r84, r133, r116, r2);     // Catch: GenAiException -> L84
        if (r0 == r52) goto L138;
        r18 = r133;
        r612 = r162;
    L84:
        e = e;
        goto L96
    L40:
        boolean r616 = r2.zzd;
        r9 = r2.zzj;
        KClass r104 = (KClass) r2.zzc;
        r11 = r2.zzi;
        StreamingCallback r126 = (StreamingCallback) r2.zzb;
        r13 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);
        boolean r617 = r616;
    L56:
        zzaab r010 = (zzaab) r0;
        r144 = 0;
        if (r010 != null) goto L99;
        r2.zza = r13;
        r2.zzb = r126;
        r2.zzi = r11;
        r2.zzc = r104;
        r2.zzj = r9;
        r2.zzk = null;
        r2.zzd = r617;
        r2.zze = 0;
        r2.zzh = 3;
        Object r154 = zzz(r2);
        if (r154 == r52) goto L138;
        KClass r23 = r104;
        r102 = r010;
        r0 = r154;
        r152 = r13;
        r133 = r11;
        r116 = r9;
        r93 = r126;
        r123 = r23;
        r612 = r617;
        goto L62
    L99:
        r45 = 0;
        r142 = r126;
        r123 = r104;
        r102 = r010;
        r68 = r617;
        goto L100
    L41:
        boolean r618 = r2.zzd;
        KClass r95 = (KClass) r2.zzc;
        String r105 = r2.zzi;
        StreamingCallback r118 = (StreamingCallback) r2.zzb;
        GenerateContentRequest r127 = (GenerateContentRequest) r2.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L44
        r104 = r95;
        String r96 = r105;
        boolean r619 = r618;
    L51:
        String r011 = (String) r0;     // Catch: GenAiException -> L44
        zzaag r136 = this.zzl;
        Context r146 = zzyt.zza(zza);
        r2.zza = r127;
        r2.zzb = r118;
        r2.zzi = r96;
        r2.zzc = r104;
        r2.zzj = r011;
        r2.zzd = r619;
        r2.zzh = 2;
        Object r137 = zzaag.zza(r136, r146, r96, r011, r2);
        if (r137 == r52) goto L138;
        String r232 = r96;
        r9 = r011;
        r0 = r137;
        r13 = r127;
        r126 = r118;
        r11 = r232;
        r617 = r619;
        goto L56
    L46:
        ResultKt.throwOnFailure(r0);
        r2.zza = r25;     // Catch: GenAiException -> L44
        r2.zzb = r26;     // Catch: GenAiException -> L44
        r96 = r27;
        r2.zzi = r96;     // Catch: GenAiException -> L44
        r104 = r28;
        r2.zzc = r104;     // Catch: GenAiException -> L44
        r2.zzd = r29;     // Catch: GenAiException -> L44
        r2.zzh = 1;     // Catch: GenAiException -> L44
        Object r128 = this.zzc.zzz(r2);     // Catch: GenAiException -> L44
        if (r128 == r52) goto L138;
        r127 = r25;
        r0 = r128;
        r118 = r26;
        r619 = r29;
    L44:
        e = move-exception;
        Log.w("GenerativeModel", "Failed to get base model name, falling back to major processor", e);
        return null;
    L29:
        e = e;
    L7:
        r2 = new zzzq(this, r30);
        goto L8
    }

    private final Object zzy(GenerateContentRequest r18, StreamingCallback r19, String r20, KClass r21, boolean r22, Continuation r23) {
        if ((r23 instanceof zzzp) == false) goto L7;
        zzzp r2 = (zzzp) r23;
        int r3 = r2.zzf;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.zzf = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.zzd;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.zzf;
        if (r4 == 0) goto L21;
        if (r4 == 1) goto L20;
        if (r4 == 2) goto L19;
        if (r4 == 3) goto L18;
        if (r4 != 4) goto L16;
        ResultKt.throwOnFailure(r1);
        return r1;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        boolean r42 = r2.zzc;
        zzxt r7 = r2.zzi;
        KClass r8 = (KClass) r2.zzb;
        String r9 = r2.zzh;
        StreamingCallback r11 = (StreamingCallback) r2.zza;
        GenerateContentRequest r12 = r2.zzg;
        ResultKt.throwOnFailure(r1);
    L32:
        ParcelFileDescriptor r13 = (ParcelFileDescriptor) r1;
        if (r13 == null) goto L42;
        if (r8 == null) goto L36;
        zzaak r0 = this.zzg;
    L37:
        zzaan r5 = new zzaan(r12, r8, r42);
        zzaal r43 = new zzaal(r7.zzd(), null, r13, 2, null);
        r2.zzg = null;
        r2.zza = null;
        r2.zzh = null;
        r2.zzb = null;
        r2.zzi = null;
        r2.zzf = 4;
        Object r02 = zzaak.zzc(r0, r5, r43, r11, r2);
        if (r02 == r32) goto L45;
        return r02;
    L45:
        return r32;
    L36:
        r0 = this.zze;
        goto L37
    L42:
        throw new GenAiException("Failed to open cached context for name: ".concat(String.valueOf(r9)), null, -103);
    L19:
        r42 = r2.zzc;
        r8 = (KClass) r2.zzb;
        r9 = r2.zzh;
        r11 = (StreamingCallback) r2.zza;
        r12 = r2.zzg;
        ResultKt.throwOnFailure(r1);
    L27:
        zzxt r14 = (zzxt) r1;
        if (r14 == null) goto L44;
        zzxs r132 = this.zzk;
        Context r142 = zzyt.zza(zza);
        r2.zzg = r12;
        r2.zza = r11;
        r2.zzh = r9;
        r2.zzb = r8;
        r2.zzi = r14;
        r2.zzc = r42;
        r2.zzf = 3;
        Object r72 = zzxs.zzc(r132, r142, r14, r2);
        if (r72 == r32) goto L45;
        r7 = r14;
        r1 = r72;
        goto L32
    L44:
        throw new GenAiException("Cached context not found for name: ".concat(String.valueOf(r9)), null, -103);
    L20:
        r42 = r2.zzc;
        KClass r92 = (KClass) r2.zzb;
        String r112 = r2.zzh;
        StreamingCallback r122 = (StreamingCallback) r2.zza;
        GenerateContentRequest r133 = r2.zzg;
        ResultKt.throwOnFailure(r1);
    L24:
        zzxs r143 = this.zzk;
        Context r15 = zzyt.zza(zza);
        r2.zzg = r133;
        r2.zza = r122;
        r2.zzh = r112;
        r2.zzb = r92;
        r2.zzc = r42;
        r2.zzf = 2;
        r1 = zzxs.zza(r143, r15, r112, (String) r1, r2);
        if (r1 == r32) goto L45;
        r8 = r92;
        r9 = r112;
        r11 = r122;
        r12 = r133;
        goto L27
    L21:
        ResultKt.throwOnFailure(r1);
        r2.zzg = r18;
        r2.zza = r19;
        r112 = r20;
        r2.zzh = r112;
        r2.zzb = r21;
        r2.zzc = r22;
        r2.zzf = 1;
        Object r93 = this.zzc.zzz(r2);
        if (r93 == r32) goto L45;
        r133 = r18;
        r1 = r93;
        r92 = r21;
        r122 = r19;
        r42 = r22;
    L7:
        r2 = new zzzp(this, r23);
        goto L8
    }

    private final Object zzz(Continuation r5) {
        if ((r5 instanceof zzzd) == false) goto L7;
        zzzd r0 = (zzzd) r5;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        boolean r3 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r52);
    L17:
        if (((Number) r52).intValue() == 3) goto L21;
        r3 = false;
    L21:
        return Boxing.boxBoolean(r3);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r52);
        r0.zzc = 1;
        r52 = zzC(r0);
        if (r52 != r12) goto L17;
        return r12;
    L7:
        r0 = new zzzd(this, r5);
        goto L8
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Object checkStatus(Continuation<? super Integer> r1) {
        return zzyd.zza(this.zzj, r1);
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final void close() {
        this.zzd.close();
        this.zze.close();
        this.zzc.close();
        this.zzf.close();
        this.zzg.close();
    }

    public final Object countTokens(GenerateContentRequest r3, Continuation<? super CountTokensResponse> r4) {
        return zzv(r3, null, true, r4);
    }

    public final Flow<DownloadStatus> download() {
        zzzj r0 = new zzzj(this.zzj);
        return FlowKt.callbackFlow(new zzyo(this.zzi, r0, null));
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Object generateContent(GenerateContentRequest r7, StreamingCallback r8, Continuation<? super GenerateContentResponse> r9) {
        return zzw(r7, r8, null, true, r9);
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Object generateContent(GenerateContentRequest r7, Continuation<? super GenerateContentResponse> r8) {
        return zzw(r7, null, null, true, r8);
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Object getBaseModelName(Continuation<? super String> r1) {
        return this.zzc.zzz(r1);
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Caches getCaches() {
        return (Caches) this.zzm.getValue();
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Object isSystemPromptAvailable(Continuation<? super Boolean> r8) {
        if ((r8 instanceof zzzt) == false) goto L7;
        zzzt r0 = (zzzt) r8;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        boolean r5 = true;
        if (r2 == 0) goto L19;
        if (r2 == 1) goto L18;
        if (r2 != 2) goto L16;
        ResultKt.throwOnFailure(r82);     // Catch: Exception -> L14
    L31:
        if (zzE((String) r82) >= 3) goto L37;
    L34:
        r5 = false;
    L37:
        return Boxing.boxBoolean(r5);
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r82);
    L22:
        if (((Number) r82).intValue() == 3) goto L26;
        return Boxing.boxBoolean(false);
    L26:
        if (GenAiUtils.isSystemPromptSupported(MlKitContext.getInstance().getApplicationContext()) == false) goto L28;
        r0.zzc = 2;     // Catch: Exception -> L14
        r82 = this.zzc.zzz(r0);     // Catch: Exception -> L14
        if (r82 != r12) goto L31;
    L38:
        return r12;
    L28:
        return Boxing.boxBoolean(false);
    L19:
        ResultKt.throwOnFailure(r82);
        r0.zzc = 1;
        r82 = zzyd.zza(this.zzj, r0);
        if (r82 == r12) goto L38;
    L14:
        e = move-exception;
        Log.w("GenerativeModel", "Failed to get model name for capability check", e);
    L7:
        r0 = new zzzt(this, r8);
        goto L8
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final Object isThinkingModeAvailable(Continuation<? super Boolean> r7) {
        if ((r7 instanceof zzzu) == false) goto L7;
        zzzu r0 = (zzzu) r7;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        boolean r4 = true;
        if (r2 == 0) goto L19;
        if (r2 == 1) goto L18;
        if (r2 != 2) goto L16;
        ResultKt.throwOnFailure(r72);     // Catch: Exception -> L14
    L31:
        if (zzE((String) r72) >= 4) goto L38;
    L35:
        r4 = false;
    L38:
        return Boxing.boxBoolean(r4);
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r72);
    L22:
        if (((Number) r72).intValue() == 3) goto L26;
        return Boxing.boxBoolean(false);
    L26:
        if (GenAiUtils.isThinkingModeSupported(MlKitContext.getInstance().getApplicationContext()) == false) goto L28;
        r0.zzc = 2;     // Catch: Exception -> L14
        r72 = this.zzc.zzz(r0);     // Catch: Exception -> L14
        if (r72 != r12) goto L31;
    L39:
        return r12;
    L28:
        return Boxing.boxBoolean(false);
    L19:
        ResultKt.throwOnFailure(r72);
        r0.zzc = 1;
        r72 = zzyd.zza(this.zzj, r0);
        if (r72 == r12) goto L39;
    L14:
        e = move-exception;
        Log.w("GenerativeModel", "Failed to get model name for capability check", e);
    L7:
        r0 = new zzzu(this, r7);
        goto L8
    }

    public final Object warmup(Continuation<? super Unit> r6) {
        if ((r6 instanceof zzzw) == false) goto L7;
        zzzw r0 = (zzzw) r6;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r62);
    L22:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r62);
    L19:
        zzaah r5 = this.zzc;
        r0.zzc = 2;
        if (r5.zzE(r0) != r12) goto L22;
    L23:
        return r12;
    L16:
        ResultKt.throwOnFailure(r62);
        r0.zzc = 1;
        if (zzu(r0) != r12) goto L19;
    L7:
        r0 = new zzzw(this, r6);
        goto L8
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final ListenableFuture zza(DownloadCallback r2) {
        return CallbackToFutureAdapter.getFuture(new zzyu(this, r2));
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final ListenableFuture zzb() {
        return CallbackToFutureAdapter.getFuture(new zzyv(this));
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final ListenableFuture zzc(GenerateContentRequest r7) {
        zzzh r3 = new zzzh(this, r7, null);
        return ListenableFutureKt.future$default(this.zzi, null, null, r3, 3, null);
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final ListenableFuture zzd() {
        zzzr r3 = new zzzr(this, null);
        return ListenableFutureKt.future$default(this.zzi, null, null, r3, 3, null);
    }

    @Override // com.google.mlkit.genai.prompt.GenerativeModel
    public final ExecutorService zzf() {
        return this.zzh;
    }
}
