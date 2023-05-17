Workspaces plugin
=================

<!-- Plugin description -->

Migrated from https://plugins.jetbrains.com/plugin/83-workspaces, and thanks for Chris Bartley's work.

This plugin enables you to create and manage named groups of files, or "workspaces", within a project.
You can bind a name to a set of one or more files and then open/close that set of files as a group.
You can create as many workspaces as you want and arrange them in the Workspaces tool window in any order you like.

Useful for:

* GitHub Copilot: read current file and any files opened in your IDE
* Domain Driven Design: group entity, repository, service, controller, etc. together

<!-- Plugin description end -->

# Components

* WorkspacesConfiguration: application's `PersistentStateComponent` with the state of the plugin
* WorkspacesConfigurable: application's `Configurable` with the UI for the plugin's configuration
* WorkspaceStateService: project's `PersistentStateComponent` with the state of project's `Workspace` list
* WorkspaceManager: Project service that manages the workspace operations
* WorkspaceProjectComponent: register menu on main menu bar
* WorkspacesToolWindow: Workspaces tool window

# References

* IntelliJ Platform Plugin Template: https://github.com/JetBrains/intellij-platform-plugin-template
