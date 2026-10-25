.class public abstract Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.super Lorg/telegram/ui/ActionBar/BaseFragment;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00be\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\t\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0015\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0012\n\u0002\u0010\u0010\n\u0002\u0008\u0015\u0008&\u0018\u00002\u00020\u0001:\u0002\u0080\u0001B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0014J\u0008\u0010.\u001a\u00020/H\u0016J\u0008\u00100\u001a\u00020/H\u0002J\u0008\u00101\u001a\u00020/H\u0002J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\'H\u0002J\u0010\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020)H\u0002J\u0008\u00106\u001a\u00020/H\u0016J\u0008\u00107\u001a\u00020/H\u0016J\u0008\u00108\u001a\u000209H&J\u0008\u0010:\u001a\u00020/H\u0014J\u000e\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020=J=\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u000e\u0010A\u001a\n\u0012\u0006\u0008\u0001\u0012\u00020C0B2\u0006\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u00020GH\u0004\u00a2\u0006\u0002\u0010HJ[\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u000e\u0010A\u001a\n\u0012\u0006\u0008\u0001\u0012\u00020C0B2\u0008\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u00020G2\u0008\u0008\u0002\u0010K\u001a\u00020\'2\u0008\u0008\u0002\u0010L\u001a\u00020\'H\u0005\u00a2\u0006\u0002\u0010MJ\u0008\u0010N\u001a\u00020/H\u0004J#\u0010O\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u000c\u0010P\u001a\u0008\u0012\u0004\u0012\u00020\'0QH\u0000\u00a2\u0006\u0002\u0008RJ\u0008\u0010S\u001a\u00020\'H\u0014J\u0008\u0010T\u001a\u00020\'H\u0014J\u0008\u0010U\u001a\u00020\'H\u0016J\u001e\u0010V\u001a\u00020/2\u000c\u0010W\u001a\u0008\u0012\u0004\u0012\u00020@0!2\u0006\u0010X\u001a\u00020YH$J0\u0010Z\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u0006\u0010[\u001a\u00020+2\u0006\u0010\\\u001a\u00020=2\u0006\u0010]\u001a\u00020)2\u0006\u0010^\u001a\u00020)H\u0014J0\u0010_\u001a\u00020\'2\u0006\u0010?\u001a\u00020@2\u0006\u0010[\u001a\u00020+2\u0006\u0010\\\u001a\u00020=2\u0006\u0010]\u001a\u00020)2\u0006\u0010^\u001a\u00020)H\u0014J\u0018\u0010`\u001a\u00020/2\u0006\u0010[\u001a\u00020+2\u0006\u0010a\u001a\u000209H\u0004J\u0008\u0010b\u001a\u00020\'H\u0017J(\u0010c\u001a\u00020/2\u0006\u0010d\u001a\u00020=2\u0006\u0010e\u001a\u00020=2\u0006\u0010f\u001a\u00020=2\u0006\u0010g\u001a\u00020=H\u0016J\u0010\u0010h\u001a\u00020=2\u0006\u0010i\u001a\u00020=H\u0014Ji\u0010j\u001a\u00020@2\n\u0010k\u001a\u0006\u0012\u0002\u0008\u00030l2\u0006\u0010m\u001a\u00020C2\u0006\u0010n\u001a\u00020\'2\n\u0008\u0002\u0010o\u001a\u0004\u0018\u00010C2\u0008\u0008\u0002\u0010p\u001a\u00020=2\u0008\u0008\u0002\u0010q\u001a\u00020\'2\n\u0008\u0002\u0010r\u001a\u0004\u0018\u0001092\u0008\u0008\u0002\u0010s\u001a\u00020\'2\u0008\u0008\u0002\u0010t\u001a\u00020=H\u0000\u00a2\u0006\u0002\u0008uJe\u0010j\u001a\u00020@2\u0006\u0010v\u001a\u00020=2\u0006\u0010m\u001a\u00020C2\u0006\u0010n\u001a\u00020\'2\n\u0008\u0002\u0010o\u001a\u0004\u0018\u00010C2\u0008\u0008\u0002\u0010p\u001a\u00020=2\u0008\u0008\u0002\u0010q\u001a\u00020\'2\n\u0008\u0002\u0010r\u001a\u0004\u0018\u0001092\u0008\u0008\u0002\u0010s\u001a\u00020\'2\u0008\u0008\u0002\u0010t\u001a\u00020=H\u0000\u00a2\u0006\u0002\u0008uJ\u0087\u0001\u0010w\u001a\u00020@2\n\u0010k\u001a\u0006\u0012\u0002\u0008\u00030l2\u0006\u0010m\u001a\u00020C2\u0008\u0008\u0002\u0010t\u001a\u00020=2\n\u0008\u0002\u0010x\u001a\u0004\u0018\u00010C2\n\u0008\u0002\u0010o\u001a\u0004\u0018\u00010C2\n\u0008\u0002\u0010y\u001a\u0004\u0018\u00010\'2\u0008\u0008\u0002\u0010q\u001a\u00020\'2\n\u0008\u0002\u0010r\u001a\u0004\u0018\u0001092\u0008\u0008\u0002\u0010z\u001a\u00020\'2\u0008\u0008\u0002\u0010{\u001a\u00020\'2\n\u0008\u0002\u0010|\u001a\u0004\u0018\u00010\'H\u0000\u00a2\u0006\u0004\u0008}\u0010~J\u0083\u0001\u0010w\u001a\u00020@2\u0006\u0010v\u001a\u00020=2\u0006\u0010m\u001a\u00020C2\u0008\u0008\u0002\u0010t\u001a\u00020=2\n\u0008\u0002\u0010x\u001a\u0004\u0018\u00010C2\n\u0008\u0002\u0010o\u001a\u0004\u0018\u00010C2\n\u0008\u0002\u0010y\u001a\u0004\u0018\u00010\'2\u0008\u0008\u0002\u0010q\u001a\u00020\'2\n\u0008\u0002\u0010r\u001a\u0004\u0018\u0001092\u0008\u0008\u0002\u0010z\u001a\u00020\'2\u0008\u0008\u0002\u0010{\u001a\u00020\'2\n\u0008\u0002\u0010|\u001a\u0004\u0018\u00010\'H\u0000\u00a2\u0006\u0004\u0008}\u0010\u007fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0006\u0010\u0007\"\u0004\u0008\u0008\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u000c\u0010\r\"\u0004\u0008\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0012\u0010\u0013\"\u0004\u0008\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u0008\u0012\u0004\u0012\u00020\"0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0081\u0001"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
        "<init>",
        "()V",
        "listView",
        "Lorg/telegram/ui/Components/UniversalRecyclerView;",
        "getListView$TMessagesProj",
        "()Lorg/telegram/ui/Components/UniversalRecyclerView;",
        "setListView$TMessagesProj",
        "(Lorg/telegram/ui/Components/UniversalRecyclerView;)V",
        "layoutManager",
        "Landroidx/recyclerview/widget/LinearLayoutManager;",
        "getLayoutManager",
        "()Landroidx/recyclerview/widget/LinearLayoutManager;",
        "setLayoutManager",
        "(Landroidx/recyclerview/widget/LinearLayoutManager;)V",
        "fadeView",
        "Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;",
        "getFadeView",
        "()Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;",
        "setFadeView",
        "(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;)V",
        "recyclerViewState",
        "Landroid/os/Parcelable;",
        "scrollableViewNoiseSuppressor",
        "Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;",
        "iBlur3SourceColor",
        "Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;",
        "iBlur3SourceGlass",
        "Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;",
        "iBlur3Capture",
        "Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;",
        "iBlur3Positions",
        "Ljava/util/ArrayList;",
        "Landroid/graphics/RectF;",
        "iBlur3PositionActionBar",
        "headerFadeAnimator",
        "Landroid/animation/ValueAnimator;",
        "headerFadeShown",
        "",
        "headerFadeAlpha",
        "",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "clearViews",
        "",
        "blur3_InvalidateBlur",
        "updateTopFade",
        "updateHeaderFade",
        "animated",
        "setHeaderFadeAlpha",
        "alpha",
        "onResume",
        "onPause",
        "getTitle",
        "",
        "initializeOptionStrings",
        "scrollToItem",
        "itemId",
        "",
        "showListDialog",
        "item",
        "Lorg/telegram/ui/Components/UItem;",
        "options",
        "",
        "",
        "title",
        "currentValue",
        "onSelected",
        "Lcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;",
        "(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V",
        "icons",
        "",
        "useRadioCell",
        "skipIfValueNotUpdated",
        "(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZ)V",
        "showRestartBulletin",
        "toggleBooleanSettingAndRefresh",
        "stateSetter",
        "Landroidx/core/util/Consumer;",
        "toggleBooleanSettingAndRefresh$TMessagesProj",
        "needHideTitle",
        "hasHeaderFade",
        "isLightStatusBar",
        "fillItems",
        "items",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "view",
        "position",
        "x",
        "y",
        "onLongClick",
        "showCopyLinkOptions",
        "settingLink",
        "isSupportEdgeToEdge",
        "onInsets",
        "left",
        "top",
        "right",
        "bottom",
        "getListTopPadding",
        "topInset",
        "uCheck",
        "enumEntry",
        "",
        "text",
        "checked",
        "subtext",
        "checkBoxIcon",
        "searchable",
        "linkAlias",
        "multiline",
        "icon",
        "uCheck$TMessagesProj",
        "id",
        "uButton",
        "value",
        "showDivider",
        "red",
        "accent",
        "prioritizeTitleOverValue",
        "uButton$TMessagesProj",
        "(Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;",
        "(ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;",
        "HeaderContentView",
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

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nBasePreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BasePreferencesActivity.kt\ncom/exteragram/messenger/preferences/BasePreferencesActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,519:1\n1#2:520\n*E\n"
    }
