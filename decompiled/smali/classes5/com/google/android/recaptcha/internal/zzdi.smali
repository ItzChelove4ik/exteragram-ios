.class final Lcom/google/android/recaptcha/internal/zzdi;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzlp;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzdo;

.field final synthetic zzd:J

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzakg;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdo;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzb:Lcom/google/android/recaptcha/internal/zzlp;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzc:Lcom/google/android/recaptcha/internal/zzdo;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzd:J

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzdi;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzdi;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzb:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzc:Lcom/google/android/recaptcha/internal/zzdo;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzd:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzdi;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzdi;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdo;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzdi;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdi;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdi;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdi;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zza:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 5
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzf:Ljava/lang/Object;

    .line 1
    check-cast v1, Lkotlinx/coroutines/Deferred;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzf:Ljava/lang/Object;

    move-object v3, p1

    check-cast v3, Lkotlinx/coroutines/CoroutineScope;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzb:Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzc:Lcom/google/android/recaptcha/internal/zzdo;

    iget-wide v7, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzd:J

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzdi;->zze:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    new-instance v4, Lcom/google/android/recaptcha/internal/zzdh;

    const/4 v10, 0x0

    invoke-direct/range {v4 .. v10}, Lcom/google/android/recaptcha/internal/zzdh;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdo;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    move-object p1, v5

    move-object v1, v6

    move-object v11, v9

    move-wide v9, v7

    const/4 v7, 0x3

    const/4 v8, 0x0

    move-object v6, v4

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-static/range {v3 .. v8}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object v12

    .line 3
    new-instance v4, Lcom/google/android/recaptcha/internal/zzdg;

    move-wide v7, v9

    const/4 v10, 0x0

    move-object v5, p1

    move-object v6, v1

    move-object v9, v11

    invoke-direct/range {v4 .. v10}, Lcom/google/android/recaptcha/internal/zzdg;-><init>(Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzdo;JLcom/google/android/recaptcha/internal/zzakg;Lkotlin/coroutines/Continuation;)V

    const/4 v7, 0x3

    const/4 v8, 0x0

    move-object v6, v4

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-static/range {v3 .. v8}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object v1

    .line 4
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzf:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzdi;->zza:I

    invoke-interface {v12, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    :goto_0
    const/4 p1, 0x0

    .line 5
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zzf:Ljava/lang/Object;

    const/4 p1, 0x2

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzdi;->zza:I

    invoke-interface {v1, p0}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_2

    .line 6
    :cond_2
    :goto_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_3
    :goto_2
    return-object v0
.end method
