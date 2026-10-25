.class public Landroidx/car/app/model/constraints/TabsConstraints;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/car/app/model/constraints/TabsConstraints$Builder;
    }
.end annotation


# static fields
.field public static final DEFAULT:Landroidx/car/app/model/constraints/TabsConstraints;


# instance fields
.field private final mMaxTabs:I

.field private final mMinTabs:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 39
    new-instance v0, Landroidx/car/app/model/constraints/TabsConstraints$Builder;

    invoke-direct {v0}, Landroidx/car/app/model/constraints/TabsConstraints$Builder;-><init>()V

    const/4 v1, 0x4

    .line 41
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/TabsConstraints$Builder;->setMaxTabs(I)Landroidx/car/app/model/constraints/TabsConstraints$Builder;

    move-result-object v0

    const/4 v1, 0x2

    .line 42
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/TabsConstraints$Builder;->setMinTabs(I)Landroidx/car/app/model/constraints/TabsConstraints$Builder;

    move-result-object v0

    .line 43
    invoke-virtual {v0}, Landroidx/car/app/model/constraints/TabsConstraints$Builder;->build()Landroidx/car/app/model/constraints/TabsConstraints;

    move-result-object v0

    sput-object v0, Landroidx/car/app/model/constraints/TabsConstraints;->DEFAULT:Landroidx/car/app/model/constraints/TabsConstraints;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/model/constraints/TabsConstraints$Builder;)V
    .locals 1

    .line 86
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 87
    iget v0, p1, Landroidx/car/app/model/constraints/TabsConstraints$Builder;->mMaxTabs:I

    iput v0, p0, Landroidx/car/app/model/constraints/TabsConstraints;->mMaxTabs:I

    .line 88
    iget p1, p1, Landroidx/car/app/model/constraints/TabsConstraints$Builder;->mMinTabs:I

    iput p1, p0, Landroidx/car/app/model/constraints/TabsConstraints;->mMinTabs:I

    return-void
.end method


# virtual methods
.method public validateOrThrow(Ljava/util/List;Ljava/lang/String;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/car/app/model/Tab;",
            ">;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    .line 54
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    iget v1, p0, Landroidx/car/app/model/constraints/TabsConstraints;->mMinTabs:I

    if-lt v0, v1, :cond_5

    .line 60
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    iget v1, p0, Landroidx/car/app/model/constraints/TabsConstraints;->mMaxTabs:I

    if-gt v0, v1, :cond_4

    .line 66
    new-instance p0, Ljava/util/HashSet;

    invoke-direct {p0}, Ljava/util/HashSet;-><init>()V

    .line 67
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    const/4 v0, 0x0

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/car/app/model/Tab;

    .line 68
    invoke-virtual {v1}, Landroidx/car/app/model/Tab;->getContentId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p2, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    const/4 v0, 0x1

    .line 71
    :cond_0
    invoke-virtual {v1}, Landroidx/car/app/model/Tab;->getContentId()Ljava/lang/String;

    move-result-object v2

    invoke-interface {p0, v2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    goto :goto_0

    .line 73
    :cond_1
    invoke-virtual {v1}, Landroidx/car/app/model/Tab;->getContentId()Ljava/lang/String;

    move-result-object p0

    const-string p1, ". Each tab must have a unique ID."

    const-string p2, "Found duplicate tab ID: "

    invoke-static {p2, p0, p1}, Lcom/sun/jna/Native$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    :cond_2
    if-eqz v0, :cond_3

    return-void

    .line 80
    :cond_3
    const-string p0, "There is no tab with content ID matching the active tab content ID set on the template"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 61
    :cond_4
    iget p0, p0, Landroidx/car/app/model/constraints/TabsConstraints;->mMaxTabs:I

    const-string p2, " tabs added, found "

    .line 62
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    const-string v0, "There cannot be more than "

    invoke-static {v0, p0, p2, p1}, Lcom/android/dex/Dex$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;ILjava/lang/Object;I)V

    return-void

    .line 55
    :cond_5
    iget p0, p0, Landroidx/car/app/model/constraints/TabsConstraints;->mMinTabs:I

    const-string p2, " tab(s) added, but only found "

    .line 57
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    const-string v0, "There must be at least "

    invoke-static {v0, p0, p2, p1}, Lcom/android/dex/Dex$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;ILjava/lang/Object;I)V

    return-void
.end method
