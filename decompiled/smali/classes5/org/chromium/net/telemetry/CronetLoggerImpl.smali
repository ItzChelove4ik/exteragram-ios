.class public Lorg/chromium/net/telemetry/CronetLoggerImpl;
.super Lorg/chromium/net/impl/CronetLogger;
.source "SourceFile"


# static fields
.field private static final TAG:Ljava/lang/String; = "CronetLoggerImpl"


# instance fields
.field private final mRateLimiter:Lorg/chromium/net/telemetry/RateLimiter;

.field private final mSamplesRateLimited:Ljava/util/concurrent/atomic/AtomicInteger;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(I)V
    .locals 1

    .line 30
    new-instance v0, Lorg/chromium/net/telemetry/RateLimiter;

    invoke-direct {v0, p1}, Lorg/chromium/net/telemetry/RateLimiter;-><init>(I)V

    invoke-direct {p0, v0}, Lorg/chromium/net/telemetry/CronetLoggerImpl;-><init>(Lorg/chromium/net/telemetry/RateLimiter;)V

    return-void
.end method

.method public constructor <init>(Lorg/chromium/net/telemetry/RateLimiter;)V
    .locals 1

    .line 35
    invoke-direct {p0}, Lorg/chromium/net/impl/CronetLogger;-><init>()V

    .line 26
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    iput-object v0, p0, Lorg/chromium/net/telemetry/CronetLoggerImpl;->mSamplesRateLimited:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 36
    iput-object p1, p0, Lorg/chromium/net/telemetry/CronetLoggerImpl;->mRateLimiter:Lorg/chromium/net/telemetry/RateLimiter;

    return-void
.end method

.method private static convertToProtoConnectionCloseSource(I)I
    .locals 2

    const/4 v0, 0x2

    const/4 v1, 0x1

    if-eq p0, v1, :cond_1

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v1

    :cond_1
    return v0
.end method

.method private static convertToProtoCronetEngineBuilderInitializedAuthor(Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;)I
    .locals 1

    .line 275
    sget-object v0, Lorg/chromium/net/telemetry/CronetLoggerImpl$1;->$SwitchMap$org$chromium$net$impl$CronetLogger$CronetEngineBuilderInitializedInfo$Author:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method

.method private static convertToProtoCronetEngineBuilderInitializedSource(Lorg/chromium/net/impl/CronetLogger$CronetSource;)I
    .locals 1

    .line 299
    sget-object v0, Lorg/chromium/net/telemetry/CronetLoggerImpl$1;->$SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 v0, 0x3

    if-eq p0, v0, :cond_0

    const/4 v0, 0x4

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method

.method private static convertToProtoCronetEngineCreatedSource(Lorg/chromium/net/impl/CronetLogger$CronetSource;)I
    .locals 1

    .line 319
    sget-object v0, Lorg/chromium/net/telemetry/CronetLoggerImpl$1;->$SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 v0, 0x3

    if-eq p0, v0, :cond_0

    const/4 v0, 0x4

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method

.method private static convertToProtoCronetRequestTerminalState(Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;)I
    .locals 1

    .line 286
    sget-object v0, Lorg/chromium/net/telemetry/CronetLoggerImpl$1;->$SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestTerminalState:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 v0, 0x3

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method

.method private static convertToProtoFailureReason(Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;)I
    .locals 1

    .line 248
    sget-object v0, Lorg/chromium/net/telemetry/CronetLoggerImpl$1;->$SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestFailureReason:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_1

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/16 p0, 0x64

    return p0

    :cond_1
    return v0
.end method

.method private static convertToProtoHttpCacheMode(I)I
    .locals 2

    const/4 v0, 0x1

    if-eqz p0, :cond_3

    const/4 v1, 0x2

    if-eq p0, v0, :cond_2

    const/4 v0, 0x3

    if-eq p0, v1, :cond_1

    if-ne p0, v0, :cond_0

    const/4 p0, 0x4

    return p0

    .line 348
    :cond_0
    const-string p0, "Expected httpCacheMode to range from 0 to 3"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0

    :cond_1
    return v0

    :cond_2
    return v1

    :cond_3
    return v0
.end method


