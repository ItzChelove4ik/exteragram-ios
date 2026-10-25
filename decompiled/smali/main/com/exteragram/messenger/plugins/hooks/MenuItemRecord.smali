.class public final Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u000b\n\u0002\u0008\u000c\n\u0002\u0010\u0008\n\u0002\u0008\t\n\u0002\u0010\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010$\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\"2\u0008\u0010$\u001a\u0004\u0018\u00010\u0001J\u0013\u0010%\u001a\u00020\u000b2\u0008\u0010&\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0008\u0010\'\u001a\u00020\u0018H\u0016J\u001c\u0010.\u001a\u00020\u000b2\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010/R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000c\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\tR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000f\u0010\tR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0011\u0010\tR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0016\u0010\tR\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001c\u0010\tR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001e\u0010\tR\u0011\u0010\u001f\u001a\u00020\u0018\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008 \u0010\u001aR\u001d\u0010(\u001a\u0004\u0018\u00010)8BX\u0082\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008,\u0010-\u001a\u0004\u0008*\u0010+\u00a8\u00060"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;",
        "",
        "pluginId",
        "",
        "pyData",
        "Lcom/chaquo/python/PyObject;",
        "<init>",
        "(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V",
        "getPluginId",
        "()Ljava/lang/String;",
        "removed",
        "",
        "itemId",
        "getItemId",
        "menuType",
        "getMenuType",
        "text",
        "getText",
        "onClickCallback",
        "getOnClickCallback",
        "()Lcom/chaquo/python/PyObject;",
        "iconName",
        "getIconName",
        "iconResId",
        "",
        "getIconResId",
        "()I",
        "subtext",
        "getSubtext",
        "conditionString",
        "getConditionString",
        "priority",
        "getPriority",
        "markRemoved",
        "",
        "executeClick",
        "contextData",
        "equals",
        "other",
        "hashCode",
        "compiledCondition",
        "Ljava/io/Serializable;",
        "getCompiledCondition",
        "()Ljava/io/Serializable;",
        "compiledCondition$delegate",
        "Lkotlin/Lazy;",
        "checkCondition",
        "",
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
        "SMAP\nMenuItemRecord.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MenuItemRecord.kt\ncom/exteragram/messenger/plugins/hooks/MenuItemRecord\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,143:1\n1#2:144\n*E\n"
    }
.end annotation


# instance fields
.field private final compiledCondition$delegate:Lkotlin/Lazy;

.field private final conditionString:Ljava/lang/String;

.field private final iconName:Ljava/lang/String;

.field private final iconResId:I

.field private final itemId:Ljava/lang/String;

.field private final menuType:Ljava/lang/String;

.field private final onClickCallback:Lcom/chaquo/python/PyObject;

.field private final pluginId:Ljava/lang/String;

.field private final priority:I

.field private volatile removed:Z

.field private final subtext:Ljava/lang/String;

.field private final text:Ljava/lang/String;


