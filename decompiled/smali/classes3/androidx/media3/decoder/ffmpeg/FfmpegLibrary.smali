.class public final Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static av1DecoderName:Ljava/lang/String;

.field private static inputBufferPaddingSize:I

.field private static version:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 33
    const-string v0, "media3.decoder.ffmpeg"

    invoke-static {v0}, Landroidx/media3/common/MediaLibraryInfo;->registerModule(Ljava/lang/String;)V

    const/4 v0, -0x1

    .line 39
    sput v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->inputBufferPaddingSize:I

    return-void
.end method

.method private static native ffmpegGetAv1DecoderName()Ljava/lang/String;
.end method

.method private static native ffmpegGetInputBufferPaddingSize()I
.end method

.method private static native ffmpegGetVersion()Ljava/lang/String;
.end method

.method private static native ffmpegHasDecoder(Ljava/lang/String;)Z
.end method

.method public static getCodecName(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 115
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p0}, Ljava/lang/String;->hashCode()I

    move-result v0

    const/4 v1, -0x1

    sparse-switch v0, :sswitch_data_0

    goto/16 :goto_0

    :sswitch_0
    const-string v0, "audio/g711-mlaw"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_0

    goto/16 :goto_0

    :cond_0
    const/16 v1, 0x25

    goto/16 :goto_0

    :sswitch_1
    const-string v0, "audio/g711-alaw"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    goto/16 :goto_0

    :cond_1
    const/16 v1, 0x24

    goto/16 :goto_0

    :sswitch_2
    const-string/jumbo v0, "video/x-vnd.on2.vp9"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    goto/16 :goto_0

    :cond_2
    const/16 v1, 0x23

    goto/16 :goto_0

    :sswitch_3
    const-string/jumbo v0, "video/x-vnd.on2.vp8"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_3

    goto/16 :goto_0

    :cond_3
    const/16 v1, 0x22

    goto/16 :goto_0

    :sswitch_4
    const-string v0, "audio/true-hd"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_4

    goto/16 :goto_0

    :cond_4
    const/16 v1, 0x21

    goto/16 :goto_0

    :sswitch_5
    const-string v0, "audio/vnd.dts.hd"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_5

    goto/16 :goto_0

    :cond_5
    const/16 v1, 0x20

    goto/16 :goto_0

    :sswitch_6
    const-string v0, "audio/opus"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_6

    goto/16 :goto_0

    :cond_6
    const/16 v1, 0x1f

    goto/16 :goto_0

    :sswitch_7
    const-string v0, "audio/mpeg"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_7

    goto/16 :goto_0

    :cond_7
    const/16 v1, 0x1e

    goto/16 :goto_0

    :sswitch_8
    const-string v0, "audio/flac"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_8

    goto/16 :goto_0

    :cond_8
    const/16 v1, 0x1d

    goto/16 :goto_0

    :sswitch_9
    const-string v0, "audio/eac3"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_9

    goto/16 :goto_0

    :cond_9
    const/16 v1, 0x1c

    goto/16 :goto_0

    :sswitch_a
    const-string v0, "audio/alac"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_a

    goto/16 :goto_0

    :cond_a
    const/16 v1, 0x1b

    goto/16 :goto_0

    :sswitch_b
    const-string v0, "audio/3gpp"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_b

    goto/16 :goto_0

    :cond_b
    const/16 v1, 0x1a

    goto/16 :goto_0

    :sswitch_c
    const-string/jumbo v0, "video/ogg"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_c

    goto/16 :goto_0

    :cond_c
    const/16 v1, 0x19

    goto/16 :goto_0

    :sswitch_d
    const-string/jumbo v0, "video/avc"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_d

    goto/16 :goto_0

    :cond_d
    const/16 v1, 0x18

    goto/16 :goto_0

    :sswitch_e
    const-string/jumbo v0, "video/mp4v-es"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_e

    goto/16 :goto_0

    :cond_e
    const/16 v1, 0x17

    goto/16 :goto_0

    :sswitch_f
    const-string/jumbo v0, "video/prores"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_f

    goto/16 :goto_0

    :cond_f
    const/16 v1, 0x16

    goto/16 :goto_0

    :sswitch_10
    const-string v0, "audio/ac3"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_10

    goto/16 :goto_0

    :cond_10
    const/16 v1, 0x15

    goto/16 :goto_0

    :sswitch_11
    const-string/jumbo v0, "video/x-flv"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_11

    goto/16 :goto_0

    :cond_11
    const/16 v1, 0x14

    goto/16 :goto_0

    :sswitch_12
    const-string/jumbo v0, "video/mpeg2"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_12

    goto/16 :goto_0

    :cond_12
    const/16 v1, 0x13

    goto/16 :goto_0

    :sswitch_13
    const-string/jumbo v0, "video/mjpeg"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_13

    goto/16 :goto_0

    :cond_13
    const/16 v1, 0x12

    goto/16 :goto_0

    :sswitch_14
    const-string v0, "audio/mp4a-latm"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_14

    goto/16 :goto_0

    :cond_14
    const/16 v1, 0x11

    goto/16 :goto_0

    :sswitch_15
    const-string v0, "audio/mpeg-L2"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_15

    goto/16 :goto_0

    :cond_15
    const/16 v1, 0x10

    goto/16 :goto_0

    :sswitch_16
    const-string v0, "audio/mpeg-L1"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_16

    goto/16 :goto_0

    :cond_16
    const/16 v1, 0xf

    goto/16 :goto_0

    :sswitch_17
    const-string v0, "audio/vorbis"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_17

    goto/16 :goto_0

    :cond_17
    const/16 v1, 0xe

    goto/16 :goto_0

    :sswitch_18
    const-string/jumbo v0, "video/x-msvideo"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_18

    goto/16 :goto_0

    :cond_18
    const/16 v1, 0xd

    goto/16 :goto_0

    :sswitch_19
    const-string v0, "audio/vnd.dts"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_19

    goto/16 :goto_0

    :cond_19
    const/16 v1, 0xc

    goto/16 :goto_0

    :sswitch_1a
    const-string v0, "audio/vnd.dts.hd;profile=lbr"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1a

    goto/16 :goto_0

    :cond_1a
    const/16 v1, 0xb

    goto/16 :goto_0

    :sswitch_1b
    const-string v0, "audio/amr-wb"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1b

    goto/16 :goto_0

    :cond_1b
    const/16 v1, 0xa

    goto/16 :goto_0

    :sswitch_1c
    const-string/jumbo v0, "video/wvc1"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1c

    goto/16 :goto_0

    :cond_1c
    const/16 v1, 0x9

    goto/16 :goto_0

    :sswitch_1d
    const-string/jumbo v0, "video/mpeg"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1d

    goto/16 :goto_0

    :cond_1d
    const/16 v1, 0x8

    goto/16 :goto_0

    :sswitch_1e
    const-string/jumbo v0, "video/mp43"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1e

    goto :goto_0

    :cond_1e
    const/4 v1, 0x7

    goto :goto_0

    :sswitch_1f
    const-string/jumbo v0, "video/mp42"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1f

    goto :goto_0

    :cond_1f
    const/4 v1, 0x6

    goto :goto_0

    :sswitch_20
    const-string/jumbo v0, "video/hevc"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_20

    goto :goto_0

    :cond_20
    const/4 v1, 0x5

    goto :goto_0

    :sswitch_21
    const-string/jumbo v0, "video/divx"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_21

    goto :goto_0

    :cond_21
    const/4 v1, 0x4

    goto :goto_0

    :sswitch_22
    const-string/jumbo v0, "video/av01"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_22

    goto :goto_0

    :cond_22
    const/4 v1, 0x3

    goto :goto_0

    :sswitch_23
    const-string/jumbo v0, "video/3gpp"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_23

    goto :goto_0

    :cond_23
    const/4 v1, 0x2

    goto :goto_0

    :sswitch_24
    const-string/jumbo v0, "video/dolby-vision"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_24

    goto :goto_0

    :cond_24
    const/4 v1, 0x1

    goto :goto_0

    :sswitch_25
    const-string v0, "audio/eac3-joc"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_25

    goto :goto_0

    :cond_25
    const/4 v1, 0x0

    .line 195
    :goto_0
    const-string/jumbo p0, "mpeg4"

    const-string/jumbo v0, "mpeg2video"

    packed-switch v1, :pswitch_data_0

    const/4 p0, 0x0

    return-object p0

    .line 146
    :pswitch_0
    const-string/jumbo p0, "pcm_mulaw"

    return-object p0

    .line 148
    :pswitch_1
    const-string/jumbo p0, "pcm_alaw"

    return-object p0

    .line 157
    :pswitch_2
    const-string/jumbo p0, "vp9"

    return-object p0

    .line 155
    :pswitch_3
    const-string/jumbo p0, "vp8"

    return-object p0

    .line 128
    :pswitch_4
    const-string/jumbo p0, "truehd"

    return-object p0

    .line 136
    :pswitch_5
    const-string/jumbo p0, "opus"

    return-object p0

    .line 142
    :pswitch_6
    const-string p0, "flac"

    return-object p0

    .line 144
    :pswitch_7
    const-string p0, "alac"

    return-object p0

    .line 138
    :pswitch_8
    const-string p0, "amrnb"

    return-object p0

    .line 191
    :pswitch_9
    const-string/jumbo p0, "theora"

    return-object p0

    .line 150
    :pswitch_a
    const-string p0, "h264"

    return-object p0

    .line 167
    :pswitch_b
    const-string/jumbo p0, "prores"

    return-object p0

    .line 123
    :pswitch_c
    const-string p0, "ac3"

    return-object p0

    .line 187
    :pswitch_d
    const-string p0, "flv1"

    return-object p0

    :pswitch_e
    return-object v0

    .line 183
    :pswitch_f
    const-string/jumbo p0, "mjpeg"

    return-object p0

    .line 117
    :pswitch_10
    const-string p0, "aac"

    return-object p0

    .line 121
    :pswitch_11
    const-string/jumbo p0, "mp3"

    return-object p0

    .line 134
    :pswitch_12
    const-string/jumbo p0, "vorbis"

    :pswitch_13
    return-object p0

    .line 132
    :pswitch_14
    const-string p0, "dca"

    return-object p0

    .line 140
    :pswitch_15
    const-string p0, "amrwb"

    return-object p0

    .line 189
    :pswitch_16
    const-string/jumbo p0, "vc1"

    return-object p0

    :pswitch_17
    return-object v0

    .line 174
    :pswitch_18
    const-string/jumbo p0, "msmpeg4"

    return-object p0

    .line 172
    :pswitch_19
    const-string/jumbo p0, "msmpeg4v2"

    :pswitch_1a
    return-object p0

    .line 178
    :pswitch_1b
    sget-object p0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->av1DecoderName:Ljava/lang/String;

    if-nez p0, :cond_26

    .line 179
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->ffmpegGetAv1DecoderName()Ljava/lang/String;

    move-result-object p0

    sput-object p0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->av1DecoderName:Ljava/lang/String;

    .line 181
    :cond_26
    sget-object p0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->av1DecoderName:Ljava/lang/String;

    return-object p0

    .line 185
    :pswitch_1c
    const-string p0, "h263"

    return-object p0

    .line 153
    :pswitch_1d
    const-string p0, "hevc"

    return-object p0

    .line 126
    :pswitch_1e
    const-string p0, "eac3"

    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x7e929daa -> :sswitch_25
        -0x6e5534ef -> :sswitch_24
        -0x63306f58 -> :sswitch_23
        -0x631b55f6 -> :sswitch_22
        -0x631a20e5 -> :sswitch_21
        -0x63185e82 -> :sswitch_20
        -0x6315f78b -> :sswitch_1f
        -0x6315f78a -> :sswitch_1e
        -0x6315f167 -> :sswitch_1d
        -0x63114f9f -> :sswitch_1c
        -0x5fc6f775 -> :sswitch_1b
        -0x51617051 -> :sswitch_1a
        -0x41455b98 -> :sswitch_19
        -0x405dba54 -> :sswitch_18
        -0x3bd43e14 -> :sswitch_17
        -0x19cc928c -> :sswitch_16
        -0x19cc928b -> :sswitch_15
        -0x3313c2e -> :sswitch_14
        0x5533c1 -> :sswitch_13
        0x57c4b9 -> :sswitch_12
        0xd45707 -> :sswitch_11
        0xb269698 -> :sswitch_10
        0xfe046e7 -> :sswitch_f
        0x46cdc642 -> :sswitch_e
        0x4f62373a -> :sswitch_d
        0x4f6269fb -> :sswitch_c
        0x59976a2d -> :sswitch_b
        0x59ac6426 -> :sswitch_a
        0x59ae0c65 -> :sswitch_9
        0x59aeaa01 -> :sswitch_8
        0x59b1e81e -> :sswitch_7
        0x59b2d2d8 -> :sswitch_6
        0x59c2dc42 -> :sswitch_5
        0x5cc95062 -> :sswitch_4
        0x5f50bed8 -> :sswitch_3
        0x5f50bed9 -> :sswitch_2
        0x71710385 -> :sswitch_1
        0x717677f9 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1e
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_1d
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_1a
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_1e
        :pswitch_6
        :pswitch_11
        :pswitch_5
        :pswitch_14
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static getInputBufferPaddingSize()I
    .locals 2

    .line 77
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->isAvailable()Z

    move-result v0

    const/4 v1, -0x1

    if-nez v0, :cond_0

    return v1

    .line 80
    :cond_0
    sget v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->inputBufferPaddingSize:I

    if-ne v0, v1, :cond_1

    .line 81
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->ffmpegGetInputBufferPaddingSize()I

    move-result v0

    sput v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->inputBufferPaddingSize:I

    .line 83
    :cond_1
    sget v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->inputBufferPaddingSize:I

    return v0
.end method

.method public static getVersion()Ljava/lang/String;
    .locals 1

    .line 63
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->isAvailable()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    .line 66
    :cond_0
    sget-object v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->version:Ljava/lang/String;

    if-nez v0, :cond_1

    .line 67
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->ffmpegGetVersion()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->version:Ljava/lang/String;

    .line 69
    :cond_1
    sget-object v0, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->version:Ljava/lang/String;

    return-object v0
.end method

.method public static isAvailable()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public static supportsFormat(Ljava/lang/String;)Z
    .locals 3

    .line 92
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->isAvailable()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    if-nez p0, :cond_1

    return v1

    .line 98
    :cond_1
    invoke-static {p0}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->getCodecName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_2

    return v1

    .line 102
    :cond_2
    invoke-static {p0}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->ffmpegHasDecoder(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 103
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "No "

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " decoder available. Check the FFmpeg build configuration."

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "FfmpegLibrary"

    invoke-static {v0, p0}, Landroidx/media3/common/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return v1

    :cond_3
    const/4 p0, 0x1

    return p0
.end method
