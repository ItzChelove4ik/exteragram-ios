.class public Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/CronetLogger;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "CronetTrafficInfo"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;,
        Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;
    }
.end annotation


# instance fields
.field private final mCronetSource:Lorg/chromium/net/impl/CronetLogger$CronetSource;

.field private final mCronetVersion:Ljava/lang/String;

.field private final mDidConnectionMigrationSucceed:Z

.field private final mFailureReason:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

.field private final mFinalUserCallbackThrew:Z

.field private final mHeadersLatency:Lj$/time/Duration;

.field private final mIsBidiStream:Z

.field private final mNegotiatedProtocol:Ljava/lang/String;

.field private final mNetworkInternalErrorCode:I

.field private final mNonfinalUserCallbackExceptionCount:I

.field private final mOnUploadReadCount:I

.field private final mQuicErrorCode:I

.field private final mReadCount:I

.field private final mRequestBodySizeInBytes:J

.field private final mRequestHeaderSizeInBytes:J

.field private final mResponseBodySizeInBytes:J

.field private final mResponseHeaderSizeInBytes:J

.field private final mResponseStatusCode:I

.field private final mSocketReused:Z

.field private final mSource:I

.field private final mTerminalState:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

.field private final mTimeToConnectMillis:J

.field private final mTimeToEstablishDnsMillis:J

.field private final mTimeToEstablishSSLMillis:J

.field private final mTimeToSendFirstByteMillis:J

.field private final mTotalLatency:Lj$/time/Duration;

.field private final mUid:I

.field private final mWasConnectionMigrationAttempted:Z


# direct methods
.method public constructor <init>(JJJJILj$/time/Duration;Lj$/time/Duration;Ljava/lang/String;ZZLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;IIIZZIIIILorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;ZLjava/lang/String;Lorg/chromium/net/impl/CronetLogger$CronetSource;JJJJ)V
    .locals 0

    .line 264
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 265
    iput-wide p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mRequestHeaderSizeInBytes:J

    .line 266
    iput-wide p3, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mRequestBodySizeInBytes:J

    .line 267
    iput-wide p5, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mResponseHeaderSizeInBytes:J

    .line 268
    iput-wide p7, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mResponseBodySizeInBytes:J

    .line 269
    iput p9, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mResponseStatusCode:I

    .line 270
    iput-object p10, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mHeadersLatency:Lj$/time/Duration;

    .line 271
    iput-object p11, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTotalLatency:Lj$/time/Duration;

    .line 272
    iput-object p12, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mNegotiatedProtocol:Ljava/lang/String;

    .line 273
    iput-boolean p13, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mWasConnectionMigrationAttempted:Z

    .line 274
    iput-boolean p14, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mDidConnectionMigrationSucceed:Z

    .line 275
    iput-object p15, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTerminalState:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

    move/from16 p1, p16

    .line 276
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mNonfinalUserCallbackExceptionCount:I

    move/from16 p1, p17

    .line 277
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mReadCount:I

    move/from16 p1, p18

    .line 278
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mOnUploadReadCount:I

    move/from16 p1, p19

    .line 279
    iput-boolean p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mIsBidiStream:Z

    move/from16 p1, p20

    .line 280
    iput-boolean p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mFinalUserCallbackThrew:Z

    move/from16 p1, p21

    .line 281
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mUid:I

    move/from16 p1, p22

    .line 282
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mNetworkInternalErrorCode:I

    move/from16 p1, p23

    .line 283
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mQuicErrorCode:I

    move/from16 p1, p24

    .line 284
    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mSource:I

    move-object/from16 p1, p25

    .line 285
    iput-object p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mFailureReason:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    move/from16 p1, p26

    .line 286
    iput-boolean p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mSocketReused:Z

    move-object/from16 p1, p27

    .line 287
    iput-object p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mCronetVersion:Ljava/lang/String;

    move-object/from16 p1, p28

    .line 288
    iput-object p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mCronetSource:Lorg/chromium/net/impl/CronetLogger$CronetSource;

    move-wide/from16 p1, p29

    .line 289
    iput-wide p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToEstablishDnsMillis:J

    move-wide/from16 p1, p31

    .line 290
    iput-wide p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToEstablishSSLMillis:J

    move-wide/from16 p1, p33

    .line 291
    iput-wide p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToConnectMillis:J

    move-wide/from16 p1, p35

    .line 292
    iput-wide p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToSendFirstByteMillis:J

    return-void
