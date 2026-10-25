.class final Lcom/google/android/recaptcha/internal/zzeb;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzec;

.field private synthetic zzb:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzec;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzeb;->zza:Lcom/google/android/recaptcha/internal/zzec;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzeb;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzeb;->zza:Lcom/google/android/recaptcha/internal/zzec;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzeb;-><init>(Lcom/google/android/recaptcha/internal/zzec;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzeb;->zzb:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzeb;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzeb;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzeb;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzeb;->zzb:Ljava/lang/Object;

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzajw;->zza()Lcom/google/android/recaptcha/internal/zzajv;

    move-result-object v1

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzajv;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzeb;->zza:Lcom/google/android/recaptcha/internal/zzec;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzec;->zze(Lcom/google/android/recaptcha/internal/zzec;)Landroid/app/Application;

    move-result-object v2

    .line 4
    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzajv;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzec;->zzf(Lcom/google/android/recaptcha/internal/zzec;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object v2

    .line 5
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzhr;->zzd()I

    move-result v2

    add-int/lit8 v2, v2, -0x2

    const/4 v3, 0x1

    if-eq v2, v3, :cond_0

    const/4 v2, 0x4

    goto :goto_0

    :cond_0
    const/4 v2, 0x3

    :goto_0
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzajv;->zzu(I)Lcom/google/android/recaptcha/internal/zzajv;

    const-string v2, "18.9.2"

    .line 6
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzajv;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    .line 7
    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzajv;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzmd;->zzb()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzajv;->zze(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzec;->zzf(Lcom/google/android/recaptcha/internal/zzec;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object p1

    .line 9
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhr;->zza()Z

    move-result p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzajv;->zzf(Z)Lcom/google/android/recaptcha/internal/zzajv;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzec;->zzf(Lcom/google/android/recaptcha/internal/zzec;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object p1

    .line 10
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhr;->zzb()Z

    move-result p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzajv;->zzg(Z)Lcom/google/android/recaptcha/internal/zzajv;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzec;->zzf(Lcom/google/android/recaptcha/internal/zzec;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object p1

    .line 11
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhr;->zzc()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzajv;->zzh(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    .line 12
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzgp;->zzb()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzajv;->zzt(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajv;

    .line 13
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzajw;

    .line 14
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzw()[B

    move-result-object p1

    array-length v0, p1

    .line 15
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrv;->zzj()Lcom/google/android/recaptcha/internal/zzrv;

    move-result-object v1

    const/4 v2, 0x0

    .line 16
    invoke-virtual {v1, p1, v2, v0}, Lcom/google/android/recaptcha/internal/zzrv;->zzg([BII)Ljava/lang/String;

    move-result-object p1

    .line 18
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamb;->zzb()Lcom/google/android/recaptcha/internal/zzaly;

    move-result-object v0

    .line 19
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zza()Lcom/google/android/recaptcha/internal/zzalz;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzalz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalz;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzama;

    .line 20
    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaly;->zzc(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzaly;

    .line 21
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzamb;

    .line 17
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzew;->zza(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzamb;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object p0

    return-object p0
.end method
