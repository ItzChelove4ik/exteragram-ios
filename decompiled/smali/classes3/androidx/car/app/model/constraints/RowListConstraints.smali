.class public final Landroidx/car/app/model/constraints/RowListConstraints;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/car/app/model/constraints/RowListConstraints$Builder;
    }
.end annotation


# static fields
.field public static final MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE:Landroidx/car/app/model/constraints/RowListConstraints;

.field public static final ROW_LIST_CONSTRAINTS_CONSERVATIVE:Landroidx/car/app/model/constraints/RowListConstraints;

.field public static final ROW_LIST_CONSTRAINTS_FULL_LIST:Landroidx/car/app/model/constraints/RowListConstraints;

.field public static final ROW_LIST_CONSTRAINTS_PANE:Landroidx/car/app/model/constraints/RowListConstraints;

.field public static final ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW:Landroidx/car/app/model/constraints/RowListConstraints;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation
.end field

.field public static final ROW_LIST_CONSTRAINTS_SIMPLE:Landroidx/car/app/model/constraints/RowListConstraints;


# instance fields
.field private final mAllowSelectableLists:Z

.field private final mMaxActions:I

.field private final mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 47
    new-instance v0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    invoke-direct {v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;-><init>()V

    const/4 v1, 0x0

    .line 49
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v0

    sget-object v2, Landroidx/car/app/model/constraints/RowConstraints;->ROW_CONSTRAINTS_CONSERVATIVE:Landroidx/car/app/model/constraints/RowConstraints;

    .line 50
    invoke-virtual {v0, v2}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v0

    .line 51
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setAllowSelectableLists(Z)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v0

    .line 52
    invoke-virtual {v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->build()Landroidx/car/app/model/constraints/RowListConstraints;

    move-result-object v0

    sput-object v0, Landroidx/car/app/model/constraints/RowListConstraints;->ROW_LIST_CONSTRAINTS_CONSERVATIVE:Landroidx/car/app/model/constraints/RowListConstraints;

    .line 56
    new-instance v2, Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    invoke-direct {v2, v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/RowListConstraints;)V

    const/4 v3, 0x2

    .line 58
    invoke-virtual {v2, v3}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setMaxActions(I)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v2

    sget-object v3, Landroidx/car/app/model/constraints/RowConstraints;->ROW_CONSTRAINTS_PANE:Landroidx/car/app/model/constraints/RowConstraints;

    .line 59
    invoke-virtual {v2, v3}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v2

    .line 60
    invoke-virtual {v2, v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setAllowSelectableLists(Z)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v1

    .line 61
    invoke-virtual {v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->build()Landroidx/car/app/model/constraints/RowListConstraints;

    move-result-object v1

    sput-object v1, Landroidx/car/app/model/constraints/RowListConstraints;->ROW_LIST_CONSTRAINTS_PANE:Landroidx/car/app/model/constraints/RowListConstraints;

    .line 65
    new-instance v1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    invoke-direct {v1, v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/RowListConstraints;)V

    sget-object v2, Landroidx/car/app/model/constraints/RowConstraints;->ROW_CONSTRAINTS_SIMPLE:Landroidx/car/app/model/constraints/RowConstraints;

    .line 67
    invoke-virtual {v1, v2}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v1

    .line 68
    invoke-virtual {v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->build()Landroidx/car/app/model/constraints/RowListConstraints;

    move-result-object v1

    sput-object v1, Landroidx/car/app/model/constraints/RowListConstraints;->ROW_LIST_CONSTRAINTS_SIMPLE:Landroidx/car/app/model/constraints/RowListConstraints;

    .line 82
    new-instance v1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    invoke-direct {v1, v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/RowListConstraints;)V

    .line 84
    invoke-virtual {v1, v2}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v1

    const/4 v3, 0x1

    .line 85
    invoke-virtual {v1, v3}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setAllowSelectableLists(Z)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v1

    .line 86
    invoke-virtual {v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->build()Landroidx/car/app/model/constraints/RowListConstraints;

    move-result-object v1

    sput-object v1, Landroidx/car/app/model/constraints/RowListConstraints;->ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW:Landroidx/car/app/model/constraints/RowListConstraints;

    .line 90
    new-instance v1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    invoke-direct {v1, v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/RowListConstraints;)V

    .line 92
    invoke-virtual {v1, v2}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v1

    .line 93
    invoke-virtual {v1, v3}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setAllowSelectableLists(Z)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v1

    .line 94
    invoke-virtual {v1}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->build()Landroidx/car/app/model/constraints/RowListConstraints;

    move-result-object v1

    sput-object v1, Landroidx/car/app/model/constraints/RowListConstraints;->MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE:Landroidx/car/app/model/constraints/RowListConstraints;

    .line 99
    new-instance v1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    invoke-direct {v1, v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;-><init>(Landroidx/car/app/model/constraints/RowListConstraints;)V

    sget-object v0, Landroidx/car/app/model/constraints/RowConstraints;->ROW_CONSTRAINTS_FULL_LIST:Landroidx/car/app/model/constraints/RowConstraints;

    .line 101
    invoke-virtual {v1, v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v0

    .line 102
    invoke-virtual {v0, v3}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->setAllowSelectableLists(Z)Landroidx/car/app/model/constraints/RowListConstraints$Builder;

    move-result-object v0

    .line 103
    invoke-virtual {v0}, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->build()Landroidx/car/app/model/constraints/RowListConstraints;

    move-result-object v0

    sput-object v0, Landroidx/car/app/model/constraints/RowListConstraints;->ROW_LIST_CONSTRAINTS_FULL_LIST:Landroidx/car/app/model/constraints/RowListConstraints;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/model/constraints/RowListConstraints$Builder;)V
    .locals 1

    .line 192
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 193
    iget v0, p1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mMaxActions:I

    iput v0, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mMaxActions:I

    .line 194
    iget-object v0, p1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    iput-object v0, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    .line 195
    iget-boolean p1, p1, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mAllowSelectableLists:Z

    iput-boolean p1, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mAllowSelectableLists:Z

    return-void
.end method

.method private validateRows(Ljava/util/List;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Landroidx/car/app/model/Item;",
            ">;)V"
        }
    .end annotation

    .line 178
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/car/app/model/Item;

    .line 179
    instance-of v1, v0, Landroidx/car/app/model/Row;

    if-eqz v1, :cond_0

    .line 180
    iget-object v1, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    check-cast v0, Landroidx/car/app/model/Row;

    invoke-virtual {v1, v0}, Landroidx/car/app/model/constraints/RowConstraints;->validateOrThrow(Landroidx/car/app/model/Row;)V

    goto :goto_0

    .line 181
    :cond_0
    instance-of v1, v0, Landroidx/car/app/messaging/model/ConversationItem;

    if-eqz v1, :cond_1

    goto :goto_0

    .line 186
    :cond_1
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    .line 184
    const-string p1, "Unsupported item type: %s"

    invoke-static {p1, p0}, Lcom/google/protobuf/FieldSet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[Ljava/lang/Object;)V

    :cond_2
    return-void
.end method


# virtual methods
.method public getMaxActions()I
    .locals 0

    .line 111
    iget p0, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mMaxActions:I

    return p0
.end method

.method public getRowConstraints()Landroidx/car/app/model/constraints/RowConstraints;
    .locals 0

    .line 117
    iget-object p0, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    return-object p0
.end method

.method public isAllowSelectableLists()Z
    .locals 0

    .line 122
    iget-boolean p0, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mAllowSelectableLists:Z

    return p0
.end method

.method public validateOrThrow(Landroidx/car/app/model/ItemList;)V
    .locals 1

    .line 132
    invoke-virtual {p1}, Landroidx/car/app/model/ItemList;->getOnSelectedDelegate()Landroidx/car/app/model/OnSelectedDelegate;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mAllowSelectableLists:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 133
    :cond_0
    const-string p0, "Selectable lists are not allowed"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 136
    :cond_1
    :goto_0
    invoke-virtual {p1}, Landroidx/car/app/model/ItemList;->getItems()Ljava/util/List;

    move-result-object p1

    invoke-direct {p0, p1}, Landroidx/car/app/model/constraints/RowListConstraints;->validateRows(Ljava/util/List;)V

    return-void
.end method

.method public validateOrThrow(Ljava/util/List;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/car/app/model/SectionedItemList;",
            ">;)V"
        }
    .end annotation

    .line 147
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 149
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/car/app/model/SectionedItemList;

    .line 150
    invoke-virtual {v1}, Landroidx/car/app/model/SectionedItemList;->getItemList()Landroidx/car/app/model/ItemList;

    move-result-object v1

    .line 151
    invoke-virtual {v1}, Landroidx/car/app/model/ItemList;->getOnSelectedDelegate()Landroidx/car/app/model/OnSelectedDelegate;

    move-result-object v2

    if-eqz v2, :cond_1

    iget-boolean v2, p0, Landroidx/car/app/model/constraints/RowListConstraints;->mAllowSelectableLists:Z

    if-eqz v2, :cond_0

    goto :goto_1

    .line 152
    :cond_0
    const-string p0, "Selectable lists are not allowed"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 155
    :cond_1
    :goto_1
    invoke-virtual {v1}, Landroidx/car/app/model/ItemList;->getItems()Ljava/util/List;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    goto :goto_0

    .line 158
    :cond_2
    invoke-direct {p0, v0}, Landroidx/car/app/model/constraints/RowListConstraints;->validateRows(Ljava/util/List;)V

    return-void
.end method
