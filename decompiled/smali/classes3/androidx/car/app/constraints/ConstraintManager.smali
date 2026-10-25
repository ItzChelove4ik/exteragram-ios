.class public Landroidx/car/app/constraints/ConstraintManager;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/Manager;


# instance fields
.field private final mCarContext:Landroidx/car/app/CarContext;

.field private final mHostDispatcher:Landroidx/car/app/HostDispatcher;


# direct methods
.method private constructor <init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)V
    .locals 0

    .line 196
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 197
    iput-object p1, p0, Landroidx/car/app/constraints/ConstraintManager;->mCarContext:Landroidx/car/app/CarContext;

    .line 198
    iput-object p2, p0, Landroidx/car/app/constraints/ConstraintManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    return-void
.end method

.method public static create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)Landroidx/car/app/constraints/ConstraintManager;
    .locals 1

    .line 193
    new-instance v0, Landroidx/car/app/constraints/ConstraintManager;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {v0, p0, p1}, Landroidx/car/app/constraints/ConstraintManager;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)V

    return-object v0
.end method
