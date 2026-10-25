.class final Lcom/google/android/recaptcha/internal/zzda;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzdo;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzaka;

.field private synthetic zze:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzda;->zzb:Lcom/google/android/recaptcha/internal/zzdo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzda;->zzc:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzda;->zzd:Lcom/google/android/recaptcha/internal/zzaka;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzda;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzda;->zzb:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzda;->zzc:Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzda;->zzd:Lcom/google/android/recaptcha/internal/zzaka;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/recaptcha/internal/zzda;-><init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzda;->zze:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlp;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzda;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzda;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzda;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzda;->zza:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object p1

    .line 3
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzda;->zze:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v8, p0

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzda;->zze:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlp;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzda;->zzb:Lcom/google/android/recaptcha/internal/zzdo;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzda;->zzc:Ljava/lang/String;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzda;->zzd:Lcom/google/android/recaptcha/internal/zzaka;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzdo;->zze(Lcom/google/android/recaptcha/internal/zzdo;)Lcom/google/android/recaptcha/internal/zzct;

    move-result-object v3

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzda;->zze:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzda;->zza:I

    const-wide/32 v6, 0xea60

    move-object v8, p0

    invoke-virtual/range {v3 .. v8}, Lcom/google/android/recaptcha/internal/zzct;->zzc(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    const/4 p0, 0x0

    iput-object p0, v8, Lcom/google/android/recaptcha/internal/zzda;->zze:Ljava/lang/Object;

    const/4 p0, 0x2

    iput p0, v8, Lcom/google/android/recaptcha/internal/zzda;->zza:I

    .line 3
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzlp;->zza()Lcom/google/android/recaptcha/internal/zzmg;

    move-result-object p0

    invoke-virtual {p1, p0, v8}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_2

    goto :goto_1

    :cond_2
    return-object p0

    :cond_3
    :goto_1
    return-object v0
.end method
