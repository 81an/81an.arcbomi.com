package com.google.firebase.database.core.utilities;

import com.google.firebase.database.snapshot.ChildKey;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TreeNode<T> {
    public Map<ChildKey, TreeNode<T>> children = new HashMap();
    public T value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toString(String str) {
        String str2 = str + "<value>: " + this.value + "\n";
        if (this.children.isEmpty()) {
            return str2 + str + "<empty>";
        }
        for (Map.Entry<ChildKey, TreeNode<T>> entry : this.children.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(str);
            sb.append(entry.getKey());
            sb.append(":\n");
            sb.append(entry.getValue().toString(str + "\t"));
            sb.append("\n");
            str2 = sb.toString();
        }
        return str2;
    }
}
