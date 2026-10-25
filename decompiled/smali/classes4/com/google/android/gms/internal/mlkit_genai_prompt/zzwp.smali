.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

.field final synthetic zzc:Landroid/content/Context;

.field final synthetic zzd:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzc:Landroid/content/Context;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzd:Ljava/lang/String;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzc:Landroid/content/Context;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzd:Ljava/lang/String;

    invoke-direct {p1, v0, v1, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    return-object p1

    :cond_0
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzc:Landroid/content/Context;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zzd:Ljava/lang/String;

    .line 2
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwo;

    const/4 v4, 0x0

    invoke-direct {v3, v2, p1, v1, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwo;-><init>(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/coroutines/Continuation;)V

    const/4 v2, 0x1

    iput v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwp;->zza:I

    invoke-static {p1, v1, v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzk(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_1

    return-object v0

    :cond_1
    return-object p0
.end method
