.class final Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/extractor/mp4/BoxParser$SampleSizeBox;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/extractor/mp4/BoxParser;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Stz2SampleSizeBox"
.end annotation


# instance fields
.field private currentByte:I

.field private final data:Landroidx/media3/common/util/ParsableByteArray;

.field private final fieldSize:I

.field private final sampleCount:I

.field private sampleIndex:I


# direct methods
.method public constructor <init>(Landroidx/media3/container/Mp4Box$LeafBox;)V
    .locals 1

    .line 2866
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2867
    iget-object p1, p1, Landroidx/media3/container/Mp4Box$LeafBox;->data:Landroidx/media3/common/util/ParsableByteArray;

    iput-object p1, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->data:Landroidx/media3/common/util/ParsableByteArray;

    const/16 v0, 0xc

    .line 2868
    invoke-virtual {p1, v0}, Landroidx/media3/common/util/ParsableByteArray;->setPosition(I)V

    .line 2869
    invoke-virtual {p1}, Landroidx/media3/common/util/ParsableByteArray;->readUnsignedIntToInt()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    iput v0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->fieldSize:I

    .line 2870
    invoke-virtual {p1}, Landroidx/media3/common/util/ParsableByteArray;->readUnsignedIntToInt()I

    move-result p1

    iput p1, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->sampleCount:I

    return-void
.end method


# virtual methods
.method public getFixedSampleSize()I
    .locals 0

    const/4 p0, -0x1

    return p0
.end method

.method public getSampleCount()I
    .locals 0

    .line 2875
    iget p0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->sampleCount:I

    return p0
.end method

.method public readNextSampleSize()I
    .locals 2

    .line 2885
    iget v0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->fieldSize:I

    const/16 v1, 0x8

    if-ne v0, v1, :cond_0

    .line 2886
    iget-object p0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->data:Landroidx/media3/common/util/ParsableByteArray;

    invoke-virtual {p0}, Landroidx/media3/common/util/ParsableByteArray;->readUnsignedByte()I

    move-result p0

    return p0

    :cond_0
    const/16 v1, 0x10

    if-ne v0, v1, :cond_1

    .line 2888
    iget-object p0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->data:Landroidx/media3/common/util/ParsableByteArray;

    invoke-virtual {p0}, Landroidx/media3/common/util/ParsableByteArray;->readUnsignedShort()I

    move-result p0

    return p0

    .line 2891
    :cond_1
    iget v0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->sampleIndex:I

    add-int/lit8 v1, v0, 0x1

    iput v1, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->sampleIndex:I

    rem-int/lit8 v0, v0, 0x2

    if-nez v0, :cond_2

    .line 2893
    iget-object v0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->data:Landroidx/media3/common/util/ParsableByteArray;

    invoke-virtual {v0}, Landroidx/media3/common/util/ParsableByteArray;->readUnsignedByte()I

    move-result v0

    iput v0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->currentByte:I

    and-int/lit16 p0, v0, 0xf0

    shr-int/lit8 p0, p0, 0x4

    return p0

    .line 2898
    :cond_2
    iget p0, p0, Landroidx/media3/extractor/mp4/BoxParser$Stz2SampleSizeBox;->currentByte:I

    and-int/lit8 p0, p0, 0xf

    return p0
.end method
