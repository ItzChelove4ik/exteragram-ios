.class final Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/MarkdownUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "HighlightSource"
.end annotation


# instance fields
.field private highlighted:Landroid/text/SpannableString;

.field final language:Ljava/lang/String;

.field final scrollGroup:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

.field final text:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    .line 276
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 273
    new-instance v0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    invoke-direct {v0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->scrollGroup:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    .line 277
    iput-object p1, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->text:Ljava/lang/String;

    .line 278
    iput-object p2, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->language:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public getHighlighted()Landroid/text/SpannableString;
    .locals 2

    .line 282
    iget-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->highlighted:Landroid/text/SpannableString;

    if-nez v0, :cond_0

    .line 283
    iget-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->text:Ljava/lang/String;

    iget-object v1, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->language:Ljava/lang/String;

    invoke-static {v0, v1}, Lorg/telegram/messenger/CodeHighlighting;->getHighlighted(Ljava/lang/CharSequence;Ljava/lang/String;)Landroid/text/SpannableString;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->highlighted:Landroid/text/SpannableString;

    .line 285
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/utils/MarkdownUtils$HighlightSource;->highlighted:Landroid/text/SpannableString;

    return-object p0
.end method
