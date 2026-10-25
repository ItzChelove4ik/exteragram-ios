.class public final Landroidx/car/app/model/constraints/RowListConstraints$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/constraints/RowListConstraints;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field mAllowSelectableLists:Z

.field mMaxActions:I

.field mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 236
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 203
    sget-object v0, Landroidx/car/app/model/constraints/RowConstraints;->UNCONSTRAINED:Landroidx/car/app/model/constraints/RowConstraints;

    iput-object v0, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/model/constraints/RowListConstraints;)V
    .locals 1

    .line 244
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 203
    sget-object v0, Landroidx/car/app/model/constraints/RowConstraints;->UNCONSTRAINED:Landroidx/car/app/model/constraints/RowConstraints;

    iput-object v0, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    .line 245
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 246
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/RowListConstraints;->getMaxActions()I

    move-result v0

    iput v0, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mMaxActions:I

    .line 247
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/RowListConstraints;->getRowConstraints()Landroidx/car/app/model/constraints/RowConstraints;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    .line 248
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/RowListConstraints;->isAllowSelectableLists()Z

    move-result p1

    iput-boolean p1, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mAllowSelectableLists:Z

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/model/constraints/RowListConstraints;
    .locals 1

    .line 232
    new-instance v0, Landroidx/car/app/model/constraints/RowListConstraints;

    invoke-direct {v0, p0}, Landroidx/car/app/model/constraints/RowListConstraints;-><init>(Landroidx/car/app/model/constraints/RowListConstraints$Builder;)V

    return-object v0
.end method

.method public setAllowSelectableLists(Z)Landroidx/car/app/model/constraints/RowListConstraints$Builder;
    .locals 0

    .line 223
    iput-boolean p1, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mAllowSelectableLists:Z

    return-object p0
.end method

.method public setMaxActions(I)Landroidx/car/app/model/constraints/RowListConstraints$Builder;
    .locals 0

    .line 209
    iput p1, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mMaxActions:I

    return-object p0
.end method

.method public setRowConstraints(Landroidx/car/app/model/constraints/RowConstraints;)Landroidx/car/app/model/constraints/RowListConstraints$Builder;
    .locals 0

    .line 216
    iput-object p1, p0, Landroidx/car/app/model/constraints/RowListConstraints$Builder;->mRowConstraints:Landroidx/car/app/model/constraints/RowConstraints;

    return-object p0
.end method
