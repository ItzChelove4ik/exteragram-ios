.class public final Lcom/exteragram/messenger/preferences/SwitchGroup;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/SwitchGroup$Child;,
        Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0010\u0010\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u000b\n\u0002\u0010!\n\u0002\u0008\u0007\u0018\u00002\u00020\u0001:\u0002GHBE\u0008\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0008\u0008\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\u0008\u0002\u0010\u0008\u001a\u0004\u0018\u00010\u0007\u0012\n\u0008\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0008\u0008\u0002\u0010\u000c\u001a\u00020\u000b\u00a2\u0006\u0004\u0008\r\u0010\u000eBI\u0008\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0010\u001a\u0006\u0012\u0002\u0008\u00030\u000f\u0012\u0008\u0008\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\u0008\u0002\u0010\u0008\u001a\u0004\u0018\u00010\u0007\u0012\n\u0008\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0008\u0008\u0002\u0010\u000c\u001a\u00020\u000b\u00a2\u0006\u0004\u0008\r\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\u0008\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\u0008\u0019\u0010\u001aJ\u000f\u0010\u0008\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\u0008\u0008\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\u0008\u001d\u0010\u001eJ\u001b\u0010!\u001a\u00020\u00002\u000c\u0010 \u001a\u0008\u0012\u0004\u0012\u00020\u00180\u001f\u00a2\u0006\u0004\u0008!\u0010\"J\u0015\u0010$\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\t\u00a2\u0006\u0004\u0008$\u0010%J=\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u0006\u0012\u0002\u0008\u00030\u000f2\u0008\u0008\u0002\u0010&\u001a\u00020\u00042\u000c\u0010(\u001a\u0008\u0012\u0004\u0012\u00020\u000b0\'2\n\u0008\u0002\u0010)\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\u0008\u0014\u0010*J?\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u0006\u0012\u0002\u0008\u00030\u000f2\u0008\u0008\u0002\u0010&\u001a\u00020\u00042\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\n\u0008\u0002\u0010)\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\u0008\u0014\u0010/JE\u00101\u001a\u00020\u00002\u0006\u00100\u001a\u00020+2\n\u0010\u0010\u001a\u0006\u0012\u0002\u0008\u00030\u000f2\u0008\u0008\u0002\u0010&\u001a\u00020\u00042\u000c\u0010(\u001a\u0008\u0012\u0004\u0012\u00020\u000b0\'2\n\u0008\u0002\u0010)\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\u00081\u00102JG\u00101\u001a\u00020\u00002\u0006\u00100\u001a\u00020+2\n\u0010\u0010\u001a\u0006\u0012\u0002\u0008\u00030\u000f2\u0008\u0008\u0002\u0010&\u001a\u00020\u00042\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\n\u0008\u0002\u0010)\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\u00081\u00103J%\u00108\u001a\u00020\u00182\u0016\u00107\u001a\u0012\u0012\u0004\u0012\u00020504j\u0008\u0012\u0004\u0012\u000205`6\u00a2\u0006\u0004\u00088\u00109J\u0015\u0010;\u001a\u00020\u00182\u0006\u0010:\u001a\u000205\u00a2\u0006\u0004\u0008;\u0010<R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\u0003\u0010=R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\u0005\u0010>R\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\u0006\u0010>R\u0016\u0010\u0008\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\u0008\u0010?R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\n\u0010@R\u0014\u0010\u000c\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008\u000c\u0010AR\u001a\u0010C\u001a\u0008\u0012\u0004\u0012\u00020\u00120B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\u0008C\u0010DR\u001e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008!\u0010ER\u0016\u0010F\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\u0008F\u0010A\u00a8\u0006I"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "fragment",
        "",
        "id",
        "titleRes",
        "",
        "title",
        "",
        "linkAlias",
        "",
        "searchable",
        "<init>",
        "(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;IILjava/lang/CharSequence;Ljava/lang/String;Z)V",
        "",
        "enum",
        "(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;Z)V",
        "Lcom/exteragram/messenger/preferences/SwitchGroup$Child;",
        "child",
        "add",
        "(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "Landroid/view/View;",
        "view",
        "",
        "onSwitchClick",
        "(Landroid/view/View;)V",
        "()Ljava/lang/CharSequence;",
        "enabledOnly",
        "count",
        "(Z)I",
        "Lkotlin/Function0;",
        "runnable",
        "onChanged",
        "(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "alias",
        "markNew",
        "(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "textRes",
        "Lkotlin/reflect/KMutableProperty0;",
        "property",
        "text",
        "(Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "Ljava/util/function/BooleanSupplier;",
        "getter",
        "Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;",
        "setter",
        "(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "visible",
        "addIf",
        "(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "Lkotlin/collections/ArrayList;",
        "items",
        "fill",
        "(Ljava/util/ArrayList;)V",
        "item",
        "onClick",
        "(Lorg/telegram/ui/Components/UItem;)V",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "I",
        "Ljava/lang/CharSequence;",
        "Ljava/lang/String;",
        "Z",
        "",
        "children",
        "Ljava/util/List;",
        "Lkotlin/jvm/functions/Function0;",
        "expanded",
        "Setter",
        "Child",
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
        "SMAP\nSwitchGroup.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SwitchGroup.kt\ncom/exteragram/messenger/preferences/SwitchGroup\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,178:1\n1#2:179\n296#3,2:180\n1834#3,4:182\n*S KotlinDebug\n*F\n+ 1 SwitchGroup.kt\ncom/exteragram/messenger/preferences/SwitchGroup\n*L\n127#1:180,2\n150#1:182,4\n*E\n"
    }
