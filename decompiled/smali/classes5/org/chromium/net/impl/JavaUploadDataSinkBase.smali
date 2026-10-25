.class public abstract Lorg/chromium/net/impl/JavaUploadDataSinkBase;
.super Lorg/chromium/net/UploadDataSink;
.source "SourceFile"


# instance fields
.field private mBuffer:Ljava/nio/ByteBuffer;

.field private final mExecutor:Ljava/util/concurrent/Executor;

.field private mReadCount:I

.field private final mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

.field private mTotalBytes:J

.field private final mUploadProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

.field private final mUserUploadExecutor:Ljava/util/concurrent/Executor;

.field private mWrittenBytes:J


# direct methods
.method public static synthetic $r8$lambda$A9-kPLqeDYWQiy-PsztmQeDMg3o(Lorg/chromium/net/impl/JavaUploadDataSinkBase;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->lambda$startRead$1()V

    return-void
.end method

.method public static synthetic $r8$lambda$DbE3blPi6UGIsevjBlzXKVCuX1Y(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Z)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->lambda$onReadSucceeded$0(Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$Skz_oWstgCsE5yV2p_g6vrBh_QI(Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 2

    .line 187
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "JavaUploadDataSinkBase#executeOnExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " running callback"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 188
    invoke-static {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object p0

    .line 192
    :try_start_0
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p0, :cond_0

    .line 193
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p1

    if-eqz p0, :cond_1

    .line 187
    :try_start_1
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception p0

    invoke-virtual {p1, p0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    throw p1
.end method

.method public static synthetic $r8$lambda$THk-oE5eTe5Y90I_YQZikeVqrI4(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Ljava/lang/String;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->lambda$executeOnUploadExecutor$5(Ljava/lang/String;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ZABnqKHUhxos_emnBkBH8Agmjts(Lorg/chromium/net/impl/JavaUploadDataSinkBase;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->lambda$readFromProvider$3()V

    return-void
.end method

.method public static synthetic $r8$lambda$Zs_uXTVqIwC-sg8cGt-9I-Wb3F8(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Z)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->lambda$start$6(Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$r9dTVjHf2TChuPiPA7KVdo-6VVo(Lorg/chromium/net/impl/JavaUploadDataSinkBase;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->lambda$readFromProvider$2()V

    return-void
.end method

.method public constructor <init>(Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Lorg/chromium/net/UploadDataProvider;)V
    .locals 2

    .line 59
    invoke-direct {p0}, Lorg/chromium/net/UploadDataSink;-><init>()V

    .line 44
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x3

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 60
    new-instance v0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$1;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$1;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Ljava/util/concurrent/Executor;)V

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mUserUploadExecutor:Ljava/util/concurrent/Executor;

    .line 71
    iput-object p2, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mExecutor:Ljava/util/concurrent/Executor;

    .line 72
    new-instance p1, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    invoke-direct {p1, p3}, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;-><init>(Lorg/chromium/net/UploadDataProvider;)V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mUploadProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    return-void
.end method

.method private executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V
    .locals 2

    .line 183
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "JavaUploadDataSinkBase#executeOnExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 184
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v0

    .line 185
    :try_start_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mExecutor:Ljava/util/concurrent/Executor;

    new-instance v1, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;

    invoke-direct {v1, p2, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda4;-><init>(Ljava/lang/String;Ljava/lang/Runnable;)V

    invoke-interface {p0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 195
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    if-eqz v0, :cond_1

    .line 183
    :try_start_1
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception p1

    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    throw p0
.end method

.method private executeOnUploadExecutor(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V
    .locals 3

    .line 206
    const-string v0, "Cronet JavaUploadDataSinkBase#executeOnUploadExecutor "

    :try_start_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 207
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v0
    :try_end_0
    .catch Ljava/util/concurrent/RejectedExecutionException; {:try_start_0 .. :try_end_0} :catch_0

    .line 209
    :try_start_1
    iget-object v1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mUserUploadExecutor:Ljava/util/concurrent/Executor;

    new-instance v2, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;

    invoke-direct {v2, p0, p2, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda2;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Ljava/lang/String;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    invoke-interface {v1, v2}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v0, :cond_0

    .line 219
    :try_start_2
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_2
    .catch Ljava/util/concurrent/RejectedExecutionException; {:try_start_2 .. :try_end_2} :catch_0

    return-void

    :catch_0
    move-exception p1

    goto :goto_1

    :cond_0
    return-void

    :catchall_0
    move-exception p1

    if-eqz v0, :cond_1

    .line 206
    :try_start_3
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception p2

    :try_start_4
    invoke-virtual {p1, p2}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    throw p1
    :try_end_4
    .catch Ljava/util/concurrent/RejectedExecutionException; {:try_start_4 .. :try_end_4} :catch_0

    .line 220
    :goto_1
    invoke-virtual {p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->processUploadError(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$executeOnUploadExecutor$5(Ljava/lang/String;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 2

    .line 211
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUploadDataSinkBase#executeOnUploadExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " running callback"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 212
    invoke-static {p1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object p1

    .line 216
    :try_start_0
    invoke-virtual {p0, p2}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->getUploadErrorSettingRunnable(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p0

    invoke-interface {p0}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_0

    .line 217
    invoke-virtual {p1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    if-eqz p1, :cond_1

    .line 211
    :try_start_1
    invoke-virtual {p1}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception p1

    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    throw p0
.end method

.method private synthetic lambda$onReadSucceeded$0(Z)V
    .locals 8

    .line 86
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->flip()Ljava/nio/Buffer;

    move-result-object v0

    check-cast v0, Ljava/nio/ByteBuffer;

    .line 87
    iget-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mTotalBytes:J

    const-wide/16 v2, -0x1

    cmp-long v4, v0, v2

    const-string v5, "Read upload data length %d exceeds expected length %d"

    if-eqz v4, :cond_0

    iget-wide v6, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mWrittenBytes:J

    sub-long/2addr v0, v6

    iget-object v4, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {v4}, Ljava/nio/Buffer;->remaining()I

    move-result v4

    int-to-long v6, v4

    cmp-long v0, v0, v6

    if-gez v0, :cond_0

    .line 90
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object p1

    iget-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mWrittenBytes:J

    iget-object v2, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    .line 92
    invoke-virtual {v2}, Ljava/nio/Buffer;->remaining()I

    move-result v2

    int-to-long v2, v2

    add-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    iget-wide v1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mTotalBytes:J

    .line 93
    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    filled-new-array {v0, v1}, [Ljava/lang/Object;

    move-result-object v0

    .line 89
    invoke-static {p1, v5, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    .line 94
    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, v0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->processUploadError(Ljava/lang/Throwable;)V

    return-void

    .line 98
    :cond_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {v0}, Ljava/nio/Buffer;->remaining()I

    move-result v0

    if-nez v0, :cond_1

    if-nez p1, :cond_1

    .line 106
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Bytes read can\'t be zero except for last chunk!"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->processUploadError(Ljava/lang/Throwable;)V

    return-void

    .line 112
    :cond_1
    iget-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mWrittenBytes:J

    iget-object v4, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {p0, v4}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->processSuccessfulRead(Ljava/nio/ByteBuffer;)I

    move-result v4

    int-to-long v6, v4

    add-long/2addr v0, v6

    iput-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mWrittenBytes:J

    .line 114
    iget-wide v6, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mTotalBytes:J

    cmp-long v4, v0, v6

    if-ltz v4, :cond_5

    cmp-long v4, v6, v2

    if-nez v4, :cond_2

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    cmp-long p1, v6, v2

    if-nez p1, :cond_3

    .line 119
    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->finish()V

    return-void

    :cond_3
    cmp-long p1, v6, v0

    if-nez p1, :cond_4

    .line 121
    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->finish()V

    return-void

    .line 125
    :cond_4
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object p1

    iget-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mWrittenBytes:J

    .line 127
    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    iget-wide v1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mTotalBytes:J

    .line 128
    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    filled-new-array {v0, v1}, [Ljava/lang/Object;

    move-result-object v0

    .line 124
    invoke-static {p1, v5, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    .line 129
    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, v0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->processUploadError(Ljava/lang/Throwable;)V

    return-void

    .line 115
    :cond_5
    :goto_0
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {p1}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    move-result-object p1

    check-cast p1, Ljava/nio/ByteBuffer;

    .line 116
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    .line 117
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->readFromProvider()V

    return-void
.end method

.method private synthetic lambda$readFromProvider$2()V
    .locals 1

    .line 176
    iget v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mReadCount:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mReadCount:I

    return-void
.end method

.method private synthetic lambda$readFromProvider$3()V
    .locals 2

    .line 171
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mUploadProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {v0, p0, v1}, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;->read(Lorg/chromium/net/UploadDataSink;Ljava/nio/ByteBuffer;)V

    .line 174
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mExecutor:Ljava/util/concurrent/Executor;

    new-instance v1, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda6;

    invoke-direct {v1, p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda6;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$start$6(Z)V
    .locals 6

    .line 234
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mUploadProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    invoke-virtual {v0}, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;->getLength()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mTotalBytes:J

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-nez v4, :cond_0

    .line 236
    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->finish()V

    return-void

    :cond_0
    cmp-long v2, v0, v2

    const/4 v3, 0x1

    if-lez v2, :cond_1

    const-wide/16 v4, 0x2000

    cmp-long v2, v0, v4

    if-gez v2, :cond_1

    long-to-int v0, v0

    add-int/2addr v0, v3

    .line 243
    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    goto :goto_0

    :cond_1
    const/16 v0, 0x2000

    .line 245
    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mBuffer:Ljava/nio/ByteBuffer;

    .line 248
    :goto_0
    iget-wide v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mTotalBytes:J

    invoke-virtual {p0, v0, v1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->initializeStart(J)V

    if-eqz p1, :cond_2

    .line 251
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->startRead()V

    return-void

    .line 253
    :cond_2
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {p1, v3}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    .line 254
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mUploadProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    invoke-virtual {p1, p0}, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;->rewind(Lorg/chromium/net/UploadDataSink;)V

    return-void
.end method

.method private synthetic lambda$startRead$1()V
    .locals 2

    .line 161
    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->initializeRead()V

    .line 162
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    .line 163
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->readFromProvider()V

    return-void
.end method

.method private readFromProvider()V
    .locals 2

    .line 169
    new-instance v0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda5;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda5;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;)V

    const-string v1, "readFromProvider"

    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->executeOnUploadExecutor(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V

    return-void
.end method

.method private startRead()V
    .locals 2

    .line 158
    new-instance v0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda3;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;)V

    .line 159
    invoke-virtual {p0, v0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->getErrorSettingRunnable(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object v0

    const-string v1, "startRead"

    .line 158
    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public abstract finish()V
.end method

.method public abstract getErrorSettingRunnable(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
.end method

.method public getReadCount()I
    .locals 0

    .line 267
    iget p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mReadCount:I

    return p0
.end method

.method public abstract getUploadErrorSettingRunnable(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
.end method

.method public abstract initializeRead()V
.end method

.method public abstract initializeStart(J)V
.end method

.method public onReadSucceeded(Z)V
    .locals 3

    .line 77
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 84
    new-instance v0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda1;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Z)V

    .line 132
    invoke-virtual {p0, v0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->getErrorSettingRunnable(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p1

    const-string v0, "onReadSucceeded"

    invoke-direct {p0, p1, v0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void

    .line 80
    :cond_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 82
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p0

    const-string p1, "onReadSucceeded() called when not awaiting a read result; in state: "

    invoke-static {p1, p0}, Lorg/telegram/ui/WearAuthSheet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-void
.end method

.method public onRewindSucceeded()V
    .locals 3

    .line 137
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x1

    const/4 v2, 0x2

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 144
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->startRead()V

    return-void

    .line 140
    :cond_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->mSinkState:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 142
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p0

    const-string v0, "onRewindSucceeded() called when not awaiting a rewind; in state: "

    invoke-static {v0, p0}, Lorg/telegram/ui/WearAuthSheet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-void
.end method

.method public abstract processSuccessfulRead(Ljava/nio/ByteBuffer;)I
.end method

.method public abstract processUploadError(Ljava/lang/Throwable;)V
.end method

.method public start(Z)V
    .locals 1

    .line 232
    new-instance v0, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase$$ExternalSyntheticLambda0;-><init>(Lorg/chromium/net/impl/JavaUploadDataSinkBase;Z)V

    const-string p1, "start"

    invoke-direct {p0, v0, p1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->executeOnUploadExecutor(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V

    return-void
.end method