# virtual methods
.method public generateId()J
    .locals 4

    .line 43
    invoke-static {}, Ljava/util/concurrent/ThreadLocalRandom;->current()Ljava/util/concurrent/ThreadLocalRandom;

    move-result-object p0

    const-wide v0, -0x7fffffffffffffffL    # -4.9E-324

    const-wide v2, 0x7ffffffffffffffdL

    invoke-virtual {p0, v0, v1, v2, v3}, Ljava/util/concurrent/ThreadLocalRandom;->nextLong(JJ)J

    move-result-wide v0

    const-wide/16 v2, -0x1

    cmp-long p0, v0, v2

    if-ltz p0, :cond_0

    const-wide/16 v2, 0x2

    add-long/2addr v0, v2

    :cond_0
    return-wide v0
.end method

.method public logCronetEngineBuilderInitializedInfo(Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;)V
    .locals 18

    move-object/from16 v0, p1

    .line 49
    const-string v1, "CronetLoggerImpl#logCronetEngineBuilderInitializedInfo"

    .line 50
    invoke-static {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v1

    .line 52
    :try_start_0
    iget-wide v3, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->cronetInitializationRef:J

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->author:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    .line 55
    invoke-static {v2}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoCronetEngineBuilderInitializedAuthor(Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;)I

    move-result v5

    iget v6, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->engineBuilderCreatedLatencyMillis:I

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->source:Lorg/chromium/net/impl/CronetLogger$CronetSource;

    .line 57
    invoke-static {v2}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoCronetEngineBuilderInitializedSource(Lorg/chromium/net/impl/CronetLogger$CronetSource;)I

    move-result v7

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->creationSuccessful:Ljava/lang/Boolean;

    .line 58
    invoke-static {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->fromBoolean(Ljava/lang/Boolean;)Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v8

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->apiVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    .line 59
    invoke-virtual {v2}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getMajorVersion()I

    move-result v9

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->apiVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    .line 60
    invoke-virtual {v2}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getMinorVersion()I

    move-result v10

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->apiVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    .line 61
    invoke-virtual {v2}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getBuildVersion()I

    move-result v11

    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->apiVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    .line 62
    invoke-virtual {v2}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getPatchVersion()I

    move-result v12

    .line 64
    iget-object v2, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->implVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    if-nez v2, :cond_0

    const/4 v2, -0x1

    goto :goto_0

    :cond_0
    invoke-virtual {v2}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getMajorVersion()I

    move-result v2

    .line 65
    :goto_0
    iget-object v14, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->implVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    if-nez v14, :cond_1

    const/4 v14, -0x1

    goto :goto_1

    :cond_1
    invoke-virtual {v14}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getMinorVersion()I

    move-result v14

    .line 66
    :goto_1
    iget-object v15, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->implVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    if-nez v15, :cond_2

    const/4 v15, -0x1

    goto :goto_2

    :cond_2
    invoke-virtual {v15}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getBuildVersion()I

    move-result v15

    .line 67
    :goto_2
    iget-object v13, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->implVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

    if-nez v13, :cond_3

    const/16 v16, -0x1

    goto :goto_3

    :cond_3
    invoke-virtual {v13}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getPatchVersion()I

    move-result v13

    move/from16 v16, v13

    :goto_3
    iget v0, v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->uid:I

    move v13, v2

    const/16 v2, 0x2fa

    move/from16 v17, v0

    .line 52
    invoke-static/range {v2 .. v17}, Lorg/chromium/net/telemetry/CronetStatsLog;->write(IJIIIIIIIIIIIII)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_4

    .line 69
    invoke-virtual {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_4
    return-void

    :catchall_0
    move-exception v0

    move-object v2, v0

    if-eqz v1, :cond_5

    .line 49
    :try_start_1
    invoke-virtual {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_4

    :catchall_1
    move-exception v0

    invoke-virtual {v2, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_5
    :goto_4
    throw v2
.end method

.method public logCronetEngineCreation(JLorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;Lorg/chromium/net/impl/CronetLogger$CronetVersion;Lorg/chromium/net/impl/CronetLogger$CronetSource;)V
    .locals 0

    if-eqz p3, :cond_1

    if-eqz p4, :cond_1

    if-nez p5, :cond_0

    goto :goto_0

    .line 107
    :cond_0
    invoke-virtual/range {p0 .. p5}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->writeCronetEngineCreation(JLorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;Lorg/chromium/net/impl/CronetLogger$CronetVersion;Lorg/chromium/net/impl/CronetLogger$CronetSource;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public logCronetTrafficInfo(JLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;)V
    .locals 2

    if-nez p3, :cond_0

    return-void

    .line 116
    :cond_0
    iget-object v0, p0, Lorg/chromium/net/telemetry/CronetLoggerImpl;->mRateLimiter:Lorg/chromium/net/telemetry/RateLimiter;

    invoke-virtual {v0}, Lorg/chromium/net/telemetry/RateLimiter;->tryAcquire()Z

    move-result v0

    .line 121
    iget-object v1, p0, Lorg/chromium/net/telemetry/CronetLoggerImpl;->mSamplesRateLimited:Ljava/util/concurrent/atomic/AtomicInteger;

    if-nez v0, :cond_1

    .line 117
    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    return-void

    :cond_1
    const/4 v0, 0x0

    .line 121
    invoke-virtual {v1, v0}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndSet(I)I

    move-result v0

    invoke-virtual {p0, p1, p2, p3, v0}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->writeCronetTrafficReported(JLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;I)V

    return-void
.end method

.method public writeCronetEngineCreation(JLorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;Lorg/chromium/net/impl/CronetLogger$CronetVersion;Lorg/chromium/net/impl/CronetLogger$CronetSource;)V
    .locals 43

    .line 130
    :try_start_0
    const-string v0, "CronetLoggerImpl#writeCronetEngineCreation"

    .line 131
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 133
    :try_start_1
    new-instance v0, Lorg/chromium/net/telemetry/ExperimentalOptions;

    .line 134
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->getExperimentalOptions()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Lorg/chromium/net/telemetry/ExperimentalOptions;-><init>(Ljava/lang/String;)V

    .line 139
    invoke-virtual/range {p4 .. p4}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getMajorVersion()I

    move-result v6

    .line 140
    invoke-virtual/range {p4 .. p4}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getMinorVersion()I

    move-result v7

    .line 141
    invoke-virtual/range {p4 .. p4}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getBuildVersion()I

    move-result v8

    .line 142
    invoke-virtual/range {p4 .. p4}, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->getPatchVersion()I

    move-result v9

    .line 143
    invoke-static/range {p5 .. p5}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoCronetEngineCreatedSource(Lorg/chromium/net/impl/CronetLogger$CronetSource;)I

    move-result v10

    .line 144
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->isBrotliEnabled()Z

    move-result v11

    .line 145
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->isHttp2Enabled()Z

    move-result v12

    .line 146
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->getHttpCacheMode()I

    move-result v2

    invoke-static {v2}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoHttpCacheMode(I)I

    move-result v13

    .line 147
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->isPublicKeyPinningBypassForLocalTrustAnchorsEnabled()Z

    move-result v14

    .line 148
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->isQuicEnabled()Z

    move-result v15

    .line 149
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->isNetworkQualityEstimatorEnabled()Z

    move-result v16

    .line 150
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->getThreadPriority()I

    move-result v17

    .line 152
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getConnectionOptionsOption()Ljava/lang/String;

    move-result-object v18

    .line 153
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStoreServerConfigsInPropertiesOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v19

    .line 154
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getMaxServerConfigsStoredInPropertiesOption()I

    move-result v20

    .line 155
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getIdleConnectionTimeoutSecondsOption()I

    move-result v21

    .line 156
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getGoawaySessionsOnIpChangeOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v22

    .line 157
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getCloseSessionsOnIpChangeOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v23

    .line 158
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getMigrateSessionsOnNetworkChangeV2Option()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v24

    .line 159
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getMigrateSessionsEarlyV2()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v25

    .line 160
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getDisableBidirectionalStreamsOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v26

    .line 161
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getMaxTimeBeforeCryptoHandshakeSecondsOption()I

    move-result v27

    .line 162
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getMaxIdleTimeBeforeCryptoHandshakeSecondsOption()I

    move-result v28

    .line 163
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getEnableSocketRecvOptimizationOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v29

    .line 165
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getAsyncDnsEnableOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v30

    .line 167
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsEnableOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v31

    .line 168
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsDelayMillisOption()I

    move-result v32

    .line 169
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsMaxExpiredTimeMillisOption()I

    move-result v33

    .line 170
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsMaxStaleUsesOption()I

    move-result v34

    .line 171
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsAllowOtherNetworkOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v35

    .line 172
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsPersistToDiskOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v36

    .line 173
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsPersistDelayMillisOption()I

    move-result v37

    .line 174
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getStaleDnsUseStaleOnNameNotResolvedOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v38

    .line 175
    invoke-virtual {v0}, Lorg/chromium/net/telemetry/ExperimentalOptions;->getDisableIpv6OnWifiOption()Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    invoke-virtual {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v39

    .line 176
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;->getCronetInitializationRef()J

    move-result-wide v40

    .line 177
    invoke-static {}, Landroid/os/Process;->myUid()I

    move-result v42

    const/16 v3, 0x2bf

    move-wide/from16 v4, p1

    .line 136
    invoke-static/range {v3 .. v42}, Lorg/chromium/net/telemetry/CronetStatsLog;->write(IJIIIIIZZIZZZILjava/lang/String;IIIIIIIIIIIIIIIIIIIIIJI)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v1, :cond_1

    .line 178
    :try_start_2
    invoke-virtual {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-void

    :catch_0
    move-exception v0

    goto :goto_1

    :catchall_0
    move-exception v0

    move-object v2, v0

    if-eqz v1, :cond_0

    .line 130
    :try_start_3
    invoke-virtual {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception v0

    :try_start_4
    invoke-virtual {v2, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_0
    :goto_0
    throw v2
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    .line 179
    :goto_1
    sget-object v1, Lorg/chromium/net/telemetry/CronetLoggerImpl;->TAG:Ljava/lang/String;

    const/4 v2, 0x3

    invoke-static {v1, v2}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 184
    invoke-static/range {p1 .. p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    filled-new-array {v2, v0}, [Ljava/lang/Object;

    move-result-object v0

    .line 182
    const-string v2, "Failed to log CronetEngine:%s creation: %s"

    invoke-static {v2, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 180
    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    return-void
.end method

.method public writeCronetTrafficReported(JLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;I)V
    .locals 45

    .line 193
    :try_start_0
    const-string v0, "CronetLoggerImpl#writeCronetTrafficReported"

    .line 194
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 199
    :try_start_1
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getRequestHeaderSizeInBytes()J

    move-result-wide v2

    .line 198
    invoke-static {v2, v3}, Lorg/chromium/net/telemetry/SizeBuckets;->calcRequestHeadersSizeBucket(J)I

    move-result v7

    .line 200
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getRequestBodySizeInBytes()J

    move-result-wide v2

    invoke-static {v2, v3}, Lorg/chromium/net/telemetry/SizeBuckets;->calcRequestBodySizeBucket(J)I

    move-result v8

    .line 202
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getResponseHeaderSizeInBytes()J

    move-result-wide v2

    .line 201
    invoke-static {v2, v3}, Lorg/chromium/net/telemetry/SizeBuckets;->calcResponseHeadersSizeBucket(J)I

    move-result v9

    .line 204
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getResponseBodySizeInBytes()J

    move-result-wide v2

    .line 203
    invoke-static {v2, v3}, Lorg/chromium/net/telemetry/SizeBuckets;->calcResponseBodySizeBucket(J)I

    move-result v10

    .line 205
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getResponseStatusCode()I

    move-result v11

    .line 206
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getNegotiatedProtocol()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/Hash;->hash(Ljava/lang/String;)J

    move-result-wide v12

    .line 207
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getHeadersLatency()Lj$/time/Duration;

    move-result-object v0

    invoke-virtual {v0}, Lj$/time/Duration;->toMillis()J

    move-result-wide v2

    long-to-int v14, v2

    .line 208
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getTotalLatency()Lj$/time/Duration;

    move-result-object v0

    invoke-virtual {v0}, Lj$/time/Duration;->toMillis()J

    move-result-wide v2

    long-to-int v15, v2

    .line 209
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->wasConnectionMigrationAttempted()Z

    move-result v16

    .line 210
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->didConnectionMigrationSucceed()Z

    move-result v17

    .line 212
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getTerminalState()Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

    move-result-object v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoCronetRequestTerminalState(Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;)I

    move-result v19

    .line 213
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getNonfinalUserCallbackExceptionCount()I

    move-result v20

    .line 216
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getReadCount()I

    move-result v25

    .line 217
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getOnUploadReadCount()I

    move-result v26

    .line 218
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getIsBidiStream()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->fromBoolean(Ljava/lang/Boolean;)Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    invoke-virtual {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v27

    .line 219
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getFinalUserCallbackThrew()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->fromBoolean(Ljava/lang/Boolean;)Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    invoke-virtual {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v28

    .line 220
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getUid()I

    move-result v29

    .line 221
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getNetworkInternalErrorCode()I

    move-result v30

    .line 222
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getQuicErrorCode()I

    move-result v31

    .line 223
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getConnectionCloseSource()I

    move-result v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoConnectionCloseSource(I)I

    move-result v32

    .line 224
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getFailureReason()Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    move-result-object v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoFailureReason(Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;)I

    move-result v33

    .line 225
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getIsSocketReused()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-static {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->fromBoolean(Ljava/lang/Boolean;)Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    invoke-virtual {v0}, Lorg/chromium/net/telemetry/OptionalBoolean;->getValue()I

    move-result v34

    .line 226
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getCronetVersion()Ljava/lang/String;

    move-result-object v35

    .line 228
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getCronetSource()Lorg/chromium/net/impl/CronetLogger$CronetSource;

    move-result-object v0

    .line 227
    invoke-static {v0}, Lorg/chromium/net/telemetry/CronetLoggerImpl;->convertToProtoCronetEngineBuilderInitializedSource(Lorg/chromium/net/impl/CronetLogger$CronetSource;)I

    move-result v36

    .line 229
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getTimeToEstablishDNSMillis()J

    move-result-wide v37

    .line 230
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getTimeToEstablishSSLMillis()J

    move-result-wide v39

    .line 231
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getTimeToConnectMillis()J

    move-result-wide v41

    .line 232
    invoke-virtual/range {p3 .. p3}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->getTimeToSendFirstByteMillis()J

    move-result-wide v43

    const/16 v4, 0x2c0

    const-wide/16 v21, -0x1

    const-wide/16 v23, -0x1

    move-wide/from16 v5, p1

    move/from16 v18, p4

    .line 195
    invoke-static/range {v4 .. v44}, Lorg/chromium/net/telemetry/CronetStatsLog;->write(IJIIIIIJIIZZIIIJJIIIIIIIIIILjava/lang/String;IJJJJ)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v1, :cond_1

    .line 233
    :try_start_2
    invoke-virtual {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-void

    :catch_0
    move-exception v0

    move-object/from16 v1, p0

    goto :goto_1

    :catchall_0
    move-exception v0

    move-object v2, v0

    if-eqz v1, :cond_0

    .line 193
    :try_start_3
    invoke-virtual {v1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception v0

    :try_start_4
    invoke-virtual {v2, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_0
    :goto_0
    throw v2
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    .line 235
    :goto_1
    iget-object v1, v1, Lorg/chromium/net/telemetry/CronetLoggerImpl;->mSamplesRateLimited:Ljava/util/concurrent/atomic/AtomicInteger;

    move/from16 v2, p4

    invoke-virtual {v1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->addAndGet(I)I

    .line 236
    sget-object v1, Lorg/chromium/net/telemetry/CronetLoggerImpl;->TAG:Ljava/lang/String;

    const/4 v2, 0x3

    invoke-static {v1, v2}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 241
    invoke-static/range {p1 .. p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    filled-new-array {v2, v0}, [Ljava/lang/Object;

    move-result-object v0

    .line 239
    const-string v2, "Failed to log cronet traffic sample for CronetEngine %s: %s"

    invoke-static {v2, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 237
    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    return-void
.end method
