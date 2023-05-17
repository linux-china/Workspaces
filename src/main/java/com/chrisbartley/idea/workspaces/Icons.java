package com.chrisbartley.idea.workspaces;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;


public final class Icons {
    public static final Icon WORKSPACES = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/workspace.png", Icons.class);

    public static final Icon INCLUDED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/check.png", Icons.class);
    public static final Icon EXCLUDED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/noCheck.png", Icons.class);

    public static final Icon OPENED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/check.png", Icons.class);
    public static final Icon PARTIALLY_OPENED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/greyCheck.png", Icons.class);
    public static final Icon UNOPENED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/noCheck.png", Icons.class);

    public static final Icon ACTIVE_WORKSPACE = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/activeWorkspace.png", Icons.class);
    public static final Icon INACTIVE_WORKSPACE = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/inactiveWorkspace.png", Icons.class);

    public static final Icon ADD_WORKSPACE = IconLoader.getIcon("/general/add.png", Icons.class);
    public static final Icon REMOVE_WORKSPACE = IconLoader.getIcon("/general/remove.png", Icons.class);

    public static final Icon CONFIGURE_WORKSPACE = IconLoader.getIcon("/actions/properties.png", Icons.class);

    public static final Icon CLOSE_WORKSPACE = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/workspaceClosed.png", Icons.class);
    public static final Icon OPEN_WORKSPACE = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/workspaceOpen.png", Icons.class);

    public static final Icon INCLUDE_WORKSPACE_ITEM = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/include.png", Icons.class);
    public static final Icon EXCLUDE_WORKSPACE_ITEM = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/exclude.png", Icons.class);

    public static final Icon EXCLUDE_WORKSPACED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/excludeWorkspaced.png", Icons.class);

    public static final Icon WORKSPACES_CONFIGURABLE = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/workspacesConfigurable.png", Icons.class);

    public static final Icon PINNED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/pinned.png", Icons.class);
    public static final Icon UNPINNED = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/unpinned.png", Icons.class);

    public static final Icon PINNED_AND_OPEN = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/pinnedAndOpen.png", Icons.class);
    public static final Icon PINNED_AND_PARTIALLY_OPEN = IconLoader.getIcon("/com/chrisbartley/idea/workspaces/icons/pinnedAndPartiallyOpen.png", Icons.class);
}

