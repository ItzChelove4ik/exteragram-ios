.class final Lcom/google/android/recaptcha/internal/zzie;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzif;

.field private synthetic zzd:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzif;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzie;->zzc:Lcom/google/android/recaptcha/internal/zzif;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzie;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzie;->zzc:Lcom/google/android/recaptcha/internal/zzif;

    invoke-direct {v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzie;-><init>(Lcom/google/android/recaptcha/internal/zzif;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzie;->zzd:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzie;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzie;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzie;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 5

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzie;->zzb:I

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v2, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzie;->zzd:Ljava/lang/Object;

    check-cast v0, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 3
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzie;->zza:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzie;->zzd:Ljava/lang/Object;

    check-cast v2, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    move-object v4, v2

    move-object v2, v1

    move-object v1, v4

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzie;->zzd:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzie;->zzc:Lcom/google/android/recaptcha/internal/zzif;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzif;->zzc(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzon;

    move-result-object p1

    .line 2
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzie;->zzd:Ljava/lang/Object;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzie;->zza:Ljava/lang/Object;

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzie;->zzb:I

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzon;->zzg(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-eq p1, v0, :cond_5

    move-object v2, v1

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmc;

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzie;->zzd:Ljava/lang/Object;

    const/4 v3, 0x0

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzie;->zza:Ljava/lang/Object;

    const/4 v3, 0x2

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzie;->zzb:I

    .line 3
    invoke-virtual {p1, v2, p0}, Lcom/google/android/recaptcha/internal/zzmc;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_2

    goto/16 :goto_2

    :cond_2
    move-object v0, v1

    .line 2
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zza()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_4

    :cond_3
    const-string p1, ""

    .line 4
    :cond_4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamf;->zza()Lcom/google/android/recaptcha/internal/zzame;

    move-result-object v1

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzame;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzie;->zzc:Lcom/google/android/recaptcha/internal/zzif;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzif;->zzd(Lcom/google/android/recaptcha/internal/zzif;)Landroid/content/Context;

    move-result-object v2

    .line 6
    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzame;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzif;->zze(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object v2

    .line 7
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzhr;->zzd()I

    move-result v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzame;->zzv(I)Lcom/google/android/recaptcha/internal/zzame;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzif;->zza()Ljava/lang/String;

    move-result-object v2

    .line 8
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzame;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzmd;->zzb()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzame;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzif;->zzb()I

    move-result v0

    .line 10
    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzame;->zze(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    .line 11
    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzame;->zzf(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzif;->zze(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object p1

    .line 12
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhr;->zza()Z

    move-result p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzame;->zzg(Z)Lcom/google/android/recaptcha/internal/zzame;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzif;->zze(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object p1

    .line 13
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhr;->zzb()Z

    move-result p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzame;->zzh(Z)Lcom/google/android/recaptcha/internal/zzame;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzif;->zze(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzhr;

    move-result-object p0

    .line 14
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzhr;->zzc()Ljava/lang/String;

    move-result-object p0

    .line 15
    invoke-virtual {v1, p0}, Lcom/google/android/recaptcha/internal/zzame;->zzt(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    .line 16
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzgp;->zzb()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Lcom/google/android/recaptcha/internal/zzame;->zzu(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzame;

    .line 17
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    return-object p0

    :cond_5
    :goto_2
    return-object v0
.end method
