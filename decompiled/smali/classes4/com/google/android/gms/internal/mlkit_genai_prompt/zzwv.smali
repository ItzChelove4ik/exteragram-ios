.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field synthetic zza:Ljava/lang/Object;

.field final synthetic zzb:Ljava/lang/String;

.field final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

.field final synthetic zzd:Landroid/content/Context;

.field final synthetic zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

.field final synthetic zzf:Lkotlin/jvm/functions/Function1;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzb:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzd:Landroid/content/Context;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzf:Lkotlin/jvm/functions/Function1;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p6}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 7

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzb:Ljava/lang/String;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzd:Landroid/content/Context;

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    iget-object v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzf:Lkotlin/jvm/functions/Function1;

    move-object v6, p2

    invoke-direct/range {v0 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;-><init>(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zza:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Ljava/util/Map;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zza:Ljava/lang/Object;

    check-cast p1, Ljava/util/Map;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzb:Ljava/lang/String;

    .line 2
    invoke-interface {p1, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    const-string v2, "CacheFileManager"

    if-eqz v1, :cond_0

    .line 3
    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    new-instance v4, Ljava/lang/StringBuilder;

    add-int/lit8 v3, v3, 0x1f

    invoke-direct {v4, v3}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v3, "Overwriting cache with name \'"

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "\'."

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzd:Landroid/content/Context;

    .line 4
    invoke-static {v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;)Z

    :cond_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    .line 5
    invoke-interface {p1, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzf:Lkotlin/jvm/functions/Function1;

    if-eqz v0, :cond_3

    .line 6
    invoke-interface {v0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Collection;

    .line 7
    invoke-interface {v0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_1
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    .line 8
    invoke-interface {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;->zza()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;->zza()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 9
    invoke-interface {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;->zza()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    const-string v4, "Eviction policy attempts to delete cache being written: "

    invoke-virtual {v4, v3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 10
    :cond_2
    invoke-interface {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;->zza()Ljava/lang/String;

    move-result-object v3

    invoke-interface {p1, v3}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    if-eqz v3, :cond_1

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;->zzd:Landroid/content/Context;

    .line 11
    invoke-static {v4, v5, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;)Z

    goto :goto_0

    .line 12
    :cond_3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
