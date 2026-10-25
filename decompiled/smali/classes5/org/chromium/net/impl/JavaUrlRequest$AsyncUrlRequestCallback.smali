.class final Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/JavaUrlRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "AsyncUrlRequestCallback"
.end annotation


# instance fields
.field final mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

.field final mFallbackExecutor:Ljava/util/concurrent/Executor;

.field final mUserExecutor:Ljava/util/concurrent/Executor;

.field final synthetic this$0:Lorg/chromium/net/impl/JavaUrlRequest;


# direct methods
.method public static synthetic $r8$lambda$-ElxwdM5p9ntEnR5c00jhA5ySA4(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$onSucceeded$6(Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public static synthetic $r8$lambda$D3Lg1jcdUAIZEKduVfK-uga6A4E(Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 2

    .line 1134
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnUserExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " running callback"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1135
    invoke-static {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object p0

    .line 1141
    :try_start_0
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p0, :cond_0

    .line 1142
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p1

    if-eqz p0, :cond_1

    .line 1134
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

.method public static synthetic $r8$lambda$L2MmDQF1IP9TU3XYnKRRuW-u5Xs(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$onReadCompleted$3(Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    return-void
.end method

.method public static synthetic $r8$lambda$RXe1m0PdxXhwUrKBIben1xR2I_M(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$onRedirectReceived$1(Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$U-zbUASvl6MLjUvoY4iIFSdcqHE(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$onCanceled$5(Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public static synthetic $r8$lambda$WCDd2pugEgBS6NGVWYDm2iefcwo(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$onResponseStarted$2()V

    return-void
.end method

.method public static synthetic $r8$lambda$YvZtwCJrGUY8GOrtOFEOSZ9D6T8(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$onFailed$7(Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method public static synthetic $r8$lambda$_9B4GRLfAWjHu6e_D7q3tew4R6c(Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 2

    .line 1156
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnFallbackExecutor  "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " running callback"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1157
    invoke-static {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object p0

    .line 1164
    :try_start_0
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p0, :cond_0

    .line 1165
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p1

    if-eqz p0, :cond_1

    .line 1156
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

.method public static synthetic $r8$lambda$wBc_bJEQLJvr4m_oENjqxQ9IYig(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->lambda$maybeReportMetrics$4()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/UrlRequest$Callback;Ljava/util/concurrent/Executor;)V
    .locals 1

    .line 882
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 883
    new-instance v0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    invoke-direct {v0, p2}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;-><init>(Lorg/chromium/net/UrlRequest$Callback;)V

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    .line 884
    invoke-static {p1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmAllowDirectExecutor(Lorg/chromium/net/impl/JavaUrlRequest;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 885
    iput-object p3, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mUserExecutor:Ljava/util/concurrent/Executor;

    const/4 p1, 0x0

    .line 886
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mFallbackExecutor:Ljava/util/concurrent/Executor;

    return-void

    .line 888
    :cond_0
    new-instance p1, Lorg/chromium/net/impl/JavaUrlRequestUtils$DirectPreventingExecutor;

    invoke-direct {p1, p3}, Lorg/chromium/net/impl/JavaUrlRequestUtils$DirectPreventingExecutor;-><init>(Ljava/util/concurrent/Executor;)V

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mUserExecutor:Ljava/util/concurrent/Executor;

    .line 889
    iput-object p3, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mFallbackExecutor:Ljava/util/concurrent/Executor;

    return-void
.end method

.method private buildCronetTrafficInfo()Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;
    .locals 42

    move-object/from16 v0, p0

    .line 961
    iget-object v1, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;

    move-result-object v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    .line 962
    iget-object v1, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;

    move-result-object v1

    invoke-virtual {v1}, Lorg/chromium/net/impl/UrlResponseInfoImpl;->getAllHeaders()Ljava/util/Map;

    move-result-object v1

    .line 963
    iget-object v3, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v3}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;

    move-result-object v3

    invoke-virtual {v3}, Lorg/chromium/net/impl/UrlResponseInfoImpl;->getNegotiatedProtocol()Ljava/lang/String;

    move-result-object v3

    .line 964
    iget-object v4, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v4}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;

    move-result-object v4

    invoke-virtual {v4}, Lorg/chromium/net/impl/UrlResponseInfoImpl;->getHttpStatusCode()I

    move-result v4

    .line 965
    iget-object v5, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v5}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;

    move-result-object v5

    invoke-virtual {v5}, Lorg/chromium/net/impl/UrlResponseInfoImpl;->wasCached()Z

    move-result v5

    move v12, v4

    :goto_0
    move-object v15, v3

    goto :goto_1

    .line 967
    :cond_0
    sget-object v1, Ljava/util/Collections;->EMPTY_MAP:Ljava/util/Map;

    .line 970
    const-string v3, ""

    move v5, v2

    move v12, v5

    goto :goto_0

    :goto_1
    const-wide/16 v3, -0x1

    const-wide/16 v6, 0x0

    if-eqz v5, :cond_1

    move-wide v8, v6

    move-wide v10, v8

    goto :goto_2

    .line 979
    :cond_1
    iget-object v8, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v8}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmRequestHeaders(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/util/Map;

    move-result-object v8

    invoke-static {v8}, Lorg/chromium/net/impl/JavaUrlRequest;->estimateHeadersSizeInBytes(Ljava/util/Map;)J

    move-result-wide v8

    move-wide v10, v6

    move-wide v6, v3

    :goto_2
    if-eqz v5, :cond_2

    move-wide v3, v10

    move-wide v13, v3

    goto :goto_3

    .line 990
    :cond_2
    invoke-static {v1}, Lorg/chromium/net/impl/JavaUrlRequest;->estimateHeadersSizeInBytesList(Ljava/util/Map;)J

    move-result-wide v13

    .line 993
    const-string v5, "Content-Length"

    invoke-interface {v1, v5}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v16

    if-eqz v16, :cond_3

    .line 995
    invoke-interface {v1, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/List;

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-static {v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$smparseContentLengthString(Ljava/lang/String;)J

    move-result-wide v3

    .line 1002
    :cond_3
    :goto_3
    invoke-static {v10, v11}, Lj$/time/Duration;->ofSeconds(J)Lj$/time/Duration;

    move-result-object v1

    .line 1003
    invoke-static {v10, v11}, Lj$/time/Duration;->ofSeconds(J)Lj$/time/Duration;

    move-result-object v5

    .line 1005
    iget-object v10, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v10}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmState(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v10

    invoke-virtual {v10}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v10

    const/4 v11, 0x6

    if-eq v10, v11, :cond_6

    const/4 v11, 0x7

    if-eq v10, v11, :cond_5

    const/16 v11, 0x8

    if-ne v10, v11, :cond_4

    .line 1015
    sget-object v10, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;->CANCELLED:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

    :goto_4
    move-object/from16 v18, v10

    move-wide v10, v3

    goto :goto_5

    .line 1018
    :cond_4
    const-string v0, "Internal Cronet error: attempted to report metrics but current state ("

    const-string v1, ") is not a done state!"

    invoke-static {v0, v10, v1}, Landroidx/exifinterface/media/ExifInterface$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;ILjava/lang/Object;)V

    const/4 v0, 0x0

    return-object v0

    .line 1009
    :cond_5
    sget-object v10, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;->SUCCEEDED:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

    goto :goto_4

    .line 1012
    :cond_6
    sget-object v10, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;->ERROR:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

    goto :goto_4

    .line 1024
    :goto_5
    new-instance v3, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;

    iget-object v4, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v4}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmNonfinalUserCallbackExceptionCount(Lorg/chromium/net/impl/JavaUrlRequest;)I

    move-result v19

    iget-object v4, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v4}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmReadCount(Lorg/chromium/net/impl/JavaUrlRequest;)I

    move-result v20

    .line 1039
    iget-object v4, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v4}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmOutputStreamDataSink(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

    move-result-object v4

    if-nez v4, :cond_7

    :goto_6
    move/from16 v21, v2

    goto :goto_7

    :cond_7
    iget-object v2, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v2}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmOutputStreamDataSink(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

    move-result-object v2

    invoke-virtual {v2}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->getReadCount()I

    move-result v2

    goto :goto_6

    :goto_7
    iget-object v0, v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmFinalUserCallbackThrew(Lorg/chromium/net/impl/JavaUrlRequest;)Z

    move-result v23

    .line 1042
    invoke-static {}, Landroid/os/Process;->myUid()I

    move-result v24

    sget-object v28, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->UNKNOWN:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    .line 1048
    invoke-static {}, Lorg/chromium/net/impl/ImplVersion;->getCronetVersion()Ljava/lang/String;

    move-result-object v30

    sget-object v31, Lorg/chromium/net/impl/CronetLogger$CronetSource;->CRONET_SOURCE_FALLBACK:Lorg/chromium/net/impl/CronetLogger$CronetSource;

    const-wide/16 v36, -0x1

    const-wide/16 v38, -0x1

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v22, 0x0

    const/16 v25, 0x0

    const/16 v26, 0x0

    const/16 v27, 0x0

    const/16 v29, 0x0

    const-wide/16 v32, -0x1

    const-wide/16 v34, -0x1

    move-wide/from16 v40, v13

    move-object v14, v5

    move-wide v4, v8

    move-wide/from16 v8, v40

    move-object v13, v1

    invoke-direct/range {v3 .. v39}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;-><init>(JJJJILj$/time/Duration;Lj$/time/Duration;Ljava/lang/String;ZZLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;IIIZZIIIILorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;ZLjava/lang/String;Lorg/chromium/net/impl/CronetLogger$CronetSource;JJJJ)V

    return-object v3
.end method

.method private synthetic lambda$maybeReportMetrics$4()V
    .locals 3

    .line 1067
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmLogger(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/CronetLogger;

    move-result-object v0

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmCronetEngineId(Lorg/chromium/net/impl/JavaUrlRequest;)I

    move-result v1

    int-to-long v1, v1

    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->buildCronetTrafficInfo()Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;

    move-result-object p0

    invoke-virtual {v0, v1, v2, p0}, Lorg/chromium/net/impl/CronetLogger;->logCronetTrafficInfo(JLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 1071
    invoke-static {}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$sfgetTAG()Ljava/lang/String;

    move-result-object v0

    const-string v1, "Error while trying to log CronetTrafficInfo: "

    invoke-static {v0, v1, p0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-void
.end method

.method private synthetic lambda$onCanceled$5(Lorg/chromium/net/UrlResponseInfo;)V
    .locals 2

    .line 1082
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-virtual {v0, v1, p1}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->onCanceled(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    .line 1084
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    const-string v1, "onCanceled"

    invoke-static {v0, v1, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$monFinalCallbackException(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;Ljava/lang/Exception;)V

    .line 1086
    :goto_0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->maybeReportMetrics()V

    .line 1087
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmEngine(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaCronetEngine;

    move-result-object p0

    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaCronetEngine;->decrementActiveRequestCount()V

    return-void
.end method

.method private synthetic lambda$onFailed$7(Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    .locals 2

    .line 1111
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-virtual {v0, v1, p1, p2}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->onFailed(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    .line 1113
    iget-object p2, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    const-string v0, "onFailed"

    invoke-static {p2, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$monFinalCallbackException(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;Ljava/lang/Exception;)V

    .line 1115
    :goto_0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->maybeReportMetrics()V

    .line 1116
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmEngine(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaCronetEngine;

    move-result-object p0

    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaCronetEngine;->decrementActiveRequestCount()V

    return-void
.end method

.method private synthetic lambda$onReadCompleted$3(Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 3

    .line 933
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmState(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v0

    const/4 v1, 0x5

    const/4 v2, 0x4

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 936
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-virtual {v0, p0, p1, p2}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->onReadCompleted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$onRedirectReceived$1(Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V
    .locals 1

    .line 913
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-virtual {v0, p0, p1, p2}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->onRedirectReceived(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$onResponseStarted$2()V
    .locals 3

    .line 921
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmState(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v0

    const/4 v1, 0x1

    const/4 v2, 0x4

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 924
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;

    move-result-object v1

    invoke-virtual {v0, p0, v1}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->onResponseStarted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$onSucceeded$6(Lorg/chromium/net/UrlResponseInfo;)V
    .locals 2

    .line 1096
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mCallback:Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-virtual {v0, v1, p1}, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->onSucceeded(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    .line 1098
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    const-string v1, "onSucceded"

    invoke-static {v0, v1, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$monFinalCallbackException(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;Ljava/lang/Exception;)V

    .line 1100
    :goto_0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->maybeReportMetrics()V

    .line 1101
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$fgetmEngine(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaCronetEngine;

    move-result-object p0

    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaCronetEngine;->decrementActiveRequestCount()V

    return-void
.end method

.method private maybeReportMetrics()V
    .locals 2

    .line 1059
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    if-ge v0, v1, :cond_0

    return-void

    .line 1064
    :cond_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    new-instance v1, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda7;

    invoke-direct {v1, p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda7;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;)V

    const-string p0, "maybeReportMetrics"

    invoke-static {v0, v1, p0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$mexecuteOnExecutor(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public execute(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V
    .locals 1

    .line 904
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$muserErrorSetting(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p1

    invoke-virtual {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->executeOnUserExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/util/concurrent/RejectedExecutionException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p1

    .line 906
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    new-instance p2, Lorg/chromium/net/impl/CronetExceptionImpl;

    const-string v0, "Exception posting task to executor"

    invoke-direct {p2, v0, p1}, Lorg/chromium/net/impl/CronetExceptionImpl;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-static {p0, p2}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$menterErrorState(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method public executeOnFallbackExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V
    .locals 2

    .line 1148
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnFallbackExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1149
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v0

    .line 1154
    :try_start_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mFallbackExecutor:Ljava/util/concurrent/Executor;

    new-instance v1, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda5;

    invoke-direct {v1, p2, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda5;-><init>(Ljava/lang/String;Ljava/lang/Runnable;)V

    invoke-interface {p0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 1167
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    if-eqz v0, :cond_1

    .line 1148
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

.method public executeOnUserExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V
    .locals 2

    .line 1128
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnUserExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1129
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v0

    .line 1132
    :try_start_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mUserExecutor:Ljava/util/concurrent/Executor;

    new-instance v1, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda4;

    invoke-direct {v1, p2, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda4;-><init>(Ljava/lang/String;Ljava/lang/Runnable;)V

    invoke-interface {p0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 1144
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    if-eqz v0, :cond_1

    .line 1128
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

.method public onCanceled(Lorg/chromium/net/UrlResponseInfo;)V
    .locals 1

    .line 1078
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v0}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$mcloseResponseChannel(Lorg/chromium/net/impl/JavaUrlRequest;)V

    .line 1079
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda0;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;)V

    const-string p1, "onCanceled"

    invoke-virtual {p0, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->executeOnUserExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method public onFailed(Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    .locals 2

    .line 1107
    const-string v0, "onFailed"

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->this$0:Lorg/chromium/net/impl/JavaUrlRequest;

    invoke-static {v1}, Lorg/chromium/net/impl/JavaUrlRequest;->-$$Nest$mcloseResponseChannel(Lorg/chromium/net/impl/JavaUrlRequest;)V

    .line 1108
    new-instance v1, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda2;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V

    .line 1119
    :try_start_0
    invoke-virtual {p0, v1, v0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->executeOnUserExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V
    :try_end_0
    .catch Lorg/chromium/net/InlineExecutionProhibitedException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 1121
    :catch_0
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->mFallbackExecutor:Ljava/util/concurrent/Executor;

    if-eqz p1, :cond_0

    .line 1122
    invoke-virtual {p0, v1, v0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->executeOnFallbackExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method public onReadCompleted(Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 1

    .line 931
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    const-string p1, "onReadCompleted"

    invoke-virtual {p0, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->execute(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V

    return-void
.end method

.method public onRedirectReceived(Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V
    .locals 1

    .line 911
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda8;

    invoke-direct {v0, p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda8;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V

    const-string p1, "onRedirectReceived"

    invoke-virtual {p0, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->execute(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V

    return-void
.end method

.method public onResponseStarted()V
    .locals 2

    .line 919
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;)V

    const-string v1, "onResponseStarted"

    invoke-virtual {p0, v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->execute(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;Ljava/lang/String;)V

    return-void
.end method

.method public onSucceeded(Lorg/chromium/net/UrlResponseInfo;)V
    .locals 1

    .line 1093
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda1;-><init>(Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;Lorg/chromium/net/UrlResponseInfo;)V

    const-string p1, "onSucceeded"

    invoke-virtual {p0, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->executeOnUserExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method