# direct methods
.method public static $r8$lambda$WYcNbFqpjFZmRSNFMesyFPF1vow(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;)Ljava/io/Serializable;
    .locals 2

    .line 118
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->conditionString:Ljava/lang/String;

    const/4 v0, 0x0

    if-eqz p0, :cond_1

    .line 119
    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 123
    :cond_0
    :try_start_0
    invoke-static {p0}, Lorg/mvel2/MVEL;->compileExpression(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 125
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    return-object v0
.end method

.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V
    .locals 4

    .line 26
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 27
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->pluginId:Ljava/lang/String;

    .line 36
    const-string/jumbo p1, "menu_type"

    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-static {p2, p1, v0, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->menuType:Ljava/lang/String;

    .line 39
    const-string/jumbo p1, "text"

    invoke-static {p2, p1, v0, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->text:Ljava/lang/String;

    .line 42
    const-string/jumbo p1, "on_click"

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string v2, "get"

    invoke-virtual {p2, v2, p1}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->onClickCallback:Lcom/chaquo/python/PyObject;

    .line 56
    const-string/jumbo p1, "item_id"

    invoke-static {p2, p1, v0, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 57
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-nez v2, :cond_1

    :cond_0
    move-object p1, v0

    :cond_1
    if-nez p1, :cond_2

    invoke-static {}, Ljava/util/UUID;->randomUUID()Ljava/util/UUID;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/UUID;->toString()Ljava/lang/String;

    move-result-object p1

    :cond_2
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->itemId:Ljava/lang/String;

    .line 60
    const-string/jumbo p1, "icon"

    invoke-static {p2, p1, v0, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object p1

    .line 59
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->iconName:Ljava/lang/String;

    .line 62
    const-string/jumbo v2, "subtext"

    invoke-static {p2, v2, v0, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v2

    .line 61
    iput-object v2, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->subtext:Ljava/lang/String;

    .line 65
    const-string v2, "condition"

    .line 63
    invoke-static {p2, v2, v0, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v2

    iput-object v2, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->conditionString:Ljava/lang/String;

    .line 70
    const-string/jumbo v2, "priority"

    const/4 v3, 0x0

    invoke-static {p2, v2, v3, v1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getInt(Lcom/chaquo/python/PyObject;Ljava/lang/String;IZ)I

    move-result p2

    .line 69
    iput p2, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->priority:I

    .line 73
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p2

    if-nez p2, :cond_3

    .line 75
    :try_start_0
    sget-object p2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 77
    invoke-virtual {p2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const-string v2, "drawable"

    invoke-virtual {p2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v1, p1, v2, p2}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 81
    :catch_0
    :cond_3
    iput v3, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->iconResId:I

    .line 83
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->menuType:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_4

    iget-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->text:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_4

    iget-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->onClickCallback:Lcom/chaquo/python/PyObject;

    if-eqz p1, :cond_4

    .line 117
    sget-object p1, Lkotlin/LazyThreadSafetyMode;->PUBLICATION:Lkotlin/LazyThreadSafetyMode;

    new-instance p2, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord$$ExternalSyntheticLambda0;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;)V

    invoke-static {p1, p2}, Lkotlin/LazyKt;->lazy(Lkotlin/LazyThreadSafetyMode;Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->compiledCondition$delegate:Lkotlin/Lazy;

    return-void

    .line 84
    :cond_4
    const-string p0, "MenuItemRecord missing essential fields: menuType, text, or onClickCallback."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v0
.end method

.method private final getCompiledCondition()Ljava/io/Serializable;
    .locals 0

    .line 117
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->compiledCondition$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/Serializable;

    return-object p0
.end method


# virtual methods
.method public final checkCondition(Ljava/util/Map;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "+",
            "Ljava/lang/Object;",
            ">;)Z"
        }
    .end annotation

    .line 132
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->conditionString:Ljava/lang/String;

    if-eqz v0, :cond_4

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-nez p1, :cond_1

    goto :goto_0

    .line 135
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getCompiledCondition()Ljava/io/Serializable;

    move-result-object p0

    const/4 v0, 0x0

    if-nez p0, :cond_2

    return v0

    .line 137
    :cond_2
    :try_start_0
    sget-object v1, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    invoke-static {p0, p1, v1}, Lorg/mvel2/MVEL;->executeExpression(Ljava/lang/Object;Ljava/util/Map;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Boolean;

    if-eqz p0, :cond_3

    .line 138
    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    :cond_3
    return v0

    :cond_4
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    const/4 v1, 0x0

    if-eqz p1, :cond_2

    .line 105
    const-class v2, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    invoke-static {v2, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_1

    goto :goto_0

    .line 107
    :cond_1
    check-cast p1, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    .line 108
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->itemId:Ljava/lang/String;

    iget-object v3, p1, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->itemId:Ljava/lang/String;

    invoke-static {v2, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->pluginId:Ljava/lang/String;

    iget-object p1, p1, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->pluginId:Ljava/lang/String;

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_2

    return v0

    :cond_2
    :goto_0
    return v1
.end method

.method public final executeClick(Ljava/lang/Object;)V
    .locals 2

    .line 93
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->removed:Z

    if-nez v0, :cond_1

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->pluginId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 97
    :cond_0
    :try_start_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->onClickCallback:Lcom/chaquo/python/PyObject;

    if-eqz p0, :cond_1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 99
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public final getConditionString()Ljava/lang/String;
    .locals 0

    .line 50
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->conditionString:Ljava/lang/String;

    return-object p0
.end method

.method public final getIconName()Ljava/lang/String;
    .locals 0

    .line 44
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->iconName:Ljava/lang/String;

    return-object p0
.end method

.method public final getIconResId()I
    .locals 0

    .line 46
    iget p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->iconResId:I

    return p0
.end method

.method public final getItemId()Ljava/lang/String;
    .locals 0

    .line 33
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->itemId:Ljava/lang/String;

    return-object p0
.end method

.method public final getMenuType()Ljava/lang/String;
    .locals 0

    .line 35
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->menuType:Ljava/lang/String;

    return-object p0
.end method

.method public final getOnClickCallback()Lcom/chaquo/python/PyObject;
    .locals 0

    .line 41
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->onClickCallback:Lcom/chaquo/python/PyObject;

    return-object p0
.end method

.method public final getPluginId()Ljava/lang/String;
    .locals 0

    .line 27
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->pluginId:Ljava/lang/String;

    return-object p0
.end method

.method public final getPriority()I
    .locals 0

    .line 52
    iget p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->priority:I

    return p0
.end method

.method public final getSubtext()Ljava/lang/String;
    .locals 0

    .line 48
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->subtext:Ljava/lang/String;

    return-object p0
.end method

.method public final getText()Ljava/lang/String;
    .locals 0

    .line 38
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->text:Ljava/lang/String;

    return-object p0
.end method

.method public hashCode()I
    .locals 1

    .line 112
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->itemId:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    .line 113
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->pluginId:Ljava/lang/String;

    invoke-virtual {p0}, Ljava/lang/String;->hashCode()I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public final markRemoved()V
    .locals 1

    const/4 v0, 0x1

    .line 89
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->removed:Z

    return-void
.end method
