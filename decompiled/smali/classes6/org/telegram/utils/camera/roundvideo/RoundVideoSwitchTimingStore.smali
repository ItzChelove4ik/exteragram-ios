.class abstract Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final KEYS:[Ljava/lang/String;

.field private static final counts:[I

.field private static loaded:Z

.field private static final nextIndices:[I

.field private static final samples:[[I


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 21
    const-string v0, "round_video_switch_back_to_front_ms"

    const-string v1, "round_video_switch_front_to_back_ms"

    filled-new-array {v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->KEYS:[Ljava/lang/String;

    const/4 v0, 0x2

    .line 25
    new-array v1, v0, [I

    const/4 v2, 0x1

    const/16 v3, 0x8

    aput v3, v1, v2

    const/4 v2, 0x0

    aput v0, v1, v2

    sget-object v2, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    invoke-static {v2, v1}, Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;[I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [[I

    sput-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->samples:[[I

    .line 26
    new-array v1, v0, [I

    sput-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->counts:[I

    .line 27
    new-array v0, v0, [I

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->nextIndices:[I

    return-void
.end method

.method private static average(I)I
    .locals 4

    .line 88
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->counts:[I

    aget v0, v0, p0

    if-nez v0, :cond_1

    if-nez p0, :cond_0

    const/16 p0, 0x2a8

    return p0

    :cond_0
    const/16 p0, 0x276

    return p0

    :cond_1
    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v1, v0, :cond_2

    .line 96
    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->samples:[[I

    aget-object v3, v3, p0

    aget v3, v3, v1

    add-int/2addr v2, v3

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    int-to-float p0, v2

    int-to-float v0, v0

    div-float/2addr p0, v0

    .line 98
    invoke-static {p0}, Ljava/lang/Math;->round(F)I

    move-result p0

    return p0
.end method

.method private static clamp(III)I
    .locals 0

    .line 132
    invoke-static {p2, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method private static direction(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)I
    .locals 0

    if-eq p0, p1, :cond_1

    .line 121
    sget-object p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-ne p0, p1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/4 p0, 0x1

    return p0

    .line 119
    :cond_1
    const-string p0, "Camera switch direction must change"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0
.end method

.method private static ensureLoaded()V
    .locals 10

    .line 60
    sget-boolean v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->loaded:Z

    if-eqz v0, :cond_0

    goto :goto_3

    :cond_0
    const/4 v0, 0x1

    .line 63
    sput-boolean v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->loaded:Z

    .line 64
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->preferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const/4 v1, 0x0

    move v2, v1

    .line 65
    :goto_0
    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->KEYS:[Ljava/lang/String;

    array-length v4, v3

    if-ge v2, v4, :cond_4

    .line 66
    aget-object v3, v3, v2

    const/4 v4, 0x0

    invoke-interface {v0, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_3

    .line 67
    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_1

    goto :goto_2

    .line 70
    :cond_1
    const-string v4, ","

    invoke-virtual {v3, v4}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v3

    .line 71
    array-length v4, v3

    add-int/lit8 v4, v4, -0x8

    invoke-static {v1, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 72
    :goto_1
    array-length v5, v3

    if-ge v4, v5, :cond_2

    .line 74
    :try_start_0
    aget-object v5, v3, v4

    .line 75
    invoke-static {v5}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v5

    const/16 v6, 0xc8

    const/16 v7, 0x7d0

    .line 74
    invoke-static {v5, v6, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->clamp(III)I

    move-result v5

    .line 79
    sget-object v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->samples:[[I

    aget-object v6, v6, v2

    sget-object v7, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->counts:[I

    aget v8, v7, v2

    add-int/lit8 v9, v8, 0x1

    aput v9, v7, v2

    aput v5, v6, v8
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    .line 83
    :cond_2
    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->nextIndices:[I

    sget-object v4, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->counts:[I

    aget v4, v4, v2

    rem-int/lit8 v4, v4, 0x8

    aput v4, v3, v2

    :cond_3
    :goto_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_4
    :goto_3
    return-void
.end method

.method public static declared-synchronized getAverageMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)I
    .locals 1

    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;

    monitor-enter v0

    .line 37
    :try_start_0
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->ensureLoaded()V

    .line 38
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->direction(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)I

    move-result p0

    .line 39
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->average(I)I

    move-result p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return p0

    :catchall_0
    move-exception p0

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private static persist(I)V
    .locals 7

    .line 102
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->counts:[I

    aget v0, v0, p0

    const/4 v1, 0x0

    const/16 v2, 0x8

    if-ne v0, v2, :cond_0

    .line 103
    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->nextIndices:[I

    aget v3, v3, p0

    goto :goto_0

    :cond_0
    move v3, v1

    .line 104
    :goto_0
    new-instance v4, Ljava/lang/StringBuilder;

    mul-int/lit8 v5, v0, 0x5

    invoke-direct {v4, v5}, Ljava/lang/StringBuilder;-><init>(I)V

    :goto_1
    if-ge v1, v0, :cond_2

    if-lez v1, :cond_1

    const/16 v5, 0x2c

    .line 107
    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 109
    :cond_1
    sget-object v5, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->samples:[[I

    aget-object v5, v5, p0

    add-int v6, v3, v1

    rem-int/2addr v6, v2

    aget v5, v5, v6

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 111
    :cond_2
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->preferences()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->KEYS:[Ljava/lang/String;

    aget-object p0, v1, p0

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, p0, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method private static preferences()Landroid/content/SharedPreferences;
    .locals 3

    .line 125
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "mainconfig"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    return-object v0
.end method

.method public static declared-synchronized recordAndGetAverageMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;I)I
    .locals 3

    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;

    monitor-enter v0

    .line 47
    :try_start_0
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->ensureLoaded()V

    .line 48
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->direction(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)I

    move-result p0

    const/16 p1, 0xc8

    const/16 v1, 0x7d0

    .line 49
    invoke-static {p2, p1, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->clamp(III)I

    move-result p1

    .line 50
    sget-object p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->samples:[[I

    aget-object p2, p2, p0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->nextIndices:[I

    aget v2, v1, p0

    aput p1, p2, v2

    .line 51
    aget p1, v1, p0

    add-int/lit8 p1, p1, 0x1

    const/16 p2, 0x8

    rem-int/2addr p1, p2

    aput p1, v1, p0

    .line 52
    sget-object p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->counts:[I

    aget v1, p1, p0

    if-ge v1, p2, :cond_0

    add-int/lit8 v1, v1, 0x1

    .line 53
    aput v1, p1, p0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 55
    :cond_0
    :goto_0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->persist(I)V

    .line 56
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->average(I)I

    move-result p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return p0

    :goto_1
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method
