.class public final Landroidx/car/app/model/TabContents$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/TabContents;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field mTemplate:Landroidx/car/app/model/Template;


# direct methods
.method public constructor <init>(Landroidx/car/app/model/Template;)V
    .locals 2

    .line 137
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 138
    sget-object v0, Landroidx/car/app/model/constraints/TabContentsConstraints;->API_7:Landroidx/car/app/model/constraints/TabContentsConstraints;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-object v1, p1

    check-cast v1, Landroidx/car/app/model/Template;

    invoke-virtual {v0, v1}, Landroidx/car/app/model/constraints/TabContentsConstraints;->validateOrThrow(Landroidx/car/app/model/Template;)V

    .line 139
    iput-object p1, p0, Landroidx/car/app/model/TabContents$Builder;->mTemplate:Landroidx/car/app/model/Template;

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/model/TabContents;
    .locals 1

    .line 109
    new-instance v0, Landroidx/car/app/model/TabContents;

    invoke-direct {v0, p0}, Landroidx/car/app/model/TabContents;-><init>(Landroidx/car/app/model/TabContents$Builder;)V

    return-object v0
.end method
