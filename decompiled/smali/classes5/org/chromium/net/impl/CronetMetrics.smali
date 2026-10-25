.class public final Lorg/chromium/net/impl/CronetMetrics;
.super Lorg/chromium/net/RequestFinishedInfo$Metrics;
.source "SourceFile"


# annotations
.annotation runtime Linternal/org/jni_zero/JNINamespace;
    value = "cronet"
.end annotation


# instance fields
.field private final mConnectEndMs:J

.field private final mConnectStartMs:J

.field private final mDnsEndMs:J

.field private final mDnsStartMs:J

.field private final mPushEndMs:J

.field private final mPushStartMs:J

.field private final mReceivedByteCount:Ljava/lang/Long;

.field private final mRequestEndMs:J

.field private final mRequestStartMs:J

.field private final mResponseStartMs:J

.field private final mSendingEndMs:J

.field private final mSendingStartMs:J

.field private final mSentByteCount:Ljava/lang/Long;

.field private final mSocketReused:Z

.field private final mSslEndMs:J

.field private final mSslStartMs:J

.field private final mTotalTimeMs:Ljava/lang/Long;

.field private final mTtfbMs:Ljava/lang/Long;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(JJJJJJJJJJJJJZJJ)V
    .locals 7
    .annotation build Linternal/org/jni_zero/CalledByNative;
    .end annotation

    move-wide/from16 v0, p23

    move-wide/from16 v2, p25

    .line 90
    invoke-direct {p0}, Lorg/chromium/net/RequestFinishedInfo$Metrics;-><init>()V

    .line 105
    iput-wide p1, p0, Lorg/chromium/net/impl/CronetMetrics;->mRequestStartMs:J

    .line 106
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mDnsStartMs:J

    .line 107
    iput-wide p5, p0, Lorg/chromium/net/impl/CronetMetrics;->mDnsEndMs:J

    .line 108
    iput-wide p7, p0, Lorg/chromium/net/impl/CronetMetrics;->mConnectStartMs:J

    move-wide/from16 p3, p9

    .line 109
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mConnectEndMs:J

    move-wide/from16 p3, p11

    .line 110
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mSslStartMs:J

    move-wide/from16 p3, p13

    .line 111
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mSslEndMs:J

    move-wide/from16 p3, p15

    .line 112
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mSendingStartMs:J

    move-wide/from16 p3, p17

    .line 113
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mSendingEndMs:J

    move-wide/from16 p3, p19

    .line 114
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mPushStartMs:J

    move-wide/from16 p3, p21

    .line 115
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mPushEndMs:J

    .line 116
    iput-wide v0, p0, Lorg/chromium/net/impl/CronetMetrics;->mResponseStartMs:J

    .line 117
    iput-wide v2, p0, Lorg/chromium/net/impl/CronetMetrics;->mRequestEndMs:J

    move/from16 p3, p27

    .line 118
    iput-boolean p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mSocketReused:Z

    .line 119
    invoke-static/range {p28 .. p29}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    iput-object p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mSentByteCount:Ljava/lang/Long;

    .line 120
    invoke-static/range {p30 .. p31}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    iput-object p3, p0, Lorg/chromium/net/impl/CronetMetrics;->mReceivedByteCount:Ljava/lang/Long;

    const-wide/16 p3, -0x1

    cmp-long v4, p1, p3

    const/4 v5, 0x0

    if-eqz v4, :cond_0

    cmp-long v6, v0, p3

    if-eqz v6, :cond_0

    sub-long/2addr v0, p1

    .line 124
    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    iput-object v0, p0, Lorg/chromium/net/impl/CronetMetrics;->mTtfbMs:Ljava/lang/Long;

    goto :goto_0

    .line 126
    :cond_0
    iput-object v5, p0, Lorg/chromium/net/impl/CronetMetrics;->mTtfbMs:Ljava/lang/Long;

    :goto_0
    if-eqz v4, :cond_1

    cmp-long p3, v2, p3

    if-eqz p3, :cond_1

    sub-long p1, v2, p1

    .line 129
    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iput-object p1, p0, Lorg/chromium/net/impl/CronetMetrics;->mTotalTimeMs:Ljava/lang/Long;

    return-void

    .line 131
    :cond_1
    iput-object v5, p0, Lorg/chromium/net/impl/CronetMetrics;->mTotalTimeMs:Ljava/lang/Long;

    return-void
.end method

.method public static empty()Lorg/chromium/net/impl/CronetMetrics;
    .locals 32

    .line 69
    new-instance v0, Lorg/chromium/net/impl/CronetMetrics;

    const-wide/16 v28, 0x0

    const-wide/16 v30, 0x0

    const-wide/16 v1, -0x1

    const-wide/16 v3, -0x1

    const-wide/16 v5, -0x1

    const-wide/16 v7, -0x1

    const-wide/16 v9, -0x1

    const-wide/16 v11, -0x1

    const-wide/16 v13, -0x1

    const-wide/16 v15, -0x1

    const-wide/16 v17, -0x1

    const-wide/16 v19, -0x1

    const-wide/16 v21, -0x1

    const-wide/16 v23, -0x1

    const-wide/16 v25, -0x1

    const/16 v27, 0x0

    invoke-direct/range {v0 .. v31}, Lorg/chromium/net/impl/CronetMetrics;-><init>(JJJJJJJJJJJJJZJJ)V

    return-object v0
.end method
