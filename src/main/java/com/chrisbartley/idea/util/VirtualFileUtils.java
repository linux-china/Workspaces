package com.chrisbartley.idea.util;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class VirtualFileUtils {
    public static List<String> getUrls(VirtualFile[] virtualFiles) {
        return getUrls(Arrays.asList(virtualFiles));
    }


    public static List<String> getUrls(List<VirtualFile> virtualFiles) {
        List<String> urls = new ArrayList<>();
        if (virtualFiles != null) {
            for (VirtualFile virtualFile : virtualFiles) {
                urls.add(virtualFile.getUrl());
            }
        }
        return urls;
    }

    public static void closeFileByUrl(FileEditorManager fileEditorManager, String url) {
        if (fileEditorManager != null && url != null) {
            VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
            if (virtualFile != null && fileEditorManager.isFileOpen(virtualFile)) {
                fileEditorManager.closeFile(virtualFile);
            }
        }
    }
}
