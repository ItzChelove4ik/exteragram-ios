.class public final Lcom/google/android/recaptcha/internal/zzjj;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/RecaptchaTasksClient;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzkn;

.field private final zzb:Ljava/lang/String;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzmd;

.field private final zzd:Lkotlinx/coroutines/sync/Mutex;

.field private final zze:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzkn;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmd;Lcom/google/android/recaptcha/internal/zzhu;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjj;->zza:Lcom/google/android/recaptcha/internal/zzkn;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzjj;->zzb:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzjj;->zzc:Lcom/google/android/recaptcha/internal/zzmd;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzjj;->zze:Lcom/google/android/recaptcha/internal/zzhu;

    const/4 p1, 0x1

    const/4 p2, 0x0

    const/4 p3, 0x0

    invoke-static {p3, p1, p2}, Lkotlinx/coroutines/sync/MutexKt;->Mutex$default(ZILjava/lang/Object;)Lkotlinx/coroutines/sync/Mutex;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzjj;->zzd:Lkotlinx/coroutines/sync/Mutex;

    return-void
.end method

.method public static final synthetic zzc(Lcom/google/android/recaptcha/internal/zzjj;Lcom/google/android/recaptcha/RecaptchaAction;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzjj;->zzf(Lcom/google/android/recaptcha/RecaptchaAction;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzjj;)Lkotlinx/coroutines/sync/Mutex;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjj;->zzd:Lkotlinx/coroutines/sync/Mutex;

    return-object p0
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzjj;)Lcom/google/android/recaptcha/internal/zzmd;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjj;->zzc:Lcom/google/android/recaptcha/internal/zzmd;

    return-object p0
.end method

.method private final zzf(Lcom/google/android/recaptcha/RecaptchaAction;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 10

    instance-of v0, p4, Lcom/google/android/recaptcha/internal/zzjd;

    if-eqz v0, :cond_0

    move-object v0, p4

    check-cast v0, Lcom/google/android/recaptcha/internal/zzjd;

    iget v1, v0, Lcom/google/android/recaptcha/internal/zzjd;->zzc:I

    const/high16 v2, -0x80000000

    and-int v3, v1, v2

    if-eqz v3, :cond_0

    sub-int/2addr v1, v2

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzjd;->zzc:I

    goto :goto_0

    .line 1
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzjd;

    invoke-direct {v0, p0, p4}, Lcom/google/android/recaptcha/internal/zzjd;-><init>(Lcom/google/android/recaptcha/internal/zzjj;Lkotlin/coroutines/Continuation;)V

    .line 0
    :goto_0
    iget-object p4, v0, Lcom/google/android/recaptcha/internal/zzjd;->zza:Ljava/lang/Object;

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzjd;->zzc:I

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    if-ne v2, v3, :cond_1

    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    :cond_1
    const-string p0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_2
    invoke-static {p4}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p4, p0, Lcom/google/android/recaptcha/internal/zzjj;->zze:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {p4}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object p4

    .line 2
    invoke-interface {p4}, Lkotlinx/coroutines/CoroutineScope;->getCoroutineContext()Lkotlin/coroutines/CoroutineContext;

    move-result-object p4

    new-instance v4, Lcom/google/android/recaptcha/internal/zzjg;

    const/4 v9, 0x0

    move-object v5, p0

    move-object v8, p1

    move-wide v6, p2

    invoke-direct/range {v4 .. v9}, Lcom/google/android/recaptcha/internal/zzjg;-><init>(Lcom/google/android/recaptcha/internal/zzjj;JLcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)V

    iput v3, v0, Lcom/google/android/recaptcha/internal/zzjd;->zzc:I

    invoke-static {p4, v4, v0}, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p4

    if-ne p4, v1, :cond_3

    return-object v1

    :cond_3
    :goto_1
    check-cast p4, Lkotlin/Result;

    invoke-virtual {p4}, Lkotlin/Result;->unbox-impl()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final executeTask(Lcom/google/android/recaptcha/RecaptchaAction;)Lcom/google/android/gms/tasks/Task;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/android/recaptcha/RecaptchaAction;",
            ")",
            "Lcom/google/android/gms/tasks/Task<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    const-wide/16 v0, 0x2710

    .line 1
    invoke-virtual {p0, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzjj;->executeTask(Lcom/google/android/recaptcha/RecaptchaAction;J)Lcom/google/android/gms/tasks/Task;

    move-result-object p0

    return-object p0
.end method

.method public final executeTask(Lcom/google/android/recaptcha/RecaptchaAction;J)Lcom/google/android/gms/tasks/Task;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/android/recaptcha/RecaptchaAction;",
            "J)",
            "Lcom/google/android/gms/tasks/Task<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 2
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzjj;->zze:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhu;->zza()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    new-instance v2, Lcom/google/android/recaptcha/internal/zzjh;

    const/4 v7, 0x0

    move-object v3, p0

    move-object v4, p1

    move-wide v5, p2

    invoke-direct/range {v2 .. v7}, Lcom/google/android/recaptcha/internal/zzjh;-><init>(Lcom/google/android/recaptcha/internal/zzjj;Lcom/google/android/recaptcha/RecaptchaAction;JLkotlin/coroutines/Continuation;)V

    const/4 v5, 0x3

    const/4 v6, 0x0

    move-object v4, v2

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzhz;->zza(Lkotlinx/coroutines/Deferred;)Lcom/google/android/gms/tasks/Task;

    move-result-object p0

    return-object p0
.end method

.method public final zza()Lcom/google/android/recaptcha/internal/zzkn;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjj;->zza:Lcom/google/android/recaptcha/internal/zzkn;

    return-object p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzjj;->zzb:Ljava/lang/String;

    return-object p0
.end method
