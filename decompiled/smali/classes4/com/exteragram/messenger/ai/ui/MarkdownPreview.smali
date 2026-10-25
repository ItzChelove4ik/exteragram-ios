.class public abstract Lcom/exteragram/messenger/ai/ui/MarkdownPreview;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static format(Ljava/lang/String;)Ljava/lang/CharSequence;
    .locals 9

    .line 35
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 36
    const-string p0, ""

    return-object p0

    .line 39
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 41
    :try_start_0
    invoke-static {p0, v0}, Lorg/telegram/ui/Components/MarkdownParser;->parse(Ljava/lang/String;Ljava/util/ArrayList;)Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v1

    .line 43
    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 44
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 46
    :goto_0
    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_1

    .line 50
    :cond_1
    invoke-static {v0}, Lorg/telegram/ui/iv/RichMessageConvert;->blocksToCharSequence(Ljava/util/List;)Ljava/lang/CharSequence;

    move-result-object v0

    .line 51
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_2

    :goto_1
    return-object p0

    .line 55
    :cond_2
    instance-of p0, v0, Landroid/text/Spannable;

    const/4 v1, 0x0

    if-eqz p0, :cond_3

    move-object p0, v0

    check-cast p0, Landroid/text/Spannable;

    .line 56
    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result v2

    const-class v3, Lorg/telegram/messenger/CodeHighlighting$Span;

    invoke-interface {p0, v1, v2, v3}, Landroid/text/Spanned;->getSpans(IILjava/lang/Class;)[Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [Lorg/telegram/messenger/CodeHighlighting$Span;

    array-length v3, v2

    move v4, v1

    :goto_2
    if-ge v4, v3, :cond_3

    aget-object v5, v2, v4

    .line 57
    invoke-interface {p0, v5}, Landroid/text/Spanned;->getSpanStart(Ljava/lang/Object;)I

    move-result v6

    .line 58
    invoke-interface {p0, v5}, Landroid/text/Spanned;->getSpanEnd(Ljava/lang/Object;)I

    move-result v7

    .line 59
    invoke-interface {p0, v5}, Landroid/text/Spannable;->removeSpan(Ljava/lang/Object;)V

    .line 60
    new-instance v5, Landroid/text/style/TypefaceSpan;

    const-string v8, "monospace"

    invoke-direct {v5, v8}, Landroid/text/style/TypefaceSpan;-><init>(Ljava/lang/String;)V

    const/16 v8, 0x21

    invoke-interface {p0, v5, v6, v7, v8}, Landroid/text/Spannable;->setSpan(Ljava/lang/Object;III)V

    add-int/lit8 v4, v4, 0x1

    goto :goto_2

    .line 63
    :cond_3
    const-class p0, Lorg/telegram/ui/Components/QuoteSpan$QuoteStyleSpan;

    invoke-static {v0, p0}, Lorg/telegram/messenger/AndroidUtilities;->removeSpans(Ljava/lang/CharSequence;Ljava/lang/Class;)Ljava/lang/CharSequence;

    .line 64
    const-class p0, Lorg/telegram/ui/Components/QuoteSpan;

    invoke-static {v0, p0}, Lorg/telegram/messenger/AndroidUtilities;->removeSpans(Ljava/lang/CharSequence;Ljava/lang/Class;)Ljava/lang/CharSequence;

    .line 66
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result p0

    :goto_3
    if-lez p0, :cond_4

    add-int/lit8 v2, p0, -0x1

    .line 67
    invoke-interface {v0, v2}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v2

    invoke-static {v2}, Ljava/lang/Character;->isWhitespace(C)Z

    move-result v2

    if-eqz v2, :cond_4

    add-int/lit8 p0, p0, -0x1

    goto :goto_3

    .line 70
    :cond_4
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-ne p0, v2, :cond_5

    goto :goto_4

    :cond_5
    invoke-interface {v0, v1, p0}, Ljava/lang/CharSequence;->subSequence(II)Ljava/lang/CharSequence;

    move-result-object v0

    :goto_4
    return-object v0
.end method
