.class final Lcom/google/android/recaptcha/internal/zzjp;
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

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzc:Ljava/lang/String;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzd:J

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzjp;->zze:Lcom/google/android/recaptcha/internal/zziw;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjp;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzc:Ljava/lang/String;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzd:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzjp;->zze:Lcom/google/android/recaptcha/internal/zziw;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzjp;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzjp;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmd;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjp;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzjp;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzjp;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzjp;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzf:Ljava/lang/Object;

    move-object v7, p1

    check-cast v7, Lcom/google/android/recaptcha/internal/zzmd;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzc:Ljava/lang/String;

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzjp;->zzd:J

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzjp;->zze:Lcom/google/android/recaptcha/internal/zziw;

    .line 2
    new-instance v1, Lcom/google/android/recaptcha/internal/zzjo;

    const/4 v8, 0x0

    invoke-direct/range {v1 .. v8}, Lcom/google/android/recaptcha/internal/zzjo;-><init>(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lcom/google/android/recaptcha/internal/zzmd;Lkotlin/coroutines/Continuation;)V

    const/4 p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzjp;->zza:I

    invoke-virtual {v7, v1, p0}, Lcom/google/android/recaptcha/internal/zzmd;->zzc(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
