.class public final synthetic Landroidx/media3/exoplayer/source/MergingMediaPeriod$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/common/base/Function;


# direct methods
.method public synthetic constructor <init>()V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    check-cast p1, Landroidx/media3/exoplayer/source/MediaPeriod;

    invoke-static {p1}, Landroidx/media3/exoplayer/source/MergingMediaPeriod;->$r8$lambda$_OSX7kD5MuhqDmCs7DjaFeCeIDA(Landroidx/media3/exoplayer/source/MediaPeriod;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method
