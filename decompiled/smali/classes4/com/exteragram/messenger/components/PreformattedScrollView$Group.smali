.class public final Lcom/exteragram/messenger/components/PreformattedScrollView$Group;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/components/PreformattedScrollView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Group"
.end annotation


# instance fields
.field private contentWidth:I

.field private driver:Lcom/exteragram/messenger/components/PreformattedScrollView;

.field private scrollX:I

.field private final views:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/components/PreformattedScrollView;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static bridge synthetic -$$Nest$fgetcontentWidth(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->contentWidth:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetdriver(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)Lcom/exteragram/messenger/components/PreformattedScrollView;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->driver:Lcom/exteragram/messenger/components/PreformattedScrollView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetscrollX(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)I
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->scrollX:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fputdriver(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->driver:Lcom/exteragram/messenger/components/PreformattedScrollView;

    return-void
.end method

.method public static bridge synthetic -$$Nest$madd(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->add(Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$madjustContentWidth(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;I)I
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->adjustContentWidth(Lcom/exteragram/messenger/components/PreformattedScrollView;I)I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mremove(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->remove(Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mscrollTo(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->scrollTo(Lcom/exteragram/messenger/components/PreformattedScrollView;I)V

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 107
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 109
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    return-void
.end method

.method private add(Lcom/exteragram/messenger/components/PreformattedScrollView;)V
    .locals 1

    .line 115
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 116
    iget-object p0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method private adjustContentWidth(Lcom/exteragram/messenger/components/PreformattedScrollView;I)I
    .locals 3

    .line 138
    iget v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->contentWidth:I

    if-le p2, v0, :cond_1

    .line 139
    iput p2, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->contentWidth:I

    const/4 p2, 0x0

    move v0, p2

    .line 140
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v0, v1, :cond_1

    .line 141
    iget-object v1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/components/PreformattedScrollView;

    if-eq v1, p1, :cond_0

    .line 142
    invoke-virtual {v1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    if-lez v2, :cond_0

    .line 143
    invoke-virtual {v1, p2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 144
    invoke-static {v1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v2, Lcom/exteragram/messenger/components/PreformattedScrollView$$ExternalSyntheticLambda0;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/components/PreformattedScrollView$$ExternalSyntheticLambda0;-><init>(Landroid/view/View;)V

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 148
    :cond_1
    iget p0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->contentWidth:I

    return p0
.end method

.method private remove(Lcom/exteragram/messenger/components/PreformattedScrollView;)V
    .locals 1

    .line 121
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 122
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->driver:Lcom/exteragram/messenger/components/PreformattedScrollView;

    if-ne v0, p1, :cond_0

    const/4 p1, 0x0

    .line 123
    iput-object p1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->driver:Lcom/exteragram/messenger/components/PreformattedScrollView;

    :cond_0
    return-void
.end method

.method private scrollTo(Lcom/exteragram/messenger/components/PreformattedScrollView;I)V
    .locals 3

    .line 128
    iput p2, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->scrollX:I

    const/4 v0, 0x0

    .line 129
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v0, v1, :cond_1

    .line 130
    iget-object v1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->views:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/components/PreformattedScrollView;

    if-eq v1, p1, :cond_0

    .line 132
    invoke-virtual {v1}, Landroid/view/View;->getScrollY()I

    move-result v2

    invoke-virtual {v1, p2, v2}, Landroid/view/View;->scrollTo(II)V

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method
