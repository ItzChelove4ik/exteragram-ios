.class final Landroidx/media3/exoplayer/text/DelegatingSubtitleDecoder;
.super Landroidx/media3/extractor/text/SimpleSubtitleDecoder;
.source "SourceFile"


# instance fields
.field private final subtitleParser:Landroidx/media3/extractor/text/SubtitleParser;


# direct methods
.method public constructor <init>(Ljava/lang/String;Landroidx/media3/extractor/text/SubtitleParser;)V
    .locals 0

    .line 51
    invoke-direct {p0, p1}, Landroidx/media3/extractor/text/SimpleSubtitleDecoder;-><init>(Ljava/lang/String;)V

    .line 52
    iput-object p2, p0, Landroidx/media3/exoplayer/text/DelegatingSubtitleDecoder;->subtitleParser:Landroidx/media3/extractor/text/SubtitleParser;

    return-void
.end method


# virtual methods
.method public decode([BIZ)Landroidx/media3/extractor/text/Subtitle;
    .locals 0

    if-eqz p3, :cond_0

    .line 58
    iget-object p3, p0, Landroidx/media3/exoplayer/text/DelegatingSubtitleDecoder;->subtitleParser:Landroidx/media3/extractor/text/SubtitleParser;

    invoke-interface {p3}, Landroidx/media3/extractor/text/SubtitleParser;->reset()V

    .line 60
    :cond_0
    iget-object p0, p0, Landroidx/media3/exoplayer/text/DelegatingSubtitleDecoder;->subtitleParser:Landroidx/media3/extractor/text/SubtitleParser;

    const/4 p3, 0x0

    invoke-interface {p0, p1, p3, p2}, Landroidx/media3/extractor/text/SubtitleParser;->parseToLegacySubtitle([BII)Landroidx/media3/extractor/text/Subtitle;

    move-result-object p0

    return-object p0
.end method
