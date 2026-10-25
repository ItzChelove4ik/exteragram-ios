.class public final synthetic Landroidx/media3/exoplayer/upstream/SlidingPercentile$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Comparator;


# direct methods
.method public synthetic constructor <init>()V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 0

    .line 0
    check-cast p1, Landroidx/media3/exoplayer/upstream/SlidingPercentile$Sample;

    check-cast p2, Landroidx/media3/exoplayer/upstream/SlidingPercentile$Sample;

    invoke-static {p1, p2}, Landroidx/media3/exoplayer/upstream/SlidingPercentile;->$r8$lambda$xVrpz1k64v1GGT5OvX-ezvbtrRg(Landroidx/media3/exoplayer/upstream/SlidingPercentile$Sample;Landroidx/media3/exoplayer/upstream/SlidingPercentile$Sample;)I

    move-result p0

    return p0
.end method
