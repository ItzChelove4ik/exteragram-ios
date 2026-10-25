.class final Lcom/google/android/recaptcha/internal/zzkb;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzke;

.field final synthetic zzd:J

.field final synthetic zze:Lkotlinx/coroutines/CompletableDeferred;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzke;JLkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzb:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzd:J

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzkb;->zze:Lkotlinx/coroutines/CompletableDeferred;

    const/4 p1, 0x1

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkb;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzb:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzd:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzkb;->zze:Lkotlinx/coroutines/CompletableDeferred;

    move-object v6, p1

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzkb;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzke;JLkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkb;->create(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzkb;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzkb;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzkb;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzb:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzc:Lcom/google/android/recaptcha/internal/zzke;

    iget-wide v3, p0, Lcom/google/android/recaptcha/internal/zzkb;->zzd:J

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzkb;->zze:Lkotlinx/coroutines/CompletableDeferred;

    .line 2
    new-instance v1, Lcom/google/android/recaptcha/internal/zzka;

    const/4 v6, 0x0

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzka;-><init>(Lcom/google/android/recaptcha/internal/zzke;JLkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzkb;->zza:I

    const/16 v2, 0x29

    const/4 v3, 0x0

    .line 3
    invoke-virtual {p1, v2, v3, v1, p0}, Lcom/google/android/recaptcha/internal/zzmg;->zze(ILjava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
