.class public Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell$Factory;
.super Lorg/telegram/ui/Components/UItem$UItemFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Factory"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lorg/telegram/ui/Components/UItem$UItemFactory<",
        "Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;",
        ">;"
    }
.end annotation


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 108
    new-instance v0, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell$Factory;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell$Factory;-><init>()V

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem$UItemFactory;->setup(Lorg/telegram/ui/Components/UItem$UItemFactory;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 106
    invoke-direct {p0}, Lorg/telegram/ui/Components/UItem$UItemFactory;-><init>()V

    return-void
.end method

.method public static asHistoryCell(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;Z)Lorg/telegram/ui/Components/UItem;
    .locals 1

    .line 139
    const-class v0, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell$Factory;

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->ofFactory(Ljava/lang/Class;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    .line 140
    iput p0, v0, Lorg/telegram/ui/Components/UItem;->id:I

    .line 141
    iput-object p1, v0, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    .line 142
    iput-object p2, v0, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    .line 143
    iput-boolean p3, v0, Lorg/telegram/ui/Components/UItem;->checked:Z

    return-object v0
.end method


# virtual methods
.method public bindView(Landroid/view/View;Lorg/telegram/ui/Components/UItem;ZLorg/telegram/ui/Components/UniversalAdapter;Lorg/telegram/ui/Components/UniversalRecyclerView;)V
    .locals 0

    .line 118
    instance-of p0, p1, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;

    if-eqz p0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;

    iget-object p0, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p4, p0, Lcom/exteragram/messenger/ai/data/Message;

    if-eqz p4, :cond_0

    check-cast p0, Lcom/exteragram/messenger/ai/data/Message;

    .line 119
    iget-object p4, p2, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    iget-boolean p2, p2, Lorg/telegram/ui/Components/UItem;->checked:Z

    invoke-virtual {p1, p0, p4, p2, p3}, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;->set(Lcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;ZZ)V

    :cond_0
    return-void
.end method

.method public contentsEquals(Lorg/telegram/ui/Components/UItem;Lorg/telegram/ui/Components/UItem;)Z
    .locals 4

    .line 130
    iget-object p0, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v0, p0, Lcom/exteragram/messenger/ai/data/Message;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    check-cast p0, Lcom/exteragram/messenger/ai/data/Message;

    iget-object v0, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v2, v0, Lcom/exteragram/messenger/ai/data/Message;

    if-eqz v2, :cond_0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Message;

    .line 133
    iget-boolean v2, p1, Lorg/telegram/ui/Components/UItem;->checked:Z

    iget-boolean v3, p2, Lorg/telegram/ui/Components/UItem;->checked:Z

    if-ne v2, v3, :cond_0

    .line 134
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    iget-object p0, p1, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    iget-object p1, p2, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    .line 135
    invoke-static {p0, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    return v1
.end method

.method public bridge synthetic createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Landroid/view/View;
    .locals 0

    .line 106
    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell$Factory;->createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;

    move-result-object p0

    return-object p0
.end method

.method public createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;
    .locals 0

    .line 113
    new-instance p0, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;

    invoke-direct {p0, p1, p5}, Lcom/exteragram/messenger/ai/ui/components/HistoryMessageCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-object p0
.end method

.method public equals(Lorg/telegram/ui/Components/UItem;Lorg/telegram/ui/Components/UItem;)Z
    .locals 0

    .line 125
    iget p0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    iget p1, p2, Lorg/telegram/ui/Components/UItem;->id:I

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
