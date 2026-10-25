.class final Lcom/google/android/recaptcha/internal/zzed;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzee;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzee;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzed;->zza:Lcom/google/android/recaptcha/internal/zzee;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 0

    new-instance p1, Lcom/google/android/recaptcha/internal/zzed;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzed;->zza:Lcom/google/android/recaptcha/internal/zzee;

    invoke-direct {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzed;-><init>(Lcom/google/android/recaptcha/internal/zzee;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzed;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzed;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzed;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 20

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static/range {p1 .. p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamb;->zzb()Lcom/google/android/recaptcha/internal/zzaly;

    move-result-object v0

    move-object/from16 v1, p0

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zzed;->zza:Lcom/google/android/recaptcha/internal/zzee;

    sget-object v2, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    .line 3
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v3

    sget-object v2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    .line 4
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v4

    sget-object v2, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    .line 5
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v5

    sget-object v2, Landroid/os/Build;->HARDWARE:Ljava/lang/String;

    .line 6
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v6

    sget-object v2, Landroid/os/Build;->FINGERPRINT:Ljava/lang/String;

    .line 7
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v7

    sget-object v2, Landroid/os/Build;->PRODUCT:Ljava/lang/String;

    .line 8
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v8

    sget-object v2, Landroid/os/Build;->BOARD:Ljava/lang/String;

    .line 9
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v9

    sget-object v2, Landroid/os/Build;->BRAND:Ljava/lang/String;

    .line 10
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v10

    sget-object v11, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;

    const/16 v18, 0x38

    const/16 v19, 0x0

    .line 11
    const-string v12, ","

    const-string v13, "["

    const-string v14, "]"

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    invoke-static/range {v11 .. v19}, Lkotlin/collections/ArraysKt;->joinToString$default([Ljava/lang/Object;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    .line 12
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v11

    sget-wide v12, Landroid/os/Build;->TIME:J

    .line 13
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zza()Lcom/google/android/recaptcha/internal/zzalz;

    move-result-object v2

    invoke-virtual {v2, v12, v13}, Lcom/google/android/recaptcha/internal/zzalz;->zzb(J)Lcom/google/android/recaptcha/internal/zzalz;

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v2

    move-object v12, v2

    check-cast v12, Lcom/google/android/recaptcha/internal/zzama;

    sget-object v2, Landroid/os/Build;->ID:Ljava/lang/String;

    .line 14
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v13

    sget-object v2, Landroid/os/Build;->BOOTLOADER:Ljava/lang/String;

    .line 15
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v14

    sget-object v2, Landroid/os/Build;->DISPLAY:Ljava/lang/String;

    .line 16
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v15

    sget-object v2, Landroid/os/Build;->TYPE:Ljava/lang/String;

    .line 17
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v16

    sget-object v2, Landroid/os/Build;->TAGS:Ljava/lang/String;

    .line 18
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzee;->zze(Lcom/google/android/recaptcha/internal/zzee;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v17

    filled-new-array/range {v3 .. v17}, [Lcom/google/android/recaptcha/internal/zzama;

    move-result-object v2

    .line 19
    invoke-static {v2}, Lkotlin/collections/CollectionsKt;->listOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v2

    check-cast v2, Ljava/lang/Iterable;

    .line 20
    invoke-virtual {v0, v2}, Lcom/google/android/recaptcha/internal/zzaly;->zzc(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzaly;

    .line 21
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzamb;

    invoke-static {v1, v0}, Lcom/google/android/recaptcha/internal/zzew;->zza(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzamb;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object v0

    return-object v0
.end method
