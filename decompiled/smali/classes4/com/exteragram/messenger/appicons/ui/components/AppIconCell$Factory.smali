.class public Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;
.super Lorg/telegram/ui/Components/UItem$UItemFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Factory"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lorg/telegram/ui/Components/UItem$UItemFactory<",
        "Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;",
        ">;"
    }
.end annotation


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 173
    new-instance v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;

    invoke-direct {v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;-><init>()V

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem$UItemFactory;->setup(Lorg/telegram/ui/Components/UItem$UItemFactory;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 171
    invoke-direct {p0}, Lorg/telegram/ui/Components/UItem$UItemFactory;-><init>()V

    return-void
.end method

.method public static asAppIcon(Lcom/exteragram/messenger/appicons/AppIcon;Z)Lorg/telegram/ui/Components/UItem;
    .locals 1

    .line 199
    const-class v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->ofFactory(Ljava/lang/Class;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    .line 200
    iput-object p0, v0, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    .line 201
    iput-boolean p1, v0, Lorg/telegram/ui/Components/UItem;->checked:Z

    const/4 p0, 0x1

    .line 202
    iput p0, v0, Lorg/telegram/ui/Components/UItem;->spanCount:I

    .line 203
    iput-boolean p0, v0, Lorg/telegram/ui/Components/UItem;->transparent:Z

    return-object v0
.end method


# virtual methods
.method public bindView(Landroid/view/View;Lorg/telegram/ui/Components/UItem;ZLorg/telegram/ui/Components/UniversalAdapter;Lorg/telegram/ui/Components/UniversalRecyclerView;)V
    .locals 0

    .line 183
    instance-of p0, p1, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;

    if-eqz p0, :cond_1

    check-cast p1, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;

    iget-object p0, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p3, p0, Lcom/exteragram/messenger/appicons/AppIcon;

    if-eqz p3, :cond_1

    check-cast p0, Lcom/exteragram/messenger/appicons/AppIcon;

    .line 184
    iget-boolean p2, p2, Lorg/telegram/ui/Components/UItem;->checked:Z

    invoke-virtual {p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->getIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object p3

    if-ne p3, p0, :cond_0

    const/4 p3, 0x1

    goto :goto_0

    :cond_0
    const/4 p3, 0x0

    :goto_0
    invoke-virtual {p1, p0, p2, p3}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;->set(Lcom/exteragram/messenger/appicons/AppIcon;ZZ)V

    :cond_1
    return-void
.end method

.method public contentsEquals(Lorg/telegram/ui/Components/UItem;Lorg/telegram/ui/Components/UItem;)Z
    .locals 1

    .line 195
    iget-object p0, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    iget-object v0, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    if-ne p0, v0, :cond_0

    iget-boolean p0, p1, Lorg/telegram/ui/Components/UItem;->checked:Z

    iget-boolean p1, p2, Lorg/telegram/ui/Components/UItem;->checked:Z

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public bridge synthetic createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Landroid/view/View;
    .locals 0

    .line 171
    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell$Factory;->createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;

    move-result-object p0

    return-object p0
.end method

.method public createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;
    .locals 0

    .line 178
    new-instance p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;

    invoke-direct {p0, p1, p5}, Lcom/exteragram/messenger/appicons/ui/components/AppIconCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-object p0
.end method

.method public equals(Lorg/telegram/ui/Components/UItem;Lorg/telegram/ui/Components/UItem;)Z
    .locals 0

    .line 190
    iget-object p0, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    iget-object p1, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
