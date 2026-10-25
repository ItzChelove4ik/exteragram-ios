.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:I

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

.field final synthetic zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

.field final synthetic zzf:Landroid/content/Context;

.field final synthetic zzg:Lkotlin/jvm/functions/Function1;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzf:Landroid/content/Context;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzg:Lkotlin/jvm/functions/Function1;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzf:Landroid/content/Context;

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzg:Lkotlin/jvm/functions/Function1;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzc:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v0, :cond_1

    if-eq v0, v2, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 4
    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzb:Ljava/lang/Object;

    .line 1
    check-cast v0, Ljava/io/File;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zza:Ljava/lang/Object;

    check-cast v2, Ljava/lang/String;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object v8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;

    iget-object v6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzf:Landroid/content/Context;

    invoke-interface {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;->zza()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;->zzb()Ljava/lang/String;

    move-result-object p1

    new-instance v0, Ljava/io/File;

    .line 2
    invoke-static {v6, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;)Ljava/io/File;

    move-result-object v4

    invoke-direct {v0, v4, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iget-object v9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzg:Lkotlin/jvm/functions/Function1;

    .line 3
    new-instance v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;

    const/4 v10, 0x0

    invoke-direct/range {v4 .. v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwv;-><init>(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V

    iput-object v5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zza:Ljava/lang/Object;

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzb:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzc:I

    invoke-static {v6, v7, v4, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzk(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v1, :cond_3

    move-object v2, v5

    :goto_0
    const/high16 p1, 0x28000000

    .line 4
    :try_start_0
    invoke-static {v0, p1}, Landroid/os/ParcelFileDescriptor;->open(Ljava/io/File;I)Landroid/os/ParcelFileDescriptor;

    move-result-object p0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    move-object p1, v0

    .line 1
    const-string v0, "CacheFileManager"

    const-string v4, "Failed to open cache for writing"

    .line 5
    invoke-static {v0, v4, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzf:Landroid/content/Context;

    .line 6
    iput-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zza:Ljava/lang/Object;

    iput-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzb:Ljava/lang/Object;

    const/4 v4, 0x2

    iput v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzww;->zzc:I

    invoke-static {p1, v0, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;->zzj(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxg;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v1, :cond_2

    goto :goto_2

    :cond_2
    :goto_1
    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    return-object v3

    :cond_3
    :goto_2
    return-object v1
.end method
