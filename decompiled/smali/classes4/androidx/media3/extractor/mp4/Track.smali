.class public final Landroidx/media3/extractor/mp4/Track;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public final durationUs:J

.field public final editListDurations:[J

.field public final editListMediaTimes:[J

.field public final format:Landroidx/media3/common/Format;

.field public final id:I

.field public final mediaDurationUs:J

.field public final movieTimescale:J

.field public final nalUnitLengthFieldLength:I

.field private final sampleDescriptionEncryptionBoxes:[Landroidx/media3/extractor/mp4/TrackEncryptionBox;

.field public final sampleTransformation:I

.field public final timescale:J

.field public final type:I


# direct methods
.method public constructor <init>(IIJJJJLandroidx/media3/common/Format;I[Landroidx/media3/extractor/mp4/TrackEncryptionBox;I[J[J)V
    .locals 0

    .line 105
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 106
    iput p1, p0, Landroidx/media3/extractor/mp4/Track;->id:I

    .line 107
    iput p2, p0, Landroidx/media3/extractor/mp4/Track;->type:I

    .line 108
    iput-wide p3, p0, Landroidx/media3/extractor/mp4/Track;->timescale:J

    .line 109
    iput-wide p5, p0, Landroidx/media3/extractor/mp4/Track;->movieTimescale:J

    .line 110
    iput-wide p7, p0, Landroidx/media3/extractor/mp4/Track;->durationUs:J

    .line 111
    iput-wide p9, p0, Landroidx/media3/extractor/mp4/Track;->mediaDurationUs:J

    .line 112
    iput-object p11, p0, Landroidx/media3/extractor/mp4/Track;->format:Landroidx/media3/common/Format;

    .line 113
    iput p12, p0, Landroidx/media3/extractor/mp4/Track;->sampleTransformation:I

    .line 114
    iput-object p13, p0, Landroidx/media3/extractor/mp4/Track;->sampleDescriptionEncryptionBoxes:[Landroidx/media3/extractor/mp4/TrackEncryptionBox;

    .line 115
    iput p14, p0, Landroidx/media3/extractor/mp4/Track;->nalUnitLengthFieldLength:I

    .line 116
    iput-object p15, p0, Landroidx/media3/extractor/mp4/Track;->editListDurations:[J

    move-object/from16 p1, p16

    .line 117
    iput-object p1, p0, Landroidx/media3/extractor/mp4/Track;->editListMediaTimes:[J

    return-void
.end method


# virtual methods
.method public copyWithFormat(Landroidx/media3/common/Format;)Landroidx/media3/extractor/mp4/Track;
    .locals 17

    move-object/from16 v0, p0

    .line 135
    new-instance v1, Landroidx/media3/extractor/mp4/Track;

    move-object v2, v1

    iget v1, v0, Landroidx/media3/extractor/mp4/Track;->id:I

    move-object v3, v2

    iget v2, v0, Landroidx/media3/extractor/mp4/Track;->type:I

    move-object v5, v3

    iget-wide v3, v0, Landroidx/media3/extractor/mp4/Track;->timescale:J

    move-object v7, v5

    iget-wide v5, v0, Landroidx/media3/extractor/mp4/Track;->movieTimescale:J

    move-object v9, v7

    iget-wide v7, v0, Landroidx/media3/extractor/mp4/Track;->durationUs:J

    move-object v11, v9

    iget-wide v9, v0, Landroidx/media3/extractor/mp4/Track;->mediaDurationUs:J

    iget v12, v0, Landroidx/media3/extractor/mp4/Track;->sampleTransformation:I

    iget-object v13, v0, Landroidx/media3/extractor/mp4/Track;->sampleDescriptionEncryptionBoxes:[Landroidx/media3/extractor/mp4/TrackEncryptionBox;

    iget v14, v0, Landroidx/media3/extractor/mp4/Track;->nalUnitLengthFieldLength:I

    iget-object v15, v0, Landroidx/media3/extractor/mp4/Track;->editListDurations:[J

    iget-object v0, v0, Landroidx/media3/extractor/mp4/Track;->editListMediaTimes:[J

    move-object/from16 v16, v0

    move-object v0, v11

    move-object/from16 v11, p1

    invoke-direct/range {v0 .. v16}, Landroidx/media3/extractor/mp4/Track;-><init>(IIJJJJLandroidx/media3/common/Format;I[Landroidx/media3/extractor/mp4/TrackEncryptionBox;I[J[J)V

    return-object v0
.end method

.method public getSampleDescriptionEncryptionBox(I)Landroidx/media3/extractor/mp4/TrackEncryptionBox;
    .locals 0

    .line 129
    iget-object p0, p0, Landroidx/media3/extractor/mp4/Track;->sampleDescriptionEncryptionBoxes:[Landroidx/media3/extractor/mp4/TrackEncryptionBox;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 131
    :cond_0
    aget-object p0, p0, p1

    return-object p0
.end method
