.class final Lcom/google/android/recaptcha/internal/zzjo;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzjt;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:J

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zziw;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzmd;

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzc:Ljava/lang/String;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzd:J

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzjo;->zze:Lcom/google/android/recaptcha/internal/zziw;

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzf:Lcom/google/android/recaptcha/internal/zzmd;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjo;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzc:Ljava/lang/String;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzd:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjo;->zze:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzf:Lcom/google/android/recaptcha/internal/zzmd;

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzjo;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzjo;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjo;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjo;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjo;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjo;->zza:I

    if-eqz v1, :cond_0

    :try_start_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :cond_0
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzg:Ljava/lang/Object;

    move-object v3, p1

    check-cast v3, Lcom/google/android/recaptcha/internal/zzmg;

    .line 2
    :try_start_1
    new-instance v1, Lcom/google/android/recaptcha/internal/zzjn;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzc:Ljava/lang/String;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzd:J

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzjo;->zze:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzjo;->zzf:Lcom/google/android/recaptcha/internal/zzmd;

    const/4 v9, 0x0

    invoke-direct/range {v1 .. v9}, Lcom/google/android/recaptcha/internal/zzjn;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Lcom/google/android/recaptcha/internal/zzmg;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzjo;->zza:I

    const/4 p1, 0x6

    const/4 v2, 0x0

    .line 3
    invoke-virtual {v3, p1, v2, v1, p0}, Lcom/google/android/recaptcha/internal/zzmg;->zze(ILjava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_1

    return-object v0

    .line 2
    :cond_1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzjj;
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzhg; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    return-object p1

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zza:Lcom/google/android/recaptcha/internal/zzhe;

    .line 5
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 6
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 7
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhg;->zzd()Lcom/google/android/recaptcha/RecaptchaException;

    move-result-object p0

    throw p0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 8
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzhg;->zzd()Lcom/google/android/recaptcha/RecaptchaException;

    move-result-object p0

    throw p0
.end method
