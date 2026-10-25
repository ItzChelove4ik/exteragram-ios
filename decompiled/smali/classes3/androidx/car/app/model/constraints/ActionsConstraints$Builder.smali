.class public final Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/constraints/ActionsConstraints;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field final mAllowedActionTypes:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field final mDisallowedActionTypes:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field mMaxActions:I

.field mMaxCustomTitles:I

.field mMaxPrimaryActions:I

.field mOnClickListenerAllowed:Z

.field mRequireActionBackgroundColor:Z

.field mRequireActionIcons:Z

.field final mRequiredActionTypes:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field mRestrictBackgroundColorToPrimaryAction:Z

.field mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 417
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 404
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequiredActionTypes:Ljava/util/Set;

    .line 405
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mDisallowedActionTypes:Ljava/util/Set;

    .line 406
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mAllowedActionTypes:Ljava/util/Set;

    const v0, 0x7fffffff

    .line 407
    iput v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxActions:I

    const/4 v0, 0x0

    .line 408
    iput v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxPrimaryActions:I

    .line 413
    iput-boolean v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRestrictBackgroundColorToPrimaryAction:Z

    .line 414
    sget-object v0, Landroidx/car/app/model/constraints/CarTextConstraints;->UNCONSTRAINED:Landroidx/car/app/model/constraints/CarTextConstraints;

    iput-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/model/constraints/ActionsConstraints;)V
    .locals 4

    .line 426
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 404
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequiredActionTypes:Ljava/util/Set;

    .line 405
    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    iput-object v1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mDisallowedActionTypes:Ljava/util/Set;

    .line 406
    new-instance v2, Ljava/util/HashSet;

    invoke-direct {v2}, Ljava/util/HashSet;-><init>()V

    iput-object v2, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mAllowedActionTypes:Ljava/util/Set;

    const v3, 0x7fffffff

    .line 407
    iput v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxActions:I

    const/4 v3, 0x0

    .line 408
    iput v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxPrimaryActions:I

    .line 413
    iput-boolean v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRestrictBackgroundColorToPrimaryAction:Z

    .line 414
    sget-object v3, Landroidx/car/app/model/constraints/CarTextConstraints;->UNCONSTRAINED:Landroidx/car/app/model/constraints/CarTextConstraints;

    iput-object v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 427
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 428
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getMaxActions()I

    move-result v3

    iput v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxActions:I

    .line 429
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getMaxPrimaryActions()I

    move-result v3

    iput v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxPrimaryActions:I

    .line 430
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getMaxCustomTitles()I

    move-result v3

    iput v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxCustomTitles:I

    .line 431
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getTitleTextConstraints()Landroidx/car/app/model/constraints/CarTextConstraints;

    move-result-object v3

    iput-object v3, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    .line 432
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getRequiredActionTypes()Ljava/util/Set;

    move-result-object v3

    invoke-interface {v0, v3}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    .line 433
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getDisallowedActionTypes()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v1, v0}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    .line 434
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->getAllowedActionTypes()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v2, v0}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    .line 435
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->areActionIconsRequired()Z

    move-result v0

    iput-boolean v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequireActionIcons:Z

    .line 436
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->isActionBackgroundColorRequired()Z

    move-result v0

    iput-boolean v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequireActionBackgroundColor:Z

    .line 437
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->isOnClickListenerAllowed()Z

    move-result v0

    iput-boolean v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mOnClickListenerAllowed:Z

    .line 439
    invoke-virtual {p1}, Landroidx/car/app/model/constraints/ActionsConstraints;->restrictBackgroundColorToPrimaryAction()Z

    move-result p1

    iput-boolean p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRestrictBackgroundColorToPrimaryAction:Z

    return-void
.end method


# virtual methods
.method public addAllowedActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 1

    .line 527
    iget-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mAllowedActionTypes:Ljava/util/Set;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public addRequiredActionType(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 1

    .line 513
    iget-object v0, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequiredActionTypes:Ljava/util/Set;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public build()Landroidx/car/app/model/constraints/ActionsConstraints;
    .locals 1

    .line 536
    new-instance v0, Landroidx/car/app/model/constraints/ActionsConstraints;

    invoke-direct {v0, p0}, Landroidx/car/app/model/constraints/ActionsConstraints;-><init>(Landroidx/car/app/model/constraints/ActionsConstraints$Builder;)V

    return-object v0
.end method

.method public setMaxActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 445
    iput p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxActions:I

    return-object p0
.end method

.method public setMaxCustomTitles(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 499
    iput p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxCustomTitles:I

    return-object p0
.end method

.method public setMaxPrimaryActions(I)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 492
    iput p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mMaxPrimaryActions:I

    return-object p0
.end method

.method public setOnClickListenerAllowed(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 475
    iput-boolean p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mOnClickListenerAllowed:Z

    return-object p0
.end method

.method public setRequireActionBackgroundColor(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 465
    iput-boolean p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequireActionBackgroundColor:Z

    return-object p0
.end method

.method public setRequireActionIcons(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 455
    iput-boolean p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRequireActionIcons:Z

    return-object p0
.end method

.method public setRestrictBackgroundColorToPrimaryAction(Z)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 485
    iput-boolean p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mRestrictBackgroundColorToPrimaryAction:Z

    return-object p0
.end method

.method public setTitleTextConstraints(Landroidx/car/app/model/constraints/CarTextConstraints;)Landroidx/car/app/model/constraints/ActionsConstraints$Builder;
    .locals 0

    .line 506
    iput-object p1, p0, Landroidx/car/app/model/constraints/ActionsConstraints$Builder;->mTitleTextConstraints:Landroidx/car/app/model/constraints/CarTextConstraints;

    return-object p0
.end method
