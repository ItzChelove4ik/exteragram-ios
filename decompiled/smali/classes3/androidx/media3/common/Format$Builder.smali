.class public final Landroidx/media3/common/Format$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/common/Format;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private accessibilityChannel:I

.field private auxiliaryTrackType:I

.field private averageBitrate:I

.field public cached:Z

.field private channelCount:I

.field private codecs:Ljava/lang/String;

.field private colorInfo:Landroidx/media3/common/ColorInfo;

.field private containerMimeType:Ljava/lang/String;

.field private cryptoType:I

.field private cueReplacementBehavior:I

.field public currentAccount:I

.field private customData:Ljava/lang/Object;

.field private decodedHeight:I

.field private decodedWidth:I

.field public documentFilename:Ljava/lang/String;

.field public documentId:J

.field private drmInitData:Landroidx/media3/common/DrmInitData;

.field private encoderDelay:I

.field private encoderPadding:I

.field private frameRate:F

.field private hasPrerollSamples:Z

.field private height:I

.field private id:Ljava/lang/String;

.field private initializationData:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "[B>;"
        }
    .end annotation
.end field

.field private label:Ljava/lang/String;

.field private labels:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/media3/common/Label;",
            ">;"
        }
    .end annotation
.end field

.field private language:Ljava/lang/String;

.field private maxInputSize:I

.field private maxNumReorderSamples:I

.field private maxSubLayers:I

.field private metadata:Landroidx/media3/common/Metadata;

.field private pcmEncoding:I

.field private peakBitrate:I

.field private pixelWidthHeightRatio:F

