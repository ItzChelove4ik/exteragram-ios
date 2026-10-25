.class final Lcom/google/android/recaptcha/internal/zzix;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zziz;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:Ljava/lang/String;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zzf:J

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zziz;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;JLjava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzix;->zzb:Lcom/google/android/recaptcha/internal/zziz;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzix;->zzc:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzix;->zzd:Ljava/lang/String;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzix;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iput-wide p5, p0, Lcom/google/android/recaptcha/internal/zzix;->zzf:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p8}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 9

    new-instance v0, Lcom/google/android/recaptcha/internal/zzix;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzix;->zzb:Lcom/google/android/recaptcha/internal/zziz;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzix;->zzc:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzix;->zzd:Ljava/lang/String;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzix;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzix;->zzf:J

    const/4 v7, 0x0

    move-object v8, p2

    invoke-direct/range {v0 .. v8}, Lcom/google/android/recaptcha/internal/zzix;-><init>(Lcom/google/android/recaptcha/internal/zziz;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;JLjava/lang/String;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzix;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzix;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzix;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzix;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzix;->zza:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p1

    .line 5
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzix;->zzg:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzix;->zzg:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzix;->zzb:Lcom/google/android/recaptcha/internal/zziz;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zziz;->zzf(Lcom/google/android/recaptcha/internal/zziz;)Lcom/google/android/recaptcha/internal/zzdp;

    move-result-object v7

    if-eqz v7, :cond_4

    .line 2
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zziz;->zzg(Lcom/google/android/recaptcha/internal/zziz;)Lcom/google/android/recaptcha/internal/zzio;

    move-result-object v6

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzix;->zzc:Ljava/lang/String;

    iget-object v10, p0, Lcom/google/android/recaptcha/internal/zzix;->zzd:Ljava/lang/String;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzix;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzix;->zzf:J

    .line 3
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzix;->zzg:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzix;->zza:I

    .line 4
    new-instance v3, Lcom/google/android/recaptcha/internal/zzim;

    const/4 v11, 0x0

    const/4 v12, 0x0

    invoke-direct/range {v3 .. v12}, Lcom/google/android/recaptcha/internal/zzim;-><init>(JLcom/google/android/recaptcha/internal/zzio;Lcom/google/android/recaptcha/internal/zzdp;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzakg;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v3}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    const/4 v2, 0x0

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzix;->zzg:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzix;->zza:I

    .line 5
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_1

    :cond_2
    return-object p0

    :cond_3
    :goto_1
    return-object v0

    .line 2
    :cond_4
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzC:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1
.end method
