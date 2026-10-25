.class final Landroidx/media3/extractor/mp4/BoxParser$VexuData;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/extractor/mp4/BoxParser;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "VexuData"
.end annotation


# instance fields
.field private final eyesData:Landroidx/media3/extractor/mp4/BoxParser$EyesData;


# direct methods
.method public constructor <init>(Landroidx/media3/extractor/mp4/BoxParser$EyesData;)V
    .locals 0

    .line 2785
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2786
    iput-object p1, p0, Landroidx/media3/extractor/mp4/BoxParser$VexuData;->eyesData:Landroidx/media3/extractor/mp4/BoxParser$EyesData;

    return-void
.end method

.method public static synthetic access$1000(Landroidx/media3/extractor/mp4/BoxParser$VexuData;)Landroidx/media3/extractor/mp4/BoxParser$EyesData;
    .locals 0

    .line 2782
    iget-object p0, p0, Landroidx/media3/extractor/mp4/BoxParser$VexuData;->eyesData:Landroidx/media3/extractor/mp4/BoxParser$EyesData;

    return-object p0
.end method


# virtual methods
.method public hasBothEyeViews()Z
    .locals 1

    .line 2790
    iget-object v0, p0, Landroidx/media3/extractor/mp4/BoxParser$VexuData;->eyesData:Landroidx/media3/extractor/mp4/BoxParser$EyesData;

    if-eqz v0, :cond_0

    .line 2791
    invoke-static {v0}, Landroidx/media3/extractor/mp4/BoxParser$EyesData;->access$1100(Landroidx/media3/extractor/mp4/BoxParser$EyesData;)Landroidx/media3/extractor/mp4/BoxParser$StriData;

    move-result-object v0

    invoke-static {v0}, Landroidx/media3/extractor/mp4/BoxParser$StriData;->access$1800(Landroidx/media3/extractor/mp4/BoxParser$StriData;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Landroidx/media3/extractor/mp4/BoxParser$VexuData;->eyesData:Landroidx/media3/extractor/mp4/BoxParser$EyesData;

    .line 2792
    invoke-static {p0}, Landroidx/media3/extractor/mp4/BoxParser$EyesData;->access$1100(Landroidx/media3/extractor/mp4/BoxParser$EyesData;)Landroidx/media3/extractor/mp4/BoxParser$StriData;

    move-result-object p0

    invoke-static {p0}, Landroidx/media3/extractor/mp4/BoxParser$StriData;->access$1900(Landroidx/media3/extractor/mp4/BoxParser$StriData;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
