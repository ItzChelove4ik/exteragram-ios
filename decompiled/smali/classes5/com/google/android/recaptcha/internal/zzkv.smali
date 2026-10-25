.class final Lcom/google/android/recaptcha/internal/zzkv;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzkw;

.field final synthetic zzc:J

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzala;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzkw;JLcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzc:J

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzd:Lcom/google/android/recaptcha/internal/zzala;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkv;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzc:J

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzd:Lcom/google/android/recaptcha/internal/zzala;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzkv;-><init>(Lcom/google/android/recaptcha/internal/zzkw;JLcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzkv;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzkv;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkv;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkv;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkv;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkv;->zze:Ljava/lang/Object;

    move-object v5, p1

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzb:Lcom/google/android/recaptcha/internal/zzkw;

    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzkw;->zze(Lcom/google/android/recaptcha/internal/zzkw;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object p1

    .line 2
    invoke-interface {p1}, Lkotlinx/coroutines/CoroutineScope;->getCoroutineContext()Lkotlin/coroutines/CoroutineContext;

    move-result-object p1

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzc:J

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzkv;->zzd:Lcom/google/android/recaptcha/internal/zzala;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzku;

    const/4 v7, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzku;-><init>(JLcom/google/android/recaptcha/internal/zzkw;Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzkv;->zza:I

    invoke-static {p1, v1, p0}, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