.end method


# virtual methods
.method public didConnectionMigrationSucceed()Z
    .locals 0

    .line 354
    iget-boolean p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mDidConnectionMigrationSucceed:Z

    return p0
.end method

.method public getConnectionCloseSource()I
    .locals 0

    .line 394
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mSource:I

    return p0
.end method

.method public getCronetSource()Lorg/chromium/net/impl/CronetLogger$CronetSource;
    .locals 0

    .line 410
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mCronetSource:Lorg/chromium/net/impl/CronetLogger$CronetSource;

    return-object p0
.end method

.method public getCronetVersion()Ljava/lang/String;
    .locals 0

    .line 406
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mCronetVersion:Ljava/lang/String;

    return-object p0
.end method

.method public getFailureReason()Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;
    .locals 0

    .line 398
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mFailureReason:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    return-object p0
.end method

.method public getFinalUserCallbackThrew()Z
    .locals 0

    .line 378
    iget-boolean p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mFinalUserCallbackThrew:Z

    return p0
.end method

.method public getHeadersLatency()Lj$/time/Duration;
    .locals 0

    .line 329
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mHeadersLatency:Lj$/time/Duration;

    return-object p0
.end method

.method public getIsBidiStream()Z
    .locals 0

    .line 374
    iget-boolean p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mIsBidiStream:Z

    return p0
.end method

.method public getIsSocketReused()Z
    .locals 0

    .line 402
    iget-boolean p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mSocketReused:Z

    return p0
.end method

.method public getNegotiatedProtocol()Ljava/lang/String;
    .locals 0

    .line 344
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mNegotiatedProtocol:Ljava/lang/String;

    return-object p0
.end method

.method public getNetworkInternalErrorCode()I
    .locals 0

    .line 386
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mNetworkInternalErrorCode:I

    return p0
.end method

.method public getNonfinalUserCallbackExceptionCount()I
    .locals 0

    .line 362
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mNonfinalUserCallbackExceptionCount:I

    return p0
.end method

.method public getOnUploadReadCount()I
    .locals 0

    .line 370
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mOnUploadReadCount:I

    return p0
.end method

.method public getQuicErrorCode()I
    .locals 0

    .line 390
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mQuicErrorCode:I

    return p0
.end method

.method public getReadCount()I
    .locals 0

    .line 366
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mReadCount:I

    return p0
.end method

.method public getRequestBodySizeInBytes()J
    .locals 2

    .line 304
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mRequestBodySizeInBytes:J

    return-wide v0
.end method

.method public getRequestHeaderSizeInBytes()J
    .locals 2

    .line 299
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mRequestHeaderSizeInBytes:J

    return-wide v0
.end method

.method public getResponseBodySizeInBytes()J
    .locals 2

    .line 314
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mResponseBodySizeInBytes:J

    return-wide v0
.end method

.method public getResponseHeaderSizeInBytes()J
    .locals 2

    .line 309
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mResponseHeaderSizeInBytes:J

    return-wide v0
.end method

.method public getResponseStatusCode()I
    .locals 0

    .line 319
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mResponseStatusCode:I

    return p0
.end method

.method public getTerminalState()Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;
    .locals 0

    .line 358
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTerminalState:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestTerminalState;

    return-object p0
.end method

.method public getTimeToConnectMillis()J
    .locals 2

    .line 422
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToConnectMillis:J

    return-wide v0
.end method

.method public getTimeToEstablishDNSMillis()J
    .locals 2

    .line 414
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToEstablishDnsMillis:J

    return-wide v0
.end method

.method public getTimeToEstablishSSLMillis()J
    .locals 2

    .line 418
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToEstablishSSLMillis:J

    return-wide v0
.end method

.method public getTimeToSendFirstByteMillis()J
    .locals 2

    .line 426
    iget-wide v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTimeToSendFirstByteMillis:J

    return-wide v0
.end method

.method public getTotalLatency()Lj$/time/Duration;
    .locals 0

    .line 339
    iget-object p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mTotalLatency:Lj$/time/Duration;

    return-object p0
.end method

.method public getUid()I
    .locals 0

    .line 382
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mUid:I

    return p0
.end method

.method public wasConnectionMigrationAttempted()Z
    .locals 0

    .line 349
    iget-boolean p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;->mWasConnectionMigrationAttempted:Z

    return p0
.end method
