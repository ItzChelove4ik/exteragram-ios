.class public final Lcom/google/android/gms/internal/location/zzec;
.super Lcom/google/android/gms/common/internal/GmsClient;
.source "SourceFile"


# instance fields
.field private final zzf:Landroidx/collection/SimpleArrayMap;

.field private final zzg:Landroidx/collection/SimpleArrayMap;

.field private final zzh:Landroidx/collection/SimpleArrayMap;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/os/Looper;Lcom/google/android/gms/common/internal/ClientSettings;Lcom/google/android/gms/common/api/internal/ConnectionCallbacks;Lcom/google/android/gms/common/api/internal/OnConnectionFailedListener;)V
    .locals 7

    const/16 v3, 0x17

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v4, p3

    move-object v5, p4

    move-object v6, p5

    .line 1
    invoke-direct/range {v0 .. v6}, Lcom/google/android/gms/common/internal/GmsClient;-><init>(Landroid/content/Context;Landroid/os/Looper;ILcom/google/android/gms/common/internal/ClientSettings;Lcom/google/android/gms/common/api/internal/ConnectionCallbacks;Lcom/google/android/gms/common/api/internal/OnConnectionFailedListener;)V

    .line 2
    new-instance p0, Landroidx/collection/SimpleArrayMap;

    invoke-direct {p0}, Landroidx/collection/SimpleArrayMap;-><init>()V

    iput-object p0, v0, Lcom/google/android/gms/internal/location/zzec;->zzf:Landroidx/collection/SimpleArrayMap;

    new-instance p0, Landroidx/collection/SimpleArrayMap;

    .line 3
    invoke-direct {p0}, Landroidx/collection/SimpleArrayMap;-><init>()V

    iput-object p0, v0, Lcom/google/android/gms/internal/location/zzec;->zzg:Landroidx/collection/SimpleArrayMap;

    new-instance p0, Landroidx/collection/SimpleArrayMap;

    .line 4
    invoke-direct {p0}, Landroidx/collection/SimpleArrayMap;-><init>()V

    iput-object p0, v0, Lcom/google/android/gms/internal/location/zzec;->zzh:Landroidx/collection/SimpleArrayMap;

    new-instance p0, Landroidx/collection/SimpleArrayMap;

    .line 5
    invoke-direct {p0}, Landroidx/collection/SimpleArrayMap;-><init>()V

    return-void
.end method

.method private final zzG(Lcom/google/android/gms/common/Feature;)Z
    .locals 5

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getAvailableFeatures()[Lcom/google/android/gms/common/Feature;

    move-result-object p0

    const/4 v0, 0x0

    if-eqz p0, :cond_3

    move v1, v0

    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_1

    .line 2
    aget-object v2, p0, v1

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/common/Feature;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2}, Lcom/google/android/gms/common/Feature;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    goto :goto_1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    const/4 v2, 0x0

    :goto_1
    if-nez v2, :cond_2

    return v0

    .line 4
    :cond_2
    invoke-virtual {v2}, Lcom/google/android/gms/common/Feature;->getVersion()J

    move-result-wide v1

    invoke-virtual {p1}, Lcom/google/android/gms/common/Feature;->getVersion()J

    move-result-wide p0

    cmp-long p0, v1, p0

    if-ltz p0, :cond_3

    const/4 p0, 0x1

    return p0

    :cond_3
    return v0
.end method


# virtual methods
.method public final synthetic createServiceInterface(Landroid/os/IBinder;)Landroid/os/IInterface;
    .locals 1

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 1
    :cond_0
    const-string p0, "com.google.android.gms.location.internal.IGoogleLocationManagerService"

    invoke-interface {p1, p0}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p0

    instance-of v0, p0, Lcom/google/android/gms/internal/location/zzu;

    if-eqz v0, :cond_1

    .line 2
    check-cast p0, Lcom/google/android/gms/internal/location/zzu;

    return-object p0

    :cond_1
    new-instance p0, Lcom/google/android/gms/internal/location/zzt;

    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/location/zzt;-><init>(Landroid/os/IBinder;)V

    return-object p0
.end method

