.class public final Lcom/google/android/recaptcha/internal/zzlq;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzln;


# direct methods
.method public synthetic constructor <init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzln;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    const/4 p2, 0x0

    .line 1
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzlm;->zze()Lcom/google/android/recaptcha/internal/zzlm;

    move-result-object p3

    if-nez p3, :cond_0

    new-instance p3, Lcom/google/android/recaptcha/internal/zzlm;

    .line 2
    invoke-direct {p3, p1, p2}, Lcom/google/android/recaptcha/internal/zzlm;-><init>(Landroid/content/Context;Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 3
    :cond_0
    invoke-static {p3}, Lcom/google/android/recaptcha/internal/zzlm;->zzf(Lcom/google/android/recaptcha/internal/zzlm;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-object p2, p3

    .line 1
    :catch_0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzlq;->zza:Lcom/google/android/recaptcha/internal/zzln;

    return-void
.end method


# virtual methods
.method public final zza(Ljava/util/List;)V
    .locals 2

    .line 1
    const-class v0, Lcom/google/android/recaptcha/internal/zzlm;

    monitor-enter v0

    :try_start_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlq;->zza:Lcom/google/android/recaptcha/internal/zzln;

    if-eqz p0, :cond_1

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzlo;

    .line 2
    invoke-interface {p0, v1}, Lcom/google/android/recaptcha/internal/zzln;->zza(Lcom/google/android/recaptcha/internal/zzlo;)J

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 3
    :cond_0
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzln;->zzb()I

    move-result p1

    add-int/lit16 p1, p1, -0x1f4

    if-lez p1, :cond_1

    .line 4
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzln;->zzc()Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    invoke-static {v1, p1}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object p1

    .line 5
    invoke-interface {p0, p1}, Lcom/google/android/recaptcha/internal/zzln;->zzd(Ljava/util/List;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 6
    :catch_0
    :cond_1
    :try_start_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0

    throw p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzamk;)V
    .locals 4

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzlo;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaec;->zzw()[B

    move-result-object p1

    array-length v1, p1

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrv;->zzi()Lcom/google/android/recaptcha/internal/zzrv;

    move-result-object v2

    const/4 v3, 0x0

    .line 3
    invoke-virtual {v2, p1, v3, v1}, Lcom/google/android/recaptcha/internal/zzrv;->zzg([BII)Ljava/lang/String;

    move-result-object p1

    .line 4
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-direct {v0, p1, v1, v2, v3}, Lcom/google/android/recaptcha/internal/zzlo;-><init>(Ljava/lang/String;JI)V

    .line 1
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzlq;->zza(Ljava/util/List;)V

    return-void
.end method

.method public final zzc()Ljava/util/List;
    .locals 1

    .line 1
    const-class v0, Lcom/google/android/recaptcha/internal/zzlm;

    monitor-enter v0

    :try_start_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlq;->zza:Lcom/google/android/recaptcha/internal/zzln;

    if-eqz p0, :cond_0

    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzln;->zzc()Ljava/util/List;

    move-result-object p0

    if-nez p0, :cond_1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    :cond_0
    :goto_0
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    .line 2
    :catch_0
    :try_start_1
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 3
    :cond_1
    :goto_1
    monitor-exit v0

    return-object p0

    :goto_2
    monitor-exit v0

    throw p0
.end method

.method public final zzd(Ljava/util/List;)V
    .locals 1

    .line 1
    const-class v0, Lcom/google/android/recaptcha/internal/zzlm;

    monitor-enter v0

    :try_start_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlq;->zza:Lcom/google/android/recaptcha/internal/zzln;

    if-eqz p0, :cond_0

    invoke-interface {p0, p1}, Lcom/google/android/recaptcha/internal/zzln;->zzd(Ljava/util/List;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 2
    :catch_0
    :try_start_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 3
    :cond_0
    :goto_0
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0

    throw p0
.end method
