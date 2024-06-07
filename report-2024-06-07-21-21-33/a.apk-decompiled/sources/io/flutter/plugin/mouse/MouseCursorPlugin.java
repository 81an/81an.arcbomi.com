package io.flutter.plugin.mouse;

import android.view.PointerIcon;
import androidx.core.view.PointerIconCompat;
import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
import io.flutter.embedding.engine.systemchannels.MouseCursorChannel;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class MouseCursorPlugin {
    private static HashMap<String, Integer> systemCursorConstants;
    private final MouseCursorViewDelegate mView;
    private final MouseCursorChannel mouseCursorChannel;

    /* loaded from: classes2.dex */
    public interface MouseCursorViewDelegate {
        PointerIcon getSystemPointerIcon(int i);

        void setPointerIcon(PointerIcon pointerIcon);
    }

    public MouseCursorPlugin(MouseCursorViewDelegate mouseCursorViewDelegate, MouseCursorChannel mouseCursorChannel) {
        this.mView = mouseCursorViewDelegate;
        this.mouseCursorChannel = mouseCursorChannel;
        mouseCursorChannel.setMethodHandler(new MouseCursorChannel.MouseCursorMethodHandler() { // from class: io.flutter.plugin.mouse.MouseCursorPlugin.1
            @Override // io.flutter.embedding.engine.systemchannels.MouseCursorChannel.MouseCursorMethodHandler
            public void activateSystemCursor(String str) {
                MouseCursorPlugin.this.mView.setPointerIcon(MouseCursorPlugin.this.resolveSystemCursor(str));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointerIcon resolveSystemCursor(String str) {
        if (systemCursorConstants == null) {
            systemCursorConstants = new HashMap<String, Integer>() { // from class: io.flutter.plugin.mouse.MouseCursorPlugin.2
                private static final long serialVersionUID = 1;

                {
                    put("alias", Integer.valueOf(PointerIconCompat.TYPE_ALIAS));
                    Integer valueOf = Integer.valueOf(PointerIconCompat.TYPE_ALL_SCROLL);
                    put("allScroll", valueOf);
                    put("basic", 1000);
                    put("cell", Integer.valueOf(PointerIconCompat.TYPE_CELL));
                    put("click", 1002);
                    put("contextMenu", 1001);
                    put("copy", Integer.valueOf(PointerIconCompat.TYPE_COPY));
                    Integer valueOf2 = Integer.valueOf(PointerIconCompat.TYPE_NO_DROP);
                    put("forbidden", valueOf2);
                    put("grab", Integer.valueOf(PointerIconCompat.TYPE_GRAB));
                    put("grabbing", Integer.valueOf(PointerIconCompat.TYPE_GRABBING));
                    put("help", Integer.valueOf(PointerIconCompat.TYPE_HELP));
                    put("move", valueOf);
                    put(DevicePublicKeyStringDef.NONE, 0);
                    put("noDrop", valueOf2);
                    put("precise", Integer.valueOf(PointerIconCompat.TYPE_CROSSHAIR));
                    put("text", Integer.valueOf(PointerIconCompat.TYPE_TEXT));
                    Integer valueOf3 = Integer.valueOf(PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW);
                    put("resizeColumn", valueOf3);
                    Integer valueOf4 = Integer.valueOf(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW);
                    put("resizeDown", valueOf4);
                    Integer valueOf5 = Integer.valueOf(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
                    put("resizeUpLeft", valueOf5);
                    Integer valueOf6 = Integer.valueOf(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW);
                    put("resizeDownRight", valueOf6);
                    put("resizeLeft", valueOf3);
                    put("resizeLeftRight", valueOf3);
                    put("resizeRight", valueOf3);
                    put("resizeRow", valueOf4);
                    put("resizeUp", valueOf4);
                    put("resizeUpDown", valueOf4);
                    put("resizeUpLeft", valueOf6);
                    put("resizeUpRight", valueOf5);
                    put("resizeUpLeftDownRight", valueOf6);
                    put("resizeUpRightDownLeft", valueOf5);
                    put("verticalText", Integer.valueOf(PointerIconCompat.TYPE_VERTICAL_TEXT));
                    put("wait", Integer.valueOf(PointerIconCompat.TYPE_WAIT));
                    put("zoomIn", Integer.valueOf(PointerIconCompat.TYPE_ZOOM_IN));
                    put("zoomOut", Integer.valueOf(PointerIconCompat.TYPE_ZOOM_OUT));
                }
            };
        }
        return this.mView.getSystemPointerIcon(systemCursorConstants.getOrDefault(str, 1000).intValue());
    }

    public void destroy() {
        this.mouseCursorChannel.setMethodHandler(null);
    }
}
