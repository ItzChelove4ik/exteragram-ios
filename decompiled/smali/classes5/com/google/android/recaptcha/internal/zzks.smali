.class final Lcom/google/android/recaptcha/internal/zzks;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field zza:I

.field final synthetic zzb:Ljava/lang/String;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzaku;

.field final synthetic zzd:Lcom/google/android/recaptcha/internal/zzakg;

.field final synthetic zze:Lcom/google/android/recaptcha/internal/zzkw;

.field final synthetic zzf:J

.field private synthetic zzg:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaku;Lcom/google/android/recaptcha/internal/zzakg;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzkw;JLkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzks;->zzb:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzks;->zzc:Lcom/google/android/recaptcha/internal/zzaku;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzks;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzks;->zze:Lcom/google/android/recaptcha/internal/zzkw;

    iput-wide p6, p0, Lcom/google/android/recaptcha/internal/zzks;->zzf:J

    const/4 p1, 0x2

    invoke-direct {p0, p1, p8}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 9

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzks;->zze:Lcom/google/android/recaptcha/internal/zzkw;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzks;->zzf:J

    new-instance v0, Lcom/google/android/recaptcha/internal/zzks;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzks;->zzb:Ljava/lang/String;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzks;->zzc:Lcom/google/android/recaptcha/internal/zzaku;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzks;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    const/4 v4, 0x0

    move-object v8, p2

    invoke-direct/range {v0 .. v8}, Lcom/google/android/recaptcha/internal/zzks;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaku;Lcom/google/android/recaptcha/internal/zzakg;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzkw;JLkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/google/android/recaptcha/internal/zzks;->zzg:Ljava/lang/Object;

    return-object v0
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzks;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzks;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzks;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 10

    .line 1
    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzks;->zza:I

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    if-eq v1, v3, :cond_0

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_1

    .line 12
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzks;->zzg:Ljava/lang/Object;

    .line 1
    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzks;->zzg:Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzmg;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzks;->zzb:Ljava/lang/String;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzks;->zzc:Lcom/google/android/recaptcha/internal/zzaku;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzks;->zzd:Lcom/google/android/recaptcha/internal/zzakg;

    .line 2
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v6

    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzmd;->zza()Ljava/lang/String;

    move-result-object v6

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzala;->zza()Lcom/google/android/recaptcha/internal/zzakz;

    move-result-object v7

    .line 4
    invoke-virtual {v7, v6}, Lcom/google/android/recaptcha/internal/zzakz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakz;

    .line 5
    invoke-virtual {v7, p1}, Lcom/google/android/recaptcha/internal/zzakz;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakz;

    .line 6
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzakg;->zzb()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v7, p1}, Lcom/google/android/recaptcha/internal/zzakz;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakz;

    .line 7
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzakg;->zza()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v7, p1}, Lcom/google/android/recaptcha/internal/zzakz;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakz;

    .line 8
    invoke-virtual {v7, v4}, Lcom/google/android/recaptcha/internal/zzakz;->zze(Lcom/google/android/recaptcha/internal/zzaku;)Lcom/google/android/recaptcha/internal/zzakz;

    .line 9
    invoke-virtual {v7}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    move-object v8, p1

    check-cast v8, Lcom/google/android/recaptcha/internal/zzala;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzks;->zze:Lcom/google/android/recaptcha/internal/zzkw;

    iget-wide v6, p0, Lcom/google/android/recaptcha/internal/zzks;->zzf:J

    .line 10
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzks;->zzg:Ljava/lang/Object;

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzks;->zza:I

    .line 11
    new-instance v4, Lcom/google/android/recaptcha/internal/zzkv;

    const/4 v9, 0x0

    invoke-direct/range {v4 .. v9}, Lcom/google/android/recaptcha/internal/zzkv;-><init>(Lcom/google/android/recaptcha/internal/zzkw;JLcom/google/android/recaptcha/internal/zzala;Lkotlin/coroutines/Continuation;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 v3, 0x1c

    invoke-direct {p1, v3, v4, v2}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    if-eq p1, v0, :cond_3

    .line 1
    :goto_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzmb;

    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzks;->zzg:Ljava/lang/Object;

    const/4 v2, 0x2

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzks;->zza:I

    .line 12
    invoke-virtual {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_2

    goto :goto_2

    .line 1
    :cond_2
    :goto_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzalc;

    return-object p1

    :cond_3
    :goto_2
    return-object v0
.end method
