.class final Lcom/google/android/recaptcha/internal/zzjn;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzjt;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zzd:Ljava/lang/String;

.field final synthetic zze:J

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zziw;

.field final synthetic zzg:Lcom/google/android/recaptcha/internal/zzmd;

.field private synthetic zzh:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjt;Lcom/google/android/recaptcha/internal/zzmg;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzd:Ljava/lang/String;

    iput-wide p4, p0, Lcom/google/android/recaptcha/internal/zzjn;->zze:J

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzf:Lcom/google/android/recaptcha/internal/zziw;

    iput-object p7, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzg:Lcom/google/android/recaptcha/internal/zzmd;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p8}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 9

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjn;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzd:Ljava/lang/String;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzjn;->zze:J

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzf:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzg:Lcom/google/android/recaptcha/internal/zzmd;

    move-object v8, p2

    invoke-direct/range {v0 .. v8}, Lcom/google/android/recaptcha/internal/zzjn;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Lcom/google/android/recaptcha/internal/zzmg;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjn;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjn;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjn;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 14

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zza:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_3

    .line 8
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_2

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    .line 2
    invoke-static {v7}, Lcom/google/android/recaptcha/internal/zzjt;->zzd(Lcom/google/android/recaptcha/internal/zzjt;)Lcom/google/android/recaptcha/internal/zzjj;

    move-result-object p1

    if-eqz p1, :cond_4

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzd:Ljava/lang/String;

    .line 3
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zzjn;->zza:I

    .line 4
    new-instance v4, Lcom/google/android/recaptcha/internal/zzjs;

    invoke-direct {v4, p1, v2, v5}, Lcom/google/android/recaptcha/internal/zzjs;-><init>(Lcom/google/android/recaptcha/internal/zzjj;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 v2, 0x2d

    invoke-direct {p1, v2, v4, v5}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p1, v0, :cond_6

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzjn;->zza:I

    .line 5
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_6

    .line 3
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzjj;

    return-object p1

    :cond_4
    iget-object v10, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzd:Ljava/lang/String;

    iget-wide v8, p0, Lcom/google/android/recaptcha/internal/zzjn;->zze:J

    iget-object v11, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzf:Lcom/google/android/recaptcha/internal/zziw;

    iget-object v12, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzg:Lcom/google/android/recaptcha/internal/zzmd;

    .line 6
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjn;->zza:I

    .line 7
    new-instance v6, Lcom/google/android/recaptcha/internal/zzjr;

    const/4 v13, 0x0

    invoke-direct/range {v6 .. v13}, Lcom/google/android/recaptcha/internal/zzjr;-><init>(Lcom/google/android/recaptcha/internal/zzjt;JLjava/lang/String;Lcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v6}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_6

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zzjn;->zzh:Ljava/lang/Object;

    const/4 v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzjn;->zza:I

    .line 8
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object v1

    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_5

    goto :goto_4

    .line 1
    :cond_5
    :goto_3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzjj;

    return-object p1

    :cond_6
    :goto_4
    return-object v0
.end method
