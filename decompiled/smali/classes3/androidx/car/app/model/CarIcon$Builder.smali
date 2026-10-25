.class public final Landroidx/car/app/model/CarIcon$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/model/CarIcon;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private final mIcon:Landroidx/core/graphics/drawable/IconCompat;

.field private mTint:Landroidx/car/app/model/CarColor;

.field private final mType:I


# direct methods
.method public constructor <init>(Landroidx/core/graphics/drawable/IconCompat;)V
    .locals 1

    .line 417
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 418
    sget-object v0, Landroidx/car/app/model/constraints/CarIconConstraints;->UNCONSTRAINED:Landroidx/car/app/model/constraints/CarIconConstraints;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v0, p1}, Landroidx/car/app/model/constraints/CarIconConstraints;->checkSupportedIcon(Landroidx/core/graphics/drawable/IconCompat;)Landroidx/core/graphics/drawable/IconCompat;

    const/4 v0, 0x1

    .line 419
    iput v0, p0, Landroidx/car/app/model/CarIcon$Builder;->mType:I

    .line 420
    iput-object p1, p0, Landroidx/car/app/model/CarIcon$Builder;->mIcon:Landroidx/core/graphics/drawable/IconCompat;

    const/4 p1, 0x0

    .line 421
    iput-object p1, p0, Landroidx/car/app/model/CarIcon$Builder;->mTint:Landroidx/car/app/model/CarColor;

    return-void
.end method


# virtual methods
.method public build()Landroidx/car/app/model/CarIcon;
    .locals 3

    .line 391
    new-instance v0, Landroidx/car/app/model/CarIcon;

    iget-object v1, p0, Landroidx/car/app/model/CarIcon$Builder;->mIcon:Landroidx/core/graphics/drawable/IconCompat;

    iget-object v2, p0, Landroidx/car/app/model/CarIcon$Builder;->mTint:Landroidx/car/app/model/CarColor;

    iget p0, p0, Landroidx/car/app/model/CarIcon$Builder;->mType:I

    invoke-direct {v0, v1, v2, p0}, Landroidx/car/app/model/CarIcon;-><init>(Landroidx/core/graphics/drawable/IconCompat;Landroidx/car/app/model/CarColor;I)V

    return-object v0
.end method
