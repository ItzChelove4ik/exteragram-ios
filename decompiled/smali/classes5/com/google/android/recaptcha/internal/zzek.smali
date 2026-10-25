.class final Lcom/google/android/recaptcha/internal/zzek;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzeo;

.field final synthetic zzc:Ljava/lang/String;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzmg;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzaka;

.field private synthetic zzf:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzeo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzek;->zzb:Lcom/google/android/recaptcha/internal/zzeo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzek;->zzc:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzek;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzek;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6

    new-instance v0, Lcom/google/android/recaptcha/internal/zzek;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzek;->zzb:Lcom/google/android/recaptcha/internal/zzeo;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzek;->zzc:Ljava/lang/String;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzek;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzek;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzek;-><init>(Lcom/google/android/recaptcha/internal/zzeo;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzaka;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzek;->zzf:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzek;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzek;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzek;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 13

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzek;->zza:I

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    if-eqz v1, :cond_0

    goto/16 :goto_2

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzek;->zzf:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lkotlinx/coroutines/CoroutineScope;

    new-instance v7, Ljava/util/ArrayList;

    .line 2
    invoke-direct {v7}, Ljava/util/ArrayList;-><init>()V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzek;->zzb:Lcom/google/android/recaptcha/internal/zzeo;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzek;->zzc:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzeo;->zzh()Ljava/util/Map;

    move-result-object v2

    .line 3
    invoke-interface {v2, v5, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v9, Ljava/util/ArrayList;

    .line 4
    invoke-direct {v9}, Ljava/util/ArrayList;-><init>()V

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzeo;->zzj(Lcom/google/android/recaptcha/internal/zzeo;)Ljava/util/List;

    move-result-object p1

    .line 5
    check-cast p1, Ljava/lang/Iterable;

    new-instance v10, Ljava/util/ArrayList;

    .line 6
    invoke-direct {v10}, Ljava/util/ArrayList;-><init>()V

    .line 7
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Lcom/google/android/recaptcha/internal/zzev;

    .line 5
    invoke-interface {v3}, Lcom/google/android/recaptcha/internal/zzev;->zzb()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 7
    invoke-interface {v10, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 5
    :cond_2
    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result p1

    const/4 v11, 0x0

    move v2, v11

    :goto_1
    if-ge v2, p1, :cond_3

    invoke-virtual {v10, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v12, v2, 0x1

    move-object v4, v3

    check-cast v4, Lcom/google/android/recaptcha/internal/zzev;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzek;->zzd:Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzek;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    .line 9
    new-instance v2, Lcom/google/android/recaptcha/internal/zzej;

    const/4 v8, 0x0

    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzej;-><init>(Lcom/google/android/recaptcha/internal/zzmg;Lcom/google/android/recaptcha/internal/zzev;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaka;Ljava/util/List;Lkotlin/coroutines/Continuation;)V

    move-object v8, v5

    const/4 v5, 0x3

    const/4 v6, 0x0

    move-object v4, v2

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v2

    .line 10
    invoke-interface {v9, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move-object v5, v8

    move v2, v12

    goto :goto_1

    .line 11
    :cond_3
    new-array p1, v11, [Lkotlinx/coroutines/Job;

    .line 12
    invoke-interface {v9, p1}, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p1

    .line 11
    check-cast p1, [Lkotlinx/coroutines/Job;

    array-length v1, p1

    invoke-static {p1, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p1

    check-cast p1, [Lkotlinx/coroutines/Job;

    const/4 v1, 0x1

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzek;->zza:I

    invoke-static {p1, p0}, Lkotlinx/coroutines/AwaitKt;->joinAll([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_4

    return-object v0

    .line 13
    :cond_4
    :goto_2
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzek;->zzb:Lcom/google/android/recaptcha/internal/zzeo;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzek;->zzc:Ljava/lang/String;

    invoke-static {p1, p0}, Lcom/google/android/recaptcha/internal/zzeo;->zzk(Lcom/google/android/recaptcha/internal/zzeo;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaku;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->box-impl(Ljava/lang/Object;)Lkotlin/Result;

    move-result-object p0

    return-object p0
.end method
