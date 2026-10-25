.class final Lcom/google/android/recaptcha/internal/zzdz;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzea;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzea;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdz;->zza:Lcom/google/android/recaptcha/internal/zzea;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 0

    new-instance p1, Lcom/google/android/recaptcha/internal/zzdz;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdz;->zza:Lcom/google/android/recaptcha/internal/zzea;

    invoke-direct {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzdz;-><init>(Lcom/google/android/recaptcha/internal/zzea;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzdz;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzdz;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzdz;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 8

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzdz;->zza:Lcom/google/android/recaptcha/internal/zzea;

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzea;->zze(Lcom/google/android/recaptcha/internal/zzea;)Landroid/content/ContentResolver;

    move-result-object v0

    .line 2
    const-string p1, "content://com.google.android.gsf.gservices"

    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    const-string p1, "android_id"

    filled-new-array {p1}, [Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    .line 3
    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p1

    const-string v0, ""

    if-eqz p1, :cond_1

    .line 4
    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p1}, Landroid/database/Cursor;->getColumnCount()I

    move-result v1

    const/4 v2, 0x2

    if-ge v1, v2, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x1

    .line 5
    :try_start_0
    invoke-interface {p1, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 6
    :catch_0
    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    .line 7
    :cond_1
    :goto_0
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result p1

    if-nez p1, :cond_3

    sget p0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 p1, 0x22

    if-le p0, p1, :cond_2

    .line 8
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzP:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    goto :goto_1

    .line 9
    :cond_2
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzQ:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v0, v1

    .line 10
    :goto_1
    throw v0

    :cond_3
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdz;->zza:Lcom/google/android/recaptcha/internal/zzea;

    .line 12
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamb;->zzb()Lcom/google/android/recaptcha/internal/zzaly;

    move-result-object p1

    .line 13
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zza()Lcom/google/android/recaptcha/internal/zzalz;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzalz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalz;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 14
    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzaly;->zzc(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzaly;

    .line 15
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzamb;

    .line 11
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzew;->zza(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzamb;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object p0

    return-object p0
.end method