.method public final getApiFeatures()[Lcom/google/android/gms/common/Feature;
    .locals 0

    .line 1
    sget-object p0, Lcom/google/android/gms/location/zzn;->zzp:[Lcom/google/android/gms/common/Feature;

    return-object p0
.end method

.method public final getMinApkVersion()I
    .locals 0

    const p0, 0xb2c988

    return p0
.end method

.method public final getServiceDescriptor()Ljava/lang/String;
    .locals 0

    const-string p0, "com.google.android.gms.location.internal.IGoogleLocationManagerService"

    return-object p0
.end method

.method public final getStartServiceAction()Ljava/lang/String;
    .locals 0

    const-string p0, "com.google.android.location.internal.GoogleLocationManagerService.START"

    return-object p0
.end method

.method public final onConnectionSuspended(I)V
    .locals 1

    .line 1
    invoke-super {p0, p1}, Lcom/google/android/gms/common/internal/BaseGmsClient;->onConnectionSuspended(I)V

    iget-object p1, p0, Lcom/google/android/gms/internal/location/zzec;->zzf:Landroidx/collection/SimpleArrayMap;

    .line 2
    monitor-enter p1

    .line 3
    :try_start_0
    invoke-virtual {p1}, Landroidx/collection/SimpleArrayMap;->clear()V

    .line 4
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_2

    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzec;->zzg:Landroidx/collection/SimpleArrayMap;

    .line 5
    monitor-enter v0

    .line 6
    :try_start_1
    invoke-virtual {v0}, Landroidx/collection/SimpleArrayMap;->clear()V

    .line 7
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    iget-object p0, p0, Lcom/google/android/gms/internal/location/zzec;->zzh:Landroidx/collection/SimpleArrayMap;

    .line 8
    monitor-enter p0

    .line 9
    :try_start_2
    invoke-virtual {p0}, Landroidx/collection/SimpleArrayMap;->clear()V

    .line 10
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1

    :catchall_1
    move-exception p0

    .line 7
    :try_start_3
    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p0

    :catchall_2
    move-exception p0

    .line 4
    :try_start_4
    monitor-exit p1
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    throw p0
.end method

.method public final usesClientTelemetry()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public final zzq(Lcom/google/android/gms/location/LastLocationRequest;Lcom/google/android/gms/tasks/TaskCompletionSource;)V
    .locals 6

    .line 1
    sget-object v0, Lcom/google/android/gms/location/zzn;->zzj:Lcom/google/android/gms/common/Feature;

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/location/zzec;->zzG(Lcom/google/android/gms/common/Feature;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/location/zzu;

    new-instance v3, Lcom/google/android/gms/internal/location/zzdj;

    .line 3
    invoke-direct {v3, p2}, Lcom/google/android/gms/internal/location/zzdj;-><init>(Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    .line 4
    new-instance v0, Lcom/google/android/gms/internal/location/zzed;

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x4

    const/4 v2, 0x0

    .line 5
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/location/zzed;-><init>(ILandroid/os/IBinder;Landroid/os/IBinder;Landroid/app/PendingIntent;Ljava/lang/String;)V

    .line 6
    invoke-interface {p0, p1, v0}, Lcom/google/android/gms/internal/location/zzu;->zzq(Lcom/google/android/gms/location/LastLocationRequest;Lcom/google/android/gms/internal/location/zzed;)V

    return-void

    :cond_0
    sget-object v0, Lcom/google/android/gms/location/zzn;->zzf:Lcom/google/android/gms/common/Feature;

    .line 7
    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/location/zzec;->zzG(Lcom/google/android/gms/common/Feature;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 8
    invoke-virtual {p0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/location/zzu;

    new-instance v0, Lcom/google/android/gms/internal/location/zzdj;

    .line 9
    invoke-direct {v0, p2}, Lcom/google/android/gms/internal/location/zzdj;-><init>(Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    .line 10
    invoke-interface {p0, p1, v0}, Lcom/google/android/gms/internal/location/zzu;->zzr(Lcom/google/android/gms/location/LastLocationRequest;Lcom/google/android/gms/internal/location/zzy;)V

    return-void

    .line 11
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/location/zzu;

    invoke-interface {p0}, Lcom/google/android/gms/internal/location/zzu;->zzs()Landroid/location/Location;

    move-result-object p0

    invoke-virtual {p2, p0}, Lcom/google/android/gms/tasks/TaskCompletionSource;->setResult(Ljava/lang/Object;)V

    return-void
.end method

.method public final zzt(Lcom/google/android/gms/internal/location/zzdq;Lcom/google/android/gms/location/LocationRequest;Lcom/google/android/gms/tasks/TaskCompletionSource;)V
    .locals 23

    move-object/from16 v0, p0

    move-object/from16 v1, p3

    .line 1
    invoke-interface/range {p1 .. p1}, Lcom/google/android/gms/internal/location/zzdq;->zza()Lcom/google/android/gms/common/api/internal/ListenerHolder;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/gms/common/api/internal/ListenerHolder;->getListenerKey()Lcom/google/android/gms/common/api/internal/ListenerHolder$ListenerKey;

    move-result-object v3

    invoke-static {v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 2
    sget-object v4, Lcom/google/android/gms/location/zzn;->zzj:Lcom/google/android/gms/common/Feature;

    .line 3
    invoke-direct {v0, v4}, Lcom/google/android/gms/internal/location/zzec;->zzG(Lcom/google/android/gms/common/Feature;)Z

    move-result v4

    iget-object v5, v0, Lcom/google/android/gms/internal/location/zzec;->zzg:Landroidx/collection/SimpleArrayMap;

    .line 4
    monitor-enter v5

    .line 5
    :try_start_0
    invoke-virtual {v5, v3}, Landroidx/collection/SimpleArrayMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/google/android/gms/internal/location/zzdu;

    const/4 v7, 0x0

    if-eqz v6, :cond_1

    if-eqz v4, :cond_0

    goto :goto_0

    .line 8
    :cond_0
    invoke-virtual {v6, v2}, Lcom/google/android/gms/internal/location/zzdu;->zzc(Lcom/google/android/gms/common/api/internal/ListenerHolder;)Lcom/google/android/gms/internal/location/zzdu;

    move-object v12, v6

    move-object v6, v7

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_3

    .line 5
    :cond_1
    :goto_0
    new-instance v2, Lcom/google/android/gms/internal/location/zzdu;

    move-object/from16 v8, p1

    .line 6
    invoke-direct {v2, v8}, Lcom/google/android/gms/internal/location/zzdu;-><init>(Lcom/google/android/gms/internal/location/zzdq;)V

    .line 7
    invoke-virtual {v5, v3, v2}, Landroidx/collection/SimpleArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-object v12, v2

    :goto_1
    if-eqz v4, :cond_2

    .line 9
    invoke-virtual {v0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/location/zzu;

    .line 10
    invoke-virtual {v3}, Lcom/google/android/gms/common/api/internal/ListenerHolder$ListenerKey;->toIdString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v6, v12, v2}, Lcom/google/android/gms/internal/location/zzed;->zzb(Landroid/os/IInterface;Lcom/google/android/gms/location/zzv;Ljava/lang/String;)Lcom/google/android/gms/internal/location/zzed;

    move-result-object v2

    .line 11
    new-instance v3, Lcom/google/android/gms/internal/location/zzdi;

    invoke-direct {v3, v7, v1}, Lcom/google/android/gms/internal/location/zzdi;-><init>(Ljava/lang/Object;Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    move-object/from16 v14, p2

    .line 12
    invoke-interface {v0, v2, v14, v3}, Lcom/google/android/gms/internal/location/zzu;->zzw(Lcom/google/android/gms/internal/location/zzed;Lcom/google/android/gms/location/LocationRequest;Lcom/google/android/gms/common/api/internal/IStatusCallback;)V

    goto :goto_2

    :cond_2
    move-object/from16 v14, p2

    .line 13
    invoke-virtual {v0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/location/zzu;

    .line 14
    new-instance v13, Lcom/google/android/gms/internal/location/zzef;

    const/16 v20, 0x0

    const-wide v21, 0x7fffffffffffffffL

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    .line 15
    invoke-direct/range {v13 .. v22}, Lcom/google/android/gms/internal/location/zzef;-><init>(Lcom/google/android/gms/location/LocationRequest;Ljava/util/List;ZZZZLjava/lang/String;J)V

    new-instance v14, Lcom/google/android/gms/internal/location/zzdc;

    .line 16
    invoke-direct {v14, v1, v12}, Lcom/google/android/gms/internal/location/zzdc;-><init>(Lcom/google/android/gms/tasks/TaskCompletionSource;Lcom/google/android/gms/location/zzv;)V

    .line 17
    invoke-virtual {v3}, Lcom/google/android/gms/common/api/internal/ListenerHolder$ListenerKey;->toIdString()Ljava/lang/String;

    move-result-object v15

    .line 18
    new-instance v8, Lcom/google/android/gms/internal/location/zzeh;

    const/4 v11, 0x0

    move-object v10, v13

    const/4 v13, 0x0

    const/4 v9, 0x1

    .line 19
    invoke-direct/range {v8 .. v15}, Lcom/google/android/gms/internal/location/zzeh;-><init>(ILcom/google/android/gms/internal/location/zzef;Landroid/os/IBinder;Landroid/os/IBinder;Landroid/app/PendingIntent;Landroid/os/IBinder;Ljava/lang/String;)V

    .line 20
    invoke-interface {v0, v8}, Lcom/google/android/gms/internal/location/zzu;->zzv(Lcom/google/android/gms/internal/location/zzeh;)V

    .line 21
    :goto_2
    monitor-exit v5

    return-void

    :goto_3
    monitor-exit v5
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public final zzw(Lcom/google/android/gms/common/api/internal/ListenerHolder$ListenerKey;ZLcom/google/android/gms/tasks/TaskCompletionSource;)V
    .locals 10

    .line 1
    iget-object v1, p0, Lcom/google/android/gms/internal/location/zzec;->zzg:Landroidx/collection/SimpleArrayMap;

    monitor-enter v1

    .line 2
    :try_start_0
    invoke-virtual {v1, p1}, Landroidx/collection/SimpleArrayMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    move-object v6, p1

    check-cast v6, Lcom/google/android/gms/internal/location/zzdu;

    if-nez v6, :cond_0

    .line 3
    sget-object p0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {p3, p0}, Lcom/google/android/gms/tasks/TaskCompletionSource;->setResult(Ljava/lang/Object;)V

    .line 4
    monitor-exit v1

    return-void

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 5
    :cond_0
    invoke-virtual {v6}, Lcom/google/android/gms/internal/location/zzdu;->zzg()V

    if-eqz p2, :cond_2

    .line 6
    sget-object p1, Lcom/google/android/gms/location/zzn;->zzj:Lcom/google/android/gms/common/Feature;

    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/location/zzec;->zzG(Lcom/google/android/gms/common/Feature;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 7
    invoke-virtual {p0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/location/zzu;

    .line 8
    sget-object p1, Lcom/google/android/gms/internal/location/zzed;->CREATOR:Landroid/os/Parcelable$Creator;

    const-string p1, "ILocationCallback@"

    .line 9
    invoke-static {v6}, Ljava/lang/System;->identityHashCode(Ljava/lang/Object;)I

    move-result p2

    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x12

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    .line 10
    invoke-static {p2, v6, p1}, Lcom/google/android/gms/internal/location/zzed;->zzb(Landroid/os/IInterface;Lcom/google/android/gms/location/zzv;Ljava/lang/String;)Lcom/google/android/gms/internal/location/zzed;

    move-result-object p1

    .line 8
    sget-object p2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    .line 11
    new-instance v0, Lcom/google/android/gms/internal/location/zzdi;

    invoke-direct {v0, p2, p3}, Lcom/google/android/gms/internal/location/zzdi;-><init>(Ljava/lang/Object;Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    .line 12
    invoke-interface {p0, p1, v0}, Lcom/google/android/gms/internal/location/zzu;->zzx(Lcom/google/android/gms/internal/location/zzed;Lcom/google/android/gms/common/api/internal/IStatusCallback;)V

    goto :goto_0

    .line 13
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/location/zzu;

    .line 14
    sget-object p1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    new-instance v8, Lcom/google/android/gms/internal/location/zzdm;

    .line 15
    invoke-direct {v8, p1, p3}, Lcom/google/android/gms/internal/location/zzdm;-><init>(Ljava/lang/Object;Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    .line 16
    new-instance v2, Lcom/google/android/gms/internal/location/zzeh;

    const/4 v7, 0x0

    const/4 v9, 0x0

    const/4 v3, 0x2

    const/4 v4, 0x0

    const/4 v5, 0x0

    .line 17
    invoke-direct/range {v2 .. v9}, Lcom/google/android/gms/internal/location/zzeh;-><init>(ILcom/google/android/gms/internal/location/zzef;Landroid/os/IBinder;Landroid/os/IBinder;Landroid/app/PendingIntent;Landroid/os/IBinder;Ljava/lang/String;)V

    .line 18
    invoke-interface {p0, v2}, Lcom/google/android/gms/internal/location/zzu;->zzv(Lcom/google/android/gms/internal/location/zzeh;)V

    goto :goto_0

    .line 19
    :cond_2
    sget-object p0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {p3, p0}, Lcom/google/android/gms/tasks/TaskCompletionSource;->setResult(Ljava/lang/Object;)V

    .line 20
    :goto_0
    monitor-exit v1

    return-void

    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