.end annotation


# instance fields
.field private final children:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/preferences/SwitchGroup$Child;",
            ">;"
        }
    .end annotation
.end field

.field private expanded:Z

.field private final fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

.field private final id:I

.field private final linkAlias:Ljava/lang/String;

.field private onChanged:Lkotlin/jvm/functions/Function0;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private final searchable:Z

.field private final title:Ljava/lang/CharSequence;

.field private final titleRes:I


# direct methods
.method public static $r8$lambda$H1vtAg21u4HYev2gzJx5EFysn30(Lkotlin/reflect/KMutableProperty0;Z)Lkotlin/Unit;
    .locals 0

    .line 84
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-interface {p0, p1}, Lkotlin/reflect/KMutableProperty0;->set(Ljava/lang/Object;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$NrwV9E00icjSfutjNzsualdsb7A(Lkotlin/reflect/KMutableProperty0;)Z
    .locals 0

    .line 61
    invoke-interface {p0}, Lkotlin/reflect/KProperty0;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$Wm1qktTcUdWT7W6MQ8u_rRLcscg(Lkotlin/reflect/KMutableProperty0;Z)Lkotlin/Unit;
    .locals 0

    .line 61
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-interface {p0, p1}, Lkotlin/reflect/KMutableProperty0;->set(Ljava/lang/Object;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$aeufRtXptfL3O3SkKV8uI86o1Bk(Lcom/exteragram/messenger/preferences/SwitchGroup;Landroid/view/View;)V
    .locals 0

    .line 100
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onSwitchClick(Landroid/view/View;)V

    return-void
.end method

.method public static $r8$lambda$e8_Wb2zIsFTiLBfPR7uY6g-swZ0(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;Z)V
    .locals 0

    .line 128
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->getSetter()Lkotlin/jvm/functions/Function1;

    move-result-object p0

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$oN8i_dARB3CxVsO-sZu-V-RlNAI(Lkotlin/reflect/KMutableProperty0;)Z
    .locals 0

    .line 84
    invoke-interface {p0}, Lkotlin/reflect/KProperty0;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0
.end method

.method public constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;IILjava/lang/CharSequence;Ljava/lang/String;Z)V
    .locals 0
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 24
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    .line 25
    iput p2, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->id:I

    .line 26
    iput p3, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->titleRes:I

    .line 27
    iput-object p4, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->title:Ljava/lang/CharSequence;

    .line 28
    iput-object p5, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->linkAlias:Ljava/lang/String;

    .line 29
    iput-boolean p6, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->searchable:Z

    .line 41
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;Z)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
            "Ljava/lang/Enum<",
            "*>;I",
            "Ljava/lang/CharSequence;",
            "Ljava/lang/String;",
            "Z)V"
        }
    .end annotation

    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 39
    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    add-int/lit8 v2, p2, 0x1

    move-object v0, p0

    move-object v1, p1

    move v3, p3

    move-object v4, p4

    move-object v5, p5

    move v6, p6

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;IILjava/lang/CharSequence;Ljava/lang/String;Z)V

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 7

    and-int/lit8 p8, p7, 0x4

    if-eqz p8, :cond_0

    const/4 p3, 0x0

    :cond_0
    move v3, p3

    and-int/lit8 p3, p7, 0x8

    const/4 p8, 0x0

    if-eqz p3, :cond_1

    move-object v4, p8

    goto :goto_0

    :cond_1
    move-object v4, p4

    :goto_0
    and-int/lit8 p3, p7, 0x10

    if-eqz p3, :cond_2

    move-object v5, p8

    goto :goto_1

    :cond_2
    move-object v5, p5

    :goto_1
    and-int/lit8 p3, p7, 0x20

    if-eqz p3, :cond_3

    const/4 p6, 0x1

    :cond_3
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v6, p6

    .line 31
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;Z)V

    return-void
