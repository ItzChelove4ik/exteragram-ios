.class Landroidx/media3/session/legacy/VolumeProviderCompat$1;
.super Landroid/media/VolumeProvider;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroidx/media3/session/legacy/VolumeProviderCompat;->getVolumeProvider()Ljava/lang/Object;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/media3/session/legacy/VolumeProviderCompat;


# direct methods
.method public constructor <init>(Landroidx/media3/session/legacy/VolumeProviderCompat;IIILjava/lang/String;)V
    .locals 0

    .line 155
    iput-object p1, p0, Landroidx/media3/session/legacy/VolumeProviderCompat$1;->this$0:Landroidx/media3/session/legacy/VolumeProviderCompat;

    invoke-direct {p0, p2, p3, p4, p5}, Landroid/media/VolumeProvider;-><init>(IIILjava/lang/String;)V

    return-void
.end method


# virtual methods
.method public onAdjustVolume(I)V
    .locals 0

    .line 163
    iget-object p0, p0, Landroidx/media3/session/legacy/VolumeProviderCompat$1;->this$0:Landroidx/media3/session/legacy/VolumeProviderCompat;

    invoke-virtual {p0, p1}, Landroidx/media3/session/legacy/VolumeProviderCompat;->onAdjustVolume(I)V

    return-void
.end method

.method public onSetVolumeTo(I)V
    .locals 0

    .line 158
    iget-object p0, p0, Landroidx/media3/session/legacy/VolumeProviderCompat$1;->this$0:Landroidx/media3/session/legacy/VolumeProviderCompat;

    invoke-virtual {p0, p1}, Landroidx/media3/session/legacy/VolumeProviderCompat;->onSetVolumeTo(I)V

    return-void
.end method
