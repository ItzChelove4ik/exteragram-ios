.class public final Lcom/google/android/recaptcha/internal/zzzu;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzzu;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzzu;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzzu;

.field public static final zzd:Lcom/google/android/recaptcha/internal/zzzu;


# instance fields
.field private final zze:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzzu;

    const-string v1, "TINK"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzzu;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zza:Lcom/google/android/recaptcha/internal/zzzu;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzzu;

    const-string v1, "CRUNCHY"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzzu;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzb:Lcom/google/android/recaptcha/internal/zzzu;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzzu;

    const-string v1, "LEGACY"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzzu;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzc:Lcom/google/android/recaptcha/internal/zzzu;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzzu;

    const-string v1, "NO_PREFIX"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzzu;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzu;->zze:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzu;->zze:Ljava/lang/String;

    return-object p0
.end method
