.class public Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static volatile loaded:Ljava/lang/Boolean;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static native addFilters(JLjava/lang/String;)Lcom/exteragram/messenger/adblock/data/FilterListMetadata;
.end method

.method public static native createEngine(J)J
.end method

.method public static native createFilterSet([Ljava/lang/String;)J
.end method

.method public static native destroyEngine(J)V
.end method

.method public static native destroyFilterSet(J)V
.end method

.method public static native getCosmeticResources(JLjava/lang/String;)Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;
.end method

.method public static native getHiddenSelectors(J[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;
.end method

.method public static isLoadFailed()Z
    .locals 2

    .line 26
    sget-object v0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    sget-object v1, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->loaded:Ljava/lang/Boolean;

    invoke-virtual {v0, v1}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static declared-synchronized loadLibraries()Z
    .locals 2

    const-class v0, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;

    monitor-enter v0

    .line 13
    :try_start_0
    sget-object v1, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->loaded:Ljava/lang/Boolean;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-nez v1, :cond_0

    .line 15
    :try_start_1
    const-string v1, "etgadblock"

    invoke-static {v1}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 16
    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    sput-object v1, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->loaded:Ljava/lang/Boolean;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v1

    .line 18
    :try_start_2
    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 19
    sget-object v1, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    sput-object v1, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->loaded:Ljava/lang/Boolean;

    goto :goto_0

    :catchall_1
    move-exception v1

    goto :goto_1

    .line 22
    :cond_0
    :goto_0
    sget-object v1, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->loaded:Ljava/lang/Boolean;

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    monitor-exit v0

    return v1

    :goto_1
    :try_start_3
    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw v1
.end method

.method public static native shouldBlock(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;
.end method

.method public static native useResources(J[Ljava/lang/String;[[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V
.end method
