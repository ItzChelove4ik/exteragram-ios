.class final Landroidx/media3/extractor/mp4/BoxParser$StsdData;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/extractor/mp4/BoxParser;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "StsdData"
.end annotation


# instance fields
.field public format:Landroidx/media3/common/Format;

.field public nalUnitLengthFieldLength:I

.field public requiredSampleTransformation:I

.field public final trackEncryptionBoxes:[Landroidx/media3/extractor/mp4/TrackEncryptionBox;


# direct methods
.method public constructor <init>(I)V
    .locals 0

    .line 2710
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2711
    new-array p1, p1, [Landroidx/media3/extractor/mp4/TrackEncryptionBox;

    iput-object p1, p0, Landroidx/media3/extractor/mp4/BoxParser$StsdData;->trackEncryptionBoxes:[Landroidx/media3/extractor/mp4/TrackEncryptionBox;

    const/4 p1, 0x0

    .line 2712
    iput p1, p0, Landroidx/media3/extractor/mp4/BoxParser$StsdData;->requiredSampleTransformation:I

    return-void
.end method
