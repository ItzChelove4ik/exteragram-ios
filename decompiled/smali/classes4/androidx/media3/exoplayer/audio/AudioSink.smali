.class public interface abstract Landroidx/media3/exoplayer/audio/AudioSink;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/exoplayer/audio/AudioSink$UnexpectedDiscontinuityException;,
        Landroidx/media3/exoplayer/audio/AudioSink$WriteException;,
        Landroidx/media3/exoplayer/audio/AudioSink$InitializationException;,
        Landroidx/media3/exoplayer/audio/AudioSink$ConfigurationException;,
        Landroidx/media3/exoplayer/audio/AudioSink$AudioTrackConfig;,
        Landroidx/media3/exoplayer/audio/AudioSink$Listener;
    }
.end annotation


# virtual methods
.method public abstract configure(Landroidx/media3/common/Format;I[I)V
.end method

.method public abstract disableTunneling()V
.end method

.method public abstract enableTunnelingV21()V
.end method

.method public abstract flush()V
.end method

.method public abstract getAudioTrackBufferSizeUs()J
.end method

.method public abstract getCurrentPositionUs(Z)J
.end method

.method public abstract getFormatOffloadSupport(Landroidx/media3/common/Format;)Landroidx/media3/exoplayer/audio/AudioOffloadSupport;
.end method

.method public abstract getFormatSupport(Landroidx/media3/common/Format;)I
.end method

.method public abstract getPlaybackParameters()Landroidx/media3/common/PlaybackParameters;
.end method

.method public abstract handleBuffer(Ljava/nio/ByteBuffer;JI)Z
.end method

.method public abstract handleDiscontinuity()V
.end method

.method public abstract hasPendingData()Z
.end method

.method public abstract isEnded()Z
.end method

.method public abstract pause()V
.end method

.method public abstract play()V
.end method

.method public abstract playToEndOfStream()V
.end method

.method public abstract release()V
.end method

.method public abstract reset()V
.end method

.method public abstract setAudioAttributes(Landroidx/media3/common/AudioAttributes;)V
.end method

.method public abstract setAudioSessionId(I)V
.end method

.method public abstract setAuxEffectInfo(Landroidx/media3/common/AuxEffectInfo;)V
.end method

.method public abstract setClock(Landroidx/media3/common/util/Clock;)V
.end method

.method public abstract setListener(Landroidx/media3/exoplayer/audio/AudioSink$Listener;)V
.end method

.method public abstract setOffloadDelayPadding(II)V
.end method

.method public abstract setOffloadMode(I)V
.end method

.method public setOutputStreamOffsetUs(J)V
    .locals 0

    return-void
.end method

.method public abstract setPlaybackParameters(Landroidx/media3/common/PlaybackParameters;)V
.end method

.method public abstract setPlayerId(Landroidx/media3/exoplayer/analytics/PlayerId;)V
.end method

.method public abstract setPreferredDevice(Landroid/media/AudioDeviceInfo;)V
.end method

.method public abstract setSkipSilenceEnabled(Z)V
.end method

.method public abstract setVolume(F)V
.end method

.method public abstract supportsFormat(Landroidx/media3/common/Format;)Z
.end method
