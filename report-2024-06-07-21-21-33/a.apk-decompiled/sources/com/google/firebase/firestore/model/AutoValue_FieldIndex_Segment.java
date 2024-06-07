package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class AutoValue_FieldIndex_Segment extends FieldIndex.Segment {
    private final FieldPath fieldPath;
    private final FieldIndex.Segment.Kind kind;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FieldIndex_Segment(FieldPath fieldPath, FieldIndex.Segment.Kind kind) {
        Objects.requireNonNull(fieldPath, "Null fieldPath");
        this.fieldPath = fieldPath;
        Objects.requireNonNull(kind, "Null kind");
        this.kind = kind;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.Segment
    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.Segment
    public FieldIndex.Segment.Kind getKind() {
        return this.kind;
    }

    public String toString() {
        return "Segment{fieldPath=" + this.fieldPath + ", kind=" + this.kind + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.Segment)) {
            return false;
        }
        FieldIndex.Segment segment = (FieldIndex.Segment) obj;
        return this.fieldPath.equals(segment.getFieldPath()) && this.kind.equals(segment.getKind());
    }

    public int hashCode() {
        return ((this.fieldPath.hashCode() ^ 1000003) * 1000003) ^ this.kind.hashCode();
    }
}
