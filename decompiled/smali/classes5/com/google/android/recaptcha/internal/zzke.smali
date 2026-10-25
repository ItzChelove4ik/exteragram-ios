.class public final Lcom/google/android/recaptcha/internal/zzke;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzkn;


# instance fields
.field private zza:Lkotlinx/coroutines/CompletableDeferred;

.field private zzb:Lcom/google/android/recaptcha/internal/zzhg;

.field private zzc:Lcom/google/android/recaptcha/internal/zzakg;

.field private zzd:Lcom/google/android/recaptcha/internal/zzkm;

.field private final zze:Lcom/google/android/recaptcha/internal/zziz;

.field private final zzf:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zziz;Lcom/google/android/recaptcha/internal/zzhl;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zzf:Lcom/google/android/recaptcha/internal/zzhu;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzke;->zze:Lcom/google/android/recaptcha/internal/zziz;

    const/4 p1, 0x0

    const/4 p2, 0x1

    invoke-static {p1, p2, p1}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zza:Lkotlinx/coroutines/CompletableDeferred;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzkm;->zza()Lcom/google/android/recaptcha/internal/zzkl;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zzd:Lcom/google/android/recaptcha/internal/zzkm;

    return-void
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzke;->zzp(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzke;Ljava/lang/Exception;)Z
    .locals 3

    .line 1
    instance-of p0, p1, Lcom/google/android/recaptcha/internal/zzhg;

    const/4 v0, 0x1

    if-eqz p0, :cond_2

    check-cast p1, Lcom/google/android/recaptcha/internal/zzhg;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zza()Lcom/google/android/recaptcha/internal/zzhf;

    move-result-object p0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzd:Lcom/google/android/recaptcha/internal/zzhf;

    invoke-static {p0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    const/4 v1, 0x0

    if-nez p0, :cond_1

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zza()Lcom/google/android/recaptcha/internal/zzhf;

    move-result-object p0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zze:Lcom/google/android/recaptcha/internal/zzhf;

    invoke-static {p0, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zza()Lcom/google/android/recaptcha/internal/zzhf;

    move-result-object p0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzhf;->zzf:Lcom/google/android/recaptcha/internal/zzhf;

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    return v1

    :cond_0
    return v0

    :cond_1
    return v1

    :cond_2
    return v0
.end method

.method public static final synthetic zzf(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzhg;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzke;->zzb:Lcom/google/android/recaptcha/internal/zzhg;

    return-object p0
.end method

.method public static final synthetic zzg(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzkm;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zzd:Lcom/google/android/recaptcha/internal/zzkm;

    return-void
.end method

.method public static final synthetic zzh(Lcom/google/android/recaptcha/internal/zzke;Lkotlinx/coroutines/CompletableDeferred;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zza:Lkotlinx/coroutines/CompletableDeferred;

    return-void
.end method

.method public static final synthetic zzi(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzakg;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zzc:Lcom/google/android/recaptcha/internal/zzakg;

    return-void
.end method

.method public static final synthetic zzj(Lcom/google/android/recaptcha/internal/zzke;Lcom/google/android/recaptcha/internal/zzhg;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzke;->zzb:Lcom/google/android/recaptcha/internal/zzhg;

    return-void
.end method

.method public static final synthetic zzk(Lcom/google/android/recaptcha/internal/zzke;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzke;->zzq(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzl(Lcom/google/android/recaptcha/internal/zzke;)Lkotlinx/coroutines/CompletableDeferred;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzke;->zza:Lkotlinx/coroutines/CompletableDeferred;

    return-object p0
.end method

.method public static final synthetic zzm(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzakg;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzke;->zzc:Lcom/google/android/recaptcha/internal/zzakg;

    return-object p0
.end method

.method public static final synthetic zzn(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zziz;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzke;->zze:Lcom/google/android/recaptcha/internal/zziz;

    return-object p0
.end method

.method public static final synthetic zzo(Lcom/google/android/recaptcha/internal/zzke;)Lcom/google/android/recaptcha/internal/zzhu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzke;->zzf:Lcom/google/android/recaptcha/internal/zzhu;

    return-object p0
.end method

.method private final zzp(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance p3, Lcom/google/android/recaptcha/internal/zzkd;

    const/4 v0, 0x0

    invoke-direct {p3, p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzkd;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method

.method private final zzq(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 4

    instance-of v0, p2, Lcom/google/android/recaptcha/internal/zzjv;

    if-eqz v0, :cond_0

    move-object v0, p2

    check-cast v0, Lcom/google/android/recaptcha/internal/zzjv;

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzjv;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzjv;->zzc:I

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzjv;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzjv;-><init>(Lcom/google/android/recaptcha/internal/zzke;Lkotlin/coroutines/Continuation;)V

    :goto_0
    iget-object p0, v0, Lcom/google/android/recaptcha/internal/zzjv;->zza:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object p2

    iget v1, v0, Lcom/google/android/recaptcha/internal/zzjv;->zzc:I

    const/4 v2, 0x1

    if-eqz v1, :cond_2

    if-ne v1, v2, :cond_1

    .line 5
    iget-object p1, v0, Lcom/google/android/recaptcha/internal/zzjv;->zzd:Lcom/google/android/recaptcha/internal/zzhk;

    .line 1
    invoke-static {p0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p0}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzhk;

    .line 2
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzhk;-><init>()V

    iput-object p0, v0, Lcom/google/android/recaptcha/internal/zzjv;->zzd:Lcom/google/android/recaptcha/internal/zzhk;

    iput v2, v0, Lcom/google/android/recaptcha/internal/zzjv;->zzc:I

    .line 3
    invoke-interface {p1, v0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, p2, :cond_3

    move-object p1, p0

    .line 4
    :goto_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhk;->zzb()V

    sget-object p0, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    .line 5
    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzhk;->zza(Ljava/util/concurrent/TimeUnit;)J

    move-result-wide p0

    invoke-static {p0, p1}, Lkotlin/coroutines/jvm/internal/Boxing;->boxLong(J)Ljava/lang/Long;

    move-result-object p0

    return-object p0

    :cond_3
    return-object p2
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzkm;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzke;->zzd:Lcom/google/android/recaptcha/internal/zzkm;

    return-object p0
.end method

.method public final zzb(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzke;->zzp(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzc(Ljava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 8

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzju;

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v1, p0

    move-object v4, p1

    move-object v5, p2

    move-wide v2, p3

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzju;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLjava/lang/String;Lcom/google/android/recaptcha/RecaptchaAction;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    new-instance p0, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    return-object p0
.end method