.field private projectionData:[B

.field private roleFlags:I

.field private rotationDegrees:I

.field private sampleMimeType:Ljava/lang/String;

.field private sampleRate:I

.field private selectionFlags:I

.field private stereoMode:I

.field private subsampleOffsetUs:J

.field private tileCountHorizontal:I

.field private tileCountVertical:I

.field private width:I


# direct methods
.method public constructor <init>()V
    .locals 3

    .line 216
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 217
    invoke-static {}, Lcom/google/common/collect/ImmutableList;->of()Lcom/google/common/collect/ImmutableList;

    move-result-object v0

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->labels:Ljava/util/List;

    const/4 v0, -0x1

    .line 218
    iput v0, p0, Landroidx/media3/common/Format$Builder;->averageBitrate:I

    .line 219
    iput v0, p0, Landroidx/media3/common/Format$Builder;->peakBitrate:I

    .line 221
    iput v0, p0, Landroidx/media3/common/Format$Builder;->maxInputSize:I

    .line 222
    iput v0, p0, Landroidx/media3/common/Format$Builder;->maxNumReorderSamples:I

    const-wide v1, 0x7fffffffffffffffL

    .line 223
    iput-wide v1, p0, Landroidx/media3/common/Format$Builder;->subsampleOffsetUs:J

    .line 225
    iput v0, p0, Landroidx/media3/common/Format$Builder;->width:I

    .line 226
    iput v0, p0, Landroidx/media3/common/Format$Builder;->height:I

    .line 227
    iput v0, p0, Landroidx/media3/common/Format$Builder;->decodedWidth:I

    .line 228
    iput v0, p0, Landroidx/media3/common/Format$Builder;->decodedHeight:I

    const/high16 v1, -0x40800000    # -1.0f

    .line 229
    iput v1, p0, Landroidx/media3/common/Format$Builder;->frameRate:F

    const/high16 v1, 0x3f800000    # 1.0f

    .line 230
    iput v1, p0, Landroidx/media3/common/Format$Builder;->pixelWidthHeightRatio:F

    .line 231
    iput v0, p0, Landroidx/media3/common/Format$Builder;->stereoMode:I

    .line 232
    iput v0, p0, Landroidx/media3/common/Format$Builder;->maxSubLayers:I

    .line 234
    iput v0, p0, Landroidx/media3/common/Format$Builder;->channelCount:I

    .line 235
    iput v0, p0, Landroidx/media3/common/Format$Builder;->sampleRate:I

    .line 236
    iput v0, p0, Landroidx/media3/common/Format$Builder;->pcmEncoding:I

    .line 238
    iput v0, p0, Landroidx/media3/common/Format$Builder;->accessibilityChannel:I

    const/4 v1, 0x1

    .line 239
    iput v1, p0, Landroidx/media3/common/Format$Builder;->cueReplacementBehavior:I

    .line 241
    iput v0, p0, Landroidx/media3/common/Format$Builder;->tileCountHorizontal:I

    .line 242
    iput v0, p0, Landroidx/media3/common/Format$Builder;->tileCountVertical:I

    const/4 v0, 0x0

    .line 244
    iput v0, p0, Landroidx/media3/common/Format$Builder;->cryptoType:I

    .line 245
    iput v0, p0, Landroidx/media3/common/Format$Builder;->auxiliaryTrackType:I

    .line 246
    iput-boolean v0, p0, Landroidx/media3/common/Format$Builder;->cached:Z

    return-void
.end method

.method private constructor <init>(Landroidx/media3/common/Format;)V
    .locals 2

    .line 254
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 255
    iget-object v0, p1, Landroidx/media3/common/Format;->id:Ljava/lang/String;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->id:Ljava/lang/String;

    .line 256
    iget-object v0, p1, Landroidx/media3/common/Format;->label:Ljava/lang/String;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->label:Ljava/lang/String;

    .line 257
    iget-object v0, p1, Landroidx/media3/common/Format;->labels:Ljava/util/List;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->labels:Ljava/util/List;

    .line 258
    iget-object v0, p1, Landroidx/media3/common/Format;->language:Ljava/lang/String;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->language:Ljava/lang/String;

    .line 259
    iget v0, p1, Landroidx/media3/common/Format;->selectionFlags:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->selectionFlags:I

    .line 260
    iget v0, p1, Landroidx/media3/common/Format;->roleFlags:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->roleFlags:I

    .line 261
    iget v0, p1, Landroidx/media3/common/Format;->averageBitrate:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->averageBitrate:I

    .line 262
    iget v0, p1, Landroidx/media3/common/Format;->peakBitrate:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->peakBitrate:I

    .line 263
    iget-object v0, p1, Landroidx/media3/common/Format;->codecs:Ljava/lang/String;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->codecs:Ljava/lang/String;

    .line 264
    iget-object v0, p1, Landroidx/media3/common/Format;->metadata:Landroidx/media3/common/Metadata;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->metadata:Landroidx/media3/common/Metadata;

    .line 265
    iget-object v0, p1, Landroidx/media3/common/Format;->customData:Ljava/lang/Object;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->customData:Ljava/lang/Object;

    .line 267
    iget-object v0, p1, Landroidx/media3/common/Format;->containerMimeType:Ljava/lang/String;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->containerMimeType:Ljava/lang/String;

    .line 269
    iget-object v0, p1, Landroidx/media3/common/Format;->sampleMimeType:Ljava/lang/String;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->sampleMimeType:Ljava/lang/String;

    .line 270
    iget v0, p1, Landroidx/media3/common/Format;->maxInputSize:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->maxInputSize:I

    .line 271
    iget v0, p1, Landroidx/media3/common/Format;->maxNumReorderSamples:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->maxNumReorderSamples:I

    .line 272
    iget-object v0, p1, Landroidx/media3/common/Format;->initializationData:Ljava/util/List;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->initializationData:Ljava/util/List;

    .line 273
    iget-object v0, p1, Landroidx/media3/common/Format;->drmInitData:Landroidx/media3/common/DrmInitData;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->drmInitData:Landroidx/media3/common/DrmInitData;

    .line 274
    iget-wide v0, p1, Landroidx/media3/common/Format;->subsampleOffsetUs:J

    iput-wide v0, p0, Landroidx/media3/common/Format$Builder;->subsampleOffsetUs:J

    .line 275
    iget-boolean v0, p1, Landroidx/media3/common/Format;->hasPrerollSamples:Z

    iput-boolean v0, p0, Landroidx/media3/common/Format$Builder;->hasPrerollSamples:Z

    .line 277
    iget v0, p1, Landroidx/media3/common/Format;->width:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->width:I

    .line 278
    iget v0, p1, Landroidx/media3/common/Format;->height:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->height:I

    .line 279
    iget v0, p1, Landroidx/media3/common/Format;->decodedWidth:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->decodedWidth:I

    .line 280
    iget v0, p1, Landroidx/media3/common/Format;->decodedHeight:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->decodedHeight:I

    .line 281
    iget v0, p1, Landroidx/media3/common/Format;->frameRate:F

    iput v0, p0, Landroidx/media3/common/Format$Builder;->frameRate:F

    .line 282
    iget v0, p1, Landroidx/media3/common/Format;->rotationDegrees:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->rotationDegrees:I

    .line 283
    iget v0, p1, Landroidx/media3/common/Format;->pixelWidthHeightRatio:F

    iput v0, p0, Landroidx/media3/common/Format$Builder;->pixelWidthHeightRatio:F

    .line 284
    iget-object v0, p1, Landroidx/media3/common/Format;->projectionData:[B

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->projectionData:[B

    .line 285
    iget v0, p1, Landroidx/media3/common/Format;->stereoMode:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->stereoMode:I

    .line 286
    iget-object v0, p1, Landroidx/media3/common/Format;->colorInfo:Landroidx/media3/common/ColorInfo;

    iput-object v0, p0, Landroidx/media3/common/Format$Builder;->colorInfo:Landroidx/media3/common/ColorInfo;

    .line 287
    iget v0, p1, Landroidx/media3/common/Format;->maxSubLayers:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->maxSubLayers:I

    .line 289
    iget v0, p1, Landroidx/media3/common/Format;->channelCount:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->channelCount:I

    .line 290
    iget v0, p1, Landroidx/media3/common/Format;->sampleRate:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->sampleRate:I

    .line 291
    iget v0, p1, Landroidx/media3/common/Format;->pcmEncoding:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->pcmEncoding:I

    .line 292
    iget v0, p1, Landroidx/media3/common/Format;->encoderDelay:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->encoderDelay:I

    .line 293
    iget v0, p1, Landroidx/media3/common/Format;->encoderPadding:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->encoderPadding:I

    .line 295
    iget v0, p1, Landroidx/media3/common/Format;->accessibilityChannel:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->accessibilityChannel:I

    .line 296
    iget v0, p1, Landroidx/media3/common/Format;->cueReplacementBehavior:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->cueReplacementBehavior:I

    .line 298
    iget v0, p1, Landroidx/media3/common/Format;->tileCountHorizontal:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->tileCountHorizontal:I

    .line 299
    iget v0, p1, Landroidx/media3/common/Format;->tileCountVertical:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->tileCountVertical:I

    .line 301
    iget v0, p1, Landroidx/media3/common/Format;->cryptoType:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->cryptoType:I

    .line 302
    iget-boolean v0, p1, Landroidx/media3/common/Format;->cached:Z

    iput-boolean v0, p0, Landroidx/media3/common/Format$Builder;->cached:Z

    .line 303
    iget-wide v0, p1, Landroidx/media3/common/Format;->documentId:J

    iput-wide v0, p0, Landroidx/media3/common/Format$Builder;->documentId:J

    .line 304
    iget v0, p1, Landroidx/media3/common/Format;->currentAccount:I

    iput v0, p0, Landroidx/media3/common/Format$Builder;->currentAccount:I

    .line 305
    iget-object p1, p1, Landroidx/media3/common/Format;->documentFilename:Ljava/lang/String;

    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->documentFilename:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Landroidx/media3/common/Format;Landroidx/media3/common/Format$1;)V
    .locals 0

    .line 146
    invoke-direct {p0, p1}, Landroidx/media3/common/Format$Builder;-><init>(Landroidx/media3/common/Format;)V

    return-void
.end method

.method public static synthetic access$100(Landroidx/media3/common/Format$Builder;)Ljava/util/List;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->labels:Ljava/util/List;

    return-object p0
.end method

.method public static synthetic access$1000(Landroidx/media3/common/Format$Builder;)Ljava/lang/String;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->codecs:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$1100(Landroidx/media3/common/Format$Builder;)Landroidx/media3/common/Metadata;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->metadata:Landroidx/media3/common/Metadata;

    return-object p0
.end method

.method public static synthetic access$1200(Landroidx/media3/common/Format$Builder;)Ljava/lang/Object;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->customData:Ljava/lang/Object;

    return-object p0
.end method

.method public static synthetic access$1300(Landroidx/media3/common/Format$Builder;)Ljava/lang/String;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->containerMimeType:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$1400(Landroidx/media3/common/Format$Builder;)Ljava/lang/String;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->sampleMimeType:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$1500(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->maxInputSize:I

    return p0
.end method

.method public static synthetic access$1600(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->maxNumReorderSamples:I

    return p0
.end method

.method public static synthetic access$1700(Landroidx/media3/common/Format$Builder;)Ljava/util/List;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->initializationData:Ljava/util/List;

    return-object p0
.end method

.method public static synthetic access$1800(Landroidx/media3/common/Format$Builder;)Landroidx/media3/common/DrmInitData;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->drmInitData:Landroidx/media3/common/DrmInitData;

    return-object p0
.end method

.method public static synthetic access$1900(Landroidx/media3/common/Format$Builder;)J
    .locals 2

    .line 146
    iget-wide v0, p0, Landroidx/media3/common/Format$Builder;->subsampleOffsetUs:J

    return-wide v0
.end method

.method public static synthetic access$200(Landroidx/media3/common/Format$Builder;)Ljava/lang/String;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->label:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$2000(Landroidx/media3/common/Format$Builder;)Z
    .locals 0

    .line 146
    iget-boolean p0, p0, Landroidx/media3/common/Format$Builder;->hasPrerollSamples:Z

    return p0
.end method

.method public static synthetic access$2100(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->width:I

    return p0
.end method

.method public static synthetic access$2200(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->height:I

    return p0
.end method

.method public static synthetic access$2300(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->decodedWidth:I

    return p0
.end method

.method public static synthetic access$2400(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->decodedHeight:I

    return p0
.end method

.method public static synthetic access$2500(Landroidx/media3/common/Format$Builder;)F
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->frameRate:F

    return p0
.end method

.method public static synthetic access$2600(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->rotationDegrees:I

    return p0
.end method

.method public static synthetic access$2700(Landroidx/media3/common/Format$Builder;)F
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->pixelWidthHeightRatio:F

    return p0
.end method

.method public static synthetic access$2800(Landroidx/media3/common/Format$Builder;)[B
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->projectionData:[B

    return-object p0
.end method

.method public static synthetic access$2900(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->stereoMode:I

    return p0
.end method

.method public static synthetic access$300(Landroidx/media3/common/Format$Builder;)Ljava/lang/String;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->id:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$3000(Landroidx/media3/common/Format$Builder;)Landroidx/media3/common/ColorInfo;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->colorInfo:Landroidx/media3/common/ColorInfo;

    return-object p0
.end method

.method public static synthetic access$3100(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->maxSubLayers:I

    return p0
.end method

.method public static synthetic access$3200(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->channelCount:I

    return p0
.end method

.method public static synthetic access$3300(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->sampleRate:I

    return p0
.end method

.method public static synthetic access$3400(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->pcmEncoding:I

    return p0
.end method

.method public static synthetic access$3500(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->encoderDelay:I

    return p0
.end method

.method public static synthetic access$3600(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->encoderPadding:I

    return p0
.end method

.method public static synthetic access$3700(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->accessibilityChannel:I

    return p0
.end method

.method public static synthetic access$3800(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->cueReplacementBehavior:I

    return p0
.end method

.method public static synthetic access$3900(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->tileCountHorizontal:I

    return p0
.end method

.method public static synthetic access$400(Landroidx/media3/common/Format$Builder;)Ljava/lang/String;
    .locals 0

    .line 146
    iget-object p0, p0, Landroidx/media3/common/Format$Builder;->language:Ljava/lang/String;

    return-object p0
.end method

.method public static synthetic access$4000(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->tileCountVertical:I

    return p0
.end method

.method public static synthetic access$4100(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->cryptoType:I

    return p0
.end method

.method public static synthetic access$500(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->selectionFlags:I

    return p0
.end method

.method public static synthetic access$600(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->auxiliaryTrackType:I

    return p0
.end method

.method public static synthetic access$700(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->roleFlags:I

    return p0
.end method

.method public static synthetic access$800(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->averageBitrate:I

    return p0
.end method

.method public static synthetic access$900(Landroidx/media3/common/Format$Builder;)I
    .locals 0

    .line 146
    iget p0, p0, Landroidx/media3/common/Format$Builder;->peakBitrate:I

    return p0
.end method


# virtual methods
.method public build()Landroidx/media3/common/Format;
    .locals 2

    .line 888
    new-instance v0, Landroidx/media3/common/Format;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Landroidx/media3/common/Format;-><init>(Landroidx/media3/common/Format$Builder;Landroidx/media3/common/Format$1;)V

    return-object v0
.end method

.method public setAccessibilityChannel(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 828
    iput p1, p0, Landroidx/media3/common/Format$Builder;->accessibilityChannel:I

    return-object p0
.end method

.method public setAuxiliaryTrackType(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 454
    iput p1, p0, Landroidx/media3/common/Format$Builder;->auxiliaryTrackType:I

    return-object p0
.end method

.method public setAverageBitrate(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 466
    iput p1, p0, Landroidx/media3/common/Format$Builder;->averageBitrate:I

    return-object p0
.end method

.method public setCached(Z)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 335
    iput-boolean p1, p0, Landroidx/media3/common/Format$Builder;->cached:Z

    return-object p0
.end method

.method public setChannelCount(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 766
    iput p1, p0, Landroidx/media3/common/Format$Builder;->channelCount:I

    return-object p0
.end method

.method public setCodecs(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 490
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->codecs:Ljava/lang/String;

    return-object p0
.end method

.method public setColorInfo(Landroidx/media3/common/ColorInfo;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 740
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->colorInfo:Landroidx/media3/common/ColorInfo;

    return-object p0
.end method

.method public setContainerMimeType(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 532
    invoke-static {p1}, Landroidx/media3/common/MimeTypes;->normalizeMimeType(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->containerMimeType:Ljava/lang/String;

    return-object p0
.end method

.method public setCryptoType(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 881
    iput p1, p0, Landroidx/media3/common/Format$Builder;->cryptoType:I

    return-object p0
.end method

.method public setCueReplacementBehavior(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 841
    iput p1, p0, Landroidx/media3/common/Format$Builder;->cueReplacementBehavior:I

    return-object p0
.end method

.method public setCurrentAccount(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 355
    iput p1, p0, Landroidx/media3/common/Format$Builder;->currentAccount:I

    return-object p0
.end method

.method public setCurrentAccount(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 362
    :try_start_0
    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1

    iput p1, p0, Landroidx/media3/common/Format$Builder;->currentAccount:I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-object p0
.end method

.method public setCustomData(Ljava/lang/Object;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 518
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->customData:Ljava/lang/Object;

    return-object p0
.end method

.method public setDecodedHeight(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 668
    iput p1, p0, Landroidx/media3/common/Format$Builder;->decodedHeight:I

    return-object p0
.end method

.method public setDecodedWidth(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 656
    iput p1, p0, Landroidx/media3/common/Format$Builder;->decodedWidth:I

    return-object p0
.end method

.method public setDocumentFilename(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 369
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->documentFilename:Ljava/lang/String;

    return-object p0
.end method

.method public setDocumentId(J)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 349
    iput-wide p1, p0, Landroidx/media3/common/Format$Builder;->documentId:J

    return-object p0
.end method

.method public setDocumentId(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 2

    .line 342
    :try_start_0
    invoke-static {p1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Landroidx/media3/common/Format$Builder;->documentId:J
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-object p0
.end method

.method public setDrmInitData(Landroidx/media3/common/DrmInitData;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 594
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->drmInitData:Landroidx/media3/common/DrmInitData;

    return-object p0
.end method

.method public setEncoderDelay(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 802
    iput p1, p0, Landroidx/media3/common/Format$Builder;->encoderDelay:I

    return-object p0
.end method

.method public setEncoderPadding(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 814
    iput p1, p0, Landroidx/media3/common/Format$Builder;->encoderPadding:I

    return-object p0
.end method

.method public setFrameRate(F)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 680
    iput p1, p0, Landroidx/media3/common/Format$Builder;->frameRate:F

    return-object p0
.end method

.method public setHasPrerollSamples(Z)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 618
    iput-boolean p1, p0, Landroidx/media3/common/Format$Builder;->hasPrerollSamples:Z

    return-object p0
.end method

.method public setHeight(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 644
    iput p1, p0, Landroidx/media3/common/Format$Builder;->height:I

    return-object p0
.end method

.method public setId(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 329
    invoke-static {p1}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->id:Ljava/lang/String;

    return-object p0
.end method

.method public setId(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 316
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->id:Ljava/lang/String;

    return-object p0
.end method

.method public setInitializationData(Ljava/util/List;)Landroidx/media3/common/Format$Builder;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "[B>;)",
            "Landroidx/media3/common/Format$Builder;"
        }
    .end annotation

    .line 582
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->initializationData:Ljava/util/List;

    return-object p0
.end method

.method public setLabel(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 384
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->label:Ljava/lang/String;

    return-object p0
.end method

.method public setLabels(Ljava/util/List;)Landroidx/media3/common/Format$Builder;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/media3/common/Label;",
            ">;)",
            "Landroidx/media3/common/Format$Builder;"
        }
    .end annotation

    .line 399
    invoke-static {p1}, Lcom/google/common/collect/ImmutableList;->copyOf(Ljava/util/Collection;)Lcom/google/common/collect/ImmutableList;

    move-result-object p1

    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->labels:Ljava/util/List;

    return-object p0
.end method

.method public setLanguage(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 411
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->language:Ljava/lang/String;

    return-object p0
.end method

.method public setMaxInputSize(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 558
    iput p1, p0, Landroidx/media3/common/Format$Builder;->maxInputSize:I

    return-object p0
.end method

.method public setMaxNumReorderSamples(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 570
    iput p1, p0, Landroidx/media3/common/Format$Builder;->maxNumReorderSamples:I

    return-object p0
.end method

.method public setMaxSubLayers(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 752
    iput p1, p0, Landroidx/media3/common/Format$Builder;->maxSubLayers:I

    return-object p0
.end method

.method public setMetadata(Landroidx/media3/common/Metadata;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 502
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->metadata:Landroidx/media3/common/Metadata;

    return-object p0
.end method

.method public setPcmEncoding(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 790
    iput p1, p0, Landroidx/media3/common/Format$Builder;->pcmEncoding:I

    return-object p0
.end method

.method public setPeakBitrate(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 478
    iput p1, p0, Landroidx/media3/common/Format$Builder;->peakBitrate:I

    return-object p0
.end method

.method public setPixelWidthHeightRatio(F)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 704
    iput p1, p0, Landroidx/media3/common/Format$Builder;->pixelWidthHeightRatio:F

    return-object p0
.end method

.method public setProjectionData([B)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 716
    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->projectionData:[B

    return-object p0
.end method

.method public setRoleFlags(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 438
    iput p1, p0, Landroidx/media3/common/Format$Builder;->roleFlags:I

    return-object p0
.end method

.method public setRotationDegrees(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 692
    iput p1, p0, Landroidx/media3/common/Format$Builder;->rotationDegrees:I

    return-object p0
.end method

.method public setSampleMimeType(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 546
    invoke-static {p1}, Landroidx/media3/common/MimeTypes;->normalizeMimeType(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Landroidx/media3/common/Format$Builder;->sampleMimeType:Ljava/lang/String;

    return-object p0
.end method

.method public setSampleRate(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 778
    iput p1, p0, Landroidx/media3/common/Format$Builder;->sampleRate:I

    return-object p0
.end method

.method public setSelectionFlags(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 423
    iput p1, p0, Landroidx/media3/common/Format$Builder;->selectionFlags:I

    return-object p0
.end method

.method public setStereoMode(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 728
    iput p1, p0, Landroidx/media3/common/Format$Builder;->stereoMode:I

    return-object p0
.end method

.method public setSubsampleOffsetUs(J)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 606
    iput-wide p1, p0, Landroidx/media3/common/Format$Builder;->subsampleOffsetUs:J

    return-object p0
.end method

.method public setTileCountHorizontal(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 855
    iput p1, p0, Landroidx/media3/common/Format$Builder;->tileCountHorizontal:I

    return-object p0
.end method

.method public setTileCountVertical(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 867
    iput p1, p0, Landroidx/media3/common/Format$Builder;->tileCountVertical:I

    return-object p0
.end method

.method public setWidth(I)Landroidx/media3/common/Format$Builder;
    .locals 0

    .line 632
    iput p1, p0, Landroidx/media3/common/Format$Builder;->width:I

    return-object p0
.end method
