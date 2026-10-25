.class abstract Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;
    }
.end annotation


# direct methods
.method public static copyRange(Ljava/io/File;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JJZ)Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;
    .locals 14

    .line 38
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->getDurationUs(Ljava/io/File;)J

    move-result-wide v2

    const-wide/16 v8, 0x3e8

    mul-long v4, p2, v8

    const-wide/16 v6, 0x0

    .line 39
    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    mul-long v10, p4, v8

    .line 40
    invoke-static {v4, v5, v10, v11}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v10

    invoke-static {v2, v3, v10, v11}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v2

    .line 41
    invoke-static {p0, v4, v5, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->findVideoSyncStart(Ljava/io/File;JJ)J

    move-result-wide v4

    cmp-long v0, v4, v6

    if-ltz v0, :cond_4

    cmp-long v0, v4, v2

    if-gez v0, :cond_4

    .line 46
    new-instance v6, Landroid/media/MediaExtractor;

    invoke-direct {v6}, Landroid/media/MediaExtractor;-><init>()V

    .line 48
    :try_start_0
    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v6, v0}, Landroid/media/MediaExtractor;->setDataSource(Ljava/lang/String;)V

    const/4 v0, 0x1

    .line 49
    invoke-static {v6, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->findTrack(Landroid/media/MediaExtractor;Z)I

    move-result v7

    const/4 v10, 0x0

    .line 50
    invoke-static {v6, v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->findTrack(Landroid/media/MediaExtractor;Z)I

    move-result v11

    if-ltz v7, :cond_3

    if-eqz p6, :cond_0

    if-ltz v11, :cond_3

    .line 54
    :cond_0
    invoke-virtual {v6, v7}, Landroid/media/MediaExtractor;->getTrackFormat(I)Landroid/media/MediaFormat;

    move-result-object v7

    invoke-virtual {p1, v0, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->setTrackFormat(ZLandroid/media/MediaFormat;)V

    if-eqz p6, :cond_1

    .line 55
    invoke-virtual {v6, v11}, Landroid/media/MediaExtractor;->getTrackFormat(I)Landroid/media/MediaFormat;

    move-result-object v0

    invoke-virtual {p1, v10, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->setTrackFormat(ZLandroid/media/MediaFormat;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 57
    :cond_1
    :goto_0
    invoke-virtual {v6}, Landroid/media/MediaExtractor;->release()V

    const/high16 v0, 0x200000

    .line 60
    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v7

    move-wide v12, v4

    move-wide v5, v2

    move-wide v3, v12

    const/4 v2, 0x1

    move-object v0, p0

    move-object v1, p1

    .line 61
    invoke-static/range {v0 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->copyTrack(Ljava/io/File;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZJJLjava/nio/ByteBuffer;)V

    if-eqz p6, :cond_2

    const/4 v2, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 62
    invoke-static/range {v0 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->copyTrack(Ljava/io/File;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZJJLjava/nio/ByteBuffer;)V

    .line 63
    :cond_2
    new-instance p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;

    div-long v0, v3, v8

    sub-long v2, v5, v3

    div-long/2addr v2, v8

    invoke-direct {p0, v0, v1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;-><init>(JJ)V

    return-object p0

    .line 52
    :cond_3
    :try_start_1
    new-instance p0, Ljava/io/IOException;

    const-string v0, "Source file has no required tracks"

    invoke-direct {p0, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 57
    :goto_1
    invoke-virtual {v6}, Landroid/media/MediaExtractor;->release()V

    .line 58
    throw p0

    .line 43
    :cond_4
    const-string p0, "Trim range has no video sync sample"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private static copyTrack(Ljava/io/File;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZJJLjava/nio/ByteBuffer;)V
    .locals 17

    move/from16 v1, p2

    move-wide/from16 v6, p3

    .line 86
    new-instance v8, Landroid/media/MediaExtractor;

    invoke-direct {v8}, Landroid/media/MediaExtractor;-><init>()V

    .line 87
    new-instance v9, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v9}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    .line 89
    :try_start_0
    invoke-virtual/range {p0 .. p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v8, v0}, Landroid/media/MediaExtractor;->setDataSource(Ljava/lang/String;)V

    .line 90
    invoke-static {v8, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->findTrack(Landroid/media/MediaExtractor;Z)I

    move-result v0

    if-ltz v0, :cond_4

    .line 92
    invoke-virtual {v8, v0}, Landroid/media/MediaExtractor;->selectTrack(I)V

    if-eqz v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x2

    .line 93
    :goto_0
    invoke-virtual {v8, v6, v7, v0}, Landroid/media/MediaExtractor;->seekTo(JI)V

    .line 96
    :goto_1
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide v2

    const-wide/16 v15, 0x0

    cmp-long v0, v2, v15

    if-ltz v0, :cond_1

    invoke-virtual {v8}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide v2

    cmp-long v0, v2, v6

    if-gez v0, :cond_1

    .line 97
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->advance()Z

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_4

    .line 99
    :cond_1
    :goto_2
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide v2

    cmp-long v0, v2, v15

    if-ltz v0, :cond_3

    invoke-virtual {v8}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide v2

    cmp-long v0, v2, p5

    if-gez v0, :cond_3

    .line 100
    invoke-virtual/range {p7 .. p7}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    const/4 v0, 0x0

    move-object/from16 v2, p7

    .line 101
    invoke-virtual {v8, v2, v0}, Landroid/media/MediaExtractor;->readSampleData(Ljava/nio/ByteBuffer;I)I

    move-result v11

    if-gez v11, :cond_2

    goto :goto_3

    .line 103
    :cond_2
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide v12

    invoke-virtual {v8}, Landroid/media/MediaExtractor;->getSampleFlags()I

    move-result v14

    const/4 v10, 0x0

    invoke-virtual/range {v9 .. v14}, Landroid/media/MediaCodec$BufferInfo;->set(IIJI)V

    neg-long v4, v6

    move-object/from16 v0, p1

    move-object v3, v9

    .line 104
    invoke-virtual/range {v0 .. v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->writeSample(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;J)V

    .line 105
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->advance()Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move/from16 v1, p2

    goto :goto_2

    .line 108
    :cond_3
    :goto_3
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->release()V

    return-void

    .line 91
    :cond_4
    :try_start_1
    new-instance v0, Ljava/io/IOException;

    const-string v1, "Source track disappeared"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 108
    :goto_4
    invoke-virtual {v8}, Landroid/media/MediaExtractor;->release()V

    .line 109
    throw v0
.end method

.method public static erase(Ljava/io/File;)V
    .locals 3

    .line 71
    :try_start_0
    new-instance v0, Ljava/io/RandomAccessFile;

    const-string v1, "rw"

    invoke-direct {v0, p0, v1}, Ljava/io/RandomAccessFile;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    const-wide/16 v1, 0x0

    .line 72
    :try_start_1
    invoke-virtual {v0, v1, v2}, Ljava/io/RandomAccessFile;->setLength(J)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 73
    :try_start_2
    invoke-virtual {v0}, Ljava/io/RandomAccessFile;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_1

    :catchall_0
    move-exception v1

    .line 71
    :try_start_3
    invoke-virtual {v0}, Ljava/io/RandomAccessFile;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception v0

    :try_start_4
    invoke-virtual {v1, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_0
    throw v1
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_0

    .line 75
    :catch_0
    :goto_1
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Ljava/io/File;->delete()Z

    :cond_0
    return-void
.end method

.method private static findTrack(Landroid/media/MediaExtractor;Z)I
    .locals 3

    if-eqz p1, :cond_0

    .line 149
    const-string p1, "video/"

    goto :goto_0

    :cond_0
    const-string p1, "audio/"

    :goto_0
    const/4 v0, 0x0

    .line 150
    :goto_1
    invoke-virtual {p0}, Landroid/media/MediaExtractor;->getTrackCount()I

    move-result v1

    if-ge v0, v1, :cond_2

    .line 151
    invoke-virtual {p0, v0}, Landroid/media/MediaExtractor;->getTrackFormat(I)Landroid/media/MediaFormat;

    move-result-object v1

    const-string v2, "mime"

    invoke-virtual {v1, v2}, Landroid/media/MediaFormat;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 152
    invoke-virtual {v1, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    return v0

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_2
    const/4 p0, -0x1

    return p0
.end method

.method private static findVideoSyncStart(Ljava/io/File;JJ)J
    .locals 6

    .line 117
    new-instance v0, Landroid/media/MediaExtractor;

    invoke-direct {v0}, Landroid/media/MediaExtractor;-><init>()V

    .line 119
    :try_start_0
    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/media/MediaExtractor;->setDataSource(Ljava/lang/String;)V

    const/4 p0, 0x1

    .line 120
    invoke-static {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->findTrack(Landroid/media/MediaExtractor;Z)I

    move-result v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const-wide/16 v2, -0x1

    if-gez v1, :cond_0

    .line 127
    invoke-virtual {v0}, Landroid/media/MediaExtractor;->release()V

    return-wide v2

    .line 122
    :cond_0
    :try_start_1
    invoke-virtual {v0, v1}, Landroid/media/MediaExtractor;->selectTrack(I)V

    .line 123
    invoke-virtual {v0, p1, p2, p0}, Landroid/media/MediaExtractor;->seekTo(JI)V

    .line 124
    invoke-virtual {v0}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const-wide/16 v4, 0x0

    cmp-long p2, p0, v4

    if-ltz p2, :cond_1

    cmp-long p2, p0, p3

    if-gez p2, :cond_1

    move-wide v2, p0

    .line 127
    :cond_1
    invoke-virtual {v0}, Landroid/media/MediaExtractor;->release()V

    return-wide v2

    :catchall_0
    move-exception p0

    invoke-virtual {v0}, Landroid/media/MediaExtractor;->release()V

    .line 128
    throw p0
.end method

.method public static getDurationMs(Ljava/io/File;)J
    .locals 4

    .line 67
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->getDurationUs(Ljava/io/File;)J

    move-result-wide v0

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    return-wide v0
.end method

.method private static getDurationUs(Ljava/io/File;)J
    .locals 6

    .line 132
    const-string v0, "durationUs"

    new-instance v1, Landroid/media/MediaExtractor;

    invoke-direct {v1}, Landroid/media/MediaExtractor;-><init>()V

    .line 134
    :try_start_0
    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Landroid/media/MediaExtractor;->setDataSource(Ljava/lang/String;)V

    const-wide/16 v2, 0x0

    const/4 p0, 0x0

    .line 136
    :goto_0
    invoke-virtual {v1}, Landroid/media/MediaExtractor;->getTrackCount()I

    move-result v4

    if-ge p0, v4, :cond_1

    .line 137
    invoke-virtual {v1, p0}, Landroid/media/MediaExtractor;->getTrackFormat(I)Landroid/media/MediaFormat;

    move-result-object v4

    .line 138
    invoke-virtual {v4, v0}, Landroid/media/MediaFormat;->containsKey(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_0

    .line 139
    invoke-virtual {v4, v0}, Landroid/media/MediaFormat;->getLong(Ljava/lang/String;)J

    move-result-wide v4

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    :cond_0
    :goto_1
    add-int/lit8 p0, p0, 0x1

    goto :goto_0

    .line 144
    :cond_1
    invoke-virtual {v1}, Landroid/media/MediaExtractor;->release()V

    return-wide v2

    :goto_2
    invoke-virtual {v1}, Landroid/media/MediaExtractor;->release()V

    .line 145
    throw p0
.end method
