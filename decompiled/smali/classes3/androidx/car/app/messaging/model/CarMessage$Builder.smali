.class public final Landroidx/car/app/messaging/model/CarMessage$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/messaging/model/CarMessage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field mBody:Landroidx/car/app/model/CarText;

.field mIsRead:Z

.field mMultimediaMimeType:Ljava/lang/String;

.field mMultimediaUri:Landroid/net/Uri;

.field mReceivedTimeEpochMillis:J

.field mSender:Landroidx/core/app/Person;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 172
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/messaging/model/CarMessage;
    .locals 5

    .line 261
    iget-object v0, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mMultimediaMimeType:Ljava/lang/String;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-nez v0, :cond_0

    move v3, v2

    goto :goto_0

    :cond_0
    move v3, v1

    :goto_0
    iget-object v4, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mMultimediaUri:Landroid/net/Uri;

    if-nez v4, :cond_1

    move v1, v2

    :cond_1
    xor-int/2addr v1, v3

    if-nez v1, :cond_4

    .line 270
    iget-object v1, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mBody:Landroidx/car/app/model/CarText;

    if-nez v1, :cond_3

    if-eqz v0, :cond_2

    goto :goto_2

    .line 271
    :cond_2
    const-string p0, "Message must have content. Please provide body text, multimedia data (URI + MIME type), or both."

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    :goto_1
    const/4 p0, 0x0

    return-object p0

    .line 275
    :cond_3
    :goto_2
    new-instance v0, Landroidx/car/app/messaging/model/CarMessage;

    invoke-direct {v0, p0}, Landroidx/car/app/messaging/model/CarMessage;-><init>(Landroidx/car/app/messaging/model/CarMessage$Builder;)V

    return-object v0

    .line 262
    :cond_4
    const-string p0, "Incomplete multimedia data detected in CarMessage. Please be sure to provide both MIME type and URI for multimedia messages."

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_1
.end method

.method public setBody(Landroidx/car/app/model/CarText;)Landroidx/car/app/messaging/model/CarMessage$Builder;
    .locals 0

    .line 209
    iput-object p1, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mBody:Landroidx/car/app/model/CarText;

    return-object p0
.end method

.method public setRead(Z)Landroidx/car/app/messaging/model/CarMessage$Builder;
    .locals 0

    .line 255
    iput-boolean p1, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mIsRead:Z

    return-object p0
.end method

.method public setReceivedTimeEpochMillis(J)Landroidx/car/app/messaging/model/CarMessage$Builder;
    .locals 0

    .line 249
    iput-wide p1, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mReceivedTimeEpochMillis:J

    return-object p0
.end method

.method public setSender(Landroidx/core/app/Person;)Landroidx/car/app/messaging/model/CarMessage$Builder;
    .locals 0

    .line 192
    iput-object p1, p0, Landroidx/car/app/messaging/model/CarMessage$Builder;->mSender:Landroidx/core/app/Person;

    return-object p0
.end method
