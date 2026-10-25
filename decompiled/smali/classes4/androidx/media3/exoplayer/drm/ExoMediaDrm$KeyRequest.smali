.class public final Landroidx/media3/exoplayer/drm/ExoMediaDrm$KeyRequest;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/exoplayer/drm/ExoMediaDrm;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "KeyRequest"
.end annotation


# instance fields
.field private final data:[B

.field private final licenseServerUrl:Ljava/lang/String;

.field private final requestType:I


# direct methods
.method public constructor <init>([BLjava/lang/String;I)V
    .locals 0

    .line 303
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 304
    iput-object p1, p0, Landroidx/media3/exoplayer/drm/ExoMediaDrm$KeyRequest;->data:[B

    .line 305
    iput-object p2, p0, Landroidx/media3/exoplayer/drm/ExoMediaDrm$KeyRequest;->licenseServerUrl:Ljava/lang/String;

    .line 306
    iput p3, p0, Landroidx/media3/exoplayer/drm/ExoMediaDrm$KeyRequest;->requestType:I

    return-void
.end method


# virtual methods
.method public getData()[B
    .locals 0

    .line 311
    iget-object p0, p0, Landroidx/media3/exoplayer/drm/ExoMediaDrm$KeyRequest;->data:[B

    return-object p0
.end method

.method public getLicenseServerUrl()Ljava/lang/String;
    .locals 0

    .line 316
    iget-object p0, p0, Landroidx/media3/exoplayer/drm/ExoMediaDrm$KeyRequest;->licenseServerUrl:Ljava/lang/String;

    return-object p0
.end method
