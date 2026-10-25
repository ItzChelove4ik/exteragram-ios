.class public final Lcom/google/android/recaptcha/internal/zzyg;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzyg;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzyg;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzyg;

.field public static final zzd:Lcom/google/android/recaptcha/internal/zzyg;


# instance fields
.field private final zze:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyg;

    const-string v1, "TINK"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzyg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyg;

    const-string v1, "CRUNCHY"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzyg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zzb:Lcom/google/android/recaptcha/internal/zzyg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyg;

    const-string v1, "LEGACY"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzyg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyg;

    const-string v1, "NO_PREFIX"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzyg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyg;->zzd:Lcom/google/android/recaptcha/internal/zzyg;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzyg;->zze:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyg;->zze:Ljava/lang/String;

    return-object p0
.end method
