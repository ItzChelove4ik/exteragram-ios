.class public interface abstract Landroidx/media3/extractor/ts/TsPayloadReader;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/extractor/ts/TsPayloadReader$TrackIdGenerator;,
        Landroidx/media3/extractor/ts/TsPayloadReader$DvbSubtitleInfo;,
        Landroidx/media3/extractor/ts/TsPayloadReader$EsInfo;,
        Landroidx/media3/extractor/ts/TsPayloadReader$Factory;
    }
.end annotation


# virtual methods
.method public abstract consume(Landroidx/media3/common/util/ParsableByteArray;I)V
.end method

.method public abstract init(Landroidx/media3/common/util/TimestampAdjuster;Landroidx/media3/extractor/ExtractorOutput;Landroidx/media3/extractor/ts/TsPayloadReader$TrackIdGenerator;)V
.end method

.method public abstract seek()V
.end method
