.class public final Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;
.super Lorg/telegram/ui/Components/UItem$UItemFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Factory"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lorg/telegram/ui/Components/UItem$UItemFactory<",
        "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0018\u0000 \u001e2\u0008\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J4\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0008\u0010\u0008\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000c\u001a\u00020\u000b2\u0008\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096\u0002J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J4\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00102\u0008\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0008\u0010\u0008\u001a\u0004\u0018\u00010\u001dH\u0016\u00a8\u0006\u001f"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;",
        "Lorg/telegram/ui/Components/UItem$UItemFactory;",
        "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;",
        "<init>",
        "()V",
        "createView",
        "context",
        "Landroid/content/Context;",
        "listView",
        "Lorg/telegram/ui/Components/RecyclerListView;",
        "currentAccount",
        "",
        "classGuid",
        "resourcesProvider",
        "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;",
        "equals",
        "",
        "a",
        "Lorg/telegram/ui/Components/UItem;",
        "b",
        "contentsEquals",
        "bindView",
        "",
        "view",
        "Landroid/view/View;",
        "item",
        "divider",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "Lorg/telegram/ui/Components/UniversalRecyclerView;",
        "Companion",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;


# direct methods
.method public static $r8$lambda$dS-hbUNjaZCMtCzJXPQ2TkFICTA(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;ILandroid/graphics/drawable/Drawable;)V
    .locals 1

    .line 351
    invoke-static {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;->access$getBoundResId$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;)I

    move-result v0

    if-ne v0, p1, :cond_0

    .line 352
    invoke-virtual {p0}, Lorg/telegram/ui/Cells/TextCell;->getValueImageView()Landroid/widget/ImageView;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    :cond_0
    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;->Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;

    .line 365
    new-instance v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;

    invoke-direct {v0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;-><init>()V

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem$UItemFactory;->setup(Lorg/telegram/ui/Components/UItem$UItemFactory;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 293
    invoke-direct {p0}, Lorg/telegram/ui/Components/UItem$UItemFactory;-><init>()V

    return-void
.end method

.method public static final asIcon(ILjava/lang/CharSequence;Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;->Companion:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;

    invoke-virtual {v0, p0, p1, p2}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;->asIcon(ILjava/lang/CharSequence;Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public bindView(Landroid/view/View;Lorg/telegram/ui/Components/UItem;ZLorg/telegram/ui/Components/UniversalAdapter;Lorg/telegram/ui/Components/UniversalRecyclerView;)V
    .locals 2

    .line 325
    check-cast p1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;

    .line 326
    invoke-virtual {p1}, Lorg/telegram/ui/Cells/TextCell;->reset()V

    .line 327
    iget p0, p2, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-static {p1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;->access$setBoundResId$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;I)V

    .line 329
    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 330
    instance-of p4, p0, Lcom/exteragram/messenger/icons/ExteraResources;

    .line 333
    iget p5, p2, Lorg/telegram/ui/Components/UItem;->id:I

    if-eqz p4, :cond_0

    .line 331
    check-cast p0, Lcom/exteragram/messenger/icons/ExteraResources;

    invoke-virtual {p0, p5}, Lcom/exteragram/messenger/icons/ExteraResources;->getOriginalDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    goto :goto_0

    .line 333
    :cond_0
    invoke-virtual {p0, p5}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    .line 336
    :goto_0
    iget-object p4, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p5, p4, Lcom/exteragram/messenger/icons/IconPack;

    const/4 v0, 0x0

    if-eqz p5, :cond_1

    check-cast p4, Lcom/exteragram/messenger/icons/IconPack;

    goto :goto_1

    :cond_1
    move-object p4, v0

    :goto_1
    if-eqz p4, :cond_2

    .line 338
    sget-object p5, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    iget v1, p2, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-virtual {p5, p4, v1}, Lcom/exteragram/messenger/icons/IconManager;->getCachedPackIconDrawable(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;

    move-result-object p5

    goto :goto_2

    .line 340
    :cond_2
    sget-object p5, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    iget v1, p2, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-virtual {p5, v1}, Lcom/exteragram/messenger/icons/IconManager;->getCachedDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p5

    .line 343
    :goto_2
    iget-object v1, p2, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    :cond_3
    invoke-virtual {p1, v1, v0, p5, p3}, Lorg/telegram/ui/Cells/TextCell;->setTextAndIconAndValueDrawable(Ljava/lang/CharSequence;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Z)V

    const/4 p0, 0x1

    .line 344
    invoke-virtual {p1, p0}, Lorg/telegram/ui/Cells/TextCell;->setIsIcon(Z)V

    .line 345
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayIcon:I

    sget p3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p1, p0, p3}, Lorg/telegram/ui/Cells/TextCell;->setColors(II)V

    const/16 p0, 0x44

    .line 346
    invoke-virtual {p1, p0}, Lorg/telegram/ui/Cells/TextCell;->setOffsetFromImage(I)V

    if-nez p5, :cond_5

    .line 349
    iget p0, p2, Lorg/telegram/ui/Components/UItem;->id:I

    .line 350
    new-instance p2, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$$ExternalSyntheticLambda0;

    invoke-direct {p2, p1, p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;I)V

    if-eqz p4, :cond_4

    .line 356
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p1, p4, p0, p2}, Lcom/exteragram/messenger/icons/IconManager;->requestPackIconDrawable(Lcom/exteragram/messenger/icons/IconPack;ILorg/telegram/messenger/Utilities$Callback;)V

    return-void

    .line 358
    :cond_4
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p1, p0, p2}, Lcom/exteragram/messenger/icons/IconManager;->requestDrawable(ILorg/telegram/messenger/Utilities$Callback;)V

    :cond_5
    return-void
.end method

.method public contentsEquals(Lorg/telegram/ui/Components/UItem;Lorg/telegram/ui/Components/UItem;)Z
    .locals 3

    .line 306
    iget p0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    iget v0, p2, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 v1, 0x0

    if-eq p0, v0, :cond_0

    return v1

    .line 307
    :cond_0
    iget-object p0, p1, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    iget-object v0, p2, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    invoke-static {p0, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_1

    return v1

    .line 309
    :cond_1
    iget-object p0, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v0, p0, Lcom/exteragram/messenger/icons/IconPack;

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    check-cast p0, Lcom/exteragram/messenger/icons/IconPack;

    goto :goto_0

    :cond_2
    move-object p0, v2

    .line 310
    :goto_0
    iget-object p2, p2, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of v0, p2, Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v0, :cond_3

    move-object v2, p2

    check-cast v2, Lcom/exteragram/messenger/icons/IconPack;

    :cond_3
    if-ne p0, v2, :cond_4

    const/4 p0, 0x1

    return p0

    :cond_4
    if-eqz p0, :cond_6

    if-nez v2, :cond_5

    goto :goto_1

    .line 314
    :cond_5
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 315
    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {v2}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object p2

    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0

    :cond_6
    :goto_1
    return v1
.end method

.method public bridge synthetic createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Landroid/view/View;
    .locals 0

    .line 293
    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;->createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;

    move-result-object p0

    return-object p0
.end method

.method public createView(Landroid/content/Context;Lorg/telegram/ui/Components/RecyclerListView;IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;
    .locals 0

    .line 301
    new-instance p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;

    invoke-direct {p0, p1, p5}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-object p0
.end method

.method public equals(Lorg/telegram/ui/Components/UItem;Lorg/telegram/ui/Components/UItem;)Z
    .locals 0

    .line 303
    iget p0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    iget p1, p2, Lorg/telegram/ui/Components/UItem;->id:I

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
