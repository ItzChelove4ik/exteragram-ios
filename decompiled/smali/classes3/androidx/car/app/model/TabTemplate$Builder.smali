.class public final Landroidx/car/app/model/TabTemplate$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/TabTemplate;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field mActiveTabContentId:Ljava/lang/String;

.field mHeaderAction:Landroidx/car/app/model/Action;

.field mIsLoading:Z

.field final mTabCallbackDelegate:Landroidx/car/app/model/TabCallbackDelegate;

.field mTabContents:Landroidx/car/app/model/TabContents;

.field final mTabs:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/car/app/model/Tab;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroidx/car/app/model/TabTemplate$TabCallback;)V
    .locals 0
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "ExecutorRegistration"
        }
    .end annotation

    .line 337
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 338
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Landroidx/car/app/model/TabTemplate$TabCallback;

    invoke-static {p1}, Landroidx/car/app/model/TabCallbackDelegateImpl;->create(Landroidx/car/app/model/TabTemplate$TabCallback;)Landroidx/car/app/model/TabCallbackDelegate;

    move-result-object p1

    iput-object p1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabCallbackDelegate:Landroidx/car/app/model/TabCallbackDelegate;

    .line 339
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabs:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public addTab(Landroidx/car/app/model/Tab;)Landroidx/car/app/model/TabTemplate$Builder;
    .locals 1

    .line 274
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 275
    iget-object v0, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabs:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public build()Landroidx/car/app/model/TabTemplate;
    .locals 3

    .line 296
    iget-object v0, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabContents:Landroidx/car/app/model/TabContents;

    if-eqz v0, :cond_0

    iget-object v0, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabs:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 297
    :goto_0
    iget-boolean v1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mIsLoading:Z

    if-eqz v1, :cond_2

    if-nez v0, :cond_1

    goto :goto_2

    .line 298
    :cond_1
    const-string p0, "Template is in a loading state but tabs are added"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    :goto_1
    const/4 p0, 0x0

    return-object p0

    :cond_2
    :goto_2
    if-nez v1, :cond_4

    if-eqz v0, :cond_3

    goto :goto_3

    .line 303
    :cond_3
    const-string p0, "Template is not in a loading state but does not contain tabs or tab contents"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_1

    :cond_4
    :goto_3
    if-eqz v0, :cond_6

    .line 308
    iget-object v1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mActiveTabContentId:Ljava/lang/String;

    if-eqz v1, :cond_5

    goto :goto_4

    .line 309
    :cond_5
    const-string p0, "Template requires setting content ID for the active tab when not in Loading state"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_1

    :cond_6
    :goto_4
    if-eqz v0, :cond_7

    .line 314
    iget-object v0, p0, Landroidx/car/app/model/TabTemplate$Builder;->mActiveTabContentId:Ljava/lang/String;

    if-eqz v0, :cond_7

    .line 315
    sget-object v1, Landroidx/car/app/model/constraints/TabsConstraints;->DEFAULT:Landroidx/car/app/model/constraints/TabsConstraints;

    iget-object v2, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabs:Ljava/util/List;

    invoke-virtual {v1, v2, v0}, Landroidx/car/app/model/constraints/TabsConstraints;->validateOrThrow(Ljava/util/List;Ljava/lang/String;)V

    .line 318
    :cond_7
    iget-boolean v0, p0, Landroidx/car/app/model/TabTemplate$Builder;->mIsLoading:Z

    if-nez v0, :cond_9

    iget-object v0, p0, Landroidx/car/app/model/TabTemplate$Builder;->mHeaderAction:Landroidx/car/app/model/Action;

    if-eqz v0, :cond_8

    goto :goto_5

    .line 319
    :cond_8
    const-string p0, "Template requires a Header Action of TYPE_APP_ICON when not in Loading state"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_1

    .line 325
    :cond_9
    :goto_5
    new-instance v0, Landroidx/car/app/model/TabTemplate;

    invoke-direct {v0, p0}, Landroidx/car/app/model/TabTemplate;-><init>(Landroidx/car/app/model/TabTemplate$Builder;)V

    return-object v0
.end method

.method public setActiveTabContentId(Ljava/lang/String;)Landroidx/car/app/model/TabTemplate$Builder;
    .locals 1

    .line 260
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 263
    iput-object p1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mActiveTabContentId:Ljava/lang/String;

    return-object p0

    .line 261
    :cond_0
    const-string p0, "The content ID cannot be null or empty"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public setHeaderAction(Landroidx/car/app/model/Action;)Landroidx/car/app/model/TabTemplate$Builder;
    .locals 2

    .line 235
    sget-object v0, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_TABS:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 236
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p1}, Ljava/util/Collections;->singletonList(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    .line 235
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/ActionsConstraints;->validateOrThrow(Ljava/util/List;)V

    .line 237
    iput-object p1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mHeaderAction:Landroidx/car/app/model/Action;

    return-object p0
.end method

.method public setTabContents(Landroidx/car/app/model/TabContents;)Landroidx/car/app/model/TabTemplate$Builder;
    .locals 0

    .line 250
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Landroidx/car/app/model/TabTemplate$Builder;->mTabContents:Landroidx/car/app/model/TabContents;

    return-object p0
.end method
