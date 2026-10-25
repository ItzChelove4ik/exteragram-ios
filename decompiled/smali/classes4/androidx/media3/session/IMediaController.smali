.class public interface abstract Landroidx/media3/session/IMediaController;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/IInterface;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/session/IMediaController$_Parcel;,
        Landroidx/media3/session/IMediaController$Stub;
    }
.end annotation


# virtual methods
.method public abstract onAvailableCommandsChangedFromPlayer(ILandroid/os/Bundle;)V
.end method

.method public abstract onConnected(ILandroid/os/Bundle;)V
.end method

.method public abstract onCustomCommand(ILandroid/os/Bundle;Landroid/os/Bundle;)V
.end method

.method public abstract onDisconnected(I)V
.end method

.method public abstract onLibraryResult(ILandroid/os/Bundle;)V
.end method

.method public abstract onPeriodicSessionPositionInfoChanged(ILandroid/os/Bundle;)V
.end method

.method public abstract onPlayerInfoChanged(ILandroid/os/Bundle;Z)V
.end method

.method public abstract onPlayerInfoChangedWithExclusions(ILandroid/os/Bundle;Landroid/os/Bundle;)V
.end method

.method public abstract onRenderedFirstFrame(I)V
.end method

.method public abstract onSessionResult(ILandroid/os/Bundle;)V
.end method
