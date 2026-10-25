.class Landroidx/media3/session/legacy/PlaybackStateCompat$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/session/legacy/PlaybackStateCompat;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator<",
        "Landroidx/media3/session/legacy/PlaybackStateCompat;",
        ">;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 870
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;)Landroidx/media3/session/legacy/PlaybackStateCompat;
    .locals 0

    .line 873
    new-instance p0, Landroidx/media3/session/legacy/PlaybackStateCompat;

    invoke-direct {p0, p1}, Landroidx/media3/session/legacy/PlaybackStateCompat;-><init>(Landroid/os/Parcel;)V

    return-object p0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    .line 870
    invoke-virtual {p0, p1}, Landroidx/media3/session/legacy/PlaybackStateCompat$1;->createFromParcel(Landroid/os/Parcel;)Landroidx/media3/session/legacy/PlaybackStateCompat;

    move-result-object p0

    return-object p0
.end method

.method public newArray(I)[Landroidx/media3/session/legacy/PlaybackStateCompat;
    .locals 0

    .line 878
    new-array p0, p1, [Landroidx/media3/session/legacy/PlaybackStateCompat;

    return-object p0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    .line 870
    invoke-virtual {p0, p1}, Landroidx/media3/session/legacy/PlaybackStateCompat$1;->newArray(I)[Landroidx/media3/session/legacy/PlaybackStateCompat;

    move-result-object p0

    return-object p0
.end method
