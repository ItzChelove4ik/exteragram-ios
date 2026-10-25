.class final Lorg/chromium/net/impl/JavaUrlRequest;
.super Lorg/chromium/net/ExperimentalUrlRequest;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;,
        Lorg/chromium/net/impl/JavaUrlRequest$SerializingExecutor;,
        Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;
    }
.end annotation


# static fields
.field private static final TAG:Ljava/lang/String; = "JavaUrlRequest"


# instance fields
.field private volatile mAdditionalStatusDetails:I

.field private final mAllowDirectExecutor:Z

.field private final mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

.field private final mCronetEngineId:I

.field private mCurrentUrl:Ljava/lang/String;

.field private mCurrentUrlConnection:Ljava/net/HttpURLConnection;

.field private final mEngine:Lorg/chromium/net/impl/JavaCronetEngine;

.field private final mExecutor:Ljava/util/concurrent/Executor;

.field private mFinalUserCallbackThrew:Z

.field private final mInitialMethod:Ljava/lang/String;

.field private final mLogger:Lorg/chromium/net/impl/CronetLogger;

.field private final mNetworkHandle:J

.field private mNonfinalUserCallbackExceptionCount:I

.field private mOutputStreamDataSink:Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

.field private mPendingRedirectUrl:Ljava/lang/String;

.field private mReadCount:I

.field private final mRequestHeaders:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

.field private final mState:Ljava/util/concurrent/atomic/AtomicInteger;

.field private final mUploadDataProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

.field private final mUploadExecutor:Ljava/util/concurrent/Executor;

.field private final mUploadProviderClosed:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private final mUrlChain:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

.field private final mUserAgent:Ljava/lang/String;


