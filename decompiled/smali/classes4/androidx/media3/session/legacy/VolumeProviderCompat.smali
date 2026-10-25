.class public abstract Landroidx/media3/session/legacy/VolumeProviderCompat;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final controlId:Ljava/lang/String;

.field private final controlType:I

.field private currentVolume:I

.field private final maxVolume:I

.field private volumeProviderFwk:Landroid/media/VolumeProvider;


# direct methods
.method public constructor <init>(IIILjava/lang/String;)V
    .locals 0

    .line 91
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 92
    iput p1, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->controlType:I

    .line 93
    iput p2, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->maxVolume:I

    .line 94
    iput p3, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->currentVolume:I

    .line 95
    iput-object p4, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->controlId:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public getVolumeProvider()Ljava/lang/Object;
    .locals 8

    .line 152
    iget-object v0, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->volumeProviderFwk:Landroid/media/VolumeProvider;

    if-nez v0, :cond_1

    .line 153
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1e

    if-lt v0, v1, :cond_0

    .line 154
    new-instance v2, Landroidx/media3/session/legacy/VolumeProviderCompat$1;

    iget v4, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->controlType:I

    iget v5, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->maxVolume:I

    iget v6, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->currentVolume:I

    iget-object v7, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->controlId:Ljava/lang/String;

    move-object v3, p0

    invoke-direct/range {v2 .. v7}, Landroidx/media3/session/legacy/VolumeProviderCompat$1;-><init>(Landroidx/media3/session/legacy/VolumeProviderCompat;IIILjava/lang/String;)V

    iput-object v2, v3, Landroidx/media3/session/legacy/VolumeProviderCompat;->volumeProviderFwk:Landroid/media/VolumeProvider;

    goto :goto_0

    :cond_0
    move-object v3, p0

    .line 167
    new-instance p0, Landroidx/media3/session/legacy/VolumeProviderCompat$2;

    iget v0, v3, Landroidx/media3/session/legacy/VolumeProviderCompat;->controlType:I

    iget v1, v3, Landroidx/media3/session/legacy/VolumeProviderCompat;->maxVolume:I

    iget v2, v3, Landroidx/media3/session/legacy/VolumeProviderCompat;->currentVolume:I

    invoke-direct {p0, v3, v0, v1, v2}, Landroidx/media3/session/legacy/VolumeProviderCompat$2;-><init>(Landroidx/media3/session/legacy/VolumeProviderCompat;III)V

    iput-object p0, v3, Landroidx/media3/session/legacy/VolumeProviderCompat;->volumeProviderFwk:Landroid/media/VolumeProvider;

    goto :goto_0

    :cond_1
    move-object v3, p0

    .line 181
    :goto_0
    iget-object p0, v3, Landroidx/media3/session/legacy/VolumeProviderCompat;->volumeProviderFwk:Landroid/media/VolumeProvider;

    return-object p0
.end method

.method public abstract onAdjustVolume(I)V
.end method

.method public abstract onSetVolumeTo(I)V
.end method

.method public final setCurrentVolume(I)V
    .locals 0

    .line 113
    iput p1, p0, Landroidx/media3/session/legacy/VolumeProviderCompat;->currentVolume:I

    .line 114
    invoke-virtual {p0}, Landroidx/media3/session/legacy/VolumeProviderCompat;->getVolumeProvider()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/media/VolumeProvider;

    .line 115
    invoke-virtual {p0, p1}, Landroid/media/VolumeProvider;->setCurrentVolume(I)V

    return-void
.end method
