.class public final synthetic Lcom/google/android/recaptcha/internal/zzab;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/Supplier;


# static fields
.field static final synthetic zza:Lcom/google/android/recaptcha/internal/zzab;

.field public static final synthetic zzb:Lcom/google/android/recaptcha/internal/zzab;


# instance fields
.field private final synthetic zzc:I


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzab;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzab;-><init>(I)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzab;->zzb:Lcom/google/android/recaptcha/internal/zzab;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzab;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzab;-><init>(I)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzab;->zza:Lcom/google/android/recaptcha/internal/zzab;

    return-void
.end method

.method private synthetic constructor <init>(I)V
    .locals 0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzab;->zzc:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic get()Ljava/lang/Object;
    .locals 0

    .line 1
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzab;->zzc:I

    if-eqz p0, :cond_0

    const/4 p0, 0x0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzar;->zza(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzar;

    move-result-object p0

    return-object p0

    :cond_0
    new-instance p0, Lcom/google/android/recaptcha/internal/zzae;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzae;-><init>()V

    return-object p0
.end method
