.class final Lcom/google/android/recaptcha/internal/zziy;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zziz;

.field final synthetic zzd:J

.field final synthetic zze:J

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zziz;JJLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzc:Lcom/google/android/recaptcha/internal/zziz;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zziy;->zzd:J

    iput-wide p4, p0, Lcom/google/android/recaptcha/internal/zziy;->zze:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zziy;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzc:Lcom/google/android/recaptcha/internal/zziz;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zziy;->zzd:J

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zziy;->zze:J

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zziy;-><init>(Lcom/google/android/recaptcha/internal/zziz;JJLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zziy;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zziy;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zziy;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzb:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v1, :cond_3

    if-eq v1, v4, :cond_2

    if-eq v1, v3, :cond_1

    if-eq v1, v2, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzakg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p0

    .line 9
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzakg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_2

    :cond_1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_2
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zza:Ljava/lang/Object;

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    check-cast v4, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzc:Lcom/google/android/recaptcha/internal/zziz;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zziy;->zzd:J

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zziz;->zzc(Lcom/google/android/recaptcha/internal/zziz;)Lcom/google/android/recaptcha/internal/zziv;

    move-result-object p1

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zza:Ljava/lang/Object;

    iput v4, p0, Lcom/google/android/recaptcha/internal/zziy;->zzb:I

    .line 3
    new-instance v4, Lcom/google/android/recaptcha/internal/zzir;

    invoke-direct {v4, p1, v6, v7, v5}, Lcom/google/android/recaptcha/internal/zzir;-><init>(Lcom/google/android/recaptcha/internal/zziv;JLkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v4}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_4

    move-object v4, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zziy;->zza:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zziy;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_4

    move-object v1, v4

    :goto_1
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zziy;->zzc:Lcom/google/android/recaptcha/internal/zziz;

    .line 1
    move-object v11, p1

    check-cast v11, Lcom/google/android/recaptcha/internal/zzakg;

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zziz;->zzd(Lcom/google/android/recaptcha/internal/zziz;)Lcom/google/android/recaptcha/internal/zzlg;

    move-result-object p1

    .line 5
    invoke-virtual {v11}, Lcom/google/android/recaptcha/internal/zzakg;->zzk()Z

    move-result v4

    invoke-interface {p1, v4}, Lcom/google/android/recaptcha/internal/zzlg;->zza(Z)Lcom/google/android/recaptcha/internal/zzdp;

    move-result-object v10

    .line 6
    invoke-static {v3, v10}, Lcom/google/android/recaptcha/internal/zziz;->zze(Lcom/google/android/recaptcha/internal/zziz;Lcom/google/android/recaptcha/internal/zzdp;)V

    iget-wide v7, p0, Lcom/google/android/recaptcha/internal/zziy;->zze:J

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zziz;->zzc(Lcom/google/android/recaptcha/internal/zziz;)Lcom/google/android/recaptcha/internal/zziv;

    move-result-object v9

    .line 7
    iput-object v11, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zziy;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zziy;->zzb:I

    .line 8
    new-instance v6, Lcom/google/android/recaptcha/internal/zziu;

    const/4 v12, 0x0

    invoke-direct/range {v6 .. v12}, Lcom/google/android/recaptcha/internal/zziu;-><init>(JLcom/google/android/recaptcha/internal/zziv;Lcom/google/android/recaptcha/internal/zzdp;Lcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v6}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_4

    move-object v2, v11

    .line 1
    :goto_2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zziy;->zzf:Ljava/lang/Object;

    iput-object v5, p0, Lcom/google/android/recaptcha/internal/zziy;->zza:Ljava/lang/Object;

    const/4 v3, 0x4

    iput v3, p0, Lcom/google/android/recaptcha/internal/zziy;->zzb:I

    .line 9
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v0, :cond_4

    return-object v2

    :cond_4
    return-object v0
.end method
