.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field synthetic zzb:Ljava/lang/Object;

.field final synthetic zzc:Lkotlin/jvm/functions/Function2;

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

.field final synthetic zze:Landroid/content/Context;


# direct methods
.method public constructor <init>(Lkotlin/jvm/functions/Function2;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzc:Lkotlin/jvm/functions/Function2;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zze:Landroid/content/Context;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 3

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzc:Lkotlin/jvm/functions/Function2;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zze:Landroid/content/Context;

    invoke-direct {v0, v1, v2, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;-><init>(Lkotlin/jvm/functions/Function2;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzb:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Ljava/util/Map;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zza:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    .line 3
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzb:Ljava/lang/Object;

    if-eq v1, v2, :cond_0

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    return-object v3

    :cond_0
    check-cast v3, Ljava/util/Map;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzb:Ljava/lang/Object;

    move-object v3, p1

    check-cast v3, Ljava/util/Map;

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzc:Lkotlin/jvm/functions/Function2;

    iput-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzb:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zza:I

    .line 2
    invoke-interface {p1, v3, p0}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_2

    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zze:Landroid/content/Context;

    .line 3
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zzb:Ljava/lang/Object;

    const/4 v4, 0x2

    iput v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxd;->zza:I

    invoke-static {v1, v2, v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzn(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-eq p0, v0, :cond_2

    return-object p1

    :cond_2
    return-object v0
.end method