# direct methods
.method public static synthetic $r8$lambda$02qzLzE_VPpZarHuSLhMV6jVulc(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$fireGetHeaders$4()V

    return-void
.end method

.method public static synthetic $r8$lambda$38dzsNc1zmdQHSuBA_JyRMFyZtI(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$start$2()V

    return-void
.end method

.method public static synthetic $r8$lambda$8Jo97Oa6sMq63KSfyXfVRlvw9I0(IZILjava/lang/Runnable;)V
    .locals 1

    .line 230
    invoke-static {}, Landroid/net/TrafficStats;->getThreadStatsTag()I

    move-result v0

    .line 231
    invoke-static {p0}, Landroid/net/TrafficStats;->setThreadStatsTag(I)V

    if-eqz p1, :cond_0

    .line 233
    invoke-static {p2}, Lorg/chromium/net/ThreadStatsUid;->set(I)V

    .line 236
    :cond_0
    :try_start_0
    invoke-interface {p3}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_1

    .line 239
    invoke-static {}, Lorg/chromium/net/ThreadStatsUid;->clear()V

    .line 241
    :cond_1
    invoke-static {v0}, Landroid/net/TrafficStats;->setThreadStatsTag(I)V

    return-void

    :catchall_0
    move-exception p0

    if-eqz p1, :cond_2

    .line 239
    invoke-static {}, Lorg/chromium/net/ThreadStatsUid;->clear()V

    .line 241
    :cond_2
    invoke-static {v0}, Landroid/net/TrafficStats;->setThreadStatsTag(I)V

    .line 242
    throw p0
.end method

.method public static synthetic $r8$lambda$GSdwLjQ4bmMzASRLACmvH5xfWmo(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$fireOpenConnection$7()V

    return-void
.end method

.method public static synthetic $r8$lambda$NQYCbe0U94j06X1DTQBiU1HE4R4(Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 2

    .line 1209
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUrlRequest#executeOnExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " running callback"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1210
    invoke-static {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object p0

    .line 1214
    :try_start_0
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p0, :cond_0

    .line 1215
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p1

    if-eqz p0, :cond_1

    .line 1209
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

.method public static synthetic $r8$lambda$PIFh1790Oeyx5a3X_KbAZQz5meg(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$userErrorSetting$9(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Wkt5Drg-UQxBhoSqw7stujcUC7Y(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$read$12(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$_wWXlBR8WJ1mu3-hFNKhRrcaJoA(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$closeResponseChannel$14()V

    return-void
.end method

.method public static synthetic $r8$lambda$aHdNiaUE4hHQdj0jbu3NBWOvxDg(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$uploadErrorSetting$10(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$bMvZoSNaUr4APxhn21vnlY_ECAQ(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$errorSetting$8(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$bR7r4RzNBFEh7BVU9n2PGU-qg68(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$fireRedirectReceived$6(Ljava/lang/String;Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ecpyyaPHU986yHiuxnDquO9vmIo(Ljava/util/concurrent/Executor;IZILjava/lang/Runnable;)V
    .locals 1

    .line 228
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;

    invoke-direct {v0, p1, p2, p3, p4}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda8;-><init>(IZILjava/lang/Runnable;)V

    invoke-interface {p0, v0}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$gJzZ29OPTZVGbpITnuc1VPRlWyc(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$fireDisconnect$13()V

    return-void
.end method

.method public static synthetic $r8$lambda$r4_dhdLDT53SSUzE8bdt2qsjO3Q(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$fireRedirectReceived$5(Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public static synthetic $r8$lambda$u-jVhrWpEjz9BKtoI5JBlbbMeKM(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$enterUserErrorState$3()V

    return-void
.end method

.method public static synthetic $r8$lambda$yRZjnistK_LSFIhhQZTjA_r2CdQ(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->lambda$read$11(Ljava/nio/ByteBuffer;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetmAllowDirectExecutor(Lorg/chromium/net/impl/JavaUrlRequest;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mAllowDirectExecutor:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetmCronetEngineId(Lorg/chromium/net/impl/JavaUrlRequest;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCronetEngineId:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetmEngine(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaCronetEngine;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mEngine:Lorg/chromium/net/impl/JavaCronetEngine;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmFinalUserCallbackThrew(Lorg/chromium/net/impl/JavaUrlRequest;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mFinalUserCallbackThrew:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetmLogger(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/CronetLogger;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mLogger:Lorg/chromium/net/impl/CronetLogger;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmNonfinalUserCallbackExceptionCount(Lorg/chromium/net/impl/JavaUrlRequest;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mNonfinalUserCallbackExceptionCount:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetmOutputStreamDataSink(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mOutputStreamDataSink:Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmPendingRedirectUrl(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/lang/String;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mPendingRedirectUrl:Ljava/lang/String;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmReadCount(Lorg/chromium/net/impl/JavaUrlRequest;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mReadCount:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetmRequestHeaders(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/util/Map;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmState(Lorg/chromium/net/impl/JavaUrlRequest;)Ljava/util/concurrent/atomic/AtomicInteger;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmUrlResponseInfo(Lorg/chromium/net/impl/JavaUrlRequest;)Lorg/chromium/net/impl/UrlResponseInfoImpl;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fputmAdditionalStatusDetails(Lorg/chromium/net/impl/JavaUrlRequest;I)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mAdditionalStatusDetails:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputmCurrentUrl(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrl:Ljava/lang/String;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputmPendingRedirectUrl(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mPendingRedirectUrl:Ljava/lang/String;

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcloseResponseChannel(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->closeResponseChannel()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$menterErrorState(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/CronetException;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->enterErrorState(Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$menterUploadErrorState(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/Throwable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->enterUploadErrorState(Ljava/lang/Throwable;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$merrorSetting(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->errorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mexecuteOnExecutor(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/Runnable;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mfireGetHeaders(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireGetHeaders()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mfireOpenConnection(Lorg/chromium/net/impl/JavaUrlRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireOpenConnection()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$monFinalCallbackException(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest;->onFinalCallbackException(Ljava/lang/String;Ljava/lang/Exception;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$muploadErrorSetting(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->uploadErrorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$muserErrorSetting(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->userErrorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$sfgetTAG()Ljava/lang/String;
    .locals 1

    .line 0
    sget-object v0, Lorg/chromium/net/impl/JavaUrlRequest;->TAG:Ljava/lang/String;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$smparseContentLengthString(Ljava/lang/String;)J
    .locals 2

    .line 0
    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->parseContentLengthString(Ljava/lang/String;)J

    move-result-wide v0

    return-wide v0
.end method

.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Lorg/chromium/net/impl/JavaCronetEngine;Lorg/chromium/net/UrlRequest$Callback;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/lang/String;Ljava/lang/String;ZZIZIJLjava/lang/String;Ljava/util/ArrayList;Lorg/chromium/net/UploadDataProvider;Ljava/util/concurrent/Executor;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/chromium/net/impl/JavaCronetEngine;",
            "Lorg/chromium/net/UrlRequest$Callback;",
            "Ljava/util/concurrent/Executor;",
            "Ljava/util/concurrent/Executor;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "ZZIZIJ",
            "Ljava/lang/String;",
            "Ljava/util/ArrayList<",
            "Ljava/util/Map$Entry<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;",
            "Lorg/chromium/net/UploadDataProvider;",
            "Ljava/util/concurrent/Executor;",
            ")V"
        }
    .end annotation

    move-object/from16 v1, p17

    .line 214
    invoke-direct {p0}, Lorg/chromium/net/ExperimentalUrlRequest;-><init>()V

    .line 73
    new-instance v2, Ljava/util/TreeMap;

    sget-object v3, Ljava/lang/String;->CASE_INSENSITIVE_ORDER:Ljava/util/Comparator;

    invoke-direct {v2, v3}, Ljava/util/TreeMap;-><init>(Ljava/util/Comparator;)V

    iput-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    .line 75
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    iput-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlChain:Ljava/util/List;

    .line 86
    new-instance v2, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v3, 0x0

    invoke-direct {v2, v3}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 88
    new-instance v2, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v2, v3}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadProviderClosed:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v2, -0x1

    .line 107
    iput v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mAdditionalStatusDetails:I

    .line 215
    const-string v2, "Cronet JavaUrlRequest#JavaUrlRequest"

    invoke-static {v2}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v2

    .line 216
    :try_start_0
    const-string v3, "URL is required"

    invoke-static {p5, v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 217
    const-string v3, "Listener is required"

    invoke-static {p2, v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 218
    const-string v3, "Executor is required"

    invoke-static {p3, v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 219
    const-string/jumbo v3, "userExecutor is required"

    invoke-static {p4, v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 221
    iput-boolean p7, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mAllowDirectExecutor:Z

    .line 222
    new-instance v3, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    invoke-direct {v3, p0, p2, p4}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/UrlRequest$Callback;Ljava/util/concurrent/Executor;)V

    iput-object v3, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    if-eqz p8, :cond_0

    move p2, p9

    goto :goto_0

    .line 224
    :cond_0
    invoke-static {}, Landroid/net/TrafficStats;->getThreadStatsTag()I

    move-result p2

    .line 225
    :goto_0
    new-instance p4, Lorg/chromium/net/impl/JavaUrlRequest$SerializingExecutor;

    new-instance v3, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;

    move/from16 v4, p10

    move/from16 v5, p11

    invoke-direct {v3, p3, p2, v4, v5}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda2;-><init>(Ljava/util/concurrent/Executor;IZI)V

    invoke-direct {p4, v3}, Lorg/chromium/net/impl/JavaUrlRequest$SerializingExecutor;-><init>(Ljava/util/concurrent/Executor;)V

    iput-object p4, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mExecutor:Ljava/util/concurrent/Executor;

    .line 245
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mEngine:Lorg/chromium/net/impl/JavaCronetEngine;

    .line 246
    invoke-virtual {p1}, Lorg/chromium/net/impl/JavaCronetEngine;->getCronetEngineId()I

    move-result p2

    iput p2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCronetEngineId:I

    .line 247
    invoke-virtual {p1}, Lorg/chromium/net/impl/JavaCronetEngine;->getCronetLogger()Lorg/chromium/net/impl/CronetLogger;

    move-result-object p1

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mLogger:Lorg/chromium/net/impl/CronetLogger;

    .line 248
    iput-object p5, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrl:Ljava/lang/String;

    .line 249
    iput-object p6, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUserAgent:Ljava/lang/String;

    move-wide/from16 p1, p12

    .line 250
    iput-wide p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mNetworkHandle:J

    .line 251
    invoke-static/range {p14 .. p14}, Lorg/chromium/net/impl/JavaUrlRequest;->checkedHttpMethod(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mInitialMethod:Ljava/lang/String;

    move-object/from16 p1, p15

    .line 252
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->setHeaders(Ljava/util/ArrayList;)V

    move-object/from16 p1, p16

    .line 253
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->checkedUploadDataProvider(Lorg/chromium/net/UploadDataProvider;)Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    move-result-object p1

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadDataProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    if-eqz v1, :cond_2

    if-eqz p7, :cond_1

    goto :goto_1

    .line 257
    :cond_1
    new-instance p1, Lorg/chromium/net/impl/JavaUrlRequestUtils$DirectPreventingExecutor;

    invoke-direct {p1, v1}, Lorg/chromium/net/impl/JavaUrlRequestUtils$DirectPreventingExecutor;-><init>(Ljava/util/concurrent/Executor;)V

    goto :goto_2

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_3

    :cond_2
    :goto_1
    move-object p1, v1

    :goto_2
    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadExecutor:Ljava/util/concurrent/Executor;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_3

    .line 258
    invoke-virtual {v2}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_3
    return-void

    :goto_3
    if-eqz v2, :cond_4

    .line 215
    :try_start_1
    invoke-virtual {v2}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_4

    :catchall_1
    move-exception v0

    move-object p1, v0

    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_4
    :goto_4
    throw p0
.end method

.method private static checkedHttpMethod(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 262
    const-string v0, "Method is required."

    invoke-static {p0, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 263
    const-string v0, "OPTIONS"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "GET"

    .line 264
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "HEAD"

    .line 265
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "POST"

    .line 266
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "PUT"

    .line 267
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "DELETE"

    .line 268
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "TRACE"

    .line 269
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "PATCH"

    .line 270
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 273
    :cond_0
    const-string v0, "Invalid http method "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    :cond_1
    :goto_0
    return-object p0
.end method

.method private checkedUploadDataProvider(Lorg/chromium/net/UploadDataProvider;)Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;
    .locals 2

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return-object v0

    .line 324
    :cond_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    const-string v1, "Content-Type"

    invoke-interface {p0, v1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 328
    new-instance p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    invoke-direct {p0, p1}, Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;-><init>(Lorg/chromium/net/UploadDataProvider;)V

    return-object p0

    .line 325
    :cond_1
    const-string p0, "Requests with upload data must have a Content-Type."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method

.method private closeResponseChannel()V
    .locals 2

    .line 1172
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda11;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda11;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    const-string v1, "closeResponseChannel"

    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method private enterCronetErrorState(Ljava/lang/Throwable;)V
    .locals 2

    .line 477
    new-instance v0, Lorg/chromium/net/impl/CronetExceptionImpl;

    const-string v1, "System error"

    invoke-direct {v0, v1, p1}, Lorg/chromium/net/impl/CronetExceptionImpl;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->enterErrorState(Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method private enterErrorState(Lorg/chromium/net/CronetException;)V
    .locals 1

    const/4 v0, 0x6

    .line 424
    invoke-direct {p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->setTerminalState(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 425
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireDisconnect()V

    .line 426
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireCloseUploadDataProvider()V

    .line 427
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

    invoke-virtual {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onFailed(Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V

    :cond_0
    return-void
.end method

.method private enterUploadErrorState(Ljava/lang/Throwable;)V
    .locals 2

    .line 470
    new-instance v0, Lorg/chromium/net/impl/CallbackExceptionImpl;

    const-string v1, "Exception received from UploadDataProvider"

    invoke-direct {v0, v1, p1}, Lorg/chromium/net/impl/CallbackExceptionImpl;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->enterErrorState(Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method private enterUserErrorState(Ljava/lang/Throwable;)V
    .locals 2

    .line 458
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda16;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda16;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    const-string v1, "enterUserErrorState"

    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    .line 464
    new-instance v0, Lorg/chromium/net/impl/CallbackExceptionImpl;

    const-string v1, "Exception received from UrlRequest.Callback"

    invoke-direct {v0, v1, p1}, Lorg/chromium/net/impl/CallbackExceptionImpl;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->enterErrorState(Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method private errorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
    .locals 1

    .line 680
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda12;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-object v0
.end method

.method public static estimateHeadersSizeInBytes(Ljava/util/Map;)J
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)J"
        }
    .end annotation

    const-wide/16 v0, 0x0

    if-nez p0, :cond_0

    return-wide v0

    .line 827
    :cond_0
    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 828
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    if-eqz v3, :cond_2

    .line 829
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    int-to-long v3, v3

    add-long/2addr v0, v3

    .line 830
    :cond_2
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    if-eqz v2, :cond_1

    .line 831
    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    int-to-long v2, v2

    add-long/2addr v0, v2

    goto :goto_0

    :cond_3
    return-wide v0
.end method

.method public static estimateHeadersSizeInBytesList(Ljava/util/Map;)J
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;)J"
        }
    .end annotation

    const-wide/16 v0, 0x0

    if-nez p0, :cond_0

    return-wide v0

    .line 807
    :cond_0
    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_5

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 808
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    if-eqz v3, :cond_2

    .line 809
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    int-to-long v3, v3

    add-long/2addr v0, v3

    .line 810
    :cond_2
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    if-nez v3, :cond_3

    goto :goto_0

    .line 812
    :cond_3
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_4
    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    if-eqz v3, :cond_4

    .line 813
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    int-to-long v3, v3

    add-long/2addr v0, v3

    goto :goto_1

    :cond_5
    return-wide v0
.end method

.method private executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V
    .locals 2

    .line 1205
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Cronet JavaUrlRequest#executeOnExecutor "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1206
    invoke-static {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object v0

    .line 1207
    :try_start_0
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mExecutor:Ljava/util/concurrent/Executor;

    new-instance v1, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda5;

    invoke-direct {v1, p2, p1}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda5;-><init>(Ljava/lang/String;Ljava/lang/Runnable;)V

    invoke-interface {p0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 1217
    invoke-virtual {v0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    if-eqz v0, :cond_1

    .line 1205
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

.method private fireCloseUploadDataProvider()V
    .locals 3

    .line 596
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadDataProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadProviderClosed:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    const/4 v2, 0x1

    .line 597
    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 600
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadExecutor:Ljava/util/concurrent/Executor;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadDataProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    invoke-static {v1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda0;

    invoke-direct {v2, v1}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda0;-><init>(Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;)V

    invoke-direct {p0, v2}, Lorg/chromium/net/impl/JavaUrlRequest;->uploadErrorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p0

    invoke-interface {v0, p0}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_0
    .catch Ljava/util/concurrent/RejectedExecutionException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 602
    sget-object v0, Lorg/chromium/net/impl/JavaUrlRequest;->TAG:Ljava/lang/String;

    const-string v1, "Exception when closing uploadDataProvider"

    invoke-static {v0, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    return-void
.end method

.method private fireDisconnect()V
    .locals 2

    .line 746
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda1;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    const-string v1, "fireDisconnect"

    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method private fireGetHeaders()V
    .locals 2

    const/16 v0, 0xd

    .line 525
    iput v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mAdditionalStatusDetails:I

    .line 526
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda10;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda10;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    .line 527
    invoke-direct {p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->errorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object v0

    const-string v1, "fireGetHeaders"

    .line 526
    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method private fireOpenConnection()V
    .locals 2

    .line 625
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda4;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    .line 626
    invoke-direct {p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->errorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object v0

    const-string v1, "fireOpenConnection"

    .line 625
    invoke-direct {p0, v0, v1}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method private fireRedirectReceived(Ljava/lang/String;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 1

    .line 608
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda13;

    invoke-direct {v0, p0, p1, p2}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda13;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/lang/String;Lorg/chromium/net/UrlResponseInfo;)V

    const/4 p1, 0x1

    const/4 p2, 0x2

    invoke-direct {p0, p1, p2, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->transitionStates(IILjava/lang/Runnable;)V

    return-void
.end method

.method private getNetworkFromHandle(J)Landroid/net/Network;
    .locals 5

    .line 1187
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mEngine:Lorg/chromium/net/impl/JavaCronetEngine;

    .line 1189
    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaCronetEngine;->getContext()Landroid/content/Context;

    move-result-object p0

    const-string v0, "connectivity"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/net/ConnectivityManager;

    .line 1190
    invoke-virtual {p0}, Landroid/net/ConnectivityManager;->getAllNetworks()[Landroid/net/Network;

    move-result-object p0

    .line 1192
    array-length v0, p0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    aget-object v2, p0, v1

    .line 1193
    invoke-virtual {v2}, Landroid/net/Network;->getNetworkHandle()J

    move-result-wide v3

    cmp-long v3, v3, p1

    if-nez v3, :cond_0

    return-object v2

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method private static isValidHeaderName(Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x0

    move v1, v0

    .line 288
    :goto_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    if-ge v1, v2, :cond_2

    .line 289
    invoke-virtual {p0, v1}, Ljava/lang/String;->charAt(I)C

    move-result v2

    const/16 v3, 0x2c

    if-eq v2, v3, :cond_1

    const/16 v3, 0x2f

    if-eq v2, v3, :cond_1

    const/16 v3, 0x7b

    if-eq v2, v3, :cond_1

    const/16 v3, 0x7d

    if-eq v2, v3, :cond_1

    packed-switch v2, :pswitch_data_0

    packed-switch v2, :pswitch_data_1

    packed-switch v2, :pswitch_data_2

    .line 310
    invoke-static {v2}, Ljava/lang/Character;->isISOControl(C)Z

    move-result v3

    if-nez v3, :cond_1

    invoke-static {v2}, Ljava/lang/Character;->isWhitespace(C)Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    :goto_1
    :pswitch_0
    return v0

    :cond_2
    const/4 p0, 0x1

    return p0

    :pswitch_data_0
    .packed-switch 0x27
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x3a
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x5b
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method

.method private synthetic lambda$closeResponseChannel$14()V
    .locals 1

    .line 1174
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

    if-eqz v0, :cond_0

    .line 1176
    :try_start_0
    invoke-interface {v0}, Ljava/nio/channels/Channel;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 1178
    invoke-virtual {v0}, Ljava/lang/Throwable;->printStackTrace()V

    :goto_0
    const/4 v0, 0x0

    .line 1180
    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

    :cond_0
    return-void
.end method

.method private synthetic lambda$enterUserErrorState$3()V
    .locals 1

    .line 460
    iget v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mNonfinalUserCallbackExceptionCount:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mNonfinalUserCallbackExceptionCount:I

    return-void
.end method

.method private synthetic lambda$errorSetting$8(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 682
    :try_start_0
    invoke-interface {p1}, Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception p1

    .line 684
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->enterCronetErrorState(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$fireDisconnect$13()V
    .locals 3

    .line 748
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mOutputStreamDataSink:Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

    if-eqz v0, :cond_0

    .line 750
    :try_start_0
    invoke-virtual {v0}, Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;->closeOutputChannel()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 752
    sget-object v1, Lorg/chromium/net/impl/JavaUrlRequest;->TAG:Ljava/lang/String;

    const-string v2, "Exception when closing OutputChannel"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 755
    :cond_0
    :goto_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    if-eqz v0, :cond_1

    .line 756
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->disconnect()V

    const/4 v0, 0x0

    .line 757
    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    :cond_1
    return-void
.end method

.method private synthetic lambda$fireGetHeaders$4()V
    .locals 13

    .line 529
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    if-nez v0, :cond_0

    return-void

    .line 532
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const/4 v1, 0x0

    .line 535
    const-string v2, "http/1.1"

    move-object v9, v2

    move v2, v1

    .line 537
    :goto_0
    iget-object v3, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    .line 536
    invoke-virtual {v3, v2}, Ljava/net/HttpURLConnection;->getHeaderFieldKey(I)Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_3

    .line 539
    const-string v4, "X-Android-Selected-Transport"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 540
    iget-object v4, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    invoke-virtual {v4, v2}, Ljava/net/HttpURLConnection;->getHeaderField(I)Ljava/lang/String;

    move-result-object v4

    move-object v9, v4

    .line 542
    :cond_1
    const-string v4, "X-Android"

    invoke-virtual {v3, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_2

    .line 543
    new-instance v4, Ljava/util/AbstractMap$SimpleEntry;

    iget-object v5, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    .line 546
    invoke-virtual {v5, v2}, Ljava/net/HttpURLConnection;->getHeaderField(I)Ljava/lang/String;

    move-result-object v5

    invoke-direct {v4, v3, v5}, Ljava/util/AbstractMap$SimpleEntry;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 543
    invoke-interface {v0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 550
    :cond_3
    iget-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    invoke-virtual {v2}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v5

    .line 555
    new-instance v3, Lorg/chromium/net/impl/UrlResponseInfoImpl;

    new-instance v4, Ljava/util/ArrayList;

    iget-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlChain:Ljava/util/List;

    invoke-direct {v4, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    iget-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    .line 559
    invoke-virtual {v2}, Ljava/net/HttpURLConnection;->getResponseMessage()Ljava/lang/String;

    move-result-object v6

    .line 560
    invoke-static {v0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object v7

    const-string v10, ""

    const-wide/16 v11, 0x0

    const/4 v8, 0x0

    invoke-direct/range {v3 .. v12}, Lorg/chromium/net/impl/UrlResponseInfoImpl;-><init>(Ljava/util/List;ILjava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/lang/String;J)V

    const/16 v0, 0x12c

    const/16 v2, 0x190

    if-lt v5, v0, :cond_4

    if-ge v5, v2, :cond_4

    .line 568
    invoke-virtual {v3}, Lorg/chromium/net/impl/UrlResponseInfoImpl;->getAllHeaders()Ljava/util/Map;

    move-result-object v0

    const-string v4, "location"

    invoke-interface {v0, v4}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    if-eqz v0, :cond_4

    .line 570
    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-direct {p0, v0, v3}, Lorg/chromium/net/impl/JavaUrlRequest;->fireRedirectReceived(Ljava/lang/String;Lorg/chromium/net/UrlResponseInfo;)V

    return-void

    .line 576
    :cond_4
    iput-object v3, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

    .line 577
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireCloseUploadDataProvider()V

    .line 586
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    if-lt v5, v2, :cond_6

    .line 579
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getErrorStream()Ljava/io/InputStream;

    move-result-object v0

    if-nez v0, :cond_5

    const/4 v0, 0x0

    goto :goto_1

    .line 583
    :cond_5
    invoke-static {v0}, Lorg/chromium/net/impl/InputStreamChannel;->wrap(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;

    move-result-object v0

    :goto_1
    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

    .line 584
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onResponseStarted()V

    return-void

    .line 588
    :cond_6
    invoke-virtual {v0}, Ljava/net/URLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    .line 587
    invoke-static {v0}, Lorg/chromium/net/impl/InputStreamChannel;->wrap(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;

    move-result-object v0

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

    .line 589
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    invoke-virtual {p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onResponseStarted()V

    return-void
.end method

.method private synthetic lambda$fireOpenConnection$7()V
    .locals 9

    .line 630
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    const/16 v1, 0x8

    if-ne v0, v1, :cond_0

    return-void

    .line 634
    :cond_0
    new-instance v0, Ljava/net/URL;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrl:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 635
    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    if-eqz v1, :cond_1

    .line 636
    invoke-virtual {v1}, Ljava/net/HttpURLConnection;->disconnect()V

    const/4 v1, 0x0

    .line 637
    iput-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    .line 640
    :cond_1
    iget-wide v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mNetworkHandle:J

    const-wide/16 v3, -0x1

    cmp-long v3, v1, v3

    if-nez v3, :cond_2

    .line 641
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    goto :goto_0

    .line 643
    :cond_2
    invoke-direct {p0, v1, v2}, Lorg/chromium/net/impl/JavaUrlRequest;->getNetworkFromHandle(J)Landroid/net/Network;

    move-result-object v1

    if-eqz v1, :cond_7

    .line 651
    invoke-virtual {v1, v0}, Landroid/net/Network;->openConnection(Ljava/net/URL;)Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    iput-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    .line 653
    :goto_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setInstanceFollowRedirects(Z)V

    .line 654
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    const-string v2, "User-Agent"

    invoke-interface {v0, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 655
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    iget-object v3, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUserAgent:Ljava/lang/String;

    invoke-interface {v0, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 657
    :cond_3
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 658
    iget-object v3, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    .line 659
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 658
    invoke-virtual {v3, v4, v2}, Ljava/net/URLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    .line 661
    :cond_4
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    iget-object v2, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mInitialMethod:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 662
    iget-object v8, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadDataProvider:Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;

    if-eqz v8, :cond_6

    .line 663
    new-instance v3, Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

    iget-object v5, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUploadExecutor:Ljava/util/concurrent/Executor;

    iget-object v6, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mExecutor:Ljava/util/concurrent/Executor;

    iget-object v7, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    move-object v4, p0

    invoke-direct/range {v3 .. v8}, Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/net/HttpURLConnection;Lorg/chromium/net/impl/VersionSafeCallbacks$UploadDataProviderWrapper;)V

    iput-object v3, v4, Lorg/chromium/net/impl/JavaUrlRequest;->mOutputStreamDataSink:Lorg/chromium/net/impl/JavaUrlRequest$OutputStreamDataSink;

    .line 669
    iget-object p0, v4, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlChain:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    const/4 v0, 0x1

    if-ne p0, v0, :cond_5

    move v1, v0

    :cond_5
    invoke-virtual {v3, v1}, Lorg/chromium/net/impl/JavaUploadDataSinkBase;->start(Z)V

    return-void

    :cond_6
    move-object v4, p0

    const/16 p0, 0xa

    .line 671
    iput p0, v4, Lorg/chromium/net/impl/JavaUrlRequest;->mAdditionalStatusDetails:I

    .line 672
    iget-object p0, v4, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrlConnection:Ljava/net/HttpURLConnection;

    invoke-virtual {p0}, Ljava/net/URLConnection;->connect()V

    .line 673
    invoke-direct {v4}, Lorg/chromium/net/impl/JavaUrlRequest;->fireGetHeaders()V

    return-void

    .line 645
    :cond_7
    new-instance p0, Lorg/chromium/net/impl/NetworkExceptionImpl;

    const/16 v0, 0x9

    const/4 v1, -0x4

    const-string v2, "Network bound to request not found"

    invoke-direct {p0, v2, v0, v1}, Lorg/chromium/net/impl/NetworkExceptionImpl;-><init>(Ljava/lang/String;II)V

    throw p0
.end method

.method private synthetic lambda$fireRedirectReceived$5(Lorg/chromium/net/UrlResponseInfo;)V
    .locals 1

    .line 618
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mPendingRedirectUrl:Ljava/lang/String;

    invoke-virtual {v0, p1, p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onRedirectReceived(Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$fireRedirectReceived$6(Ljava/lang/String;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 1

    .line 612
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrl:Ljava/lang/String;

    invoke-static {v0}, Ljava/net/URI;->create(Ljava/lang/String;)Ljava/net/URI;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/net/URI;->resolve(Ljava/lang/String;)Ljava/net/URI;

    move-result-object p1

    invoke-virtual {p1}, Ljava/net/URI;->toString()Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mPendingRedirectUrl:Ljava/lang/String;

    .line 613
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlChain:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 614
    new-instance p1, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;

    invoke-direct {p1, p0, p2}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda14;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    const/4 p2, 0x2

    const/4 v0, 0x3

    invoke-direct {p0, p2, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->transitionStates(IILjava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$read$11(Ljava/nio/ByteBuffer;)V
    .locals 2

    .line 716
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

    if-eqz v0, :cond_0

    .line 717
    iget v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mReadCount:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mReadCount:I

    .line 718
    invoke-interface {v0, p1}, Ljava/nio/channels/ReadableByteChannel;->read(Ljava/nio/ByteBuffer;)I

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, -0x1

    .line 720
    :goto_0
    invoke-direct {p0, v0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->processReadResult(ILjava/nio/ByteBuffer;)V

    return-void
.end method

.method private synthetic lambda$read$12(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 1

    .line 726
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->errorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;

    move-result-object p1

    const-string v0, "read"

    invoke-direct {p0, p1, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->executeOnExecutor(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$start$2()V
    .locals 2

    .line 418
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlChain:Ljava/util/List;

    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCurrentUrl:Ljava/lang/String;

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 419
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireOpenConnection()V

    return-void
.end method

.method private synthetic lambda$uploadErrorSetting$10(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 702
    :try_start_0
    invoke-interface {p1}, Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception p1

    .line 704
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->enterUploadErrorState(Ljava/lang/Throwable;)V

    return-void
.end method

.method private synthetic lambda$userErrorSetting$9(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V
    .locals 0

    .line 692
    :try_start_0
    invoke-interface {p1}, Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception p1

    .line 694
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->enterUserErrorState(Ljava/lang/Throwable;)V

    return-void
.end method

.method private onFinalCallbackException(Ljava/lang/String;Ljava/lang/Exception;)V
    .locals 3

    .line 1200
    sget-object v0, Lorg/chromium/net/impl/JavaUrlRequest;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Exception in "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " method"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p1, 0x1

    .line 1201
    iput-boolean p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mFinalUserCallbackThrew:Z

    return-void
.end method

.method private static parseContentLengthString(Ljava/lang/String;)J
    .locals 2

    .line 838
    :try_start_0
    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return-wide v0

    :catch_0
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method private processReadResult(ILjava/nio/ByteBuffer;)V
    .locals 1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_0

    .line 732
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

    invoke-virtual {p1, p0, p2}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onReadCompleted(Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    return-void

    .line 734
    :cond_0
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mResponseChannel:Ljava/nio/channels/ReadableByteChannel;

    if-eqz p1, :cond_1

    .line 735
    invoke-interface {p1}, Ljava/nio/channels/Channel;->close()V

    .line 737
    :cond_1
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 p2, 0x5

    const/4 v0, 0x7

    invoke-virtual {p1, p2, v0}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 739
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireDisconnect()V

    .line 740
    iget-object p1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

    invoke-virtual {p1, p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onSucceeded(Lorg/chromium/net/UrlResponseInfo;)V

    :cond_2
    return-void
.end method

.method private setHeaders(Ljava/util/ArrayList;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/util/Map$Entry<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)V"
        }
    .end annotation

    .line 278
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Ljava/util/Map$Entry;

    .line 279
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-static {v3}, Lorg/chromium/net/impl/JavaUrlRequest;->isValidHeaderName(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    const-string v4, "\r\n"

    invoke-virtual {v3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_0

    .line 283
    iget-object v3, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mRequestHeaders:Ljava/util/Map;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v3, v4, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 281
    :cond_0
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    const-string p1, "Invalid header with headername: "

    invoke-static {p1, p0}, Lokio/Buffer$$ExternalSyntheticBUOutline4;->m(Ljava/lang/String;Ljava/lang/Object;)V

    :cond_1
    return-void
.end method

.method private setTerminalState(I)Z
    .locals 2

    .line 433
    :cond_0
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    if-eqz v0, :cond_2

    const/4 v1, 0x6

    if-eq v0, v1, :cond_1

    const/4 v1, 0x7

    if-eq v0, v1, :cond_1

    const/16 v1, 0x8

    if-eq v0, v1, :cond_1

    .line 442
    iget-object v1, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v1, v0, p1}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0

    .line 436
    :cond_2
    const-string p0, "Can\'t enter error state before start"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0
.end method

.method private transitionStates(IILjava/lang/Runnable;)V
    .locals 1

    .line 488
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0, p1, p2}, Ljava/util/concurrent/atomic/AtomicInteger;->compareAndSet(II)Z

    move-result v0

    if-nez v0, :cond_1

    .line 489
    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p0

    const/16 p2, 0x8

    if-eq p0, p2, :cond_2

    const/4 p2, 0x6

    if-ne p0, p2, :cond_0

    goto :goto_0

    .line 493
    :cond_0
    invoke-static {p1}, Lorg/chromium/net/impl/JavaUrlRequestUtils;->stateToString(I)Ljava/lang/String;

    move-result-object p1

    .line 495
    invoke-static {p0}, Lorg/chromium/net/impl/JavaUrlRequestUtils;->stateToString(I)Ljava/lang/String;

    move-result-object p0

    const-string p2, "Invalid state transition - expected "

    const-string p3, " but was "

    invoke-static {p2, p1, p3, p0}, Landroidx/webkit/WebMessageCompat$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    .line 501
    :cond_1
    invoke-static {p1}, Lorg/chromium/net/impl/JavaUrlRequestUtils;->stateToString(I)Ljava/lang/String;

    move-result-object p0

    .line 503
    invoke-static {p2}, Lorg/chromium/net/impl/JavaUrlRequestUtils;->stateToString(I)Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Cronet JavaUrlRequest#transitionStates "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " -> "

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 499
    invoke-static {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->scoped(Ljava/lang/String;)Lorg/chromium/base/metrics/ScopedSysTraceEvent;

    move-result-object p0

    .line 504
    :try_start_0
    invoke-interface {p3}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p0, :cond_2

    .line 505
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V

    :cond_2
    :goto_0
    return-void

    :catchall_0
    move-exception p1

    if-eqz p0, :cond_3

    .line 498
    :try_start_1
    invoke-virtual {p0}, Lorg/chromium/base/metrics/ScopedSysTraceEvent;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_1

    :catchall_1
    move-exception p0

    invoke-virtual {p1, p0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_3
    :goto_1
    throw p1
.end method

.method private uploadErrorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
    .locals 1

    .line 700
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda9;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda9;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-object v0
.end method

.method private userErrorSetting(Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)Ljava/lang/Runnable;
    .locals 1

    .line 690
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda15;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda15;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    return-object v0
.end method


# virtual methods
.method public cancel()V
    .locals 2

    .line 765
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mState:Ljava/util/concurrent/atomic/AtomicInteger;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndSet(I)I

    move-result v0

    const/4 v1, 0x1

    if-eq v0, v1, :cond_0

    const/4 v1, 0x2

    if-eq v0, v1, :cond_0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    const/4 v1, 0x4

    if-eq v0, v1, :cond_0

    const/4 v1, 0x5

    if-eq v0, v1, :cond_0

    return-void

    .line 778
    :cond_0
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireDisconnect()V

    .line 779
    invoke-direct {p0}, Lorg/chromium/net/impl/JavaUrlRequest;->fireCloseUploadDataProvider()V

    .line 780
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mCallbackAsync:Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;

    iget-object p0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mUrlResponseInfo:Lorg/chromium/net/impl/UrlResponseInfoImpl;

    invoke-virtual {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$AsyncUrlRequestCallback;->onCanceled(Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public followRedirect()V
    .locals 3

    .line 511
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$1;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$1;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    const/4 v1, 0x3

    const/4 v2, 0x1

    invoke-direct {p0, v1, v2, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->transitionStates(IILjava/lang/Runnable;)V

    return-void
.end method

.method public read(Ljava/nio/ByteBuffer;)V
    .locals 2

    .line 711
    invoke-static {p1}, Lorg/chromium/net/impl/Preconditions;->checkDirect(Ljava/nio/ByteBuffer;)V

    .line 712
    invoke-static {p1}, Lorg/chromium/net/impl/Preconditions;->checkHasRemaining(Ljava/nio/ByteBuffer;)V

    .line 713
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0, p1}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda6;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Ljava/nio/ByteBuffer;)V

    .line 722
    new-instance p1, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda7;

    invoke-direct {p1, p0, v0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda7;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;Lorg/chromium/net/impl/JavaUrlRequestUtils$CheckedRunnable;)V

    const/4 v0, 0x4

    const/4 v1, 0x5

    invoke-direct {p0, v0, v1, p1}, Lorg/chromium/net/impl/JavaUrlRequest;->transitionStates(IILjava/lang/Runnable;)V

    return-void
.end method

.method public start()V
    .locals 3

    const/16 v0, 0xa

    .line 412
    iput v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mAdditionalStatusDetails:I

    .line 413
    iget-object v0, p0, Lorg/chromium/net/impl/JavaUrlRequest;->mEngine:Lorg/chromium/net/impl/JavaCronetEngine;

    invoke-virtual {v0}, Lorg/chromium/net/impl/JavaCronetEngine;->incrementActiveRequestCount()V

    .line 414
    new-instance v0, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaUrlRequest$$ExternalSyntheticLambda3;-><init>(Lorg/chromium/net/impl/JavaUrlRequest;)V

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-direct {p0, v1, v2, v0}, Lorg/chromium/net/impl/JavaUrlRequest;->transitionStates(IILjava/lang/Runnable;)V

    return-void
.end method
