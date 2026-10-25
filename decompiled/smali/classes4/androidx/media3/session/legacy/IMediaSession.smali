.class public interface abstract Landroidx/media3/session/legacy/IMediaSession;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/IInterface;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/session/legacy/IMediaSession$Stub;
    }
.end annotation


# virtual methods
.method public abstract addQueueItem(Landroidx/media3/session/legacy/MediaDescriptionCompat;)V
.end method

.method public abstract addQueueItemAt(Landroidx/media3/session/legacy/MediaDescriptionCompat;I)V
.end method

.method public abstract adjustVolume(IILjava/lang/String;)V
.end method

.method public abstract fastForward()V
.end method

.method public abstract getExtras()Landroid/os/Bundle;
.end method

.method public abstract getFlags()J
.end method

.method public abstract getLaunchPendingIntent()Landroid/app/PendingIntent;
.end method

.method public abstract getMetadata()Landroidx/media3/session/legacy/MediaMetadataCompat;
.end method

.method public abstract getPackageName()Ljava/lang/String;
.end method

.method public abstract getPlaybackState()Landroidx/media3/session/legacy/PlaybackStateCompat;
.end method

.method public abstract getQueue()Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Landroidx/media3/session/legacy/MediaSessionCompat$QueueItem;",
            ">;"
        }
    .end annotation
.end method

.method public abstract getQueueTitle()Ljava/lang/CharSequence;
.end method

.method public abstract getRatingType()I
.end method

.method public abstract getRepeatMode()I
.end method

.method public abstract getSessionInfo()Landroid/os/Bundle;
.end method

.method public abstract getShuffleMode()I
.end method

.method public abstract getTag()Ljava/lang/String;
.end method

.method public abstract getVolumeAttributes()Landroidx/media3/session/legacy/ParcelableVolumeInfo;
.end method

.method public abstract isCaptioningEnabled()Z
.end method

.method public abstract isShuffleModeEnabledRemoved()Z
.end method

.method public abstract isTransportControlEnabled()Z
.end method

.method public abstract next()V
.end method

.method public abstract pause()V
.end method

.method public abstract play()V
.end method

.method public abstract playFromMediaId(Ljava/lang/String;Landroid/os/Bundle;)V
.end method

.method public abstract playFromSearch(Ljava/lang/String;Landroid/os/Bundle;)V
.end method

.method public abstract playFromUri(Landroid/net/Uri;Landroid/os/Bundle;)V
.end method

.method public abstract prepare()V
.end method

.method public abstract prepareFromMediaId(Ljava/lang/String;Landroid/os/Bundle;)V
.end method

.method public abstract prepareFromSearch(Ljava/lang/String;Landroid/os/Bundle;)V
.end method

.method public abstract prepareFromUri(Landroid/net/Uri;Landroid/os/Bundle;)V
.end method

.method public abstract previous()V
.end method

.method public abstract rate(Landroidx/media3/session/legacy/RatingCompat;)V
.end method

.method public abstract rateWithExtras(Landroidx/media3/session/legacy/RatingCompat;Landroid/os/Bundle;)V
.end method

.method public abstract registerCallbackListener(Landroidx/media3/session/legacy/IMediaControllerCallback;)V
.end method

.method public abstract removeQueueItem(Landroidx/media3/session/legacy/MediaDescriptionCompat;)V
.end method

.method public abstract removeQueueItemAt(I)V
.end method

.method public abstract rewind()V
.end method

.method public abstract seekTo(J)V
.end method

.method public abstract sendCommand(Ljava/lang/String;Landroid/os/Bundle;Landroidx/media3/session/legacy/MediaSessionCompat$ResultReceiverWrapper;)V
.end method

.method public abstract sendCustomAction(Ljava/lang/String;Landroid/os/Bundle;)V
.end method

.method public abstract sendMediaButton(Landroid/view/KeyEvent;)Z
.end method

.method public abstract setCaptioningEnabled(Z)V
.end method

.method public abstract setPlaybackSpeed(F)V
.end method

.method public abstract setRepeatMode(I)V
.end method

.method public abstract setShuffleMode(I)V
.end method

.method public abstract setShuffleModeEnabledRemoved(Z)V
.end method

.method public abstract setVolumeTo(IILjava/lang/String;)V
.end method

.method public abstract skipToQueueItem(J)V
.end method

.method public abstract stop()V
.end method

.method public abstract unregisterCallbackListener(Landroidx/media3/session/legacy/IMediaControllerCallback;)V
.end method
