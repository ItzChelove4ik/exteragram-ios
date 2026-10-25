.class public final Landroidx/car/app/model/MessageTemplate$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/MessageTemplate;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field mActionList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/car/app/model/Action;",
            ">;"
        }
    .end annotation
.end field

.field mActionStrip:Landroidx/car/app/model/ActionStrip;

.field mDebugCause:Ljava/lang/Throwable;

.field mDebugMessage:Landroidx/car/app/model/CarText;

.field mDebugString:Ljava/lang/String;

.field mHeaderAction:Landroidx/car/app/model/Action;

.field mIcon:Landroidx/car/app/model/CarIcon;

.field mIsLoading:Z

.field mMessage:Landroidx/car/app/model/CarText;

.field mTitle:Landroidx/car/app/model/CarText;


# direct methods
.method public constructor <init>(Ljava/lang/CharSequence;)V
    .locals 1

    .line 426
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 221
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mActionList:Ljava/util/List;

    .line 427
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Ljava/lang/CharSequence;

    invoke-static {p1}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object p1

    iput-object p1, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mMessage:Landroidx/car/app/model/CarText;

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/model/MessageTemplate;
    .locals 2

    .line 400
    iget-boolean v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mIsLoading:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mIcon:Landroidx/car/app/model/CarIcon;

    if-nez v0, :cond_0

    goto :goto_0

    .line 401
    :cond_0
    const-string p0, "Template in a loading state can not have an icon"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 404
    :cond_1
    :goto_0
    iget-object v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mMessage:Landroidx/car/app/model/CarText;

    invoke-virtual {v0}, Landroidx/car/app/model/CarText;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_5

    .line 408
    iget-object v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mDebugString:Ljava/lang/String;

    if-nez v0, :cond_2

    const-string v0, ""

    .line 409
    :cond_2
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_3

    iget-object v1, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mDebugCause:Ljava/lang/Throwable;

    if-eqz v1, :cond_3

    .line 410
    const-string v1, "\n"

    invoke-virtual {v0, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 412
    :cond_3
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mDebugCause:Ljava/lang/Throwable;

    invoke-static {v0}, Landroid/util/Log;->getStackTraceString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 413
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_4

    .line 414
    invoke-static {v0}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mDebugMessage:Landroidx/car/app/model/CarText;

    .line 417
    :cond_4
    new-instance v0, Landroidx/car/app/model/MessageTemplate;

    invoke-direct {v0, p0}, Landroidx/car/app/model/MessageTemplate;-><init>(Landroidx/car/app/model/MessageTemplate$Builder;)V

    return-object v0

    .line 405
    :cond_5
    const-string p0, "Message cannot be empty"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method public setHeaderAction(Landroidx/car/app/model/Action;)Landroidx/car/app/model/MessageTemplate$Builder;
    .locals 2

    .line 337
    sget-object v0, Landroidx/car/app/model/constraints/ActionsConstraints;->ACTIONS_CONSTRAINTS_HEADER:Landroidx/car/app/model/constraints/ActionsConstraints;

    .line 338
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p1}, Ljava/util/Collections;->singletonList(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    .line 337
    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/ActionsConstraints;->validateOrThrow(Ljava/util/List;)V

    .line 339
    iput-object p1, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mHeaderAction:Landroidx/car/app/model/Action;

    return-object p0
.end method

.method public setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/MessageTemplate$Builder;
    .locals 1

    .line 256
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Ljava/lang/CharSequence;

    invoke-static {p1}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object p1

    iput-object p1, p0, Landroidx/car/app/model/MessageTemplate$Builder;->mTitle:Landroidx/car/app/model/CarText;

    .line 257
    sget-object v0, Landroidx/car/app/model/constraints/CarTextConstraints;->TEXT_ONLY:Landroidx/car/app/model/constraints/CarTextConstraints;

    invoke-virtual {v0, p1}, Landroidx/car/app/model/constraints/CarTextConstraints;->validateOrThrow(Landroidx/car/app/model/CarText;)V

    return-object p0
.end method
