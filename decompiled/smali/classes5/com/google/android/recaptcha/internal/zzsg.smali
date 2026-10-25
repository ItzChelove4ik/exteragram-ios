.class public final Lcom/google/android/recaptcha/internal/zzsg;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzsg;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzsg;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzsg;


# instance fields
.field private final zzd:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsg;

    const-string v1, "ENABLED"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsg;->zza:Lcom/google/android/recaptcha/internal/zzsg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsg;

    const-string v1, "DISABLED"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsg;->zzb:Lcom/google/android/recaptcha/internal/zzsg;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsg;

    const-string v1, "DESTROYED"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsg;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsg;->zzc:Lcom/google/android/recaptcha/internal/zzsg;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzsg;->zzd:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsg;->zzd:Ljava/lang/String;

    return-object p0
.end method