.end method

.method private final add(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 1

    .line 132
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public static synthetic add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 0

    and-int/lit8 p6, p5, 0x2

    if-eqz p6, :cond_0

    const/4 p2, 0x0

    :cond_0
    and-int/lit8 p5, p5, 0x8

    if-eqz p5, :cond_1

    const/4 p4, 0x0

    .line 56
    :cond_1
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic addIf$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 7

    and-int/lit8 p8, p7, 0x4

    if-eqz p8, :cond_0

    const/4 p3, 0x0

    :cond_0
    move v3, p3

    and-int/lit8 p3, p7, 0x20

    if-eqz p3, :cond_1

    const/4 p6, 0x0

    :cond_1
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v4, p4

    move-object v5, p5

    move-object v6, p6

    .line 86
    invoke-virtual/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->addIf(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic addIf$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 6

    and-int/lit8 p7, p6, 0x4

    if-eqz p7, :cond_0

    const/4 p3, 0x0

    :cond_0
    move v3, p3

    and-int/lit8 p3, p6, 0x10

    if-eqz p3, :cond_1

    const/4 p5, 0x0

    :cond_1
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v4, p4

    move-object v5, p5

    .line 78
    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/SwitchGroup;->addIf(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method private final count(Z)I
    .locals 3

    .line 150
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    check-cast p0, Ljava/lang/Iterable;

    .line 1834
    instance-of v0, p0, Ljava/util/Collection;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    move-object v0, p0

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    return v1

    .line 1836
    :cond_0
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    .line 150
    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->isVisible()Z

    move-result v2

    if-eqz v2, :cond_1

    if-eqz p1, :cond_2

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->getGetter()Lkotlin/jvm/functions/Function0;

    move-result-object v0

    invoke-interface {v0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_2
    add-int/lit8 v1, v1, 0x1

    if-gez v1, :cond_1

    .line 1836
    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwCountOverflow()V

    goto :goto_0

    :cond_3
    return v1
.end method

.method private final onSwitchClick(Landroid/view/View;)V
    .locals 5

    .line 135
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    check-cast p1, Lorg/telegram/ui/Cells/TextCheckCell2;

    iget p1, p1, Lorg/telegram/ui/Cells/TextCheckCell2;->id:I

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->findItemByItemId(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p1

    .line 136
    iget-boolean v0, p1, Lorg/telegram/ui/Components/UItem;->checked:Z

    const/4 v1, 0x1

    xor-int/2addr v0, v1

    .line 138
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    .line 139
    invoke-virtual {v3}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->isVisible()Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-virtual {v3}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->getSetter()Lkotlin/jvm/functions/Function1;

    move-result-object v3

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    invoke-interface {v3, v4}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 142
    :cond_1
    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    .line 143
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 144
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged:Lkotlin/jvm/functions/Function0;

    if-eqz p0, :cond_2

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    :cond_2
    return-void
.end method

.method private final title()Ljava/lang/CharSequence;
    .locals 1

    .line 147
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->title:Ljava/lang/CharSequence;

    if-nez v0, :cond_0

    iget p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->titleRes:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    return-object v0
.end method


# virtual methods
.method public final add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Enum<",
            "*>;I",
            "Ljava/util/function/BooleanSupplier;",
            "Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;",
            "Ljava/lang/CharSequence;",
            ")",
            "Lcom/exteragram/messenger/preferences/SwitchGroup;"
        }
    .end annotation

    .line 69
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 v1, p1, 0x1

    new-instance v5, Lcom/exteragram/messenger/preferences/SwitchGroup$add$5;

    invoke-direct {v5, p3}, Lcom/exteragram/messenger/preferences/SwitchGroup$add$5;-><init>(Ljava/lang/Object;)V

    new-instance v6, Lcom/exteragram/messenger/preferences/SwitchGroup$add$6;

    invoke-direct {v6, p4}, Lcom/exteragram/messenger/preferences/SwitchGroup$add$6;-><init>(Ljava/lang/Object;)V

    const/4 v4, 0x0

    move v2, p2

    move-object v3, p5

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;-><init>(IILjava/lang/CharSequence;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method public final add(Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Enum<",
            "*>;I",
            "Lkotlin/reflect/KMutableProperty0<",
            "Ljava/lang/Boolean;",
            ">;",
            "Ljava/lang/CharSequence;",
            ")",
            "Lcom/exteragram/messenger/preferences/SwitchGroup;"
        }
    .end annotation

    .line 61
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    add-int/lit8 v1, p1, 0x1

    new-instance v5, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda0;

    invoke-direct {v5, p3}, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda0;-><init>(Lkotlin/reflect/KMutableProperty0;)V

    new-instance v6, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda1;

    invoke-direct {v6, p3}, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda1;-><init>(Lkotlin/reflect/KMutableProperty0;)V

    const/4 v4, 0x0

    move v2, p2

    move-object v3, p4

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;-><init>(IILjava/lang/CharSequence;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method public final addIf(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/function/BooleanSupplier;",
            "Ljava/lang/Enum<",
            "*>;I",
            "Ljava/util/function/BooleanSupplier;",
            "Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;",
            "Ljava/lang/CharSequence;",
            ")",
            "Lcom/exteragram/messenger/preferences/SwitchGroup;"
        }
    .end annotation

    .line 93
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    add-int/lit8 v1, p2, 0x1

    new-instance v4, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$7;

    invoke-direct {v4, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$7;-><init>(Ljava/lang/Object;)V

    new-instance v5, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$8;

    invoke-direct {v5, p4}, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$8;-><init>(Ljava/lang/Object;)V

    new-instance v6, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$9;

    invoke-direct {v6, p5}, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$9;-><init>(Ljava/lang/Object;)V

    move v2, p3

    move-object v3, p6

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;-><init>(IILjava/lang/CharSequence;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method public final addIf(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/function/BooleanSupplier;",
            "Ljava/lang/Enum<",
            "*>;I",
            "Lkotlin/reflect/KMutableProperty0<",
            "Ljava/lang/Boolean;",
            ">;",
            "Ljava/lang/CharSequence;",
            ")",
            "Lcom/exteragram/messenger/preferences/SwitchGroup;"
        }
    .end annotation

    .line 84
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    add-int/lit8 v1, p2, 0x1

    new-instance v4, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$4;

    invoke-direct {v4, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup$addIf$4;-><init>(Ljava/lang/Object;)V

    new-instance v5, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda2;

    invoke-direct {v5, p4}, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda2;-><init>(Lkotlin/reflect/KMutableProperty0;)V

    new-instance v6, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda3;

    invoke-direct {v6, p4}, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda3;-><init>(Lkotlin/reflect/KMutableProperty0;)V

    move v2, p3

    move-object v3, p5

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;-><init>(IILjava/lang/CharSequence;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-result-object p0

    return-object p0
.end method

.method public final fill(Ljava/util/ArrayList;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;)V"
        }
    .end annotation

    const/4 v0, 0x1

    .line 96
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->count(Z)I

    move-result v1

    const/4 v2, 0x0

    .line 97
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/preferences/SwitchGroup;->count(Z)I

    move-result v3

    .line 99
    iget v4, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->id:I

    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/SwitchGroup;->title()Ljava/lang/CharSequence;

    move-result-object v5

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    filled-new-array {v6, v3}, [Ljava/lang/Object;

    move-result-object v3

    const/4 v6, 0x2

    invoke-static {v3, v6}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v3

    const-string v6, "%d/%d"

    invoke-static {v6, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    .line 98
    new-instance v6, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda4;

    invoke-direct {v6, p0}, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/preferences/SwitchGroup;)V

    invoke-static {v4, v5, v3, v6}, Lorg/telegram/ui/Components/UItem;->asExteraExpandableSwitch(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/view/View$OnClickListener;)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    if-lez v1, :cond_0

    move v2, v0

    .line 101
    :cond_0
    invoke-virtual {v3, v2}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    .line 102
    iget-boolean v1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->expanded:Z

    xor-int/2addr v0, v1

    invoke-virtual {v3, v0}, Lorg/telegram/ui/Components/UItem;->setCollapsed(Z)Lorg/telegram/ui/Components/UItem;

    .line 103
    iget-boolean v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->searchable:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {v3, v0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    .line 104
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->linkAlias:Ljava/lang/String;

    if-eqz v0, :cond_2

    iget-object v1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {v3, v0, v1}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    .line 106
    :cond_2
    invoke-virtual {p1, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 108
    iget-boolean v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->expanded:Z

    if-nez v0, :cond_3

    goto :goto_1

    .line 109
    :cond_3
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    .line 110
    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->isVisible()Z

    move-result v1

    if-nez v1, :cond_4

    goto :goto_0

    .line 113
    :cond_4
    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->getId()I

    move-result v1

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->text()Ljava/lang/CharSequence;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asRoundCheckbox(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 114
    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->getGetter()Lkotlin/jvm/functions/Function0;

    move-result-object v0

    invoke-interface {v0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/Components/UItem;->pad()Lorg/telegram/ui/Components/UItem;

    .line 116
    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_5
    :goto_1
    return-void
.end method

.method public final markNew(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 1

    .line 48
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->last(Ljava/util/List;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->setNewFeatureAlias(Ljava/lang/String;)V

    return-object p0
.end method

.method public final onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)",
            "Lcom/exteragram/messenger/preferences/SwitchGroup;"
        }
    .end annotation

    .line 46
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged:Lkotlin/jvm/functions/Function0;

    return-object p0
.end method

.method public final onClick(Lorg/telegram/ui/Components/UItem;)V
    .locals 4

    .line 121
    iget v0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    iget v1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->id:I

    if-ne v0, v1, :cond_0

    .line 122
    iget-boolean v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->expanded:Z

    xor-int/lit8 v1, v0, 0x1

    iput-boolean v1, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->expanded:Z

    .line 123
    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/UItem;->setCollapsed(Z)Lorg/telegram/ui/Components/UItem;

    .line 124
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    return-void

    .line 127
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->children:Ljava/util/List;

    check-cast v0, Ljava/lang/Iterable;

    .line 296
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    .line 127
    invoke-virtual {v2}, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;->getId()I

    move-result v2

    iget v3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-ne v2, v3, :cond_1

    goto :goto_0

    :cond_2
    const/4 v1, 0x0

    :goto_0
    check-cast v1, Lcom/exteragram/messenger/preferences/SwitchGroup$Child;

    if-nez v1, :cond_3

    goto :goto_1

    .line 128
    :cond_3
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->fragment:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    new-instance v2, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda5;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)V

    invoke-virtual {v0, p1, v2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 129
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged:Lkotlin/jvm/functions/Function0;

    if-eqz p0, :cond_4

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    :cond_4
    :goto_1
    return-void
.end method
