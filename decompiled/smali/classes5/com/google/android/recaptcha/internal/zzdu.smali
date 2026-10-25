.class final Lcom/google/android/recaptcha/internal/zzdu;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzdv;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzalg;

.field private synthetic zzd:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdv;JLcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzb:Lcom/google/android/recaptcha/internal/zzdv;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzc:Lcom/google/android/recaptcha/internal/zzalg;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzc:Lcom/google/android/recaptcha/internal/zzalg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzdu;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzb:Lcom/google/android/recaptcha/internal/zzdv;

    const-wide/16 v2, 0x2710

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzdu;-><init>(Lcom/google/android/recaptcha/internal/zzdv;JLcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzdu;->zzd:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdu;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdu;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdu;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzdu;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzd:Ljava/lang/Object;

    move-object v5, p1

    check-cast v5, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzb:Lcom/google/android/recaptcha/internal/zzdv;

    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzdv;->zzc(Lcom/google/android/recaptcha/internal/zzdv;)Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object p1

    .line 2
    invoke-interface {p1}, Lkotlinx/coroutines/CoroutineScope;->getCoroutineContext()Lkotlin/coroutines/CoroutineContext;

    move-result-object p1

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzdu;->zzc:Lcom/google/android/recaptcha/internal/zzalg;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzdt;

    const-wide/16 v2, 0x2710

    const/4 v7, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzdt;-><init>(JLcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzlp;Lcom/google/android/recaptcha/internal/zzalg;Lkotlin/coroutines/Continuation;)V

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzdu;->zza:I

    invoke-static {p1, v1, p0}, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
