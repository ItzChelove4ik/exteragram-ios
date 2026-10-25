.class public final Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;
.super Lorg/telegram/tgnet/tl/TL_iv$pageBlockPreformatted;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/MarkdownUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PreformattedChunk"
.end annotation


# instance fields
.field private final end:I

.field private highlightedText:Ljava/lang/CharSequence;

.field public final joinsNext:Z

.field public final joinsPrevious:Z

.field private final plainText:Ljava/lang/String;

.field public final scrollGroup:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

.field private final source:Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;

.field private final start:I


# direct methods
.method private constructor <init>(Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;II)V
    .locals 3

    .line 245
    invoke-direct {p0}, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPreformatted;-><init>()V

    .line 246
    iput-object p1, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->source:Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;

    .line 247
    iput p2, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->start:I

    .line 248
    iput p3, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->end:I

    .line 249
    iget-object v0, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->text:Ljava/lang/String;

    invoke-virtual {v0, p2, p3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->plainText:Ljava/lang/String;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-lez p2, :cond_0

    move p2, v2

    goto :goto_0

    :cond_0
    move p2, v1

    .line 250
    :goto_0
    iput-boolean p2, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->joinsPrevious:Z

    .line 251
    iget-object p2, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->text:Ljava/lang/String;

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    if-ge p3, p2, :cond_1

    move v1, v2

    :cond_1
    iput-boolean v1, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->joinsNext:Z

    .line 252
    iget-object p2, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->scrollGroup:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    iput-object p2, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->scrollGroup:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    .line 253
    invoke-static {v0}, Lcom/exteragram/messenger/utils/MarkdownUtils;->-$$Nest$smplain(Ljava/lang/String;)Lorg/telegram/tgnet/tl/TL_iv$RichText;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/tgnet/tl/TL_iv$PageBlock;->text:Lorg/telegram/tgnet/tl/TL_iv$RichText;

    .line 254
    iget-object p1, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->language:Ljava/lang/String;

    iput-object p1, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPreformatted;->language:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;IILcom/exteragram/messenger/utils/MarkdownUtils-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;-><init>(Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;II)V

    return-void
.end method


# virtual methods
.method public getHighlightedText()Ljava/lang/CharSequence;
    .locals 3

    .line 258
    iget-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->highlightedText:Ljava/lang/CharSequence;

    if-nez v0, :cond_1

    .line 259
    iget-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->source:Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;

    invoke-virtual {v0}, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->getHighlighted()Landroid/text/SpannableString;

    move-result-object v0

    .line 260
    instance-of v1, v0, Lorg/telegram/messenger/CodeHighlighting$LockedSpannableString;

    if-eqz v1, :cond_0

    move-object v1, v0

    check-cast v1, Lorg/telegram/messenger/CodeHighlighting$LockedSpannableString;

    iget-boolean v1, v1, Lorg/telegram/messenger/CodeHighlighting$LockedSpannableString;->ready:Z

    if-nez v1, :cond_0

    .line 261
    iget-object p0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->plainText:Ljava/lang/String;

    return-object p0

    .line 263
    :cond_0
    iget v1, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->start:I

    iget v2, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->end:I

    invoke-virtual {v0, v1, v2}, Landroid/text/SpannableString;->subSequence(II)Ljava/lang/CharSequence;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->highlightedText:Ljava/lang/CharSequence;

    .line 265
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->highlightedText:Ljava/lang/CharSequence;

    return-object p0
.end method
