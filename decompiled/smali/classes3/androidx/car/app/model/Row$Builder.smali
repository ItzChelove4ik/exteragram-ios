.class public final Landroidx/car/app/model/Row$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/Row;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field final mActions:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/car/app/model/Action;",
            ">;"
        }
    .end annotation
.end field

.field mDecoration:I

.field mImage:Landroidx/car/app/model/CarIcon;

.field mIsBrowsable:Z

.field mIsEnabled:Z

.field mMetadata:Landroidx/car/app/model/Metadata;

.field mOnClickDelegate:Landroidx/car/app/model/OnClickDelegate;

.field mRowImageType:I

.field final mTexts:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/car/app/model/CarText;",
            ">;"
        }
    .end annotation
.end field

.field mTitle:Landroidx/car/app/model/CarText;

.field mToggle:Landroidx/car/app/model/Toggle;


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 694
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    .line 335
    iput-boolean v0, p0, Landroidx/car/app/model/Row$Builder;->mIsEnabled:Z

    .line 338
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Landroidx/car/app/model/Row$Builder;->mTexts:Ljava/util/List;

    .line 341
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Landroidx/car/app/model/Row$Builder;->mActions:Ljava/util/List;

    const/4 v1, -0x1

    .line 342
    iput v1, p0, Landroidx/car/app/model/Row$Builder;->mDecoration:I

    .line 347
    sget-object v1, Landroidx/car/app/model/Metadata;->EMPTY_METADATA:Landroidx/car/app/model/Metadata;

    iput-object v1, p0, Landroidx/car/app/model/Row$Builder;->mMetadata:Landroidx/car/app/model/Metadata;

    .line 349
    iput v0, p0, Landroidx/car/app/model/Row$Builder;->mRowImageType:I

    return-void
.end method


# virtual methods
.method public addText(Ljava/lang/CharSequence;)Landroidx/car/app/model/Row$Builder;
    .locals 2

    .line 463
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-object v0, p1

    check-cast v0, Ljava/lang/CharSequence;

    invoke-static {v0}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object v0

    .line 464
    sget-object v1, Landroidx/car/app/model/constraints/CarTextConstraints;->TEXT_WITH_COLORS_AND_ICON:Landroidx/car/app/model/constraints/CarTextConstraints;

    invoke-virtual {v1, v0}, Landroidx/car/app/model/constraints/CarTextConstraints;->validateOrThrow(Landroidx/car/app/model/CarText;)V

    .line 465
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mTexts:Ljava/util/List;

    invoke-static {p1}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public build()Landroidx/car/app/model/Row;
    .locals 2

    .line 654
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mTitle:Landroidx/car/app/model/CarText;

    if-eqz v0, :cond_8

    .line 658
    iget-boolean v0, p0, Landroidx/car/app/model/Row$Builder;->mIsBrowsable:Z

    if-eqz v0, :cond_3

    .line 659
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mToggle:Landroidx/car/app/model/Toggle;

    if-nez v0, :cond_2

    .line 662
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mOnClickDelegate:Landroidx/car/app/model/OnClickDelegate;

    if-eqz v0, :cond_1

    .line 666
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mActions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    .line 667
    :cond_0
    const-string p0, "A browsable row must not have a secondary action set"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    :goto_0
    const/4 p0, 0x0

    return-object p0

    .line 663
    :cond_1
    const-string p0, "A browsable row must have its onClickListener set"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 660
    :cond_2
    const-string p0, "A browsable row must not have a toggle set"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 673
    :cond_3
    :goto_1
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mToggle:Landroidx/car/app/model/Toggle;

    if-eqz v0, :cond_7

    .line 674
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mOnClickDelegate:Landroidx/car/app/model/OnClickDelegate;

    if-nez v0, :cond_6

    .line 679
    iget v0, p0, Landroidx/car/app/model/Row$Builder;->mDecoration:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_5

    .line 684
    iget-object v0, p0, Landroidx/car/app/model/Row$Builder;->mActions:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_4

    goto :goto_2

    .line 685
    :cond_4
    const-string p0, "If a row contains a toggle, it must not have a secondary action set"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 680
    :cond_5
    const-string p0, "If a row contains a toggle, it must not have a numeric decoration set"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 675
    :cond_6
    const-string p0, "If a row contains a toggle, it must not have an onClickListener set"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 690
    :cond_7
    :goto_2
    new-instance v0, Landroidx/car/app/model/Row;

    invoke-direct {v0, p0}, Landroidx/car/app/model/Row;-><init>(Landroidx/car/app/model/Row$Builder;)V

    return-object v0

    .line 655
    :cond_8
    const-string p0, "A title must be set on the row"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public setBrowsable(Z)Landroidx/car/app/model/Row$Builder;
    .locals 0

    .line 601
    iput-boolean p1, p0, Landroidx/car/app/model/Row$Builder;->mIsBrowsable:Z

    return-object p0
.end method

.method public setImage(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/model/Row$Builder;
    .locals 1

    .line 491
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Landroidx/car/app/model/Row$Builder;->setImage(Landroidx/car/app/model/CarIcon;I)Landroidx/car/app/model/Row$Builder;

    move-result-object p0

    return-object p0
.end method

.method public setImage(Landroidx/car/app/model/CarIcon;I)Landroidx/car/app/model/Row$Builder;
    .locals 1

    .line 518
    sget-object v0, Landroidx/car/app/model/constraints/CarIconConstraints;->UNCONSTRAINED:Landroidx/car/app/model/constraints/CarIconConstraints;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v0, p1}, Landroidx/car/app/model/constraints/CarIconConstraints;->validateOrThrow(Landroidx/car/app/model/CarIcon;)V

    .line 519
    iput-object p1, p0, Landroidx/car/app/model/Row$Builder;->mImage:Landroidx/car/app/model/CarIcon;

    .line 520
    iput p2, p0, Landroidx/car/app/model/Row$Builder;->mRowImageType:I

    return-object p0
.end method

.method public setOnClickListener(Landroidx/car/app/model/OnClickListener;)Landroidx/car/app/model/Row$Builder;
    .locals 0
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "MissingGetterMatchingBuilder",
            "ExecutorRegistration"
        }
    .end annotation

    .line 616
    invoke-static {p1}, Landroidx/car/app/model/OnClickDelegateImpl;->create(Landroidx/car/app/model/OnClickListener;)Landroidx/car/app/model/OnClickDelegate;

    move-result-object p1

    iput-object p1, p0, Landroidx/car/app/model/Row$Builder;->mOnClickDelegate:Landroidx/car/app/model/OnClickDelegate;

    return-object p0
.end method

.method public setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/Row$Builder;
    .locals 1

    .line 364
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Ljava/lang/CharSequence;

    invoke-static {p1}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object p1

    .line 365
    invoke-virtual {p1}, Landroidx/car/app/model/CarText;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 368
    sget-object v0, Landroidx/car/app/model/constraints/CarTextConstraints;->TEXT_AND_ICON:Landroidx/car/app/model/constraints/CarTextConstraints;

    invoke-virtual {v0, p1}, Landroidx/car/app/model/constraints/CarTextConstraints;->validateOrThrow(Landroidx/car/app/model/CarText;)V

    .line 369
    iput-object p1, p0, Landroidx/car/app/model/Row$Builder;->mTitle:Landroidx/car/app/model/CarText;

    return-object p0

    .line 366
    :cond_0
    const-string p0, "The title cannot be null or empty"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method
