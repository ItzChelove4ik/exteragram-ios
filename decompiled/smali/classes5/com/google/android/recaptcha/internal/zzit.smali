.class final Lcom/google/android/recaptcha/internal/zzit;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zziv;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzdp;

.field final synthetic zzf:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zzg:J


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zziv;Lcom/google/android/recaptcha/internal/zzdp;Lcom/google/android/recaptcha/internal/zzakg;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzit;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzit;->zzd:Lcom/google/android/recaptcha/internal/zziv;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzit;->zze:Lcom/google/android/recaptcha/internal/zzdp;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzit;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    iput-wide p5, p0, Lcom/google/android/recaptcha/internal/zzit;->zzg:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p7}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 8

    new-instance v0, Lcom/google/android/recaptcha/internal/zzit;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzit;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzit;->zzd:Lcom/google/android/recaptcha/internal/zziv;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzit;->zze:Lcom/google/android/recaptcha/internal/zzdp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzit;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzit;->zzg:J

    move-object v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/google/android/recaptcha/internal/zzit;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zziv;Lcom/google/android/recaptcha/internal/zzdp;Lcom/google/android/recaptcha/internal/zzakg;JLkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzit;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzit;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzit;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzit;->zzb:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 4
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzit;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzit;->zzc:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzit;->zzd:Lcom/google/android/recaptcha/internal/zziv;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzit;->zze:Lcom/google/android/recaptcha/internal/zzdp;

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzit;->zzf:Lcom/google/android/recaptcha/internal/zzakg;

    iget-wide v5, p0, Lcom/google/android/recaptcha/internal/zzit;->zzg:J

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzit;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzit;->zzb:I

    .line 3
    new-instance v3, Lcom/google/android/recaptcha/internal/zzis;

    const/4 v9, 0x0

    invoke-direct/range {v3 .. v9}, Lcom/google/android/recaptcha/internal/zzis;-><init>(Lcom/google/android/recaptcha/internal/zzdp;JLcom/google/android/recaptcha/internal/zzakg;Lcom/google/android/recaptcha/internal/zziv;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmc;

    invoke-direct {p1, v3}, Lcom/google/android/recaptcha/internal/zzmc;-><init>(Lkotlin/jvm/functions/Function2;)V

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    const/4 v2, 0x0

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzit;->zza:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzit;->zzb:I

    .line 4
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_2

    .line 5
    :cond_2
    :goto_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_3
    :goto_2
    return-object v0
.end method
