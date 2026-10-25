.class public final Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable;


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "BanParcelableUsage"
    }
.end annotation

.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/session/legacy/MediaSessionCompat;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "QueueItem"
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

.field private final id:J

.field private itemFwk:Landroid/media/session/MediaSession$QueueItem;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1789
    new-instance v0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem$1;

    invoke-direct {v0}, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem$1;-><init>()V

    sput-object v0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method private constructor <init>(Landroid/media/session/MediaSession$QueueItem;Landroidx/media3/session/legacy/MediaDescriptionCompat;J)V
    .locals 2

    .line 1703
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/16 v0, -0x1

    cmp-long v0, p3, v0

    if-eqz v0, :cond_0

    .line 1707
    iput-object p2, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

    .line 1708
    iput-wide p3, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->id:J

    .line 1709
    iput-object p1, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->itemFwk:Landroid/media/session/MediaSession$QueueItem;

    return-void

    .line 1705
    :cond_0
    const-string p0, "Id cannot be QueueItem.UNKNOWN_ID"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public constructor <init>(Landroid/os/Parcel;)V
    .locals 2

    .line 1712
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1713
    sget-object v0, Landroidx/media3/session/legacy/MediaDescriptionCompat;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {v0, p1}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/media3/session/legacy/MediaDescriptionCompat;

    iput-object v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

    .line 1714
    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v0

    iput-wide v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->id:J

    return-void
.end method

.method public constructor <init>(Landroidx/media3/session/legacy/MediaDescriptionCompat;J)V
    .locals 1

    const/4 v0, 0x0

    .line 1699
    invoke-direct {p0, v0, p1, p2, p3}, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;-><init>(Landroid/media/session/MediaSession$QueueItem;Landroidx/media3/session/legacy/MediaDescriptionCompat;J)V

    return-void
.end method


# virtual methods
.method public describeContents()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public getDescription()Landroidx/media3/session/legacy/MediaDescriptionCompat;
    .locals 0

    .line 1719
    iget-object p0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

    return-object p0
.end method

.method public getQueueId()J
    .locals 2

    .line 1724
    iget-wide v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->id:J

    return-wide v0
.end method

.method public getQueueItem()Landroid/media/session/MediaSession$QueueItem;
    .locals 4

    .line 1744
    iget-object v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->itemFwk:Landroid/media/session/MediaSession$QueueItem;

    if-eqz v0, :cond_0

    return-object v0

    .line 1747
    :cond_0
    iget-object v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

    invoke-virtual {v0}, Landroidx/media3/session/legacy/MediaDescriptionCompat;->getMediaDescription()Landroid/media/MediaDescription;

    move-result-object v0

    .line 1748
    new-instance v1, Landroid/media/session/MediaSession$QueueItem;

    iget-wide v2, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->id:J

    invoke-direct {v1, v0, v2, v3}, Landroid/media/session/MediaSession$QueueItem;-><init>(Landroid/media/MediaDescription;J)V

    iput-object v1, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->itemFwk:Landroid/media/session/MediaSession$QueueItem;

    return-object v1
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    .line 1805
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "MediaSession.QueueItem { Description="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", Id="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->id:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, " }"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 2

    .line 1729
    iget-object v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->description:Landroidx/media3/session/legacy/MediaDescriptionCompat;

    invoke-virtual {v0, p1, p2}, Landroidx/media3/session/legacy/MediaDescriptionCompat;->writeToParcel(Landroid/os/Parcel;I)V

    .line 1730
    iget-wide v0, p0, Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;->id:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    return-void
.end method