.end annotation


# instance fields
.field private fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

.field private headerFadeAlpha:F

.field private headerFadeAnimator:Landroid/animation/ValueAnimator;

.field private headerFadeShown:Z

.field private iBlur3Capture:Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;

.field private final iBlur3PositionActionBar:Landroid/graphics/RectF;

.field private final iBlur3Positions:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Landroid/graphics/RectF;",
            ">;"
        }
    .end annotation
.end field

.field private final iBlur3SourceColor:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

.field private final iBlur3SourceGlass:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

.field protected layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

.field public listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

.field private recyclerViewState:Landroid/os/Parcelable;

.field private final scrollableViewNoiseSuppressor:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;


# direct methods
.method public static $r8$lambda$2XkPzyMLgPEoFCyfwWpI27jGeHQ(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/graphics/Canvas;Landroid/view/View;J)Z
    .locals 0

    .line 161
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p1, p2, p3, p4}, Lorg/telegram/ui/Components/RecyclerListView;->drawChild(Landroid/graphics/Canvas;Landroid/view/View;J)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$Ij3MPNxngjiBZQnGGMNJHMrKwXI(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/ItemOptions;Ljava/lang/String;)V
    .locals 2

    .line 383
    invoke-virtual {p1}, Lorg/telegram/ui/Components/ItemOptions;->getContext()Landroid/content/Context;

    move-result-object p1

    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    new-instance v1, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;

    invoke-direct {v1, p2, p0, p1, v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method public static $r8$lambda$J8lpDwhj5CURYfoZlw4B6f0uhHk(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    .line 158
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->blur3_InvalidateBlur()V

    return-void
.end method

.method public static $r8$lambda$JDWUecVaZ6cx-VlL81WxsQKWYP8(ZILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;I)V
    .locals 0

    if-eqz p0, :cond_0

    if-ne p1, p6, :cond_0

    goto :goto_0

    .line 317
    :cond_0
    invoke-interface {p2, p6}, Lcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;->onClick(I)V

    .line 319
    invoke-virtual {p3}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget p1, p4, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->findViewByItemId(I)Landroid/view/View;

    move-result-object p0

    .line 320
    instance-of p1, p0, Lorg/telegram/ui/Cells/TextCell;

    const/4 p2, 0x1

    if-eqz p1, :cond_1

    check-cast p0, Lorg/telegram/ui/Cells/TextCell;

    aget-object p1, p5, p6

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Cells/TextCell;->setValue(Ljava/lang/CharSequence;Z)V

    .line 321
    :cond_1
    invoke-virtual {p3}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    :goto_0
    return-void
.end method

.method public static $r8$lambda$PohbPrV8pAWTGoLi_kTQMvfJnRM(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 253
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->setHeaderFadeAlpha(F)V

    return-void
.end method

.method public static $r8$lambda$k7IrsXGeug19Q0QXMcoRuKyd6Qo(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 2

    .line 331
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v0

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/pm/PackageManager;->getLaunchIntentForPackage(Ljava/lang/String;)Landroid/content/Intent;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 332
    invoke-virtual {v0}, Landroid/content/Intent;->getComponent()Landroid/content/ComponentName;

    move-result-object v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-static {v0}, Landroid/content/Intent;->makeRestartActivityTask(Landroid/content/ComponentName;)Landroid/content/Intent;

    move-result-object v0

    .line 333
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 334
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    .line 335
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object p0

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Ljava/lang/Runtime;->exit(I)V

    return-void
.end method

.method public static $r8$lambda$tX0yOM5R7_7cm6q9veWRk3xEkrM(Lorg/telegram/ui/Components/UniversalRecyclerView;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 1

    .line 158
    new-instance v0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda11;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda11;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {p0, v0}, Landroid/view/View;->postOnAnimation(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$ulwajnBn1zBqckjd2-PkYxxVeDs(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;I)I
    .locals 0

    .line 289
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->findPositionByItemId(I)I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$vJl1gTG8-BK9M2VG8YWhm5C9OQc(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    .line 378
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 379
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->LinkCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    return-void
.end method

.method public constructor <init>()V
    .locals 4

    .line 67
    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;-><init>()V

    .line 75
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v1, 0x0

    const/16 v2, 0x1f

    if-lt v0, v2, :cond_0

    new-instance v3, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    invoke-direct {v3}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;-><init>()V

    goto :goto_0

    :cond_0
    move-object v3, v1

    :goto_0
    iput-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->scrollableViewNoiseSuppressor:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    .line 77
    new-instance v3, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-direct {v3}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;-><init>()V

    iput-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceColor:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    if-lt v0, v2, :cond_1

    .line 79
    new-instance v1, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    invoke-direct {v1, v3}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;-><init>(Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    :cond_1
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceGlass:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    .line 81
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3Positions:Ljava/util/ArrayList;

    .line 82
    new-instance v1, Landroid/graphics/RectF;

    invoke-direct {v1}, Landroid/graphics/RectF;-><init>()V

    iput-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3PositionActionBar:Landroid/graphics/RectF;

    .line 89
    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public static final synthetic access$blur3_InvalidateBlur(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    .line 67
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->blur3_InvalidateBlur()V

    return-void
.end method

.method public static final synthetic access$getIBlur3SourceColor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;
    .locals 0

    .line 67
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceColor:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    return-object p0
.end method

.method public static final synthetic access$getIBlur3SourceGlass$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;
    .locals 0

    .line 67
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceGlass:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    return-object p0
.end method

.method public static final synthetic access$getScrollableViewNoiseSuppressor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;
    .locals 0

    .line 67
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->scrollableViewNoiseSuppressor:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    return-object p0
.end method

.method public static final synthetic access$updateHeaderFade(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Z)V
    .locals 0

    .line 67
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->updateHeaderFade(Z)V

    return-void
.end method

.method public static final synthetic access$updateTopFade(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    .line 67
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->updateTopFade()V

    return-void
.end method

.method private final blur3_InvalidateBlur()V
    .locals 7

    .line 221
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3Capture:Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;

    if-nez v0, :cond_0

    goto :goto_0

    .line 222
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-nez v0, :cond_1

    goto :goto_0

    .line 223
    :cond_1
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1f

    if-lt v0, v1, :cond_3

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->scrollableViewNoiseSuppressor:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    if-eqz v0, :cond_3

    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->chatBlurEnabled()Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    .line 226
    :cond_2
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    .line 227
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    const/high16 v2, 0x42400000    # 48.0f

    .line 228
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 229
    iget-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3PositionActionBar:Landroid/graphics/RectF;

    neg-int v4, v2

    int-to-float v4, v4

    int-to-float v5, v0

    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v6}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    add-int/2addr v6, v2

    int-to-float v2, v6

    const/4 v6, 0x0

    invoke-virtual {v3, v6, v4, v5, v2}, Landroid/graphics/RectF;->set(FFFF)V

    .line 231
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->scrollableViewNoiseSuppressor:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    iget-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3Positions:Ljava/util/ArrayList;

    const/4 v4, 0x1

    invoke-virtual {v2, v3, v4}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->setupRenderNodes(Ljava/util/List;I)V

    .line 232
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->scrollableViewNoiseSuppressor:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3Capture:Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;

    invoke-virtual {v2, p0, v0, v1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->invalidateResultRenderNodes(Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;II)Z

    :cond_3
    :goto_0
    return-void
.end method

.method private final setHeaderFadeAlpha(F)V
    .locals 1

    .line 261
    iput p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeAlpha:F

    .line 262
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    if-eqz p0, :cond_0

    const/high16 v0, 0x437f0000    # 255.0f

    mul-float/2addr p1, v0

    invoke-static {p1}, Lkotlin/math/MathKt;->roundToInt(F)I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeTopAlpha(I)V

    :cond_0
    return-void
.end method

.method public static synthetic showListDialog$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZILjava/lang/Object;)V
    .locals 12

    move/from16 v0, p9

    if-nez p10, :cond_3

    and-int/lit8 v1, v0, 0x40

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    if-nez p3, :cond_0

    move v1, v2

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    move v10, v1

    goto :goto_1

    :cond_1
    move/from16 v10, p7

    :goto_1
    and-int/lit16 v0, v0, 0x80

    if-eqz v0, :cond_2

    move v11, v2

    :goto_2
    move-object v3, p0

    move-object v4, p1

    move-object v5, p2

    move-object v6, p3

    move-object/from16 v7, p4

    move/from16 v8, p5

    move-object/from16 v9, p6

    goto :goto_3

    :cond_2
    move/from16 v11, p8

    goto :goto_2

    .line 303
    :goto_3
    invoke-virtual/range {v3 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZ)V

    return-void

    :cond_3
    const-string p0, "Super calls with default arguments not supported in this target, function: showListDialog"

    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;
    .locals 2

    if-nez p13, :cond_9

    and-int/lit8 p13, p12, 0x4

    const/4 v0, 0x0

    if-eqz p13, :cond_0

    move p3, v0

    :cond_0
    and-int/lit8 p13, p12, 0x8

    const/4 v1, 0x0

    if-eqz p13, :cond_1

    move-object p4, v1

    :cond_1
    and-int/lit8 p13, p12, 0x10

    if-eqz p13, :cond_2

    move-object p5, v1

    :cond_2
    and-int/lit8 p13, p12, 0x20

    if-eqz p13, :cond_3

    move-object p6, v1

    :cond_3
    and-int/lit8 p13, p12, 0x40

    if-eqz p13, :cond_4

    const/4 p7, 0x1

    :cond_4
    and-int/lit16 p13, p12, 0x80

    if-eqz p13, :cond_5

    move-object p8, v1

    :cond_5
    and-int/lit16 p13, p12, 0x100

    if-eqz p13, :cond_6

    move p9, v0

    :cond_6
    and-int/lit16 p13, p12, 0x200

    if-eqz p13, :cond_7

    move p10, v0

    :cond_7
    and-int/lit16 p12, p12, 0x400

    if-eqz p12, :cond_8

    move-object p11, v1

    .line 493
    :cond_8
    invoke-virtual/range {p0 .. p11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj(ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0

    :cond_9
    const-string p0, "Super calls with default arguments not supported in this target, function: uButton"

    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;
    .locals 2

    if-nez p13, :cond_9

    and-int/lit8 p13, p12, 0x4

    const/4 v0, 0x0

    if-eqz p13, :cond_0

    move p3, v0

    :cond_0
    and-int/lit8 p13, p12, 0x8

    const/4 v1, 0x0

    if-eqz p13, :cond_1

    move-object p4, v1

    :cond_1
    and-int/lit8 p13, p12, 0x10

    if-eqz p13, :cond_2

    move-object p5, v1

    :cond_2
    and-int/lit8 p13, p12, 0x20

    if-eqz p13, :cond_3

    move-object p6, v1

    :cond_3
    and-int/lit8 p13, p12, 0x40

    if-eqz p13, :cond_4

    const/4 p7, 0x1

    :cond_4
    and-int/lit16 p13, p12, 0x80

    if-eqz p13, :cond_5

    move-object p8, v1

    :cond_5
    and-int/lit16 p13, p12, 0x100

    if-eqz p13, :cond_6

    move p9, v0

    :cond_6
    and-int/lit16 p13, p12, 0x200

    if-eqz p13, :cond_7

    move p10, v0

    :cond_7
    and-int/lit16 p12, p12, 0x400

    if-eqz p12, :cond_8

    move-object p11, v1

    .line 467
    :cond_8
    invoke-virtual/range {p0 .. p11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj(Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0

    :cond_9
    const-string p0, "Super calls with default arguments not supported in this target, function: uButton"

    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;
    .locals 3

    if-nez p11, :cond_6

    and-int/lit8 p11, p10, 0x8

    const/4 v0, 0x0

    if-eqz p11, :cond_0

    move-object p4, v0

    :cond_0
    and-int/lit8 p11, p10, 0x10

    const/4 v1, 0x0

    if-eqz p11, :cond_1

    move p5, v1

    :cond_1
    and-int/lit8 p11, p10, 0x20

    const/4 v2, 0x1

    if-eqz p11, :cond_2

    move p6, v2

    :cond_2
    and-int/lit8 p11, p10, 0x40

    if-eqz p11, :cond_3

    move-object p7, v0

    :cond_3
    and-int/lit16 p11, p10, 0x80

    if-eqz p11, :cond_4

    move p8, v2

    :cond_4
    and-int/lit16 p10, p10, 0x100

    if-eqz p10, :cond_5

    move p9, v1

    .line 446
    :cond_5
    invoke-virtual/range {p0 .. p9}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj(ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZI)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0

    :cond_6
    const-string p0, "Super calls with default arguments not supported in this target, function: uCheck"

    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;
    .locals 3

    if-nez p11, :cond_6

    and-int/lit8 p11, p10, 0x8

    const/4 v0, 0x0

    if-eqz p11, :cond_0

    move-object p4, v0

    :cond_0
    and-int/lit8 p11, p10, 0x10

    const/4 v1, 0x0

    if-eqz p11, :cond_1

    move p5, v1

    :cond_1
    and-int/lit8 p11, p10, 0x20

    const/4 v2, 0x1

    if-eqz p11, :cond_2

    move p6, v2

    :cond_2
    and-int/lit8 p11, p10, 0x40

    if-eqz p11, :cond_3

    move-object p7, v0

    :cond_3
    and-int/lit16 p11, p10, 0x80

    if-eqz p11, :cond_4

    move p8, v2

    :cond_4
    and-int/lit16 p10, p10, 0x100

    if-eqz p10, :cond_5

    move p9, v1

    .line 424
    :cond_5
    invoke-virtual/range {p0 .. p9}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj(Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZI)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0

    :cond_6
    const-string p0, "Super calls with default arguments not supported in this target, function: uCheck"

    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private final updateHeaderFade(Z)V
    .locals 3

    .line 242
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    if-nez v0, :cond_0

    goto :goto_0

    .line 243
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/RecyclerListView;->canScrollVertically(I)Z

    move-result v0

    .line 244
    iget-boolean v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeShown:Z

    if-ne v1, v0, :cond_1

    :goto_0
    return-void

    .line 245
    :cond_1
    iput-boolean v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeShown:Z

    .line 246
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeAnimator:Landroid/animation/ValueAnimator;

    if-eqz v1, :cond_2

    invoke-virtual {v1}, Landroid/animation/ValueAnimator;->cancel()V

    :cond_2
    const/4 v1, 0x0

    .line 247
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeAnimator:Landroid/animation/ValueAnimator;

    const/4 v1, 0x0

    const/high16 v2, 0x3f800000    # 1.0f

    if-nez p1, :cond_4

    if-eqz v0, :cond_3

    move v1, v2

    .line 249
    :cond_3
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->setHeaderFadeAlpha(F)V

    return-void

    .line 252
    :cond_4
    iget p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeAlpha:F

    if-eqz v0, :cond_5

    move v1, v2

    :cond_5
    const/4 v0, 0x2

    new-array v0, v0, [F

    const/4 v2, 0x0

    aput p1, v0, v2

    const/4 p1, 0x1

    aput v1, v0, p1

    invoke-static {v0}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p1

    .line 253
    new-instance v0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {p1, v0}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    const-wide/16 v0, 0x140

    .line 254
    invoke-virtual {p1, v0, v1}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 255
    sget-object v0, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {p1, v0}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 252
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeAnimator:Landroid/animation/ValueAnimator;

    .line 257
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->start()V

    return-void
.end method

.method private final updateTopFade()V
    .locals 5

    .line 236
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    if-nez v0, :cond_0

    return-void

    .line 237
    :cond_0
    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v2

    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    const/4 v4, 0x1

    invoke-static {v0, v1, v2, v3, v4}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->setupChatTopFade(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;Lorg/telegram/ui/ActionBar/ActionBar;IIZ)V

    .line 238
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    iget p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->headerFadeAlpha:F

    const/high16 v1, 0x437f0000    # 255.0f

    mul-float/2addr p0, v1

    invoke-static {p0}, Lkotlin/math/MathKt;->roundToInt(F)I

    move-result p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeTopAlpha(I)V

    return-void
.end method


# virtual methods
.method public clearViews()V
    .locals 1

    .line 179
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    if-eqz v0, :cond_0

    .line 180
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getLayoutManager()Landroidx/recyclerview/widget/LinearLayoutManager;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->recyclerViewState:Landroid/os/Parcelable;

    .line 182
    :cond_0
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->clearViews()V

    return-void
.end method

.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 9

    .line 93
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->initializeOptionStrings()V

    .line 95
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    .line 96
    sget v1, Lorg/telegram/messenger/R$drawable;->ic_ab_back:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setBackButtonImage(I)V

    const/4 v1, 0x0

    .line 97
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setAllowOverlayTitle(Z)V

    .line 98
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getTitle()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 99
    new-instance v2, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {v0, v2}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    .line 104
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setCastShadows(Z)V

    .line 105
    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setAddToContainer(Z)V

    .line 108
    new-instance v0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/content/Context;)V

    .line 109
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v3

    invoke-virtual {v0, v3}, Landroid/view/View;->setBackgroundColor(I)V

    .line 111
    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    iget-object v4, v3, Lorg/telegram/ui/ActionBar/ActionBar;->menu:Lorg/telegram/ui/ActionBar/ActionBarMenu;

    if-nez v4, :cond_0

    invoke-virtual {v3}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    .line 113
    :cond_0
    iget-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceColor:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-virtual {p0, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v4

    invoke-virtual {v3, v4}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->setColor(I)V

    .line 115
    iget-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceGlass:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    const/4 v4, 0x0

    if-eqz v3, :cond_2

    .line 116
    sget v5, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v6, 0x1f

    if-lt v5, v6, :cond_1

    goto :goto_0

    :cond_1
    move-object v3, v4

    :goto_0
    if-eqz v3, :cond_2

    .line 118
    new-instance v5, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;

    invoke-direct {v5, v3}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;-><init>(Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    const/high16 v3, 0x40000

    .line 119
    invoke-static {v3}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result v3

    invoke-virtual {v5, v3}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->setLiquidGlassEffectAllowed(Z)V

    goto :goto_1

    .line 121
    :cond_2
    new-instance v5, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;

    iget-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3SourceColor:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-direct {v5, v3}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;-><init>(Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    .line 122
    :goto_1
    new-instance v3, Lorg/telegram/ui/Components/chat/ViewPositionWatcher;

    invoke-direct {v3, v0}, Lorg/telegram/ui/Components/chat/ViewPositionWatcher;-><init>(Landroid/view/View;)V

    invoke-virtual {v5, v3, v0}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->setSourceRootView(Lorg/telegram/ui/Components/chat/ViewPositionWatcher;Landroid/view/ViewGroup;)V

    .line 124
    iget-object v3, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/high16 v6, 0x41400000    # 12.0f

    .line 125
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    invoke-virtual {v3, v6}, Lorg/telegram/ui/ActionBar/ActionBar;->setGlassPadding(I)V

    const/16 v6, 0x14

    .line 126
    invoke-virtual {v3, v6}, Lorg/telegram/ui/ActionBar/ActionBar;->setGlassTitleTextSize(I)V

    .line 127
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getResourceProvider()Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v6

    invoke-static {v6}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->topPanelChatActivity(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Lorg/telegram/ui/ActionBar/ActionBar;->setupGlass(Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)V

    const/4 v5, 0x1

    .line 128
    invoke-static {v3, v5}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->applyChatHeaderGlassStyle(Lorg/telegram/ui/ActionBar/ActionBar;Z)V

    .line 129
    sget v6, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    invoke-virtual {p0, v6}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v7

    invoke-virtual {v3, v7}, Lorg/telegram/ui/ActionBar/ActionBar;->setTitleColor(I)V

    .line 130
    invoke-virtual {p0, v6}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v6

    invoke-virtual {v3, v6, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsColor(IZ)V

    .line 131
    sget v6, Lorg/telegram/ui/ActionBar/Theme;->key_listSelector:I

    invoke-virtual {p0, v6}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v6

    invoke-virtual {v3, v6, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setItemsBackgroundColor(IZ)V

    .line 134
    new-instance v3, Lorg/telegram/ui/Components/UniversalRecyclerView;

    new-instance v6, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    new-instance v7, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda7;

    invoke-direct {v7, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    new-instance v8, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda8;

    invoke-direct {v8, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda8;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-direct {v3, p0, v6, v7, v8}, Lorg/telegram/ui/Components/UniversalRecyclerView;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/messenger/Utilities$Callback2;Lorg/telegram/messenger/Utilities$Callback5;Lorg/telegram/messenger/Utilities$Callback5Return;)V

    .line 135
    invoke-virtual {v3}, Lorg/telegram/ui/Components/UniversalRecyclerView;->setSections()V

    .line 136
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->needHideTitle()Z

    move-result v6

    if-eqz v6, :cond_3

    iget-object v6, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v6, v3, v5}, Lorg/telegram/ui/ActionBar/ActionBar;->setAdaptiveBackground(Landroidx/recyclerview/widget/RecyclerView;Z)V

    .line 137
    :cond_3
    iget-object v6, v3, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {v6, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->setApplyBackground(Z)V

    .line 138
    invoke-virtual {v3, v1}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    .line 139
    sget v6, Lorg/telegram/messenger/AndroidUtilities;->statusBarHeight:I

    invoke-virtual {p0, v6}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListTopPadding(I)I

    move-result v6

    invoke-virtual {v3, v1, v6, v1, v1}, Landroid/view/View;->setPadding(IIII)V

    .line 141
    new-instance v6, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-direct {v6, p1, v5, v1}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    .line 142
    invoke-virtual {p0, v6}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->setLayoutManager(Landroidx/recyclerview/widget/LinearLayoutManager;)V

    .line 141
    invoke-virtual {v3, v6}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    .line 144
    iget-object v5, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->recyclerViewState:Landroid/os/Parcelable;

    if-eqz v5, :cond_4

    .line 145
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getLayoutManager()Landroidx/recyclerview/widget/LinearLayoutManager;

    move-result-object v6

    invoke-virtual {v6, v5}, Landroidx/recyclerview/widget/LinearLayoutManager;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 146
    iput-object v4, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->recyclerViewState:Landroid/os/Parcelable;

    .line 149
    :cond_4
    new-instance v4, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {v3, v4}, Landroidx/recyclerview/widget/RecyclerView;->addOnScrollListener(Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;)V

    .line 158
    new-instance v4, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda9;

    invoke-direct {v4, v3, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/ui/Components/UniversalRecyclerView;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {v3, v4}, Lorg/telegram/ui/Components/RecyclerListView;->addEdgeEffectListener(Ljava/lang/Runnable;)V

    .line 134
    invoke-virtual {p0, v3}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->setListView$TMessagesProj(Lorg/telegram/ui/Components/UniversalRecyclerView;)V

    .line 161
    new-instance v3, Lorg/telegram/ui/Components/blur3/ViewGroupPartRenderer;

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v4

    new-instance v5, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda10;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda10;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-direct {v3, v4, v0, v5}, Lorg/telegram/ui/Components/blur3/ViewGroupPartRenderer;-><init>(Landroid/view/ViewGroup;Landroid/view/ViewGroup;Lorg/telegram/ui/Components/blur3/ViewGroupPartRenderer$DrawChildMethod;)V

    iput-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->iBlur3Capture:Lorg/telegram/ui/Components/blur3/capture/IBlur3Capture;

    .line 162
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v3

    const/high16 v4, -0x40800000    # -1.0f

    const/4 v5, -0x1

    invoke-static {v5, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v3, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 164
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->hasHeaderFade()Z

    move-result v3

    if-eqz v3, :cond_5

    .line 165
    new-instance v3, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    invoke-direct {v3, p1}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;-><init>(Landroid/content/Context;)V

    .line 166
    invoke-virtual {v3, v2}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setupColorKey(I)V

    .line 167
    invoke-virtual {v3, v1}, Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;->setFadeTopAlpha(I)V

    .line 165
    iput-object v3, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    .line 169
    invoke-static {}, Lorg/telegram/ui/Components/LayoutHelper;->createFrameMatchParent()Landroid/widget/FrameLayout$LayoutParams;

    move-result-object p1

    invoke-virtual {v0, v3, p1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 172
    :cond_5
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    const/4 v1, -0x2

    const/16 v2, 0x30

    invoke-static {v5, v1, v2}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(III)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 174
    iput-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    return-object v0
.end method

.method public abstract fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation
.end method

.method public final getFadeView()Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;
    .locals 0

    .line 71
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    return-object p0
.end method

.method public final getLayoutManager()Landroidx/recyclerview/widget/LinearLayoutManager;
    .locals 0

    .line 70
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public getListTopPadding(I)I
    .locals 0

    .line 422
    invoke-static {}, Lorg/telegram/ui/ActionBar/ActionBar;->getCurrentActionBarHeight()I

    move-result p0

    add-int/2addr p1, p0

    return p1
.end method

.method public final getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;
    .locals 0

    .line 69
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public abstract getTitle()Ljava/lang/String;
.end method

.method public hasHeaderFade()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public initializeOptionStrings()V
    .locals 0

    return-void
.end method

.method public isLightStatusBar()Z
    .locals 2

    .line 357
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    if-nez v0, :cond_0

    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->isLightStatusBar()Z

    move-result p0

    return p0

    .line 358
    :cond_0
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {p0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result p0

    const/4 v1, 0x1

    invoke-static {v0, p0, v1}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isLightChatStatusBar(Lorg/telegram/ui/ActionBar/ActionBar;IZ)Z

    move-result p0

    return p0
.end method

.method public isSupportEdgeToEdge()Z
    .locals 0
    .annotation runtime Lkotlin/Deprecated;
        message = "Deprecated in Java"
    .end annotation

    const/4 p0, 0x1

    return p0
.end method

.method public needHideTitle()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public abstract onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
.end method

.method public onInsets(IIII)V
    .locals 0

    .line 415
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    .line 416
    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListTopPadding(I)I

    move-result p0

    const/4 p2, 0x0

    invoke-virtual {p1, p2, p0, p2, p4}, Landroid/view/View;->setPadding(IIII)V

    .line 417
    invoke-virtual {p1, p2}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    return-void
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 0

    .line 366
    invoke-static {}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->getInstance()Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;

    move-result-object p3

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p4

    invoke-virtual {p3, p4, p1}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->getFirstSettingLink(Ljava/lang/Class;Lorg/telegram/ui/Components/UItem;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 367
    invoke-virtual {p0, p2, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showCopyLinkOptions(Landroid/view/View;Ljava/lang/String;)V

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public onPause()V
    .locals 0

    .line 276
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onPause()V

    .line 277
    invoke-static {p0}, Lorg/telegram/ui/Components/Bulletin;->removeDelegate(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void
.end method

.method public onResume()V
    .locals 2

    .line 266
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onResume()V

    .line 267
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget-object v0, v0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 268
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$onResume$1;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-static {p0, v0}, Lorg/telegram/ui/Components/Bulletin;->addDelegate(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/Components/Bulletin$Delegate;)V

    return-void
.end method

.method public final scrollToItem(I)V
    .locals 3

    .line 285
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    if-nez v0, :cond_0

    return-void

    .line 286
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->findPositionByItemId(I)I

    move-result v0

    if-ltz v0, :cond_2

    .line 287
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v1

    iget-object v1, v1, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lorg/telegram/ui/Components/UniversalAdapter;->getItemCount()I

    move-result v1

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    :goto_0
    if-ge v0, v1, :cond_2

    .line 288
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getLayoutManager()Landroidx/recyclerview/widget/LinearLayoutManager;

    move-result-object v1

    const/high16 v2, 0x42a00000    # 80.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-virtual {v1, v0, v2}, Landroidx/recyclerview/widget/LinearLayoutManager;->scrollToPositionWithOffset(II)V

    .line 289
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;I)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/RecyclerListView;->highlightRow(Lorg/telegram/ui/Components/RecyclerListView$IntReturnCallback;)V

    return-void

    .line 291
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->getInstance()Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;

    move-result-object p1

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->onSettingNotFound(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void
.end method

.method public final setFadeView(Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;)V
    .locals 0

    .line 71
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->fadeView:Lorg/telegram/ui/Components/chat/layouts/ChatActivityFadeView;

    return-void
.end method

.method public final setLayoutManager(Landroidx/recyclerview/widget/LinearLayoutManager;)V
    .locals 0

    .line 70
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->layoutManager:Landroidx/recyclerview/widget/LinearLayoutManager;

    return-void
.end method

.method public final setListView$TMessagesProj(Lorg/telegram/ui/Components/UniversalRecyclerView;)V
    .locals 0

    .line 69
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->listView:Lorg/telegram/ui/Components/UniversalRecyclerView;

    return-void
.end method

.method public final showCopyLinkOptions(Landroid/view/View;Ljava/lang/String;)V
    .locals 4

    .line 374
    invoke-static {p0, p1}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object v0

    const/4 v1, 0x0

    .line 375
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/ItemOptions;->setLongPressSelectionEnabled(Z)Lorg/telegram/ui/Components/ItemOptions;

    .line 377
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_copy:I

    sget v2, Lorg/telegram/messenger/R$string;->CopyLink:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {v3, p2, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda4;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 382
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_share:I

    sget v2, Lorg/telegram/messenger/R$string;->ShareLink:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {v3, p0, v0, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/ItemOptions;Ljava/lang/String;)V

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 406
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 407
    invoke-virtual {v0}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return-void
.end method

.method public final showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V
    .locals 11

    const/16 v9, 0xc0

    const/4 v10, 0x0

    const/4 v3, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v4, p3

    move v5, p4

    move-object/from16 v6, p5

    .line 301
    invoke-static/range {v0 .. v10}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZILjava/lang/Object;)V

    return-void
.end method

.method public final showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V
    .locals 11
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/16 v9, 0xc0

    const/4 v10, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move/from16 v5, p5

    move-object/from16 v6, p6

    invoke-static/range {v0 .. v10}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZILjava/lang/Object;)V

    return-void
.end method

.method public final showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;Z)V
    .locals 11
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/16 v9, 0x80

    const/4 v10, 0x0

    const/4 v8, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move/from16 v5, p5

    move-object/from16 v6, p6

    move/from16 v7, p7

    invoke-static/range {v0 .. v10}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZILjava/lang/Object;)V

    return-void
.end method

.method public final showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;[ILjava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;ZZ)V
    .locals 7
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 314
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    move-object v6, p2

    move-object p2, p4

    .line 315
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object p4

    new-instance v0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda0;

    move-object v4, p0

    move-object v5, p1

    move v2, p5

    move-object v3, p6

    move v1, p8

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda0;-><init>(ZILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;)V

    move-object p5, v0

    .line 322
    iget-object p6, v4, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-object p1, p3

    move p3, v2

    move-object p0, v6

    .line 315
    invoke-static/range {p0 .. p7}, Lcom/exteragram/messenger/utils/ui/PopupUtils;->showDialog([Ljava/lang/CharSequence;[ILjava/lang/String;ILandroid/content/Context;Lcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)V

    return-void
.end method

.method public final showRestartBulletin()V
    .locals 5

    .line 326
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    .line 327
    sget v1, Lorg/telegram/messenger/R$raw;->info:I

    .line 328
    sget v2, Lorg/telegram/messenger/R$string;->RestartRequired:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 329
    sget v3, Lorg/telegram/messenger/R$string;->BotUnblock:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 326
    new-instance v4, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 336
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public final toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/ui/Components/UItem;",
            "Landroidx/core/util/Consumer<",
            "Ljava/lang/Boolean;",
            ">;)V"
        }
    .end annotation

    .line 340
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    iget v1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->findViewByItemId(I)Landroid/view/View;

    move-result-object v0

    .line 341
    iget-boolean v1, p1, Lorg/telegram/ui/Components/UItem;->checked:Z

    const/4 v2, 0x1

    xor-int/2addr v1, v2

    .line 342
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    invoke-interface {p2, v3}, Landroidx/core/util/Consumer;->accept(Ljava/lang/Object;)V

    .line 344
    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    .line 346
    instance-of p1, v0, Lorg/telegram/ui/Cells/CheckBoxCell;

    if-eqz p1, :cond_0

    check-cast v0, Lorg/telegram/ui/Cells/CheckBoxCell;

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Cells/CheckBoxCell;->setChecked(ZZ)V

    goto :goto_0

    .line 347
    :cond_0
    instance-of p1, v0, Lorg/telegram/ui/Cells/TextCheckCell;

    if-eqz p1, :cond_1

    check-cast v0, Lorg/telegram/ui/Cells/TextCheckCell;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Cells/TextCheckCell;->setChecked(Z)V

    .line 349
    :cond_1
    :goto_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    invoke-virtual {p0, v2}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    return-void
.end method

.method public final uButton$TMessagesProj(ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;
    .locals 8

    move-object/from16 v0, p8

    if-eqz p5, :cond_1

    .line 506
    invoke-interface {p5}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    const/16 v6, 0x40

    const/16 v7, 0x3c

    move v2, p1

    move-object v4, p2

    move v3, p3

    move-object v5, p5

    invoke-static/range {v2 .. v7}, Lorg/telegram/ui/Components/UItem;->asButtonWithSubtext(IILjava/lang/CharSequence;Ljava/lang/CharSequence;II)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    goto :goto_3

    :cond_1
    :goto_0
    if-eqz p4, :cond_3

    .line 507
    invoke-interface {p4}, Ljava/lang/CharSequence;->length()I

    move-result p5

    if-nez p5, :cond_2

    goto :goto_1

    :cond_2
    if-eqz p3, :cond_3

    invoke-static {p1, p3, p2, p4}, Lorg/telegram/ui/Components/UItem;->asButton(IILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    goto :goto_3

    :cond_3
    :goto_1
    if-eqz p4, :cond_5

    .line 508
    invoke-interface {p4}, Ljava/lang/CharSequence;->length()I

    move-result p5

    if-nez p5, :cond_4

    goto :goto_2

    :cond_4
    invoke-static {p1, p2, p4}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    goto :goto_3

    :cond_5
    :goto_2
    if-eqz p3, :cond_6

    .line 509
    invoke-static {p1, p3, p2}, Lorg/telegram/ui/Components/UItem;->asButton(IILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    goto :goto_3

    .line 510
    :cond_6
    invoke-static/range {p1 .. p2}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    :goto_3
    if-eqz p9, :cond_7

    .line 512
    invoke-virtual {p1}, Lorg/telegram/ui/Components/UItem;->red()Lorg/telegram/ui/Components/UItem;

    :cond_7
    if-eqz p10, :cond_8

    .line 513
    invoke-virtual {p1}, Lorg/telegram/ui/Components/UItem;->accent()Lorg/telegram/ui/Components/UItem;

    :cond_8
    if-eqz p7, :cond_9

    .line 514
    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    :cond_9
    if-eqz v0, :cond_a

    .line 515
    invoke-virtual {p1, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    :cond_a
    if-eqz p6, :cond_b

    .line 516
    invoke-virtual {p6}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/UItem;->showDivider(Z)Lorg/telegram/ui/Components/UItem;

    :cond_b
    if-eqz p11, :cond_c

    .line 517
    invoke-virtual/range {p11 .. p11}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/UItem;->prioritizeTitleOverValue(Z)Lorg/telegram/ui/Components/UItem;

    :cond_c
    return-object p1
.end method

.method public final uButton$TMessagesProj(Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;
    .locals 12
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Enum<",
            "*>;",
            "Ljava/lang/CharSequence;",
            "I",
            "Ljava/lang/CharSequence;",
            "Ljava/lang/CharSequence;",
            "Ljava/lang/Boolean;",
            "Z",
            "Ljava/lang/String;",
            "ZZ",
            "Ljava/lang/Boolean;",
            ")",
            "Lorg/telegram/ui/Components/UItem;"
        }
    .end annotation

    .line 480
    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 v1, p1, 0x1

    move-object v0, p0

    move-object v2, p2

    move v3, p3

    move-object/from16 v4, p4

    move-object/from16 v5, p5

    move-object/from16 v6, p6

    move/from16 v7, p7

    move-object/from16 v8, p8

    move/from16 v9, p9

    move/from16 v10, p10

    move-object/from16 v11, p11

    .line 479
    invoke-virtual/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj(ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method

.method public final uCheck$TMessagesProj(ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZI)Lorg/telegram/ui/Components/UItem;
    .locals 0

    if-eqz p4, :cond_0

    .line 457
    invoke-static {p1, p2, p4, p8}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    goto :goto_0

    .line 458
    :cond_0
    invoke-static {p1, p2}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    .line 460
    :goto_0
    invoke-virtual {p1, p3}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    if-eqz p9, :cond_1

    .line 461
    iput p9, p1, Lorg/telegram/ui/Components/UItem;->iconResId:I

    :cond_1
    if-eqz p5, :cond_2

    .line 462
    invoke-virtual {p1, p5}, Lorg/telegram/ui/Components/UItem;->setCheckBoxIcon(I)Lorg/telegram/ui/Components/UItem;

    :cond_2
    if-eqz p6, :cond_3

    .line 463
    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    :cond_3
    if-eqz p7, :cond_4

    .line 464
    invoke-virtual {p1, p7, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    :cond_4
    return-object p1
.end method

.method public final uCheck$TMessagesProj(Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZI)Lorg/telegram/ui/Components/UItem;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Enum<",
            "*>;",
            "Ljava/lang/CharSequence;",
            "Z",
            "Ljava/lang/CharSequence;",
            "IZ",
            "Ljava/lang/String;",
            "ZI)",
            "Lorg/telegram/ui/Components/UItem;"
        }
    .end annotation

    .line 435
    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 v1, p1, 0x1

    move-object v0, p0

    move-object v2, p2

    move v3, p3

    move-object v4, p4

    move v5, p5

    move/from16 v6, p6

    move-object/from16 v7, p7

    move/from16 v8, p8

    move/from16 v9, p9

    .line 434
    invoke-virtual/range {v0 .. v9}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj(ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZI)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    return-object p0
.end method
