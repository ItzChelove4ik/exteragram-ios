.class public final Landroidx/car/app/model/Tab$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/Tab;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field mContentId:Ljava/lang/String;

.field mIcon:Landroidx/car/app/model/CarIcon;

.field mTitle:Landroidx/car/app/model/CarText;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 226
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/model/Tab;
    .locals 1

    .line 209
    iget-object v0, p0, Landroidx/car/app/model/Tab$Builder;->mTitle:Landroidx/car/app/model/CarText;

    if-eqz v0, :cond_2

    .line 213
    iget-object v0, p0, Landroidx/car/app/model/Tab$Builder;->mIcon:Landroidx/car/app/model/CarIcon;

    if-eqz v0, :cond_1

    .line 217
    iget-object v0, p0, Landroidx/car/app/model/Tab$Builder;->mContentId:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 222
    new-instance v0, Landroidx/car/app/model/Tab;

    invoke-direct {v0, p0}, Landroidx/car/app/model/Tab;-><init>(Landroidx/car/app/model/Tab$Builder;)V

    return-object v0

    .line 218
    :cond_0
    const-string p0, "A content ID must be set for the tab"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    :goto_0
    const/4 p0, 0x0

    return-object p0

    .line 214
    :cond_1
    const-string p0, "A icon must be set for the tab"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 210
    :cond_2
    const-string p0, "A title must be set for the tab"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public setContentId(Ljava/lang/String;)Landroidx/car/app/model/Tab$Builder;
    .locals 1

    .line 173
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 176
    iput-object p1, p0, Landroidx/car/app/model/Tab$Builder;->mContentId:Ljava/lang/String;

    return-object p0

    .line 174
    :cond_0
    const-string p0, "The content ID cannot be null or empty"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public setIcon(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/model/Tab$Builder;
    .locals 1

    .line 197
    sget-object v0, Landroidx/car/app/model/constraints/CarIconConstraints;->DEFAULT:Landroidx/car/app/model/constraints/CarIconConstraints;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v0, p1}, Landroidx/car/app/model/constraints/CarIconConstraints;->validateOrThrow(Landroidx/car/app/model/CarIcon;)V

    .line 198
    iput-object p1, p0, Landroidx/car/app/model/Tab$Builder;->mIcon:Landroidx/car/app/model/CarIcon;

    return-object p0
.end method

.method public setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/Tab$Builder;
    .locals 1

    .line 156
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Ljava/lang/CharSequence;

    invoke-static {p1}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object p1

    .line 157
    invoke-virtual {p1}, Landroidx/car/app/model/CarText;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 160
    sget-object v0, Landroidx/car/app/model/constraints/CarTextConstraints;->TEXT_AND_ICON:Landroidx/car/app/model/constraints/CarTextConstraints;

    invoke-virtual {v0, p1}, Landroidx/car/app/model/constraints/CarTextConstraints;->validateOrThrow(Landroidx/car/app/model/CarText;)V

    .line 161
    iput-object p1, p0, Landroidx/car/app/model/Tab$Builder;->mTitle:Landroidx/car/app/model/CarText;

    return-object p0

    .line 158
    :cond_0
    const-string p0, "The title cannot be null or empty"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method
