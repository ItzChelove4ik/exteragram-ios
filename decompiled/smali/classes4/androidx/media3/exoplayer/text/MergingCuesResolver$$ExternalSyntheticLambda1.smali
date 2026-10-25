.class public final synthetic Landroidx/media3/exoplayer/text/MergingCuesResolver$$ExternalSyntheticLambda1;
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
    check-cast p1, Landroidx/media3/extractor/text/CuesWithTiming;

    invoke-static {p1}, Landroidx/media3/exoplayer/text/MergingCuesResolver;->$r8$lambda$IYQ6NFPgGvIp5yy_PJ2dwIRwFAk(Landroidx/media3/extractor/text/CuesWithTiming;)Ljava/lang/Long;

    move-result-object p0

    return-object p0
.end method
